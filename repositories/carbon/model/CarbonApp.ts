export type Locale = string

export interface CarbonText {
    locale: Locale
    text: string
}

export interface CarbonKey {
    key: string
    pageCode: string
    original: string
    translation?: CarbonText[]
    description?: string
}

export interface CarbonPage {
    name: string
    code: string
    description?: string
    keyList?: CarbonKey[]
}

export interface CarbonApp {
    id: string
    name: string
    fallbackLocale: Locale
    localeList: Locale[]
    description?: string
    pageList?: CarbonPage[]
}

export interface CarbonAppOverview {
    id: string
    name: string
    pageCount: number
    keyCount: number
    description?: string

}