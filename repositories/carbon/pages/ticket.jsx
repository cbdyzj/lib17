import Head from 'next/head'
import Cookies from 'js-cookie'
import Markdown from '../components/Markdown'
import CarbonHead from '../components/CarbonHead'
import LocaleSelect from '../components/LocaleSelect'
import Button from '../components/Button'
import { useState } from 'react'

const TICKET = 'ticket'

function getTicketCookie() {
    return Cookies.get(TICKET) || ''
}

export default function Apps(props) {

    const [ticketCookie, setTicketCookie] = useState(getTicketCookie)
    const [ticket, setTicket] = useState(getTicketCookie)

    function handleUpdateTicket() {
        Cookies.set(TICKET, ticket)
        setTicketCookie(ticket)
    }

    function handleInputValueChange(ev) {
        setTicket(ev.target.value)
    }

    return (
        <div>
            <Head>
                <title>Apps - carbon</title>
            </Head>
            <Markdown page>
                <CarbonHead />
                <h2 id="Ticket">Ticket</h2>

                <div className="mb-1">
                    <Button disabled={ticket === ticketCookie} onClick={handleUpdateTicket}>更新Ticket</Button>
                </div>
                <input className="border-b outline-none" type="text" value={ticket} onChange={handleInputValueChange} />

                {/* locale */}
                <hr />
                <LocaleSelect />
            </Markdown>
        </div>
    )
}