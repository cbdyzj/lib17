const Redux = globalThis.Redux

const LEFT = 'LEFT'
const RIGHT = 'RIGHT'
const UP = 'UP'
const DOWN = 'DOWN'

const SPEED = 1

const COLORS = [
    '#fb7185',
    '#f472b6',
    '#e879f9',
    '#c084fc',
    '#a78bfa',
    '#818cf8',
    '#60a5fa',
    '#38bdfa',
    '#22d3ee',
    '#2dd4bf',
    '#34d399',
    '#4ade80',
    '#a3e635',
    '#facc15',
    '#fbbf24',
    '#fb923c',
    '#f87171',
    '#a1a1aa',
    '#000000',
]

function nextStyle(previous) {
    return COLORS[COLORS.indexOf(previous) + 1 % COLORS.length]
}

const initialState = {
    boxX: 0,
    boxY: 0,
    boxFillStyle: nextStyle(),
    boxWidth: 40,
    boxHeight: 40,
    clientWidth: 300,
    clientHeight: 150,
    directionX: RIGHT,
    directionY: DOWN,
}

const handlers = {
    init(state, payload) {
        return { ...state, ...payload }
    },
    resize(state, payload) {
        return {
            ...state,
            clientWidth: payload.clientWidth,
            clientHeight: payload.clientHeight,
        }
    },
    move(state) {
        const newState = {}
        switch (state.directionX) {
            case RIGHT:
                if (state.boxX + state.boxWidth + SPEED > state.clientWidth) {
                    newState.directionX = LEFT
                    newState.boxX = state.boxX - SPEED
                } else {
                    newState.boxX = state.boxX + SPEED
                }
                break
            case LEFT:
                if (state.boxX - SPEED < 0) {
                    newState.directionX = RIGHT
                    newState.boxX = state.boxX + SPEED
                } else {
                    newState.boxX = state.boxX - SPEED
                }
        }
        switch (state.directionY) {
            case DOWN:
                if (state.boxY + state.boxHeight + SPEED > state.clientHeight) {
                    newState.directionY = UP
                    newState.boxY = state.boxY - SPEED
                } else {
                    newState.boxY = state.boxY + SPEED
                }
                break
            case UP:
                if (state.boxY - SPEED < 0) {
                    newState.directionY = DOWN
                    newState.boxY = state.boxY + SPEED
                } else {
                    newState.boxY = state.boxY - SPEED
                }
        }
        if (newState.directionX || newState.directionY) {
            newState.boxFillStyle = nextStyle(state.boxFillStyle)
        }
        return {
            ...state,
            ...newState,
        }
    },
}

function reducer(state = initialState, action = {}) {
    const handler = handlers[action.type]
    if (typeof handler !== 'function') {
        return state
    } else {
        return handler(state, action.payload)
    }
}

const store = Redux.createStore(reducer)

function updateCanvas(context, canvas) {
    const state = store.getState()
    //
    canvas.width = state.clientWidth
    canvas.height = state.clientHeight
    //
    context.clearRect(0, 0, state.clientWidth, state.clientHeight)
    context.fillStyle = state.boxFillStyle
    context.fillRect(state.boxX, state.boxY, state.boxWidth, state.boxHeight);
}

function handleLoaded() {
    const canvas = document.querySelector('#canvas')
    const context = canvas.getContext('2d')

    function getClientSize() {
        return {
            clientWidth: document.body.clientWidth,
            clientHeight: document.body.clientHeight,
        }
    }

    store.subscribe(() => {
        updateCanvas(context, canvas)
    })

    window.addEventListener('resize', () => {
        store.dispatch({
            type: 'resize',
            payload: getClientSize(),
        })
    })



    function move() {
        requestAnimationFrame(() => {
            store.dispatch({ type: 'move' })
            move()
        })
    }

    move()

    store.dispatch({
        type: 'init',
        payload: getClientSize(),
    })
}

document.addEventListener('DOMContentLoaded', handleLoaded)