com.logicalnode.tarsys.collections.Estudios = Backbone.Collection.extend({
//for more information about why I commented this see http://stackoverflow.com/questions/9723384/upgrading-underscore-js-breaks-backbone-js
//	model: com.logicalnode.tarsys.models.Estudio,
	
	initialize: function(){
		this.model = com.logicalnode.tarsys.models.Estudio;
	},
	
	url: "api/estudios"
	
});