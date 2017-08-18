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

## A little something on developing this editor

The keybindings in this editor are sort of emacs-based. Which means that
you press `C-n`, `C-p` for next line and previous line, respectively.

Now, for this to work you need to be able to insert _control characters_ into
the source. In emacs inserting `C-a` into your file is done by first pressing `C-q` and then `c-a`.
In vi you'd do this by first going into insert-mode, and then pressing `C-v` followed by `a`.

In other, inferior, editors, this seems to be a royal PITA, most of them cannot even display
control characters correctly.

## Ideas to play with

Please have a look at the [issues](https://github.com/slipset/editor/issues) where I've collected some 
ideas.
