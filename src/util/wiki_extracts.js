const axios = require('axios')

const QUERY_API = 'https://org-jianzhao-payroll.herokuapp.com/wiki/extracts?titles='
const URL_PREFIX = 'https://zh.m.wikipedia.org/wiki/'

const MAX_LENGTH = 600

function ellipsis(longString = '') {
    if (longString.length <= MAX_LENGTH) {
        return longString
    }
    const ellipsisString = longString.slice(0, MAX_LENGTH)
    if (!/[。!]$/.test(ellipsisString)) {
        return ellipsisString + '...'
    }
    return ellipsisString
}

async function getWikiExtracts(titles) {
    try {
        const encodingTitles = encodeURIComponent(titles)
        const response = await axios.get(QUERY_API + encodingTitles)
        const data = response.data.query.pages

        const wiki = Object.values(data)[0]
        if (data['-1'] || !wiki) {
            return 'nano没有找到：' + titles
        }

        return ellipsis(wiki.extract) + '\n' + URL_PREFIX + encodingTitles
    } catch (error) {
        console.error(error)
        return 'nano请求wiki时遇到了异常'
    }
}


module.exports = {
    getWikiExtracts,
}
