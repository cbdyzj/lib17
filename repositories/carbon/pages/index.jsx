import Head from 'next/head'
import Markdown from '../components/Markdown'
import LocaleSelect from '../components/LocaleSelect'
import CarbonHead from '../components/CarbonHead'

export default function Home() {

    return (
        <div>
            <Head>
                <title>carbon</title>
            </Head>

            <Markdown page>
                <CarbonHead />
                <blockquote>
                    <p>国际化资源小工具</p>
                </blockquote>
                <ul>
                    <li><a href={'/apps'}>进入应用列表</a></li>
                    <li><a href={'/ticket'}>Ticket</a></li>
                </ul>
                <h3 id="文档">文档</h3>
                <table>
                    <thead>
                    <tr>
                        <th>链接</th>
                        <th>说明</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td><a href={'/docs/api'}>API文档</a></td>
                        <td>HTTP API</td>
                    </tr>
                    <tr>
                        <td>Java SDK</td>
                        <td>0依赖，0侵入</td>
                    </tr>
                    <tr>
                        <td>JavaScript SDK</td>
                        <td>单个<code>&lt;script&gt;</code>标签完成集成</td>
                    </tr>
                    </tbody>
                </table>
                {/* locale */}
                <hr />
                <LocaleSelect />
            </Markdown>
        </div>
    )
}
