requirejs.config({
    paths: {
        jquery     : 'node_modules/jquery/dist/jquery',
        underscore : 'node_modules/underscore/underscore',
        backbone   : 'node_modules/backbone/backbone',
        text       : 'node_modules/requirejs-text/text'
    }
});

require(['Router'], function(Router) {
    'use strict'
    
    var router = new Router();

});
