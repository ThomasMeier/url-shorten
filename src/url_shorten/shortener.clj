(ns url-shorten.shortener
  (:require [schema.core :as s]
            [ring.util.http-response :refer :all]
            [hashids.core :as h]
            [url-shorten.db :as db]
            [environ.core :refer [env]])
  (:import (org.apache.commons.validator.routines UrlValidator)))

;; Validation here does not detect if bad words or javascript
;; injections are present. Would be nice to add though!
(def url-validator
  (UrlValidator. (into-array ["http" "https"])))

(def h-opt {:salt (:hashids-salt env)})

(defn- new-short-url! [full-url]
  (let [url-id (:id (db/insert-full-url db/db-spec {:full_url full-url}))]
    (h/encode h-opt url-id)))

(defn shorten-url [full-url]
  (if (.isValid url-validator full-url)
    (ok
     {:url_key (new-short-url! full-url)
      :full_url full-url})
    (unprocessable-entity)))

(defn try-url-key [url-key]
  (let [url-data (db/get-full-url-from-id
                  db/db-spec
                  {:id (first (h/decode h-opt url-key))})]
    (if url-data
      (moved-permanently (:full_url url-data))
      (not-found))))
