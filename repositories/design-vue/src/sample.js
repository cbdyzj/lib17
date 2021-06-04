import Vue from 'vue'

import SampleList from '@/pages/SampleList/SampleList.vue'
import store from '@/pages/SampleList/store.js'

import { Modal, Table, Button, Select, Form, Input, Divider, Tag, Icon, Popconfirm } from 'ant-design-vue'

// ant design
const ac = [Modal, Table, Button, Select, Form, Input, Divider, Tag, Icon, Popconfirm]
ac.forEach(it => Vue.use(it))

Vue.config.productionTip = false

const app = new Vue({
    store,
    render: h => h(SampleList)
})

app.$mount("#app")

// https://zhuanlan.zhihu.com/p/31360204