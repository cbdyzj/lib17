const {
    textReply,
    cannotHandleReply,
} = require('./reply_templates')

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
            content: content[0],
        })
    }
    return cannotHandleReply({
        toUserName: fromusername[0],
        fromUserName: tousername[0],
    })
}

module.exports = wxService
