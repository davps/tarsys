com.logicalnode.tarsys.views.FacturaListView = Backbone.View.extend({

	initialize: function(){
		_.bindAll(this, "render");
	
		this.template = _.template(tpl.get('factura-list-page'));

		_.bindAll(this, "render");
		this.collection.on("reset", this.render, this);

	},
	
    render:function (eventName) {
        $(this.el).html(this.template());
        
        console.log("la colección de facturas es");
    	this.collection.each(function(factura){
    		console.log(factura);
    		var facturaItemView = new com.logicalnode.tarsys.views.FacturaListItemView({model:factura});
    		facturaItemView.estudio = this.estudio;
    		$("ul", this.el).append(facturaItemView.render().el);
    	}, this);
        return this;
    }
	
});
