import { CARBON_TICKET } from '@/env'

const TICKET = CARBON_TICKET || ''

export function withAuth(handler) {
    return async (req, res) => {
        const ticket = req.cookies.ticket || ''
        if (TICKET !== ticket) {
            res.status(400).send({ error: 'Illegal ticket' })
        } else {
            await handler(req, res)
        }
    }
}