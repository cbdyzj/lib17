export default {
    data() {
        return {
            count: 0
        }
    },
    methods: {
        handleClick(ev) {
            this.count++
        }
    },
    render() {
        return (
            <div>
                <p>Good Component with pure JavaScript</p>
                <p>Click times: {this.count}</p>
                <button onClick={this.handleClick}>Click here</button>
            </div>
        )
    }
}
