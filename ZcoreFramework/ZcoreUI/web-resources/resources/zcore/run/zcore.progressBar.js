/**
 *
 * @author Ali Karimizandi
 * @since 2012
 *
 */

(function ($, _) {

    _.clazz({

        name: 'progressBar',

        inherit: 'component',

        body: function (o) {

            /*
             * ----------------------------------------------------------------------
             * 								 properties
             * ----------------------------------------------------------------------
             */

            this.properties({

                /*
                 * override
                 */
                value: {
                    get: function () {
                        return this.__.o.progressbar("value");
                    },
                    set: function (v) {
                        this.__.o.progressbar("value", v);
                    }
                },
                disabled: {
                    after: function (v) {
                        this.__.focused.progressbar("option", "disabled", v);
                    }
                }
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
                render: {
                    advice: 'before',
                    method: function () {
                        var T = this.__;
                        T.o.progressbar({});
                    }
                },
                change: {
                    event: function (v) {
                        var T = this.__;
                        if ($.isFunction(v)) {
                            T.focused.bind('progressbarchange', function (e) {
                                if (!$(this).is(':disabled')) {
                                    v.apply(T, arguments);
                                }
                            });
                        }
                    },
                    method: function (v) {
                        //--> nop
                    }
                },
                complete: {
                    event: function (v) {
                        var T = this.__;
                        if ($.isFunction(v)) {
                            T.focused.bind('progressbarcomplete', function (e) {
                                if (!$(this).is(':disabled')) {
                                    v.apply(T, arguments);
                                }
                            });
                        }
                    },
                    method: function (v) {
                        this.__.focused.trigger('progressbarcomplete');
                        //--> nop
                    }
                }
            });

        }

    });

})(jQuery, jQuery.ZOF);