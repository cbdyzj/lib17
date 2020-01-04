const {useExpireCache} = require('../src/util/cache_util')

function targetFunction(key) {
    console.log('targetFunction ' + key)
    return key.length
}

async function test() {
    const proxy = useExpireCache(targetFunction, {
        cacheKeyGetter: key => key,
        expireTime: 3000,
    })
    for (let i = 0; i < 17; i++) {
        const result = await proxy('foo')
        console.log(result)
        await new Promise(resolve => setTimeout(resolve, 700))
    }
}

if (require.main === module) {
    test()
}
