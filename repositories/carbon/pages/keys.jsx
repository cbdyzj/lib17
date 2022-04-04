import Head from 'next/head'
import LocaleSelect from '../components/LocaleSelect'
import Markdown from '../components/Markdown'
import CarbonHead from '../components/CarbonHead'
import { withErrorHandling } from '../utils/error'
import { getApp } from '../api/natrium'
import { useState } from 'react'
import Modal from '../components/Modal'
import Button from '../components/Button'

function KeyModal(props) {

    const [key, setKey] = useState('')
    const [pageCode, setPageCode] = useState(props.pageList[0]?.code)
    const [original, setOriginal] = useState('')
    const [description, setDescription] = useState('')

    async function handleClickConfirm(ev) {
        await props.onSubmit({
            key,
            pageCode,
            original,
            description,
        })
        props.onClose()
    }

    return (
        <Modal visible={props.visible} onClose={props.onClose}>
            <div className="bg-white border border-white shadow p-4">
                <h3 className="font-medium">新增Key</h3>
                <div className="mt-2">
                    <span className="inline-block w-1/5">Key: </span>
                    <input value={key} onChange={ev => setKey(ev.target.value)}
                           className="border-b outline-none inline-block w-4/5" type="text" />
                </div>
                <div className="mt-2">
                    <span className="inline-block w-1/5">页面: </span>
                    <select value={pageCode} onChange={ev => setPageCode(ev.target.value)}
                            className="inline-block w-4/5">
                        {props.pageList.map(it => {
                            return (
                                <option key={it.code} value={it.code}>{it.name}</option>
                            )
                        })}
                    </select>
                </div>
                <div className="mt-2">
                    <span className="inline-block w-1/5">原文: </span>
                    <input value={original} onChange={ev => setOriginal(ev.target.value)}
                           className="border-b outline-none inline-block w-4/5" type="text" />
                </div>
                <div className="mt-2">
                    <span className="inline-block w-1/5">说明: </span>
                    <input value={description} onChange={ev => setDescription(ev.target.value)}
                           className="border-b outline-none inline-block w-4/5" type="text" />
                </div>

                <div className="mt-2">
                    <Button onClick={props.onClose}>取消</Button>
                    <Button className="ml-1" onClick={handleClickConfirm}>确认</Button>
                </div>

            </div>
        </Modal>
    )
}

function getKeyList(app) {
    if (!Array.isArray(app.pageList)) {
        return []
    }

    function getPageName(code) {
        return app.pageList.find(it => it.code === code)?.name ?? ''
    }

    return app.pageList
        .map(it => it.keyList)
        .filter(it => Array.isArray(it))
        .flatMap(it => it)
        .map(it => {
            return {
                key: it.key,
                url: `/key?appId=${app.id}&key=${it.key}`,
                pageCode: it.pageCode,
                pageName: getPageName(it.pageCode),
                original: it.original,
                description: it.description ?? ''
            }
        })
}

function getPageList(app) {
    return (app.pageList ?? []).map(it => {
        return {
            code: it.code,
            name: it.name,
        }
    })
}

export default function Keys(props) {

    const [app, setApp] = useState(props.app)
    const [keyList, setKeyList] = useState(props.keyList)
    const [modalVisible, setModalVisible] = useState(false)

    function handleClickCreateKey(ev) {
        setModalVisible(true)
    }

    async function addNewKey(newKey) {
        console.log(newKey)
        // todo
    }

    return (
        <div>
            <Head>
                <title>Keys - carbon</title>
            </Head>
            <Markdown page>
                <CarbonHead />
                <h2 id="key列表">Key列表</h2>

                <div className="mb-1">
                    <span>共{keyList.length}个Key</span>
                    <span className="mx-1">|</span>
                    <Button onClick={handleClickCreateKey}>新增Key</Button>
                </div>

                <table>
                    <thead>
                    <tr>
                        <th>Key</th>
                        <th>页面</th>
                        <th>原文</th>
                        <th>说明</th>
                    </tr>
                    </thead>
                    <tbody>
                    {keyList.map(it => {
                        return (
                            <tr key={it.key}>
                                <td><a href={it.url}>{it.key}</a></td>
                                <td>{it.pageName}</td>
                                <td>{it.original}</td>
                                <td>{it.description}</td>
                            </tr>
                        )
                    })}
                    </tbody>
                </table>

                {/* locale */}
                <hr />
                <LocaleSelect />
            </Markdown>
            <KeyModal pageList={props.pageList}
                      visible={modalVisible}
                      onSubmit={addNewKey}
                      onClose={() => setModalVisible(false)} />
        </div>
    )
}

export const getServerSideProps = withErrorHandling(async function (ctx) {
    const { appId } = ctx.query

    if (!appId) {
        return {
            redirect: {
                destination: '/',
                permanent: false,
            }
        }
    }

    const app = await getApp(appId)

    const pageList = getPageList(app)
    const keyList = getKeyList(app)

    return {
        props: {
            app,
            pageList,
            keyList,
        }
    }
})