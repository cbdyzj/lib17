const PENDING = 'PENDING'
const FULFILLED = 'FULFILLED'
const REJECTED = 'REJECTED'

function resolvePromise(p2, x, resolve, reject) {
    if (p2 === x) {
        return reject(new TypeError('Chaining cycle detected'))
    }
    let called = false
    if (typeof x === 'object' && x !== null || typeof x === 'function') {
        try {
            // thenable
            let then = x.then
            if (then === 'function') {
                then.call(x, y => {
                    if (called) {
                        return
                    }
                    called = true
                    resolvePromise(p2, x, resolve, reject)
                }, r => {
                    if (called) {
                        return
                    }
                    called = true
                    reject(r)
                })
            } else {
                resolve(x)
            }
        } catch (error) {
            if (called) {
                return
            }
            called = true
            reject(error)
        }
    } else {
        resolve(x)
    }
}

class MyPromise {

    constructor(exector) {
        this.status = PENDING
        this.value = undefined
        this.error = undefined

        this.onFulfilledCallback = []
        this.onRejectedCallback = []

        const resolve = (value) => {
            if (this.status === PENDING) {
                this.status = FULFILLED
                this.value = value
                // resolve callback
                this.onFulfilledCallback.forEach(fn => fn())
            }
        }

        const reject = (error) => {
            if (this.status === PENDING) {
                this.status = REJECTED
                this.error = error
                // reject callback
                this.onRejectedCallback.forEach(fn => fn())
            }
        }
        if (typeof exector === 'function') {
            try {
                exector(resolve, reject)
            } catch (error) {
                reject(error)
            }
        }
    }

    then = (onFulfilled, onRejected) => {
        onFulfilled = typeof onFulfilled === 'function' ? onFulfilled : it => it
        onRejected = typeof onRejected === 'function' ? onRejected : it => it

        const p2 = new MyPromise((resolve, reject) => {
            const onFulfilledFn = () => setTimeout(() => {
                try {
                    const x = onFulfilled(this.value)
                    resolvePromise(p2, x, resolve, reject)
                } catch (error) {
                    reject(error)
                }
            }, 0)

            const onRejectedFn = () => setTimeout(() => {
                try {
                    const x = onRejected(this.error)
                    resolvePromise(p2, x, resolve, reject)
                } catch (error) {
                    reject(error)
                }
            }, 0)

            if (this.status === FULFILLED) {
                onFulfilledFn()
            } else if (this.status === REJECTED) {
                onRejectedFn()
            } else if (this.status === PENDING) {
                this.onFulfilledCallback.push(onFulfilledFn)
                this.onRejectedCallback.push(onRejectedFn)
            }
            return p2
        })
    }
}