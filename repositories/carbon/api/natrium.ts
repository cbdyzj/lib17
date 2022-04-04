// natrium carbon service
// https://natrium.herokuapp.com/
import { assert } from "@/utils/assert"
import { NANO_API_TOKEN } from '@/env'
import { CarbonApp, Locale, CarbonText, CarbonAppOverview } from "@/model/CarbonApp";

const NATRIUM = process.env.NODE_ENV === 'production' ? 'https://natrium.herokuapp.com' : 'http://localhost:8080'

const CARBON_APP_API = NATRIUM + '/api/carbon/app'
const CARBON_APP_LIST_API = NATRIUM + '/api/carbon/app/list'
const CARBON_TEXT_API = NATRIUM + '/api/carbon/text'

export async function createApp(app: CarbonApp) {
    const response = await fetch(CARBON_APP_API, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'X-Token': NANO_API_TOKEN,
        },
        body: JSON.stringify(app),
    })
    const result = await response.json()
    assert(!result.error, result.error)
    return result.payload ?? {}
}

export async function updateApp(app: CarbonApp) {
    const response = await fetch(CARBON_APP_API, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
            'X-Token': NANO_API_TOKEN,
        },
        body: JSON.stringify(app)
    })
    const result = await response.json()
    assert(!result.error, result.error)
    return result.payload ?? {}
}


export async function getApp(appId: string): Promise<CarbonApp> {
    const response = await fetch(`${CARBON_APP_API}?appId=${appId}`)
    const result = await response.json()
    assert(!result.error, result.error)
    return result.payload ?? {}
}

export async function getAppList(): Promise<CarbonAppOverview[]> {
    const response = await fetch(CARBON_APP_LIST_API)
    const result = await response.json()
    assert(!result.error, result.error)
    return result.payload ?? {}
}

export async function getText(appId: string, key: string, locale: Locale): Promise<CarbonText> {
    const q = new URLSearchParams({
        appId,
        key,
        locale,
    })
    const response = await fetch(`${CARBON_TEXT_API}?${q.toString()}`)
    const result = await response.json()
    assert(!result.error, result.error)
    return result.payload ?? {}
}
