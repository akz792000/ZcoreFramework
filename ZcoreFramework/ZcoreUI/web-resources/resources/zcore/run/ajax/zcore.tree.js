/**
 *
 * @author Ali Karimizandi
 * @since 2012
 *
 */

(function ($, _) {

    _.clazz({

        name: 'tree',

        inherit: 'postable',

        friend: 'ajax',

        body: function (o) {

            /*
             * ----------------------------------------------------------------------
             * 								 resources
             * ----------------------------------------------------------------------
             */

            this.resource = {
                root: '/zcore/util/tree/',
                files: ['jquery.jstree.js']
            };

            /*
             * ----------------------------------------------------------------------
             * 								 properties
             * ----------------------------------------------------------------------
             */

            this.properties({

                /*
                 * override
                 */

                readonly: null,

                value: {
                    get: function () {
                        var T = this.__;
                        return T.o.ZcoreTreeInvoker('option', 'get', T);
                        //return "";
                    },
                    set: function () {
                        //--> nop
                    }
                },

                /*
                 * new
                 */

                category: _.defaultProperty('string', 'crud'),

                twoState: _.defaultProperty('boolean'),

                oneSelect: _.defaultProperty('boolean'),

                invisible: _.defaultProperty(),

                operation: _.defaultProperty('string', 'ALL_CHILDREN'),

                completeTitle: _.defaultProperty('boolean'),

                // todo Azadi Add property  
                searchShow: _.defaultProperty('boolean'),
                
                selectingNode: _.defaultProperty('string', ''),
                
                defaultIdVal: _.defaultProperty('int', '0'),

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
                    method: function () {
                        var T = this.__;
                        T.o.ZcoreTreeInvoker('show', T.params.category, T);
                    }
                },
                addAction: {
                    method: function (v) {
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

                /*
                 * new
                 */

                persistClick: {
                    //--> just event base
                },

                mergeClick: {
                    //--> just event base
                },

                removeClick: {
                    //--> just event base
                },

                viewClick: {
                    //--> just event base
                },

                primeParam: {
                    event: function (v) {
                        //--> add new method
                        this.__.methods({
                            prime: {

                                method: v
                            }
                        });
                    }
                },

                refreshTree: {
                    method: function (v) {
                        var T = this.__;
                        if (v) {
                            T.o.unbind('refresh.jstree').bind('refresh.jstree', function () {
                                $.jstree._reference(T.id()).open_all(-1);
                                if ($.isFunction(v))
                                    v();
                            });
                        }
                        T.o.jstree('refresh', -1);
                    }
                },

                persist_node: {
                    method: function (v) {
                        var tree = $.jstree._focused();
                        if (tree._is_loaded('#' + v.parent)) {
                            tree.create_node(
                                '#' + v.parent,
                                'last', {
                                    data: v.name,
                                    attr: {
                                        id: v.id,
                                        rel: 'without_children'
                                    }
                                },
                                null,
                                false);
                        }
                        if (tree._get_node('#' + v.parent).attr('rel') == 'without_children') {
                            tree._get_node('#' + v.parent).attr('rel', 'default');
                        }
                        tree.open_node('#' + v.parent);
                    }
                },

                merge_node: {
                    method: function (v) {
                        $.jstree._focused().rename_node('#' + v.id, v.name);
                    }
                },

                remove_node: {
                    method: function (v) {
                        var
                            tree = $.jstree._focused(),
                            parent = tree._get_parent('#' + v.id);
                        if ((tree._get_children('#' + parent.attr('id')).length == 1) && (parent.attr('id') != '1') && (parent.attr('rel') != 'root')) {
                            parent.attr('rel', 'without_children');
                        }
                        tree.delete_node('#' + v.id);
                    }
                },

                getNode: {
                    method: function (v) {
                        return $.jstree._focused()._get_node('#' + v);
                    }
                },


                crud: {
                    event: function (method) {
                        var
                            T = this.__,
                            func = function (v, n) {
                                method(v, {
                                    id: n.attr('id'),
                                    "class": n.attr('class'),
                                    rel: n.attr('rel')
                                });
                            };

                        T
                            .onPersistClick(function (n) {
                                func('persist', n);
                            })
                            .onMergeClick(function (n) {
                                func('merge', n);
                            })
                            .onRemoveClick(function (n) {
                                func('remove', n);
                            })
                            .onViewClick(function (n) {
                                func('view', n);
                            });
                    }
                },


            });
        }
    });

})(jQuery, jQuery.ZOF);