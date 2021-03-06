const fs = require('fs')

function replaceRename(regex) {
    if (!(regex instanceof RegExp)) {
        throw new Error(`"${regex}" is not instance of RegExp`)
    }
    const fileNames = fs.readdirSync(__dirname)

    for (const fileName of fileNames) {
        if (regex.test(fileName)) {
            const newFileName = fileName.replace(regex, '')
            fs.renameSync(fileName, newFileName)
        }
    }
}

if (require.main === module) {
    replaceRename(/target/gi)
}
