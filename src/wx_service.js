function textReply(payload) {
    const {
        toUserName,
        fromUserName,
        content,
    } = payload
    return `<xml>
 <ToUserName><![CDATA[${toUserName}]]></ToUserName>
 <FromUserName><![CDATA[${fromUserName}]]></FromUserName>
 <CreateTime>${Math.trunc(new Date().getTime() / 1000)}</CreateTime>
 <MsgType><![CDATA[text]]></MsgType>
 <Content><![CDATA[${content}]]></Content>
 </xml>`
}

function cannotHandleReply(payload) {
    const {
        toUserName,
        fromUserName
    } = payload
    const content = 'nano：啥？'
    return `<xml>
 <ToUserName><![CDATA[${toUserName}]]></ToUserName>
 <FromUserName><![CDATA[${fromUserName}]]></FromUserName>
 <CreateTime>${Math.trunc(new Date().getTime() / 1000)}</CreateTime>
 <MsgType><![CDATA[text]]></MsgType>
 <Content><![CDATA[${content}]]></Content>
 </xml>`
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
            content: content[0],
        })
    }
    return cannotHandleReply({
        toUserName: fromusername[0],
        fromUserName: tousername[0],
    })
}

module.exports = wxService
