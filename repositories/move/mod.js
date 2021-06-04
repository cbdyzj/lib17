const CONTENT_TYPE = [
    { pattern: /\.html?$/i, type: 'text/html; charset=UTF-8' },
    { pattern: /\.css$/i, type: 'text/css; charset=UTF-8' },
    { pattern: /\.js$/i, type: 'text/javascript; charset=UTF-8' },
    { pattern: /\.svg$/i, type: 'image/svg+xml; charset=UTF-8' },
]

function getContentType(url) {
    const it = CONTENT_TYPE.find(it => it.pattern.test(url))
    if (it) {
        return it.type
    }
}

function getRelativePath(pathname) {
    let rp = pathname.substring(1, pathname.length)
    if (!rp || rp.endsWith('/')) {
        rp = rp + 'index.html'
    }
    return rp
}

function getUrl(pathname) {
    return new URL(getRelativePath(pathname), import.meta.url).toString()
}

async function handleRequest(request) {
    const { pathname } = new URL(request.url);
    const url = getUrl(pathname)
    const contentType = getContentType(url)
    if (!contentType) {
        return fetch(url)
    } else {
        const response = await fetch(url)
        const text = await response.text()
        return new Response(text, {
            headers: {
                'content-type': contentType,
            },
        })
    }
}

addEventListener('fetch', (event) => {
    event.respondWith(handleRequest(event.request));
})