import { useState } from 'react'

export default function LocaleSelect(props) {

    const [locale, setLocale] = useState('zh-CN')

    function handleSelectLocale(ev) {
        setLocale(ev.target.value)
    }

    // noinspection HtmlUnknownTarget
    return (
        <span>
           <img className="inline-block w-6" src="/language.svg" alt="language" />
           <select className="inline-block ml-0.5" value={locale} onChange={handleSelectLocale}>
               <option value="zh-CN">简体中文</option>
               <option value="zh-HK">繁体中文（香港）</option>
               <option value="en-US">English (United States)</option>
           </select>
       </span>
    )
}