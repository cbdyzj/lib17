type OnResolve<T, N> = (result: T) => N
type OnReject = (error: Error) => void
type Executor<T> = (onResolve: OnResolve<T, void>, onReject?: OnReject) => void

type State = 'pending' | 'fulfilled' | 'rejected'

class Promise2<T> {

    private state: State = 'pending'
    private result: T
    private error: Error
    private thenTask = () => { }

    constructor(exector: Executor<T>) {
        exector(this.onResolved.bind(this), this.onRejected.bind(this))
    }

    private onResolved(result: T): void {
        this.state = 'fulfilled'
        this.result = result
        this.thenTask()
    }

    private onRejected(error: Error): void {
        this.state = 'rejected'
        this.error = error
        this.thenTask()
    }

    then<N>(ors: OnResolve<T, N>, orj?: OnReject): Promise2<N> {
        return new Promise2<N>((resolve, reject) => {
            const thenTodo = () => {
                if (this.error) {
                    orj(this.error)
                    reject(this.error)
                    return
                }
                resolve(ors(this.result))
            }
            switch (this.state) {
                case 'pending':
                    this.thenTask = thenTodo
                    break
                case 'fulfilled':
                case 'rejected':
                    thenTodo()
            }
        })
    }

    static resolve<T>(result: T): Promise2<T> {
        return new Promise2<T>((resolve) => resolve(result))
    }

    static reject(error: Error): Promise2<void> {
        return new Promise2<void>((resolve, reject) => reject(error))
    }
}
