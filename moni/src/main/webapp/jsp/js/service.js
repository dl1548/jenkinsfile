var vue = new Vue({
    el: '#service',
    data: {
        hostGroup: '',
        host: '',
        hostGroupModel: '',
        hostGroupView: '',
        hostModel: '',
        hostView: '',
        process: [{
            id: '',
            businessname: '',
            processname: '',
            hostGroup: [],
            host: [],
            application: [],
            item: [],
            hostGroupid: [],
            hostid: [],
            applicationid: [],
            itemid: [],
            hostGroupName: [],
            hostName: [],
            applicationName: [],
            itemName: []
        }],
        checked: 0,
        hostGroupModel2: '',
        host2: '',
        host2Model: '',
        application: '',
        applicationModel: '',
        application_: [],
        application_Model: '',
        item: [],
        itemModel: '',
        item_: '',
        nameVal: '',
        processIndex: '',
        name: '',
        business: '',
        showTriggerView: '',
        singelProcess: '',
        groupsName: '',
        hostsName: '',
        processEdit: '',
        dp: '',
        delprocessindex: '',
        netWorkkeys: '',
        edi: false,
        showProcessindex: '',
        hostidbyapp: '',
        searchProcess: '',
        serviceSearch: ''
    },
    beforeCreate: function () {
        var _this = this;
        $('#load').mLoading("show");
        $.post("/monitor/getHostGroup", function (data) {
            var da = JSON.parse(JSON.parse(data)).result;
            _this.hostGroup = da;
            console.log(da);
        });
        $.post("/monitor/host", function (data) {
            _this.host = data;
            console.log(data);
        });
        $.post("/monitor/getBus", function (data) {
            for (var a = 0; a < data.length; a++) {
                if (data[a].maxFault == 0) {
                    data[a].class = 'back1';
                } else if (data[a].maxFault == 1) {
                    data[a].class = 'back2';
                } else if (data[a].maxFault == 2) {
                    data[a].class = 'back3';
                } else if (data[a].maxFault == 3) {
                    data[a].class = 'back4';
                } else if (data[a].maxFault == 4) {
                    data[a].class = 'back5';
                } else if (data[a].maxFault == 5) {
                    data[a].class = 'back6';
                } else {
                    data[a].class = 'back0';
                }
                $('#load').mLoading("hide");
            }
            _this.business = data;
            console.log(data);
        });
    },
    methods: {
        searchService: function (name) {
            $.post("/monitor/getBus", {name: name}, function (data) {
                for (var a = 0; a < data.length; a++) {
                    if (data[a].priority == 0) {
                        data[a].class = 'nor';
                    } else {
                        data[a].class = 'err';
                    }
                }
                vue.business = data;
                console.log(data);
            });
        },
        rePro: function () {
            $.post("/monitor/getBus", function (data) {
                for (var a = 0; a < data.length; a++) {
                    if (data[a].priority == 0) {
                        data[a].class = 'nor';
                    } else {
                        data[a].class = 'err';
                    }
                }
                vue.business = data;
                console.log(data);
            });
        },
        seaPro: function (i) {
            $.post("/monitor/getBus", {name: i}, function (data) {
                console.log(data);
                if (data[0].process.length > 0) {
                    for (var a = 0; a < data.length; a++) {
                        if (data[a].maxFault == 0) {
                            data[a].class = 'back1';
                        } else if (data[a].maxFault == 1) {
                            data[a].class = 'back2';
                        } else if (data[a].maxFault == 2) {
                            data[a].class = 'back3';
                        } else if (data[a].maxFault == 3) {
                            data[a].class = 'back4';
                        } else if (data[a].maxFault == 4) {
                            data[a].class = 'back5';
                        } else if (data[a].maxFault == 5) {
                            data[a].class = 'back6';
                        } else {
                            data[a].class = 'back0';
                        }
                    }
                    vue.business = data;
                } else {
                    vue.business = [];
                }
            });
        },
        dep: function () {
            $.post("/monitor/deleteView", {name: vue.dp}, function (data) {
                console.log(data);
                //alert(data.data);
                if (data.data = "删除成功") {
                    vue.business.splice(vue.delprocessindex, 1);
                    layui.use(['layer', 'form'], function () {
                        var layer = layui.layer
                            , form = layui.form;
                        layer.alert('删除成功', {icon: 6});
                    });
                }
            });
        },
        dps: function (i, index) {
            console.log(i);
            vue.dp = i;
            vue.delprocessindex = index
        },
        editSubmit: function () {
            for (var a = 0; a < vue.processEdit.length; a++) {
                delete vue.processEdit[a].position
            }
            console.log(vue.processEdit);
            $.post("/monitor/saveBusview", {process: JSON.stringify(vue.processEdit)}, function (data) {
                console.log(data);
                if (data.data == "保存成功") {
                    layui.use(['layer', 'form'], function () {
                        var layer = layui.layer
                            , form = layui.form;
                        layer.alert('保存成功', {icon: 6});
                    });
                    $('#createProcess').modal('hide');
                    $.post("/monitor/getBus", function (data) {
                        for (var a = 0; a < data.length; a++) {
                            if (data[a].maxFault == 0) {
                                data[a].class = 'back1';
                            } else if (data[a].maxFault == 1) {
                                data[a].class = 'back2';
                            } else if (data[a].maxFault == 2) {
                                data[a].class = 'back3';
                            } else if (data[a].maxFault == 3) {
                                data[a].class = 'back4';
                            } else if (data[a].maxFault == 4) {
                                data[a].class = 'back5';
                            } else if (data[a].maxFault == 5) {
                                data[a].class = 'back6';
                            } else {
                                data[a].class = 'back0';
                            }
                        }
                        vue.business = data;
                        console.log(data);
                    });
                }else{
                    layui.use(['layer', 'form'], function () {
                        var layer = layui.layer
                            , form = layui.form;
                        layer.alert(data.msg, {icon: 5});
                    });
                }
            });
        },
        save2: function () {
            console.log(vue.checked);
            console.log(vue.processEdit[vue.checked]);
            console.log(vue.nameVal);
            console.log(vue.hostGroupView);
            console.log(vue.hostView);
            console.log(vue.application_);
            console.log(vue.item_);
            if (typeof vue.checked == 'number') {
                if (vue.nameVal) {
                    if (vue.hostGroupView.length > 0 || vue.hostView.length > 0 || vue.application_.length > 0 || vue.item_.length > 0) {
                        vue.processEdit[vue.checked].processname = vue.nameVal;
                        vue.processEdit[vue.checked].businessname = vue.name;
                        if (vue.hostGroupView.length > 0) {
                            var id = [], name = [];
                            for (var a = 0; a < vue.hostGroupView.length; a++) {
                                id.push(vue.hostGroupView[a].groupid);
                                name.push(vue.hostGroupView[a].name)
                            }
                            vue.processEdit[vue.checked].hostgroupid = id.toString();
                            vue.processEdit[vue.checked].hostgroupname = name.toString();
                        } else {
                            vue.processEdit[vue.checked].hostgroupid = '';
                            vue.processEdit[vue.checked].hostgroupname = '';
                        }
                        if (vue.hostView.length > 0) {
                            var id2 = [], name2 = [];
                            for (var b = 0; b < vue.hostView.length; b++) {
                                id2.push(vue.hostView[b].hostid);
                                name2.push(vue.hostView[b].name)
                            }
                            vue.processEdit[vue.checked].hostid = id2.toString();
                            vue.processEdit[vue.checked].hostname = name2.toString();
                        } else {
                            vue.processEdit[vue.checked].hostid = '';
                            vue.processEdit[vue.checked].hostname = '';
                        }
                        if (vue.application_.length > 0) {
                            var id3 = [], name3 = [];
                            for (var c = 0; c < vue.application_.length; c++) {
                                id3.push(vue.application_[c].applicationid);
                                name3.push(vue.application_[c].name)
                            }
                            vue.processEdit[vue.checked].applicationid = id3.toString();
                            vue.processEdit[vue.checked].applicationname = name3.toString();
                        } else {
                            vue.processEdit[vue.checked].applicationid = '';
                            vue.processEdit[vue.checked].applicationname = '';
                        }
                        if (vue.item_.length > 0) {
                            var id4 = [], name4 = [];
                            for (var d = 0; d < vue.item_.length; d++) {
                                id4.push(vue.item_[d].itemid);
                                name4.push(vue.item_[d].name)
                            }
                            vue.processEdit[vue.checked].itemid = id4.toString();
                            vue.processEdit[vue.checked].itemname = name4.toString();
                        } else {
                            vue.processEdit[vue.checked].itemid = '';
                            vue.processEdit[vue.checked].itemname = '';
                        }
                        console.log(vue.processEdit);
                        layui.use(['layer', 'form'], function () {
                            var layer = layui.layer
                                , form = layui.form;
                            layer.alert('保存成功', {icon: 6});
                        });
                    } else {
                        //alert('至少选择一个监控指标')
                        layui.use(['layer', 'form'], function () {
                            var layer = layui.layer
                                , form = layui.form;
                            layer.alert('至少选择一个监控指标', {icon: 7});
                        });
                    }
                } else {
                    //alert('请输入流程名称')
                    layui.use(['layer', 'form'], function () {
                        var layer = layui.layer
                            , form = layui.form;
                        layer.alert('请输入流程名称', {icon: 7});
                    });
                }
            } else {
                //alert('请选中一个流程')
                layui.use(['layer', 'form'], function () {
                    var layer = layui.layer
                        , form = layui.form;
                    layer.alert('请选择一个流程', {icon: 7});
                });
            }
        },
        makeSureDel: function () {
            vue.processEdit.splice(vue.processIndex, 1);
            vue.checked = '';
            $('.process2').children('.processbtn2').removeClass('btn-primary')
        },
        addProcess2: function () {
            var obj = {
                applicationid: [],
                applicationname: [],
                businessname: '',
                hostgroupid: [],
                hostgroupname: [],
                hostid: [],
                hostname: [],
                id: '',
                itemid: [],
                itemname: [],
                position: '',
                processname: ''
            };
            vue.processEdit.push(obj);
            console.log(vue.processEdit);
        },
        processClick2: function (i) {
            vue.checked = i;
            $('.process2').eq(i).children('.processbtn2').addClass('btn-primary');
            $('.process2').eq(i).siblings('.process2').children('.processbtn2').removeClass('btn-primary');
            console.log(vue.processEdit[i]);
            vue.nameVal = vue.processEdit[i].processname;
            var hostGroup = [], host = [], application = [], item = [];
            if (typeof vue.processEdit[i].hostgroupid == 'string') {
                if (vue.processEdit[i].hostgroupid.split(',').length > 0) {
                    for (var a = 0; a < vue.processEdit[i].hostgroupid.split(',').length; a++) {
                        hostGroup[a] = {
                            name: vue.processEdit[i].hostgroupname.split(',')[a],
                            groupid: vue.processEdit[i].hostgroupid.split(',')[a]
                        }
                    }
                }
            }
            vue.hostGroupView = hostGroup;
            if (typeof vue.processEdit[i].hostid == 'string') {
                if (vue.processEdit[i].hostid.split(',').length > 0) {
                    for (var b = 0; b < vue.processEdit[i].hostid.split(',').length; b++) {
                        host[b] = {
                            name: vue.processEdit[i].hostname.split(',')[b],
                            hostid: vue.processEdit[i].hostid.split(',')[b]
                        }
                    }
                }
            }
            vue.hostView = host;
            if (typeof vue.processEdit[i].applicationid == 'string') {
                if (vue.processEdit[i].applicationid.split(',').length > 0) {
                    for (var c = 0; c < vue.processEdit[i].applicationid.split(',').length; c++) {
                        application[c] = {
                            name: vue.processEdit[i].applicationname.split(',')[c],
                            applicationid: vue.processEdit[i].applicationid.split(',')[c]
                        }
                    }
                }
            }
            vue.application_ = application;
            if (typeof vue.processEdit[i].itemid == 'string') {
                if (vue.processEdit[i].itemid.split(',').length > 0) {
                    for (var d = 0; d < vue.processEdit[i].itemid.split(',').length; d++) {
                        item[d] = {
                            name: vue.processEdit[i].itemname.split(',')[d],
                            itemid: vue.processEdit[i].itemid.split(',')[d]
                        }
                    }
                }
            }
            vue.item_ = item;
        },
        editProcess: function (i) {
            console.log(i);
            if (typeof i == "number") {

            } else {
                i = vue.showProcessindex
            }
            console.log(i);
            console.log(vue.showProcessindex);
            console.log(vue.business[i]);
            vue.name = vue.business[i].process[0].businessname;
            vue.processEdit = vue.business[i].process;
            vue.nameVal = vue.processEdit[0].processname;
            var hostGroup = [], host = [], application = [], item = [];
            if (vue.processEdit[0].hostgroupid.split(',').length > 0) {
                for (var a = 0; a < vue.processEdit[0].hostgroupid.split(',').length; a++) {
                    hostGroup[a] = {
                        name: vue.processEdit[0].hostgroupname.split(',')[a],
                        hostgroupid: vue.processEdit[0].hostgroupid.split(',')[a]
                    }
                }
            }
            vue.hostGroupView = hostGroup;
            if (vue.processEdit[0].hostid.split(',').length > 0) {
                for (var b = 0; b < vue.processEdit[0].hostid.split(',').length; b++) {
                    host[b] = {
                        name: vue.processEdit[0].hostname.split(',')[b],
                        hostid: vue.processEdit[0].hostid.split(',')[b]
                    }
                }
            }
            vue.hostView = host;
            if (vue.processEdit[0].applicationid.split(',').length > 0) {
                for (var c = 0; c < vue.processEdit[0].applicationid.split(',').length; c++) {
                    application[c] = {
                        name: vue.processEdit[0].applicationname.split(',')[c],
                        applicationid: vue.processEdit[0].applicationid.split(',')[c]
                    }
                }
            }
            vue.application_ = application;
            if (vue.processEdit[0].itemname.split(',').length > 0) {
                for (var d = 0; d < vue.processEdit[0].itemname.split(',').length; d++) {
                    item[d] = {
                        name: vue.processEdit[0].itemname.split(',')[d],
                        itemid: JSON.parse(vue.processEdit[0].itemkey)[d]
                    }
                }
            }
            vue.item_ = item;
            this.$nextTick(function () {
                $('.process2').eq(0).find('.processbtn2').addClass('btn-primary')
            })
        },
        seeGroups: function (i) {
            var arr = [];
            for (var a = 0; a < vue.hostGroup.length; a++) {
                if (vue.singelProcess[i].groups.indexOf(parseInt(vue.hostGroup[a].groupid)) >= 0) {
                    arr.push(vue.hostGroup[a].name)
                }
            }
            vue.groupsName = arr;
            console.log(arr)
        },
        seeHosts: function (i) {
            var arr = [];
            for (var a = 0; a < vue.host.length; a++) {
                if (vue.singelProcess[i].hosts.indexOf(parseInt(vue.host[a].hostid)) >= 0) {
                    arr.push(vue.hostGroup[a].name)
                }
            }
            vue.hostsName = arr;
            console.log(arr)
        },
        clickProcess: function (i) {
            $('.allProcess').eq(i).children('.showProcess').addClass('newProcessStyle');
            $('.allProcess').eq(i).siblings().children('.showProcess').removeClass('newProcessStyle');
            var obj = {
                0: 'errorcolor1',
                1: 'errorcolor2',
                2: 'errorcolor3',
                3: 'errorcolor4',
                4: 'errorcolor5',
                5: 'errorcolor6'
            };
            for (var a = 0; a < vue.showTriggerView[i].xiangqing.length; a++) {
                vue.showTriggerView[i].xiangqing[a].css = obj[vue.showTriggerView[i].xiangqing[a].priority]
            }
            vue.singelProcess = vue.showTriggerView[i].xiangqing;
            console.log(vue.singelProcess);
        },
        showProcess: function (name, index) {
            vue.showProcessindex = index;
            var obj = {
                0: 'errorcolor1',
                1: 'errorcolor2',
                2: 'errorcolor3',
                3: 'errorcolor4',
                4: 'errorcolor5',
                5: 'errorcolor6'
            };
            var ajax = $.post("/monitor/showTriggerView", {name: name}, function (data) {
                console.log(JSON.parse(JSON.parse(data)));
                var da = JSON.parse(JSON.parse(data));
                console.log(Object.values(da)[0]);
                vue.showTriggerView = Object.values(da)[0];
                for (var a = 0; a < vue.showTriggerView[0].xiangqing.length; a++) {
                    vue.showTriggerView[0].xiangqing[a].css = obj[vue.showTriggerView[0].xiangqing[a].priority]
                }
                vue.singelProcess = vue.showTriggerView[0].xiangqing;
            });
            $.when(ajax).done(function () {
                $('.showProcess').removeClass('newProcessStyle');
                $('.showProcess').first().addClass('newProcessStyle');
            })
        },
        subProcess: function () {
            console.log(vue.process);
            if (vue.name && vue.process.length > 0) {
                var arr = [];
                for (var a = 0; a < vue.process.length; a++) {
                    arr[a] = {
                        applicationid: vue.process[a].applicationid.toString(),
                        businessname: vue.name,
                        hostid: vue.process[a].hostid.toString(),
                        hostgroupid: vue.process[a].hostGroupid.toString(),
                        id: '',
                        itemid: vue.process[a].itemid,
                        processname: vue.process[a].processname,
                        hostgroupname: vue.process[a].hostGroupName.toString(),
                        hostname: vue.process[a].hostName.toString(),
                        applicationname: vue.process[a].applicationName.toString(),
                        itemname: vue.process[a].itemName.toString()
                    }
                }
                console.log(JSON.stringify(arr));
                $.post("/monitor/saveBusview", {process: JSON.stringify(arr)}, function (data) {
                    console.log(data);
                    if (data.data == '保存成功') {
                        layui.use(['layer', 'form'], function () {
                            var layer = layui.layer
                                , form = layui.form;
                            layer.alert('保存成功', {icon: 6});
                        });
                        $('#create').modal('hide');
                        $.post("/monitor/getBus", function (data) {
                            for (var a = 0; a < data.length; a++) {
                                if (data[a].maxFault == 0) {
                                    data[a].class = 'back1';
                                } else if (data[a].maxFault == 1) {
                                    data[a].class = 'back2';
                                } else if (data[a].maxFault == 2) {
                                    data[a].class = 'back3';
                                } else if (data[a].maxFault == 3) {
                                    data[a].class = 'back4';
                                } else if (data[a].maxFault == 4) {
                                    data[a].class = 'back5';
                                } else if (data[a].maxFault == 5) {
                                    data[a].class = 'back6';
                                } else {
                                    data[a].class = 'back0';
                                }
                            }
                            vue.business = data;
                            console.log(data);
                        });
                    }
                });
            } else {
                //alert('请输入业务名称')
                layui.use(['layer', 'form'], function () {
                    var layer = layui.layer
                        , form = layui.form;
                    layer.alert('请输入业务名称', {icon: 7});
                });
            }
        },
        getIndex: function (i) {
            vue.processIndex = i
        },
        createNew: function () {
            vue.checked = 0;
            vue.nameVal = '';
            vue.name = '';
            vue.hostGroupView = [];
            vue.hostView = [];
            vue.application_ = [];
            vue.item_ = [];
            vue.procsee = [{
                id: '',
                businessname: '',
                processname: '',
                hostGroup: [],
                host: [],
                application: [],
                item: [],
                hostGroupid: [],
                hostid: [],
                applicationid: [],
                itemid: [],
                hostGroupName: [],
                hostName: [],
                applicationName: [],
                itemName: []
            }];
            $('.process').eq(0).find('.processbtn').addClass('btn-primary')
        },
        delItem: function (i) {
            var arr = [];
            for (var a = 0; a < vue.item_.length; a++) {
                arr[a] = a
            }
            if (arr.indexOf(i) >= 0) {
                vue.item_.splice(i, 1)
            }
        },
        itemPush: function () {
            var key = Object.keys(vue.item), val = Object.values(vue.item);
            var arr = [];
            for (var b = 0; b < key.length; b++) {
                arr[b] = {id:key[b],name:val[b]}
            }
            console.log(arr);
            for (var a = 0; a < vue.itemModel.length; a++) {
                if (vue.item_.indexOf(arr[vue.itemModel[a]]) >= 0) {

                } else {
                    vue.item_.push(arr[vue.itemModel[a]])
                }
            }
        },
        getItem: function () {
            console.log(vue.application_Model);
            console.log(vue.hostidbyapp);
            $.ajax({
                type: 'post',
                url: '/monitor/getItemByApp',
                traditional: true,
                data: {
                    applicationId: vue.application_Model,
                    hostid: vue.hostidbyapp
                },
                success: function (data) {
                    console.log(data);
                    if (Object.keys(data).length > 0) {

                    } else {
                        //alert('此应用集无监控项')
                        layui.use(['layer', 'form'], function () {
                            var layer = layui.layer
                                , form = layui.form;
                            layer.alert('此应用集无监控项', {icon: 7});
                        });
                    }
                    vue.item = data
                }
            })
        },
        delapplication: function (i) {
            console.log(vue.application_);
            var arr = [];
            for (var a = 0; a < vue.application_.length; a++) {
                arr[a] = a
            }
            if (arr.indexOf(i) >= 0) {
                vue.application_.splice(i, 1)
            }
        },
        applicationChange: function () {
            console.log(vue.applicationModel);
            for (var a = 0; a < vue.applicationModel.length; a++) {
                if (vue.application_.indexOf(vue.application[vue.applicationModel[a]]) >= 0) {

                } else {
                    vue.application_.push(vue.application[vue.applicationModel[a]])
                }
            }
        },
        getHostByHostGroup: function () {
            $.post("/monitor/hostGet", {hostGroupId: vue.hostGroupModel2}, function (data) {
                var id = Object.keys(data), name = Object.values(data), arr = [];
                for (var a = 0; a < id.length; a++) {
                    var obj = {name: '', id: ''};
                    arr[a] = obj;
                    arr[a].name = name[a];
                    arr[a].id = id[a];
                }
                vue.host2 = arr;
                console.log(arr);
            });
        },
        getApplicationByHost: function () {
            $.post("/monitor/getApplicationByHost", {hostId: vue.host2Model}, function (data) {
                console.log(JSON.parse(JSON.parse(data)).result);
                vue.application = JSON.parse(JSON.parse(data)).result[0].applications;
            });
        },
        processClick: function (i) {
            vue.checked = i;
            $('.process').eq(i).children('.processbtn').addClass('btn-primary');
            $('.process').eq(i).siblings('.process').children('.processbtn').removeClass('btn-primary');
            vue.nameVal = vue.process[i].processname;
            vue.hostView = vue.process[i].host;
            vue.hostGroupView = vue.process[i].hostGroup;
            vue.application_ = vue.process[i].application;
            vue.item_ = vue.process[i].item;
            console.log(vue.process);
        },
        save: function () {
            if (typeof vue.checked == 'number') {
                if (vue.nameVal) {
                    if (vue.hostGroupView.length > 0 || vue.hostView.length > 0 || vue.application_.length > 0 || vue.item_.length > 0) {
                        vue.process[vue.checked].processname = vue.nameVal;
                        if (vue.hostGroupView.length > 0) {
                            for (var a = 0; a < vue.hostGroupView.length; a++) {
                                vue.process[vue.checked].hostGroup[a] = {
                                    name: vue.hostGroupView[a].name,
                                    groupid: vue.hostGroupView[a].groupid
                                };
                                vue.process[vue.checked].hostGroupid[a] = vue.hostGroupView[a].groupid;
                                vue.process[vue.checked].hostGroupName[a] = vue.hostGroupView[a].name;
                            }
                        }
                        if (vue.hostView.length > 0) {
                            for (var b = 0; b < vue.hostView.length; b++) {
                                vue.process[vue.checked].host[b] = {
                                    name: vue.hostView[b].name,
                                    hostid: vue.hostView[b].hostid
                                };
                                vue.process[vue.checked].hostid[b] = vue.hostView[b].hostid;
                                vue.process[vue.checked].hostName[b] = vue.hostView[b].name;
                            }
                        }
                        if (vue.application_.length > 0) {
                            for (var c = 0; c < vue.application_.length; c++) {
                                vue.process[vue.checked].application[c] = {
                                    name: vue.application_[c].name,
                                    applicationid: vue.application_[c].applicationid
                                };
                                vue.process[vue.checked].applicationid[c] = vue.application_[c].applicationid;
                                vue.process[vue.checked].applicationName[c] = vue.application_[c].name;
                            }
                        }
                        if (vue.item_.length > 0) {
                            for (var d = 0; d < vue.item_.length; d++) {
                                vue.process[vue.checked].item[d] = obj4 = {
                                    name: vue.item_[d].name,
                                    itemid: vue.item_[d].itemid
                                };
                                vue.process[vue.checked].itemid[d] = vue.item_[d].id;
                                vue.process[vue.checked].itemName[d] = vue.item_[d].name;
                            }
                        }
                        console.log(vue.process);
                        //alert('保存成功')
                        layui.use(['layer', 'form'], function () {
                            var layer = layui.layer
                                , form = layui.form;
                            layer.alert('保存成功', {icon: 6});
                        });
                    } else {
                        //alert('至少选择一个监控指标')
                        layui.use(['layer', 'form'], function () {
                            var layer = layui.layer
                                , form = layui.form;
                            layer.alert('至少选择一个监控指标', {icon: 7});
                        });
                    }
                } else {
                    //alert('请输入流程名称')
                    layui.use(['layer', 'form'], function () {
                        var layer = layui.layer
                            , form = layui.form;
                        layer.alert('请输入流程名称', {icon: 7});
                    });
                }
            } else {
                //alert('请选中一个流程')
                layui.use(['layer', 'form'], function () {
                    var layer = layui.layer
                        , form = layui.form;
                    layer.alert('请选择一个流程', {icon: 7});
                });
            }
        },
        addProcess: function () {
            var obj = {
                name: '',
                hostGroup: [],
                host: [],
                application: [],
                item: [],
                hostGroupid: [],
                hostid: [],
                applicationid: [],
                itemid: [],
                hostGroupName: [],
                hostName: [],
                applicationName: [],
                itemName: []
            };
            vue.process.push(obj);
            console.log(vue.process);
        },
        delProcess: function () {
            var arr = [];
            for (var a = 0; a < vue.process.length; a++) {
                arr[a] = a
            }
            if (arr.indexOf(vue.processIndex) >= 0) {
                vue.process.splice(vue.processIndex, 1)
            }
            vue.checked = '';
            $('.process').children('.processbtn').removeClass('btn-primary')
        },
        addHostGroup: function () {
            var arr = [];
            for (var a = 0; a < vue.hostGroupModel.length; a++) {
                arr.push(vue.hostGroup[vue.hostGroupModel[a]])
            }
            vue.hostGroupView = arr;

        },
        delHostGroup: function (i) {
            var arr = [];
            for (var a = 0; a < vue.hostGroupView.length; a++) {
                arr[a] = a
            }
            if (arr.indexOf(i) >= 0) {
                vue.hostGroupView.splice(i, 1)
            }
        },
        addHost: function () {
            var arr = [];
            for (var a = 0; a < vue.hostModel.length; a++) {
                arr.push(vue.host[vue.hostModel[a]])
            }
            vue.hostView = arr;
            vue.hostModel = [];
            console.log(arr);
            var id = [];
            for (var b = 0; b < vue.hostView.length; b++) {
                id.push(vue.hostView[b].hostid)
            }
            console.log(id);
            vue.hostidbyapp = id;
            $.ajax({
                type: 'post',
                url: '/monitor/getApplicationByHost',
                traditional: true,
                data: {
                    hostId: id
                },
                success: function (data) {
                    vue.application = JSON.parse(JSON.parse(data)).result;
                    console.log(vue.application);
                }
            })
        },
        selectApplication: function () {
            var id = [];
            for (var b = 0; b < vue.hostView.length; b++) {
                id.push(vue.hostView[b].hostid)
            }
            console.log(id);
            vue.hostidbyapp = id;
            if (id.length > 0) {
                $.ajax({
                    type: 'post',
                    url: '/monitor/getApplicationByHost',
                    traditional: true,
                    data: {
                        hostId: id
                    },
                    success: function (data) {
                        console.log(data);
                        vue.application = JSON.parse(JSON.parse(data)).result;
                        console.log(vue.application);
                    }
                })
            } else {
                //alert('请先选择主机')
                layui.use(['layer', 'form'], function () {
                    var layer = layui.layer
                        , form = layui.form;
                    layer.alert('请选择主机', {icon: 7});
                });
            }
        },
        delHost: function (i) {
            var arr = [];
            for (var a = 0; a < vue.hostView.length; a++) {
                arr[a] = a
            }
            if (arr.indexOf(i) >= 0) {
                vue.hostView.splice(i, 1)
            }
        }
    }
});
layui.use('element', function () {
    var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块

    //监听导航点击
    element.on('nav(demo)', function (elem) {
        //console.log(elem)
        layer.msg(elem.text());
    });
});

function cleanCookie() {
    $.cookie('hostName', null);
    $.cookie('type', null);
    window.location.href = "Infrastructure.html";
}