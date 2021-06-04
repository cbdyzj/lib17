import React from 'react'
import { createStore } from 'redux'
import { Provider, createDispatchHook, createSelectorHook, createStoreHook } from 'react-redux'

const initialState = {
    count: 0,
}

const handlers = {
    increment(state, action) {
        return {
            ...state,
            count: state.count + 1
        }
    },
    decrement(state, action) {
        return {
            ...state,
            count: state.count - 1
        }
    },
}

function reducer(state = initialState, action = {}) {
    const handler = handlers[action.type]
    if (typeof handler !== 'function') {
        return state
    } else {
        return handler(state, action)
    }
}

const Context = React.createContext(null)

export const useStore = createStoreHook(Context)
export const useDispatch = createDispatchHook(Context)
export const useSelector = createSelectorHook(Context)

const store = createStore(reducer)

export function ContextProvider({ children }) {
    return (
        <Provider context={Context} store={store}>
            {children}
        </Provider>
    )
}