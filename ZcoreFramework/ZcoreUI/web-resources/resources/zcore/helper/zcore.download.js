/**
 * 
 * @author Ali Karimizandi
 * @since 2012 
 *   
 */

(function($, _) {
		
	_.clazz({
		
		name : 'download',
		
		body : function() {		
		
			$.extend(this, {
							
				_init : function() {
					this._o = $('<div>')
						.appendTo('body')
						.dialog({
							dialogClass : 'zui-global',
							autoOpen : false, 
							modal : true, 
							resizable : true, 
							draggable : true,	
							closeOnEscape : false,
							width : '800',
							height : '600'
						});
				},
				
				_deleteCookie : function (key) {
				    document.cookie = key + "=; " + "Expires=Thu, 01 Jan 1970 00:00:01 GMT; Path=/";
				},

				_getCookie : function(key) {
				    var 
				    	name = key + "=",
				    	ca = document.cookie.split(';');
				    for (var i = 0; i < ca.length; i++) {
				        var c = ca[i];
				        while (c.charAt(0) == ' ') {
				        	c = c.substring(1);
				        }
				        if (c.indexOf(name) == 0) {
				        	return c.substring(name.length, c.length);
				        }
				    }
				    return "";
				},	
								
				_end : function(params) {
					this._deleteCookie("Download");
					clearInterval(this._checkDownload);
					//--> complete
					if (params.complete) {
						params.complete();
					}	
					$.ZAP.loading.hide();	
				},
				
				_checkDownload : undefined,
				
				_show : function() {
					//--> titlebar
					var titlebar = this._o.dialog('widget').find('.ui-dialog-titlebar');
					titlebar.find('.ui-dialog-titlebar-close').attr({
						'tabindex' : '-1',
						'title' : $.ZAP.message.msg('close')
					});
					
					// open
					this._o.dialog('open');	
				},
				
				ajax : function(params) {
					$.ZAP.loading.show();
					this._o.empty();
					//--> beforeSend
					if (params.beforeSend) {
						params.beforeSend();
					}	
					//--> create iframe & form & elements
					var	
						that = this,
						//--> create iframe
						iframe = $('<iframe>', {
							id : 'frame',
							name : 'frame',
							src : 'about:blank',
							style : 'top:0;left:0;width:100%;height:100%;'
						}).appendTo(this._o),
						//--> create form
						form = $('<form>', {							
							action : params.url,
							target : 'frame',
							method : 'POST',
							/*
							 * does not get utf-8 charset field correctly in server side so we add following line
							 * 
							 * as I have see event when you set enctype with accept-charset the browser doesn't send
							 * encoding therefore I add CharacterEncodingFilter to filters ( see the source code of
							 * CharacterEncodingFilter for further information)
							 */
							enctype : "application/x-www-form-urlencoded",
							'accept-charset' : "UTF-8"
						}).appendTo(iframe);								
					//--> create elements
					for (var key in params.values) {
						$("<input>",	{
							name : key,
							value : typeof params.values[key] === "object" ? JSON.stringify(params.values[key]) : params.values[key]
						}).appendTo(form);
					}
					//--> if has file
					if (params.hasFile) {
                        for (var key in params.hasFile) {
                            form.append($('#' + key).find(":file").attr('name', key));
                        }
                        form.attr('enctype','multipart/form-data');
                    }
					/*
					 * it doesn't possible to add csrf as request's headers 
					 * therefore we send csrf as request's parameters
					 */
					$("<input>",	{
						name : '_csrf',
						value : $.ZAP.csrf
					}).appendTo(form);
					//--> call when receive error from server
					iframe.load(function() {
						var 
							v = $(this).contents().find('pre').html(),
							status = 597;
						try {
							status = $.parseJSON(v)[0];
						} catch (e) {};
						$.ZAP._errorHandler(params,	{
							status : status,
							responseText: v
						});
						that._end(params);
					})					
					//--> clear cookie
					this._deleteCookie("Download");
					//--> call when file receive from server completely
					this._checkDownload = window.setInterval(function() {
						var type = that._getCookie("Download");
	                    if (type != "") {
	                    	if (type == 'pdf') {
	                    		that._show();	                    		
	                    	}
	                    	//--> success
							if (params.success) {
								params.success();
							}                    	
							that._end(params);
			            }
					}, 50);					
					//--> submit
					form.submit();	
				}						
							
			});
			
			this._init();
			
		}
		
	});
	
})(jQuery, jQuery.ZOF);