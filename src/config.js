const config = {
    wx: {
        token: '',
    },
    baidu: {
        clientId: '',
        clientSecret: '',
        accessToken: '',
    }
}

try {
    module.exports = require('../_config')
} catch (error) {
    module.exports = config
}
