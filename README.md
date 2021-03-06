[![Build Status](https://travis-ci.org/tatitati/play_slick_project.svg?branch=master)](https://travis-ci.org/tatitati/play_slick_project)



![Build history](https://buildstats.info/travisci/chart/tatitati/play_slick_project?branch=master)


# Research

- [ ] How to run before/after (setUp/teardDOwn) with ScalaTest? --> getting "no application started error"
- [x] ~Implement different style of dependency injection (nottations, and explicit binding)~
- [ ] Deploy using AWS Benstalk instead of Boxfuse



## Set up

```
sbt reload update
```

## Run

```
sbt run
```

## run tests

```
sbt test
```

## Deploy to AWS with Boxfuse

``` 
sbt clean dist
boxfuse run -env=prod -healthcheck.path=/hello
```


## Some stuff to play

http://localhost:9000

http://localhost:9000/hello

http://localhost:9000/detail

http://localhost:9000/list


**Using DI:**

http://localhost:9000/say  -> this route is using a service injected with Guice (DI)

http://localhost:9000/sayenglish  -> this route is using a service injected with Guice (DI)


**Using slick:**

http://localhost:9000/create-db   -> play is so shit that only can create the db once is running through the UI (shame)
http://localhost:9000/insert


# Gitter:
[![Join the chat in Scala](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/explore/tags/curated:scala,scala) => Scala

[![Join the chat in Scala](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/slick/slick) => Slick


[![Join the chat in Scala](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/playframework/playframework)
=> Play

