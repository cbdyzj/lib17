class Context {

    constructor(resolve, reject) {
        this.reject = reject
        this.resolve = resolve
    }

    resolve(value) {
        this.value = value
        this.resolve(this)
    }

    reject(error) {
        this.error = error
        this.reject(this)
    }
}

function run(task, args) {
    return new Promise((resolve, reject) => task.apply(new Context(resolve, reject), args))
}
