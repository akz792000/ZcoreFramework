/**
 * 
 * @author Ali Karimizandi
 * @since 2012 
 *   
 */

(function($, _) {
			
	_.clazz({
		
		name : 'mask',
		
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
				
				value : {
					get : function() {
						var
							T = this.__,
							mask = T.temp.mask;
						if (mask.format === undefined || T.valence()) {
							return mask.o.val();
						} else {
							switch (mask.type) {
							case "decimal": return new DecimalFormat(mask.format).formatBack(mask.o.val());
							case "mask": return mask.o.mask();
							case "alphanumeric": return mask.o.val();
							case "float": return new FloatFormat(mask.format).formatBack(mask.o.val());
							}					
						}
					},
					set : function(v) {
						var	
							T = this.__,
							mask = T.temp.mask;
						mask.o.val(v);
					},
					after : function(v) {
						this.__.refresh();
					}
				},				
										
				/*
				 * new
				 */
			
				realSize : _.defaultProperty('int', -1),
				
				floatSize : _.defaultProperty('int', -1),
				
				max : _.defaultProperty('float', -1),	
				
				min : _.defaultProperty('float', -1),	
				
				mask : $.extend(_.defaultProperty(), {
					refresh : 'finalize',
					set : function(v) {
						var 
							T = this.__,
							mask = T.temp.mask;
						switch (v) {
						case "numeric":
							mask.type = undefined;
							mask.format = undefined;
							T.numeric();
							break;
						case "commaSeparated":
							mask.type = "decimal";
							mask.format = '#,###';
							break;							
						case "mask":
							mask.type = "mask";
							mask.format = T.format();
							break;
						case "decimal":
							mask.type = "decimal";
							mask.format = T.format();
							break;
						case "alphanumeric":
							mask.type = "alphanumeric";
							mask.format = T.format();
							mask.signature = T.signature();
							break;
						case "float":
							mask.type = "float";
							mask.format = '#,###';
							break;
						}						
					},
					after : function() {
						this.__.refresh();
					}
				}),
				
				format : _.defaultProperty(),
				
				valence : _.defaultProperty("boolean", false),
				
				signature : _.defaultProperty("boolean", false)
				
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
						$.extend(this.__.temp, {
							mask : {
								o : undefined,
								type : undefined,
								format : undefined
							}
						});
					}			
				},		
				
				refresh : {
					advice : 'after',
					method : function() {
						var 
							T = this.__,
							mask = T.temp.mask;
						switch (mask.type) {
						case "mask":
							mask.o.mask(mask.format);
							break;
						case "decimal":
							T.decimal(mask.format, T.realSize());
							break;
						case "alphanumeric":
							T.alphanumeric();
							break;
						case "float":
							T.float(mask.format, T.realSize(), T.floatSize());
							break;
						default:
							if (parseInt(T.floatSize()) > 0) {
								var val = mask.o.val();
								if (parseInt(val) > 0) {
									mask.o.val(parseFloat(val || 0).toFixed(T.floatSize()));
								}
							}
							break;
						}
					}
				},
				
				/*
				 * new
				 */
				
				numeric : {
					method : function() {
						var 
							T = this.__,
							mask = T.temp.mask;
						mask.o
							.unbind('paste.mask').bind('paste.mask', function() {
								var element = $(this);
								setTimeout(function(){
									element.val(element.val().replace(/[^0-9+]/g, ''));
								});					
							})
							.unbind('keyup.mask').bind('keyup.mask', function(event) {
								//--> prevent typing zero as first character
								if ($(this).val().substring(0, 1) == '0' && T.format() !== 'zeroable') {
									$(this).val('');  									
								}
							})
							.unbind('blur.mask').bind('blur.mask', function(event) {								
								var
									max = T.max(),
									min = T.min(),
									val = parseFloat($(this).val() || 0).toFixed(T.floatSize());
								if (val == 0) {
									return;
								}
								if (parseInt(T.floatSize()) > 0) {
									$(this).val(val);
								}
								//--> check max
								if (max > -1 && val > T.max()) {
									$(this).val(max);
								}
								//--> check min
								if (min > -1 && val < T.min()) {
									$(this).val(min);
								}
							})						
							.unbind('keypress.mask').bind('keypress.mask', function(event) {
								var val = $(this).val();
								if (!(event.which >= 48 && event.which <= 57)) {
									if ($.inArray(event.keyCode, $.ZOF.functionalKeys) !== -1) {
										return;
									}
									if (event.ctrlKey && (event.which == 99 /* copy */ || event.which == 118 /* paste */)) {
										return;
									}
									if (parseInt(T.floatSize()) > 0) {
										//--> check for dot
										if (event.which == 46) {
											if (val == "" || val.indexOf('.') !== -1) {
												event.preventDefault();
											}
											return;
										} 
									}
									event.preventDefault();
									return;
								};
								if (parseInt(T.floatSize()) > 0) {
									//--> check for numeric number
									var
										charIndex = $(this).get(0).selectionStart,
										charEndIndex = $(this).get(0).selectionEnd,
										pointIndex = val.indexOf('.'),
										vals = val.split('.'),
										rv = vals[0] || '0',
										fv = vals[1] || '0',
										rs = T.realSize(),
										fs = T.floatSize();
									//--> check selected equal to maxlength	
									var charLength = charEndIndex - charIndex;
									if ((charLength == T.maxlength()) || (charLength == T.realSize()) || (charLength == T.floatSize())) {
										return;
									}
									//--> real
									if (pointIndex == -1 || charIndex < pointIndex) {
										if (rv.length < rs) {
											return;
										}									
									//--> float	
									} else {
										if (fv.length < fs) {
											return;
										}																			
									}				
									event.preventDefault();
									return
								}
							});
					}
				},
				
				decimal : {
					method : function(maskFormat, realSize) {
						//--> parse it for first time 
						var 
							T = this.__,
							mask = T.temp.mask,
							decimalFormat = new DecimalFormat(maskFormat),
							val = decimalFormat.formatBack(mask.o.val()), 
							flag = true;
						mask.o.val(decimalFormat.format(val));
						//--> binding
						mask.o
							.unbind('paste.mask').bind('paste.mask', function() {
								var element = $(this);
								setTimeout(function(){
									var 
										decimalFormat = new DecimalFormat(maskFormat),
										val = decimalFormat.formatBack(element.val().replace(/[^0-9+]/g, ''));
									element.val(decimalFormat.format(val));
								}, 10);					
							})
							.unbind('blur.mask keyup.mask').bind('blur.mask keyup.mask', function(event) {
								var 
									decimalFormat = new DecimalFormat(maskFormat),
									val = decimalFormat.formatBack($(this).val());
								$(this).val(decimalFormat.format(val));
								flag = true;
								return false;
							})
							.unbind('keypress.mask').bind('keypress.mask', function(event) {	
								if (!flag) {
									event.preventDefault();
									event.stopPropagation();
									return; 
								}
								flag = false;
								if (!(event.which >= 48 && event.which <= 57 || event.which == 46)) {
									if ($.inArray(event.keyCode, $.ZOF.functionalKeys) !== -1) {
										return;
									}
									if (event.ctrlKey && (event.which == 99 /* copy */ || event.which == 118 /* paste */)) {
										return;
									}
									event.preventDefault();
									return;
								};
								var ch = "1234567890.";
								if (ch.indexOf(String.fromCharCode(event.charCode)) != -1) {
									//--> check for minus
									if (String.fromCharCode(event.charCode) == '-' && $(this).val().indexOf(String.fromCharCode(event.charCode)) != -1) { 
										return false;
									}
									if (String.fromCharCode(event.charCode) == '-' && $(this).val().length != 0) {
										return false;
									}
									//--> check it
									var mainVal = $(this).val().replace(new RegExp(',', 'g'), ''),
										realVal = mainVal.split('.')[0],
										floatVal = mainVal.split('.')[1];
									if (realSize != undefined && realSize != -1 && realVal.length >= realSize) { 
										return false; 
									}
									//--> check for point
									if (event.which == 46) {
										if (maskFormat.indexOf('0') == -1) {
											return false;										
										}
									}
									return true;
								}									
							});														
					}									
				},
				
				alphanumeric : {
					method : function() {
						var T = this.__;
						T.temp.mask.o.unbind('keypress.mask').bind('keypress.mask', function(event) {	
						    var ew = event.which;
						    if (T.temp.mask.signature && $.ZOF.signatureKeys(ew)) {
						    	return true;
						    }
						    if ($.inArray(event.keyCode, $.ZOF.functionalKeys) !== -1) {
								return true;
							}
						    if (32 == ew) {
						        return true;
						    }
						    if (48 <= ew && ew <= 57) {
						        return true;
						    }
						    //--> english
						    if (T.temp.mask.format == 'english') {
							    if (65 <= ew && ew <= 90) {
							        return true;
							    }
							    if (97 <= ew && ew <= 122) {
							        return true;
							    }
							//--> farsi    
						    } else {
						    	if ($.inArray(ew, 
							    	[1570,1575,1576,1662,1578,1579,1580,1670,1581,1582,1583,
							    	1584,1585,1586,1688,1587,1588,1589,1590,1591,1592,1593,
							    	1594,1601,1602,1705,1711,1604,1605,1606,1608,1607,1740,
							    	1574,1632,1633,1634,1635,1636,1637,1638,1639,1640,1641]) !== -1) {
						    		return true;
						    	}						    	
						    }
						    return false;
						});
					}
				},

				float: {
					method: function (maskFormat, realSize, floatSize) {
						//--> parse it for first time
						var
							T = this.__,
							mask = T.temp.mask,
							decimalFormat = new FloatFormat(maskFormat),
							val = decimalFormat.formatBack(mask.o.val()),
							flag = true;
						mask.o.val(decimalFormat.format(mask.o.val(), floatSize));
						//--> binding
						mask.o
							.unbind('paste.mask').bind('paste.mask', function () {
								var element = $(this);
								setTimeout(function () {
									var
										decimalFormat = new FloatFormat(maskFormat),
										val = decimalFormat.formatBack(element.val().replace(/[^0-9+]/g, ''));
									element.val(decimalFormat.format(val, floatSize));
								}, 10);
							})
							.unbind('blur.mask').bind('blur.mask', function (event) {
								var
									decimalFormat = new FloatFormat(maskFormat),
									val = decimalFormat.formatBack($(this).val());
								$(this).val(decimalFormat.format(val, floatSize));
								flag = true;
								return false;
							})
							.unbind('keyup.mask').bind('keyup.mask', function (event) {
								//--> prevent typing zero as first character
								if (($(this).val().substring(0, 1) == '0' && T.format() !== 'zeroable' )) {
									$(this).val('');
								}
								if ($(this).val().substring(0, 1) == '-' && $(this).val().substring(1, 2) == '0' && T.format() !== 'zeroable') {
									$(this).val('-');
								}
								flag = true;
								return false;
							})
							.unbind('keypress.mask').bind('keypress.mask', function (event) {
							var val = $(this).val();
							if (!flag) {
								event.preventDefault();
								event.stopPropagation();
								return;
							}
							flag = false;
							if (!(event.which >= 48 && event.which <= 57 || event.which == 46 || event.which == 45)) {
								if ($.inArray(event.keyCode, $.ZOF.functionalKeys) !== -1) {
									return;
								}
								if (event.ctrlKey && (event.which == 99 /* copy */ || event.which == 118 /* paste */)) {
									return;
								}
								event.preventDefault();
								return;
							}
							;
							if (event.which == 46) {
								//--> check for dot
								if (parseInt(T.floatSize()) > 0) {
									if (val == "" || val.indexOf('.') !== -1) {
										event.preventDefault();
									}
									return true;
								}
							}
							var ch = "1234567890.";
							if (ch.indexOf(String.fromCharCode(event.charCode)) != -1) {
								//--> check for minus
								if (String.fromCharCode(event.charCode) == '-' && $(this).val().indexOf(String.fromCharCode(event.charCode)) != -1) {
									return false;
								}
								if (String.fromCharCode(event.charCode) == '-' && $(this).val().length != 0) {
									return false;
								}
								//--> check it
								var mainVal = $(this).val().replace(new RegExp(',', 'g'), ''),
									realVal = mainVal.split('.')[0] || '0',
									floatVal = mainVal.split('.')[1] || '0',
									pointIndex = mainVal.indexOf('.'),
									rs = T.realSize(),
									fs = T.floatSize();
								if (realVal.substring(0, 1) == '-')
									realVal = realVal.substring(1, realVal.length);
								if (pointIndex == -1) {
									if (realVal.length > rs-1) {
										return false;
									}
									//--> float
								} else {
									if (floatVal.length > fs-1) {
										return false;
									}
								}
								return true;
							}
						});
					}
				}

			});
			
		}
						
	});
	
})(jQuery, jQuery.ZOF);