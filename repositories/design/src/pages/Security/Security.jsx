import React, { useRef } from 'react'

import style from './Security.module.less'

export default function Security(props) {
    const xss1Ref = useRef()

    function xss1() {
        const divRef = xss1Ref.current
        if (!divRef) {
            return
        }
        const script = document.createElement('script')
        script.innerHTML = `alert('hello')`
        if (divRef.firstChild) {
            divRef.removeChild(divRef.firstChild)
        }
        divRef.appendChild(script)
    }

    function xss2() {
        const link = document.getElementById('link')
        link.href = 'https://www.google.com'
    }

    return (
        <div className={style.box}>
            <h2>几个Web安全的例子</h2>

            <h4>XSS1</h4>
            <button onClick={xss1}>xss1</button>
            <div ref={xss1Ref} />
            <br />

            <h4>XSS2</h4>
            <button onClick={xss2}>xss2</button>
            <p>
                <a id="link" target="_blank">一个超链接</a>
            </p>
            <div ref={xss1Ref} />
        </div>
    )
}
