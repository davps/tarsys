com.logicalnode.tarsys.views.EstudioOptimizerView = Backbone.View.extend({

	initialize: function(){
		_.bindAll(this, "render");
	
		this.template = _.template(tpl.get('estudio-optimizer-page'));

		this.tarifas = new com.logicalnode.tarsys.collections.Tarifas();
		this.tarifas.estudioId = this.model.get("id");
		this.tarifas.on("reset", this.render, this);
		this.tarifas.fetch();
	},
	
    render:function (eventName) {
    	if(this.tarifas.length > 0){
    		this.$el.attr('data-role', 'page');
        	var tarifa;
        	this.tarifas.each(function(t){
        		tarifa = t;
        	}, this);
        	    	
            $(this.el).html(this.template({
            	estudio: this.model.toJSON(),
            	tarifaOptima : tarifa.toJSON(),
            }));
            $.mobile.changePage(this.$el);
    	}
        return this;
    },
    
});
