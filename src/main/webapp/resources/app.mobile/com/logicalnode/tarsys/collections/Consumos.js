com.logicalnode.tarsys.collections.Consumos = Backbone.Collection.extend({
//	model: com.logicalnode.tarsys.models.Consumo,

	initialize: function(){
		this.model = com.logicalnode.tarsys.models.Consumo;
	},
	
	estudioId: 0,

	facturaId: 0,
	
	url: function(){
		return "api/estudios/" + this.estudioId + 
			   			   "/facturas/" + this.facturaId + 
			   			   "/consumos" ;
	},
});
