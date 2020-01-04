const channels = [{
    name: '维基百科',
    prompt: '发送关键词可以从维基百科查询词条哦（15分钟内有效）',
}, {
    name: '植物识别',
    prompt: '上传图片能识别植物哦（15分钟内有效，为了防止图片太大，请避免上传原图哦）',
}, {
    name: '谷歌搜索',
    prompt: '谷歌反爬太厉害了，nano暂时不能帮你搜索',
}]

function getChannel(name) {
    for (const channel of channels) {
        if (channel.name === name) {
            return channel
        }
    }
}

module.exports = {
    channels,
    getChannel,
}
