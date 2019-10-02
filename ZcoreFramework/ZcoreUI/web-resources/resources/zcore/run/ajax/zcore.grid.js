/**
 * 
 * @author Ali Karimizandi
 * @since 2012 
 *   
 */

(function($, _) {
	
	_.clazz({
		
		name : 'grid',
		
		inherit : 'postable',
		
		friend : 'ajax',
		
		body : function(o) {
			
			/*
			 * ----------------------------------------------------------------------
			 * 								 resources
			 * ---------------------------------------------------------------------- 
			 */			
			
			this.resource = {					
					root : '/zcore/util/grid/',
					files : ['zcore.grid.css', 'zcore.grid.js']
			};			
		
			/*
			 * ----------------------------------------------------------------------
			 * 								 initialize
			 * ---------------------------------------------------------------------- 
			 */	
			
			this._setInvoker = function() {
				return {
					method : function() {
						return this.__.o.ZcoreGrid.apply(this.__.o, $.merge([this.name], arguments)); 				
					}
				};
			};
			
			this.temp.concatData = [];
						
			/*
			 * ----------------------------------------------------------------------
			 * 								 properties
			 * ---------------------------------------------------------------------- 
			 */
			
			this.properties({
				
				/*
				 * override
				 */
				
				readonly : null,
				
				height : {
					set : function() {
						//--> do nothing height use for div-body
					}
				},
				
				width : {
					set : function() {
						//--> do nothing width
					}
				},
											
				value : {
					get : function() {
						var 
							T = this.__,
							res = T.clientable() ? T.clientData() : T.getSelectedRows(true);
						return (res || []).concat(T.temp.concatData);			
					},
					set : function(v) {
						var T = this.__;
						if (v != undefined) {
							if (T.temp.crudable) {
								v = T.toColumnName(v);
								var n = [];
								for (var key in v) {
									var row = v[key];
									if (parseInt(row['status']) >= 0) {
										n.push(row);
									} else {
										T.temp.concatData.push(T.toColumnIndex([row])[0]);
									}
								}
								v = T.toColumnIndex(n);
							}
							this.__.clientData(v, v.length);
						}
					}
				},
				
				data : {
					set : function(v) {
						if (v[2]) {
							this.__.clientData(v[2].data, v[2].totalCount);
						}
					}
				},
				
				/*
				 * new
				 */			
				
				limitPageItems : _.defaultProperty('string'),
				
				multiSelected : _.defaultProperty('boolean'),	
				
				showNumber : _.defaultProperty('string', 'all'),				
				
				items : {
					type : 'array',
					get : function() {
						//--> nop
					},
					set : function(v) {
						//--> nop
					}					
				},
				
				refreshShow : _.defaultProperty('boolean'),
								
				filterShow : _.defaultProperty('boolean'),
				
				sortShow : _.defaultProperty('boolean'),
				
				orderShow : _.defaultProperty('boolean'),
				
				containerHeaderShow : _.defaultProperty('boolean', true),
				
				persistItem : _.defaultProperty(),
				
				mergeItem : _.defaultProperty(),
				
				removeItem : _.defaultProperty(),
				
				viewItem : _.defaultProperty(),
				
				orderByItem : _.defaultProperty(),				
				
				orderByItemSort : _.defaultProperty('string', 0, ['asc', 'dsc']),
				
				refreshOnRender : _.defaultProperty('boolean'),
				
				clientable : _.defaultProperty('boolean'),
				
				filterColumnsValue : _.defaultProperty(),
				
				contextMenu :  _.defaultProperty('boolean'),
				
				headerTitle : _.defaultProperty()				
							
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
				
				clientModel : {
					method : function() {
						var 
							P = this.__.params,
							limitPage = P.limitPageItems !== '' ? P.limitPageItems.split(',')[0] : 10;
						return {
				    		limitPage : P.clientable ? 0 : limitPage,  
				  		  	activePage : P.activePage,
				  		  	orderByItem : (P.orderByItem.length != 0) ? P.orderByItem : undefined,
				  		  	orderByItemSort	: (P.orderByItem.length != 0 && P.orderByItemSort.length != 0) ? P.orderByItemSort : undefined,
				  		  	filterColumnsValue : (P.filterColumnsValue.toString().replace(new RegExp(',', 'g'), '').replace(new RegExp('undefined', 'g'), '').length != 0) ? P.filterColumnsValue.replace(new RegExp('undefined', 'g'), '') : undefined,			  		  				  		  			
						}
					}
				},				
							
				render : {
					method : function() {
						var T = this.__;
						//--> grid
						T.o.ZcoreGridInvoker('grid', T);
						//--> context popup
						T.o.find('#div-body table tbody').contextPopup({items : [{
							label : $.ZAP.message.msg('choose'),
							icon : 'ui-icon ui-icon-clipboard',
							action : function(e) {								
								var
									cell = $(e.target), 
									input = $('<input>', {
										type : 'text',
										readonly : 'readonly',
										"class" : 'ui-widget-content zui-text-content zui-text-sizing',
										/*
										 * could not set width 100% because td has padding attribute
										 */
										style : 'width:100%;margin:0px;',
										value : $(e.target).html() 
									}).click(function() {
										return false;
									})
									.blur(function() {
										cell.html($(this).val());									
									});
								//--> render
								cell.html('').append(input);
								//--> focus and select
								input.focus().select();
							} 
						}]});
					}			
				},
				
				getMasterValue : {
					method : function(component, argument) {	
						var master = this.__.owner.elements[component];
						switch (master.type()) {
						case 'grid':
							var rows = master.getHoverRows();
							for (var item in master.getItems()) { 
								if (master.getItems()[item] == argument) {
									break;
								}
							}
							return ((rows[0]) ? ((rows[0][item]) ? rows[0][item] : '') : '');
						default:
							return master.value().split(',')[0];
						}
					}
				},		
				
				getRowsObject : {
					method : function() {
						return this.__.o.find('#div-body table tbody tr');
					}
				},
				
				getRowData : this._setInvoker(),
				
				getSelectedRows : this._setInvoker(),	
				
				getSelectedCols : this._setInvoker(),		
				
				getHoverRows : this._setInvoker(),
				
				getHoverCols : this._setInvoker(),		
				
				getSelectedRowsId : this._setInvoker(),
				
				setSelectedRowsId : this._setInvoker(),
				
				getTotalCount : this._setInvoker(),
				
				getHoverRowsId : this._setInvoker(),
				
				getItems : this._setInvoker(),
				
				getTitle : this._setInvoker(),
				
				clientData : this._setInvoker(),
				
				clientModelize : {
					method : function() {
						var 
							T = this.__,
							data = T.toColumnName(T.clientData());
						//--> return modelize
						return {
							persist : function(v) {
								// get last persist id
								var last = 0;
								for (var row in data) {
									var lastId = parseInt(data[row]['id']);
									if (lastId < last) {
										last = lastId;
									}
								}
								// push new record
								data.push(
									$.extend(v, {
										'id' : last - 1,
										'status' : 1
									})
								);
								T.clientData(T.toColumnIndex(data));
							},
							merge : function(v) {
								for (var row in data) {
									if (data[row]['id'].toString() == v['id'].toString()) {
										for (var key in data[row]) {
											if (key == 'id') {
												continue;
											}
											if (key != 'status') {
												if (v[key] !== undefined) {
													data[row][key] = v[key].toString();
												}
											} else {
												data[row][key] = parseInt(data[row][key]) === 1 ? 1 : 2;
											}
											
										}
									}
								}
								T.clientData(T.toColumnIndex(data));
							},
							remove : function(v) {
								data = $.grep(data, function(row) {					
									if (row['id'].toString() != v['id'].toString()) {
										return true;
									} else {
										if (row['status'] != undefined && parseInt(row['status']) !== 1) {
											for (var key in row) {
												if (key == 'id') {
													continue;
												}
												if (key != 'status') {
													if (v[key] !== undefined) {
														row[key] = v[key].toString();
													}
												} else {
													row[key] = -1;
												}
											}											
											T.temp.concatData.push(T.toColumnIndex([row])[0]);
										}
										return false;
									}
								});	
								T.clientData(T.toColumnIndex(data));
							}
						}
					}
				},
				
				toDimensionArray : {
					method : function(v, size) {
						v = v.toString().split(',');
						var result = [];
						while (v[0] !== undefined) {
							result.push(v.splice(0, size));
						}
						return result;
					}
				},
				
				toColumnName : {
					method : function(v) {
						var 
							T = this.__,
							columns = T.params.data[0],
							info = T.params.data[1],
							res = [];
						for (var row in v) {
							var map = {};
							for (var col in v[row]) {
								var infoCol = info[col];
								if (infoCol && infoCol.type == "table") {
									map[columns[col]] = T.toDimensionArray(v[row][col], infoCol.data.length);
								} else {
									map[columns[col]] = v[row][col];
								}	
							}
							res.push(map);
						}
						return res;
					}
				},
				
				toColumnIndex : {
					method : function(v) {
						var 
							T = this.__,
							columns = T.params.data[0],
							info = T.params.data[1],
							res = [];
						for (var row in v) {
							// init array
							var arr = [];
							for (var column in columns) {
								arr.push(null);
							}
							// set array
							for (var key in v[row]) { 
								var infoCol = info[columns.indexOf(key)];
								if (infoCol && infoCol.type == "table") {
									arr[columns.indexOf(key)] = T.toDimensionArray(v[row][key], infoCol.data.length);
								} else {
									arr[columns.indexOf(key)] = v[row][key];
								}
							}
							res.push(arr);
						}
						return res;
					}
				},
				
				visibility : {
					method : function(name, v, k) {
						var 
							T = this.__,
							c = undefined;
						switch (name) {
						case "operation":
							c = T.o.find('#div-body thead tr td:last-child, #div-body tbody tr td:last-child');
							break;
						case "columns":
							var columns = T.params.data[0];
							for (var i in v) {
								var index = columns.indexOf(v[i]);
								//--> show and hide data and header
								c = T.o.find('#col-' + index + ", #td-" + v[i]);								
								arguments[arguments.length - 1] ? c.show() : c.hide();
								//--> show and hide filter
								c = T.o.find('#filterColumn-' + index).parent();								
								arguments[arguments.length - 1] ? c.show() : c.hide();
							}
							return;
							break;
						default:
							/*
							 * When you use jQuery("#elemid") it selects only the first element with the given ID.
							 * However, when you select by attribute, it returns all matching elements, 
							 * like so: jQuery("[id=elemid]") 
							 * This of course works for selection on any attribute, and you could further refine your selection by specifying the tag 
							 * jQuery("div[id=elemid]") 
							 */
							c = T.o.find("[id=" + name + "]");
							break;
						}								
						arguments[arguments.length - 1] ? c.show() : c.hide();
					}
				},
								
				addAction : {
					method : function(v) {
						var P = this.__.params;
						if (P.iconCallback === undefined) {
							P.iconCallback = [];
						}
						if ($.isArray(v)) {
							for (var i = 0; i < v.length; i++) {
								P.iconCallback.push(v[i]);		
							}
						} else if ($.isPlainObject(v)) { 
							P.iconCallback.push(v);
						}
					}
				},			
				
				persistClick : {
					//--> event
				},
				
				mergeClick : {
					//--> event
				},
				
				removeClick : {
					//--> event
				},
				
				viewClick : {
					//--> event
				},
				
				rowClick : {
					//--> event
				},
				
				rowDblClick : {
					//--> event
				},
				
				refreshClick : {
					//--> event
				},			
				
				rowAllClick : {
					//--> event
				},
				
				hoverClick : {
					//--> event
				},
				
				refreshGrid : {
					method : function() {
						var T = this.__;
						T.o.ZcoreGrid.apply(T.o, $.merge(["refresh"], arguments));
					}
				},
				
				clean : {
					method : function() {
						this.__.clientData([]);
					}
				},
				
				crud : {
					event : function(method) {
						var 
							T = this.__,
							func = function(v, row) {
								row = row || [];
								if (row.length !== 0) {
									row = T.toColumnName([row])[0];
								}
								method(v, row);
							};
						T.temp.crudable = true;							
						T
							.onPersistClick(function() {
					      		func('persist');
							})
							.onMergeClick(function(row) {  
					      		func('merge', row);
					  		})
							.onRemoveClick(function(row) { 
					      		func('remove', row);
							})
							.onViewClick(function(row) { 
					      		func('view', row);
							})
							.onKeypress(function(e) {
								var row = T.getHoverRows(true);								
								switch (e.which) {
								case 43:
					          		func('persist');
					        		break;
								case 42:
									e.shiftKey ? func('view', row[0]) : func('merge', row[0]);
					        		break;
					        	case 45:
					          		func('remove', row[0]);
					        		break;	
					      		}
					      	});						
					}
				}
			
			});
			
		}
		
	});
	
})(jQuery, jQuery.ZOF);