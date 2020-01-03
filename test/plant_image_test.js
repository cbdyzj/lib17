const fs = require('fs')
const { join } = require('path')

const image = fs.readFileSync(join(__dirname, './image.png')).toString('base64')
const client = require('../src/baidu/plant_client')

if (require.main === module) {
    client(image).then(result => {
        console.log(result.result[0])
    })
}
