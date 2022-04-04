export default function Markdown(props) {

    if (!props.page) {
        return (
            <div className="markdown-body">{props.children}</div>
        )
    }

    return (
        <div className="flex justify-center">
            <div className="markdown-body mt-4 mb-10 max-w-4xl text-lg w-3/4">
                {props.children}
            </div>
        </div>
    )
}
