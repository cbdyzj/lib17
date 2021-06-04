import React from 'react'

import style from './Switcher.module.less'

export default function Switcher(props) {
    return (
        <div className={style.switch__container}>
            <input  id="switch-flat" className="switch switch--flat" type="checkbox"
                    onChange={props.onChange} checked={props.checked}/>
            <label htmlFor="switch-flat" />
        </div>
    )
}