import React, { useContext } from 'react'

import Context from './context'

export default function Y() {
    const context = useContext(Context)

    return (
        <div>
            <p>React context value: {context}</p>
        </div>
    )
}
