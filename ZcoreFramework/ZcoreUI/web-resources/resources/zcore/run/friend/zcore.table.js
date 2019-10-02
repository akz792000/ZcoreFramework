/**
 * 
 * @author Ali Karimizandi
 * @since 2012 
 *   
 */

(function($, _) {
			
	_.clazz({
		
		name : 'table',
		
		body : function(o) {	
			
			/*
			 * ----------------------------------------------------------------------
			 * 								 properties
			 * ---------------------------------------------------------------------- 
			 */	
			
			this.properties({
			
				/*
				 * new
				 */
				
				col : {
					type : 'int',
					defVal : 0,
					get : function() {	
						//--> NOP
					},
					set : function(v) {	
						//--> NOP
					}
				}
			
			});			
						
			/*
			 * ----------------------------------------------------------------------
			 * 								 methods
			 * ---------------------------------------------------------------------- 
			 */	
			
			this.methods({	
				
				/*
				 * new
				 */
				
				embed : {
					method : function(v) {
						var T = this.__;
						//--> table layout
						if (T.params.col >= 1) {
							//--> add row
							var 
								insert = function(k, o, c) {
									switch (k) {
									case "TABLE" : return $('<table>', { 
										"class" : "zui-panel-table"	
									}).appendTo(o);								
									case "ROW" : return $('<tr>', {
										"class" : "zui-panel-tr"
									}).appendTo(o);
									case "COL" : return $('<td>', {											
											"class" : "zui-panel-td" + (v.cssClass && v.cssClass.indexOf('zui-fixed') !== -1 ? ' zui-fixed' : ''),
											colspan : c, 
											width : (100 / T.params.col * c) + '%'
										}).appendTo(o);
									}
								},
								row = T.canister.find('tr').last(),
								colspan = v.colspan || 1;
							if (row.length === 0) {
								return insert("COL", 
											insert("ROW", 
													insert("TABLE", T.canister)), colspan);	
							}
							//--> add column
							var cnt = 0;
							row.find('td').each(function() {
								cnt += parseInt($(this).attr('colspan'));
							});						
							return (cnt < T.params.col) 
								? insert("COL", row, colspan) 
								: insert("COL", insert("ROW", T.canister.find('tbody')), colspan);								
						} 
						//--> default layout
						else {
							return T.canister;
						}
					}
				}				
						
			});
			
		}
						
	});
	
})(jQuery, jQuery.ZOF);