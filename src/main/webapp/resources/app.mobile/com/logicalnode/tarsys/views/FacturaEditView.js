com.logicalnode.tarsys.views.FacturaEditView = Backbone.View.extend({
	events: {
		"click .guardar"	: "guardar",
		"click .cancelar"	: "cancelar",
	},
	
//	estudio_id: 0,

//	tipoDeFormulario : 0,

//	template : null,

	initialize: function(){
		_.bindAll(this, "render", "guardar", "guardarFactura", "guardarConsumos", "cancelar");
	
		this.template = _.template(tpl.get("factura-edit-page"));
//		this.template1 = _.template(tpl.get("factura-edit-page-1"));
//		this.template2 = _.template(tpl.get("factura-edit-page-2"));
//		this.template3 = _.template(tpl.get("factura-edit-page-3"));
		
	},
	
	render: function(){
		this.$el.html(this.template(this.model.toJSON()));
		
		//com.logicalnode.tarsys.app.consumos
		var consumoView = new com.logicalnode.tarsys.views.ConsumoEditView({
			collection : this.collection
		});
		$("#replace-by-consumos-divs", this.el).replaceWith(consumoView.render().el);
	},
	
    guardar: function(){
    	console.log("[EstudioEditView].guardar()");

    	if(this.model.isNew()){
    		com.logicalnode.tarsys.app.facturas.add(this.model);
    	}
    	this.guardarFactura();
    	
    	
//		constante : $("#constante").val(),
    	
    },
    
    guardarFactura: function(){
    	this.model.save({
    		numeroDeFactura : $("#numeroDeFactura").val()
    	},{
			wait: true,
			success: this.guardarConsumos,
			error: function(){}
    	});
    },
    
    guardarConsumos: function(model, response){
    	console.log("el modelo ya guardado es");
    	console.log(model);
    	this.collection.each(function(consumo){
    		var lectura = $("#" + consumo.get("tipo")).val();
    		console.log("la lectura es:");
    		console.log(lectura);
    		consumo.save({
    			lectura : lectura,
    			factura	: model.get("id"),
    		});
    	}, this);
    	
		com.logicalnode.tarsys.app.navigate("estudios/" + model.get("estudio") + "/main", {trigger: true});
    },
    
    cancelar: function(){
    	console.log("[EstudioEditView].cancelar()");
    },
    
});
