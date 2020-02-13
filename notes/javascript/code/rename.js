const fs = require('fs')

const fileNames = fs.readdirSync(__dirname)

for (const fileName of fileNames) {
    if (fileName === 'a.txt') {
        fs.renameSync(fileName, 'b.txt')
    }
}
