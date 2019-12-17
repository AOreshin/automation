#!/bin/sh

mkdir $HOME/secrets
gpg --quiet --batch --yes --decrypt --passphrase="$passphrase" \
--output $HOME/secrets/keys.gpg keys.gpg.gpg