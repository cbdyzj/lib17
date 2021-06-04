export function sleep(ms) {
    return new Promise((resolve) => setTimeout(resolve, ms))
}

export function debounce(func, delay) {
    let timer

    return function (...args) {
        if (timer) {
            clearTimeout(timer)
        }
        timer = setTimeout(() => func(...args), delay)
    }
}

export function throttle(func, delay) {
    let wait
    return function (...args) {
        if (wait) {
            return
        }
        wait = true
        setTimeout(() => {
            wait = false
            func(...args)
        }, delay)
    }
}
