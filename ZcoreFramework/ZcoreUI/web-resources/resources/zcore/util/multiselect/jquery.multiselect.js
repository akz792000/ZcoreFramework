/*
 * jQuery MultiSelect UI Widget 1.10
 * Copyright (c) 2011 Eric Hynds
 *
 * http://www.erichynds.com/jquery/jquery-ui-multiselect-widget/
 *
 * Depends:
 *   - jQuery 1.4.2+
 *   - jQuery UI 1.8 widget factory
 *
 * Optional:
 *   - jQuery UI effects
 *   - jQuery UI position utility
 *
 * Dual licensed under the MIT and GPL licenses:
 *   http://www.opensource.org/licenses/mit-license.php
 *   http://www.gnu.org/licenses/gpl.html
 *
*/
(function($, undefined){

var multiselectID = 0;

$.widget("ech.multiselect", {
	
	// default options
	options: {
		header : true,
		height : 175,
		minWidth : 225,
		containerWidth : 0,
		classes : '',
		checkAllText : 'Check all',
		uncheckAllText : 'Uncheck all',
		noneSelectedText : 'Select options',
		selectedText : '# selected',
		selectedList : 0,
		show : '',
		hide : '',
		autoOpen : false,
		multiple : true,
		position : {},
		direction : 'ltr',
		container : $('body')
	},

	_create : function() {
		var 
			ZcoreMultiSelect = $('body').children('#ZcoreMultiSelect:first'),
			el = this.element.hide(),
			o = this.options;

		// zcore multi select
		if (ZcoreMultiSelect.length == 0) {
			ZcoreMultiSelect = $('<div>', { id : "ZcoreMultiSelect", 'class' : 'zui-global'});
			$('body').prepend(ZcoreMultiSelect);
		}
		
		this.speed = $.fx.speeds._default; // default speed for effects
		this._isOpen = false; // assume no

		// button
		this.button = $('<button>', {
			type : 'button',
			id : this.element.attr('id') + 'Btn',
			html : '<span class="ui-icon ui-icon-triangle-2-n-s"></span>',
			'class' : 'ui-multiselect ui-multiselect-button ui-widget ui-state-default ui-corner-all ui-multiselect-' + this.options.direction,
			title : el.attr('title'), 
			'aria-haspopup' : true, 
			tabindex : el.attr('tabindex') 
		}).insertAfter(el);
			
		// menu
		this.menu = $('<div>', {
			'class' : 'ui-multiselect-menu ui-widget ui-widget-content ui-corner-all ui-multiselect-menu-' + this.options.direction 
			+ ' ' + o.classes + (o.multiple ? '' : ' ui-multiselect-single')
		}).appendTo(ZcoreMultiSelect);
								
		// header		
		this.header = $('<div>', {
			'class' : 'ui-widget-header ui-corner-all ui-multiselect-header ui-helper-clearfix ui-multiselect-header-' + this.options.direction
		}).appendTo(this.menu);
		
		// button label
		this.buttonlabel = $('<div>', {
			'style' : 'white-space:nowrap; overflow:hidden;',
			html : o.noneSelectedText 
		}).appendTo(this.button);
			
		// headerLinkContainer
		this.headerLinkContainer = $('<ul>', {
			'class' : 'ui-helper-reset',
			html : function() {
				if (o.header === true) {
					return '<li><a class="ui-multiselect-all" href="#"><span class="ui-icon ui-icon-check"></span><span>' + o.checkAllText + '</span></a></li><li><a class="ui-multiselect-none" href="#"><span class="ui-icon ui-icon-closethick"></span><span>' + o.uncheckAllText + '</span></a></li>';
				} else if (typeof o.header === "string") {
					return '<li>' + o.header + '</li>';
				} else {
					return '';
				}
			}
		}).appendTo(this.header);
				
		// checkboxContainer
		this.checkboxContainer = $('<ul>', {
			'class' : 'ui-multiselect-checkboxes ui-helper-reset'
		}).appendTo(this.menu);
		
		// perform event bindings
		this._bindEvents();
		
		// build menu
		/*
		 * for performance we do not refresh menu of multiselect here
		 * this action invoke when set data or value in multiselect component
		 */
		
		//this.refresh(true);
		
	},
	
	_init : function() {
		if (this.options.header === false) {
			this.header.hide();
		}
		if (!this.options.multiple || !this.options.checkAllShow) {
			this.headerLinkContainer.find('.ui-multiselect-all').hide();
		}
		if (!this.options.uncheckAllShow) {
			this.headerLinkContainer.find('.ui-multiselect-none').hide();
		}
		if (this.options.autoOpen) {
			this.open();
		}
		if (this.element.is(':disabled')) {
			this.disable();
		}
	},
	
	refresh : function(init) {
		var 
			el = this.element,
			o = this.options,
			menu = this.menu,
			checkboxContainer = this.checkboxContainer,
			optgroups = [],
			html = [],
			id = el.attr('id') + multiselectID++; // unique ID for the label & option tags	
		
		
		// build items
		this.element.find('option').each(function(i) {
			var  
				parent = this.parentNode,
				title = this.innerHTML,
				value = this.value,
				inputID = this.id || 'ui-multiselect-' + id + '-option-' + i, 
				isDisabled = this.disabled,
				isSelected = this.selected,
				labelClasses = ['ui-corner-all'],
				optLabel;
			
			// is this an optgroup?
			if (parent.tagName.toLowerCase() === 'optgroup') {
				optLabel = parent.getAttribute('label');
				// has this optgroup been added already?
				if ($.inArray(optLabel, optgroups) === -1) {
					html.push('<li class="ui-multiselect-optgroup-label"><a href="#">' + optLabel + '</a></li>');
					optgroups.push( optLabel );
				}
			}
		
			if (isDisabled) {
				labelClasses.push('ui-state-disabled');
			}

			// browsers automatically select the first option
			// by default with single selects
			if (isSelected && !o.multiple) {
				labelClasses.push('ui-state-active');
			}
			
			html.push('<li class="' + (isDisabled ? 'ui-multiselect-disabled' : '') + '">');
			html.push('<label for="' + inputID + '" class="' + labelClasses.join(' ')+ '">');
			html.push('<input tabindex="-1" id="' + inputID + '" name="multiselect_' + id + '" type="' + (o.multiple ? "checkbox" : "radio")+'" value="' + value + '" title="' + title + '"');

			if( isSelected ){
				html.push(' checked="checked"');
				html.push(' aria-selected="true"');
			}

			if( isDisabled ){
				html.push(' disabled="disabled"');
				html.push(' aria-disabled="true"');
			}

			// add the title and close everything off
			html.push(' /><span>' + title + '</span></label></li>');
		});
		
		// insert into the DOM
		checkboxContainer.html("<div class='ui-multiselect-menu-container' style='width:" + (o.containerWidth === 0 ? "auto" : o.containerWidth + "px")  + "'>" +  html.join('') + "</div>");

		// cache some moar useful elements
		this.labels = menu.find('label');
		
		// set widths
		this._setMenuWidth();
		
		// remember default value
		this.button[0].defaultValue = this.update();
		
		// broadcast refresh event; useful for widgets
		if(!init){
			this._trigger('refresh');
		}
		
	},
	
	// updates the button text.  call refresh() to rebuild
	update : function() {
		var 
			o = this.options,
			$inputs = this.labels.find('input'),
			$checked = $inputs.filter(':checked'),
			numChecked = $checked.length,
			value;
		
		if (numChecked === 0) {
			value = o.noneSelectedText;
		} else {
			if ($.isFunction(o.selectedText)) {
				value = o.selectedText.call(this, numChecked, $inputs.length, $checked.get());
			} else if ( /\d/.test(o.selectedList) && o.selectedList > 0 && numChecked <= o.selectedList){
				value = $checked.map(function(){ return this.title; }).get().join(', ');
			} else {
				value = o.selectedText.replace('#', numChecked).replace('#', $inputs.length);
			}
		}
		this.buttonlabel.html( value ).parent().attr('title', value);
		return value;
	},
	
	// binds events
	_bindEvents : function() {
		var 
			self = this, 
			button = this.button;
		
		function clickHandler(){
			self[self._isOpen ? 'close' : 'open']();
			return false;
		}
		
		// webkit doesn't like it when you click on the span :(
		button
			.find('span')
			.bind('click.multiselect', clickHandler);
		
		// button events
		button.bind({
			click : clickHandler,
			keydown : function(e) {
				if (e.keyCode == 13) {
					e.preventDefault();
				}
			},
			keypress : function(e){
				switch(e.keyCode){
				case 46:
					self.uncheckAll();					
					break;
				case 27: // esc
				case 38: // up
				case 37: // left
					self.close();
					break;
				case 39: // right
				case 40: // down
					self.open();
					break;
				}
			},
			mouseenter : function(){
				if (!button.hasClass('ui-state-disabled')) {
					$(this).addClass('ui-state-hover');
				}
			},
			mouseleave : function(){
				$(this).removeClass('ui-state-hover');
			},
			focus : function(){
				if (!button.hasClass('ui-state-disabled')) {
					$(this).addClass('ui-state-focus');
				}
			},
			blur: function(){
				$(this).removeClass('ui-state-focus');
			}
		});

		// header links
		this.header
			.delegate('a', 'click.multiselect', function(e){
				// close link
				if( $(this).hasClass('ui-multiselect-close') ){
					self.close();
				// check all / uncheck all
				} else {
					self[ $(this).hasClass('ui-multiselect-all') ? 'checkAll' : 'uncheckAll' ]();
				}
				e.preventDefault();
			});
		
		// optgroup label toggle support
		this.menu
			.delegate('li.ui-multiselect-optgroup-label a', 'click.multiselect', function(e){
				e.preventDefault();
				
				var 
					$this = $(this),
					$inputs = $this.parent().nextUntil('li.ui-multiselect-optgroup-label').find('input:visible:not(:disabled)'),
				    nodes = $inputs.get(),
				    label = $this.parent().text();
				
				// trigger event and bail if the return is false
				if( self._trigger('beforeoptgrouptoggle', e, { inputs:nodes, label:label }) === false ){
					return;
				}
				
				// toggle inputs
				self._toggleChecked(
					$inputs.filter(':checked').length !== $inputs.length,
					$inputs
				);

				self._trigger('optgrouptoggle', e, {
				    inputs: nodes,
				    label: label,
				    checked: nodes[0].checked
				});
				
				self._trigger('change', e, {
				    inputs: nodes,
				    label: label,
				    checked: nodes[0].checked
				});				
			})
			.delegate('label', 'mouseenter.multiselect', function() {
				if (!$(this).hasClass('ui-state-disabled')) {
					self.labels.removeClass('ui-state-hover');
					$(this).addClass('ui-state-hover').find('input').focus();
				}
			})
			.delegate('label', 'keydown.multiselect', function(e) {
				e.preventDefault();
				
				switch(e.which){
					case 9: // tab
					case 27: // esc
						self.close();
						break;
					case 38: // up
					case 40: // down
					case 37: // left
					case 39: // right
						self._traverse(e.which, this);
						break;
					case 13: // enter
						$(this).find('input')[0].click();
						break;
				}
			})
			.delegate('input[type="checkbox"], input[type="radio"]', 'click.multiselect', function(e) {

				var 
					$this = $(this),
					val = this.value,
					checked = this.checked,
					tags = self.element.find('option');
				
				//<ZANDI>
				var oldVal = [];
				tags.each(function(){
					if( this.selected )
						oldVal.push(this.value);
				});	
				// toggle aria state
				$this.attr('aria-selected', checked);
				
				// set the original option tag to selected
				tags.each(function(){
					if (this.value === val) {
						this.selected = checked;

					// deselect all others in a single select
					} else if (!self.options.multiple) {
						this.selected = false;
					}
				});	

				// bail if this input is disabled or the event is cancelled
				if( this.disabled 
						|| self._trigger('click', e, { value: oldVal, text:this.title, checked:checked }) === false
						|| self._trigger('change', e, { value: oldVal, text:this.title, checked:checked }) === false){
					e.preventDefault();
					return;
				}			
				
				// some additional single select-specific logic
				if (!self.options.multiple) {
					self.labels.removeClass('ui-state-active');
					$this.closest('label').toggleClass('ui-state-active', checked );
					self.close();
				}
				
				// setTimeout is to fix multiselect issue #14 and #47. caused by jQuery issue #3827
				// http://bugs.jquery.com/ticket/3827 
				setTimeout($.proxy(self.update, self), 10);
			});
		
		// close each widget when clicking on any other element/anywhere else on the page
		$(document).bind('mousedown.multiselect.' + self.element.attr('id'), function(e){
			if (self._isOpen && !$.contains(self.menu[0], e.target) && !$.contains(self.button[0], e.target) && e.target !== self.button[0]){
				self.close();
			}
		});
		
		
		self.options.container.bind('scroll.multiselect', function() {	
			self.close();
		});
		
		// deal with form resets.  the problem here is that buttons aren't
		// restored to their defaultValue prop on form reset, and the reset
		// handler fires before the form is actually reset.  delaying it a bit
		// gives the form inputs time to clear.
		$(this.element[0].form).bind('reset.multiselect', function(){
			setTimeout(function(){ self.update(); }, 10);
		});
	},

	// set menu width
	_setMenuWidth : function() {
		var 
			m = this.menu,
			width = this.button.outerWidth() -
				parseInt(m.css('padding-left'), 10) -
				parseInt(m.css('padding-right'), 10) -
				parseInt(m.css('border-right-width'), 10) -
				parseInt(m.css('border-left-width'), 10);	
		m.width(width || this.button.outerWidth());
	},
	
	// move up or down within the menu
	_traverse : function(which, start) {
		var 
			$start = $(start),
			moveToLast = which === 38 || which === 37,
			
			// select the first li that isn't an optgroup label / disabled
			$next = $start.parent()[moveToLast ? 'prevAll' : 'nextAll']('li:not(.ui-multiselect-disabled, .ui-multiselect-optgroup-label)')[ moveToLast ? 'last' : 'first']();
		
		// if at the first/last element
		if (!$next.length) {
			
			var $container = this.menu.find('ul:last');
			
			// move to the first/last
			this.menu.find('label')[ moveToLast ? 'last' : 'first' ]().trigger('mouseover');
			
			// set scroll position
			$container.scrollTop( moveToLast ? $container.height() : 0 );
			
		} else {
			$next.find('label').trigger('mouseover');
		}
	},

	// This is an internal function to toggle the checked property and
	// other related attributes of a checkbox.
	//
	// The context of this function should be a checkbox; do not proxy it.
	_toggleCheckbox : function(prop, flag) {
		return function(){
			!this.disabled && (this[ prop ] = flag);
			if (flag) {
				this.setAttribute('aria-selected', true);
			} else {
				this.removeAttribute('aria-selected');
			}
		};
	},

	_toggleChecked : function(flag, group) {
		var 
			$inputs = (group && group.length) ?
			group :
			this.labels.find('input'),

			self = this;

		// toggle state on inputs
		$inputs.each(this._toggleCheckbox('checked', flag));
		
		// update button text
		this.update();
		
		// gather an array of the values that actually changed
		var values = $inputs.map(function(){
			return this.value;
		}).get();

		// toggle state on original option tags
		this.element
			.find('option')
			.each(function(){
				if( !this.disabled && $.inArray(this.value, values) > -1 ){
					self._toggleCheckbox('selected', flag).call( this );
				}
			});
	},

	_toggleDisabled : function(flag) {
		this.button
			.attr({ 'disabled':flag, 'aria-disabled':flag })[ flag ? 'addClass' : 'removeClass' ]('ui-state-disabled');
		
		this.menu
			.find('input')
			.attr({ 'disabled':flag, 'aria-disabled':flag })
			.parent()[ flag ? 'addClass' : 'removeClass' ]('ui-state-disabled');
		
		this.element
			.attr({ 'disabled':flag, 'aria-disabled':flag });
	},
	
	// open the menu
	open : function(e) {
		var 
			self = this,
			button = this.button,
			menu = this.menu,
			speed = this.speed,
			o = this.options;
		
		// bail if the multiselectopen event returns false, this widget is disabled, or is already open 
		if (this._trigger('beforeopen') === false || button.hasClass('ui-state-disabled') || this._isOpen) {
			return;
		}
		
		var 
			$container = menu.find('ul:last'),
			effect = o.show,
			pos = button.position();
		
		// figure out opening effects/speeds
		if ($.isArray(o.show)) {
			effect = o.show[0];
			speed = o.show[1] || self.speed;
		}
		
		// set the scroll of the checkbox container
		$container.scrollTop(0).height(o.height);
		
		// position and show menu
		if ($.ui.position && !$.isEmptyObject(o.position)) {
			o.position.of = o.position.of || button;
			
			menu
				.show()
				.position( o.position )
				.hide()
				.show( effect, speed );
		
		// if position utility is not available...
		} else {
			var offset = button.offset();
			menu.css({ 
				top: pos.top + offset.top + button.outerHeight(),
				left: pos.left + offset.left
			});
			menu.outerWidth(button.outerWidth());
			menu.show();
			// for considering scroll
			offset = button.offset();
			menu.css({ 
				left: pos.left + offset.left
			});			
			menu.hide();
			if (effect != '') {
				menu.show( effect, speed );
			} else {
				menu.show();
			}
		}
				
		button.addClass('ui-state-active');
		this._isOpen = true;
		this._trigger('open');
	},
	
	// close the menu
	close : function() {
		if(this._trigger('beforeclose') === false){
			return;
		}
	
		var o = this.options, effect = o.hide, speed = this.speed;
		
		// figure out opening effects/speeds
		if ($.isArray(o.hide)) {
			effect = o.hide[0];
			speed = o.hide[1] || this.speed;
		}

		//this.menu.hide(effect, speed);
		if (effect != '')
			this.menu.hide(effect, speed);
		else
			this.menu.hide();
		
		this.button.removeClass('ui-state-active');
		this._isOpen = false;
		this._trigger('close');
	},

	enable : function() {
		this._toggleDisabled(false);
	},
	
	disable : function() {
		this._toggleDisabled(true);
	},
	
	checkAll : function(e) {
		var flag = false;
		if (this.menu.find('input').filter(':checked').length == 0) {
			flag = true;
		}			
		this._toggleChecked(true);
		this._trigger('checkAll');
		if (flag) {
			this._trigger('change', e, 'checkAll');
		}
	},
	
	uncheckAll : function(e) {
		var flag = false;
		if (this.menu.find('input').filter(':checked').length) {
			flag = true;
		}			
		this._toggleChecked(false);	
		this._trigger('uncheckAll');
		if (flag) {
			this._trigger('change', e, 'uncheckAll');
		}
	},
	
	getChecked : function() {
		return this.menu.find('input').filter(':checked');
	},
	
	destroy : function() {
		// remove classes + data
		$.Widget.prototype.destroy.call( this );
		
		this.button.remove();
		this.menu.remove();
		this.element.show();
		$(document).unbind('mousedown.multiselect.' + this.element.attr('id'));
		return this;
	},
	
	isOpen : function() {
		return this._isOpen;
	},
	
	widget : function() {
		return this.menu;
	},
	
	// react to option changes after initialization
	_setOption: function(key, value) {
		var menu = this.menu;
		
		switch(key){
			case 'header':
				menu.find('div.ui-multiselect-header')[ value ? 'show' : 'hide' ]();
				break;
			case 'checkAllText':
				menu.find('a.ui-multiselect-all span').eq(-1).text(value);
				break;
			case 'uncheckAllText':
				menu.find('a.ui-multiselect-none span').eq(-1).text(value);
				break;
			case 'height':
				menu.find('ul:last').height( parseInt(value,10) );
				break;
			case 'minWidth':
				this.options[ key ] = parseInt(value,10);
				this._setMenuWidth();
				break;
			case 'selectedText':
			case 'selectedList':
			case 'noneSelectedText':
				this.options[key] = value; // these all needs to update immediately for the update() call
				this.update();
				break;
			case 'classes':
				menu.add(this.button).removeClass(this.options.classes).addClass(value);
				break;
		}
		
		$.Widget.prototype._setOption.apply( this, arguments );
	}
});

})(jQuery);



/* jshint forin:true, noarg:true, noempty:true, eqeqeq:true, boss:true, undef:true, curly:true, browser:true, jquery:true */
/*
 * jQuery MultiSelect UI Widget Filtering Plugin 1.5pre
 * Copyright (c) 2012 Eric Hynds
 *
 * http://www.erichynds.com/jquery/jquery-ui-multiselect-widget/
 *
 * Depends:
 *   - jQuery UI MultiSelect widget
 *
 * Dual licensed under the MIT and GPL licenses:
 *   http://www.opensource.org/licenses/mit-license.php
 *   http://www.gnu.org/licenses/gpl.html
 *
 */
(function($) {
  var rEscape = /[\-\[\]{}()*+?.,\\\^$|#\s]/g;

  $.widget('ech.multiselectfilter', {

    options: {
      label: 'Filter:',
      width: null, /* override default width set in css file (px). null will inherit */
      placeholder: 'Enter keywords',
      autoReset: false
    },

    _create: function() {
    	
      var opts = this.options;
      var elem = $(this.element);

      // get the multiselect instance
      var instance = (this.instance = (elem.data('echMultiselect') || elem.data("multiselect") || elem.data("ech-multiselect")));

      // store header; add filter class so the close/check all/uncheck all links can be positioned correctly
      var header = (this.header = instance.menu.find('.ui-multiselect-header').addClass('ui-multiselect-hasfilter'));

      // wrapper elem
      var wrapper = (this.wrapper = $('<div class="ui-multiselect-filter">' + (opts.label.length ? opts.label : '') + '<input placeholder="'+opts.placeholder+'" type="search"' + (/\d/.test(opts.width) ? 'style="width:'+opts.width+'px"' : '') + ' /></div>').prependTo(this.header));

      // reference to the actual inputs
      this.inputs = instance.menu.find('input[type="checkbox"], input[type="radio"]');

      // build the input box
      this.input = wrapper.find('input').bind({
        keydown: function(e) {
          // prevent the enter key from submitting the form / closing the widget
          if(e.which === 13) {
            e.preventDefault();
          }
        },
        keyup: $.proxy(this._handler, this),
        click: $.proxy(this._handler, this)
      });
      
      // cache input values for searching
      this.updateCache();

      // rewrite internal _toggleChecked fn so that when checkAll/uncheckAll is fired,
      // only the currently filtered elements are checked
      instance._toggleChecked = function(flag, group) {
        var $inputs = (group && group.length) ?  group : this.labels.find('input');
        var _self = this;

        
        
        //<ZANDI>
        // do not include hidden elems if the menu isn't open.
        /*var selector = instance._isOpen ?  ':disabled, :hidden' : ':disabled';       
        
        $inputs = $inputs
          .not(selector)
          .each(this._toggleCheckbox('checked', flag));*/
        
        $inputs = $inputs.not(":disabled").not(function() {
        		if (instance._isOpen) {
        			return !( $(this).closest('li').is(':visible'));
        		} else {
        			return false;
        		}
        	}).each(this._toggleCheckbox('checked', flag));
        
        if (!flag) {
        	this.labels.removeClass('ui-state-active');
        }
        //<ZANDI>
        
        // update text
        this.update();

        // gather an array of the values that actually changed
        var values = $inputs.map(function() {
          return this.value;
        }).get();

        // select option tags
        this.element.find('option').filter(function() {
          if(!this.disabled && $.inArray(this.value, values) > -1) {
            _self._toggleCheckbox('selected', flag).call(this);
          }
        });

        // trigger the change event on the select
        if($inputs.length) {
          this.element.trigger('change');
        }
      };

      // rebuild cache when multiselect is updated
      var doc = $(document).bind('multiselectrefresh', $.proxy(function() {
        this.updateCache();
        this._handler();
      }, this));

      // automatically reset the widget on close?
      if(this.options.autoReset) {
        doc.bind('multiselectclose', $.proxy(this._reset, this));
      }
    },

    // thx for the logic here ben alman
    _handler: function(e) {
      var term = $.trim(this.input[0].value.toLowerCase()),

      // speed up lookups
      rows = this.rows, inputs = this.inputs, cache = this.cache;

      if(!term) {
        rows.show();
      } else {
        rows.hide();

        var regex = new RegExp(term.replace(rEscape, "\\$&"), 'gi');

        this._trigger("filter", e, $.map(cache, function(v, i) {
          if(v.search(regex) !== -1) {
            rows.eq(i).show();
            return inputs.get(i);
          }

          return null;
        }));
      }

      // show/hide optgroups
      this.instance.menu.find(".ui-multiselect-optgroup-label").each(function() {
        var $this = $(this);
        var isVisible = $this.nextUntil('.ui-multiselect-optgroup-label').filter(function() {
          return $.css(this, "display") !== 'none';
        }).length;

        $this[isVisible ? 'show' : 'hide']();
      });
    },

    _reset: function() {
      this.input.val('').trigger('keyup');
    },

    updateCache: function() {
      // each list item
      this.rows = this.instance.menu.find(".ui-multiselect-checkboxes li:not(.ui-multiselect-optgroup-label)");

      // cache
      this.cache = this.element.children().map(function() {
        var elem = $(this);

        // account for optgroups
        if(this.tagName.toLowerCase() === "optgroup") {
          elem = elem.children();
        }

        return elem.map(function() {
          return this.innerHTML.toLowerCase();
        }).get();
      }).get();
    },

    widget: function() {
      return this.wrapper;
    },

    destroy: function() {
      $.Widget.prototype.destroy.call(this);
      this.input.val('').trigger("keyup");
      this.wrapper.remove();
    }
  });

})(jQuery);

