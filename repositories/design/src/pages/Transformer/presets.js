import marked from 'marked'

const AsyncFunction = Object.getPrototypeOf(async () => ({})).constructor

const CUSTOM = 'custom'
const MARKED = 'marked'

export const PRESET_TYPE = {
    CUSTOM,
    MARKED,
}

async function customTransform(inputText, transformerText) {
    try {
        const evaluated = await new AsyncFunction('input', transformerText)(inputText)
        if (typeof evaluated === 'function') {
            return String(await evaluated(inputText) || '')
        }
        return String(evaluated || '')
    } catch (err) {
        return 'Error: ' + err
    }
}

function markedTransform(inputText) {
    try {
        return marked(inputText) || ''
    } catch (err) {
        return 'Error: ' + err
    }
}

const presets = {
    [CUSTOM]: customTransform,
    [MARKED]: markedTransform,
}

export function getPresetTransform(type) {
    const t = presets[type]
    if (!t) {
        throw new Error('Illegal preset type')
    }
    return t
}





