import { CarbonApp } from "@/model/CarbonApp"

const CARBON_APP_API = '/api/app'

export async function getApp(appId: string): Promise<CarbonApp> {
    const response = await fetch(`${CARBON_APP_API}?appId=${appId}`)
    return await response.json()
}

export async function createApp(app: CarbonApp | unknown) {
    const response = await fetch(CARBON_APP_API, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(app),
    })
    return await response.json()
}

export async function updateApp(app: CarbonApp | unknown) {
    const response = await fetch(CARBON_APP_API, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(app)
    })
    return await response.json()
}
