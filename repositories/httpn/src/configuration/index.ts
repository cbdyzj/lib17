import * as development from './development'

const env = process.env.NODE_ENV || 'development'

export const configuration: any = {}

switch (env) {
    case 'production':
    case 'test':
    case 'development':
    default:
        Object.assign(configuration, development)
        break
}
