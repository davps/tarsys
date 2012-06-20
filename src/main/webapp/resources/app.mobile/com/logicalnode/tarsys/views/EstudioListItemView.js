com.logicalnode.tarsys.views.EstudioListItemView = Backbone.View.extend({
	tagName: "li",
	
	initialize: function(){
		_.bindAll(this, "render");
	
		this.template = _.template(tpl.get('estudio-list-item'));
	},

    render:function (eventName) {
        $(this.el).html(this.template(this.model.toJSON()));
        return this;
    },
	
});