-- :name save-message! :! :n
-- :doc creates a new message using name and msg keys
INSERT INTO guestbook
(name, message)
VALUES (:name, :message)
-- :name get-messages :? :*
-- :doc selects all available messages
SELECT * from guestbook
