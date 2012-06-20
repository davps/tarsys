com.logicalnode.tarsys.views.EstudioHomeView = Backbone.View.extend({


	initialize: function(){
		_.bindAll(this, "render");
	
		this.template = _.template(tpl.get('estudio-home-page'));
	},
	
    render:function (eventName) {
        $(this.el).html(this.template());
        return this;
    }
});
