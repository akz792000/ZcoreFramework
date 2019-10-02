/**
 * 
 * @author Ali Karimizandi
 * @since 2012 
 *   
 */

(function($) {
	
	function ZcoreCookiePlugin() {}
	
	//--> register as global
	$.ZCK = new ZcoreCookiePlugin();
	
	/*
	function setCookie(key, value) {
		var expires = new Date();
		expires.setTime(expires.getTime() + 31536000000); // 1 year
		document.cookie = key + '=' + value + ';expires=' + expires.toUTCString();
	}

	function getCookie(key) {
		var keyValue = document.cookie.match('(^|;) ?' + key + '=([^;]*)(;|$)');
		return keyValue ? keyValue[2] : null;
	}
	
 	*/	
	
	(function(_) {
		
		/*
		 * ----------------------------------------------------------------------
		 * 								 methods
		 * ---------------------------------------------------------------------- 
		 */		
		
		$.extend(ZcoreCookiePlugin.prototype, {		
			
			cookieObject : function(params) {
				return $.extend({
					cookie : '',
					cookieable : true,
					def : '',
					value : undefined,
					after : undefined,
					get : function() {
						return this.value || (this.cookieable ? $.ZCK.read(this.cookie) : undefined) || this.def;
					},
					set : function(v) {
						this.value = v || this.get();					
						if (this.cookieable) {
							$.ZCK.create(this.cookie, this.value);
						}
						if (this.after) {
							this.after(this.value);
						}
					}
				}, params);
			},
			
			create : function(name, value, days) {
			    var expires;
	
			    if (days) {
			        var date = new Date();
			        date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
			        expires = "; expires=" + date.toGMTString();
			    } else {
			        expires = "";
			    }
			    document.cookie = encodeURIComponent(name) + "=" + encodeURIComponent(value) + expires + "; path=/";
			},
	
			read : function(name) {
			    var 
			    	nameEQ = encodeURIComponent(name) + "=",
			    	ca = document.cookie.split(';');
			    for (var i = 0; i < ca.length; i++) {
			        var c = ca[i];
			        while (c.charAt(0) === ' ') c = c.substring(1, c.length);
			        if (c.indexOf(nameEQ) === 0) return decodeURIComponent(c.substring(nameEQ.length, c.length));
			    }
			    return null;
			},
	
			erase : function (name) {
			    this.create(name, "", -1);
			}
			
		});
			
	})($.ZCK);
	
})(jQuery);