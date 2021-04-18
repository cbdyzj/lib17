const isNumber = (expr) => !Number.isNaN(parseFloat(expr))
const tokens = ['+', '-', '*', '/', '(', ')']

const stackTopIn = (stack, tokens) => {
    for (const token of tokens) {
        if (stack[stack.length - 1] === token) { return true }
    }
    return false
}

function splitExpreion(expreion) {
    let expr: string = expreion
    for (const token of tokens) {
        expr = expr.split(token).join(`|${token}|`)
    }
    return expr.split('|').map(t => t.trim()).filter(t => t !== '')
}

function suffixArray(exprArray) {
    const outStack = []
    const symbolStack = []
    for (const expr of exprArray) {
        if (isNumber(expr)) {
            outStack.push(expr)
            continue
        }

        if (symbolStack.length === 0) {
            symbolStack.push(expr)
            continue
        }

        if (expr === '*' || expr === '/') {
            while (stackTopIn(symbolStack, ['*', '/'])) {
                outStack.push(symbolStack.pop())
            }
            symbolStack.push(expr)
            continue
        }

        if (expr === '+' || expr === '-') {
            while (stackTopIn(symbolStack, ['*', '/', '+', '-'])) {
                outStack.push(symbolStack.pop())
            }
            symbolStack.push(expr)
            continue
        }

        if (expr === '(') {
            symbolStack.push(expr)
            continue
        }

        if (expr === ')') {
            while (stackTopIn(symbolStack, ['*', '/', '+', '-'])) {
                outStack.push(symbolStack.pop())
            }
            symbolStack.pop()
            continue
        }
    }

    return outStack.concat(symbolStack.reverse())
}

function clacStack(outStack) {
    const calcStack: number[] = []
    while (outStack.length > 0) {
        const e = outStack.shift()
        if (isNumber(e)) {
            calcStack.push(parseFloat(e))
            continue
        }
        let l: number, r: number
        r = calcStack.pop()
        l = calcStack.pop()
        switch (e) {
            case '+':
                calcStack.push(l + r)
                break
            case '-':
                calcStack.push(l - r)
                break
            case '*':
                calcStack.push(l * r)
                break
            case '/':
                calcStack.push(l / r)
                break
        }
    }
    return calcStack.pop()
}


export function calc(expreion) {
    const exprArray = splitExpreion(expreion)
    const outStack = suffixArray(exprArray)
    return clacStack(outStack)
}
