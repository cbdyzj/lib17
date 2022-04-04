import { NextApiRequest, NextApiResponse } from 'next'
import { getText } from "@/api/natrium"

export default async function (req: NextApiRequest, res: NextApiResponse) {
    try {
        if (req.method === 'GET') {
            const query: any = req.query
            const result = await getText(query.appId, query.key, query.locale)
            res.status(200).send(result)
        } else {
            res.status(404).end()
        }
    } catch (err) {
        console.error(err)
        res.status(400).send(err.message)
    }
}
