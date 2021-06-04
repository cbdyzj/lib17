// noinspection JSCheckFunctionSignatures

import React, { Suspense, lazy } from 'react'
import { HashRouter as Router, Route, Switch } from 'react-router-dom'

import './App.less'

const Index = lazy(() => import('@/pages/Index/Index'))
const Hello = lazy(() => import('@/pages/Hello/Hello'))
const Bind2 = lazy(() => import('@/pages/Bind2/Bind2'))
const PictureList = lazy(() => import('@/pages/PictureList/PictureList'))
const Layout = lazy(() => import('@/pages/Layout/Layout'))
const ATemplate = lazy(() => import('@/pages/ATemplate/ATemplate'))
const Security = lazy(() => import('@/pages/Security/Security'))
const X = lazy(() => import('@/pages/X/X'))
const Transformer = lazy(() => import('@/pages/Transformer/Transformer'))
const Scroller = lazy(() => import('@/pages/Scroller/Scroller'))

function Loading(props) {
    return (
        <div>loading...</div>
    )
}

export default function App(props) {

    return (
        <Router>
            <Suspense fallback={<Loading />}>
                <Switch>
                    <Route path="/" exact><Index /></Route>
                    <Route path="/hello"><Hello /></Route>
                    <Route path="/bind2"><Bind2 /></Route>
                    <Route path="/picture_list"><PictureList /></Route>
                    <Route path="/layout"><Layout /></Route>
                    <Route path="/scroller"><Scroller /></Route>
                    <Route path="/a_template"><ATemplate /></Route>
                    <Route path="/security"><Security /></Route>
                    <Route path="/x"><X /></Route>
                    <Route path="/transformer"><Transformer /></Route>
                    <Route path="*">
                        <div>No match route</div>
                    </Route>
                </Switch>
            </Suspense>
        </Router>
    )
}
