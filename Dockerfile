FROM clojure
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app
COPY project.clj /usr/src/app/
RUN lein uberjar
COPY . /usr/src/app
EXPOSE 3000
CMD ["java", "-jar", "/usr/src/app/target/url-shorten-server.jar"]
