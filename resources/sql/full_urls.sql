-- :name insert-full-url :<! :1
-- :doc insert a new long url and return insert ID
INSERT INTO full_urls (full_url) VALUES (:full_url) RETURNING id

-- :name get-full-url-from-id :? :1
-- :doc gets a full url from a given id
SELECT full_url FROM full_urls WHERE id = :id
