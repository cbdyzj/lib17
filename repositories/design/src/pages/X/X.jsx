import React from 'react'

import Context from './context'
import Y from './Y'

export default function X(props) {

    return (
        <Context.Provider value={1}>
            <div style={{ margin: '24px' }}>
                <h2>X</h2>
                <Y/>
            </div>
        </Context.Provider>
    )
}
