const fs = require('fs')
const {join} = require('path')
const client = require('../src/baidu/plant_client')
const url2base64 = require('../src/util/url2base64')

const imageUrl = "https://wx4.sinaimg.cn/mw690/e7c6b476ly1gajjoo7nf8j20aw0bm0ta.jpg"

async function plantImageTest() {
    const image = await url2base64(imageUrl)
    const result = await client(image)
    const info = result.result[0].baike_info
    console.log(info.description + '\n' + info.baike_url)

}

if (require.main === module) {
    plantImageTest()
}
