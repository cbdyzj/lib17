import { NextApiRequest, NextApiResponse } from 'next'
import { createApp, getApp, updateApp } from "@/api/natrium"
import { withAuth } from '@/security/auth'

export default withAuth(async function (req: NextApiRequest, res: NextApiResponse) {
    try {
        if (req.method === 'GET') {
            const result = await getApp(req.query.appId as string)
            res.status(200).send(result)
        } else if (req.method === 'PUT') {
            const result = await updateApp(req.body)
            res.status(200).send(result)
        } else if (req.method === 'POST') {
            const result = await createApp(req.body)
            res.status(200).send(result)
        } else {
            res.status(404).end()
        }
    } catch (err) {
        console.error(err)
        res.status(400).send({error: err.message})
    }
})