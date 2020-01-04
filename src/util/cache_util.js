const ExpireMap = require('expiry-map')

const CACHE_EXPIRE_TIME = 3000

function useExpireCache(target, options) {
    const {
        cacheKeyGetter,
        expireTime,
        refreshable,
    } = options

    if (!cacheKeyGetter && typeof cacheKeyGetter !== 'function') {
        throw new Error('Cache key getter required')
    }
    const cacheBucket = new ExpireMap(expireTime || CACHE_EXPIRE_TIME)

    return async function (...args) {
        const cacheKey = cacheKeyGetter(...args)
        if (!cacheKey) {
            throw new Error('Illegal cache key')
        }
        const cachedItem = cacheBucket.get(cacheKey)
        if (cachedItem) {
            if (refreshable) {
                cacheBucket.set(cacheKey, cachedItem)
            }
            return cachedItem
        }
        const result = await target(...args)
        cacheBucket.set(cacheKey, result)
        return result
    }

}

module.exports = {
    useExpireCache,
}
