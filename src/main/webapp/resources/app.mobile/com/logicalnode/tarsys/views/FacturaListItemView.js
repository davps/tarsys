com.logicalnode.tarsys.views.FacturaListItemView = Backbone.View.extend({
	tagName: "li",
	
	initialize: function(){
		_.bindAll(this, "render");
	
		this.template = _.template(tpl.get('factura-list-item'));
	},

    render:function (eventName) {
        $(this.el).html(this.template({
        		factura:this.model.toJSON(),
        		estudio:this.estudio.toJSON()
        	}));
        return this;
    },
	
});