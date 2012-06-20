com.logicalnode.tarsys.collections.Tarifas = Backbone.Collection.extend({

	estudioId: 0,
	
	initialize: function(){
		this.model = com.logicalnode.tarsys.models.Tarifa;
	},
	
	url: function(){
		return "api/estudios/" + this.estudioId + "/optimizar";
	},
});
