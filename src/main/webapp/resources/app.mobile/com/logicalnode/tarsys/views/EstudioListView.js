com.logicalnode.tarsys.views.EstudioListView = Backbone.View.extend({


	initialize: function(){
		_.bindAll(this, "render");
	
		this.template = _.template(tpl.get('estudio-list-page'));
	},
	
    render:function (eventName) {
        $(this.el).html(this.template());
    	this.collection.each(function(estudio){
    		var itemView = new com.logicalnode.tarsys.views.EstudioListItemView({model:estudio});
    		$("ul", this.el).append(itemView.render().el);
    	}, this);
        return this;
    }
});
