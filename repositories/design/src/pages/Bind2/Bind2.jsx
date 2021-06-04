import React, { useEffect } from 'react'

import style from './Bind2.module.css'

export default function Bind2() {
    useEffect(() => {
        const p = document.querySelector('p')
        const input = document.querySelector('input')
        const mo = new Proxy(
            { value: '' },
            {
                set: (target, property, value) => {
                    target[property] = value
                    input.value = value
                    p.innerText = JSON.stringify(target)
                    return true
                },
            }
        )
        input.addEventListener('input', ({ target }) => (mo.value = target['value']))
    },[])

    return (
        <div className={style['bind2']}>
            <h2>双向绑定</h2>
            <form>
                <label htmlFor="bind2" />
                <input type="text" id="bind2" name="value" placeholder="Enter" />
            </form>
            <p>{''}</p>
        </div>
    )
}
