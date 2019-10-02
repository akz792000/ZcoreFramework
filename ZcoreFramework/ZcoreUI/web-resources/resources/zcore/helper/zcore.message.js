/**
 * 
 * @author Ali Karimizandi
 * @since 2012 
 *   
 */

(function($, _) {
		
	_.clazz({
		
		name : 'message',
		
		body : function() {	
		
			$.extend(this, {
				
				_init : function() {
					
					this.en = {
							
					};
					
					this.fa = {
							
							/*
							 * component
							 */	
							'Logo title' : 'سامانه متمرکز بانک مسکن',
							'Process has not been started' : 'پردازش سمت سیستم مقصد شروع نشده است',
														
							// app	
							'requestNotHandle' : 'سرور قادر به شناسایی درخواست ارسالی نمی باشد',
							'badGateway' : 'سرور مربوطه قادر به پاسخگویی نمی باشد',
							'serviceUnavailable' : 'سرویس موجود نمی باشد',
							'timeout' : 'ارتباط با سرور قطع می باشد',
							'forbidden' : 'اجازه دسترسی به این قسمت را ندارید',
							'error' : 'خطا',
							'notHandle' : 'لطفآ با مسئولین تماس بگیرید.',
							'Bad Captcha' : 'حروف کلیدی صحیح نمی باشد',
							'Bad credentials' : 'نام کاربری یا رمز عبور صحیح نمی باشد',
							'Bad Authorities' : 'عدم وجود سترسی مناسب برای ورود به سیستم',
							'Failed Try' : 'کاربر اجازه دسترسی به سیستم را به مدت {0} ثانیه ندارد',
							'SECURITY_USER_DISABLE' : 'کاربر غیر فعال شده است',
							'SECURITY_USER_LOCK' : 'کاربر مسدود شده است',
							'SECURITY_USER_EXPIRE' : 'اعتبار کاربر تمام شده است',
							'SECURITY_ORGANIZATION_CALENDAR_NOT_FOUND':'مجاز ورود به سیستم نمی باشید',
							'Maximum sessions of 1 for this principal exceeded' : 'کاربر هنوز از سیستم خارج نشده است',
							'Bad file size' : 'حداکثر اندازه فایل برابر با {0} مگابایت است',
							'Bad file types' : 'نوع فایل انتخاب شده مجاز نمی باشد',
							
							// security state 
							'SECURITY_USER_ENABLED' : 'رمز عبور کاربر منقضی شده است',
							'SECURITY_USER_CREDENTIAL_EXPIRED' : 'رمز عبور کاربر منقضی شده است',
							'SECURITY_USER_CREDENTIAL_CHANGE' : 'رمز عبور کاربر منقضی شده است',
							'SECURITY_USER_MULTI_ORGANIZATIONAL' : 'سازمان  کاربر را انتخاب کنید',
							'SERVICE_IS_DOWN': 'بروز خطای سیستمی',
						    
									
							// loading
							'loading' : 'صبر کنید ...',
							
							// bar
							'success' : 'عملیات با موفقیت انجام شد',
							'validate' : 'مقدار ورودی معتبر نمی باشد',
							
							// box
							'yes' : 'بلی',
							'no' : 'خیر',
							'ok' : 'تایید',
							'cancel' : 'انصراف',
													
							// dialog							
							'close' : 'بستن',
							
							// lov
							'clear' : 'پاک',

							// multiselect & chosen
							'checkAll' : 'انتخاب همه',
							'uncheckAll' : 'هیچکدام',
							'ChooseItem' : 'انتخاب گزینه ها',
							'selectedItem' : '# گزینه',
							'search' : 'جستجو',
							'noResult' : 'یافت نشد',
							
							// grid
							'row' : 'ردیف',
							'refresh' : 'بروزرسانی',
							'persist': 'درج',
							'merge' : 'اصلاح',
							'remove' : 'حذف',
							'view' : 'نمایش',
							'filter' : 'فیلتر',
							'asc' : 'صعودی',
							'desc' : 'نزولی',
							'page' : 'صفحه',
							'to' : 'از',
							'first' : 'ابتدا',
							'prev' : 'قبلی',
							'next' : 'بعدی',
							'last' : 'انتها',	
							'count' : 'تعداد کل',
							'operation' : 'عملیات',
							'choose' : 'انتخاب',
							'notFound' : 'مشخص نشده',
							
							// date time
							'hour' : 'ساعت',
							'minute' : 'دقیقه',
							'second' : 'ثانیه',	
							
							/*
							 * validation
							 */	
							
							// null
							'not.null' : 'مقدار ورودی اجباری است',
							
							// length
							'not.blank' : 'مقدار ورودی اجباری است',
							'length' : 'مقدار ورودی می بایستی بین {0} تا {1} کاراکتر باشد',
							'max.length' : 'حداکثر مقدار برابر {0} کاراکتر می باشد',
							'min.length' : 'حداقل مقدار برابر {0} کاراکتر می باشد',
							'fix.length' : 'مقدار ورودی می بایستی {0} کاراکتر باشد',
							
							// size
							'not.empty' : 'مقدار ورودی اجباری است',
							'size' : 'تعداد مقادیر ورودی می بایستی بین {0} تا {1} باشد',
							'max.size' : 'تعداد مقادیر  حداکثر {0} می باشد',
							'min.size' : 'تعداد مقادیر حداقل {0} می باشد',
							'fix.size' : 'تعداد مقادیر می بایستی {0} کاراکتر باشد',
							
							// range
							'max' : 'حداکثر مقدار برابر {0} می باشد',		
							'min' : 'حداقل مقدار برابر {0} می باشد',
							'range' : '',
							'regexp' : 'مقدار ورودی معتبر نیست',
							
							// email
							'email' : 'مقدار ورودی معتبر نیست',
														
							// expression
							'expression' : 'مقدار ورودی معتبر نیست',
							
							// date
							'date.fault' : 'تاریخ معتبر نمی باشد',
							
							// others
							'value.exist' : 'مقدار ورودی معتبر نیست',
							'examine.captcha' : 'مقدار ورودی معتبر نیست',
								
							'is.duplicate' : 'مقدار ورودی تکراری است',	
							
						};						

				},
				
				set : function(locale, k, v) {
					this[locale][k] = v;
				},	
				
				get : function(locale, v) {
					return this[locale][v] !== undefined ? this[locale][v] : v;
				},
				
				msg : function(v, args, locale) {
					var msg = this.get(locale !== undefined ? locale : $.ZAP.locale.get(), v);
					if ((args) && (args.length != 0)) {
						for (var item in args) {
							msg = msg
									//--> first replace translate able arguments
									.replace(new RegExp('\\$\\{' + item + '\\}', 'g'), this.msg(args[item]))
									.replace(new RegExp('\\{' + item + '\\}', 'g'), args[item]);
						}
					}; 
					return msg;
				},
				
				exist : function (v, locale) {
					return this[locale !== undefined ? locale : $.ZAP.locale.get()][v] !== undefined;
				},
				
				translate : function(v, args, locale) {					
					if (v) {
						var that = this;
						return v.replace(/\$\{[\s\S]*?\}/g, function(matched) {
							var 
								exps = matched.substr(2, matched.length - 3).split(' '),
								res = '';
							for (var i = 0; i < exps.length; i++) {
								res += (res == '' ? '' : ' ') + that.msg(exps[i], args, locale);
							}
							return res;
						});
					}
				}				
			
			});
			
			this._init();
			
		}
		
	});
	
})(jQuery, jQuery.ZOF);