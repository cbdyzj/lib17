const mailer = require('nodemailer')

const mail = mailer.createTransport({
    host: 'smtp.qq.com',
    port: 465,
    secure: true,
    auth: {
        user: 'cbdyzj@jianzhao.org',
        pass: ''
    }
})

const message = {
    from: 'cbdyzj@jianzhao.org',
    to: 'cbdyzj@gmail.com',
    subject: 'Mail by Nodemailer',
    html: '<h1>Test!</h1>',
    attachments: [{
        filename: 'image.jpg',
        path: './image.jpg',
    }]
}

async function main() {
    try {
        await mail.verify()
        const res = await mail.sendMail(message)
        console.log(res)
    } catch (error) {
        console.error(error)
    }
}

if (require.main === module) {
    main()
}