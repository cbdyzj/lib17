/* 写个函数求和如下数组 */
const numbers = [7, 2, 6, 4, 5, 8, 11]

/* 普通青年 */
function sumNormal(numberArray: number[]): number {
    let sum = 0
    for (const number of numberArray) {
        sum += number
    }
    return sum
}

/* 文艺青年 */
function sumArt(numberArray: number[]): number {
    return numberArray.reduce((a, c) => a + c, 0)
}

/* 2B青年 */
function sum2b(numberArray: number[]): number {
    return eval(numberArray.join('+'))
}
