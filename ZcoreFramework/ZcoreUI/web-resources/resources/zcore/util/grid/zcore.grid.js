/**
 * 
 * @author Ali Karimizandi
 * @since 2012 
 *   
 */

(function($) {
	
	/*
	 * constructor
	 */
	function ZcoreGridPlugin() {
	}
	
	$.extend(ZcoreGridPlugin, {
		
		/*
		 * clientable data
		 */
		clientableData : function(options) {
			var data = undefined;
		    if (options.clientable) {
		    	//--> get main data
		    	data = options.o.data("ZcoreGridData").slice(); 
		    	//--> order
		    	if (options.orderByItem) {
					data.sort(function(fst, snd){
						var 
							itm = options.cols.indexOf(options.orderByItem),
							itmSort = options.orderByItemSort,
							first = fst[itm] == undefined ? '' : fst[itm].toString().replace(new RegExp('/', 'g'), '0'),
							second = snd[itm] == undefined ? '' : snd[itm].toString().replace(new RegExp('/', 'g'), '0');
				    	if (!isNaN(parseFloat(first, 10)) && !isNaN(parseInt(second), 10)) {
				    		first = parseInt(first, 10);
				    		second = parseInt(second, 10); 
				    	}		    
				    	switch (itmSort) {
							case 'asc':
								return (first < second) ? -1 : 1;
								break;
							case 'desc':
								return (first < second) ? 1 : -1;
								break;
						}						
				    });	
		    	}	
		    	var filterColumnsValue = options.filterColumnsValue.toString().replace(new RegExp(',', 'g'), '').replace(new RegExp('undefined', 'g'), '');
		    	if (filterColumnsValue != '') {
		    		var filters = options.filterColumnsValue;
		    		data = data.filter(function(value) {	    		
			    		for (var i = 0; i < filters.length; i++) {
			    			var 
			    				filter = filters[i],
			    				res = true;
			    			if (filter != undefined && filter != '') {
			    				if (options.filterColumns[i] == undefined) {
			    					res = res && value[i].toString().startsWith(filter.toString());
			    				} else {
			    					res = res && value[i].toString() == filter.toString();
			    				}
			    				if (!res) {
			    					break;
			    				}
			    			}
			    		}
			    		return res;
		    		});
		    	}
		    	options.totalPage = Math.ceil(data.length / ((options.limitPage == 0) ? data.length : options.limitPage));		    	
		    	options.totalCount = data.length;
				//--> return data		    	
				var page, fromPage, toPage;
				page = options.activePage <= 0 ? 1 : options.activePage;				
			    page = page >= options.totalPage ? options.totalPage : page;
		    	fromPage = (page - 1) * options.limitPage;
		    	toPage = page * options.limitPage;
			    fromPage = fromPage <= options.totalCount ? fromPage : options.totalCount;
			    toPage = toPage <= options.totalCount ? toPage : options.totalCount;
			    data = data.slice(fromPage, toPage);
		    } 
		    return data;
		},
		
		/*
		 * draw seperator
		 */		
		drawSeperator : function(options) {
			return '&nbsp;&nbsp;';
		},
			
		/*
		 * draw header Title
		 */
		drawHeaderTitle : function(table, options) {
			var 
				orientation = options.direction === 'rtl' ? ['right', 'left'] : ['left', 'right'],
				trHeaderStyle = 'style="text-align:' + orientation[0] + '"';			
	    	table.push('<tr ' + trHeaderStyle + '>');
	    	table.push('<th colspan="' + parseInt(options.cols.length + 2) + '" style="border:0px;padding:2px 0;">');
	    	table.push('<label style="margin:0 24px;">' + 
    				options.headerTitle + 
    				'</label>' +  ZcoreGridPlugin.drawSeperator(options));
	    	table.push('<div id="headerTitle" class="ui-corner-all" style="position:absolute;top:5px;width:16px;margin:0 2px;">');
	    	table.push('<span class="ui-icon ui-icon-minus"></span>');
	    	table.push('</div>');
	    	table.push('</th>');
	    	table.push('</tr>');
		},
		
		/*
		 * draw header icon
		 */
		drawHeaderIcon : function(table, options) {
			var 
				orientation = options.direction === 'rtl' ? ['right', 'left'] : ['left', 'right'],
				trHeaderStyle = 'style="text-align:' + orientation[0] + '"',
				trHeaderRStyle = 'style="float:FLOAT;border-BORDER:1px solid;display:DISPLAY"'
					.replace("FLOAT", orientation[0])
					.replace("BORDER", orientation[1])
					.replace("DISPLAY", options.refreshShow ? "block" : 'none'),
				trHeaderBStyle = 'float:FLOAT;border-BORDER:1px solid;padding-FST:2px;padding-SND:8px;'
						.replace("FLOAT", orientation[0])
						.replace("BORDER", orientation[1])
						.replace("FST", orientation[0])
						.replace("SND", orientation[1]),
				bodyIcon = false;		
			table.push('<tr ' + trHeaderStyle + '>');
		    table.push('<th colspan="' + parseInt(options.cols.length + 2) + '">');
		    //--> refreshIcon
	    	table.push('<div id="refresh" title="' + options.message.get(options.locale, 'refresh') + '" ' + trHeaderRStyle + '><span class="ZcoreGridHeaderIcon ZcoreGridRefreshStaticIcon"></span></div>' +  ZcoreGridPlugin.drawSeperator(options));
		    //--> filterIcon
	    	if (options.filterShow) {
	    		table.push('<div id="filter" style="' + trHeaderBStyle + '"><span class="ZcoreGridHeaderIcon ZcoreGridFilterIcon"></span><b>' + options.message.get(options.locale, 'filter') + '</b></div>' +  ZcoreGridPlugin.drawSeperator(options));
	    	}
		    //--> persist
		    if (options.persistCallback && options.persist !== '' ) {
		    	if (options.persist.split(',').length === 1) {
			    	table.push('<div id="persist" style="' + trHeaderBStyle + '"><span class="ZcoreGridHeaderIcon ZcoreGridPersistIcon"></span><b>' + 
			    				((options.persist !== 'default') ? (options.persist) : options.message.get(options.locale, 'persist')) + 
			    				'</b></div>' +  ZcoreGridPlugin.drawSeperator(options));
		    	} else {
		    		bodyIcon = true;
		    	}
		    }
		    //--> merge
		    if (options.mergeCallback && options.merge !== '') {
		    	if (options.merge.split(',').length === 1) {
			    	table.push('<div id="merge" style="' + trHeaderBStyle + '"><span class="ZcoreGridHeaderIcon ZcoreGridMergeIcon"></span><b>' + 
			    				((options.merge !== 'default' ) ? (options.merge) : options.message.get(options.locale, 'merge')) + '</b></div>' +
			    				ZcoreGridPlugin.drawSeperator(options));
		    	} else {
		    		bodyIcon = true;
		    	}			    	
		    }
		    //--> remove
		    if (options.removeCallback && options.remove !== '') {
		    	if (options.remove.split(',').length === 1) {
		    		table.push('<div id="remove" style="' + trHeaderBStyle + '"><span class="ZcoreGridHeaderIcon ZcoreGridRemoveIcon"></span><b>' + 
			    				((options.remove !== 'default') ? (options.remove) : options.message.get(options.locale, 'remove')) + '</b></div>' +  
			    				ZcoreGridPlugin.drawSeperator(options));
		    	} else {
		    		bodyIcon = true;
		    	}			    		
		    }		    
		    //--> view
		    if (options.viewCallback && options.view !== '') {
		    	if (options.view.split(',').length === 1) {
		    		table.push('<div id="view" style="' + trHeaderBStyle + '"><span class="ZcoreGridHeaderIcon ZcoreGridViewIcon"></span><b>' + 
			    				((options.view !== 'default') ? (options.view) : options.message.get(options.locale, 'view')) + '</b></div>' +  
			    				ZcoreGridPlugin.drawSeperator(options));
		    	} else {
		    		bodyIcon = true;
		    	}			    		
		    }		    
		    //--> icon 
		    for (var i = 0; i < options.iconCallback.length; i++) {
		    	if (options.iconCallback[i].action !== undefined) {
		    		if (options.iconCallback[i].title.split(',').length === 1) {
			    		table.push(
			    				(options.iconCallback[i].image ? '<img align="top" alt="" src="' + options.iconCallback[i].image + '">' : '') +
			    				'<' + options.iconCallback[i].name + '> ' + options.message.translate(options.iconCallback[i].title, undefined, options.locale) + 
			    				' </' + options.iconCallback[i].name + '>' + 
			    				ZcoreGridPlugin.drawSeperator(options));
		    		} else {
		    			bodyIcon = true;
		    		}
		    	}
			}	
			table.push('</th></tr>');
			return bodyIcon;
		},
		
		/*
		 * draw header
		 */
		drawHeader : function(table, options, bodyIcon) {
		    table.push('<tr>'); 
		    //--> for hover column
		    table.push('<td>' + options.message.get(options.locale, 'row') + '</td>');
		    //--> draw check box		    
		    if (options.multiSelected)
		    	table.push('<td id="checkbox" align="center"><input tabindex="-1" id="checkbox-input" type="checkbox" style="cursor: pointer" /></td>');
		    //--> draw sort and order
		    for (var i = 0; i < options.cols.length; i++) {
		    	//--> set picSortClientItem for draw 
		        var picSortClientItem = 'ZcoreGridBGIcon';
		        var sortClientItemTitle = "";
		    	if (i === options.sortClientItem) {		
		    		switch (options.sortClientItemSort) {		    		
		    			case 'asc':
		    				picSortClientItem = 'ZcoreGridASCIcon';
		    				sortClientItemTitle = options.message.get(options.locale, 'asc');
		    				break;
		    			case 'desc':
		    				picSortClientItem = 'ZcoreGridDSCIcon';
		    				sortClientItemTitle = options.message.get(options.locale, 'desc');
		      				break;
		    		} 		    		
		    	}		    	
		    	//--> set picOrderByItem for draw 
		    	var picOrderByItem = 'ZcoreGridBGIcon';
		    	var orderByItemTitle = ""; 
		    	if (options.cols[i] === options.orderByItem) {
		    		switch (options.orderByItemSort) {		    		
		    			case 'asc':
		    				picOrderByItem = 'ZcoreGridASCIcon';
		    				orderByItemTitle = options.message.get(options.locale, 'asc');
		    				break;
		    			case 'desc':
		      				picOrderByItem = 'ZcoreGridDSCIcon';
		      				orderByItemTitle = options.message.get(options.locale, 'desc');
		      				break;
		    		}    		                       	
		    	}		    	
		    	//--> draw icon
		    	var imgStyle = '';    
		    	//--> col style
		    	var colStyle = '';
		    	if (options.colsStyle !== undefined) {
		    		colStyle = ((options.colsStyle[i] !== undefined) ? ' style="' + options.colsStyle[i] + '"' : '');
		    	}
		    	//--> col width
		    	var colWidth = '';
		    	if (options.colsWidth !== undefined) {		    		
		    		colWidth = ((options.colsWidth[i] !== undefined) ? ' width="' + options.colsWidth[i] + 'px"' : '');
		    	}				
		    	table.push('<td id="td-' + options.cols[i] + '" class="td"' + 
		    			   colStyle +
		    			   colWidth +		    			   
		    			   '>' +
		    			   ((options.sortShow && options.sortsShow[i]) ? '<span id="sortClientItem-' + i + '" class="' + picSortClientItem + '" title="' + sortClientItemTitle + '" ' + imgStyle + ' />' : '') + 
		    			   options.titles[i] + 
		    			   ((options.orderShow && options.ordersShow[i]) ? '<span id="orderByItem-' + options.cols[i] + '" class="' + picOrderByItem + '" title="' + orderByItemTitle + '" ' + imgStyle + ' />' : '') +
		    			   '</td>');      	
		    }	
	    	if (bodyIcon) {
	    		table.push('<td>' + options.message.get(options.locale, 'operation') + '</td>');
	    	}		    
		    table.push('</tr>');    
		    //--> draw filter row 
		    if (options.filterRowVisible) {
		    	table.push('<tr id="filterRow">');
		    } else {
		    	table.push('<tr id="filterRow" style="display: none;">');
		    }	 
		    //--> for hover column
		    table.push('<td></td>');
		    if (options.multiSelected) 
		    	table.push('<td></td>');
		    for (var i = 0; i < options.cols.length; i++) {
		    	//--> col style
		    	var colStyle = '';
		    	if (options.colsStyle !== undefined) {
		    		colStyle = ((options.colsStyle[i] !== undefined) ? ' style="' + options.colsStyle[i] + '"' : '');
		    	}
		    	//--> col width
		    	var colWidth = '';
		    	if (options.colsWidth !== undefined) {		    		
		    		colWidth = ((options.colsWidth !== undefined) ? ' width="' + options.colsWidth[i] + 'px"' : '');
		    	}
		    	//--> set filter columns
		    	if (options.filtersShow[i]) {
			    	if (options.filterColumns && options.filterColumns[i]) { 
				    	table.push('<td ' + colStyle + colWidth + '>');
				    	if (options.filterColumns[i].type == 'select') { 
					    	table.push('<select id="filterColumn-' + i + '" tabindex="-1" class="ui-corner-all">');
					    	table.push('<option value="" selected=""></option>');
					    	for (var item in options.filterColumns[i].data) 
					    		table.push('<option value="' + item + '">' + options.filterColumns[i].data[item] + '</option>');		
					    	table.push('</select>');					
						} else
					    	table.push('<input tabindex="-1" type="text" id="filterColumn-' + i + '" value="' + (options.filterColumnsValue[i] === undefined ? '' : options.filterColumnsValue[i]) + '" class="ui-corner-all" />');
				    	table.push('</td>');		    		
			    	} else 			    	
				    	table.push('<td ' + colStyle + colWidth + '>' + 
				    			   '<input tabindex="-1" type="text" id="filterColumn-' + i + '" value="' + (options.filterColumnsValue[i] === undefined ? '' : options.filterColumnsValue[i]) + '" class="ui-corner-all" />' +    				
				    	 		   '</td>');
		    	} else {
		    		table.push('<td ' + colStyle + colWidth + '>' + '</td>');
		    	}		    	
		    }	
	    	if (bodyIcon) {
	    		table.push('<td></td>');
	    	}
		    table.push('</tr>');		    
		},		
		
		/*
		 * draw body
		 */
		drawBody : function(table, options) {
		    //--> sort 
			if (options.data) {
				options.data.sort(function(fst, snd){
					var 
						itm = options.sortClientItem,
						itmSort = options.sortClientItemSort,
						first = fst[itm] == undefined ? '' : fst[itm].toString().replace(new RegExp('/', 'g'), '0'),
						second = snd[itm] == undefined ? '' : snd[itm].toString().replace(new RegExp('/', 'g'), '0');
			    	if (!isNaN(parseFloat(first, 10)) && !isNaN(parseInt(second), 10)) {
			    		first = parseInt(first, 10);
			    		second = parseInt(second, 10); 
			    	}		    
			    	switch (itmSort) {
						case 'asc':
							return (first < second) ? -1 : 1;
							break;
						case 'desc':
							return (first < second) ? 1 : -1;
							break;
					}
			    });	  
			};
		    //--> order
			var loopCount = options.limitPage ? options.limitPage : (options.data ? options.data.length : 0);
			var trClass = '';
		    for (var tr = 0; tr < loopCount; tr++) {
		    	trClass = '';
		    	if ((tr == 0) && (options.data && options.data[tr] && (options.data[tr].length != 0)))
		    			trClass = ' ZcoreGridHoverRow';
		        //--> for row that hasn't any data		
		    	var showRow = '';
		    	if ((tr % 2) !== 0) {
		    		showRow = '<tr class="tr ZcoreGridOddRow' + trClass + '" id="row-' +  tr + '" %s>';
		    	} else {
		    		showRow = '<tr class="tr ZcoreGridEvenRow' + trClass + '" id="row-' +  tr + '" %s>';
		    	}
		    	table.push(showRow.replace("%s", (options.data && options.data[tr] && (options.data[tr].length != 0)) ? "" : 'style="display:none"'));
		    	//--> for hover column
		    	var rowNum = '';
		    	if (options.data && options.data[tr] && (options.data[tr].length != 0)) {
		    		switch (options.showNumber) {
					case 'page':
						rowNum = tr + 1;
						break;
					case 'all':
			    		var activePageNum = options.activePage - 1;
			    		rowNum = tr + 1 + (activePageNum > 0 ? activePageNum : 0) * options.limitPage;
						break;						
					default:
						rowNum = '';
						break;
					}
		    	} else {
		    		rowNum = '';
		    	}
		    	table.push('<td class="td-hover" align="center">' + rowNum + '</td>');
		    	if (options.multiSelected) 
		    		table.push('<td class="td-hover" align="center"><input tabindex="-1" type="checkbox" style="cursor: pointer"/></td>');			    	
		    	for (var td = 0; td < options.cols.length; td++) {
			    	//--> col style
			    	var colStyle = '';
			    	if (options.colsStyle !== undefined) {
			    		colStyle = ((options.colsStyle[td] !== undefined) ? ' style="' + options.colsStyle[td] + '"' : '');
			    	}
			    	//--> col width
			    	var colWidth = '';
			    	if (options.colsWidth !== undefined) {		    		
			    		colWidth = ((options.colsWidth[td] !== undefined) ? ' width="' + options.colsWidth[td] + 'px"' : '');
			    	}	
			    	var colType = '';
			    	if (options.colsType !== undefined) {	
			    		colType = ((options.colsType[td] !== undefined) ? ' type="' + (options.colsType[td] != undefined ? options.colsType[td] : 'string') + '"' : '');
			    	}
		    		if ((options.data) && (options.data[tr])) {
		            	//--> for col that has null value
		                if (options.data[tr][td] === null) {
		                    table.push('<td class="td" id="col-' + td + '"' + colStyle + colWidth + colType + '>&#160;</td>');
		                } else {		                	
		                	if (options.filterColumns && options.filterColumns[td]) { //--> render filter columns
		                		switch (options.filterColumns[td].type) {
								case 'select':
									table.push('<td class="td" id="col-' + td + '"' + colStyle + colWidth + colType + 'valence="' + options.data[tr][td] + '"' + ' title="' + options.data[tr][td] + '">' + 
											(options.filterColumns[td].data[options.data[tr][td]] || options.data[tr][td]) + '</td>');									
									break;
								case 'date':
									table.push('<td class="td" id="col-' + td + '"' + colStyle + colWidth + colType + 'valence="' + options.data[tr][td] + '"' + ' title="' + options.data[tr][td] + '">' + 
											options.data[tr][td] + '</td>');
									break;
								case 'string':
									table.push('<td class="td" id="col-' + td + '"' + colStyle + colWidth + colType + 'valence="' + options.data[tr][td] + '"' + ' title="' + options.data[tr][td] + '">' + 
											$.mask.format(options.filterColumns[td].data, options.data[tr][td]) + '</td>');
									break;									
								case 'numeric':
									table.push('<td class="td" id="col-' + td + '"' + colStyle + colWidth + colType + 'valence="' + options.data[tr][td] + '"' + ' title="' + options.data[tr][td] + '">' + 
											(new DecimalFormat(options.filterColumns[td].data).format(options.data[tr][td])) + '</td>');
									break;
								default:
									table.push('<td class="td" id="col-' + td + '"' + colStyle + colWidth + colType + 'valence="' + options.data[tr][td] + '"' + ' title="' + options.data[tr][td] + '">' + 
											options.data[tr][td] + '</td>');
									break;	
								}
		                	} else {
		                		//--> hintsLength
		                		if (options.hintsLength) {
		                			if (options.hintsLength[td] && (options.hintsLength[td] != 0)) {
		                				if (options.data[tr][td].length > options.hintsLength[td]) {
		                					table.push('<td class="td" id="col-' + td + '"' + colStyle + colWidth + colType + 'valence="' + options.data[tr][td] + '"' + ' title="' + options.data[tr][td] + '">' + options.data[tr][td].substr(0, options.hintsLength[td]) + ' ... </td>');
		                				} else {
		                					table.push('<td class="td" id="col-' + td + '"' + colStyle + colWidth + colType + '>' + options.data[tr][td] + '</td>');
		                				}
		                			} else {
		                				table.push('<td class="td" id="col-' + td + '"' + colStyle + colWidth + colType + '>' + options.data[tr][td] + '</td>');
		                			}			                			
		                		} else {
		                			table.push('<td class="td" id="col-' + td + '"' + colStyle + colWidth + colType + '>' + options.data[tr][td] + '</td>');
		                		}
		                	}           
		                }
		            } else {
		            	//--> for row that has null value 
		                table.push('<td class="td" id="col-' + td + '"' + colStyle + colWidth + colType + '>&#160;</td>');
		            }			    		
		        }
			    /*
			     * draw body icons
			     */ 
		    	var bodyIcon = '';
		    	//--> persist
			    if (options.persistCallback && options.persist !== '' && options.persist.split(',').length === 2 && options.persist.split(',')[1] == 'body') {
			    	bodyIcon += '<span class="ZcoreGridBodyIcon" style="display:table-cell"><span id="persist" class="ZcoreGridHeaderIcon ZcoreGridPersistIcon" title="' + ((options.persist.split(',')[0] !== 'default') ? (options.persist.split(',')[0]) : options.message.get(options.locale, 'persist')) + '"></span></span>';
			    }
			    //--> view
			    if (options.viewCallback && options.view !== '' && options.view.split(',').length === 2 && options.view.split(',')[1] == 'body') {
			    	bodyIcon += '<span class="ZcoreGridBodyIcon" style="display:table-cell"><span id="view" class="ZcoreGridHeaderIcon ZcoreGridViewIcon" title="' + ((options.view.split(',')[0] !== 'default') ? (options.view.split(',')[0]) : options.message.get(options.locale, 'view')) + '"></span></span>';
			    }			    
			    //--> merge
			    if (options.mergeCallback && options.merge !== '' && options.merge.split(',').length === 2 && options.merge.split(',')[1] == 'body') {
			    	bodyIcon += '<span class="ZcoreGridBodyIcon" style="display:table-cell"><span id="merge" class="ZcoreGridHeaderIcon ZcoreGridMergeIcon" title="' + ((options.merge.split(',')[0] !== 'default') ? (options.merge.split(',')[0]) : options.message.get(options.locale, 'merge')) + '"></span></span>';
			    }	
			    //--> remove
			    if (options.removeCallback && options.remove !== '' && options.remove.split(',').length === 2 && options.remove.split(',')[1] == 'body') {
			    	bodyIcon += '<span class="ZcoreGridBodyIcon" style="display:table-cell"><span id="remove" class="ZcoreGridHeaderIcon ZcoreGridRemoveIcon" title="' + ((options.remove.split(',')[0] !== 'default') ? (options.remove.split(',')[0]) : options.message.get(options.locale, 'remove')) + '"></span></span>';
			    }				    
			    //--> icon 
			    for (var i = 0; i < options.iconCallback.length; i++) {
			    	if (options.iconCallback[i].action !== undefined && options.iconCallback[i].title.split(',').length === 2 && options.iconCallback[i].title.split(',')[1] == 'body') {
			    		bodyIcon += '<span class="ZcoreGridBodyIcon" style="display:table-cell;vertical-align: middle;"><img id="' + options.iconCallback[i].name + '" align="top" alt="" height="16" width="16" style="padding:0 2px 0 2px;" src="' + options.iconCallback[i].image + '" title="' + options.message.translate(options.iconCallback[i].title.split(',')[0], undefined, options.locale) + '"></span>';
			    	}
				}			    
			    //--> add body icons
			    if (bodyIcon !== "") {
			    	bodyIcon = (options.data && options.data[tr]) ? '<div style="display:table">'+ bodyIcon + "</div>" : "";
			    	table.push('<td class="td-hover" align="center">' + bodyIcon + '</td>');
		    	}
		        table.push('</tr>');                            	                        	
		    }
		    //--> draw last rows 
		    if (options.lastRow) {
	    		table.push('<tr class="ZcoreGridLastRow" id="row-' +  tr + '">');
		    	for (var td = 0; td < options.cols.length; td++) {
		    		var colStyle = ((options.colsStyle !== undefined) ? ' style="' + options.colsStyle[td] + '"' : '"');
		    		var colWidth = ((options.colsWidth !== undefined) ? ' width="' + options.colsWidth[td] + 'px"' : '"');
               		if (td === 0) {
               			table.push('<td class="td" id="col-' + td + '"' + colStyle + colWidth + '>' + options.lastRow[0].title + '</td>');
               		} else {
               			var sum = 0;
        				var loopCount = options.limitPage ? options.limitPage : (options.data ? options.data.length : 0);
        			    for (var tr = 0; tr < loopCount; tr++) { 
        			    	if ((options.data) && (options.data[tr])) {            			    
        			    		sum = sum + options.lastRow[0].rowFunc(td, tr, options.data[tr][td]);
        			    	}
        			    }
               			table.push('<td class="td" id="col-' + td + '"' + colStyle + colWidth + '>' + options.lastRow[0].resFunc(td, tr, sum) + '</td>');
               		}
		    	}
		        table.push('</tr>');	
		    }
		},
		
		drawLimitPageItems : function(options) {
			var result = "<select id='limitPageItems' tabindex='-1' class='ui-corner-all'>";
			for (var item in options.limitPageItems) {
				result += ("<option value='%v' " +  (options.limitPageItems[item] == options.limitPage ? "selected='selected'" : "") + ">%v</option>").replace(/%v/g, options.limitPageItems[item]);
			}
			result += "</select>";
			return result;
		},
				
		/*
		 * draw footer
		 */
		drawFooter : function(table, options) {
			table.push('<tr>');
		    table.push('<td colspan="' + parseInt(options.cols.length + 2) + '" align="center">');
		    //--> draw first icon
		    var imageClass = '';		    
		    var disableStyle = options.activePage <= 1 ? 'style="opacity: .3; cursor: auto;"' : ''; 
		    imageClass = options.direction === 'rtl' ? 'ZcoreGridLastIcon' : 'ZcoreGridFirstIcon';
		    table.push('<span id="first" class="' + imageClass + '" title="' + options.message.get(options.locale, 'first') + '" ' + disableStyle + '/>');		    
		   	//--> draw prev icon
		    disableStyle = options.activePage <= 1 ? 'style="opacity: .3; cursor: auto;"' : '';
		    imageClass = options.direction === 'rtl' ? 'ZcoreGridNextIcon' : 'ZcoreGridPrevIcon';
		   	table.push('<span id="prev" class="' + imageClass + '" title="' + options.message.get(options.locale, 'prev') + '" ' + disableStyle + '/>');
		   	//--> draw input text 
		   	table.push(
		   		'<span">' + 	
		   		options.message.get(options.locale, 'page') +
		   		' <input tabindex="-1" id="activePage" type="text" value="' + options.activePage + '" class="ui-corner-all"/> ' + 
		   		options.message.get(options.locale, 'to') + ' ' + options.totalPage +
		   		'</span>' 
		   	);
		   	//--> draw next icon 
		   	disableStyle = options.activePage >= options.totalPage ? 'style="opacity: .3; cursor: auto;"' : '';
		    imageClass = options.direction === 'rtl' ? 'ZcoreGridPrevIcon' : 'ZcoreGridNextIcon';
		   	table.push('<span id="next" class="' + imageClass + '" title="' + options.message.get(options.locale, 'next') + '" ' + disableStyle + '/>');
		    //--> draw last icon
		    disableStyle = options.activePage >= options.totalPage ? 'style="opacity: .3; cursor: auto;"' : '';
		    imageClass = options.direction === 'rtl' ? 'ZcoreGridFirstIcon' : 'ZcoreGridLastIcon';
		    table.push('<span id="last" class="' + imageClass + '" title="' + options.message.get(options.locale, 'last') + '" ' + disableStyle + '/>');
		    //--> draw clear total count for centeralize 
		    var	page = options.activePage;
		    page = page === 0 ? 1 : page;
		    var 
		    	fromPage = (page - 1) * options.limitPage + 1,
		    	toPage = page * options.limitPage;
		    fromPage = fromPage <= options.totalCount ? fromPage : options.totalCount;
		    toPage = toPage <= options.totalCount ? toPage : options.totalCount;		    
		    var	totalCountStr = '<label>' + 
		    	options.message.get(options.locale, 'view') +	
		    	" " + 
		    	fromPage +
		    	" - " +
		    	toPage +
		    	" " +
		    	options.message.get(options.locale, 'to') +
		    	" " +
		    	options.totalCount +
		    	'</label>';
		    table.push('<span style="padding-top:3px;float:' + (options.direction === 'rtl' ? "right;" : "left;") + '">');
		    var totalCountStrClear = '&nbsp;';
		    /*for ( var i = 0; i < totalCountStr.length; i++) {
		    	totalCountStrClear += '&nbsp;';
			}*/
		    table.push(totalCountStrClear + '</span>');
		    //--> draw limit page items
		    table.push(ZcoreGridPlugin.drawSeperator());
		    table.push(ZcoreGridPlugin.drawLimitPageItems(options));
		    //--> draw main total count
		    table.push('<span id="totalCount" style="font-weight:bold;padding-top:3px;float:' +
		    		(options.direction === 'rtl' ? "right;" : "left;") + '">' + totalCountStr + '</span>');		    
		    table.push('</td>');
		    table.push('</tr>');     
		},
		
		/*
		 * drawLastCol
		 */
		drawLastCol: function(options) {
			if (options.lastCol) {
				options.o.find('#div-head table thead:last tr').append('<td>' + options.lastCol[0].head + '</td>');
			   	var row = 0;
			   	options.o.find('#div-body table tbody tr').each(function() {
			   		if ($(this).hasClass('tr') || $(this).hasClass('ZcoreGridLastRow')) {		   					   			
			   			if ($(this).hasClass('ZcoreGridLastRow')) {
			   				row += 1;
			   			} else {
			   				row = parseInt($(this).attr('id').split('-')[1]);
			   			}
			   			$(this).append('<td>' + options.lastCol[0].colFunc(ZcoreGridPlugin.getData(options, row, options.lastCol[0].colCalc)) + '</td>'); 		   				
			   		}		   		
				});
			}
		},
		
		/*
		 * getData
		 */
		getData: function(options, row, col) {	
			var res = '';
			options.o.find('#div-body table tbody tr').each(function() {				
				if (row == parseInt($(this).attr('id').split('-')[1])) {
					$(this).find('td').each(function() {
						if (col == parseInt($(this).attr('id').split('-')[1])) {
							res = $(this).text().replace(/^\s+/, "");
						}
					});        			        			        					
				}
			});	
			return res;
		},		
		
		/*
		 * is ajax start
		 */
		isAjaxStart : function(options) {
			return options.o.find('#refresh span').hasClass('ZcoreGridRefreshIcon') || options.o.attr('disabled') === 'disabled';
		},
		
		/*
		 * setFilterColumnsValue
		 */
		setFilterColumnsValue : function(options) {
			for (var i = 0; i < options.cols.length; i++) 
				if (options.filterColumns && options.filterColumns[i] && options.filterColumns[i].type == 'numeric') {
					var val = options.o.find('#filterColumn-' + i).val();
					if (val != undefined && val != '') {
						options.filterColumnsValue[i] = new DecimalFormat(options.filterColumns[i].data).formatBack(val);
					} else {
						options.filterColumnsValue[i] = val;
					}
				} else if (options.filterColumns && options.filterColumns[i] && options.filterColumns[i].type == 'string') {
					var val = options.o.find('#filterColumn-' + i).val();
					if (val != undefined && val != '') {
						options.filterColumnsValue[i] = $.mask.formatBack(options.filterColumns[i].data, val);
					} else {
						options.filterColumnsValue[i] = val;
					}
				} else {
					options.filterColumnsValue[i] = options.o.find('#filterColumn-' + i).val();
				}
		},
		
		/*
		 * set event of HeaderTitle
		 */
		setEventHeaderTitle : function(options) {
			options.o.find('#headerTitle').click(function(e, refreshable) {      
				if (!ZcoreGridPlugin.isAjaxStart(options)) {
					var span = $(this).find('span');
					if (span.hasClass('ui-icon-plus')) {
						options.o.find('#div-head-title').removeClass('ui-corner-bl ui-corner-br').css('border-width', '0 0 1px 0');
						span.removeClass('ui-icon-plus').addClass('ui-icon-minus');
						options.o.find('#div-container').show();
					} else {
						options.o.find('#div-head-title').addClass('ui-corner-bl ui-corner-br').css('border-width', '0px');
						span.removeClass('ui-icon-minus').addClass('ui-icon-plus');
						options.o.find('#div-container').hide();
					}
				}
		    });	
		},		
		
		/*
		 * set event of refreshIcon
		 */
		setEventRefreshIcon : function(options) {
			options.o.find('#refresh').click(function(e, refreshable, totalCount, done) {      
				if (!ZcoreGridPlugin.isAjaxStart(options) || done) {
					options.activePage = parseInt(options.o.find('#activePage').val()) || 0;
					ZcoreGridPlugin.setFilterColumnsValue(options);	
					options.data = (refreshable === undefined || refreshable === 'SERVER') ? undefined : ZcoreGridPlugin.clientableData(options);
					if (options.data == undefined && refreshable === 'CLIENT_DATA') {
						options.data = options.o.data("ZcoreGridData");
					}
					options.refreshable = refreshable === undefined ? options.refreshable : refreshable;
					options.totalCount = totalCount || options.totalCount; 
					ZcoreGridPlugin.refreshGrid(options);
				}
		    });	
		},
		
		/*
		 * set event of persist icon
		 */
		setEventPersistIcon : function(options) {
			options.o.find('#persist')
				.unbind()
				.click(function() {
					if (!ZcoreGridPlugin.isAjaxStart(options)) {
						options.persistCallback();						
					}
				})
				.hover( 
						function() {
							if (options.o.attr('disabled') !== 'disabled') {
								$(this).css({'cursor': 'pointer'});
							}
						}, 
						function() {
							$(this).css({'cursor': 'auto'});
						}
				);	
		},
		
		/*
		 * set event of merge icon
		 */
		setEventMergeIcon : function(options) {
			options.o.find('#merge')
				.unbind()
				.click(function() {
					if (!ZcoreGridPlugin.isAjaxStart(options)) {
						options.mergeCallback();						
					}
				})				
				.hover( 
						function() {
							if (options.o.attr('disabled') !== 'disabled') {
								$(this).css({'cursor': 'pointer'});
							}
						}, 
						function() {
							$(this).css({'cursor': 'auto'});
						}
				);	
		},		
		
		/*
		 * set event of remove icon
		 */
		setEventRemoveIcon : function(options) {
			options.o.find('#remove')
				.unbind()
				.click(function() {
					if (!ZcoreGridPlugin.isAjaxStart(options)) {
						options.removeCallback();						
					}
				})					
				.hover( 
						function() {
							if (options.o.attr('disabled') !== 'disabled') {
								$(this).css({'cursor': 'pointer'});
							}
						}, 
						function() {
							$(this).css({'cursor': 'auto'});
						}
				);
		},	
		
		/*
		 * set event of view icon
		 */
		setEventViewIcon : function(options) {
			options.o.find('#view')
				.unbind()
				.click(function() {
					if (!ZcoreGridPlugin.isAjaxStart(options)) {
						options.viewCallback();						
					}
				})					
				.hover( 
						function() {
							if (options.o.attr('disabled') !== 'disabled') {
								$(this).css({'cursor': 'pointer'});
							}
						}, 
						function() {
							$(this).css({'cursor': 'auto'});
						}
				);
		},			
		
		/*
		 * set event of action icon
		 */
		setEventActionIcon : function(options) {			
			for (var i = 0; i < options.iconCallback.length; i++) {
				options.o.find('#div-head thead tr ' + options.iconCallback[i].name)
					.unbind()
					.click(!ZcoreGridPlugin.isAjaxStart(options) && options.iconCallback[i].action)
					.hover( 
							function() {
								if (options.o.attr('disabled') !== 'disabled') {
									$(this).css({'cursor': 'pointer'});
								}
							}, 
							function() {
								$(this).css({'cursor': 'auto'});
							}
						);									
			}
		},		
		
		/*
		 * set event of filterIcon
		 */
		setEventFilterIcon : function(options) {			
			options.o.find('#filter')
				.click(function() {       	
					if (!ZcoreGridPlugin.isAjaxStart(options)) {
						if (options.filterShow) {
							(options.filterRowVisible) ? options.o.find('#filterRow')
										.fadeOut(100) : options.o.find('#filterRow')
										.fadeIn(100); 
							options.filterRowVisible = !options.filterRowVisible;
						}
					}
				})
				.hover( 
						function() {
							if (options.o.attr('disabled') !== 'disabled') {
								$(this).css({'cursor': 'pointer'});
							}
						}, 
						function() {
							$(this).css({'cursor': 'auto'});
						}
				);	 
		},
		
		/*
		 * set filter event 
		 */
		setEventFilterColumn : function(options) {
			for (var j = 0; j < options.cols.length; j++) {
				//--> set event for filter column change
				if (options.filterColumns && options.filterColumns[j]) { 	
					//--> on change 
					if (options.o.find('#filterColumn-' + j).is('select')) {
						options.o.find('#filterColumn-' + j).unbind('change.ZcoreGrid.FilterColumn').bind('change.ZcoreGrid.FilterColumn', function() {    
							if (!ZcoreGridPlugin.isAjaxStart(options)) {
						    	options.activePage = 1; 		
						    	ZcoreGridPlugin.setFilterColumnsValue(options);
						    	options.data = ZcoreGridPlugin.clientableData(options);
								ZcoreGridPlugin.refreshGrid(options);
						    }            	    	         
					    });      
					}			
					//--> mask
					if (options.filterColumns[j].type == 'date') {
						options.o.find('#filterColumn-' + j).mask(options.filterColumns[j].data);
					} else if (options.filterColumns[j].type == 'string') {
						options.o.find('#filterColumn-' + j).mask(options.filterColumns[j].data);
					} else if (options.filterColumns[j].type == 'numeric') 
						options.o.find('#filterColumn-' + j).unbind('keypress.ZcoreGrid.FilterColumn').bind('keypress.ZcoreGrid.FilterColumn', function(event) {
							var item = $(this).attr('id').split('-')[1],
								maskValue = options.filterColumns[item].data;
							//--> prevent A-Z and a-z
							if ((event.which >= 65 && event.which <= 90) ||(event.which >= 97 && event.which <= 122)) {
								event.preventDefault();
								event.stopPropagation();
								return false;
							};
							if (!(event.which >= 48 && event.which <= 57 || event.which == 46)) {
								if ($.inArray(event.keyCode, [46, 8, 9, 27, 13, 33, 34, 35, 36, 37, 38, 39, 40, 110, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 190]) !== -1) {
									return;
								}
								if (event.ctrlKey && (event.which == 99 /* copy */ || event.which == 118 /* paste */)) {
									return;
								}
								event.preventDefault();
								return;
							};							
							if ("1234567890.".indexOf(String.fromCharCode(event.charCode)) != -1) {
						    	//--> just press one '.'	    	
						    	if (event.which == 46) {
						    		if (maskValue.indexOf('.') == -1 || $(this).val().indexOf('.') != -1) 
										return false;
									else
										return true;	
								}
								//--> for '.' do nothing			
								if ($(this).val().indexOf('.') == $(this).val().length - 1)
									return true;																	 
								//--> parse it 
								var decimalFormat = new DecimalFormat(maskValue),
									val = decimalFormat.formatBack($(this).val());
								$(this).val(decimalFormat.format(val + String.fromCharCode(event.which)));														
								return false;
							}							
					});
				}
				//--> set event filter column press
				options.o.find('#filterColumn-' + j).unbind('keypress.ZcoreGrid.FilterColumnPress').bind('keypress.ZcoreGrid.FilterColumnPress', function(event) {
					if (ZcoreGridPlugin.isAjaxStart(options)) {
						event.preventDefault();
						return false;
					}
			    	switch (event.keyCode) {					
						case $.ui.keyCode.TAB:
						case $.ui.keyCode.DOWN:
						case $.ui.keyCode.END:
						case $.ui.keyCode.HOME:
						case $.ui.keyCode.PAGE_DOWN://case $.ui.keyCode.LEFT:
						case $.ui.keyCode.PAGE_UP://case $.ui.keyCode.RIGHT:
						case $.ui.keyCode.UP:	
							break;	
						case $.ui.keyCode.ENTER:
				    		event.stopPropagation();
							options.activePage = 1;
							ZcoreGridPlugin.setFilterColumnsValue(options);
							options.data = ZcoreGridPlugin.clientableData(options);
							options.activeFocus = $(this).attr('id');
							ZcoreGridPlugin.refreshGrid(options);
							break;
						default:		    	
					    	if (!ZcoreGridPlugin.isAjaxStart(options)) {
					    		var item = $(this).attr('id').split('-')[1];
					    		if (options.filterColumns && options.filterColumns[item]) 
					    			if (options.filterColumns[item].type != 'numeric')
					    				return;
					    		event.stopPropagation();
								clearTimeout(options.filterAjax[$(this).attr('id')]);
								options.filterAjax[$(this).attr('id')] = setTimeout(function(id) {
									options.activePage = 1; 		
									ZcoreGridPlugin.setFilterColumnsValue(options);
									options.data = ZcoreGridPlugin.clientableData(options);
									options.activeFocus = id;
									ZcoreGridPlugin.refreshGrid(options);
								}, 500, $(this).attr('id'));
						    }
						break;
					}					
			    });  
				
			} 
		},		
		
		/*
		 * set sort client event
		 */
		setEventSortIcon : function(options) {
			for (var i = 0; i < options.cols.length; i++) {
				options.o.find('#sortClientItem-' + i).click(function() {
					if (!ZcoreGridPlugin.isAjaxStart(options)) {						
						options.o.find('.ZcoreGridDivBody').find("table thead tr:first")
							.find('span[id^="sortClientItem"]').not($(this))
								.attr('class', "ZcoreGridBGIcon").attr('title', "");
				    	var sortClientItem = 'sortClientItem';
				    	options.sortClientItem = parseInt($(this).attr('id').substr(sortClientItem.length + 1, $(this).attr('id').length - sortClientItem.length));				    	
				    	if ($(this).hasClass("ZcoreGridASCIcon")) {
							options.sortClientItemSort = 'desc';
							$(this)
								.attr('class', "ZcoreGridDSCIcon")
								.attr('title', options.message.get(options.locale, 'desc'));
				    	} else if ($(this).hasClass("ZcoreGridDSCIcon")) {
							options.sortClientItemSort = '';
							options.sortClientItem = '';
							$(this)
								.attr('class', "ZcoreGridBGIcon")
								.attr('title', "");
				    	} else {
							options.sortClientItemSort = 'asc';
							$(this)
								.attr('class', "ZcoreGridASCIcon")
								.attr('title', options.message.get(options.locale, 'asc'));	
				    	}
				    	if (options.clientable) {
				    		options.data = ZcoreGridPlugin.clientableData(options);
				    	}
						ZcoreGridPlugin.refreshGrid(options);
					}
			    });  
			}
		},		
		
		/*
		 * set order by event
		 */   
		setEventOrderIcon : function(options) {
			for (var i = 0; i < options.cols.length; i++) {    
				options.o.find('#orderByItem-' + options.cols[i]).click(function() {
					if (!ZcoreGridPlugin.isAjaxStart(options)) {						
						options.o.find('.ZcoreGridDivBody').find("table thead tr:first")
							.find('span[id^="orderByItem"]').not($(this))
								.attr('class', "ZcoreGridBGIcon").attr('title', "");
						var orderByItem = 'orderByItem';
				    	options.orderByItem = $(this).attr('id').substr(orderByItem.length + 1, $(this).attr('id').length - orderByItem.length);
				    	if ($(this).hasClass("ZcoreGridASCIcon")) {
							options.orderByItemSort = 'desc';
							$(this)
								.attr('class', "ZcoreGridDSCIcon")
								.attr('title', options.message.get(options.locale, 'desc'));
				    	} else if ($(this).hasClass("ZcoreGridDSCIcon")) {
							options.orderByItemSort = '';
							options.orderByItem = '';
							$(this)
								.attr('class', "ZcoreGridBGIcon")
								.attr('title', "");
				    	} else {
							options.orderByItemSort = 'asc';
							$(this)
								.attr('class', "ZcoreGridASCIcon")
								.attr('title', options.message.get(options.locale, 'asc'));	
				    	}					
				    	options.data = ZcoreGridPlugin.clientableData(options);
						ZcoreGridPlugin.refreshGrid(options);						
					}
			    });    
			} 
		},
		
		/*
		 * setEventBodyIcon
		 */
		setEventBodyIcon : function(options) {
			options.o.find('table tbody .ZcoreGridBodyIcon').children()
				.unbind()
				.click(function(event) {
						if (!ZcoreGridPlugin.isAjaxStart(options)) {
							//--> set hover
							options.o.find('#div-body tbody tr').removeClass("ZcoreGridHoverRow");
							$(this).closest('tr').addClass("ZcoreGridHoverRow");
							//--> callback functions							
							var closest = $(this).closest('tr'),
								res = ZcoreGridPlugin.getRowData(closest, true);
							res = (res.length !== 0) ? res : undefined;
							//--> callback
							if ($(this).is('span') && !$(this).hasClass('ui-state-disabled')) {			
								options[$(this).attr('id') + 'Callback'](res, closest);
							} else {
								for (var i = 0; i < options.iconCallback.length; i++) {
									if (options.iconCallback[i].name == $(this).attr('id') && !$(this).hasClass('ui-state-disabled')) {
										options.iconCallback[i].action(res, closest);
										break;
									}
								}
							}
						}
						event.stopPropagation();
					});	
		},
		
		/*
		 * isAllRecordSelected
		 */
		isAllRecordSelected : function (options) {
			var res = true && options.o.find('table tbody tr:visible').length > 0;
			options.o.find('table tbody tr').each(function() {
				if ($(this).find('td:nth-child(3)').text().replace(/^\s+/, "") != '') {
					if (!$(this).find('td:nth-child(2) input').is(':checked')) {
						res = false;
					}
				}
			});
			return res;
		},
		
		/*
		 * multiSelectProc
		 */
		multiSelectProc : function (options, last) {
			var first = (ZcoreGridPlugin.getSelectedRowsId(options.o)[0] == undefined) ? last : ZcoreGridPlugin.getSelectedRowsId(options.o)[0];
			options.o.find('table tbody tr').each(function() {
				if ($(this).hasClass('ZcoreGridSelectedRow')) {
					$(this).removeClass("ZcoreGridSelectedRow");
					$(this).find('td:nth-child(2) input').prop('checked', false);
				}
			});		
			var firstRow, lastRow;
			if (first <= last) {
				firstRow = first;
				lastRow = last;
			} else {
				firstRow = last;
				lastRow = first;
			}					
			for (var item = firstRow; item <= lastRow; item++) {
				options.o.find('#row-' + item).addClass('ZcoreGridSelectedRow');	
				options.o.find('#row-' + item).find('td:nth-child(2) input').prop('checked', true);
			}	
			if (ZcoreGridPlugin.isAllRecordSelected(options))
				options.o.find('#checkbox-input').prop('checked', true);	
			else
				options.o.find('#checkbox-input').prop('checked', false);						
		},
		
		/*
		 * can row hover
		 */
		canRowHover : function(options, objRow) {
			/*var colStr = '';
			if (options.multiSelected)						
				colStr = objRow.find('td:nth-child(3)').text().replace(/^\s+/, "");
			else
				colStr = objRow.find('td:nth-child(2)').text().replace(/^\s+/, "");
			if ((colStr == '') || (objRow.hasClass('ZcoreGridLastRow'))) {
				if (objRow.find('td:nth-child(2) input').is(':checked')) {
					objRow.find('td:nth-child(2) input').prop('checked', false);
				}
				return false;
			}
			return true;*/
			if (objRow.hasClass('ZcoreGridLastRow')) {
				return false;
			}
			return objRow.is(':visible');
		},
 		
		/*
		 * set event of row click
		 */
		setEventRowClick : function(options) {
			options.o.find('#div-body table tbody tr').click(function(event) {
				if (!ZcoreGridPlugin.isAjaxStart(options)) {
					options.o.focus();
					if (!ZcoreGridPlugin.canRowHover(options, $(this)))
						return;
					//--> for hover
					options.o.find('#div-body table tbody tr').each(function() {
							$(this).removeClass("ZcoreGridHoverRow");
					});
					$(this).addClass("ZcoreGridHoverRow");
					//--> multi select
					if (options.multiSelected) {
						if ($(this).hasClass('ZcoreGridSelectedRow')) {
							if (event.shiftKey) {
								//ZcoreGridPlugin.multiSelectProc(options,  parseInt($(this).attr('id').split('-')[1]));								
							} else {
								$(this).removeClass("ZcoreGridSelectedRow");
								$(this).find('td:nth-child(2) input').prop('checked', false);
								
								//--> set DSV
								var 
									ds = options.o.data('ZcoreGrid-DSV'),
									removeItem = ZcoreGridPlugin.getRowData($(this), true);
								ds = $.grep(ds, function(value) {
									return value[0] != removeItem[0]; 
								});
								options.o.data('ZcoreGrid-DSV', ds);
								
								//--> set DS 								
								ds = options.o.data('ZcoreGrid-DS');
								removeItem = ZcoreGridPlugin.getRowData($(this), false);
								ds = $.grep(ds, function(value) {
									return value[0] != removeItem[0]; 
								});
								options.o.data('ZcoreGrid-DS', ds);
							}
						} else {
							if (event.shiftKey) {
								//ZcoreGridPlugin.multiSelectProc(options,  parseInt($(this).attr('id').split('-')[1]));
							} else {
								$(this).addClass("ZcoreGridSelectedRow");
								$(this).find('td:nth-child(2) input').prop('checked', true);	
								
								//--> set DSV
								var 
									ds = options.o.data('ZcoreGrid-DSV'),
									removeItem = ZcoreGridPlugin.getRowData($(this), true);
								ds = $.grep(ds, function(value) {
									return value[0] != removeItem[0]; 
								});
								ds.push(removeItem);
								options.o.data('ZcoreGrid-DSV', ds);
								
								//--> set DS 								
								ds = options.o.data('ZcoreGrid-DS');
								removeItem = ZcoreGridPlugin.getRowData($(this), false);
								ds = $.grep(ds, function(value) {
									return value[0] != removeItem[0]; 
								});
								ds.push(removeItem);
								options.o.data('ZcoreGrid-DS', ds);								
							}
						}
						if (ZcoreGridPlugin.isAllRecordSelected(options))
							options.o.find('#checkbox-input').prop('checked', true);	
						else
							options.o.find('#checkbox-input').prop('checked', false);
					} else {								
						options.o.find('#div-body table tbody tr').each(function() {
							if ($(this).hasClass('ZcoreGridSelectedRow')) {
								$(this).removeClass("ZcoreGridSelectedRow");
							}
						});
						$(this).addClass("ZcoreGridSelectedRow");
							
						//--> set DSV
						options.o.data('ZcoreGrid-DSV', [ZcoreGridPlugin.getRowData($(this), true)]);
							
						//--> set DS
						options.o.data('ZcoreGrid-DS', [ZcoreGridPlugin.getRowData($(this), false)]);
					}
					//--> rowClickCallback
					if ($.isFunction(options.rowClickCallback)) {
						options.rowClickCallback();
					}
				}
			});
		},	
		
		/*
		 * set event of row double click
		 */
		setEventRowDblClick : function(options) {
			options.o.find('#div-body table tbody tr').dblclick(function(event) {
				if (!ZcoreGridPlugin.isAjaxStart(options)) {
					if (!ZcoreGridPlugin.canRowHover(options, $(this)))
						return;
					//--> rowDblClickCallback
					if ($.isFunction(options.rowDblClickCallback)) {
						options.rowDblClickCallback();
					}
				}
			});
		},
		
		/*
		 * set event check box
		 */
		setEventCheckBox : function(options) {
			options.o.find('#checkbox-input').click(function(event) {
				if (options.data === undefined) {
					event.preventDefault();
					return;
				}
				if (!ZcoreGridPlugin.isAjaxStart(options)) {
					options.o.focus();
					if (options.multiSelected) {
						if ($(this).is(':checked')) {																	
							options.o.find('#div-body table tbody tr').each(function() {
								//if ($(this).find('td:nth-child(3)').text().replace(/^\s+/, "") != '') {
								if ($(this).is(':visible')) {
									$(this).addClass("ZcoreGridSelectedRow");
									$(this).find('td:nth-child(2) input').prop('checked', true);
									
									//--> set DSV
									var 
										ds = options.o.data('ZcoreGrid-DSV'),
										removeItem = ZcoreGridPlugin.getRowData($(this), true);
									ds = $.grep(ds, function(value) {
										return value[0] != removeItem[0]; 
									});
									ds.push(removeItem);
									options.o.data('ZcoreGrid-DSV', ds);
									
									//--> set DS 								
									ds = options.o.data('ZcoreGrid-DS');
									removeItem = ZcoreGridPlugin.getRowData($(this), false);
									ds = $.grep(ds, function(value) {
										return value[0] != removeItem[0]; 
									});
									ds.push(removeItem);
									options.o.data('ZcoreGrid-DS', ds);										
								}
							});
						} else {
							options.o.find('#div-body table tbody tr').each(function() {
								$(this).removeClass("ZcoreGridSelectedRow");
								$(this).find('td:nth-child(2) input').prop('checked', false);	
								
								//--> set DSV
								var 
									ds = options.o.data('ZcoreGrid-DSV'),
									removeItem = ZcoreGridPlugin.getRowData($(this), true);
								ds = $.grep(ds, function(value) {
									return value[0] != removeItem[0]; 
								});
								options.o.data('ZcoreGrid-DSV', ds);
								
								//--> set DS 								
								ds = options.o.data('ZcoreGrid-DS');
								removeItem = ZcoreGridPlugin.getRowData($(this), false);
								ds = $.grep(ds, function(value) {
									return value[0] != removeItem[0]; 
								});
								options.o.data('ZcoreGrid-DS', ds);								
							});							
						}
						//--> rowAllClickCallback
						if ($.isFunction(options.rowAllClickCallback)) {
							options.rowAllClickCallback($(this).is(':checked'));
						}
					}
				}	
			});
		},
				
		/*
		 * set first icon event
		 */
		setEventFirstIcon : function(options) {
			if (options.activePage > 1) {
				options.o.find('#first').click(function(event, func) {	
					if (!ZcoreGridPlugin.isAjaxStart(options)) {
						options.activePage = 1;
						options.data = ZcoreGridPlugin.clientableData(options);
						if ($.isFunction(func))
							options.onSuccess = func;
						ZcoreGridPlugin.refreshGrid(options);
					}
				});
			}
		},
		
		/*
		 * set prev icon event
		 */
		setEventPrevIcon : function(options) {
			if (options.activePage > 1) {
				options.o.find('#prev').click(function(event, func) {
					if (!ZcoreGridPlugin.isAjaxStart(options)) {
						options.activePage = (parseInt(options.o.find('#activePage').val()) || 0) - 1;
						options.data = ZcoreGridPlugin.clientableData(options);
						if ($.isFunction(func))
							options.onSuccess = func;
						ZcoreGridPlugin.refreshGrid(options);
					}
				});
			}
		},	
		
		/*
		 * set input text event
		 */
		setEventInputIcon : function(options) {
			options.o.find('#activePage').unbind('keypress.ZcoreGrid.ActivePage').bind('keypress.ZcoreGrid.ActivePage', function(event) {		    	
			    if (event.which === 13) { 
					if (!ZcoreGridPlugin.isAjaxStart(options)) {
						options.activeFocus = 'activePage';
						options.activePage = parseInt(options.o.find('#activePage').val()) || 0,			
						options.data = ZcoreGridPlugin.clientableData(options);
						ZcoreGridPlugin.refreshGrid(options);
					}
			    } else {
			    	event.stopPropagation();
			    }           	    	         
			});
		},	
		
		/*
		 * set next icon event
		 */
		setEventNextIcon : function(options) {
		    if (options.activePage < options.totalPage) {
		    	options.o.find('#next').click(function(event, func) {
					if (!ZcoreGridPlugin.isAjaxStart(options)) {			
						options.activePage = (parseInt(options.o.find('#activePage').val()) || 0) + 1;
						options.data = ZcoreGridPlugin.clientableData(options);
						if ($.isFunction(func))
							options.onSuccess = func;
						ZcoreGridPlugin.refreshGrid(options);
					}
				}); 
		    }
		},
		
		/*
		 * set last icon event
		 */
		setEventLastIcon : function(options) {
		    if (options.activePage < options.totalPage) {
		    	options.o.find('#last').click(function(event, func) {
					if (!ZcoreGridPlugin.isAjaxStart(options)) {
						options.activePage = options.totalPage;
						options.data = ZcoreGridPlugin.clientableData(options);
						if ($.isFunction(func))
							options.onSuccess = func;						
						ZcoreGridPlugin.refreshGrid(options);
					}
				});  
		    }
		},
		
		/*
		 * set event limit page items
		 */
		setEventLimitPageItems : function(options) {
			options.o.find('#limitPageItems').change(function() {		    	
				if (!ZcoreGridPlugin.isAjaxStart(options)) {
					options.activeFocus = 'activePage';
					options.activePage = parseInt(options.o.find('#activePage').val()) || 0,
					options.limitPage = $(this).val();
					options.data = ZcoreGridPlugin.clientableData(options);
					ZcoreGridPlugin.refreshGrid(options);
				}
			});
		},		
		
	
		/*
		 * setEventTrigger
		 */
		setEventTrigger : function(options) {
			options.o.unbind('keypress.ZcoreGrid').bind('keypress.ZcoreGrid', function(event) {
				switch (event.keyCode) {
					//--> end
					case 35:
						if ((parseInt($(this).find('#activePage').val()) || 0) < options.totalPage)						
							$(this).find('#last').trigger('click');
						event.stopPropagation();
						event.preventDefault();
						break;					
					//--> home
					case 36:
						if ((parseInt($(this).find('#activePage').val()) || 0) > 1) 						
							$(this).find('#first').trigger('click');
						event.stopPropagation();
						event.preventDefault();
						break;							
					//--> left	
					case $.ui.keyCode.PAGE_UP: //case 37:
						if ((parseInt($(this).find('#activePage').val()) || 0) < options.totalPage) 						
							$(this).find('#next').trigger('click');						
						/*if (options.direction === 'rtl') {
							if ($(this).find('#activePage').val() < options.totalPage) 						
								$(this).find('#next').trigger('click');
						} else {
							if ($(this).find('#activePage').val() > 1) 							
								$(this).find('#prev').trigger('click');							
						}*/
						event.stopPropagation();
						event.preventDefault();
						break;	
					//--> up	
					case 38:
						var ids = parseInt(options.o.ZcoreGrid('getHoverRowsId')) + 1;
						if (ids > 1) {
							$(this).find('#div-body tbody tr:nth-child(' + ids + ')').removeClass("ZcoreGridHoverRow");
							ids -= 1;
							$(this).find('#div-body tbody tr:nth-child(' + ids + ')').addClass("ZcoreGridHoverRow");
							if (options.hoverCallback)
								options.hoverCallback();
						}
						event.stopPropagation();
						event.preventDefault();
						break;	
					//--> right	
					case $.ui.keyCode.PAGE_DOWN: //case 39:
						/*if (options.direction === 'rtl') {
							if ($(this).find('#activePage').val() > 1) 							
								$(this).find('#prev').trigger('click');
						} else {
							if ($(this).find('#activePage').val() < options.totalPage) 						
								$(this).find('#next').trigger('click');							
						}*/
						if ((parseInt($(this).find('#activePage').val()) || 0) > 1) 							
							$(this).find('#prev').trigger('click');
						event.stopPropagation();
						event.preventDefault();
						break;	
					//--> down	
					case 40:
						var ids = parseInt(options.o.ZcoreGrid('getHoverRowsId') + 1);
						if (ids < options.limitPage) {
							var obj = $(this).find(' tbody tr:nth-child(' + (ids + 1) + ')');
							if (ZcoreGridPlugin.canRowHover(options, obj)) {
								$(this).find('#div-body tbody tr:nth-child(' + ids + ')').removeClass("ZcoreGridHoverRow");
								ids += 1;
								$(this).find('#div-body tbody tr:nth-child(' + ids + ')').addClass("ZcoreGridHoverRow");
								if (options.hoverCallback)
									options.hoverCallback();
							}
						}
						event.stopPropagation();
						event.preventDefault();
						break;	
					//--> insert
					case 45:
						var ids = parseInt(options.o.ZcoreGrid('getHoverRowsId') + 1);
						$(this).find('#div-body table tbody tr:nth-child(' + ids + ')').trigger('click');
						event.stopPropagation();
						event.preventDefault();
						break;						
				}
			});
		},
		
		/*
		 * event grid
		 */
		eventGrid : function(type, options) {
			switch (type) {
				case 100:
					ZcoreGridPlugin.setEventHeaderTitle(options);
					ZcoreGridPlugin.setEventRefreshIcon(options);					
					ZcoreGridPlugin.setEventPersistIcon(options);
					ZcoreGridPlugin.setEventMergeIcon(options);
					ZcoreGridPlugin.setEventRemoveIcon(options);
					ZcoreGridPlugin.setEventViewIcon(options);
					ZcoreGridPlugin.setEventActionIcon(options);					
					ZcoreGridPlugin.setEventFilterIcon(options);
					ZcoreGridPlugin.setEventFilterColumn(options);
					ZcoreGridPlugin.setEventSortIcon(options);
					ZcoreGridPlugin.setEventOrderIcon(options);
					ZcoreGridPlugin.setEventRowClick(options);
					ZcoreGridPlugin.setEventRowDblClick(options);
					ZcoreGridPlugin.setEventCheckBox(options);					
					ZcoreGridPlugin.setEventFirstIcon(options);
					ZcoreGridPlugin.setEventPrevIcon(options);
					ZcoreGridPlugin.setEventInputIcon(options);
					ZcoreGridPlugin.setEventLimitPageItems(options);
					ZcoreGridPlugin.setEventNextIcon(options);
					ZcoreGridPlugin.setEventLastIcon(options);
					ZcoreGridPlugin.setEventTrigger(options);
					ZcoreGridPlugin.setEventBodyIcon(options);
					break;
				case 80:
					ZcoreGridPlugin.setEventRowClick(options);
					ZcoreGridPlugin.setEventRowDblClick(options);
					ZcoreGridPlugin.setEventCheckBox(options);
					ZcoreGridPlugin.setEventFirstIcon(options);
					ZcoreGridPlugin.setEventPrevIcon(options);
					ZcoreGridPlugin.setEventInputIcon(options);
					ZcoreGridPlugin.setEventLimitPageItems(options);
					ZcoreGridPlugin.setEventNextIcon(options);
					ZcoreGridPlugin.setEventLastIcon(options);
					ZcoreGridPlugin.setEventBodyIcon(options);
					break;
				case 50:
					ZcoreGridPlugin.setEventRowClick(options);
					ZcoreGridPlugin.setEventRowDblClick(options);
					ZcoreGridPlugin.setEventCheckBox(options);
					ZcoreGridPlugin.setEventBodyIcon(options);					
					break;
			}	
		},		
		
		/*
		 * draw grid
		 */
		drawGrid : function(type, options) {
			var gridTable = new Array();
			switch (type) {
				case 100:
					var width = 0;
					if (options.colsWidth !== undefined) {
						for (var i = 0; i < options.cols.length; i++) {   
							width += parseInt((options.colsWidth[i] !== undefined) ? options.colsWidth[i] : 0);
						}
					}
					//--> head-title
					if (options.headerTitle !== '') {
						gridTable.push('<div tabindex="-1" id="div-head-title" class="ZcoreGridDivHeader ui-widget ui-state-default ui-corner-tl ui-corner-tr" style="padding: 4px;border-width: 0 0 1px 0; background: none;">');
						gridTable.push('<table width="100%" height="100%">');
						gridTable.push('<thead>');
						var bodyIcon = ZcoreGridPlugin.drawHeaderTitle(gridTable, options);
						gridTable.push('</thead>');					
						gridTable.push('</table>');
						gridTable.push('</div>');	
					}
					gridTable.push('<div id="div-container">');
					//--> head
					gridTable.push('<div tabindex="-1" id="div-head" class="ZcoreGridDivHeader ui-widget ui-corner-tl ui-corner-tr" style="padding: 4px;' + (options.containerHeaderShow ? '' : 'display:none') + '">');
					gridTable.push('<table width="100%" height="100%">');
					gridTable.push('<thead>');
					var bodyIcon = ZcoreGridPlugin.drawHeaderIcon(gridTable, options);
					gridTable.push('</thead>');					
					gridTable.push('</table>');
					gridTable.push('</div>');
					//--> body
					gridTable.push(('<div tabindex="-1" id="div-body" class="ZcoreGridDivBody ui-widget ui-widget-content" style="overflow:auto;height:' + (options.height == 'auto' ? 'auto' : (options.height) + 'px') + ';">'));
					gridTable.push('<table ' + ('width="' + width + '"') + '>');
					gridTable.push('<thead>');	
					ZcoreGridPlugin.drawHeader(gridTable, options, bodyIcon);
					gridTable.push('</thead>');					
					gridTable.push('<tbody>');
					ZcoreGridPlugin.drawBody(gridTable, options);
					gridTable.push('</tbody>');
					gridTable.push('</table>');
					gridTable.push('</div>');
					//--> foot		
					gridTable.push('<div tabindex="-1" id="div-foot" class="ZcoreGridDivFooter ui-widget ui-corner-bl ui-corner-br" style="padding: 1px;">');
					gridTable.push('<table width="100%" height="100%">');
					gridTable.push('<tfoot>'); 
					ZcoreGridPlugin.drawFooter(gridTable, options);
					gridTable.push('</tfoot>'); 	
					gridTable.push('</table>');
					gridTable.push('</div>');
					gridTable.push('</div>');
					//--> render
					options.o.html(gridTable.join(''));
					options.o.show(); 
					break;
				case 80:
					options.o.hide();
					//--> tbody
					gridTable = [];
					ZcoreGridPlugin.drawBody(gridTable, options);
					options.o.find('#div-body table tbody').html(gridTable.join(''));
					//--> tfoot
					gridTable = [];
					ZcoreGridPlugin.drawFooter(gridTable, options);
					options.o.find('#div-foot table tfoot').html(gridTable.join(''));
					options.o.show();						   	
					break;
				case 50:
					options.o.hide();
					//--> tbody
					gridTable = [];
					ZcoreGridPlugin.drawBody(gridTable, options);
					options.o.find('#div-body table tbody').html(gridTable.join(''));	
					options.o.show();	
					break;
			}
			//--> draw last col
			ZcoreGridPlugin.drawLastCol(options);
			//--> set event
			ZcoreGridPlugin.eventGrid(type, options);	
			//--> set data selected
			var allColumns = options.o.find('[id^="row-"]');
			$.each(options.o.data('ZcoreGrid-DSV'), function(index, value) {
				allColumns.each(function() {
					if (ZcoreGridPlugin.getRowData($(this), true)[0] == value[0]) { 
						$(this).addClass('ZcoreGridSelectedRow');
						if (options.multiSelected) {
							$(this).find('td:nth-child(2) input').prop('checked', true);
						}
					}				
				});
			});
			if (ZcoreGridPlugin.isAllRecordSelected(options))
				options.o.find('#checkbox-input').prop('checked', true);	
			else
				options.o.find('#checkbox-input').prop('checked', false);
		},
		
		/*
		 * initialize grid
		 */
		initializeGrid : function(options) {
			options.refreshable = (options.refreshable === undefined) ? "SERVER" : options.refreshable;
			options.clientable = (options.clientable === undefined) ? false : options.clientable;
			options.type = options.type || 'GET';
			options.id = options.id;
			options.height = options.height;  
			options.width = options.width; 
			options.headerTitle = options.headerTitle;
			options.style = options.style;
			options.colsStyle = options.colsStyle;
			options.colsWidth = options.colsWidth;
			options.colsType = options.colsType;
			options.cols = options.cols;
			options.titles = options.titles;
			options.source = options.source;
			options.sourceData = (options.sourceData !== undefined) ? options.sourceData : '';
			options.limitPageItems = options.limitPageItems.length !== 0 ? options.limitPageItems : [10];
			options.limitPage = (options.limitPage !== undefined) ? options.limitPage : options.limitPageItems[0];
			options.totalCount = (options.totalCount != undefined) ? options.totalCount : 0;
			options.totalPage = Math.ceil(options.totalCount / ((options.limitPage == 0) ? options.totalCount : options.limitPage));	
			options.activePage = options.activePage !== undefined ? options.activePage : 1;
			options.activePage = options.activePage <= 1 ? 1 : options.activePage;
			options.activePage = options.activePage >= options.totalPage ? options.totalPage : options.activePage;  			
			options.multiSelected = (options.multiSelected !== undefined) ? options.multiSelected : false;
			options.sortClientItem = options.sortClientItem;
			options.sortClientItemSort = (options.sortClientItemSort !== undefined) ? options.sortClientItemSort : '';
			options.orderByItem = (options.orderByItem !== undefined) ? options.orderByItem : '';
			options.orderByItemSort = (options.orderByItemSort !== undefined) ? options.orderByItemSort : '';
			options.filterRowVisible = (options.filterRowVisible !== undefined) ? options.filterRowVisible: false;		
			options.iconCallback = (options.iconCallback) ? options.iconCallback : [];
			options.persistCallback = options.persistCallback;
			options.mergeCallback = options.mergeCallback;
			options.removeCallback = options.removeCallback;
			options.viewCallback = options.viewCallback;
			options.rowClickCallback = (options.rowClickCallback) ? options.rowClickCallback : undefined;
			options.rowAllClickCallback = (options.rowAllClickCallback) ? options.rowAllClickCallback : undefined;
			options.refreshCallback = (options.refreshCallback) ? options.refreshCallback : undefined;
			options.hoverCallback = (options.hoverCallback) ? options.hoverCallback : undefined;
			options.lastRow = options.lastRow;
			options.hintsLength = options.hintsLength;
			options.locale = (options.locale !== undefined) ? options.locale : 'fa';
			options.direction = (options.direction !== undefined) ? options.direction : 'rtl';
			options.async = (options.async !== undefined) ? options.async : true;
			options.centeralize = options.centeralize;
			if (options.filterColumnsValue === undefined) {
				options.filterColumnsValue = new Array();
				for (var i = 0; i < options.cols.length; i++) {
					options.filterColumnsValue[i] = '';	
				}
			} else
				options.filterColumnsValue = options.filterColumnsValue;			
			if (options.filterColumnsCondition === undefined) {
				options.filterColumnsCondition = new Array();
				for (var i = 0; i < options.cols.length; i++) {
					options.filterColumnsCondition[i] = '';	
				}
			} else
				options.filterColumnsCondition = options.filterColumnsCondition;	
			options.data = options.data;
			options.filterColumns = options.filterColumns;
			options.filterAjax = new Array();
			options.activeFocus = (options.activeFocus) ? options.activeFocus : undefined;	
			options.refreshShow = (options.refreshShow !== undefined) ? options.refreshShow : true;
			options.filterShow = (options.filterShow !== undefined) ? options.filterShow : true;
			options.sortShow = (options.sortShow !== undefined) ? options.sortShow : true;
			options.containerHeaderShow = (options.containerHeaderShow !== undefined) ? options.containerHeaderShow : true;
			options.orderShow = (options.orderShow !== undefined) ? options.orderShow : true;
			options.filtersShow = (options.filtersShow !== undefined) ? options.filtersShow : true;
			options.sortsShow = (options.sortsShow !== undefined) ? options.sortsShow : true;
			options.ordersShow = (options.ordersShow !== undefined) ? options.ordersShow : true;			
			options.persist = options.persist;
			options.merge = options.merge;
			options.remove = options.remove;
			options.view = options.view;
			options.showNumber = (options.showNumber !== undefined) ? options.showNumber : 'page';
			options.filterShow = options.filterShow;
			options.orderShow = options.orderShow;				
		},
		
		/*
		 * on start ajax
		 */
		onStartAjax: function(options) {
			options.o.find('#refresh span').addClass('ZcoreGridRefreshIcon');
			options.o.addClass('ZcoreGridDisable');
		},
		
		/*
		 * on stop ajax
		 */
		onStopAjax: function(options) {
			options.o.find('#refresh span').removeClass('ZcoreGridRefreshIcon');
			options.o.removeClass('ZcoreGridDisable');
		},
		
		/*
		 * refresh grid
		 */
		refreshGrid : function(options) {
			//--> checkbox input checked false if selected 
			options.o.find('#checkbox-input').prop('checked', false);	
			//--> initialize
			ZcoreGridPlugin.initializeGrid(options);
			//--> prepare from external javascript
			if ($.isFunction(options.prepare)) {
				options.sourceData = options.prepare(options);
			}
			if (options.data !== undefined) {
				ZcoreGridPlugin.drawGrid(80, options); 
    			if ($.isFunction(options.refreshCallback)) {		    				
    				options.refreshCallback(options.refreshable);
    			}
		    } else {    
		    	ZcoreGridPlugin.onStartAjax(options);
		    	/*
		    	 * ajax
		    	 */
		    	$.ajax({
		    		type: options.type,
		    		async: options.async(),
		  		  	url: options.source,
		  		  	data : $.extend({
			    		limitPage : options.clientable ? 0 : options.limitPage, //--> zero = get all data  
			  		  	activePage : options.activePage,
			  		  	orderByItem : (options.orderByItem.length != 0) ? options.orderByItem : undefined,
			  		  	orderByItemSort	: (options.orderByItem.length != 0 && options.orderByItemSort.length != 0) ? options.orderByItemSort : undefined,
			  		  	filterColumnsValue : (options.filterColumnsValue.toString().replace(new RegExp(',', 'g'), '').replace(new RegExp('undefined', 'g'), '').length != 0) ? options.filterColumnsValue.join(',').replace(new RegExp('undefined', 'g'), '') : undefined,
			  		  	filterColumnsCondition : (options.filterColumnsCondition.toString().replace(new RegExp(',', 'g'), '').replace(new RegExp('undefined', 'g'), '').length != 0) ? options.filterColumnsCondition.join(',').replace(new RegExp('undefined', 'g'), '') : undefined,			  		  				  		  			
			    	}, options.sourceData || {}),
		  		  	contentType: "application/x-www-form-urlencoded; charset=UTF-8",      
		  		  	dataType: "json",	
		  		  	error: function(jqXHR, textStatus, errorThrown) {
		        		ZcoreGridPlugin.onStopAjax(options);
		        		$.ajaxSettings.error(jqXHR, textStatus, errorThrown);
		  		  	},
		  		  	success: function(dataCallback) {
		        		ZcoreGridPlugin.onStopAjax(options);
		    			switch (dataCallback.type) {
				  	  		case 'grid':
				  	  			options.totalCount = dataCallback.totalCount;	
				  	  			options.totalPage = Math.ceil(options.totalCount / ((options.limitPage == 0) ? options.totalCount : options.limitPage));	
				  	  			if (options.totalPage > 0) {
				  	  				if (options.activePage === 0) {
				  	  					options.activePage = 1;
				  	  				}
				  	  			} 
				  	  			if (options.activePage > options.totalPage) {
				  	  				options.activePage = options.totalPage;	
				  	  			}
				  	  			options.data = dataCallback.data; 
				  	  			options.o.data("ZcoreGridData", options.data);
				  	  			ZcoreGridPlugin.drawGrid(80, options);	
				  	  			break;
				  	  		case 'exception':
				  	  			alert(dataCallback.value);
		    		  	  		break;
		        		}
		    			if ($.isFunction(options.refreshCallback)) {		    				
		    				options.refreshCallback(options.refreshable);
		    			}	    			
		  	  			if (options.activeFocus) {
		  	  				var v = options.o.find('#' + options.activeFocus).val();
		  	  				options.o.find('#' + options.activeFocus).focus().val('').val(v);		  	  				
		  	  				options.activeFocus = undefined;
		  	  			}	
		        	} 
		      	});				
		     }
		},
		
		/*
		 * getRowData 
		 */
		getRowData : function(o, valence) {
			var record = new Array();
			o.find('td').each(function() {
				if (!$(this).hasClass('td-hover')) {
					var val;
					if (valence && $(this).attr('valence'))
						val = $(this).attr('valence').replace(/^\s+/, "");
					else
						val = $(this).text().replace(/^\s+/, "");
					if ($(this).attr('type') === 'numeric') {
						val = Number(val);
					}
					record.push(val);
				}
			});        			        			        
			return record;
		},
				
		/*
		 * getSelectedRows 
		 */
		getSelectedRows : function(o, valence) {
			/*var res = new Array();				
			o.find('#div-body table tbody tr').each(function() {
				if ($(this).hasClass('ZcoreGridSelectedRow')) {       			        			        
					res.push(ZcoreGridPlugin.getRowData($(this), valence));
				}
			});*/
			var res = valence ? o.data('ZcoreGrid-DSV') : o.data('ZcoreGrid-DS');
			return (res.length !== 0) ? res : undefined;
		},
		
		/*
		 * getSelectedRowsId 
		 */
		getSelectedRowsId : function(o) {
			/*var res = new Array();				
			o.find('#div-body table tbody tr').each(function() {
				if ($(this).hasClass('ZcoreGridSelectedRow')) {
					res.push(parseInt($(this).attr('id').split('-')[1]));
				}
			});
			return (res.length !== 0) ? res : undefined;*/
			var res = new Array(),				
				ds = o.data('ZcoreGrid-DSV');
			for (var i in ds) {
				res.push(parseInt(ds[i][0]));
			}
			return (res.length !== 0) ? res : undefined;			
		},
		
		/*
		 * setSelectedRowsId 
		 */
		setSelectedRowsId : function(o, items) {
			var data = o.data('ZcoreGridData');
			if (!ZcoreGridPlugin.isAjaxStart({o : o}) && data !== undefined && data.length !== 0) {
				if (typeof items === 'boolean') {
					o.find('#checkbox-input').prop('checked', items).triggerHandler("click");
					return;
				}
				if (!(items instanceof Array)) {
					items = [items];
				}
				for (var item in items) 
					o.find('#div-body table tbody tr:nth-child(' + (items[item] + 1) + ')').trigger('click');
			}
		},		
		
		/*
		 * getHoverRows 
		 */
		getHoverRows : function(o, valence) {
			var res = new Array();				
			o.find('#div-body table tbody tr').each(function() {
				if ($(this).hasClass('ZcoreGridHoverRow')) {
					var record = new Array();
					$(this).find('td').each(function() {
						if (!$(this).hasClass('td-hover'))
							if ($(this).find('input').attr('type') != 'checkbox') {
								var val;
								if (valence && $(this).attr('valence'))
									val = $(this).attr('valence').replace(/^\s+/, "");
								else
									val = $(this).text().replace(/^\s+/, "");
								if ($(this).attr('type') === 'numeric') {
									val = Number(val);
								}
								record.push(val);
							}
					});        			        			        
					res.push(record);
				}
			});
			return (res.length !== 0) ? res : undefined;
		},		
		
		/*
		 * getHoverRowsId 
		 */
		getHoverRowsId : function(o) {
			var res = 0;				
			o.find('#div-body table tbody tr').each(function() {
				if ($(this).hasClass('ZcoreGridHoverRow')) {
					res = parseInt($(this).attr('id').split('-')[1]);
				}
			});
			return (res.length !== 0) ? res : undefined;
		},	
		
		/*
		 * getColumns 
		 */
		getColumns : function(o) {
			var res = new Array(),
				prefix = "td-";
			o.find('#div-body table thead tr:first td').each(function() {
				if ($(this).hasClass('td'))
					res.push($(this).attr('id').substr(prefix.length, $(this).attr('id').length - prefix.length));
			});
			return (res.length !== 0) ? res : undefined;
		},	
		
		/*
		 * getTitle 
		 */
		getTitle : function(o) {
			return o.find('#div-head table thead:first tr:first th b').html();
		},		
		
		/*
		 * getTotalCount 
		 */
		getTotalCount : function(o) {
			return o.find('#div-foot table tfoot:first tr td').find('#totalCount label').html();
		}
		
	});

	/*
	 * zcore grid
	 */
	$.fn.ZcoreGrid = function(action, options, params) {
		switch (action) {
			case 'show':
				if (options) {
					$(this).addClass('ZcoreGrid');
					options.o = $(this);
					$(this).data('ZcoreGridData', options.data || []);
					
					//--> set DSV
					$(this).data('ZcoreGrid-DSV', options.selectedDataValence || []);
					
					//--> set DS
					$(this).data('ZcoreGrid-DS', options.selectedData || []);
					
					ZcoreGridPlugin.initializeGrid(options);
					$(this).css('min-width', "380px").width(options.width);
					ZcoreGridPlugin.drawGrid(100, options);
					if (options.data !== undefined) { 				
		    			if ($.isFunction(options.refreshCallback)) {		    				
		    				options.refreshCallback(options.refreshable);
		    			}
					}
				}	
				if (options.refreshOnRender.toString() === 'true') {
					options.data = undefined;
					ZcoreGridPlugin.refreshGrid(options);
				}
				break;
			case 'getRowData':	
				return ZcoreGridPlugin.getRowData(options, params);			
				break;				
			case 'getSelectedRows':	
				return ZcoreGridPlugin.getSelectedRows($(this), options);			
				break;
			case 'getSelectedCols':
				var 
					rows = ZcoreGridPlugin.getSelectedRows($(this), params),
					res = [];
				for (var i = 0; i < rows.length; i++)
					res.push(rows[i][options]);
				return res;				
				break;				
			case 'getSelectedRowsId':
				return ZcoreGridPlugin.getSelectedRowsId($(this));
				break;
			case 'setSelectedRowsId':
				ZcoreGridPlugin.setSelectedRowsId($(this), options);
				break;
			case 'getHoverRows':		
				return ZcoreGridPlugin.getHoverRows($(this), options);
				break;	
			case 'getHoverCols':
				var 
					rows = ZcoreGridPlugin.getHoverRows($(this)),
					res = [];
				for (var i = 0; i < rows.length; i++)
					res.push(rows[i][options]);
				return res;				
				break;
			case 'getHoverRowsId':
				return ZcoreGridPlugin.getHoverRowsId($(this));
				break;				
			case 'refresh':	
				$(this).find('#refresh').triggerHandler('click', [options, undefined, true]);	
				break;				
			case 'getColumns':			
				return ZcoreGridPlugin.getColumns($(this));				
				break;	
			case 'getTitle':
				return ZcoreGridPlugin.getTitle($(this));				
				break;		
			case 'getTotalCount':	
				return ZcoreGridPlugin.getTotalCount($(this));				
				break;	
			case 'height' :
				$(this).find('.ZcoreGridDivBody').height(options - 48);
				break;
			case 'clientData' :
				if ($.isArray(options)) {
					$(this).data('ZcoreGridData', options);
					$(this).find('#refresh').triggerHandler('click', ["CLIENT_DATA", params || options.length, true]);
				} 
				return $(this).data('ZcoreGridData');
				break;
			default:
				break;
		}
	};
		
})(jQuery);