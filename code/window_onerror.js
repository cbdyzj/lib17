window.onerror = function (...args) {
    console.log(args)
}

setTimeout(() => {
    throw new Error('An error occurred')
}, 3000)