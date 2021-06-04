define(['backbone','text!views/article/ArticleTpl.html'],
    function(Backbone,Tpl){
    'use strict'

    var ArticleView = Backbone.View.extend({
        el: '#main',
        events: {},
        render:function(n) {
            this.$el.html(Tpl);
            $('h3').text('title ' + n);
            $('h3+p').text('content ' + n);
        }
    });

    console.log('define article page');
    return new ArticleView();
});