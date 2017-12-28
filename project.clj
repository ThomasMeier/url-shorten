 (defproject url-shorten "0.1.0"
   :description "URL Shortener on top of compojure-api"
   :dependencies [[org.clojure/clojure "1.8.0"]
                  [metosin/compojure-api "1.1.11"]
                  [commons-validator/commons-validator "1.6"]
                  [piton "0.1.1"]
                  [jstrutz/hashids "1.0.1"]
                  [com.layerware/hugsql "0.4.8"]
                  [org.postgresql/postgresql "42.1.4"]
                  [environ "1.1.0"]
                  [ring/ring-mock "0.3.0"]]
   :plugins [[lein-ring "0.12.2"]
             [lein-environ "1.1.0"]
             [lein-piton "0.1.1"]]
   :ring {:handler url-shorten.handler/app}
   :uberjar-name "url-shorten-server.jar"
   :profiles {:dev {:dependencies [[javax.servlet/javax.servlet-api "3.1.0"]
                                   [cheshire "5.5.0"]
                                   [ring/ring-mock "0.3.0"]]}})
