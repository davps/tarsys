com.logicalnode.tarsys.views.ConsumoItemSegunTipoView = Backbone.View.extend({
	
	initialize: function(){
		_.bindAll(this, "render", "selectHtmlLabelByType");
	
		this.template = _.template(tpl.get("consumo-item"));
		this.model.on("change", this.render, this);
	},
	
	render: function(){
		this.$el.attr("data-role", "fieldcontain");
		this.$el.addClass("ui-field-contain ui-body ui-br");
		
		var label = this.selectHtmlLabelByType(this.model.get("tipo"));
		console.log("el label es: " + label);
//		var tipo = {tipo:label};
		this.$el.html(this.template({
				consumo	: this.model.toJSON(),
				label	: label,
		}));

		$("input", this.$el).addClass("ui-input-text ui-body-c ui-corner-all ui-shadow-inset");
		
		return this;
	},
	
	selectHtmlLabelByType: function(tipo){
		var c = "";
		
		if(tipo == "PotenciaReservada") 					c = "Potencia Reservada" 							;
		if(tipo == "ExcesoDePotenciaReservada") 			c = "Exceso de Potencia Reservada" 					;
		if(tipo == "EnergiaEnPunta") 						c = "Energia en Punta" 								;
		if(tipo == "EnergiaFueraDePunta") 					c = "Energia fuera de Punta" 						;
		if(tipo == "Energia") 								c = "Energia" 										;
		if(tipo == "PotenciaReservadaEnPunta") 				c = "Potencia Reservada en Punta" 					;
		if(tipo == "PotenciaReservadaFueraDePunta") 		c = "Potencia Reservada fuera de Punta" 			;
		if(tipo == "ExcesoDePotenciaReservadaEnPunta") 		c = "Exceso de Potencia Reservada en Punta" 		;
		if(tipo == "ExcesoDePotenciaReservadaFueraDePunta") c = "Exceso de Potencia Reservada fuera de Punta"	;

		return c;
	},
});