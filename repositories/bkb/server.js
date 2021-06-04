var express = require('express');

var app = express();

app.use(express.static(__dirname));
app.use('/',function(req, res){
    res.sendFile('index.html',{ root: __dirname });
});

app.listen(3000); 