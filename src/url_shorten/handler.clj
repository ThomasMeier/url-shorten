(ns url-shorten.handler
  (:require [compojure.api.sweet :refer :all]
            [ring.util.http-response :refer :all]
            [schema.core :as s]
            [url-shorten.shortener :as short]))

(s/defschema NewUrl
  {:full_url s/Str})

;; Does not include any analytics, would need to be built in
(s/defschema ShortenedUrl
  {:url_key s/Str
   :full_url s/Str})

(def app
  (api
    {:swagger
     {:ui "/swagger"
      :spec "/swagger.json"
      :data {:info {:title "Url-shortener"
                    :description "A URL Shortener using Compojure API"}
             :tags [{:name "api", :description "Shorten/Expand a URL"}]}}}
    (context "/" []
      :tags ["api"]
      (POST "/shorten" []
        :return ShortenedUrl
        :body [new-url NewUrl]
        :summary "Creates a new shortened url"
        (short/shorten-url (:full_url new-url)))
      (GET "/:url-id" []
        :path-params [url-id :- String]
        :summary "Expands URL ID and redirects user"
        :no-doc true
        (short/try-url-key url-id)))))
