com.logicalnode.tarsys.routers.AppRouter = Backbone.Router.extend({

    routes:{
        ""												: "home",
        "estudios-home"									: "estudioHomePage",
        "estudios"										: "estudioListPage",
        "estudios/nuevo"								: "estudioNewPage",
        "estudios/:id_estudio"							: "estudioEditPage",
        "estudios/:id_estudio/main"						: "estudioMainPage",
        "estudios/:id_estudio/optimizar"				: "estudioOptimizerPage",
        "estudios/:id_estudio/facturas"					: "facturaListPage",
        "estudios/:id_estudio/facturas/nuevo"			: "facturaNewPage",
        "estudios/:id_estudio/facturas/:id_factura"		: "facturaEditPage",
        "page1"											: "page1",
        "page2"											: "page2",
    },

    initialize:function () {
		_.bindAll(this, "home", "estudioHomePage", "estudioListPage", "estudioNewPage", "estudioEditPage", "facturaListPage", "facturaEditPage", "changePage");
	
        // Handle back button throughout the application
        $('.back').live('click', function(event) {
            window.history.back();
            return false;
        });
        this.firstPage = true;
        
        this.estudios = new com.logicalnode.tarsys.collections.Estudios();
        this.estudios.fetch();
        
        this.facturas = new com.logicalnode.tarsys.collections.Facturas();
//        this.consumos = new com.logicalnode.tarsys.collections.Consumos();
    },

    home:function () {
        console.log('#home');
        this.changePage(new com.logicalnode.tarsys.views.HomeView());
    },
    
    estudioHomePage: function(){
    	console.log("#estudios-home");
    	this.changePage(new com.logicalnode.tarsys.views.EstudioHomeView());
    },
    
    estudioListPage: function(){
    	console.log("#estudios");
    	var self = this;
    	this.estudios.fetch({
    		success: function(){
    	    	self.changePage(new com.logicalnode.tarsys.views.EstudioListView({collection:self.estudios}));
    		}
    	});
    },
    
    estudioNewPage: function(){
    	console.log("#estudios/nuevo");
    	var estudio = new com.logicalnode.tarsys.models.Estudio();
    	this.changePage(new com.logicalnode.tarsys.views.EstudioEditView({model:estudio}));
    },
    
    estudioEditPage: function(id_estudio){
    	console.log("#estudios/" + id_estudio);
    	
    	var estudio = this.estudios.get(id_estudio);//new com.logicalnode.tarsys.models.Estudio();
    	this.changePage(new com.logicalnode.tarsys.views.EstudioEditView({model:estudio}));
    },
    
    estudioMainPage: function(id_estudio){
    	console.log("#estudios/" + id_estudio + "/main");
    	
    	var estudio = this.estudios.get(id_estudio);
    	this.changePage(new com.logicalnode.tarsys.views.EstudioMainView({model:estudio}));
    },
    
    estudioOptimizerPage: function(id_estudio){
    	console.log("#estudios/" + id_estudio + "/main");

    	var estudio = this.estudios.get(id_estudio);
    	this.changePage(new com.logicalnode.tarsys.views.EstudioOptimizerView({model:estudio}));
    },
    
    facturaListPage: function(id_estudio){
    	console.log("#estudios/" + id_estudio);
    	var estudio = this.estudios.get(id_estudio);
    	var self = this;
    	
    	this.facturas.estudioId = id_estudio;
    	this.facturas.fetch({
    		success: function(){
    			console.log("el fetch se realizón con suceso!!!!");
    	    	var facturaListView = new com.logicalnode.tarsys.views.FacturaListView({collection:self.facturas})
    	    	facturaListView.estudio = estudio;
    	        self.changePage(facturaListView);
    		}
    	});

		console.log(this.facturas);
    	
    },
    
    facturaNewPage: function(id_estudio){
    	console.log("#facturas/nuevo");
    	this.facturas.estudioId = id_estudio;
    	var factura = new com.logicalnode.tarsys.models.Factura();
    	
//    	this.consumos.estudioId = id_estudio;
//    	this.consumos.facturaId = "nuevo";
//    	this.consumos.reset();
//    	this.consumos = new com.logicalnode.tarsys.collections.Consumos();
//    	this.consumos.fetch();

    	var consumos = new com.logicalnode.tarsys.collections.Consumos();
    	consumos.estudioId = id_estudio;
    	consumos.fetch();
    	this.changePage(new com.logicalnode.tarsys.views.FacturaEditView({model:factura, collection:consumos}));
    },

    
    facturaEditPage: function(id_estudio, id_factura){
    	console.log("#estudios/" + id_estudio + "/facturas/" + id_factura);
    	
    	var estudio = this.estudios.get(id_estudio);
    	this.facturas.estudioId = id_estudio;

    	var factura = this.facturas.get(id_factura); //_.find(this.facturas.models, function(model){

    	var consumos = new com.logicalnode.tarsys.collections.Consumos();
    	consumos.estudioId = id_estudio;
    	consumos.facturaId = id_factura;
    	consumos.fetch();

    	this.changePage(new com.logicalnode.tarsys.views.FacturaEditView({model:factura, collection:consumos}));
    },

    page1:function () {
        console.log('#page1');
        this.changePage(new com.logicalnode.tarsys.views.Page1View());
    },

    page2:function () {
        console.log('#page2');
        this.changePage(new com.logicalnode.tarsys.views.Page2View());
    },

    changePage:function (page) {
        $(page.el).attr('data-role', 'page');
        page.render();
        $('body').append($(page.el));
        var transition = $.mobile.defaultPageTransition;
        // We don't want to slide the first page
        if (this.firstPage) {
            transition = 'none';
            this.firstPage = false;
        }
        $.mobile.changePage($(page.el), {changeHash:false, transition: transition});
    }

});