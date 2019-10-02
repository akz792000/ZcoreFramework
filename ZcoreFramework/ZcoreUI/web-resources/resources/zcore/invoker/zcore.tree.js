/**
 *
 * @author Ali Karimizandi
 * @since 2012
 *
 */

(function ($) {

    function ZcoreTreeInvokerPlugin() {
    }

    $.extend(ZcoreTreeInvokerPlugin, {

        /*
         * all methods are static
         */

        _plugins: ['ui', 'core', 'themes', 'types', 'json_data', 'hotkeys'],

        _tree: function (o, theme, resourcePath, T, params) {
            o.jstree($.extend({
                ui: {
                	selecting_node: T.selectingNode()
                },
                core: {
                    rtl: T.orientation()[0] === 'rtl',
                    strings: {
                        new_node: "...",
                        loading: $.ZAP.message.get($.ZAP.locale.get(), 'loading')
                    },
                },
                themes: {
                    theme: theme,
                    url: resourcePath + '/style.css?' + $.ZAP.version
                },
                types: {
                    types: {
                        root: {
                            icon: {
                                image: resourcePath + '/drive.png'
                            }
                        },
                        "default": {
                            icon: {
                                image: resourcePath + '/folder.png'
                            }
                        },
                        without_children: {
                            icon: {
                                image: resourcePath + '/file.png'
                            }
                        }
                    }
                },
                // todo Azadi Add property  
                searchShow: {
                    show: T.params.searchShow
                },
                json_data: {
                    ajax: {
                        url: T.service(),
                        contentType: "application/x-www-form-urlencoded; charset=utf-8",
                        type: 'GET',
                        data: function (n) {
                            var
                                prime = $.proxy(function (options) {
                                    return this.invoke('method', 'prime', 'execute', [options]);
                                }, T),
                                prepare = $.proxy(function (options) {
                                    return this.invoke('method', 'prepare', 'execute', [options]);
                                }, T);
                            return $.extend({
                                operation: T.operation(),
                                id: n.attr ? n.attr("id") : T.defaultIdVal(), // todo Azadi Add property  
                                search: T.attributes.type == 'lovtree' ? T.temp.modal.find('#searchInput').val() : T.o.find("#searchInput").val()
                            }, prime(), prepare());
                        }
                    }
                }
            }, params));
        },

        checkbox: function (o, theme, resourcePath, T) {
            ZcoreTreeInvokerPlugin._tree(o, theme, resourcePath, T, {
                checkbox: {
                    two_state: T.twoState(),
                    one_select: T.oneSelect()
                },
                plugins: ZcoreTreeInvokerPlugin._plugins.concat(T.oneSelect() ? [] : ['checkbox'])
            });
        },

        crud: function (o, theme, resourcePath, T) {
            ZcoreTreeInvokerPlugin._tree(o, theme, resourcePath, T, {
                contextmenu: {
                    items: function (node) {
                        var menuitems = {
                            createItem: {
                                label: $.ZAP.message.get($.ZAP.locale.get(), 'persist'),
                                action: function () {
                                    T.signal('persistClick', arguments);
                                }
                            },
                            renameItem: {
                                label: $.ZAP.message.get($.ZAP.locale.get(), 'merge'),
                                action: function () {
                                    T.signal('mergeClick', arguments);
                                }
                            },
                            deleteItem: {
                                label: $.ZAP.message.get($.ZAP.locale.get(), 'remove'),
                                action: function () {
                                    T.signal('removeClick', arguments);
                                }
                            },
                            viewItem: {
                                label: $.ZAP.message.get($.ZAP.locale.get(), 'view'),
                                action: function () {
                                    T.signal('viewClick', arguments);
                                }
                            }
                        };
                        //--> set invisible
                        var invisible = T.invisible().split(',');
                        for (var i in invisible) {
                            delete menuitems[invisible[i] + 'Item'];
                        }
                        //--> return item
                        var output = {};
                        switch (node.attr('rel')) {
                            case "root" :
                                output['createItem'] = menuitems.createItem;
                                ParamT = T.params.iconCallback;
                                if (T.params.iconCallback != undefined) {
                                    for (var i = 0; i < T.params.iconCallback.length; i++) {
                                        output[T.params.iconCallback[i].item] = {
                                            'label': T.params.iconCallback[i].label,
                                            'action': T.params.iconCallback[i].action
                                        };
                                    }
                                }
                                return output;
                                break;
                            case "default" :
                                output['createItem'] = menuitems.createItem;
                                output['renameItem'] = menuitems.renameItem;
                                output['viewItem'] = menuitems.viewItem;
                                if (T.params.iconCallback != undefined) {
                                    for (var i = 0; i < T.params.iconCallback.length; i++) {
                                        output[T.params.iconCallback[i].item] = {
                                            'label': T.params.iconCallback[i].label,
                                            'action': T.params.iconCallback[i].action
                                        };
                                    }
                                }

                                return output;
                                break;
                            default :
                                output['createItem'] = menuitems.createItem;
                                output['renameItem'] = menuitems.renameItem;
                                output['viewItem'] = menuitems.viewItem;
                                output['deleteItem'] = menuitems.deleteItem;
                                if (T.params.iconCallback != undefined) {
                                    for (var i = 0; i < T.params.iconCallback.length; i++) {
                                        output[T.params.iconCallback[i].item] = {
                                            'label': T.params.iconCallback[i].label,
                                            'action': T.params.iconCallback[i].action
                                        };
                                    }
                                }
                                return output;
                                break;
                        }
                    }
                },
                plugins: ZcoreTreeInvokerPlugin._plugins.concat(['crrm', 'contextmenu'])
            });
        }

    });

    /*
     * register as jQuery function
     */
    $.fn.ZcoreTreeInvoker = function (key, method, T) {
        switch (key) {
            case 'show':
                var
                    theme = (T.orientation()[0] === 'ltr') ? 'default' : 'default-rtl',
                    resourcePath = $.ZAP.resourceUrl + "/zcore/util/tree/themes/" + theme;
                ZcoreTreeInvokerPlugin[method]($(this).addClass('ui-widget ui-widget-content ui-state-default ui-corner-all'), theme, resourcePath, T);
                break;

            case 'option':
                switch (method) {
                    case 'set':
                        $(this)
                            .bind("select_node.jstree", function (event, data) {
                                $(this).attr('select_node_id', data.inst.data.ui.selected.attr('id'));
                            })
                            .bind("open_node.jstree loaded.jstree", function (event, data) {
                                if (T.oneSelect()) {
                                    var nodeSelected = $(this).attr('select_node_id') || T.valDsc().val[0];
                                    if ($(this).find('.jstree-clicked').length == 0) {
                                        $.jstree._focused().select_node('#' + nodeSelected);
                                    }
                                } else {
                                    var nodeSelected = T.valDsc().val;
                                    if ($.isArray(nodeSelected)) {
                                        for (var i = 0; i < nodeSelected.length; i++) {
                                            if (nodeSelected[i] !== '') {
                                                $.jstree._focused().check_node('#' + nodeSelected[i]);
                                            }
                                        }
                                    }
                                }
                            });
                        return $(this);
                    case 'get':
                        var res = [];
                        if (T.oneSelect()) {
                            $(this).find('.jstree-clicked').each(function () {
                                var
                                    parent = $(this).parent(),
                                    idVal = parent.attr('id')
                                txtVal = parent.find('a:first').text();
                                if (T.completeTitle()) {
                                    parent = parent.parent('li > ul');
                                    while (parent.length) {
                                        parent = parent.parent();
                                        txtVal = parent.find('a:first').text() + " > " + txtVal;
                                        parent = parent.parent('li > ul');
                                    }
                                }
                                res.push([idVal, txtVal]);
                            });
                        } else {
                            $(this).find('.jstree-checked').each(function () {
                                var node = $.jstree._focused()._get_parent('#' + $(this).attr('id'));
                                if (node === -1 || !node.hasClass('jstree-checked')) {
                                    res.push([$(this).attr('id'), $(this).find('a:first').text()]);
                                }
                            });
                        }
                        return res;
                    default:
                        break;
                }
            default:
                break;
        }
        return $(this);
    };

})(jQuery);