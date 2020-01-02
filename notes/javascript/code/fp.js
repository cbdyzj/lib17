/**
 * 写一个函数，满足：
 * f(1).value = 1
 * f(1)(2).value = 5
 * f(1)(2)(3).value = 14
 */
function f(i) {
    const c = function (i) {
        c.value += i ** 2
        return c
    }
    c.value = i ** 2
    return c
}
