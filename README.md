# url-shorten

A small application that shortens URLs using compojure-api.

## Usage

### Run the application locally

Copy `profiles.clj.example` to `profiles.clj` and add your database credentails, then:

`lein ring server`

### Run the tests

Copy `profiles.clj.example` to `profiles.clj` and add your database credentails, then:

`lein test`

### Packaging and running as standalone jar

```
lein do clean, ring uberjar
java -jar target/url-shorten-server.jar
```

### Packaging as war

`lein ring uberwar`

## License

Copyright © Thomas Meier 2017
