#!/usr/bin/env node

console.log(((2 ** 31 - Math.round(Date.now() / 1000)) / (3600 * 24 * 365)).toFixed(2) + '年')
