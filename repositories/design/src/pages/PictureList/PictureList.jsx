import React from 'react'

import style from './PictureList.module.css'
import xmm from './xmm.jpeg'

export default function PictureList() {
    return (
        <div className={style['picture-list']}>
            <h1>Picture List</h1>
            <img src={xmm} alt="小猫咪" />
        </div>
    )
}

