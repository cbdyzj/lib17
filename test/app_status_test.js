const {
    getStatusText
} = require('../src/util/app_status')

if(require.main === module){
    console.log(getStatusText())
}
