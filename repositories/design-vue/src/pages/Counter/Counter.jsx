import { mapState } from 'vuex'

export default {
    render(h) {
        return (
            <div>
                <input type="text" value={this.count} onChange={this.handleInputChange} />
                <br />
                <button onClick={this.handlePlus}>+</button>
                <button onClick={this.handleMinus}>-</button>
                <button onClick={this.handleMinus1s} disabled={this.plus1sButtonDisabled}>+1s</button>
            </div>
        )
    },
    data() {
        return {
            plus1sButtonDisabled: false
        }
    },
    methods: {
        handlePlus(ev) {
            this.$store.commit('setCount', this.count + 1)
        },
        handleMinus(ev) {
            this.$store.commit('setCount', this.count - 1)
        },
        handleInputChange(ev) {
            this.$store.commit('setCount', Number(ev.target.value) || 0)
        },
        async handleMinus1s(ev) {
            this.plus1sButtonDisabled = true
            await this.$store.dispatch('setCountAfter1s', this.count + 1)
            this.plus1sButtonDisabled = false
        }
    },
    computed: {
        ...mapState({
            count: state => state.count,
        })
    }
}