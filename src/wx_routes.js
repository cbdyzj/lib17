const { Router } = require('express')

const wxRouter = Router()

wxRouter.get("/wx",(req,res)=>{
    console.log(req.query)
    res.end('wx')
})

module.exports = wxRouter
