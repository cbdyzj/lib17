const channels = [{
    name: '维基百科',
    prompt: '10分钟内发送关键词将从维基百科查询词条',
}, {
    name: '谷歌搜索',
    prompt: '谷歌搜索功能还在施工中🔧，请稍后试试',
}, {
    name: '植物识别',
    prompt: '10分钟内上传图片能识别植物哦（为了防止图片太大，请避免上传原图哦）',
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
