import React, { useState } from 'react'

import { PRESET_TYPE, getPresetTransform } from './presets'
import style from './Transformer.module.less'

const TRANSFORM_DELAY = 1000

export default function Transformer() {
    const [input, setInput] = useState('')
    const [output, setOutput] = useState('')
    const [transformer, setTransformer] = useState('return input')
    const [transformerType, setTransformerType] = useState(PRESET_TYPE.CUSTOM)
    const [timeoutHandle, setTimeoutHandle] = useState()

    const transform = getPresetTransform(transformerType)

    function applyTransform(func) {
        clearTimeout(timeoutHandle)
        setTimeoutHandle(setTimeout(func, TRANSFORM_DELAY))
    }

    function handleInputChange(ev) {
        const newInput = ev.target.value
        setInput(newInput)
        applyTransform(async () => setOutput(await transform(newInput, transformer)))
    }

    function handleTransformerChange(ev) {
        const newTransformer = ev.target.value
        setTransformer(newTransformer)
        applyTransform(async () => setOutput(await transform(input, newTransformer)))
    }

    function handleTransformerTypeChange(ev) {
        const newTransformerType = ev.target.value
        const newTransform = getPresetTransform(newTransformerType)
        applyTransform(async () => setOutput(await newTransform(input, transformer)))
        setTransformerType(newTransformerType)
    }

    return (
        <div className={style.transformer}>
            <textarea className="text-box" onChange={handleInputChange} value={input} />
            <textarea readOnly className="text-box" value={output} />
            <br />
            <select className="transformer-type-select" value={transformerType} onChange={handleTransformerTypeChange}>
                <option value={PRESET_TYPE.CUSTOM}>custom</option>
                <option value={PRESET_TYPE.MARKED}>marked</option>
            </select>
            <br />
            {transformerType === PRESET_TYPE.CUSTOM
            && <textarea className="transformer-box" onChange={handleTransformerChange} value={transformer} />}
        </div>
    )
}
