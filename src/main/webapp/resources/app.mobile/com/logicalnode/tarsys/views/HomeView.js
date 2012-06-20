com.logicalnode.tarsys.views.HomeView = Backbone.View.extend({

	initialize: function(){
		_.bindAll(this, "render");
	
		this.template = _.template(tpl.get('home-page'));
	},
	
    render:function (eventName) {
        $(this.el).html(this.template());
        return this;
    },
    
});
