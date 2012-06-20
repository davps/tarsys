com.logicalnode.tarsys.views.EstudioMainView = Backbone.View.extend({


	initialize: function(){
		_.bindAll(this, "render", "renderGraficoEstadisticas");
	
		this.template = _.template(tpl.get('estudio-main-page'));
	},
	
    render:function (eventName) {
        $(this.el).html(this.template(this.model.toJSON()));
        
    	
    	var url = "api/estudios/" + this.model.get("id") + " /consumos"
    	this.consumos = new com.logicalnode.tarsys.collections.Consumos({url:url});
    	this.consumos.url = url;
    	this.consumos.fetch({
    		success: this.renderGraficoEstadisticas
    	});
    	
        return this;
    },
    
    renderGraficoEstadisticas: function(model, response){
    	var hashConsumos = {};
    	this.consumos.each(function(consumo, index){
    		if( hashConsumos[consumo.get("tipo")] === undefined ){
    			hashConsumos[consumo.get("tipo")] = [];
    		}
    		var point = [index, consumo.get("lectura")];
    		hashConsumos[consumo.get("tipo")].push(point);
    	}, this);
    	
    	var curves = [];
    	_.each(hashConsumos, function(value, key){
    		curves.push(value);
    	}, this);

    	_.each(curves, function(curve){
    		_.each(curve, function(point, index){
    			point[0] = index;
    			point[1] = point[1] / 1; 
    		}, this);
    	}, this);

    	console.log(curves);

    	(function basic(container) {
    		  graph = Flotr.draw(container, curves, {
    		    xaxis: {
    		      minorTickFreq: 4
    		    }, 
    		    grid: {
    		      minorVerticalLines: true
    		    }
    		  });
    		})(document.getElementById("grafico-de-consumos"));    	
    	

    	
    },
    
});
