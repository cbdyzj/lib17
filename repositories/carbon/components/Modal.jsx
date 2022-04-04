export default function Modal(props) {

    const modalClassName = 'fixed flex flex-col justify-center overflow-hidden items-center inset-0 z-40'

    return (
        <div className={`${modalClassName}${props.visible ? '' : ' hidden'}`}>
            <div className="absolute inset-0 bg-black opacity-20" onClick={props.onClose} />
            <div className="relative my-0 mx-auto">
                {props.children}
            </div>
        </div>
    )
}
