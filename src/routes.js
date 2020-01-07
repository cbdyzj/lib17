const { Router } = require('express')

const router = Router()

router.get('/api/hi', (req, res) => {
    res.end('hi')
})

module.exports = router
