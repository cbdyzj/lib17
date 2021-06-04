define(['backbone'],function(Backbone){
    'use strict'

    return Backbone.Router.extend({
        routes: {
            ''      : 'homePage',
            'home'  : 'homePage',
            'about' : 'aboutPage',
            'article:n' : 'articlePage'
        },
        initialize  : function(){
            Backbone.history.start({pushState: true});
        },
        homePage    : function(){
            require(['views/home/HomeView'],function(HomeView){
                HomeView.render();
            });
        },
        aboutPage   : function(){
            require(['views/about/AboutView'],function(AboutView){
                AboutView.render();
            });
        },
        articlePage : function(n){
            require(['views/article/ArticleView'],function(ArticleView){
                ArticleView.render(n);
            });
        }
    });
});