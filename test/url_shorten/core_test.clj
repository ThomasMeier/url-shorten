(ns url-shorten.core-test
  (:require [cheshire.core :as cheshire]
            [clojure.test :refer :all]
            [url-shorten.handler :refer :all]
            [ring.mock.request :as mock]
            [schema.core :as s]))

(defn parse-body [body]
  (cheshire/parse-string (slurp body) true))

(def test-full-url
  {:full_url "https://google.com/"})

(deftest test-shortening
  (testing "Test POST request to /shorten returns expected response"
    (let [response (app (-> (mock/request :post "/shorten")
                            (mock/content-type "application/json")
                            (mock/body (cheshire/generate-string test-full-url))))
          body     (parse-body (:body response))]
      (is (= (:status response) 200))
      (is (s/validate ShortenedUrl body))))
  (testing "Test GET request to /{url-key} returns expected response"
    (let [post-response (app (-> (mock/request :post "/shorten")
                                 (mock/content-type "application/json")
                                 (mock/body (cheshire/generate-string test-full-url))))
          url-key (:url_key (parse-body (:body post-response)))
          response (app (-> (mock/request :get (str "/" url-key))))]
      (is (= (:status response) 301)))))
