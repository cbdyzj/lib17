import axios from 'axios'
import { message } from 'antd'

const API = '/'

const client = axios.create({ baseURL: API })

export async function splitExcel(file) {
    const formData = new FormData()
    formData.append('file', file)
    const result = await client.post( '/api/payroll', formData,
        { responseType: 'blob' })
    if (result.status !== 200) {
        message.warn(result.statusText)
        return
    }
    const defaultName = String(new Date().getTime())
    const blobFile = {
        name: defaultName,
        blob: new Blob([result.data], { type: 'application/octet-stream' })
    }
    const disposition = result.headers['content-disposition']
    if (disposition) {
        blobFile.name = decodeURIComponent(disposition.split('filename=')[1]) || defaultName
    }
    return blobFile
}
