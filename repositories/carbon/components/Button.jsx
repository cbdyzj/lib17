export default function Button(props) {

    const classNames = ['focus:outline-none']

    if (props.disabled) {
        classNames.push('text-blue-400')
    } else {
        classNames.push('text-blue-600')
    }

    if (props.className) {
        classNames.push(props.className)
    }

    props = {
        ...props,
        className: classNames.join(' '),
    }

    return (
        <button {...props}>
            {props.children}
        </button>
    )
}