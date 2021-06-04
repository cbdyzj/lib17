import Vue from 'vue'
import App from './pages/App/App.vue'

Vue.config.productionTip = false

const app = new Vue({
    render: h => h(App),
})

app.$mount("#app")
