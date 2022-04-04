import Head from 'next/head'

export default function Error(props) {

    return (
        <div>
            <Head>
                <title>Error - carbon</title>
            </Head>
            <div className="flex justify-center items-center h-screen">
                <div className="flex items-center">
                    <span className="font-medium text-xl">Error</span>
                    <span className="border-l border-gray-300 h-14 mx-5" />
                    <span>{props.message}</span>
                </div>
            </div>

        </div>
    )
}

export async function getServerSideProps(ctx) {
    const { message = 'unknown' } = ctx.query
    return {
        props: {
            message,
        }
    }
}