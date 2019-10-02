/**
 * 
 * @author Ali Karimizandi
 * @since 2012 
 *   
 */

//--> document ready
$(function() {
	
	function ZcoreAppPlugin() {}
	
	//--> register as global
	$.ZAP = new ZcoreAppPlugin();
	
	(function(_) {
	
		$.extend(ZcoreAppPlugin.prototype, {
			
			container : {
				header : undefined,
				body : $('body'),
				style : $('<div>').appendTo($('head'))
			},
			
			tabindex : 1,
			
			version : "",
			
			translate : false,
			
			xmlKey : {
				root : 'zcore',
				view : 'view',
				script : 'script',
				style: 'style'
			},
			
			locale : $.ZCK.cookieObject({
				def : 'fa',
				direction : function() {
					switch (this.get()) {
					case 'fa': return 'rtl';
					default: return 'ltr';
					}
				},
				after : function(v) {
					$.ZAP.clear();
					$('body').attr('dir', this.direction());						
				}
			}),
			
			ui : {
				cache : true
			},
			
			_orientationGroup : {
				ltr : ['ltr', 'left', 'east'],
				rtl : ['rtl', 'right', 'west'],
			},
			
			orientation : function(v, reverse) {
				v = v || this.locale.direction();
				if (reverse) {
					v = v.split('').reverse().join('');
				}
				return this._orientationGroup[v];
			},
			
			config : undefined,
			
			resourceUrl : 'resources',
			
			_resources : {},
			
			_global : {},
			
			views : {},
			
			templates : {},
			
			csrf : undefined,
				
			isNumber : function(v) {
				return !isNaN(parseInt(v, 10));
			},
			
			toString : function(v) {
				var res = "";
				for (var i in v) {
					res += (res == "") ? v[i].toString().trim() : "," + v[i].toString().trim();
				}
				return res;
			},
			
			_setPrototype : function () {
				/*
				 * String
				 */
				
				//--> lpad
				String.prototype.lpad = function(padString, length) {
					var str = this;
				    while (str.length < length) {
				        str = padString + str;
				    }
				    return str;
				};		
				//--> trim
				String.prototype.trim = function() {
					return this.replace(/^\s+|\s+$/g,"");
				};
				//--> ltrim
				String.prototype.ltrim = function() {
					return this.replace(/^\s+/,"");
				};			
				//--> rtrim
				String.prototype.rtrim = function() {
					return this.replace(/\s+$/,"");
				};		
				//--> reverse
				String.prototype.reverse = function() {
					for (var i = this.length - 1, o = ''; i >= 0; o += this[i--]) { }
					return o;
				};
				//--> upperFirstLetter
				String.prototype.upperFirstLetter = function() {
					return this.charAt(0).toUpperCase() + this.slice(1);
				};
				//--> toNumber
				String.prototype.toNumber = function() {
					return parseInt(this, 10) || 0;
				};
				
			},				
						
			translateDate : function(y, m, d) {
				var
					dateType = function(locale) {
						switch (locale) {
						case 'fa': return 'persian';
						default: return '';
						}
					},				
					jd = $.calendars.instance('').newDate(y, m, d).toJD(),					
				  	date = $.calendars.instance(dateType(this.locale), this.locale).fromJD(jd),
					res =
					    date._calendar.local.dayNames[date.dayOfWeek()] + ', ' +
						date.day() + ' ' + date._calendar.local.monthNames[date.month() - 1] + ' ' +
					    this.message.msg('month') + ' ' + date.formatYear();
				return res;
			},
			
			_ajaxSetup : {
				global : true,
				cache : true, //--> prevent a query string parameter, "_=[TIMESTAMP]", to the URL
				beforeSend : function(xhr) {
					if (_.csrf != undefined) {
						xhr.setRequestHeader("X-CSRF-TOKEN", _.csrf);
					}
				},	
				error : function(jqXHR, textStatus, errorThrown) {
					_._errorHandler(null, jqXHR, textStatus, errorThrown);
				}
			},
			
			clear : function() {
				this._resources = {};
				this.templates = {};	
			},
			
			_init : function() {
				//--> ajax Setup
				$.ajaxSetup(_._ajaxSetup);		
				
				//--> jQuery configuration
				$.ajaxPrefilter("script", function(options, originalOptions, jqXHR) {
					options.cache = true; //--> prevent a query string parameter, "_=[TIMESTAMP]", to the URL
					//--> prepare pure url
					if (_._resources[options.url]) {
						jqXHR.abort();
					} else {
						_._resources[options.url] = true;
					}
				});
				
				//--> manipulate jQuery dialog 
				$.ui.dialog.prototype._focusTabbable = function() {
					return;
				};
							
				//--> clear resources
				this.clear();
				
				//--> set prototype
				this._setPrototype();
				
				//--> set zcore event-ret
				window.removeEventListener("zcore-event-ret", this.eventRetFunc);
				window.addEventListener("zcore-event-ret", this.eventRetFunc, false);				
					
			},
			
			eventRetFunc : function(event) {
				//--> we do not have any desire function return value
			},
			
			dispatchEvent : function(params) {
				var event = document.createEvent('CustomEvent');
				event.initCustomEvent("zcore-event", true, true, params);
				document.documentElement.dispatchEvent(event);					
			},			
			
			initial : function(params) {
				
				//--> change default value of jquery ui components
				$.extend($.ui.tooltip.prototype.options, { 							
					position: {
						my: "right bottom-2",
						at: "right top",
						collision: "flipfit flip"
					} 
				});
				
				//--> helper configuration
				(function(v) {
					for (var item = 0; item < v.length; item++) {						
						_[v[item]] = $.ZOF.getInstance(v[item]);
					}
				})(['loading', 'message', 'bar', 'box', 'hint', 'dialog', 'download']);
				
				//--> load
				$.ZOF.extend(this, params);
				
				
				// locale
				this.locale.set();

				return this;
			},
			
			_errorHandler : function(params, jqXHR, textStatus, errorThrown) {
				params = params || {};
				switch (jqXHR.status) {	
					//--> 0
					case 0:
						this.box.show(this.message.msg('error'), this.message.msg('timeout'), 'MB_OK', function() {
							window.location.replace('');							
						});
						break;
					//--> Unauthorized	
					case 401:
					case 451:	
						try {
							var msgArgs = $.parseJSON(jqXHR.responseText);
							this.bar.show('error', this.message.msg(msgArgs.msg, msgArgs.args));
						} catch (e) {
							this.bar.show('error', this.message.msg(jqXHR.responseText));
						}
						if (params.error) {
							params.error(jqXHR, textStatus, errorThrown);
						}
						break;
					case 404:
						this.box.show(this.message.msg('error'), this.message.msg('requestNotHandle'));						
						break;
					//--> Forbidden
					case 403: 
						this.bar.show('error', this.message.msg('forbidden'));
						break;					
					//--> Authentication Timeout (session validate filter logout)	
					case 419: 
						var func = this.config.login;
						if ($.isFunction(func)) {
							func();
						}						
						break;		
					//--> Security Credential	
					case 420:
						var func = this.config.security;
						if ($.isFunction(func)) {
							func(jqXHR.responseText);
						}
						break;							
					//--> Internal Server Error
					case 500: 
						this.box.show(this.message.msg('error'), jqXHR.responseText);
						break;
					//--> Bad Gateway
					case 502: 
						this.box.show(this.message.msg('error'), this.message.msg('badGateway'));
						break;
					//--> Service Unavailable
					case 503: 
						this.box.show(this.message.msg('error'), this.message.msg('serviceUnavailable'));
						break;												
					//--> Validate
					case 590: 
			  			if (params.validate) {
			  				params.validate($.parseJSON(jqXHR.responseText)[1], textStatus, jqXHR);
			  			}
			  			break;	
			  		//--> Exception	
					case 591:
                    case 593:
						var
                            value = $.parseJSON(jqXHR.responseText)[1],
                            cmp = {
                                obj: this.bar,
                                title: 'error'
                            };
                        if (jqXHR.status == 593) {
                            cmp = {
                                obj: this.box,
                                title: this.message.msg('error')
                            }
                        }
						if ($.isArray(value)) {
                            cmp.obj.show(cmp.title, this.message.msg(value[0], value[1]));
						} else {
                            cmp.obj.show(cmp.title, this.message.msg(value));
						}
			  			if (params.exception) {
			  				params.exception(value, textStatus, jqXHR);
			  			}
			  			break;
					//--> Error not handle message box
					case 597:
						var msg = jqXHR.responseText &&  jqXHR.responseText !== undefined ?  jqXHR.responseText : this.message.msg('SERVICE_IS_DOWN');
						this.box.show(this.message.msg('error'), msg, undefined, undefined, undefined, 'ltr');
						break;				  			
					//--> Error not handle message bar
					case 598:
					    var requestLogKey = jqXHR.getResponseHeader("REQUEST_LOG_KEY");
						this.bar.show('error', this.message.msg('notHandle') + (requestLogKey != '' ? ' [' + requestLogKey + ']' : ''));
						break;					
					//--> Session Timeout	
					case 599: 
						this.box.show(this.message.msg('error'), this.message.msg('timeout'), 'MB_OK', function() {
							window.location.replace('');							
						});
						break;	
					//--> Others	
					default:
						if (params.error) {
							params.error(jqXHR, textStatus, errorThrown);
						} else {
							this.box.show(this.message.msg('error'), "Unhandled response status - " + jqXHR.status + ' - ' + jqXHR.responseText);
						}
						break;
				}	
			},	
									
			ajax : function(params) {			
				$.ajax({
					  async : params.async != undefined ? params.async : true,
					  type : params.type,
					  url : params.url,
					  dataType : params.dataType, //--> default: Intelligent Guess (xml, json, script, or html)
					  data : params.data,
					  cache : params.cache,
					  contentType : params.contentType === undefined ? "application/x-www-form-urlencoded; charset=UTF-8" : params.contentType, 					  
					  processData: params.processData,
					  beforeSend : function(xhr) { 
						  _.loading.show(params.loading);					  
						  if (params.barHide == undefined || params.barHide) {
							  _.bar.hide();
						  }
						  _._ajaxSetup.beforeSend(xhr);						  					  
						  if (params.beforeSend) {
							  params.beforeSend(xhr);
						  }
					  },				  
					  error : function(jqXHR, textStatus, errorThrown) {
						  var errorHandle = true;
						  if (params.beforeError) {
							  errorHandle = params.beforeError(jqXHR, textStatus, errorThrown);
							  if (errorHandle == undefined) {
								  errorHandle = true;
							  }
						  }
						  if (errorHandle) {
							  _._errorHandler(params, jqXHR, textStatus, errorThrown);
						  }
					  },
					  success : function(data, textStatus, jqXHR) {					  
							switch (jqXHR.status) {
						  	//--> OK
							/*
							 * OK
							 * jqXHR.status = 200 
							 * must have response body to prevent from jQuery handle in success routine
					  		 */	
						  	case 200:
						  	case 204: //--> NO CONTENT
						  		/*
						  		 * because when xml response they don't have csrf value  
						  		 */
						  		var csrf = jqXHR.getResponseHeader("X-CSRF-TOKEN");
						  		if (csrf != undefined) {
						  			_.csrf = csrf != 'X-CSRF-REMOVE' ? csrf : undefined; 
						  		}
						  		switch (params.dataType) {
							  		case 'json':
							  		case 'xml':
							  		case 'html':	
							  			if (params.success) {
							  				params.success(data, textStatus, jqXHR);
							  			}
							  			break;
							  		default:
							  			_.box.show(_.message.msg('error'), "Unhandled response status - " + jqXHR.status + ' - ' + jqXHR.responseText);  
							  			return;							  			
						  		}
						  		break;						  	
							default:
								_.box.show(_.message.msg('error'), "Unhandled response status - " + jqXHR.status + ' - ' + jqXHR.responseText);
								return;
							};	
					  }				  
				})
				/*
				 * Deprecation Notice: The jqXHR.success(), jqXHR.error(), and jqXHR.complete() callbacks are 
				 * deprecated as of jQuery 1.8. To prepare your code for their eventual removal, 
				 * use jqXHR.done(), jqXHR.fail(), and jqXHR.always() instead.
				 */	
					  .always(function(jqXHR, textStatus) {
						  if (params.complete) {
							  params.complete(jqXHR, textStatus);
						  }  
						  _.loading.hide(params.loading);
					  });			
			},
			
			loadResource : function(resource) {	
				if (resource) {
					for (var i = 0; i < resource.files.length; i++) {
						var file = resource.files[i],
							url = resource.root + file;
						if (!this._resources[url]) {
							var arr = file.split("."),
								version = this.version;
							switch (arr[arr.length - 1]) {
							case 'js':
								$('<script>', {
									type : 'text/javascript', 
									src : this.resourceUrl + url + '?' + version
								}).appendTo('head');
								break;
							case 'css':
								var link = document.createElement('link');
								link.type = "text/css";
								link.rel = "stylesheet"
								link.href = this.resourceUrl + url + '?' + version;
								link.onload = function() {
									_.loading.hide();
								}
								_.loading.show();
								// if you want have sleep but below line in setTimeout
								document.head.appendChild(link);
							}
							this._resources[url] = true;
						}
					}			
				}
			},	
			
			removeView : function(view) {
				if (view === undefined) {
					//--> sort
					var views = [];
					$.each(_.views, function(k, v) {
						views.push(v);
					});
					views.sort(function(a, b) {
						return b.generation - a.generation;
					});
					//--> remove views
					$.each(views, function(k, v) {
						v.remove();
					});					
				} else if (typeof view === "string") {
					if (_.views[view]) {
						_.views[view].remove();
					}
				} else if (view instanceof $.ZOF.view) {
					view.remove();
				};
			},
			
			_getTemplate : function(data, params) {
				var 
					xml = $(data), 
					key = this.xmlKey,		
					/*
					 *  get items
					 */
					getItems = function(v) {
						var items = [];
						//--> get item
						$(v).children('item').each(function(key, value) {
							var item = {};
							//--> set attributes
							for (var i = 0; i < value.attributes.length; i++) {
								var val = value.attributes[i].nodeValue;
								item[value.attributes[i].nodeName] = _.translate ? _.message.translate(val) : val;								
							};
							items.push(item);
						});
						return items;
					},
					/*
					 * get model
					 */ 
					getModel = function(v) {
						var res = {};
						res.attributes = { 
							type : v.tagName 
						};							
						//--> set attributes
						for (var i = 0; i < v.attributes.length; i++) {
							var val = v.attributes[i].nodeValue;
							res.attributes[v.attributes[i].nodeName] = _.translate ? _.message.translate(val) : val;								
						};
						//--> set components
						var children = $(v).children();						
						if (children.length > 0) {
							res.components = res.components || [];
							children.each(function(key, value) {
								if ($(this).is("items")) {
									//--> items attribute
									res.attributes["items"] = getItems(value);
								} else if ($(this).is("validators")) {
									//--> items attribute
									res.attributes["validators"] = getItems(value);									
								} else {
									//--> components
									res.components.push(getModel(value));
								}
							});
						}	
						return res;
					};
				//--> get childs
				var childs = [];
				$(xml).find(key.view).children().each(function() {
					childs.push(getModel($(this)[0]));
				});
				//--> return result
				return {
					model : childs,
					script : xml.find(key.script).text(), //--> javascript code
					style : xml.find(key.style).text(), //--> style code
					ui : params.ui, //--> model of ui
					name : params.name, //--> name of view
					service : params.service //--> model of service,					
				};
			},		
			
			_renderView : function(view, params, data) {
				//--> add style
				var style = view.template.style.trim();
				if (style.length) {
					view.style = $('<style>', {
						type : 'text/css',
						id : view.name,
						html : style 
					}).appendTo(_.container.style);
				}
				//--> initialize component by hierarchy
				for (var name in view.elements) {
					$.ZOF.initializeComponent(view.elements[name], data);
				}					
				//--> prepare and execute script
				var	command = "var app = $.ZAP, view = app.views." + view.name;					
				for (var name in view.components) {
					command += ', ' + name + ' = view.components.' + name;
				}				
				command += ";";
				//--> execute command
				(new Function(
						//--> command
						command +
						//--> script
						view.template.script
				))();
				//--> execute initialize of view
				view.initialize(data);
				//--> render component by hierarchy				
				for (var name in view.elements) {
					$.ZOF.renderComponent(view.elements[name]);
				}				
				//--> set ui param
				try {
					for (var name in params.ui.param) {
						for (var property in params.ui.param[name]) {
							var value = params.ui.param[name][property];
							if (value !== undefined) {
								view.components[name].invoke('property', property, 'execute', [params.ui.param[name][property]]);
							}
						}
					}
				} catch (e) {
					console.error(name);
					throw e;
				}
				//--> execute finalize of view
				view.finalize(data);				
			},			
						
			_showView : function(view, params, data, textStatus, jqXHR) { 		
				//--> register view
				_.views[view.name] = view;
				//--> render view
				_._renderView(view, params, data);
				//--> show
				if (view.display == 'dialog') {	
					_.dialog.finalize(view.container, params.dialog);
				} 
				//--> success callback				
				if (params.success) {
					params.success(data, textStatus, jqXHR);
				}
				return _.views[view.name];
			},
			
			_createView : function(template, params) {
				var 
					guid = {
						id : new Date().getMilliseconds(), //--> use when one component doesn't have id	
						tabindex : _.tabindex
					};	
				if (params.beforeCreate) {
					params.beforeCreate();
				}
				//--> create view
				var view = $.ZOF.getInstance('view');
				view.generation = new Date().getTime();
				view.name = template.name;									
				view.template = template;
				//--> set parent and child
				view.parent = params.parent;
				if (params.dialog) {					
					view.container = _.dialog.show(view.name, params.dialog);
					view.display = 'dialog';
				} else {
					view.container = params.container || $.ZAP.container.body;
					view.display = 'default';
					var activeView = _.views[view.container.attr('active-view')];
					if (activeView) {						
						activeView.remove();
					}
					view.container.attr('active-view', view.name);
				}
				//--> set parent's child   
				if (view.parent instanceof $.ZOF.view) {
					view.parent.childs[view.name] = view;
				}
				//--> create sub component 
				for (var i = 0; i < view.template.model.length; i++) {					
					$.ZOF.createComponent(
							view.template.model[i], //--> model
							view, //--> parent
							view, //--> owner
							guid //--> global unique identifier
					);				
				}	
				//--> add client model components as serivce action
				if (params.service) {
					var clientModel = view.clientModel();
					if (!$.isEmptyObject(clientModel)) {
						params.service.actionParam = {
							'clientModel' : clientModel
						};	
					}					
				}
				//--> set global tabindex
				_.tabindex = guid.tabindex;	
				var 
					exhibition = true,
					arg = {};
				//--> show view
				if (params.service) {
					this.ajax({
						async : false,
						type : "POST",
						url : params.service.url,
						dataType : 'json', 
						data : this.stringify(params.service.data()),
						beforeSend : function(xhr) {
							//--> set exhibition
							exhibition = false;
						},
						beforeError : function() {
							view.close();
						},
						validate : function(data, textStatus, jqXHR) {
							if (params.validate) {
								params.validate(data, textStatus, jqXHR);
							}
						},									
						success : function(data, textStatus, jqXHR) {
							//--> set arg
							arg.data = data;
							arg.textStatus = textStatus;
							arg.jqXHR = jqXHR;
							//--> set exhibition
							exhibition = true;
						},
						error : function() {
							if (params.error) {
								params.error();
							}
						}
					});
				}
				//--> show view
				if (exhibition) {	
					return _._showView(view, params, arg.data, arg.textStatus, arg.jqXHR);
				}
			},			
			
			loadView : function(params) {
				try { 
					 _.loading.show(params.loading);
					//--> prepare tabindex
					if (params.tabindex) {
						_.tabindex = params.tabindex;
					}
					//--> prepare ui
					params.ui = this.provide('ui', params.ui);
					//--> initialize service
					if (params.service) {
						params.service = this.provide('service', params.service);
					}
					//--> prepare name
					params.name = this._prepareName(params.ui.url);
					var creation = true;
					//--> check for resource existance
					if (!this._resources[params.name]) {
						//--> load ui
						this.ajax({
							async : false,
							loading : params.loading,
							url : params.ui.url + '.xml' + '?'+ _.version,
							dataType : 'xml',
							beforeSend : function(xhr) {
								//--> set creation
								creation = false;
							},
							success : function(data, textStatus, jqXHR) {
								if (params.ui.cache) {
									_._resources[params.name] = true;
								}
								//--> get view model
								_.templates[params.name] = _._getTemplate(data, params);
								//--> set creation
								creation = true;													
							}
						});					
					}
					//--> create view
					if (creation) {
						return this._createView(_.templates[params.name], params);
					}
				} finally {
					 _.loading.hide(params.loading);
				}
			},			
			
			stringify : function(v) {
				var res = '';
				for (var k in v) {
					var val = v[k];
					if (val instanceof Object) {
						if ($.isEmptyObject(val)) { 
							val = undefined;
						} else {
							val = JSON.stringify(val);
						}
					}
					if (val != undefined) {
						res += (res == '' ? '' : '&') +	k + '=' + val;
					}
				}
				return res;					
			},
			
			formData : function(v) {
				var res = new FormData();
				for (var k in v) {
					var val = v[k];
					res.append(k, val);
				}
				return res;
			},
						
			provide : function(type, v) {				
				var res = {	url : undefined	};
				switch (type) {
				//--> service
				case "service":
					$.extend(res, {
						url : undefined,
						param : undefined,
						actionParam : undefined,
						extend : undefined,
						data : function(def) {
							var result = ($.isEmptyObject(this.param) ? def : this.param) || {};
							//--> serviceActionParam 
							if (this.actionParam) {
								$.extend(result, {
									serviceActionParam : JSON.stringify(this.actionParam)
								});				
							}
							//--> extend
							if (this.extend) {
								for (var key in this.extend) {
									result[key] = this.extend[key];
								}
							}
							return result;
						}						
					});
					break;
				//--> ui	
				case "ui":
					$.extend(res, {
						cache : true,
						param : undefined		
					}, _.ui);
					//--> add value it doesn't have
					for (var name in v.param) {
						if (!$.isPlainObject(v.param[name])) {
							v.param[name] = { value : v.param[name] };
						}
					}
					break;					
				}	
				//--> finalize
				if (typeof v === "string") {
					res.url = v;
				} else {
					$.extend(res, v);					
				}
				return res;
			},
			
			predispose : function(v, sequential) {
				var 
					service = this.provide('service', v.service),
					file = {};
				//--> initial
				v.url = v.url || service.url;
				v.values = {}; //--> not in jquery ajax parameter
				v.request = v.request || 'ajax'; //--> not in jquery ajax parameter
				v.data = {};
				//--> prepare data and file
				for (var k in sequential) {
					var obj = sequential[k];
					if (obj.type === 'file') {
						file[obj.key] = obj.val;								
					} else {
						v.values[obj.key] = obj.val;
					}
				}
				//--> prepare values & data
				v.values = service.data(v.values);
				v.data = this.stringify(v.values);
                v.hasFile = undefined;
				//--> prepare data & url						
				if (!$.isEmptyObject(file)) {
					if (v.data.trim() !== '') {
						v.url += "?" + v.data;
					}
					v.data = this.formData(file);
                    v.hasFile = file;
					v.contentType = false;
					v.processData = false;
				}				
			},
			
			call : function(v, options) {
				options = options || {};
				var 
					events = $.extend({
						beforeSend : function(xhr) {
							if (v.beforeSend) {
								v.beforeSend(xhr);
							}										
						},		
						beforeError : function(jqXHR, textStatus, errorThrown) {
							if (v.beforeError) {
								return v.beforeError(jqXHR, textStatus, errorThrown);
							}
						},
						error : !v.error ? undefined : function(jqXHR, textStatus, errorThrown) {
							if (v.error) {
								v.error(jqXHR, textStatus, errorThrown);
							}
						},						
						exception : function(data, textStatus, jqXHR) {
							if (v.exception) {
								v.exception(data, textStatus, jqXHR);
							}
						},
						validate : function(data, textStatus, jqXHR) {
							if (v.validate) {
								v.validate(data, textStatus, jqXHR);
							}
						},										
						success : function(data, textStatus, jqXHR) {
							if (v.success) {
								v.success(data, textStatus, jqXHR);
							}
						},
						complete : function(jqXHR, textStatus) {
							if (v.complete) {
								v.complete(jqXHR, textStatus);
							}
						}
					}, options.events);
				//--> prepare 	
				this.predispose(v, options.sequential);
				//--> validate 
				if (options.validity == undefined || options.validity) {
					switch (v.request) {
					case 'ajax':
						this.ajax($.extend({
							async : v.async,
							loading : v.loading,
							type : v.type || "POST",
							url : v.url,
							dataType : v.dataType || 'json', 
							data : v.data, //--> prepare in predispose
							cache : v.cache,
							contentType : v.contentType,							
							processData: v.processData,
						}, events));						
						break;
					case 'download':
						this.download.ajax($.extend({
							url : v.url,
							values :  v.values,
							hasFile : v.hasFile
						}, events));
						break;
					default:
						break;
					}							
				}
			},				
			
			_prepareName : function(name) {
				return name.replace(/\//g, '_');
			},
			
			global : function() {
				var dom = '$.ZAP._global';
				//--> get
				if (arguments.length === 1) {
					try {
						return eval(dom + '.' + arguments[0])
					} catch (e) {
						return undefined;
					}
				} 
				//--> set
				var args = arguments[0].split('.');
				for (var i in args) {
					dom += '.' + args[i]
					//--> initialize if it doesn't have value
					eval(dom + ' = ' + dom + ' || {}');
				}
				new Function(dom + ' = this').call(arguments[1]);
			},		
			
			reverse : function(v) {
				var res = {};
				for (var i in v) {
					res[v[i]] = i;
				}
				return res;
			}
			
		});
		
		_._init();
	
	
	})($.ZAP);
	
});		