const axios = require('axios')

const QUERY_API = 'https://org-jianzhao-payroll.herokuapp.com/wiki/extracts?titles='
const URL_PREFIX = 'https://zh.m.wikipedia.org/wiki/'

async function getWikiExtracts(titles) {
    try {
        const encodingTitles = encodeURIComponent(titles)
        const response = await axios.get(QUERY_API + encodingTitles)
        const data = response.data.query.pages

        const wiki = Object.values(data)[0]
        if (data['-1'] || !wiki) {
            return 'nano没有找到：' + titles
        }
        return wiki.extract + '\n' + URL_PREFIX + encodingTitles
    } catch (error) {
        console.error(error)
        return 'nano请求wiki时遇到了异常'
    }
}

module.exports = {
    getWikiExtracts,
}
