define(['backbone','text!views/about/AboutTpl.html'],
    function(Backbone,Tpl){
    'use strict'

    var AboutView = Backbone.View.extend({
        el: '#main',
        events: {
            'click h2': 'toggle'
        },
        render: function(){
            this.$el.html(Tpl);
        },
        toggle: function(){
            $('p').toggle('normal');
        }
    });

    console.log('define about page');
    return new AboutView();
});