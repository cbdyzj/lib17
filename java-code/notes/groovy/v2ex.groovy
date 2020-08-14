@Grab('org.jsoup:jsoup:1.10.1')
import org.jsoup.Jsoup

Jsoup.connect('https://v2ex.com').get()
    .select('span.item_title > a').each {
    println it.text() + ':' + 'https://v2ex.com' + it.attr('href')
}
