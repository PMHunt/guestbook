(ns guestbook.routes.home
  (:require
   [guestbook.layout :as layout]
   [guestbook.db.core :as db]
   [clojure.java.io :as io]
   [guestbook.middleware :as middleware]
   [ring.util.response]
   [ring.util.http-response :as response]))

(defn home-page [request]
  (layout/render request "home.html" {:messages (db/get-messages)}))

(defn about-page
  "Slurps markdown for Selmer filter defined in layout.clj and used in *.html"
  [request]
  (layout/render request "about.html" {:docs (-> "docs/docs.md"
                                                 io/resource
                                                 slurp)}))

(defn save-message! [{:keys [params]}]
  (db/save-message! params)
-  (response/found "/"))

(defn home-routes []
  [""
   {:middleware [middleware/wrap-csrf
                 middleware/wrap-formats]}
   ["/" {:get home-page}]
   ["/message" {:post save-message!}]
   ["/about" {:get about-page}]])
