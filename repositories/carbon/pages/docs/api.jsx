import Head from 'next/head'
import Markdown from '../../components/Markdown'
import marked from 'marked'
import LocaleSelect from '../../components/LocaleSelect'
import CarbonHead from '../../components/CarbonHead'

export default function Api(props) {
    return (
        <div>
            <Head>
                <title>API Reference | carbon</title>
            </Head>
            <Markdown page>
                <CarbonHead />
                <div dangerouslySetInnerHTML={{ __html: marked(props.text) }} />
                <hr />
                <LocaleSelect />
            </Markdown>
        </div>
    )
}

export async function getServerSideProps(ctx) {
    const response = await fetch('https://carbon-cbdyzj.vercel.app/docs/API.md')
    const text = await response.text()
    return {
        props: {
            text,
        }
    }
}
