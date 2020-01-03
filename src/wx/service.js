const {
    textReply,
    cannotHandleReply,
} = require('./reply_templates')

function handleTextMessage(content){
    if(/^在吗？?$/.test(content)){
        return 'nano在的'
    }
    return content
}


function wxService(payload) {
    if (!payload.xml) {
        return ''
    }
    const {
        msgtype = [],
        fromusername = [],
        tousername = [],
        content = [],
    } = payload.xml

    if (msgtype[0] === 'text') {
        // 复读
        return textReply({
            toUserName: fromusername[0],
            fromUserName: tousername[0],
            content: handleTextMessage(content[0]),
        })
    }
    return cannotHandleReply({
        toUserName: fromusername[0],
        fromUserName: tousername[0],
    })
}

module.exports = wxService
