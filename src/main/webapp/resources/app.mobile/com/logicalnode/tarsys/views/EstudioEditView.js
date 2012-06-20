com.logicalnode.tarsys.views.EstudioEditView = Backbone.View.extend({
	events: {
		"click .guardar"	: "guardar",
		"click .cancelar"	: "cancelar",
	},

	initialize: function(){
		_.bindAll(this, "render", "guardar", "cancelar");
	
		this.template = _.template(tpl.get('estudio-edit-page'));
	},
	
    render:function (eventName) {
        $(this.el).html(this.template(this.model.toJSON()));
        return this;
    },
    
    guardar: function(){
    	console.log("[EstudioEditView].guardar()");

    	if(this.model.isNew()){
    		com.logicalnode.tarsys.app.estudios.add(this.model);
    	}
    	
    	this.model.save({
			nombre			: $("#nombre").val(),
			empresa			: $("#empresa").val(),
			categoria		: $("#categoria").val(),
			numeroDeMedidor	: $("#numeroDeMedidor").val(),
			observaciones	: $("#observaciones").val(),
   		},{
			wait: true,
			success: function(model, response){
				com.logicalnode.tarsys.app.navigate("estudios-home", {trigger: true});
			},
			error: function(){

			}
		});
//		com.logicalnode.tarsys.app.navigate("estudios-home");
    },
    
    cancelar: function(){
    	console.log("[EstudioEditView].cancelar()");
    },
});
