import React from 'react'

import Extract from '@/components/Extract/Extract'
import style from './Layout.module.less'

export default function Layout() {
    return (
        <div className={style.layout}>
            <h4>flex-auto</h4>
            <div className="flex-auto">
                <span />
                <span />
                <span />
                <span />
            </div>
            <h4>flex-none</h4>
            <div className="flex-none">
                <span />
                <span />
                <span />
                <span />
            </div>
            <h4>Extract</h4>
            <Extract />
            <div className="a-box">A Box</div>
        </div>
    )
}
