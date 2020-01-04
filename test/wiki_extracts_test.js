const {getWikiExtracts} = require('../src/util/wiki_extracts')


async function test() {
    const result = await getWikiExtracts('中国')
    console.log(result)
}

if (require.main === module) {
    test()
}
