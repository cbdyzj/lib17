#!/usr/bin/env bash
cd "$(dirname "$0")" || exit 1

# shellcheck disable=SC2120
saveLine1() {
  local saved=0

  while read -r line
  do
    if [[ $saved = 0 && "$line" =~ PING ]]; then
      echo "$line" > line1.log
      saved=1
    fi
    echo "$line"
  done < "${1:-/dev/stdin}"
}

ping localhost | saveLine1
