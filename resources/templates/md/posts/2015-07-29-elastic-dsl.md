{:title "Elasticsearch DSL-DSL"
 :layout :post
 :tags ["clojure" "elasticsearch" "dsl"]}


Elasticsearch is a search server based on Apache Lucene. As a developer it is
easy to use, has an expressive query DSL and all is based on JSON
serialization. Often though I find myself in a position where I need to adapt
queries frequently and non-trivially, say in a demonstration in front of
customers or product owners.

The questions are mostly similar: "what if I also filter for X", "how does the
ranking change, when I add a freshness function", "do I get a better result if
I boost document types Y" and so on. While these are easy to add, two things
bother me: first, I need to come up with the queries during a presentation,
which adds pauses to the meetings. When I'm finished with the query the
discussion has evolved. Second, I don't want to be the enabler. If they can
find out what they want without me it also means a faster feedback loop for
them. In brainstorming sessions it is easy to focus on arguments and skip the
sometimes lengthy query finding pauses. Win win for everyone it seems.

For this I have startet working on **meta-DSLs** for Elasticsearch projects.
The idea is simple: given a base query I want to be able to quickly alter or
enhance it using simple functions that are aligned with the mapping and index
structure. Given my current addiction towards Clojure, this is what I've come
up with:

```clojure
(query
 (freshness "1h")
 (tags ["politics" "sports"])
 (prefer-category {"politics" 2,
                   "sports" 0.5}))
```

Ok, it's not yet a graphical interface, but it is a start. And it's intuitive.
After demonstrating this to customers a few times they like it and request more
features. Their own feedback loop has shortened considerably. And the best of
it is that I am out of the loop and can focus on adding features.

In this example the domain will be news articles. They have a title, tags, a
published time and categories. Something like this:

```clojure
{
    :title "The news"
    :tags ["obama" "kerry" "merkel"]
    :timestamp "2015-07-28T10:00:00Z"
    :category ["politics"]
}
```

This post is my story of how I implemented this. Publishing this as a library
might be an idea but in the end all of this is tied to an exact mapping, index
structure and use case. If there is interest though, starting something similar
in a library could be interesting, if there is interest.

## DSLs in Clojure

Creating a Domain Specific Language is pretty straight forward in Clojure
assuming you expose Clojure or Lisp syntax to the user. Using the clojure
reader and `eval` parsing a DSL into Clojure code is simple and defining the
DSL itself does then only involve implementing the functions.

In the next part I focus on the DSL implementation itself and the functions for
manipulating the query. In the last section, parsing and evaluating the DSL
into a real Elasticsearch query finishes.


## The DSL

For the custom DSL I started with a base query structure upon which all other
functions build. It has four parts: query, scoring, filtering and aggregations:

```clojure
(def default-query
 {:query
  {:filtered
   {:query {:function_score {:query {}
                             :functions []}}
    :filter {:bool {:must []
                    :must_not []}}}}
  :aggregations {}})
```

For all functions I am assuming the query to be the first argument in all
functions working with it. This simplifies the code later on as I can use the
`thread-first` macro to chain the individual function call.

Defining a function to add a `query` and for adding aggregations is straight
forward and does not even need a helper function:

```clojure
(defn- set-query
 "Given a valid ES query `q` add this to the generated query and return the
  new version."
 [query q]
 (assoc-in query [:query :filtered :query :function_score :query] q))

(defn- add-aggregation
 "Add a new aggregation to the query"
 [query agg]
 (assoc-in query [:query :aggregations] agg))
```

To work with this data structure a few helper methods come in handy when
developing the individual DSL functions. The first function helps when
manipulating lists in a nested map. Basically each scoring function or filter
needs to be added like this:

```clojure
(defn- append-in-nested-list
 "Given a map, append a new element to a nested  list."
 [q ks elm]
 (assoc-in q                        ; the query
           ks                       ; the list of keys in the query
           (apply conj              ; append
                  (get-in q ks)     ; to the list
                  elm)))            ; the new element
```

With this basic function adding more expressive functions to manipulate the
specific parts of the query are easy:

```clojure
(defn- add-function-score-function
 "Add a function score function to the query"
 [query fs-function]
 (append-in-nested-list query
                        [:query :filtered :query :function_score :functions]
                        [fs-function]))

(defn- add-must-filter
 "Add a must filter to the query"
 [query must-filter]
 (append-in-nested-list query
                        [:query :filtered :filter :bool :must]
                        [must-filter]))

(defn- add-must-not-filter
 "Add a must filter to the query"
 [query must-filter]
 (append-in-nested-list query
                        [:query :filtered :filter :bool :must_not]
                        [must-filter]))
```

### DSL functions

The individual functions now basically compose the DSL. Being able to add
`(q "merkel")` is translated into the following Clojure function:

```clojure
(defn q
  "Simple query"
  [query user-query]
  (set-query query
             {:query_string {:query user-query
                             :default_operator "AND"}}))
```

Filtering for tags in our dataset (`(tags ["merkel"])`) is equally trivial:

```clojure
(defn tags
  "Filter for a list of tags"
  [query tags]
  (add-must-filter query {:terms {:tags tags}}))
```

Freshness seems more complicated but in the end I can simply add a function
score function using an [exponential decay](). With this the user can even
change parameters and see the effects:

```clojure
(defn freshness
  "Add freshness preferences to the query. When called with query and hours as
   arguments"
  [query hours]
  (add-function-score-function query
                               {:exp {:publishTime {:decay 0.9
                                                    :scale hours}}}))
```

Prefering categories over other categories is another function score function.
Basically I add a boost (`weight`) to all documents matching a certain query:

```clojure
(defn prefer-category
  "Prefer categories over all other categories."
  [query category-preferences]
  (let [nested-keys [:query :filtered :query :function_score :functions]
        functions (map (fn[x] {:filter {:term {:category (first x)}}
                               :weight (second x)})
                       (seq category-preferences))
        existing (get-in query nested-keys)]
    (assoc-in query nested-keys (apply conj existing functions))))
```

Aggregations help in understanding the data there is. Classical example in this
case would be getting the number of documents in the result set in a category.
In ES this is a simple terms aggregation:

```clojure
(defn aggregate-categories
 "Aggregate the result by categories."
 [query]
 (add-aggregation query {:terms {:field :category}}))
```

To tie everything up I need to be able to wrap all functions into one
expression. For this I create a new macro called `query`:

```clojure
(defmacro query [& body]
`(-> default-query
     ~@body))
```

Using this macro a query can now be defined like this:

```clojure
(def simple-query (query
                   (q "test"))
```

## Parsing the DSL

Doing this is Clojure is nice and easy for me but then again I want the PO not
to contact me about getting into the repl. So in the final step I need a
function that converts a string to Clojure code. First I need to parse the
string using `read-string` and then I can `eval` the resulting code. For this
to work as expected I need to set the special var `*ns*` to the namespace of my
DSL functions above using the `the-ns` function.

```clojure
(ns demo.dsl)

(defn dsl
 "Compile the DSL string into code"
 [dsl-string]
 (binding [*ns* (the-ns 'demo.dsl-functions)
           *read-eval* false]
  (eval (read-string dsl-string))))
```

The `binding` form binds the special var `*ns*` to the namespace containing my
dsl functions. I also bind `*read-eval*` to false and by this disable the
`eval` function inside the string. The parsed string will have access to all
functions declared in there. `read-string` converts a string into Clojure code
and `eval` will execute it. In this case it will simply return the final
Elasticsearch query.

## Result

In essence this allows me to have a web frontend where a user can input
the query from the beginning

```clojure
(query
 (freshness "1h")
 (tags ["politics" "sports"])
 (prefer-category {"politics" 2,
                   "sports" 0.5}))
```

get back the equivalent Elasticsearch query:

```clojure
{:query
 {:filtered
  {:query
   {:function_score
    {:query {},
     :functions
     [{:exp {:publishTime {:decay 0.9, :scale "1h"}}}
      {:filter {:term {:category "politics"}}, :weight 2}
      {:filter {:term {:category "sports"}}, :weight 0.5}]}},
   :filter
   {:bool
    {:must [{:terms {:tags ["politics" "sports"]}}], :must_not []}}}},
 :aggregations {}}
```

which I can execute in the backend and display the results. With all the domain
functions in place I can then keep on improving the DSL or the frontend and
enable the PO to experiment at lot easier without my direct involvement, at
least in parts that I am not really interested in.
