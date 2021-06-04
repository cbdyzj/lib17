define(['backbone','text!views/home/HomeTpl.html'],
    function(Backbone,Tpl){
    'use strict'

    var HomeView = Backbone.View.extend({
        el: '#main',
        events: {},
        render:function() {
            this.$el.html(Tpl);
        }
    });

    console.log('define home page');
    return new HomeView();
});