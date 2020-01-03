function textReply(parameters) {
    const {
        toUserName,
        fromUserName,
        content,
    } = parameters
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
}
