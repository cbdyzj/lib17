const axios = require('axios')

module.exports = async function url2base64(url) {
    const response = await axios.request({
        url,
        method: 'GET',
        responseType: 'arraybuffer'
    })
    return Buffer.from(response.data, 'binary').toString('base64')
}
