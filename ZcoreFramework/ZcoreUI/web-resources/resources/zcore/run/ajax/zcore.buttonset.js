/**
 * 
 * @author Ali Karimizandi
 * @since 2012 
 *   
 */

(function($, _) {
			
	_.clazz({
		
		name : 'buttonset',
		
		inherit : 'postable',
		
		friend : 'ajax',
		
		body : function(o) {		
				
			/*
			 * ----------------------------------------------------------------------
			 * 								 properties
			 * ---------------------------------------------------------------------- 
			 */		
			
			this.properties({
				
				/*
				 * override
				 */	
				
				__null__ : ['readonly'],
				
				disabled : {
					after : function(v) {
						this.__.o.find('input').button("option", "disabled", v);
					}
				},						
				
				tabindex : {
					get : function() {
						this.__.o.children('input:first').attr(this.name);
					},
					set : function(v) {
						this.__.o.children('input').attr(this.name, v);
					}
				},				
				
				value : {					
					type : 'array',
					get : function() {
						var 
							T = this.__,
							res = [];
						T.o.find("input:checked").each(function() {
							res.push($(this).val());
						});
						return res;
					},
					set : function(v) {
						v = v || [];
						this.__.o.find('input').each(function() {
							for (var i = 0; i < v.length; i++) {
								$(this).prop('checked', $(this).val() == v[i]);
							}
						});
					},
					after : function() {
						this.__.o.buttonset('refresh');					
					}
				},
				
				data : {
					refresh : null,
					type : 'map',
					get : function() {
						//--> must be implemented;
					},
					set : function(v) {
						//--> must be implemented;
					}				
				},				
				
				width : {
					after : function() {
						var 
							T = this.__,
							labels = T.o.find('label'),
							border = {
								left : parseInt($(labels[0]).css('border-left-width')),
								right : parseInt($(labels[0]).css('border-right-width'))
							};
						labels.each(function(index) {
							$(this).css(
									'width', 
									'calc(:w% - :bpx)'
										.replace(':w', 100 / labels.length)
										.replace(':b', border.left + border.right)
							);
						});
					}
				},					
									
				/*
				 * new
				 */
				
				kind : _.defaultProperty('enum', 0, ['radio', 'checkbox'])
				
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
						this.__.o.removeAttr('name');
					}					
				},			
				
				render : {
					method : function() {
						var T = this.__,
							P = T.params,
							query = '',
							uniqueId = P.id + new Date().getTime();
						for (var i in P.data) {
							query += 
									"<input type=':kind' name=':name' id=':id' value=':value' tabindex=':tabindex' /><label for=':id'>:message</label>"
										.replace(':kind', P.kind)
										.replace(':name', uniqueId)
										.replace(/:id/g, uniqueId + i)								
										.replace(':value', P.data[i])
										.replace(':tabindex', P.tabindex) 
										.replace(':message', $.ZAP.message.translate('${' + i + '}'));
						}
						T.o.append(query).buttonset();
						if (P.direction === 'rtl') {
							T.o.children('label[class*=" ui-corner-"]').each(function() {
								var 
									rem = 'left', 
									add = 'right';
								if ($(this).hasClass('ui-corner-right')) {
									rem = 'right';
									add = 'left';
								}
								$(this).removeClass('ui-corner-' + rem).addClass('ui-corner-' + add);	
							});	
						}
					
					}
				},	
				
				finalize : {
					method : function() {
						var T = this.__;
						//--> set event
						T.o.find('input').each(function() {
							$(this)
								.bind('click.zcore', function() {
									if (!$(this).is(':disabled')) {
										T.validateClear();
										T.signal('click', arguments); 
									}
								})
								.bind('focus', function() {
									$(this).next().addClass('zui-state-hover');
								})
								.bind('blur', function() {
									$(this).next().removeClass('zui-state-hover');
								});		
						});	
					}
				},
				
				validateObject : {
					method : function() {
						return this.__.o.find('label');                  	
					}
				},
							
				/*
				 * new
				 */
				
				click : {}		
			
			});	
			
		}
						
	});	
	
})(jQuery, jQuery.ZOF);