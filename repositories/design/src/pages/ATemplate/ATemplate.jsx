import React, { useState } from 'react'

function parseJson(s = '{}') {
    try {
        return JSON.parse(s)
    } catch (error) {
        return {}
    }
}

function ContentRender(props) {

    function make() {
        const scope = parseJson(props.scope)

        const content = props.content || ''
        const split = content.split(/\${(.*?)}/)

        return split.map((it, index) => {
            if (index % 2 === 0) {
                return (<span key={'span-' + index}>{it || ''}</span>)
            } else {
                return (<input readOnly key={'input-' + index} value={scope[it] || ''} />)
            }
        })
    }

    const elements = make()

    return (
        <div style={{ margin: '8px 0' }}>{elements}</div>
    )
}

export default function ATemplate(props) {
    const [content, setContent] = useState('')
    const [scope, setScope] = useState('{}')

    return (
        <div style={{ margin: '24px' }}>
            <h2>A Template</h2>
            <pre>Sample template: {'hello ${name}'}</pre>
            <pre>Sample scope: {'{"name": "world"}'}</pre>
            <p>
                <span>Template: </span>
                <input value={content} onChange={ev => setContent(ev.target.value)} />
            </p>
            <p>
                <span>Scope: </span>
                <input value={scope} onChange={ev => setScope(ev.target.value)} />
            </p>
            <ContentRender content={content} scope={scope} />
        </div>
    )
}
