/**
 * 
 * @author Ali Karimizandi
 * @since 2012 
 *   
 */

(function($, _) {
	
	_.clazz({
		
		name : 'bpmn',
		
		inherit : 'component',
		
		body : function(o) {	
			
			/*
			 * ----------------------------------------------------------------------
			 * 								 resources
			 * ---------------------------------------------------------------------- 
			 */			
			
			this.resource = {					
					root : '/zcore/util/bpmn-js/',
					files : ['bpmn-viewer-custom.js']
			};				
		
			/*
			 * ----------------------------------------------------------------------
			 * 								 initialize
			 * ---------------------------------------------------------------------- 
			 */
			
			this.tag = '<div>';
						
			/*
			 * ----------------------------------------------------------------------
			 * 								 properties
			 * ---------------------------------------------------------------------- 
			 */
			
			this.properties({
				
				/*
				 * override
				 */
				
				__null__ : ['direction', 'value']
				
			});	
			
			/*
			 * ----------------------------------------------------------------------
			 * 								 methods
			 * ---------------------------------------------------------------------- 
			 */	
			
			this.methods({
				
				/*
				 * override
				 */			
			
				initialize : { 	
					advice : 'after',
					method : function() {
						var T = this.__;
						T.o.addClass("ui-widget-content ui-corner-all");
						$.extend(T.temp, {	
							renderer : new window.BpmnJS({ 
								container : T.o,
								height : '100%',
								width : '100%'
							}),
							//--> zoomReset
							zoomReset : $('<button>', {
								"class" : 'zui-bpmn-btn zui-bpmn-btn-reset',
								tabindex : -1,
							}).appendTo(T.o),
							//--> zoomIn
							zoomIn : $('<button>', {
								"class" : 'zui-bpmn-btn zui-bpmn-btn-in',
								tabindex : -1,
							}).appendTo(T.o),
							//--> zoomOut
							zoomOut : $('<button>', {
								"class" : 'zui-bpmn-btn zui-bpmn-btn-out',
								tabindex : -1,
							}).appendTo(T.o)							
						});
					}			
				},
				
				render : { 		
					method : function() {
						var T = this.__;
						//--> zoomReset
						T.temp.zoomReset
								.button({
									icons: {
										primary: "ui-icon-arrow-4-diag",
										text : undefined
									}
								})
								.click(function() {
									if (T.temp.renderer.diagram) {
										T.temp.renderer.diagram.get('zoomScroll').reset();
									}
								})
								.find('.ui-button-text').remove();							
						//--> zoomIn 
						T.temp.zoomIn 
								.button({
									icons: {
										primary: "ui-icon-plusthick",
										text : undefined
									}
								})
								.click(function() {
									if (T.temp.renderer.diagram) {
										T.temp.renderer.diagram.get('zoomScroll').stepZoom(1);
									}
								})
								.find('.ui-button-text').remove();	
						//--> zoomOut 
						T.temp.zoomOut
								.button({
									icons: {
										primary: "ui-icon-minusthick",
										text : undefined
									}
								})
								.click(function() {
									if (T.temp.renderer.diagram) {
										T.temp.renderer.diagram.get('zoomScroll').stepZoom(-1);
									}
								})
								.find('.ui-button-text').remove();						
					}
				},
					
				/*
				 * new
				 */
				
				importXML : {
					method : function(responseText, taskDefinitionKey) {
						var	T = this.__;		
						T.temp.renderer.importXML(responseText, function(err) {
							if (!err) {
								setTimeout(function() {
									// get canvas
									var canvas = T.temp.renderer.get('canvas');
									
									// zoom to fit full viewport
									canvas.zoom('fit-viewport', 'auto');
									
									// add marker
									if (taskDefinitionKey) {
										canvas.addMarker(taskDefinitionKey, 'highlight');
									}
								}, 0);
							} else {
								throw err;  
							}
						});
					}
				}			
						
			});		
			
		}
									
	});
	
})(jQuery, jQuery.ZOF);