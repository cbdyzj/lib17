import React, { useState } from 'react'

function slowTask(val) {
    console.time('long-running')
    const fakeArray = []
    const TIMES = 10_000_000
    for (let i = 0; i < TIMES; i++) {
        fakeArray.push(val)
        if (i === TIMES - 1) {
            console.timeEnd('long-running')
        }
    }
    return fakeArray
}

export default function SlowInput(props) {

    const [val, setVal] = useState('')

    function handleInputChange(ev) {
        setVal(ev.target.value)
    }

    if (props.slow) {
        slowTask(val)
    }

    return (
        <>
            <p>{val}</p>
            <input value={val} onChange={handleInputChange} />
        </>
    )
}
