[![Travis](https://travis-ci.org/Synesso/scala-stellar-sdk.svg?branch=master)](https://travis-ci.org/Synesso/scala-stellar-sdk)
[![codecov](https://codecov.io/gh/Synesso/scala-stellar-sdk/branch/master/graph/badge.svg)](https://codecov.io/gh/Synesso/scala-stellar-sdk)

[![Download](https://api.bintray.com/packages/synesso/mvn/scala-stellar-sdk/images/download.svg)](https://bintray.com/synesso/mvn/scala-stellar-sdk/_latestVersion)

# Stellar SDK for Scala

A Scala SDK for the [Stellar network](https://www.stellar.org/). It is a work in [progress](#Progress) with a target of being fully functional
by March 15, 2018. Contributions are welcome.

## Benefits

A Scala developer would choose this SDK over the Java SDK because:

* `Option`s, not nulls
* `Try`s, not exceptions
* `Future`s for all network operations
* Encapsulation of paged responses into `Stream`s
* No builder patterns, just case classes
* Explicit type hierarchies instead of meaningful primitives
* Test coverage: Generative testing using scalacheck with the goal of 100% coverage
* Perform network operations on the terminal through the scala REPL

## Installation

In your `build.sbt`

```
resolvers += "scala-stellar-sdk-repo" at "https://dl.bintray.com/synesso/mvn"

libraryDependencies +=  "stellar.scala.sdk" %% "scala-stellar-sdk" % "0.0.1.5"
```

## Examples

All of the following examples use the [Ammonite REPL](http://ammonite.io/). After launching `amm`, fetch and import the
Stellar SDK for Scala.

```
interp.repositories() ++= Seq(coursier.MavenRepository("https://dl.bintray.com/synesso/mvn/"))

import $ivy.`stellar.scala.sdk::scala-stellar-sdk:0.0.1.5`
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent._
import stellar.sdk._
import stellar.sdk.resp._
```

### Accounts

#### Creating and funding a test account

```
val kp = KeyPair.random
TestNetwork.fund(kp) // Future[FundTestAccountResponse]
```

#### Checking the status of an account

```
TestNetwork.account(kp) // Future[AccountResp]
```

### Assets

#### Fetching a stream of all assets

```
TestNetwork.assets // Future[Stream[AssetResp]]
```

## Progress

```
[✓] Operations
[✓] Transactions
[ ] Endpoints
  [✓] Account details
  [ ] Assets
    [🚀] Unfiltered - todo increase batch size in Page
    [ ] By code
    [ ] By issuer
  [ ] Data for account
  [ ] Effects
    [ ] Unfiltered stream
    [ ] By account
    [ ] By ledger
    [ ] By operation
    [ ] By transaction
  [ ] Ledgers
  [ ] Ledger details
  [ ] Offers for account
  [ ] Operations
    [ ] Unfiltered stream
    [ ] By account
    [ ] By ledger
    [ ] By transaction
  [ ] Operation details
  [ ] Orderbook details
  [ ] Payment paths
  [ ] Payments
    [ ] Unfiltered stream
    [ ] By account
    [ ] By ledger
    [ ] By transaction
  [ ] Trade aggregations
  [ ] Trades
    [ ] Unfiltered
    [ ] By orderbook
  [ ] Transactions
    [ ] Unfiltered
    [ ] By orderbook
    [ ] By account
    [ ] By ledger
  [ ] Post transaction
```
