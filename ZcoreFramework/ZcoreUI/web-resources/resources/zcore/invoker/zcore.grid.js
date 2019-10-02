/**
 * 
 * @author Ali Karimizandi
 * @since 2012 
 *   
 */

(function($) {
	
	function ZcoreGridInvokerPlugin() {}
	
	$.extend(ZcoreGridInvokerPlugin, {		
		
		//--> static method
		grid : function(T, G, event) {
			var 
				O = {},
				P = T.params;
			//--> items
			O.id = P.id,
			O.message = $.ZAP.message;
			O.colsWidth = [];
			O.colsCaption = [];
			O.hintsLength = [];
			O.filtersShow = [];	
			O.sortsShow = [];
			O.ordersShow = [];	
			$.each(P.items, function(k, v) {
				O.colsWidth.push(v.width);
				O.colsCaption.push(v.caption || '');
				O.hintsLength.push(v.length || 0);
				O.filtersShow.push(v.filterShow == 'true');
				O.sortsShow.push(v.sortShow == 'true');
				O.ordersShow.push(v.orderShow == 'true');
				if (v.filter) {
					G[1][k] = {
						type : "select",
						client : v.filter
					}
				}
			});
			O.colsStyle = [];
			for (var i = 0; i < O.colsWidth.length; i++) {
				if (O.colsWidth[i] == 0)
					O.colsStyle.push('display: none;');
				else
					O.colsStyle.push('');
			}					
			//--> grid
			O.cols = G[0]; 
			O.titles = O.colsCaption; 
			O.filterColumns = G[1];
			if (G[2]) {
				O.data = G[2].data;
				O.totalCount = G[2].totalCount;
			}
			//--> set columns type from filter columns
			O.colsType = [];
			for (var i = 0; i < O.filterColumns.length; i++) {
				if (O.filterColumns[i]) {
					O.colsType.push(O.filterColumns[i].type);					
					//--> if have client then load from global
					if (O.filterColumns[i].client !== undefined) {
						O.filterColumns[i].data = $.ZAP.reverse($.ZAP.global(O.filterColumns[i].client)); 
					}
					//--> translate
					if (O.filterColumns[i].type === 'select') {
						for (var d in O.filterColumns[i].data) {
							O.filterColumns[i].data[d] = $.ZAP.message.translate('${' + O.filterColumns[i].data[d] + '}'); 
						}
					}
				} else {
					O.colsType.push(null);	
				}
			}	
			O.filterColumnsValue = P.filterColumnsValue.split(',');
			//--> others
			O.height = P.height || 'auto';
			O.width = P.width || 'auto';
			O.headerTitle = P.headerTitle;
			O.limitPageItems = P.limitPageItems === '' ? [] : P.limitPageItems.split(',');
			O.source = P.service;
			O.sourceData = undefined;
			O.locale = $.ZAP.locale.get();
			O.direction = T.orientation()[0];
			O.multiSelected = P.multiSelected;
			O.refreshOnRender = P.refreshOnRender;
			O.filterShow = P.filterShow;
			O.sortShow = P.sortShow;
			O.orderShow = P.orderShow;
			O.refreshShow = P.refreshShow;
			O.containerHeaderShow = P.containerHeaderShow;
			O.persist = P.persistItem;
			O.merge = P.mergeItem;
			O.remove = P.removeItem;
			O.view = P.viewItem;
			O.showNumber = P.showNumber;	
			O.orderByItem = P.orderByItem;
			O.orderByItemSort = P.orderByItemSort;
			O.clientable = P.clientable;
			O.prepare = $.proxy(function(options) { 
				return this.invoke('method', 'prepare', 'execute', [options]);
			}, T);
			O.async = $.proxy(function() { return this.async(); }, T);
			//--> events
			if (event) {
				O.iconCallback = P.iconCallback;
				O.persistCallback = $.proxy(function() { this.signal('persistClick', arguments); }, T);
				O.mergeCallback = $.proxy(function() { this.signal('mergeClick', arguments); }, T);
				O.removeCallback = $.proxy(function() { this.signal('removeClick', arguments); }, T);
				O.viewCallback = $.proxy(function() { this.signal('viewClick', arguments); }, T);
				O.rowClickCallback = $.proxy(function() { this.signal('rowClick', arguments); }, T);
				O.rowDblClickCallback = $.proxy(function() { this.signal('rowDblClick', arguments); }, T);
				O.rowAllClickCallback = $.proxy(function() { this.signal('rowAllClick', arguments); }, T);
				O.refreshCallback = $.proxy(function() { this.signal('refreshClick', arguments); return T;}, T);
				O.hoverCallback = $.proxy(function() { this.signal('hoverClick', arguments); }, T);
			}
			return O;
		}
				
	});

	/*
	 * register as jQuery function
	 */
	$.fn.ZcoreGridInvoker = function(key, T) {
		var options = {};
		switch (key) {
		case 'grid':
			options = ZcoreGridInvokerPlugin['grid'](T, T.params.data, true);
			break;
		case 'lovgrid':
			options = ZcoreGridInvokerPlugin['grid'](T, T.params.data, false);
			options.height = T.params.lovHeight;
			options.width = T.params.lovWidth;
			break;		 				
		default:
			break;
		}
		return $(this).addClass('ui-state-default ui-corner-all').ZcoreGrid('show', options);
	};
	
})(jQuery);