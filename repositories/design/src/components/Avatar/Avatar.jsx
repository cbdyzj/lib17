import React from 'react'

import style from './Avatar.module.less'

export default function Avatar(props) {
    return (
        <span className={style.avatar}>
            {props.character || '?'}
        </span>
    )
}
