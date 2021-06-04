import React, { useRef } from 'react'

import style from './Scroller.module.less'

export default function Scroller(props) {

    const adaptiveRef = useRef()
    const containerRef = useRef()

    function handleScroll() {
        if (!adaptiveRef.current || !containerRef.current) {
            return
        }
        const adaptiveOffsetWidth = adaptiveRef.current['offsetWidth']
        const containerScrollLeft = containerRef.current['scrollLeft']
        const containerClientWidth = containerRef.current['clientWidth']
        if (adaptiveOffsetWidth === 800) {
            const len = 800 + 20 + 20
            if (containerScrollLeft + containerClientWidth < len) {
                adaptiveRef.current.style.left = 0
            } else {
                adaptiveRef.current.style.left = containerScrollLeft + containerClientWidth - len + 'px'
            }
        } else if (adaptiveOffsetWidth === 1000) {
            adaptiveRef.current.style.left = containerScrollLeft + 'px'
        } else {
            adaptiveRef.current.style.left = containerScrollLeft + 'px'
        }
    }

    return (
        <div className={style.container}
             ref={containerRef}
             onScroll={handleScroll}>
            <span>
                <p className="uppercase">Container</p>
                <p>width: 100%</p>
           </span>
            <div ref={adaptiveRef} className="adaptive">
                <p className="uppercase">Adaptive block</p>
                <p>min-width: 800px | max-width: 1000px</p>
            </div>
            <div className="wide">
                <p className="uppercase">Very wide block</p>
                <p>width: 1200px</p>
            </div>
            <div className="footer">
                <p className="uppercase">Footer</p>
            </div>
        </div>
    )
}