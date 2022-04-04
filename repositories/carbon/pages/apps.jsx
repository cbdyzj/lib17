import Head from 'next/head'
import Markdown from '../components/Markdown'
import LocaleSelect from '../components/LocaleSelect'
import CarbonHead from '../components/CarbonHead'
import { getAppList } from '../api/natrium'
import Button from '../components/Button'

export default function Apps(props) {
    const { appList = [] } = props

    function handleClickCreateApp(ev) {
    }

    return (
        <div>
            <Head>
                <title>Apps - carbon</title>
            </Head>
            <Markdown page>
                <CarbonHead />
                <h2 id="应用列表">应用列表</h2>

                <div className="mb-1">
                    <span>共{appList.length}个应用</span>
                    <span className="mx-1">|</span>
                    <Button onClick={handleClickCreateApp}>新建应用</Button>
                </div>

                <table>
                    <thead>
                    <tr>
                        <th>应用名称</th>
                        <th>应用ID</th>
                        <th>页面</th>
                        <th>Key</th>
                        <th>说明</th>
                    </tr>
                    </thead>
                    <tbody>
                    {appList.map(it => {
                        return (
                            <tr key={it.id}>
                                <td>{it.url ? <a href={it.url}>{it.name}</a> : it.name}</td>
                                <td>{it.id}</td>
                                <td><a href={`/pages?appId=${it.id}`}>共{it.pageCount}个页面</a></td>
                                <td><a href={`/keys?appId=${it.id}`}>共{it.keyCount}个Key</a></td>
                                <td>{it.description ?? ''}</td>
                            </tr>
                        )
                    })}
                    </tbody>
                </table>
                {/* locale */}
                <hr />
                <LocaleSelect />
            </Markdown>
        </div>
    )
}

export async function getServerSideProps(ctx) {

    return {
        props: {
            appList: await getAppList(),
        }
    }
}