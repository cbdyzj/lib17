const states = ['pending', 'fulfilled', 'rejected']

class Promise2 {

    constructor(exector) {
        this.state = 'pending'
        this.doneTask = (error, result) => { }
        exector(this.onResolved.bind(this), this.onRejected.bind(this))
    }

    triggerTask(newTask) {
        const oldTask = this.doneTask
        this.doneTask = (error, result) => {
            oldTask(error, result)
            newTask(error, result)
        }
        if (this.state !== 'pending') {
            this.doneTask(this.error, this.result)
        }
    }

    onResolved(result) {
        // assert this.state === 'pending'
        if (result instanceof Promise2) {
            result.triggerTask((error, result) => {
                this.state = 'fulfilled'
                this.result = result
                this.doneTask(this.error, this.result)

            })
        } else {
            this.state = 'fulfilled'
            this.result = result
            this.doneTask(this.error, this.result)
        }
    }

    onRejected(error) {
        // assert this.state === 'pending'
        if (result instanceof Promise2) {
            result.triggerTask((error, result) => {
                this.state = 'rejected'
                this.error = error
                this.doneTask(this.error, this.result)

            })
        } else {
            this.state = 'rejected'
            this.error = error
            this.doneTask(this.error, this.result)
        }
    }

    then(ors, orj) {
        return new Promise2((resolve, reject) => {
            this.triggerTask((error, result) => {
                if (error) {
                    if (orj) {
                        orj(error)
                    }
                    reject(error)
                    return
                }
                // assert ors
                resolve(ors(result))
            })
        })
    }

    static resolve(result) {
        return new Promise2((resolve) => resolve(result))
    }

    static reject(error) {
        return new Promise2((resolve, reject) => reject(error))
    }
}

function test() {
    Promise2.resolve(1)
        .then(result => {
            return new Promise2((resolve) => {
                console.log(result)
                setTimeout(() => resolve(result + 1), 1000)
            })
        })
        .then(result => {
            return new Promise2((resolve) => {
                console.log(result)
                setTimeout(() => resolve(result+'1'), 1000)
            })
        }).then(result => console.log(result))
}

test()