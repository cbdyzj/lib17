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

module.exports = {
    textReply,
    cannotHandleReply,
}
