#!/bin/sh

# Don't forget to give values to OLD_EMAIL / CORRECT_NAME / CORRECT_EMAIL

git filter-branch --env-filter '
OLD_EMAIL="andreinicolinciobanu@deloitte.co.uk"
CORRECT_NAME="nomemory"
CORRECT_EMAIL="gnomemory@yahoo.com"
if [ "$GIT_COMMITTER_EMAIL" = "$OLD_EMAIL" ]
then
    export GIT_COMMITTER_NAME="$CORRECT_NAME"
    export GIT_COMMITTER_EMAIL="$CORRECT_EMAIL"
fi
if [ "$GIT_AUTHOR_EMAIL" = "$OLD_EMAIL" ]
then
    export GIT_AUTHOR_NAME="$CORRECT_NAME"
    export GIT_AUTHOR_EMAIL="$CORRECT_EMAIL"
fi
' --tag-name-filter cat -- --branches --tags