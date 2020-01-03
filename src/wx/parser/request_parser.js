const {
    textReply
} = require('./reply_builder')


function parseRequest(request) {
    if (!request || !request.xml) {
        throw new Error('Illegal request')
    }

    function getValue(key) {
        const val = request.xml[key]
        return val && val[0]
    }

    return {
        messageId: getValue('msgid'),
        messageType: getValue('msgtype'),
        fromUserName: getValue('fromusername'),
        toUserName: getValue('tousername'),
        content: getValue('content'),
        pictureUrl: getValue('picurl'),
        createTime: getValue('createtime'),
        mediaId: getValue('mediaid'),
    }
}

function buildContext(request) {
    const payload = parseRequest(request)
    return {
        payload,
        text(content) {
            return textReply({
                fromUserName: payload.toUserName,
                toUserName: payload.fromUserName,
                content
            })
        },
    }
}

module.exports = {
    buildContext,
}
