const axios = require('axios')
const qs = require('querystring')
const { baidu }  = require('../config')

const { accessToken } = baidu

const API = 'https://aip.baidubce.com/rest/2.0/image-classify/v1/plant?access_token='

module.exports = async function (image) {
    const response = await axios.request({
        url: API + accessToken,
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        data: qs.stringify({
            image, baike_num: 1
        }),
    })
    return response.data
}
