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
    return textReply({
        content: '怎么办呢？',
        ...payload,
    })
}

module.exports = {
    textReply,
    cannotHandleReply,
}
