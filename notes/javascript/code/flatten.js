export function flatten(data) {
    const result = {}
    function recurse(cur, prop) {
        if (Object(cur) !== cur) {
            result[prop] = cur
        } else if (Array.isArray(cur)) {
            if (cur.length == 0) {
                result[prop] = []
            } else {
                for (let i = 0; i < cur.length; i++) {
                    recurse(cur[i], prop ? prop + '.' + i : '' + i)
                }
            }
        } else {
            let isEmpty = true
            for (const p of Object.keys(cur)) {
                isEmpty = false
                recurse(cur[p], prop ? prop + '.' + p : p)
            }
            if (isEmpty) {
                result[prop] = {}
            }

        }
    }
    recurse(data, '')
    return result
}

export function unflatten(data) {
    if (Object(data) !== data || Array.isArray(data)) {
        return data
    }
    const result = {}
    let cur, prop, idx, last, temp
    for (const p of Object.keys(data)) {
        cur = result, prop = '', last = 0
        do {
            idx = p.indexOf('.', last)
            temp = p.substring(last, idx !== -1 ? idx : undefined)
            cur = cur[prop] || (cur[prop] = (!isNaN(parseInt(temp)) ? [] : {}))
            prop = temp
            last = idx + 1
        } while (idx >= 0)
        cur[prop] = data[p]
    }
    return result['']
}