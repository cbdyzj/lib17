import React, { Suspense, lazy } from 'react'
import { HashRouter as Router, Link, Route, Redirect, Switch } from 'react-router-dom'

import { Spin } from 'antd'

import style from './style.module.less'

const PayrollExcel = lazy(() => import('./PayrollExcel/PayrollExcel'))

function Loading() {
    const style = {
        width: '100%',
        minHeight: '60vh',
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'center',
    }
    return (
        <div style={style}>
            <Spin size="large" />
        </div>
    )
}

export default function App() {

    return (
        <div className={style.container}>
            <Router>
                <nav>
                    <ul className={style['nav-ul']}>
                        <li><a href={'/'}>Payroll</a></li>
                        <li><a href={'/swagger-ui/'}>Swagger UI</a></li>
                        <li><a href={'https://github.com/cbdyzj/payroll'}>GitHub</a></li>
                        <li>
                            <Link to="/payroll-excel">Payroll Excel</Link>
                        </li>
                    </ul>
                </nav>
                <Suspense fallback={<Loading />}>
                    <Switch>
                        <Route path="/" exact><Redirect to="/payroll-excel" /></Route>
                        <Route path="/payroll-excel"><PayrollExcel /></Route>
                        <Route path="*">
                            <div>No match route</div>
                        </Route>
                    </Switch>
                </Suspense>
            </Router>
        </div>
    )
}
