# WAT?

This is a reimplementation of [Gary Bernhardts](https://github.com/garybernhardt) editor 
which he presented in his [Text editor from scratch](https://www.destroyallsoftware.com/screencasts/catalog/text-editor-from-scratch) screen cast. If you haven't yet [subscribed](https://www.destroyallsoftware.com/screencasts/users/sign_up) to Destroy all software, please do.

## Goals

The goal of this repo is to have a starting point for playing around with either editor implementation
or Clojurescript. It is never meant to be used for actual editing.

## Installation

To be able to hack on this, you need to install [Planck](https://github.com/mfikes/planck), a nifty
Clojurescript REPL that you can read all about [here](http://planck-repl.org).

On a mac, this is done by

```sh
$ brew install planck
```

On Ubuntu, you install it by doing

```sh
$ sudo add-apt-repository ppa:mfikes/planck
$ sudo apt-get update
$ sudo apt-get install planck
```

If you're on Windows, you're on your own.

## Run

In order to run the editor, you simply do

```sh
$ ./run.sh a-file
```

## Ideas to play with

Please have a look at the [issues](https://github.com/slipset/editor/issues) where I've collected some 
ideas.
