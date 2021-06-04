import React, { useState } from 'react'

import style from './style.module.less'
import { Upload, Typography, Button } from 'antd'
import { InboxOutlined } from '@ant-design/icons'

import { splitExcel } from '@/api/excel'

const { Paragraph } = Typography

export default function PayrollExcel() {

    const [file, setFile] = useState(null)
    const [pending, setPending] = useState(false)
    const [resultFile, setResultFile] = useState(null)

    async function handleUpload() {
        setPending(true)
        const result = await splitExcel(file)
        setPending(false)
        if (result) {
            setResultFile(result)
        }
    }

    function handleDownload() {
        if (!resultFile) {
            return
        }
        const url = window.URL.createObjectURL(resultFile.blob)
        let link = document.createElement('a')
        link.style.display = 'none'
        link.href = url
        link.download = resultFile.name
        link.click()
    }

    const uploadConfig = {
        style: {
            width: '250px',
            margin: 'auto',
        },
        accept: '.xlsx',
        showUploadList: false,
        beforeUpload(file, fileList) {
            setFile(file)
            setResultFile(null)
            return false
        },
    } as any

    const tip = file ? file.name : '点击或拖拽文件到此处上传'

    return (
        <div className={style['content-box']}>
            <section className={style['upload-form']}>
                <Paragraph>把大Excel拆分成一个个小Excel的小工具</Paragraph>
                <Upload.Dragger {...uploadConfig}>
                    <p className="ant-upload-drag-icon">
                        <InboxOutlined/>
                    </p>
                    <p className="ant-upload-text">{tip}</p>
                    <p className="ant-upload-hint">
                        每次上传一个文件
                    </p>
                </Upload.Dragger>
                <Button
                    disabled={!file}
                    loading={pending}
                    onClick={handleUpload}
                    className={style['op-button']}
                    type="primary">
                    上传并拆分
                </Button>
                <Button
                    hidden={!resultFile}
                    onClick={handleDownload}
                    className={style['download-button']}>
                    下载结果
                </Button>
            </section>
            <section/>
        </div>
    )
}
