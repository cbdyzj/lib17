!function () {
  const a = '\\', b = '\'', c = '\n', d = '$'
  const s = '!function () {$c  const a = $b$a$a$b, b = $b$a$b$b, c = $b$an$b, d = $b$d$b$c  const s = $b$s$b$c  console.log(s.replaceAll($b$da$b, a).replaceAll($b$db$b, b).replaceAll($b$dc$b, c).replaceAll($b$dd$b, d).replace($b$ds$b, s))$c}()'
  console.log(s.replaceAll('$a', a).replaceAll('$b', b).replaceAll('$c', c).replaceAll('$d', d).replace('$s', s))
}()
