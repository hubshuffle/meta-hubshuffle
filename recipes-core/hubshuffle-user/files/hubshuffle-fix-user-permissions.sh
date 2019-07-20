#!/usr/bin/env bash

USERNAME="hubshuffle"

OWNER=$(stat -c '%U' /home/${USERNAME})

[ "${OWNER}" != "${USERNAME}" ] && chown -R hubshuffle:hubshuffle "/home/${USERNAME}"
