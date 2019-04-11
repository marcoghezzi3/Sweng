#!/bin/bash

# ###########################################################################
#
# Una volta effettuato il clone del repository,
# il driver effettua cd nella directory del progetto ed esegue il comando:
# bash start.sh email_unimi username_bitbucket username_bitbucket_compagno
#
# ###########################################################################

git config alias.pair '!([ "$1" = "swap" ] && UNO="$(git config user.name1)" && DUE="$(git config user.name2)" && git config user.name1 "$DUE" && git config user.name2 "$UNO" && git config user.name "$DUE -- $UNO")|| ([ -n "$1" ] && [ -n "$2" ] && git config  user.name1 "$1"  && git config user.name2 "$2" && git config user.name "$1 -- $2") || git config user.name'

git config alias.hist "log --pretty=format:'%Cred%h%Creset %C(bold blue)<%an>%Creset%C(yellow)%d%Creset %Cgreen(%cr)%Creset%n%w(80,8,8)%s' --graph"

[ -n "$1" ] && git config --global user.email "$1"

git pair "$2" "$3"
