com.logicalnode.tarsys.collections.Facturas = Backbone.Collection.extend({
//	model: com.logicalnode.tarsys.models.Factura,
	
	estudioId: 1,
	
	initialize: function(){
		this.model = com.logicalnode.tarsys.models.Factura;
	},
	
	url: function(){
		return "api/estudios/" + this.estudioId + "/facturas";
	},
});
