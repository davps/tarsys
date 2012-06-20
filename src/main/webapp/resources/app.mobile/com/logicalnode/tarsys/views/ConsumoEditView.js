com.logicalnode.tarsys.views.ConsumoEditView = Backbone.View.extend({
	
	initialize: function(){
		_.bindAll(this, "render");
	
		this.collection.on("reset", this.render, this);
	},
	
	render: function(){
//        this.$el.attr('data-role', 'fieldcontain');
		console.log("comenzando a iterar sobre los consumos");
        this.collection.each(function(consumo){
    		console.log(consumo.get("tipo"));
        	var consumoView = new com.logicalnode.tarsys.views.ConsumoItemSegunTipoView({model:consumo});
        	this.$el.append(consumoView.render().el);
        }, this);
        var hijos = this.$el.children();
        this.$el.replaceWith(hijos);
        
        return this;
	}
});