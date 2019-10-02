/**
 * 
 * @author Ali Karimizandi
 * @since 2012 
 *   
 */

// document ready
$(function() {
	
	// global common
	$.ZAP.global('portal', {
		/*
		 * viewMode
		 */
		viewMode: function (p) {
            var c = p.components();
            for (var i in c) {
                if (!( c[i] instanceof $.ZOF.label)) {
                    c[i].disabled(true);
                }
            }
        },
        /*
         * validateLegalPersonNationalId
         */
        validateLegalPersonNationalId: function (nationalId) {
            var 
            	result = false,
            	sum = 0,
            	modEleven = 0;
            if (nationalId.value() != '') {
                if (nationalId.value().length == 11) {
                    for (var i = 0; i < 10; i++) {
                        var sumLastDigitAndSingleDigit = parseInt(nationalId.value().substring(i, i + 1)) + parseInt(nationalId.value().substring(9, 10));
                        sumLastDigitAndSingleDigit += 2;
                        switch (i) {
                            case 0:
                                sumLastDigitAndSingleDigit *= 29;
                                break;
                            case 1:
                                sumLastDigitAndSingleDigit *= 27;
                                break;
                            case 2:
                                sumLastDigitAndSingleDigit *= 23;
                                break;
                            case 3:
                                sumLastDigitAndSingleDigit *= 19;
                                break;
                            case 4:
                                sumLastDigitAndSingleDigit *= 17;
                                break;
                            case 5:
                                sumLastDigitAndSingleDigit *= 29;
                                break;
                            case 6:
                                sumLastDigitAndSingleDigit *= 27;
                                break;
                            case 7:
                                sumLastDigitAndSingleDigit *= 23;
                                break;
                            case 8:
                                sumLastDigitAndSingleDigit *= 19;
                                break;
                            case 9:
                                sumLastDigitAndSingleDigit *= 17;
                                break;
                        }
                        sum += sumLastDigitAndSingleDigit;
                    }
                    modEleven = sum % 11;
                    if (modEleven / 10 == 0) {
                        if (modEleven == parseInt(nationalId.value().substring(10, 11))) {
                            result = true;
                        }
                    } else if (!(modEleven / 10 == 0)) {
                        if (modEleven % 10 == parseInt(nationalId.value().substring(10, 11))) {
                            result = true;
                        }
                    }
                }
            }
            return result;

        },
        /*
         * validateNationalId
         */
        validateNationalId: function (nationalId) {
            if (nationalId.value() != '') {
                if (nationalId.value().length == 10) {
                    var ncode = nationalId.value();
                    if (ncode == '1111111111' || ncode == '2222222222' || ncode == '3333333333' || ncode == '4444444444' ||
                        ncode == '5555555555' || ncode == '6666666666' || ncode == '7777777777' || ncode == '8888888888' ||
                        ncode == '9999999999' || ncode == '0000000000') {
                        return false;
                    }
                    var 
                    	ld = parseInt(nationalId.value().substring(9, 10)),
                    	n = 0, 
                    	m = 0;
                    for (var i = 0; i < 9; i++) {
                        n = n + parseInt(nationalId.value().substring(i, i + 1)) * (10 - i);
                    }
                    m = n % 11;
                    if (!((m == 0 && ld == 0) || (m == 1 && ld == 1) || (m > 1 && ld == 11 - m))) {
                        return false;
                    } else {
                        return true;
                    }
                }
            }
            return false;
        }
    });	

});		