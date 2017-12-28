(ns url-shorten.db
  (:require [piton.core :as p]
            [environ.core :refer [env]]
            [hugsql.core :as hug]))

(when (and (env :database-url) (env :database-user))
  (p/migrate
   (str "jdbc:postgresql://" (env :database-url) "/" (env :database-name))
   (env :database-user)
   (env :database-password)))

(def db-spec
  {:dbtype "postgresql"
   :dbname (env :database-name)
   :host (env :database-url)
   :user (env :database-user)
   :password (env :database-password)})

(hug/def-db-fns "sql/full_urls.sql")
