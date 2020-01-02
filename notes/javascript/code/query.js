const mysql = require('mysql2/promise');

const config = {
    host: 'localhost',
    user: 'root',
    password: 'password',
    database: 'test'
}

async function main() {
    const connection = await mysql.createConnection(config)
    const [rows, fields] = await connection.execute('SELECT NOW();')
    console.log(rows)
}

if (require.main === module) {
    main().then(() => process.exit(0))
}
