import React, { useCallback } from 'react'
import createReactClass from 'create-react-class'
import { ContextProvider, useDispatch, useSelector } from './context'

import style from './Hello.module.less'

const CountText = createReactClass({

    render() {
        return (<span>{this.props.count}</span>)
    }
})

function useIncrease() {

    const dispatch = useDispatch()

    return useCallback(() => {
        dispatch({ type: 'increment' })
    }, [dispatch])
}

function Hello(props) {
    const count = useSelector(state => state.count)
    const increase = useIncrease()

    return (
        <div className={style.hello}>
            <h2>Hello Design</h2>
            <p>{navigator.userAgent}</p>
            <button className="uppercase" onClick={increase}>
                Click me
            </button>
            <CountText count={count} />
        </div>
    )
}

export default function (props) {
    return (
        <ContextProvider>
            <Hello {...props} />
        </ContextProvider>
    )
}
