$(document).ready(function () {
	
	var list = [
	            "home-page", 
                "estudio-home-page", 
                "estudio-main-page",
                "estudio-list-page", 
                "estudio-edit-page",
                "estudio-list-item",
                "factura-list-page",
                "factura-list-item",
                "factura-edit-page",
                "consumo-item",
                "estudio-optimizer-page"
                ];
	
    tpl.loadTemplates(list, function () {
		    console.log('document ready');
		    com.logicalnode.tarsys.app = new com.logicalnode.tarsys.routers.AppRouter();
		    Backbone.history.start();
    });
	
});
