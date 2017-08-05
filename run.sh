#!/bin/bash

/bin/stty raw
planck -c src -m editor.core $@
/bin/stty -raw
 

