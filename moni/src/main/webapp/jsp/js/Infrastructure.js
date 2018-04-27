var vue1 = new Vue({
    el: '#Infrastructure',
    data: {
        d: '',
        se: '',
        checkModel: [],
        search: '',
        hostGroup: '',
        hgp: '',
        hosts: '',
        tips: '',
        tip: [],
        tip2: [],
        status: '',
        tipindex: '',
        tipindex2: '',
        hide1: false,
        hide2: false,
        hide3: false,
        hide4: false,
        m1: false,
        m2: false,
        m3: false,
        m4: false,
        normal: '',
        error: '',
        ip: '',
        name: '',
        statu: '',
        stamod: [],
        ipAndDns3: '',
        sho: false,
        sho2: false,
        template: '',
        hgp2: '',
        hosts2: '',
        h1: true,
        h2: true,
        h3: true,
        h4: true,
        mo1: false,
        mo2: false,
        mo3: false,
        mo4: false,
        iad: '',
        s: false,
        s2: false,
        iad2: '',
        s_: false,
        s2_: false,
        iad3: '',
        s_3: false,
        s2_3: false,
        type: '',
        typ: '',
        port1: '',
        ip1: '',
        dns1: '',
        port2: '',
        name2: '',

        trigger: '',
        triggerSearch: '',
        triggerModel: [],
        getTriggerName: '',
        triggerName: '',
        triggerExpression: '',
        multipleProblems: '',
        triggerDescription: '',
        severity: '',
        triggerStatus: '',

        da: [
            {
                port: 10050,
                type: '1',
                ip: '',
                dns: '',
                main: '',
                useIp: ''
            }
        ],
        obj1: {
            port: 10050,
            type: '1',
            ip: '',
            dns: '',
            main: '',
            useIp: ''
        },
        vm: '0',
        da2: [
            {
                port: 161,
                type: '2',
                ip: '',
                dns: '',
                main: '',
                useIp: ''
            }
        ],
        obj2: {
            port: 161,
            type: '2',
            ip: '',
            dns: '',
            main: '',
            useIp: ''
        },
        vm2: '0',
        da3: [{
            Macro: '',
            Value: ''
        }],
        obj3: {
            Macro: '',
            Value: ''
        },
        da4: [
            {
                port: 623,
                type: '3',
                ip: '',
                dns: '',
                main: '',
                useIp: ''
            }
        ],
        obj4: {
            port: 623,
            type: '3',
            ip: '',
            dns: '',
            main: '',
            useIp: ''
        },
        vm4: '0',
        templateModel: '',
        temp: [],
        ty2: '',
        ty3: '',
        ty4: '',
        ty5: '',
        ty6: '',
        age: [],
        snm: [],
        ipm: [],
        mmo1: '',
        mmo2: '',
        mmo3: '',
        macro: '',
        e1: '',
        e2: '',
        e3: '',
        e4: '',
        edittemp: [],
        mark: '',
        markModel: '',
        markModel2: '',
        ehid: '',
        showHost: '',
        showIp: '',
        showName: '',
        showuname: '',
        showstatus_: '',
        showuptime: '',
        showAgent: false,
        showSNMP: false,
        showIPMI: false,
        showcreatedate: '',
        showmark: '',
        showgroups: '',
        showTemplates: '',
        showEvent: '',
        showda: false,
        ramKey: '',
        ramKeyModel: 0,
        ramin: '',
        ranOut: '',
        pc: false,
        network: false,
        networkin: '',
        networkout: '',
        netWorkkeysmodel: 0,
        netWorkkeysmodel2: 1,
        netWorkkeysmodel3: 2,
        netWorkkeysmodel4: 3,
        netWorkkeys2: '',
        timeModel: 3600,
        showHostId: '',
        hosttype: '',
        count: '',
        className: {
            network: 'sitemap',
            Windows: 'windows',
            Linux: 'linux'
        },
        timeModel2: '3600',
        showHostid: '',
        inputModel: '1',
        markSearch: '',
        hostGroupModel: 'a',
        typeGroupModel: '0',
        lastData: '',
        hisData: '',
        hosti: '',
        hostho: '',
        netWorkkeyss: '',
        num:'1'
    },
    beforeCreate: function () {
        console.log( $.cookie('hostName'));
        console.log( $.cookie('type'));
        var _this = this;
        var obj = {
            0: 'avi1',
            1: 'avi2',
            2: 'avi3'
        };
        $('#load').mLoading("show");
        $.post("/monitor/getFaultHostCount", function (data) {
            var d = JSON.parse(JSON.parse(data));
            _this.normal = d.normal;
            _this.error = d.count;
        });
        setInterval(function () {
            $.post("/monitor/getFaultHostCount", function (data) {
                var d = JSON.parse(JSON.parse(data));
                _this.normal = d.normal;
                _this.error = d.count;
            });
        }, 30000);
        if($.cookie('type') !== 'null'){
            $.post("/monitor/getResource", {select: $.cookie('type')}, function (data) {
                console.log(JSON.parse(data[0]));
                console.log(JSON.parse(data[0]).count);
                if (data.length > 0) {
                    vue1.count = JSON.parse(data[0]).count;
                } else {
                    vue1.count = 0
                }
                var arr = [];
                var obj = {
                    0: 'avi1',
                    1: 'avi2',
                    2: 'avi3'
                };
                for (var a = 0; a < data.length; a++) {
                    arr.push(JSON.parse(data[a]));
                    if (arr[a].items) {
                        arr[a].cpu = arr[a].items[0].cpuUsed;
                        arr[a].ram = arr[a].items[0].memotyUsed;
                        arr[a].typ = arr[a].items[0].type
                    } else {
                        arr[a].cpu = '';
                        arr[a].ram = '';
                        arr[a].typ = ''
                    }
                    if (arr[a].status == '0') {
                        vue1.stamod.push(a)
                    }
                    arr[a].back = vue1.className[arr[a].typ];
                    arr[a].available_ = obj[arr[a].available];
                    arr[a].ipmi_available_ = obj[arr[a].ipmi_available];
                    arr[a].snmp_available_ = obj[arr[a].snmp_available];
                    arr[a].jmx_available_ = obj[arr[a].jmx_available];
                    if (arr[a].available_ == 'avi2' || arr[a].ipmi_available_ == 'avi2' || arr[a].snmp_available_ == 'avi2' || arr[a].jmx_available_ == 'avi2') {
                        arr[a].color = 'avi2'
                    } else {
                        arr[a].color = 'avi3'
                    }
                    if (arr[a].typ == 'network') {
                        arr[a].typ = 'Network'
                    }
                }
                $('#load').mLoading("hide");
                vue1.d = arr;
            });
        } else if($.cookie('hostName') !== 'null'){
            $.post("/monitor/getResource", {resourcename: $.cookie('hostName')}, function (data) {
                console.log( $.cookie('hostName'));
                console.log(data);
                if (data.length > 0) {
                    vue1.count = JSON.parse(data[0]).count;
                } else {
                    vue1.count = 0
                }
                var arr = [];
                var obj = {
                    0: 'avi1',
                    1: 'avi2',
                    2: 'avi3'
                };
                for (var a = 0; a < data.length; a++) {
                    arr.push(JSON.parse(data[a]));
                    if (arr[a].items) {
                        arr[a].cpu = arr[a].items[0].cpuUsed;
                        arr[a].ram = arr[a].items[0].memotyUsed;
                        arr[a].typ = arr[a].items[0].type
                    } else {
                        arr[a].cpu = '';
                        arr[a].ram = '';
                        arr[a].typ = ''
                    }
                    if (arr[a].status == '0') {
                        vue1.stamod.push(a)
                    }
                    arr[a].back = vue1.className[arr[a].typ];
                    arr[a].available_ = obj[arr[a].available];
                    arr[a].ipmi_available_ = obj[arr[a].ipmi_available];
                    arr[a].snmp_available_ = obj[arr[a].snmp_available];
                    arr[a].jmx_available_ = obj[arr[a].jmx_available];
                    if (arr[a].available_ == 'avi2' || arr[a].ipmi_available_ == 'avi2' || arr[a].snmp_available_ == 'avi2' || arr[a].jmx_available_ == 'avi2') {
                        arr[a].color = 'avi2'
                    } else {
                        arr[a].color = 'avi3'
                    }
                    if (arr[a].typ == 'network') {
                        arr[a].typ = 'Network'
                    }
                }
                console.log(arr);
                $('#load').mLoading("hide");
                vue1.d = arr;
            });
        }else{
            var ajax = $.post("/monitor/getResource", function (data) {
                vue1.count = JSON.parse(data[0]).count;
                var arr = [];
                for (var a = 0; a < data.length; a++) {
                    arr.push(JSON.parse(data[a]));
                    if (arr[a].items) {
                        arr[a].cpu = arr[a].items[0].cpuUsed;
                        arr[a].ram = arr[a].items[0].memotyUsed;
                        arr[a].typ = arr[a].items[0].type
                    } else {
                        arr[a].cpu = '';
                        arr[a].ram = '';
                        arr[a].typ = ''
                    }
                    if (arr[a].status == '0') {
                        _this.stamod.push(a)
                    }
                    arr[a].back = vue1.className[arr[a].typ];
                    arr[a].available_ = obj[arr[a].available];
                    arr[a].ipmi_available_ = obj[arr[a].ipmi_available];
                    arr[a].snmp_available_ = obj[arr[a].snmp_available];
                    arr[a].jmx_available_ = obj[arr[a].jmx_available];
                    if (arr[a].available_ == 'avi2' || arr[a].ipmi_available_ == 'avi2' || arr[a].snmp_available_ == 'avi2' || arr[a].jmx_available_ == 'avi2') {
                        arr[a].color = 'avi2'
                    } else {
                        arr[a].color = 'avi3'
                    }
                    if (arr[a].typ == 'network') {
                        arr[a].typ = 'Network'
                    }
                }
                console.log(arr);
                _this.d = arr;
            });
            setInterval(function () {
                $.post("/monitor/getResource", function (data) {
                    if (data.length > 0) {
                        vue1.count = JSON.parse(data[0]).count;
                    } else {
                        vue1.count = 0
                    }
                    var arr = [];
                    for (var a = 0; a < data.length; a++) {
                        arr.push(JSON.parse(data[a]));
                        if (arr[a].items) {
                            arr[a].cpu = arr[a].items[0].cpuUsed;
                            arr[a].ram = arr[a].items[0].memotyUsed;
                            arr[a].typ = arr[a].items[0].type
                        } else {
                            arr[a].cpu = '';
                            arr[a].ram = '';
                            arr[a].typ = ''
                        }
                        if (arr[a].status == '0') {
                            _this.stamod.push(a)
                        }
                        arr[a].back = vue1.className[arr[a].typ];
                        arr[a].available_ = obj[arr[a].available];
                        arr[a].ipmi_available_ = obj[arr[a].ipmi_available];
                        arr[a].snmp_available_ = obj[arr[a].snmp_available];
                        arr[a].jmx_available_ = obj[arr[a].jmx_available];
                        if (arr[a].available_ == 'avi2' || arr[a].ipmi_available_ == 'avi2' || arr[a].snmp_available_ == 'avi2' || arr[a].jmx_available_ == 'avi2') {
                            arr[a].color = 'avi2'
                        } else {
                            arr[a].color = 'avi3'
                        }
                        if (arr[a].typ == 'network') {
                            arr[a].typ = 'Network'
                        }
                    }
                    console.log(arr);
                });
            }, 30000);
            $.when(ajax).done(function () {
                $('#load').mLoading("hide");
                layui.use(['laypage', 'layer', 'laydate'], function () {
                    var laypage = layui.laypage, layer = layui.layer, laydate = layui.laydate;
                    laypage.render({
                        elem: 'Pagination5'
                        , count: vue1.count
                        , limit: 20
                        , layout: ['count', 'prev', 'page', 'next']
                        , jump: function (obj, first) {
                            if (!first) {
                                $('#load').mLoading("show");
                                var obj2 = {
                                    0: 'avi1',
                                    1: 'avi2',
                                    2: 'avi3'
                                };
                                $.post("/monitor/getResource", {pagenum: obj.curr}, function (data) {
                                    $('#load').mLoading("hide");
                                    var arr = [];
                                    for (var a = 0; a < data.length; a++) {
                                        arr.push(JSON.parse(data[a]));
                                        if (arr[a].items) {
                                            arr[a].cpu = arr[a].items[0].cpuUsed;
                                            arr[a].ram = arr[a].items[0].memotyUsed;
                                            arr[a].typ = arr[a].items[0].type
                                        } else {
                                            arr[a].cpu = '';
                                            arr[a].ram = '';
                                            arr[a].typ = ''
                                        }
                                        if (arr[a].status == '0') {
                                            _this.stamod.push(a)
                                        }
                                        arr[a].back = vue1.className[arr[a].typ];
                                        arr[a].available_ = obj2[arr[a].available];
                                        arr[a].ipmi_available_ = obj2[arr[a].ipmi_available];
                                        arr[a].snmp_available_ = obj2[arr[a].snmp_available];
                                        arr[a].jmx_available_ = obj2[arr[a].jmx_available];
                                        if (arr[a].available_ == 'avi2' || arr[a].ipmi_available_ == 'avi2' || arr[a].snmp_available_ == 'avi2' || arr[a].jmx_available_ == 'avi2') {
                                            arr[a].color = 'avi2'
                                        } else {
                                            arr[a].color = 'avi3'
                                        }
                                        if (arr[a].typ == 'network') {
                                            arr[a].typ = 'Network'
                                        }
                                    }
                                    console.log(arr);
                                    vue1.d = arr;
                                });
                            }
                        }
                    });
                });
            });
        }
        $.post("/monitor/hostGroup", function (data) {
            _this.hostGroup = data;
            console.log(data);
        });
        $.post("/monitor/getTemplate", function (data) {
            _this.template = data;
            console.log(data);
        });
    },
    methods: {
        createTriggers: function () {
            vue1.triggerName = '';
            vue1.triggerExpression = '';
            vue1.multipleProblems = false;
            vue1.triggerDescription = '';
            vue1.severity = '';
            vue1.triggerStatus = true;
        },
        triggerCheckAll: function (event) {
            var _this = this;
            if (!event.currentTarget.checked) {
                this.triggerModel = [];
            } else { //实现全选
                _this.triggerModel = [];
                _this.trigger.forEach(function (item, i) {
                    _this.triggerModel.push(i);
                });
            }
        },
        getTriggerData: function (i, data, id) {
            console.log(data[i]);
            vue1.datatriggerid = id;
            var obj = {
                0: true,
                1: false
            };
            vue1.triggerName = data[i].description;
            vue1.triggerExpression = data[i].expression;
            vue1.multipleProblems = obj[data[i].type];
            vue1.triggerDescription = data[i].comments;
            vue1.severity = data[i].priority;
            vue1.triggerStatus = obj[data[i].status];
        },
        createSubmit: function (triggerName, triggerExpression, multipleProblems,
                                triggerDescription, severity, triggerStatus) {
            console.log(triggerName);
            console.log(triggerExpression);
            console.log(multipleProblems);
            console.log(triggerDescription);
            console.log(severity);
            console.log(triggerStatus);
            $.ajax({
                type: 'post',
                url: '/monitor/newTrigger',
                traditional: true,
                data: {
                    description: triggerName,
                    type: triggerStatus,
                    priority: severity,
                    expression: triggerExpression,
                    comments: triggerDescription,
                    status: triggerStatus
                },
                dataType: 'json',
                success: function (data) {
                    console.log(data);
                    if (JSON.parse(data).result) {
                        $('#createTrigger').modal('hide');
                        $.post("/monitor/getTriggerByTem", {template: vue1.getTriggerName}, function (data) {
                            var d = JSON.parse(JSON.parse(data)).result;
                            console.log(d);
                            vue1.trigger = d;
                            for (var a = 0; a < d.length; a++) {
                                if (d[a].status == '0') {
                                    vue1.status.push(a)
                                }
                            }
                        });
                        //alert('创建成功');
                        layui.use(['layer', 'form'], function () {
                            var layer = layui.layer
                                , form = layui.form;
                            layer.alert('创建成功', {icon: 6});
                        });
                    } else {
                        //alert(JSON.parse(data).error.data)
                        layui.use(['layer', 'form'], function () {
                            var layer = layui.layer
                                , form = layui.form;
                            layer.alert(JSON.parse(data).error.data, {icon: 7});
                        });
                    }
                }
            });
        },
        updateTrigger: function (triggerName, triggerExpression, multipleProblems,
                                 triggerDescription, severity, triggerStatus) {
            $.ajax({
                type: 'post',
                url: '/monitor/updateTrigger',
                traditional: true,
                data: {
                    description: triggerName,
                    type: triggerStatus,
                    priority: severity,
                    expression: triggerExpression,
                    comments: triggerDescription,
                    status: triggerStatus,
                    triggerId: vue1.datatriggerid
                },
                dataType: 'json',
                success: function (data) {
                    console.log(data);
                    if (JSON.parse(data).result) {
                        $('#updateTrigger').modal('hide');
                        $.post("/monitor/getTriggerByTem", {template: vue1.getTriggerName}, function (data) {
                            var d = JSON.parse(JSON.parse(data)).result;
                            console.log(d);
                            vue1.trigger = d;
                            for (var a = 0; a < d.length; a++) {
                                if (d[a].status == '0') {
                                    vue1.status.push(a)
                                }
                            }
                        });
                        //alert('创建成功');
                        layui.use(['layer', 'form'], function () {
                            var layer = layui.layer
                                , form = layui.form;
                            layer.alert('创建成功', {icon: 6});
                        });
                    } else {
                        //alert(JSON.parse(data).error.data)
                        layui.use(['layer', 'form'], function () {
                            var layer = layui.layer
                                , form = layui.form;
                            layer.alert(JSON.parse(data).error.data, {icon: 6});
                        });
                    }
                }
            })
        },
        delTrigger: function (i, data) {
            console.log(i);
            if (i.length > 0) {
                var arr = [], ar = [];
                for (var a = 0; a < i.length; a++) {
                    arr.push(data[i[a]].triggerid)
                }
                for (var b = 0; b < data.length; b++) {
                    if (i.indexOf(b) >= 0) {

                    } else {
                        ar.push(data[b])
                    }
                }
                console.log(arr);
                $.ajax({
                    type: 'post',
                    url: '/monitor/deleteTrigger',
                    traditional: true,
                    data: {triggerIds: arr},
                    success: function (da) {
                        if (JSON.parse(JSON.parse(da)).result) {
                            //alert('删除成功');
                            layui.use(['layer', 'form'], function () {
                                var layer = layui.layer
                                    , form = layui.form;
                                layer.alert('删除成功', {icon: 6});
                            });
                            vue6.triggerModel = [];
                            vue6.trigger = ar;
                        }
                    }
                });
            } else {
                //alert('请选择触发器')
                layui.use(['layer', 'form'], function () {
                    var layer = layui.layer
                        , form = layui.form;
                    layer.alert('请选择触发器', {icon: 7});
                });
            }
        },


        reShow: function (i, ho, typ,t) {
            console.log(i);
            console.log(ho);
            console.log(typ);
            chart1.showLoading();
            chart4.showLoading();
            chart2.showLoading();
            chart3.showLoading();
            chart5.showLoading();
            chart6.showLoading();
            chart7.showLoading();
            chart8.showLoading();
            chart9.showLoading();
            chart10.showLoading();
            chart11.showLoading();
            chart12.showLoading();
            chart13.showLoading();

            function timestampToTime(timestamp) {
                var date = new Date(timestamp * 1000);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
                Y = date.getFullYear() + '-';
                M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
                D = date.getDate() + ' ';
                h = date.getHours() + ':';
                m = date.getMinutes() + ':';
                s = date.getSeconds();
                return Y + M + D + h + m + s;
            }

            function timestampToTime2(timestamp) {
                var date = new Date(timestamp * 1000);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
                Y = date.getFullYear() + '/';
                M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '/';
                D = date.getDate() + ' ';
                h = date.getHours() + ':';
                if (date.getMinutes() >= 10) {
                    m = date.getMinutes() + ':';
                } else {
                    m = '0' + date.getMinutes() + ':';
                }
                if (date.getSeconds() >= 10) {
                    s = date.getSeconds();
                } else {
                    s = '0' + date.getSeconds();
                }
                return Y + M + D + h + m + s;
            }
            var time = ((Date.parse(new Date()) / 1000) - t);
            console.log(time);
            if (typ) {
                if (typ == 'Windows' || typ == 'Linux') {
                    vue1.pc = true;
                    vue1.network = false;
                } else {
                    vue1.pc = false;
                    vue1.network = true;
                }
                $.ajax({
                    type: 'post',
                    url: '/monitor/getDraw',
                    data: {host: ho, hostid: i,timeStamp: time},
                    success: function (data) {
                        console.log(data);
                        if (typ == 'Windows' || typ == 'Linux') {
                            if (JSON.parse(data.cpuUsed).result.length > 0) {
                                var cpuUsed = JSON.parse(data.cpuUsed).result, cpuUsedTime = [], cpuUsedValue = [];
                                for (var a = 0; a < cpuUsed.length; a++) {
                                    cpuUsedTime.push(timestampToTime(cpuUsed[a].clock));
                                    cpuUsedValue.push(cpuUsed[a].value)
                                }
                                option.xAxis.data = cpuUsedTime;
                                option.series.data = cpuUsedValue;
                                chart1.setOption(option, true);
                                chart1.hideLoading();
                            } else {
                                option.xAxis.data = [];
                                option.series.data = [];
                                chart1.setOption(option, true);
                                chart1.hideLoading();
                            }
                            var key = Object.keys(data), val = Object.values(data), used = [], usedVal = [],
                                freeVal = [],
                                usedVal2 = [], freeVal2 = [], fv2 = [], diskKey = [], uv = '', fv = '', ramKey = [],
                                ramIn = [], readReats = [], writeReats = [],
                                ramOut = [], wps = '', rps = '', reqTime = [], rpsValue = [], wpsValue = [];
                            for (var c = 0; c < key.length; c++) {
                                if (key[c].indexOf('vfs.fs.size') >= 0) {
                                    if (JSON.parse(val[c]).result.length > 0) {
                                        if (key[c].indexOf('used') >= 0) {
                                            used.push(key[c]);
                                            uv = parseFloat(JSON.parse(val[c]).result[0].value) / 1073741824;
                                            usedVal.push(uv.toFixed(2))
                                        } else {
                                            fv = parseFloat(JSON.parse(val[c]).result[0].value) / 1073741824;
                                            freeVal.push(fv.toFixed(2))
                                        }
                                    }
                                }
                                if (key[c].indexOf('net.if.') >= 0) {
                                    if (JSON.parse(val[c]).result.length > 0) {
                                        if ((key[c].indexOf('net.if.in') >= 0)) {
                                            ramKey.push(key[c].split('[')[1].split(']')[0]);
                                            ramIn.push(JSON.parse(val[c]).result)
                                        } else {
                                            ramOut.push(JSON.parse(val[c]).result)
                                        }
                                    }
                                }
                                if (key[c].indexOf('custom.vfs.dev.iostats') >= 0) {
                                    if (JSON.parse(val[c]).result.length > 0) {
                                        if (key[c].indexOf('custom.vfs.dev.iostats.rps') >= 0) {
                                            rps = JSON.parse(val[c]).result
                                        }
                                        if (key[c].indexOf('custom.vfs.dev.iostats.wps') >= 0) {
                                            wps = JSON.parse(val[c]).result
                                        }
                                        if (key[c].indexOf('custom.vfs.dev.iostats.rkB') >= 0) {
                                            readReats = JSON.parse(val[c]).result
                                        }
                                        if (key[c].indexOf('custom.vfs.dev.iostats.wkB') >= 0) {
                                            writeReats = JSON.parse(val[c]).result
                                        }
                                    }
                                }
                            }
                            var wrlen = '', wrReatsTime = [], wReatsValues = [], rReatsValues = [];
                            if (readReats.length > writeReats.length) {
                                wrlen = writeReats.length
                            } else {
                                wrlen = readReats.length
                            }
                            for (var w = 0; w < wrlen; w++) {
                                wrReatsTime.push(timestampToTime(writeReats[w].clock));
                                wReatsValues.push(writeReats[w].value);
                                rReatsValues.push(readReats[w].value)
                            }
                            console.log(wps);
                            for (var j = 0; j < rps.length; j++) {
                                reqTime.push(timestampToTime(rps[j].clock));
                                rpsValue.push(rps[j].value);
                                wpsValue.push(wps[j].value);
                            }
                            vue1.ramKey = ramKey;
                            vue1.ramIn = ramIn;
                            vue1.ramOut = ramOut;
                            console.log(ramOut);
                            console.log(ramIn);
                            console.log(vue1.ramKeyModel);
                            var arr1 = [], arr2 = [], arr3 = [], ramlength = '';
                            if (ramIn[0].length > ramOut[0].length) {
                                ramlength = ramOut[0].length
                            } else {
                                ramlength = ramIn[0].length
                            }
                            if (ramIn.length > 0) {
                                for (var g = 0; g < ramlength; g++) {
                                    arr1.push(timestampToTime(ramIn[vue1.ramKeyModel][g].clock));
                                    arr2.push((parseInt(ramIn[vue1.ramKeyModel][g].value) / 1024).toFixed(2));
                                    arr3.push((parseInt(ramOut[vue1.ramKeyModel][g].value) / 1024).toFixed(2))
                                }
                            }
                            if (arr1.length > 0 && arr2.length > 0 && arr3.length > 0) {
                                option5.xAxis.data = arr1;
                                option5.series[0].data = arr2;
                                option5.series[1].data = arr3;
                                chart5.setOption(option5, true);
                                chart5.hideLoading();
                            } else {
                                option5.xAxis.data = [];
                                option5.series[0].data = [];
                                option5.series[1].data = [];
                                chart5.setOption(option5, true);
                                chart5.hideLoading();
                            }
                            for (var d = 0; d < used.length; d++) {
                                diskKey.push(used[d].split(',')[0].split('[')[1]);
                                usedVal2.push((parseFloat(usedVal[d]) + parseFloat(freeVal[d])).toFixed(2))
                            }
                            for (var e = 0; e < usedVal2.length; e++) {
                                freeVal2.push((parseFloat(usedVal[e]) / parseFloat(usedVal2[e])).toFixed(2))
                            }
                            for (var f = 0; f < freeVal2.length; f++) {
                                fv2.push(1 - parseFloat(freeVal2[f]))
                            }
                            if (diskKey.length > 0 && usedVal.length > 0 && freeVal.length > 0 && freeVal2.length > 0 && fv2.length > 0) {
                                option2.yAxis.data = diskKey;
                                option3.yAxis.data = diskKey;
                                option2.series[0].data = usedVal;
                                option2.series[1].data = freeVal;
                                option3.series[0].data = freeVal2;
                                option3.series[1].data = fv2;
                                chart2.setOption(option2, true);
                                chart3.setOption(option3, true);
                                chart2.hideLoading();
                                chart3.hideLoading();
                            } else {
                                option2.yAxis.data = [];
                                option3.yAxis.data = [];
                                option2.series[0].data = [];
                                option2.series[1].data = [];
                                option3.series[0].data = [];
                                option3.series[1].data = [];
                                chart2.setOption(option2, true);
                                chart3.setOption(option3, true);
                                chart2.hideLoading();
                                chart3.hideLoading();
                            }
                            if (data.crmUsed && data.crmFree) {
                                console.log(JSON.parse(data.crmFree).result);
                                console.log(data.crmUsed);
                                console.log(JSON.parse(data.crmUsed).result);
                                if (/*JSON.parse(data.crmFree).result.length > 0 &&*/ JSON.parse(data.crmUsed).result.length > 0) {
                                    var ramFree = JSON.parse(data.crmFree).result,
                                        ramUsed = JSON.parse(data.crmUsed).result,
                                        ramTime = [], free = [], ud = [];
                                    console.log(ramUsed);
                                    console.log(ramFree);
                                    for (var k = 0; k < ramUsed.length; k++) {
                                        ramTime.push(timestampToTime(ramUsed[k].clock));
                                        // free.push((parseInt(ramFree[k].value) / 8589934592).toFixed(2));
                                        ud.push((parseInt(ramUsed[k].value) / 8589934592).toFixed(2))
                                    }
                                    option7.xAxis.data = ramTime;
                                    option7.series[0].data = ud;
                                    // option7.series[1].data = free;
                                    chart7.setOption(option7, true);
                                    chart7.hideLoading();
                                } else {
                                    option7.xAxis.data = [];
                                    option7.series[0].data = [];
                                    chart7.setOption(option7, true);
                                    chart7.hideLoading();
                                }
                            } else {
                                // alert('未配置监控项');
                                chart7.hideLoading();
                            }
                            console.log(ramTime);
                            console.log(free);
                            console.log(ud);
                            if (typ == 'Windows') {
                                if (JSON.parse(data.Write).result.length > 0 && JSON.parse(data.Read).result.length > 0) {
                                    var write = JSON.parse(data.Write).result, read = JSON.parse(data.Read).result,
                                        writeVal = '',
                                        readVal = '', wrlen = '';
                                    console.log(write);
                                    console.log(read);
                                    if (write.length > read.length) {
                                        wrlen = read.length
                                    } else {
                                        wrlen = write.length
                                    }
                                    for (var b = 0; b < wrlen.length; b++) {
                                        option4.xAxis.data.push(timestampToTime(write[b].clock));
                                        writeVal = parseFloat(write[b].value) / 1024;
                                        readVal = parseFloat(read[b].value) / 1024;
                                        option4.series[0].data.push(readVal.toFixed(2));
                                        option4.series[1].data.push(writeVal.toFixed(2));
                                    }
                                    chart4.setOption(option4, true);
                                    chart4.hideLoading();
                                } else {
                                    option4.xAxis.data = [];
                                    option4.series[0].data = [];
                                    option4.series[1].data = [];
                                    chart4.setOption(option4, true);
                                    chart4.hideLoading();
                                }
                                option6.xAxis.data = [];
                                option6.series[0].data = [];
                                option6.series[1].data = [];
                                chart6.setOption(option6, true);
                                chart6.hideLoading();
                            } else if (typ == 'Linux') {
                                if (reqTime.length > 0 && rpsValue.length > 0 && wpsValue.length > 0) {
                                    option6.xAxis.data = reqTime;
                                    option6.series[0].data = rpsValue;
                                    option6.series[1].data = wpsValue;
                                    chart6.setOption(option6, true);
                                    chart6.hideLoading();
                                } else {
                                    option6.xAxis.data = [];
                                    option6.series[0].data = [];
                                    option6.series[1].data = [];
                                    chart6.setOption(option6, true);
                                    chart6.hideLoading();
                                }
                                if (wrReatsTime.length > 0 && wReatsValues.length > 0 && rReatsValues.length > 0) {
                                    option4.xAxis.data = wrReatsTime;
                                    option4.series[0].data = rReatsValues;
                                    option4.series[1].data = wReatsValues;
                                    chart4.setOption(option4, true);
                                    chart4.hideLoading();
                                } else {
                                    option4.xAxis.data = [];
                                    option4.series[0].data = [];
                                    option4.series[1].data = [];
                                    chart4.setOption(option4, true);
                                    chart4.hideLoading();
                                }
                            }
                        } else if (typ == 'Network') {
                            console.log(data);
                            var networkcpu = JSON.parse(data.cpuUtilization5m).result, networkcpuX = [],
                                networkcpuY = [];
                            for (var m = 0; m < networkcpu.length; m++) {
                                networkcpuX.push(timestampToTime(parseInt(networkcpu[m].clock)));
                                networkcpuY.push(networkcpu[m].value)
                            }
                            option8.xAxis.data = networkcpuX;
                            option8.series.data = networkcpuY;
                            chart8.setOption(option8, true);
                            chart8.hideLoading();
                            var networkRamFree = JSON.parse(data.MemoryPoolFree).result,
                                networkRamUsed = JSON.parse(data.MemoryPoolUsed).result, networkRamFreeData = [],
                                networkRamUsedData = [];
                            for (var n = 0; n < networkRamFree.length; n++) {
                                networkRamFreeData[n] = {
                                    name: timestampToTime2(parseInt(networkRamFree[n].clock)),
                                    value: [timestampToTime2(parseInt(networkRamFree[n].clock)), networkRamFree[n].value]
                                }
                            }
                            for (var u = 0; u < networkRamUsed.length; u++) {
                                networkRamUsedData[u] = {
                                    name: timestampToTime2(parseInt(networkRamUsed[u].clock)),
                                    value: [timestampToTime2(parseInt(networkRamUsed[u].clock)), networkRamUsed[u].value]
                                }
                            }
                            option9.series[0].data = networkRamUsedData;
                            option9.series[1].data = networkRamFreeData;
                            chart9.setOption(option9, true);
                            chart9.hideLoading();
                            console.log(JSON.parse(data.NetWork));
                            console.log(JSON.parse(data.keyId));
                            var networkValues = Object.values(JSON.parse(data.NetWork));
                            vue1.netWorkkeyss = JSON.parse(data.keyId);
                            vue1.netWorkkeys2 = Object.keys(JSON.parse(data.NetWork));
                            console.log(networkValues);
                            var networkin = [], networkout = [];
                            for (var v = 0; v < networkValues.length; v++) {
                                networkin.push(JSON.parse(networkValues[v].in).result);
                                networkout.push(JSON.parse(networkValues[v].out).result)
                            }
                            console.log(networkin);
                            console.log(networkout);
                            var networkindata = [], networkoutdata = [], networkindata1 = [], networkoutdata1 = [],
                                networkindata2 = [], networkoutdata2 = [], networkindata3 = [], networkoutdata3 = [];
                            for (var y = 0; y < networkin[0].length; y++) {
                                networkindata[y] = {
                                    name: timestampToTime2(parseFloat(networkin[0][y].clock)),
                                    value: [timestampToTime2(parseFloat(networkin[0][y].clock)), networkin[0][y].value]
                                };
                                networkindata1[y] = {
                                    name: timestampToTime2(parseFloat(networkin[1][y].clock)),
                                    value: [timestampToTime2(parseFloat(networkin[1][y].clock)), networkin[1][y].value]
                                };
                                networkindata2[y] = {
                                    name: timestampToTime2(parseFloat(networkin[2][y].clock)),
                                    value: [timestampToTime2(parseFloat(networkin[2][y].clock)), networkin[2][y].value]
                                };
                                networkindata3[y] = {
                                    name: timestampToTime2(parseFloat(networkin[3][y].clock)),
                                    value: [timestampToTime2(parseFloat(networkin[3][y].clock)), networkin[3][y].value]
                                };
                            }
                            for (var z = 0; z < networkout[0].length; z++) {
                                networkoutdata[z] = {
                                    name: timestampToTime2(parseFloat(networkout[0][z].clock)),
                                    value: [timestampToTime2(parseFloat(networkout[0][z].clock)), networkout[0][z].value]
                                };
                                networkoutdata1[z] = {
                                    name: timestampToTime2(parseFloat(networkout[1][z].clock)),
                                    value: [timestampToTime2(parseFloat(networkout[1][z].clock)), networkout[1][z].value]
                                };
                                networkoutdata2[z] = {
                                    name: timestampToTime2(parseFloat(networkout[2][z].clock)),
                                    value: [timestampToTime2(parseFloat(networkout[2][z].clock)), networkout[2][z].value]
                                };
                                networkoutdata3[z] = {
                                    name: timestampToTime2(parseFloat(networkout[3][z].clock)),
                                    value: [timestampToTime2(parseFloat(networkout[3][z].clock)), networkout[3][z].value]
                                };
                            }
                            console.log(networkindata);
                            console.log(networkoutdata);
                            console.log(Object.keys(JSON.parse(data.NetWork)));
                            // option10.title.subtext = Object.keys(JSON.parse(data.NetWork))[0];
                            option10.series[0].data = networkindata;
                            option10.series[1].data = networkoutdata;
                            // option11.title.subtext = Object.keys(JSON.parse(data.NetWork))[1];
                            option11.series[0].data = networkindata1;
                            option11.series[1].data = networkoutdata1;
                            // option12.title.subtext = Object.keys(JSON.parse(data.NetWork))[2];
                            option12.series[0].data = networkindata2;
                            option12.series[1].data = networkoutdata2;
                            // option13.title.subtext = Object.keys(JSON.parse(data.NetWork))[3];
                            option13.series[0].data = networkindata3;
                            option13.series[1].data = networkoutdata3;
                            chart10.setOption(option10, true);
                            chart11.setOption(option11, true);
                            chart12.setOption(option12, true);
                            chart13.setOption(option13, true);
                            vue1.networkin = networkin;
                            vue1.networkout = networkout;
                            chart10.hideLoading();
                            chart11.hideLoading();
                            chart12.hideLoading();
                            chart13.hideLoading();
                        }
                    },
                    error: function () {
                        alert('networkerror')
                    }
                });
            }
        },
        addMonitor: function () {
            vue1.tip = [];
            vue1.tips = '';
            vue1.typ = '';
            vue1.hosts2 = '';
            vue1.edittemp = '';
            vue1.ty2='';
            vue1.hide1 = false;
            vue1.hide2 = false;
            vue1.hide3 = false;
            $('.img').css("display","none");
        },
        seeHis: function (a, b, c, d) {
            console.log(a);
            console.log(b);
            console.log(c);
            console.log(d);

            function timestampToTime2(timestamp) {
                var date = new Date(timestamp * 1000);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
                Y = date.getFullYear() + '/';
                M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '/';
                D = date.getDate() + ' ';
                h = date.getHours() + ':';
                if (date.getMinutes() >= 10) {
                    m = date.getMinutes() + ':';
                } else {
                    m = '0' + date.getMinutes() + ':';
                }
                if (date.getSeconds() >= 10) {
                    s = date.getSeconds();
                } else {
                    s = '0' + date.getSeconds();
                }
                return Y + M + D + h + m + s;
            }

            $.post("/monitor/getHistoryOfItem", {itemId: a, hostId: d, units: b, value_type: c}, function (data) {
                console.log(JSON.parse(JSON.parse(data)).result);
                var d = JSON.parse(JSON.parse(data)).result, arr = [];
                for (var a = 0; a < d.length; a++) {
                    d[a].time = timestampToTime2(d[a].clock)
                }
                vue1.hisData = d;
            });
        },
        addMark: function () {
            $.post("/monitor/getAllMark", function (data) {
                vue1.mark = data;
                console.log(data);
            });
        },
        delcomm: function (data, index) {
            data.splice(index, 1)
        },
        timeChange2: function (time, id) {
            function timestampToTime(timestamp) {
                var date = new Date(timestamp * 1000);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
                Y = date.getFullYear() + '-';
                M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
                D = date.getDate() + ' ';
                h = date.getHours() + ':';
                m = date.getMinutes() + ':';
                s = date.getSeconds();
                return Y + M + D + h + m + s;
            }

            $.post("/monitor/getTriggerById", {id: id, time: time}, function (da) {
                var data = JSON.parse(JSON.parse(da)).result;
                var obj = {
                    0: '正常',
                    1: "异常"
                };
                console.log(data);
                if (data.length > 0) {
                    vue1.showda = false;
                    var arr = data;
                    for (var l = 0; l < data.length; l++) {
                        if (arr[l].priority == '0') {
                            arr[l].priority_ = '未分类'
                        } else if (data[l].priority == '1') {
                            arr[l].priority_ = '信息'
                        } else if (data[l].priority == '2') {
                            arr[l].priority_ = '警告'
                        } else if (data[l].priority == '3') {
                            arr[l].priority_ = '一般'
                        } else if (data[l].priority == '4') {
                            arr[l].priority_ = '高'
                        } else if (data[l].priority == '5') {
                            arr[l].priority_ = '灾难'
                        }
                        arr[l].lastchange = timestampToTime(parseInt(data[l].lastchange));
                        arr[l].value_ = obj[arr[l].value]
                    }
                    vue1.showEvent = arr
                } else {
                    vue1.showda = true;
                    vue1.showEvent = [];
                }
            })
        },
        reInfrastructure: function () {
            vue1.hostGroupModel='a';
            vue1.typeGroupModel='0';
            $('#load').mLoading("show");
            var ajax = $.post("/monitor/getResource", function (data) {
                vue1.count = JSON.parse(data[0]).count;
                var arr = [];
                var obj = {
                    0: 'avi1',
                    1: 'avi2',
                    2: 'avi3'
                };
                for (var a = 0; a < data.length; a++) {
                    arr.push(JSON.parse(data[a]));
                    if (arr[a].items) {
                        arr[a].cpu = arr[a].items[0].cpuUsed;
                        arr[a].ram = arr[a].items[0].memotyUsed;
                        arr[a].typ = arr[a].items[0].type
                    } else {
                        arr[a].cpu = '';
                        arr[a].ram = '';
                        arr[a].typ = ''
                    }
                    if (arr[a].status == '0') {
                        vue1.stamod.push(a)
                    }
                    arr[a].back = vue1.className[arr[a].typ];
                    arr[a].available_ = obj[arr[a].available];
                    arr[a].ipmi_available_ = obj[arr[a].ipmi_available];
                    arr[a].snmp_available_ = obj[arr[a].snmp_available];
                    arr[a].jmx_available_ = obj[arr[a].jmx_available];
                    if (arr[a].available_ == 'avi2' || arr[a].ipmi_available_ == 'avi2' || arr[a].snmp_available_ == 'avi2' || arr[a].jmx_available_ == 'avi2') {
                        arr[a].color = 'avi2'
                    } else {
                        arr[a].color = 'avi3'
                    }
                    if (arr[a].typ == 'network') {
                        arr[a].typ = 'Network'
                    }
                }
                console.log(arr);
                $('#load').mLoading("hide");
                vue1.d = arr;
            });
            $.when(ajax).done(function () {
                layui.use(['laypage', 'layer', 'laydate'], function () {
                    var laypage = layui.laypage, layer = layui.layer, laydate = layui.laydate;
                    laypage.render({
                        elem: 'Pagination5'
                        , count: vue1.count
                        , limit: 20
                        , layout: ['count', 'prev', 'page', 'next']
                        , jump: function (obj, first) {
                            if (!first) {
                                var obj2 = {
                                    0: 'avi1',
                                    1: 'avi2',
                                    2: 'avi3'
                                };
                                $.post("/monitor/getResource", {pagenum: obj.curr}, function (data) {
                                    var arr = [];
                                    for (var a = 0; a < data.length; a++) {
                                        arr.push(JSON.parse(data[a]));
                                        if (arr[a].items) {
                                            arr[a].cpu = arr[a].items[0].cpuUsed;
                                            arr[a].ram = arr[a].items[0].memotyUsed;
                                            arr[a].typ = arr[a].items[0].type
                                        } else {
                                            arr[a].cpu = '';
                                            arr[a].ram = '';
                                            arr[a].typ = ''
                                        }
                                        if (arr[a].status == '0') {
                                            vue1.stamod.push(a)
                                        }
                                        arr[a].back = vue1.className[arr[a].typ];
                                        arr[a].available_ = obj2[arr[a].available];
                                        arr[a].ipmi_available_ = obj2[arr[a].ipmi_available];
                                        arr[a].snmp_available_ = obj2[arr[a].snmp_available];
                                        arr[a].jmx_available_ = obj2[arr[a].jmx_available];
                                        if (arr[a].available_ == 'avi2' || arr[a].ipmi_available_ == 'avi2' || arr[a].snmp_available_ == 'avi2' || arr[a].jmx_available_ == 'avi2') {
                                            arr[a].color = 'avi2'
                                        } else {
                                            arr[a].color = 'avi3'
                                        }
                                        if (arr[a].typ == 'network') {
                                            arr[a].typ = 'Network'
                                        }
                                    }
                                    console.log(arr);
                                    vue1.d = arr;
                                });
                            }
                        }
                    });
                });
            });
        },
        hostGroupChange: function (h) {
            if (h != 'a') {
                var ajax = $.post("/monitor/getResource", {hostGroupid: h}, function (data) {
                    if (data.length > 0) {
                        vue1.count = JSON.parse(data[0]).count;
                    } else {
                        vue1.count = 0
                    }
                    var arr = [];
                    var obj = {
                        0: 'avi1',
                        1: 'avi2',
                        2: 'avi3'
                    };
                    for (var a = 0; a < data.length; a++) {
                        arr.push(JSON.parse(data[a]));
                        if (arr[a].items) {
                            arr[a].cpu = arr[a].items[0].cpuUsed;
                            arr[a].ram = arr[a].items[0].memotyUsed;
                            arr[a].typ = arr[a].items[0].type
                        } else {
                            arr[a].cpu = '';
                            arr[a].ram = '';
                            arr[a].typ = ''
                        }
                        if (arr[a].status == '0') {
                            vue1.stamod.push(a)
                        }
                        arr[a].back = vue1.className[arr[a].typ];
                        arr[a].available_ = obj[arr[a].available];
                        arr[a].ipmi_available_ = obj[arr[a].ipmi_available];
                        arr[a].snmp_available_ = obj[arr[a].snmp_available];
                        arr[a].jmx_available_ = obj[arr[a].jmx_available];
                        if (arr[a].available_ == 'avi2' || arr[a].ipmi_available_ == 'avi2' || arr[a].snmp_available_ == 'avi2' || arr[a].jmx_available_ == 'avi2') {
                            arr[a].color = 'avi2'
                        } else {
                            arr[a].color = 'avi3'
                        }
                        if (arr[a].typ == 'network') {
                            arr[a].typ = 'Network'
                        }
                    }
                    console.log(arr);
                    vue1.d = arr;
                });
                $.when(ajax).done(function () {
                    layui.use(['laypage', 'layer', 'laydate'], function () {
                        var laypage = layui.laypage, layer = layui.layer, laydate = layui.laydate;
                        laypage.render({
                            elem: 'Pagination5'
                            , count: vue1.count
                            , limit: 20
                            , layout: ['count', 'prev', 'page', 'next']
                            , jump: function (obj, first) {
                                if (!first) {
                                    var obj2 = {
                                        0: 'avi1',
                                        1: 'avi2',
                                        2: 'avi3'
                                    };
                                    $.post("/monitor/getResource", {pagenum: obj.curr}, function (data) {
                                        var arr = [];
                                        for (var a = 0; a < data.length; a++) {
                                            arr.push(JSON.parse(data[a]));
                                            if (arr[a].items) {
                                                arr[a].cpu = arr[a].items[0].cpuUsed;
                                                arr[a].ram = arr[a].items[0].memotyUsed;
                                                arr[a].typ = arr[a].items[0].type
                                            } else {
                                                arr[a].cpu = '';
                                                arr[a].ram = '';
                                                arr[a].typ = ''
                                            }
                                            if (arr[a].status == '0') {
                                                vue1.stamod.push(a)
                                            }
                                            arr[a].back = vue1.className[arr[a].typ];
                                            arr[a].available_ = obj2[arr[a].available];
                                            arr[a].ipmi_available_ = obj2[arr[a].ipmi_available];
                                            arr[a].snmp_available_ = obj2[arr[a].snmp_available];
                                            arr[a].jmx_available_ = obj2[arr[a].jmx_available];
                                            if (arr[a].available_ == 'avi2' || arr[a].ipmi_available_ == 'avi2' || arr[a].snmp_available_ == 'avi2' || arr[a].jmx_available_ == 'avi2') {
                                                arr[a].color = 'avi2'
                                            } else {
                                                arr[a].color = 'avi3'
                                            }
                                            if (arr[a].typ == 'network') {
                                                arr[a].typ = 'Network'
                                            }
                                        }
                                        console.log(arr);
                                        vue1.d = arr;
                                    });
                                }
                            }
                        });
                    });
                });
            }
        },
        typeGroupChange: function (h) {
            if (h != '0') {
                console.log('资源类型');
                $('#load').mLoading("show");
                var ajax = $.post("/monitor/getResource", {select: h}, function (data) {
                    console.log(h);
                    console.log(JSON.parse(data[0]));
                    console.log(JSON.parse(data[0]).count);
                    if (data.length > 0) {
                        vue1.count = JSON.parse(data[0]).count;
                    } else {
                        vue1.count = 0
                    }
                    var arr = [];
                    var obj = {
                        0: 'avi1',
                        1: 'avi2',
                        2: 'avi3'
                    };
                    for (var a = 0; a < data.length; a++) {
                        arr.push(JSON.parse(data[a]));
                        if (arr[a].items) {
                            arr[a].cpu = arr[a].items[0].cpuUsed;
                            arr[a].ram = arr[a].items[0].memotyUsed;
                            arr[a].typ = arr[a].items[0].type
                        } else {
                            arr[a].cpu = '';
                            arr[a].ram = '';
                            arr[a].typ = ''
                        }
                        if (arr[a].status == '0') {
                            vue1.stamod.push(a)
                        }
                        arr[a].back = vue1.className[arr[a].typ];
                        arr[a].available_ = obj[arr[a].available];
                        arr[a].ipmi_available_ = obj[arr[a].ipmi_available];
                        arr[a].snmp_available_ = obj[arr[a].snmp_available];
                        arr[a].jmx_available_ = obj[arr[a].jmx_available];
                        if (arr[a].available_ == 'avi2' || arr[a].ipmi_available_ == 'avi2' || arr[a].snmp_available_ == 'avi2' || arr[a].jmx_available_ == 'avi2') {
                            arr[a].color = 'avi2'
                        } else {
                            arr[a].color = 'avi3'
                        }
                        if (arr[a].typ == 'network') {
                            arr[a].typ = 'Network'
                        }
                    }
                    $('#load').mLoading("hide");
                    vue1.d = arr;
                });
                $.when(ajax).done(function () {
                    layui.use(['laypage', 'layer', 'laydate'], function () {
                        var laypage = layui.laypage, layer = layui.layer, laydate = layui.laydate;
                        laypage.render({
                            elem: 'Pagination5'
                            , count: vue1.count
                            , limit: 20
                            , layout: ['count', 'prev', 'page', 'next']
                            , jump: function (obj, first) {
                                if (!first) {
                                    var obj2 = {
                                        0: 'avi1',
                                        1: 'avi2',
                                        2: 'avi3'
                                    };
                                    $.post("/monitor/getResource", {pagenum: obj.curr}, function (data) {
                                        var arr = [];
                                        for (var a = 0; a < data.length; a++) {
                                            arr.push(JSON.parse(data[a]));
                                            if (arr[a].items) {
                                                arr[a].cpu = arr[a].items[0].cpuUsed;
                                                arr[a].ram = arr[a].items[0].memotyUsed;
                                                arr[a].typ = arr[a].items[0].type
                                            } else {
                                                arr[a].cpu = '';
                                                arr[a].ram = '';
                                                arr[a].typ = ''
                                            }
                                            if (arr[a].status == '0') {
                                                vue1.stamod.push(a)
                                            }
                                            arr[a].back = vue1.className[arr[a].typ];
                                            arr[a].available_ = obj2[arr[a].available];
                                            arr[a].ipmi_available_ = obj2[arr[a].ipmi_available];
                                            arr[a].snmp_available_ = obj2[arr[a].snmp_available];
                                            arr[a].jmx_available_ = obj2[arr[a].jmx_available];
                                            if (arr[a].available_ == 'avi2' || arr[a].ipmi_available_ == 'avi2' || arr[a].snmp_available_ == 'avi2' || arr[a].jmx_available_ == 'avi2') {
                                                arr[a].color = 'avi2'
                                            } else {
                                                arr[a].color = 'avi3'
                                            }
                                            if (arr[a].typ == 'network') {
                                                arr[a].typ = 'Network'
                                            }
                                        }
                                        console.log(arr);
                                        vue1.d = arr;
                                    });
                                }
                            }
                        });
                    });
                });
            }
        },
        searchInfrastructure: function (m, s) {
            $('#load').mLoading("show");
            if (s) {
                if (m == '1') {
                    console.log('主机');
                    var ajax = $.post("/monitor/getResource", {resourcename: vue1.search}, function (data) {
                        if (data.length > 0) {
                            vue1.count = JSON.parse(data[0]).count;
                        } else {
                            vue1.count = 0
                        }
                        var arr = [];
                        var obj = {
                            0: 'avi1',
                            1: 'avi2',
                            2: 'avi3'
                        };
                        for (var a = 0; a < data.length; a++) {
                            arr.push(JSON.parse(data[a]));
                            if (arr[a].items) {
                                arr[a].cpu = arr[a].items[0].cpuUsed;
                                arr[a].ram = arr[a].items[0].memotyUsed;
                                arr[a].typ = arr[a].items[0].type
                            } else {
                                arr[a].cpu = '';
                                arr[a].ram = '';
                                arr[a].typ = ''
                            }
                            if (arr[a].status == '0') {
                                vue1.stamod.push(a)
                            }
                            arr[a].back = vue1.className[arr[a].typ];
                            arr[a].available_ = obj[arr[a].available];
                            arr[a].ipmi_available_ = obj[arr[a].ipmi_available];
                            arr[a].snmp_available_ = obj[arr[a].snmp_available];
                            arr[a].jmx_available_ = obj[arr[a].jmx_available];
                            if (arr[a].available_ == 'avi2' || arr[a].ipmi_available_ == 'avi2' || arr[a].snmp_available_ == 'avi2' || arr[a].jmx_available_ == 'avi2') {
                                arr[a].color = 'avi2'
                            } else {
                                arr[a].color = 'avi3'
                            }
                            if (arr[a].typ == 'network') {
                                arr[a].typ = 'Network'
                            }
                        }
                        console.log(arr);
                        $('#load').mLoading("hide");
                        vue1.d = arr;
                    });
                    $.when(ajax).done(function () {
                        layui.use(['laypage', 'layer', 'laydate'], function () {
                            var laypage = layui.laypage, layer = layui.layer, laydate = layui.laydate;
                            laypage.render({
                                elem: 'Pagination5'
                                , count: vue1.count
                                , limit: 20
                                , layout: ['count', 'prev', 'page', 'next']
                                , jump: function (obj, first) {
                                    if (!first) {
                                        var obj2 = {
                                            0: 'avi1',
                                            1: 'avi2',
                                            2: 'avi3'
                                        };
                                        $.post("/monitor/getResource", {pagenum: obj.curr}, function (data) {
                                            var arr = [];
                                            for (var a = 0; a < data.length; a++) {
                                                arr.push(JSON.parse(data[a]));
                                                if (arr[a].items) {
                                                    arr[a].cpu = arr[a].items[0].cpuUsed;
                                                    arr[a].ram = arr[a].items[0].memotyUsed;
                                                    arr[a].typ = arr[a].items[0].type
                                                } else {
                                                    arr[a].cpu = '';
                                                    arr[a].ram = '';
                                                    arr[a].typ = ''
                                                }
                                                if (arr[a].status == '0') {
                                                    vue1.stamod.push(a)
                                                }
                                                arr[a].back = vue1.className[arr[a].typ];
                                                arr[a].available_ = obj2[arr[a].available];
                                                arr[a].ipmi_available_ = obj2[arr[a].ipmi_available];
                                                arr[a].snmp_available_ = obj2[arr[a].snmp_available];
                                                arr[a].jmx_available_ = obj2[arr[a].jmx_available];
                                                if (arr[a].available_ == 'avi2' || arr[a].ipmi_available_ == 'avi2' || arr[a].snmp_available_ == 'avi2' || arr[a].jmx_available_ == 'avi2') {
                                                    arr[a].color = 'avi2'
                                                } else {
                                                    arr[a].color = 'avi3'
                                                }
                                                if (arr[a].typ == 'network') {
                                                    arr[a].typ = 'Network'
                                                }
                                            }
                                            console.log(arr);
                                            $('#load').mLoading("hide");
                                            vue1.d = arr;
                                        });
                                    }
                                }
                            });
                        });
                    });
                } else if(m == 2) {
                    console.log('标签');
                    $.post("/monitor/getResource", {markname: vue1.search}, function (data) {
                        if (data.length > 0) {
                            vue1.count = JSON.parse(data[0]).count;
                        } else {
                            vue1.count = 0
                        }
                        var arr = [];
                        var obj = {
                            0: 'avi1',
                            1: 'avi2',
                            2: 'avi3'
                        };
                        for (var a = 0; a < data.length; a++) {
                            arr.push(JSON.parse(data[a]));
                            if (arr[a].items) {
                                arr[a].cpu = arr[a].items[0].cpuUsed;
                                arr[a].ram = arr[a].items[0].memotyUsed;
                                arr[a].typ = arr[a].items[0].type
                            } else {
                                arr[a].cpu = '';
                                arr[a].ram = '';
                                arr[a].typ = ''
                            }
                            if (arr[a].status == '0') {
                                vue1.stamod.push(a)
                            }
                            arr[a].back = vue1.className[arr[a].typ];
                            arr[a].available_ = obj[arr[a].available];
                            arr[a].ipmi_available_ = obj[arr[a].ipmi_available];
                            arr[a].snmp_available_ = obj[arr[a].snmp_available];
                            arr[a].jmx_available_ = obj[arr[a].jmx_available];
                            if (arr[a].available_ == 'avi2' || arr[a].ipmi_available_ == 'avi2' || arr[a].snmp_available_ == 'avi2' || arr[a].jmx_available_ == 'avi2') {
                                arr[a].color = 'avi2'
                            } else {
                                arr[a].color = 'avi3'
                            }
                            if (arr[a].typ == 'network') {
                                arr[a].typ = 'Network'
                            }
                        }
                        console.log(arr);
                        $('#load').mLoading("hide");
                        vue1.d = arr;
                    });
                }else{
                    console.log('资源类型');
                    $.post("/monitor/getResource", {select: vue1.search}, function (data) {
                        console.log(JSON.parse(data[0]));
                        console.log(JSON.parse(data[0]).count);
                        if (data.length > 0) {
                            vue1.count = JSON.parse(data[0]).count;
                        } else {
                            vue1.count = 0
                        }
                        var arr = [];
                        var obj = {
                            0: 'avi1',
                            1: 'avi2',
                            2: 'avi3'
                        };
                        for (var a = 0; a < data.length; a++) {
                            arr.push(JSON.parse(data[a]));
                            if (arr[a].items) {
                                arr[a].cpu = arr[a].items[0].cpuUsed;
                                arr[a].ram = arr[a].items[0].memotyUsed;
                                arr[a].typ = arr[a].items[0].type
                            } else {
                                arr[a].cpu = '';
                                arr[a].ram = '';
                                arr[a].typ = ''
                            }
                            if (arr[a].status == '0') {
                                vue1.stamod.push(a)
                            }
                            arr[a].back = vue1.className[arr[a].typ];
                            arr[a].available_ = obj[arr[a].available];
                            arr[a].ipmi_available_ = obj[arr[a].ipmi_available];
                            arr[a].snmp_available_ = obj[arr[a].snmp_available];
                            arr[a].jmx_available_ = obj[arr[a].jmx_available];
                            if (arr[a].available_ == 'avi2' || arr[a].ipmi_available_ == 'avi2' || arr[a].snmp_available_ == 'avi2' || arr[a].jmx_available_ == 'avi2') {
                                arr[a].color = 'avi2'
                            } else {
                                arr[a].color = 'avi3'
                            }
                            if (arr[a].typ == 'network') {
                                arr[a].typ = 'Network'
                            }
                        }
                        $('#load').mLoading("hide");
                        vue1.d = arr;
                    });
                }
            }
        },
        timeChange: function (t) {
            chart1.showLoading();
            chart4.showLoading();
            chart2.showLoading();
            chart3.showLoading();
            chart5.showLoading();
            chart6.showLoading();
            chart7.showLoading();
            chart8.showLoading();
            chart9.showLoading();
            chart10.showLoading();
            chart11.showLoading();
            chart12.showLoading();
            chart13.showLoading();

            function timestampToTime(timestamp) {
                var date = new Date(timestamp * 1000);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
                Y = date.getFullYear() + '-';
                M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
                D = date.getDate() + ' ';
                h = date.getHours() + ':';
                m = date.getMinutes() + ':';
                s = date.getSeconds();
                return Y + M + D + h + m + s;
            }

            function timestampToTime2(timestamp) {
                var date = new Date(timestamp * 1000);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
                Y = date.getFullYear() + '/';
                M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '/';
                D = date.getDate() + ' ';
                h = date.getHours() + ':';
                if (date.getMinutes() >= 10) {
                    m = date.getMinutes() + ':';
                } else {
                    m = '0' + date.getMinutes() + ':';
                }
                if (date.getSeconds() >= 10) {
                    s = date.getSeconds();
                } else {
                    s = '0' + date.getSeconds();
                }
                return Y + M + D + h + m + s;
            }

            var time = ((Date.parse(new Date()) / 1000) - t);
            console.log(time);
            $.post("/monitor/getDraw", {
                timeStamp: time,
                hostid: vue1.showHostId,
                host: vue1.showHost
            }, function (data) {
                console.log(data);
                if (vue1.hostType == 'Windows' || vue1.hostType == 'Linux') {
                    if (JSON.parse(data.cpuUsed).result.length > 0) {
                        var cpuUsed = JSON.parse(data.cpuUsed).result, cpuUsedTime = [], cpuUsedValue = [];
                        for (var a = 0; a < cpuUsed.length; a++) {
                            cpuUsedTime.push(timestampToTime(cpuUsed[a].clock));
                            cpuUsedValue.push(cpuUsed[a].value)
                        }
                        option.xAxis.data = cpuUsedTime;
                        option.series.data = cpuUsedValue;
                        chart1.setOption(option, true);
                        chart1.hideLoading();
                    } else {
                        option.xAxis.data = [];
                        option.series.data = [];
                        chart1.setOption(option, true);
                        chart1.hideLoading();
                    }
                    var key = Object.keys(data), val = Object.values(data), used = [], usedVal = [], freeVal = [],
                        usedVal2 = [], freeVal2 = [], fv2 = [], diskKey = [], uv = '', fv = '', ramKey = [],
                        ramIn = [], readReats = [], writeReats = [],
                        ramOut = [], wps = '', rps = '', reqTime = [], rpsValue = [], wpsValue = [];
                    for (var c = 0; c < key.length; c++) {
                        if (key[c].indexOf('vfs.fs.size') >= 0) {
                            if (JSON.parse(val[c]).result.length > 0) {
                                if (key[c].indexOf('used') >= 0) {
                                    used.push(key[c]);
                                    uv = parseFloat(JSON.parse(val[c]).result[0].value) / 1073741824;
                                    usedVal.push(uv.toFixed(2))
                                } else {
                                    fv = parseFloat(JSON.parse(val[c]).result[0].value) / 1073741824;
                                    freeVal.push(fv.toFixed(2))
                                }
                            }
                        }
                        if (key[c].indexOf('net.if.') >= 0) {
                            if (JSON.parse(val[c]).result.length > 0) {
                                if ((key[c].indexOf('net.if.in') >= 0)) {
                                    ramKey.push(key[c].split('[')[1].split(']')[0]);
                                    ramIn.push(JSON.parse(val[c]).result)
                                } else {
                                    ramOut.push(JSON.parse(val[c]).result)
                                }
                            }
                        }
                        if (key[c].indexOf('custom.vfs.dev.iostats') >= 0) {
                            if (JSON.parse(val[c]).result.length > 0) {
                                if (key[c].indexOf('custom.vfs.dev.iostats.rps') >= 0) {
                                    rps = JSON.parse(val[c]).result
                                }
                                if (key[c].indexOf('custom.vfs.dev.iostats.wps') >= 0) {
                                    wps = JSON.parse(val[c]).result
                                }
                                if (key[c].indexOf('custom.vfs.dev.iostats.rkB') >= 0) {
                                    readReats = JSON.parse(val[c]).result
                                }
                                if (key[c].indexOf('custom.vfs.dev.iostats.wkB') >= 0) {
                                    writeReats = JSON.parse(val[c]).result
                                }
                            }
                        }
                    }
                    var wrlen = '', wrReatsTime = [], wReatsValues = [], rReatsValues = [];
                    if (readReats.length > writeReats.length) {
                        wrlen = writeReats.length
                    } else {
                        wrlen = readReats.length
                    }
                    for (var w = 0; w < wrlen; w++) {
                        wrReatsTime.push(timestampToTime(writeReats[w].clock));
                        wReatsValues.push(writeReats[w].value);
                        rReatsValues.push(readReats[w].value)
                    }
                    console.log(wps);
                    for (var j = 0; j < rps.length; j++) {
                        reqTime.push(timestampToTime(rps[j].clock));
                        rpsValue.push(rps[j].value);
                        wpsValue.push(wps[j].value);
                    }
                    vue1.ramKey = ramKey;
                    vue1.ramIn = ramIn;
                    vue1.ramOut = ramOut;
                    console.log(ramOut);
                    console.log(ramIn);
                    var arr1 = [], arr2 = [], arr3 = [];
                    if (ramIn.length > 0) {
                        if (ramIn[vue1.ramKeyModel].length > ramOut[vue1.ramKeyModel].length) {
                            for (var r = 0; r < ramOut[vue1.ramKeyModel].length; r++) {
                                arr1.push(timestampToTime(ramOut[vue1.ramKeyModel][r].clock));
                                arr2.push((parseInt(ramOut[vue1.ramKeyModel][r].value) / 1024).toFixed(2));
                                arr3.push((parseInt(ramOut[vue1.ramKeyModel][r].value) / 1024).toFixed(2))
                            }
                        } else {
                            for (var g = 0; g < ramIn[vue1.ramKeyModel].length; g++) {
                                arr1.push(timestampToTime(ramIn[vue1.ramKeyModel][g].clock));
                                arr2.push((parseInt(ramIn[vue1.ramKeyModel][g].value) / 1024).toFixed(2));
                                arr3.push((parseInt(ramOut[vue1.ramKeyModel][g].value) / 1024).toFixed(2))
                            }
                        }
                    }
                    if (arr1.length > 0 && arr2.length > 0 && arr3.length > 0) {
                        option5.xAxis.data = arr1;
                        option5.series[0].data = arr2;
                        option5.series[1].data = arr3;
                        chart5.setOption(option5, true);
                        chart5.hideLoading();
                    } else {
                        option5.xAxis.data = [];
                        option5.series[0].data = [];
                        option5.series[1].data = [];
                        chart5.setOption(option5, true);
                        chart5.hideLoading();
                    }
                    for (var d = 0; d < used.length; d++) {
                        diskKey.push(used[d].split(',')[0].split('[')[1]);
                        usedVal2.push((parseFloat(usedVal[d]) + parseFloat(freeVal[d])).toFixed(2))
                    }
                    for (var e = 0; e < usedVal2.length; e++) {
                        freeVal2.push((parseFloat(usedVal[e]) / parseFloat(usedVal2[e])).toFixed(2))
                    }
                    for (var f = 0; f < freeVal2.length; f++) {
                        fv2.push(1 - parseFloat(freeVal2[f]))
                    }
                    if (diskKey.length > 0 && usedVal.length > 0 && freeVal.length > 0 && freeVal2.length > 0 && fv2.length > 0) {
                        option2.yAxis.data = diskKey;
                        option3.yAxis.data = diskKey;
                        option2.series[0].data = usedVal;
                        option2.series[1].data = freeVal;
                        option3.series[0].data = freeVal2;
                        option3.series[1].data = fv2;
                        chart2.setOption(option2, true);
                        chart3.setOption(option3, true);
                        chart2.hideLoading();
                        chart3.hideLoading();
                    } else {
                        option2.yAxis.data = [];
                        option3.yAxis.data = [];
                        option2.series[0].data = [];
                        option2.series[1].data = [];
                        option3.series[0].data = [];
                        option3.series[1].data = [];
                        chart2.setOption(option2, true);
                        chart3.setOption(option3, true);
                        chart2.hideLoading();
                        chart3.hideLoading();
                    }
                    if (data.crmFree && data.crmUsed) {
                        console.log(JSON.parse(data.crmFree).result);
                        console.log(data.crmUsed);
                        console.log(JSON.parse(data.crmUsed).result);
                        if (/*JSON.parse(data.crmFree).result.length > 0 &&*/ JSON.parse(data.crmUsed).result.length > 0) {
                            var ramFree = JSON.parse(data.crmFree).result, ramUsed = JSON.parse(data.crmUsed).result,
                                ramTime = [], free = [], ud = [];
                            console.log(ramUsed);
                            console.log(ramFree);
                            for (var k = 0; k < ramUsed.length; k++) {
                                ramTime.push(timestampToTime(ramUsed[k].clock));
                                // free.push((parseInt(ramFree[k].value) / 8589934592).toFixed(2));
                                ud.push((parseInt(ramUsed[k].value) / 8589934592).toFixed(2))
                            }
                            option7.xAxis.data = ramTime;
                            option7.series[0].data = ud;
                            // option7.series[1].data = free;
                            chart7.setOption(option7, true);
                            chart7.hideLoading();
                        } else {
                            option7.xAxis.data = [];
                            option7.series[0].data = [];
                            chart7.setOption(option7, true);
                            chart7.hideLoading();
                        }
                        console.log(ramTime);
                        console.log(free);
                        console.log(ud);
                    }
                    if (vue1.hostType == 'Windows') {
                        if (JSON.parse(data.Write).result.length > 0 && JSON.parse(data.Read).result.length > 0) {
                            var write = JSON.parse(data.Write).result, read = JSON.parse(data.Read).result,
                                writeVal = '',
                                readVal = '';
                            console.log(write);
                            console.log(read);
                            for (var b = 0; b < write.length; b++) {
                                option4.xAxis.data.push(timestampToTime(write[b].clock));
                                writeVal = parseFloat(write[b].value) / 1024;
                                readVal = parseFloat(read[b].value) / 1024;
                                option4.series[0].data.push(readVal.toFixed(2));
                                option4.series[1].data.push(writeVal.toFixed(2));
                            }
                            chart4.setOption(option4, true);
                            chart4.hideLoading();
                        } else {
                            option4.xAxis.data = [];
                            option4.series[0].data = [];
                            option4.series[1].data = [];
                            chart4.setOption(option4, true);
                            chart4.hideLoading();
                        }
                        option6.xAxis.data = [];
                        option6.series[0].data = [];
                        option6.series[1].data = [];
                        chart6.setOption(option6, true);
                        chart6.hideLoading();
                    } else if (vue1.hostType == 'Linux') {
                        if (reqTime.length > 0 && rpsValue.length > 0 && wpsValue.length > 0) {
                            option6.xAxis.data = reqTime;
                            option6.series[0].data = rpsValue;
                            option6.series[1].data = wpsValue;
                            chart6.setOption(option6, true);
                            chart6.hideLoading();
                        } else {
                            option6.xAxis.data = [];
                            option6.series[0].data = [];
                            option6.series[1].data = [];
                            chart6.setOption(option6, true);
                            chart6.hideLoading();
                        }
                        if (wrReatsTime.length > 0 && wReatsValues.length > 0 && rReatsValues.length > 0) {
                            option4.xAxis.data = wrReatsTime;
                            option4.series[0].data = rReatsValues;
                            option4.series[1].data = wReatsValues;
                            chart4.setOption(option4, true);
                            chart4.hideLoading();
                        } else {
                            option4.xAxis.data = [];
                            option4.series[0].data = [];
                            option4.series[1].data = [];
                            chart4.setOption(option4, true);
                            chart4.hideLoading();
                        }
                    }
                } else if (vue1.hostType == 'Network') {
                    console.log(data);
                    var networkcpu = JSON.parse(data.cpuUtilization5m).result, networkcpuX = [],
                        networkcpuY = [];
                    for (var m = 0; m < networkcpu.length; m++) {
                        networkcpuX.push(timestampToTime(parseInt(networkcpu[m].clock)));
                        networkcpuY.push(networkcpu[m].value)
                    }
                    option8.xAxis.data = networkcpuX;
                    option8.series.data = networkcpuY;
                    chart8.setOption(option8, true);
                    chart8.hideLoading();
                    var networkRamFree = JSON.parse(data.MemoryPoolFree).result,
                        networkRamUsed = JSON.parse(data.MemoryPoolUsed).result, networkRamFreeData = [],
                        networkRamUsedData = [];
                    for (var n = 0; n < networkRamFree.length; n++) {
                        networkRamFreeData[n] = {
                            name: timestampToTime2(parseInt(networkRamFree[n].clock)),
                            value: [timestampToTime2(parseInt(networkRamFree[n].clock)), networkRamFree[n].value]
                        }
                    }
                    for (var u = 0; u < networkRamUsed.length; u++) {
                        networkRamUsedData[u] = {
                            name: timestampToTime2(parseInt(networkRamUsed[u].clock)),
                            value: [timestampToTime2(parseInt(networkRamUsed[u].clock)), networkRamUsed[u].value]
                        }
                    }
                    option9.series[0].data = networkRamUsedData;
                    option9.series[1].data = networkRamFreeData;
                    chart9.setOption(option9, true);
                    chart9.hideLoading();
                    console.log(data.NetWork);
                    console.log(JSON.parse(data.NetWork));
                    console.log(JSON.parse(data.keyId));
                    var networkValues = Object.values(JSON.parse(data.NetWork));
                    vue1.netWorkkeyss = JSON.parse(data.keyId);
                    vue1.netWorkkeys2 = Object.keys(JSON.parse(data.NetWork));
                    console.log(networkValues);
                    var networkin = [], networkout = [];
                    for (var v = 0; v < networkValues.length; v++) {
                        networkin.push(JSON.parse(networkValues[v].in).result);
                        networkout.push(JSON.parse(networkValues[v].out).result)
                    }
                    console.log(networkin);
                    console.log(networkout);
                    var networkindata = [], networkoutdata = [], networkindata1 = [], networkoutdata1 = [],
                        networkindata2 = [], networkoutdata2 = [], networkindata3 = [], networkoutdata3 = [];
                    for (var y = 0; y < networkin[0].length; y++) {
                        networkindata[y] = {
                            name: timestampToTime2(parseFloat(networkin[0][y].clock)),
                            value: [timestampToTime2(parseFloat(networkin[0][y].clock)), networkin[0][y].value]
                        };
                        networkindata1[y] = {
                            name: timestampToTime2(parseFloat(networkin[1][y].clock)),
                            value: [timestampToTime2(parseFloat(networkin[1][y].clock)), networkin[1][y].value]
                        };
                        networkindata2[y] = {
                            name: timestampToTime2(parseFloat(networkin[2][y].clock)),
                            value: [timestampToTime2(parseFloat(networkin[2][y].clock)), networkin[2][y].value]
                        };
                        networkindata3[y] = {
                            name: timestampToTime2(parseFloat(networkin[3][y].clock)),
                            value: [timestampToTime2(parseFloat(networkin[3][y].clock)), networkin[3][y].value]
                        };
                    }
                    for (var z = 0; z < networkout[0].length; z++) {
                        networkoutdata[z] = {
                            name: timestampToTime2(parseFloat(networkout[0][z].clock)),
                            value: [timestampToTime2(parseFloat(networkout[0][z].clock)), networkout[0][z].value]
                        };
                        networkoutdata1[z] = {
                            name: timestampToTime2(parseFloat(networkout[1][z].clock)),
                            value: [timestampToTime2(parseFloat(networkout[1][z].clock)), networkout[1][z].value]
                        };
                        networkoutdata2[z] = {
                            name: timestampToTime2(parseFloat(networkout[2][z].clock)),
                            value: [timestampToTime2(parseFloat(networkout[2][z].clock)), networkout[2][z].value]
                        };
                        networkoutdata3[z] = {
                            name: timestampToTime2(parseFloat(networkout[3][z].clock)),
                            value: [timestampToTime2(parseFloat(networkout[3][z].clock)), networkout[3][z].value]
                        };
                    }
                    console.log(networkindata);
                    console.log(networkoutdata);
                    console.log(Object.keys(JSON.parse(data.NetWork)));
                    // option10.title.subtext = Object.keys(JSON.parse(data.NetWork))[0];
                    option10.series[0].data = networkindata;
                    option10.series[1].data = networkoutdata;
                    // option11.title.subtext = Object.keys(JSON.parse(data.NetWork))[1];
                    option11.series[0].data = networkindata1;
                    option11.series[1].data = networkoutdata1;
                    // option12.title.subtext = Object.keys(JSON.parse(data.NetWork))[2];
                    option12.series[0].data = networkindata2;
                    option12.series[1].data = networkoutdata2;
                    // option13.title.subtext = Object.keys(JSON.parse(data.NetWork))[3];
                    option13.series[0].data = networkindata3;
                    option13.series[1].data = networkoutdata3;
                    chart10.setOption(option10, true);
                    chart11.setOption(option11, true);
                    chart12.setOption(option12, true);
                    chart13.setOption(option13, true);
                    vue1.networkin = networkin;
                    vue1.networkout = networkout;
                    chart10.hideLoading();
                    chart11.hideLoading();
                    chart12.hideLoading();
                    chart13.hideLoading();
                }

            });
        },
        ramKeyChange: function () {
            console.log(vue1.ramKeyModel);
            chart5.showLoading();
            var arr1 = [], arr2 = [], arr3 = [];

            function timestampToTime(timestamp) {
                var date = new Date(timestamp * 1000);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
                Y = date.getFullYear() + '-';
                M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
                D = date.getDate() + ' ';
                h = date.getHours() + ':';
                m = date.getMinutes() + ':';
                s = date.getSeconds();
                return Y + M + D + h + m + s;
            }

            if (vue1.ramIn[vue1.ramKeyModel].length > vue1.ramOut[vue1.ramKeyModel].length) {
                for (var r = 0; r < vue1.ramOut[vue1.ramKeyModel].length; r++) {
                    arr1.push(timestampToTime(vue1.ramOut[vue1.ramKeyModel][r].clock));
                    arr2.push((parseInt(vue1.ramOut[vue1.ramKeyModel][r].value) / 1024).toFixed(2));
                    arr3.push((parseInt(vue1.ramOut[vue1.ramKeyModel][r].value) / 1024).toFixed(2))
                }
            } else {
                for (var g = 0; g < vue1.ramIn[vue1.ramKeyModel].length; g++) {
                    arr1.push(timestampToTime(vue1.ramIn[vue1.ramKeyModel][g].clock));
                    arr2.push((parseInt(vue1.ramIn[vue1.ramKeyModel][g].value) / 1024).toFixed(2));
                    arr3.push((parseInt(vue1.ramOut[vue1.ramKeyModel][g].value) / 1024).toFixed(2))
                }
            }

            option5.xAxis.data = arr1;
            option5.series[0].data = arr2;
            option5.series[1].data = arr3;
            chart5.setOption(option5, true);
            chart5.hideLoading();
        },
        markRe: function () {
            var _this = this;
            $.post("/monitor/getAllMark", function (data) {
                _this.mark = data;
            });
        },
        marksub: function (q, w, e) {
            for (var a = 0; a < w.length; a++) {
                e.push(q[w[a]])
            }
            console.log(e)
        },
        marksub2: function () {
            for (var a = 0; a < vue1.markModel2.length; a++) {
                vue1.tip2.push(vue1.mark[vue1.markModel2[a]])
            }
        },
        delsub: function (index, data) {
            console.log(data);
            var arr = [], arr2 = [];
            for (var a = 0; a < index.length; a++) {
                arr.push(data[index[a]].id)
            }
            for (var b = 0; b < data.length; b++) {
                if (arr.indexOf(data[b].id) >= 0) {

                } else {
                    arr2.push(data[b])
                }
            }
            console.log(arr);
            if (arr.length > 0) {
                $.ajax({
                    type: 'post',
                    url: '/monitor/deleteMark',
                    traditional: true,
                    data: {ids: arr},
                    success: function (d) {
                        vue1.mark = arr2;
                        vue1.markModel = []
                    }
                });
            }

        },
        editsub: function () {
            var arr1 = [], arr2 = [], ian = [], ho = [], markid = [];
            for (var j = 0; j < vue1.hosts2.length; j++) {
                arr1.push(vue1.hosts2[j].groupid)
            }

            function get(q, w, e) {
                for (var a = 0; a < q.length; a++) {
                    q[a].port = w.eq(a).find('.port').val();
                    if (w.eq(a).find('.iad')[0].checked) {
                        q[a].useIp = w.eq(a).find('.iad')[0].value;
                        q[a].ip = w.eq(a).find('.ip').val();
                        q[a].dns = '';
                    } else {
                        q[a].useIp = w.eq(a).find('.iad')[1].value;
                        q[a].dns = w.eq(a).find('.dns').val();
                        q[a].ip = '';
                    }
                    if (parseInt(e) == a) {
                        q[a].main = 1
                    } else {
                        q[a].main = 0
                    }
                }
            }

            if (vue1.mo1) {
                get(vue1.age, $('.age'), vue1.mmo1);
                for (var y = 0; y < vue1.age.length; y++) {
                    ian.push(JSON.stringify(vue1.age[y]));
                    // ian.push(vue1.age[y])
                }
            }
            if (vue1.mo2) {
                get(vue1.snm, $('.snm'), vue1.mmo2);
                for (var b = 0; b < vue1.macro.length; b++) {
                    vue1.macro[b].Macro = $('.snmMacro').eq(b).find('.nam').val();
                    vue1.macro[b].Value = $('.snmMacro').eq(b).find('.val').val();
                }
                for (var u = 0; u < vue1.snm.length; u++) {
                    ian.push(JSON.stringify(vue1.snm[u]));
                    // ian.push(vue1.snm[u])
                }
            }
            for (var z = 0; z < vue1.macro.length; z++) {
                ho.push(JSON.stringify(vue1.macro[z]))
            }
            if (vue1.mo3) {
                get(vue1.ipm, $('.ipm'), vue1.mmo3);
                for (var t = 0; t < vue1.ipm.length; t++) {
                    ian.push(JSON.stringify(vue1.ipm[t]));
                    // ian.push(vue1.ipm[t])
                }
            } else {
                vue1.ty3 = '';
                vue1.ty4 = '';
                vue1.ty5 = '';
                vue1.ty6 = '';
            }
            for (var p = 0; p < vue1.edittemp.length; p++) {
                arr2.push(vue1.edittemp[p].templateid)
            }
            for (var g = 0; g < vue1.tip.length; g++) {
                markid.push(vue1.tip[g].id)
            }
            console.log(vue1.ehid);
            console.log(vue1.name2);
            console.log(arr1);
            console.log(markid);
            console.log(ian);
            console.log(ho);
            console.log(vue1.e1);
            console.log(vue1.e2);
            console.log(vue1.e3);
            console.log(vue1.e4);
            console.log(arr2);
            var substr = {
                'id': vue1.ehid,
                'hostname': vue1.name2,
                'groupId': arr1,
                'mark': markid,
                'hostInterface': ian,
                'macro': ho,
                'ipmi_authtype': vue1.e1,
                'ipmi_privilege': vue1.e2,
                'ipmi_username': vue1.e3,
                'ipmi_password': vue1.e4,
                'templateId': arr2
            };
            console.log(substr);
            if (vue1.mo1 || vue1.mo2 || vue1.mo1) {
                $.ajax({
                    type: 'post',
                    url: '/monitor/resourceUpdate',
                    traditional: true,
                    ContentType: 'application/json',
                    data: {
                        // hostInterface:substr
                        id: vue1.ehid,
                        hostname: vue1.name2,
                        groupId: arr1,
                        mark: markid,
                        hostInterface: ian,
                        macro: ho,
                        ipmi_authtype: vue1.e1,
                        ipmi_privilege: vue1.e2,
                        ipmi_username: vue1.e3,
                        ipmi_password: vue1.e4,
                        templateId: arr2
                    },
                    dataType: 'json',
                    success: function (data) {
                        console.log(JSON.parse(data));
                        var d = JSON.parse(data);
                        if (d.error) {
                            //alert(d.error.data)
                            layui.use(['layer', 'form'], function () {
                                var layer = layui.layer
                                    , form = layui.form;
                                layer.alert(d.error.data, {icon: 7});
                            });
                        } else {
                            //alert('修改成功');
                            layui.use(['layer', 'form'], function () {
                                var layer = layui.layer
                                    , form = layui.form;
                                layer.alert('修改成功', {icon: 6});
                            });
                            $('#ed').modal('hide');
                        }
                    }
                });
            } else {
                //alert('至少选择一种监控方式')
                layui.use(['layer', 'form'], function () {
                    var layer = layui.layer
                        , form = layui.form;
                    layer.alert('至少选择一种监控方式', {icon: 7});
                });
            }
        },
        icd: function (i) {
            var arr = [];
            for (var a = 0; a < vue1.temp.length; a++) {
                arr[a] = a
            }
            if (arr.indexOf(i) >= 0) {
                vue1.temp.splice(i, 1)
            }
        },
        ste: function () {
            for (var a = 0; a < vue1.templateModel.length; a++) {
                vue1.temp.push(vue1.template[vue1.templateModel[a]])
            }
            vue1.templateModel = []
        },
        add: function () {
            vue1.da.push(vue1.obj1);
        },
        de: function (i) {
            var arr = [];
            for (var a = 0; a < vue1.da.length; a++) {
                arr[a] = a
            }
            if (arr.indexOf(i) >= 0) {
                vue1.da.splice(i, 1)
            }
        },
        add2: function () {
            vue1.da2.push(vue1.obj2);
        },
        de2: function (i) {
            var arr = [];
            for (var a = 0; a < vue1.da2.length; a++) {
                arr[a] = a
            }
            if (arr.indexOf(i) >= 0) {
                vue1.da2.splice(i, 1)
            }
        },
        add3: function () {
            vue1.da3.push(vue1.obj3);
        },
        de3: function (i) {
            var arr = [];
            for (var a = 0; a < vue1.da3.length; a++) {
                arr[a] = a
            }
            if (arr.indexOf(i) >= 0) {
                vue1.da3.splice(i, 1)
            }
        },
        add4: function () {
            vue1.da4.push(vue1.obj4);
        },
        de4: function (i) {
            var arr = [];
            for (var a = 0; a < vue1.da4.length; a++) {
                arr[a] = a
            }
            if (arr.indexOf(i) >= 0) {
                vue1.da4.splice(i, 1)
            }
        },
        su1: function () {
            console.log(vue1.tip);
            var arr1 = [], arr2 = [], ian = [], ho = [], markid = [];
            for (var j = 0; j < vue1.hosts.length; j++) {
                arr1.push(vue1.hosts[j].groupid)
            }

            function get(q, w, e) {
                for (var a = 0; a < q.length; a++) {
                    q[a].port = w.eq(a).find('.port').val();
                    if (w.eq(a).find('.iad')[0].checked) {
                        q[a].useIp = w.eq(a).find('.iad')[0].value;
                        q[a].ip = w.eq(a).find('.ip').val();
                        q[a].dns = '';
                    } else {
                        q[a].useIp = w.eq(a).find('.iad')[1].value;
                        q[a].dns = w.eq(a).find('.dns').val();
                        q[a].ip = '';
                    }
                    if (parseInt(e) == a) {
                        q[a].main = 1
                    } else {
                        q[a].main = 0
                    }
                }
            }

            if (vue1.m1) {
                get(vue1.da, $('.agent'), vue1.vm);
                for (var y = 0; y < vue1.da.length; y++) {
                    ian.push(JSON.stringify(vue1.da[y]))
                }
            }
            if (vue1.m2) {
                get(vue1.da2, $('.snmp'), vue1.vm2);
                for (var b = 0; b < vue1.da3.length; b++) {
                    vue1.da3[b].Macro = $('.snmph').eq(b).find('.nam').val();
                    vue1.da3[b].Value = $('.snmph').eq(b).find('.val').val();
                }
                for (var u = 0; u < vue1.da2.length; u++) {
                    ian.push(JSON.stringify(vue1.da2[u]))
                }
            }
            if (vue1.m3) {
                get(vue1.da4, $('.ipmi'), vue1.vm4);
                for (var t = 0; t < vue1.da4.length; t++) {
                    ian.push(JSON.stringify(vue1.da4[t]))
                }
            } else {
                vue1.ty3 = '';
                vue1.ty4 = '';
                vue1.ty5 = '';
                vue1.ty6 = '';
            }
            for (var g = 0; g < vue1.tip.length; g++) {
                markid.push(vue1.tip[g].id)
            }
            console.log(markid);
            for (var c = 0; c < vue1.da3.length; c++) {
                ho.push(JSON.stringify(vue1.da3[c]))
            }
            console.log(vue1.ty2);
            console.log(arr1);
            console.log(ian);
            console.log(ho);
            console.log(markid);
            console.log(vue1.ty3);
            console.log(vue1.ty4);
            console.log(vue1.ty5);
            console.log(vue1.ty6);
            console.log(arr2);
            for (var p = 0; p < vue1.temp.length; p++) {
                arr2.push(vue1.temp[p].templateid)
            }
            if (vue1.m1 || vue1.m2 || vue1.m3) {
                $.ajax({
                    type: 'post',
                    url: '/monitor/createHost ',
                    traditional: true,
                    ContentType: 'application/json',
                    data: {
                        hostname: vue1.ty2,
                        groupId: arr1,
                        mark: markid,
                        hostInterface: ian,
                        macro: ho,
                        ipmi_authtype: vue1.ty3,
                        ipmi_privilege: vue1.ty4,
                        ipmi_username: vue1.ty5,
                        ipmi_password: vue1.ty6,
                        templateId: arr2,
                        select:vue1.typ
                    },
                    dataType: 'json',
                    success: function (data2) {
                        console.log(JSON.parse(data2));
                        var d = JSON.parse(data2);
                        if (d.error) {
                            //alert(d.error.data)
                            layui.use(['layer', 'form'], function () {
                                var layer = layui.layer
                                    , form = layui.form;
                                layer.alert(d.error.data, {icon: 7});
                            });
                        } else {
                            //alert('创建成功')
                            layui.use(['layer', 'form'], function () {
                                var layer = layui.layer
                                    , form = layui.form;
                                layer.alert('创建成功'+ ',3秒后刷新', {icon: 6});
                            });
                            setInterval(function(){location.reload()},3000);
                          /*  $('#myModal').modal('hide');
                            $.post("/monitor/getResource", function (data) {
                                var arr = [];
                                var obj = {
                                    0: 'avi1',
                                    1: 'avi2',
                                    2: 'avi3'
                                };
                                for (var a = 0; a < data.length; a++) {
                                    arr.push(JSON.parse(data[a]));
                                    if (arr[a].items) {
                                        arr[a].cpu = arr[a].items[0].cpuUsed;
                                        arr[a].ram = arr[a].items[0].memotyUsed;
                                        arr[a].typ = arr[a].items[0].type
                                    } else {
                                        arr[a].cpu = '';
                                        arr[a].ram = '';
                                        arr[a].typ = ''
                                    }
                                    if (arr[a].status == '0') {
                                        vue1.stamod.push(a)
                                    }
                                    arr[a].back = vue1.className[arr[a].typ];
                                    arr[a].available_ = obj[arr[a].available];
                                    arr[a].ipmi_available_ = obj[arr[a].ipmi_available];
                                    arr[a].snmp_available_ = obj[arr[a].snmp_available];
                                    arr[a].jmx_available_ = obj[arr[a].jmx_available];
                                    if (arr[a].available_ == 'avi2' || arr[a].ipmi_available_ == 'avi2' || arr[a].snmp_available_ == 'avi2' || arr[a].jmx_available_ == 'avi2') {
                                        arr[a].color = 'avi2'
                                    } else {
                                        arr[a].color = 'avi3'
                                    }
                                    if (arr[a].typ == 'network') {
                                        arr[a].typ = 'Network'
                                    }
                                }
                                console.log(arr);
                                vue1.d = arr;
                                vue1.normal = arr[0].normal;
                                vue1.error = arr[0].error;
                            });
                            vue1.ty = '';
                            vue1.ty2 = '';
                            vue1.tips = '';
                            vue1.hosts = [];
                            vue1.tip = [];
                            vue1.m1 = false;
                            vue1.m2 = false;
                            vue1.m3 = false;
                            vue1.da = [
                                {
                                    port: 10050,
                                    type: '1',
                                    ip: '',
                                    dns: '',
                                    main: '',
                                    useIp: ''
                                }
                            ];
                            vue1.da2 = [
                                {
                                    port: 161,
                                    type: '2',
                                    ip: '',
                                    dns: '',
                                    main: '',
                                    useIp: ''
                                }
                            ];
                            vue1.da3 = [{
                                Macro: '',
                                Value: ''
                            }];
                            vue1.da4 = [
                                {
                                    port: 623,
                                    type: '3',
                                    ip: '',
                                    dns: '',
                                    main: '',
                                    useIp: ''
                                }
                            ];
                            vue1.ty3 = '';
                            vue1.ty4 = '';
                            vue1.ty5 = '';
                            vue1.ty6 = '';
                            vue1.temp = [];*/
                        }
                    }
                });
            } else {
                //alert('至少选择一种监控方式')
                layui.use(['layer', 'form'], function () {
                    var layer = layui.layer
                        , form = layui.form;
                    layer.alert('至少选择一种监控方式', {icon: 7});
                });
            }
        },
        iadc: function () {
            if (vue1.iad == '0') {
                vue1.s = true;
                vue1.s2 = false;
            } else {
                vue1.s = false;
                vue1.s2 = true;
            }
        },
        iadc2: function () {
            if (vue1.iad2 == '0') {
                vue1.s_ = true;
                vue1.s2_ = false;
            } else {
                vue1.s_ = false;
                vue1.s2_ = true;
            }
        },
        iadc3: function () {
            if (vue1.iad3 == '0') {
                vue1.s_3 = true;
                vue1.s2_3 = false;
            } else {
                vue1.s_3 = false;
                vue1.s2_3 = true;
            }
        },
        r1: function () {
            var arr = [];
            for (var a = 0; a < vue1.hgp2.length; a++) {
                arr.push(vue1.hostGroup[vue1.hgp2[a]])
            }
            vue1.hosts2 = arr;
        },
        r2: function () {
            if (vue1.tips) {
                $.post("/monitor/createMark", {name: vue1.tips}, function (data) {
                    console.log(data);
                    vue1.tip2.push(data);
                    vue1.tips = ''
                });
            }
        },
        r3: function () {
            var arr = [];
            for (var a = 0; a < vue1.tip2.length; a++) {
                if (vue1.tipindex2.indexOf(a) >= 0) {

                } else {
                    arr.push(vue1.tip2[a])
                }
            }
            vue1.tip2 = arr
        },
        del: function () {
            if (vue1.checkModel.length > 0) {
                var _this = this, arr = [], id = [];
                var len = _this.d.length;
                for (var i = 0; i < len; i++) {
                    if (_this.checkModel.indexOf(i) >= 0) {

                    } else {
                        arr.push(_this.d[i])
                    }
                }
                for (var n = 0; n < _this.checkModel.length; n++) {
                    id.push(_this.d[_this.checkModel[n]].hostid)
                }
                console.log(id);
                $.ajax({
                    type: 'post',
                    url: '/monitor/resourceDelete ',
                    traditional: true,
                    data: {ids: id},
                    dataType: 'json',
                    success: function (data) {
                        console.log(data);
                        if (JSON.parse(data).result) {
                            //alert('删除成功');
                            layui.use(['layer', 'form'], function () {
                                var layer = layui.layer
                                    , form = layui.form;
                                layer.alert('删除成功', {icon: 6});
                            });
                            vue1.d = arr;
                            vue1.checkboxModel = [];
                        }
                    }
                });
            } else {
                //alert('请选择主机')
                layui.use(['layer', 'form'], function () {
                    var layer = layui.layer
                        , form = layui.form;
                    layer.alert('请选择主机', {icon: 7});
                });
            }
        },
        see: function (data) {
            console.log(data);
            vue1.se = data;
        },
        checkedAll: function (event) {
            var _this = this;
            if (!event.currentTarget.checked) {
                this.checkModel = [];
            } else { //实现全选
                _this.checkModel = [];
                _this.d.forEach(function (item, i) {
                    _this.checkModel.push(i);
                });
            }
        },
        t1: function () {
            var arr = [];
            for (var a = 0; a < vue1.hgp.length; a++) {
                arr.push(vue1.hostGroup[vue1.hgp[a]])
            }
            vue1.hosts = arr;
        },
        t2: function (t, d) {
            console.log(t);
            console.log(d);
            if (t) {
                $.post("/monitor/createMark", {name: t}, function (data) {
                    console.log(data);
                    if (data == -1) {
                        //alert('标签已存在');
                        layui.use(['layer', 'form'], function () {
                            var layer = layui.layer
                                , form = layui.form;
                            layer.alert('标签已存在', {icon: 7});
                        });
                    } else {
                        var obj = {
                            name: t,
                            id: data
                        };
                        d.push(obj);
                    }
                    vue1.tips = ''
                });
            }
        },
        sta: function (id, sta) {
            console.log(sta);
            console.log(vue1.stamod);
            var status = '';
            if (sta == '1') {
                status = true
            } else {
                status = false
            }
            $.post("/monitor/resourceMassupdate", {id: id, status: status}, function (data) {
                console.log(JSON.parse(data));
                if (JSON.parse(data) == '修改成功') {
                    //alert(data)
                    layui.use(['layer', 'form'], function () {
                        var layer = layui.layer
                            , form = layui.form;
                        layer.alert('修改成功', {icon: 6});
                    });
                } else {
                    //alert(data);
                    layui.use(['layer', 'form'], function () {
                        var layer = layui.layer
                            , form = layui.form;
                        layer.alert(JSON.parse(data), {icon: 5});
                    });
                }
            });
        },
        t3: function () {
            var arr = [];
            for (var a = 0; a < vue1.tip.length; a++) {
                if (vue1.tipindex.indexOf(a) >= 0) {

                } else {
                    arr.push(vue1.tip[a])
                }
            }
            vue1.tip = arr
        },
        ty: function () {
            console.log(1);
            vue1.m1 = false;
            vue1.m2 = false;
            vue1.m3 = false;
            vue1.m4 = false;
            for (var p = 0; p < 6; p++) {
                if (parseInt(vue1.typ) == p) {
                    $('.img').eq(p).show()
                } else {
                    $('.img').eq(p).hide()
                }
            }
            if (vue1.typ == '0' || vue1.typ == '1') {
                vue1.hide1 = true;
                vue1.hide2 = true;
                vue1.hide3 = true;
                vue1.hide4 = false
            } else if (vue1.typ == '2') {
                vue1.hide1 = false;
                vue1.hide2 = true;
                vue1.hide3 = true;
                vue1.hide4 = false
            } else if (vue1.typ == '3') {
                vue1.hide1 = false;
                vue1.hide2 = true;
                vue1.hide3 = false;
                vue1.hide4 = false
            } else if (vue1.typ == '4') {
                vue1.hide1 = false;
                vue1.hide2 = false;
                vue1.hide3 = false;
                vue1.hide4 = true
            } else if (vue1.typ == '5') {
                vue1.hide1 = false;
                vue1.hide2 = true;
                vue1.hide3 = false;
                vue1.hide4 = true
            }
        },
        show: function (i, ho, typ) {
            var obj10 = {
                    0: "未分类",
                    1: "信息",
                    2: "警告",
                    3: "一般严重",
                    4: "严重",
                    5: "灾难"
                },
                obj20 = {
                    0: '启用',
                    1: "未启用"
                };
            vue1.getTriggerName = i;
            console.log(i);
            console.log(ho);
            console.log(typ);
            $.post("/monitor/getTriggerByHostId", {hostId: i}, function (data) {
                console.log(data);
                var d = JSON.parse(JSON.parse(data)).result;
                console.log(d);
                for (var a = 0; a < d.length; a++) {
                    d[a].status_ = obj20[d[a].status];
                    d[a].priority_ = obj10[d[a].priority]
                }
                vue1.trigger = d;
            });




            vue1.timeModel = '3600';
            vue1.timeModel2 = "3600";
            chart1.showLoading();
            chart4.showLoading();
            chart2.showLoading();
            chart3.showLoading();
            chart5.showLoading();
            chart6.showLoading();
            chart7.showLoading();
            chart8.showLoading();
            chart9.showLoading();
            chart10.showLoading();
            chart11.showLoading();
            chart12.showLoading();
            chart13.showLoading();

            function timestampToTime(timestamp) {
                var date = new Date(timestamp * 1000);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
                Y = date.getFullYear() + '-';
                M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
                D = date.getDate() + ' ';
                h = date.getHours() + ':';
                m = date.getMinutes() + ':';
                s = date.getSeconds();
                return Y + M + D + h + m + s;
            }

            function timestampToTime2(timestamp) {
                var date = new Date(timestamp * 1000);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
                Y = date.getFullYear() + '/';
                M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '/';
                D = date.getDate() + ' ';
                h = date.getHours() + ':';
                if (date.getMinutes() >= 10) {
                    m = date.getMinutes() + ':';
                } else {
                    m = '0' + date.getMinutes() + ':';
                }
                if (date.getSeconds() >= 10) {
                    s = date.getSeconds();
                } else {
                    s = '0' + date.getSeconds();
                }
                return Y + M + D + h + m + s;
            }

            vue1.hostType = typ;
            vue1.hosti = i;
            vue1.hostho = ho;
            if (typ) {
                if (typ == 'Windows' || typ == 'Linux') {
                    vue1.pc = true;
                    vue1.network = false;
                } else {
                    vue1.pc = false;
                    vue1.network = true;
                }
                $.ajax({
                    type: 'post',
                    url: '/monitor/getDraw',
                    data: {host: ho, hostid: i},
                    success: function (data) {
                        console.log(data);
                        if (typ == 'Windows' || typ == 'Linux') {
                            if (JSON.parse(data.cpuUsed).result.length > 0) {
                                var cpuUsed = JSON.parse(data.cpuUsed).result, cpuUsedTime = [], cpuUsedValue = [];
                                for (var a = 0; a < cpuUsed.length; a++) {
                                    cpuUsedTime.push(timestampToTime(cpuUsed[a].clock));
                                    cpuUsedValue.push(cpuUsed[a].value)
                                }
                                option.xAxis.data = cpuUsedTime;
                                option.series.data = cpuUsedValue;
                                chart1.setOption(option, true);
                                chart1.hideLoading();
                            } else {
                                option.xAxis.data = [];
                                option.series.data = [];
                                chart1.setOption(option, true);
                                chart1.hideLoading();
                            }
                            var key = Object.keys(data), val = Object.values(data), used = [], usedVal = [],
                                freeVal = [],
                                usedVal2 = [], freeVal2 = [], fv2 = [], diskKey = [], uv = '', fv = '', ramKey = [],
                                ramIn = [], readReats = [], writeReats = [],
                                ramOut = [], wps = '', rps = '', reqTime = [], rpsValue = [], wpsValue = [];
                            for (var c = 0; c < key.length; c++) {
                                if (key[c].indexOf('vfs.fs.size') >= 0) {
                                    if (JSON.parse(val[c]).result.length > 0) {
                                        if (key[c].indexOf('used') >= 0) {
                                            used.push(key[c]);
                                            uv = parseFloat(JSON.parse(val[c]).result[0].value) / 1073741824;
                                            usedVal.push(uv.toFixed(2))
                                        } else {
                                            fv = parseFloat(JSON.parse(val[c]).result[0].value) / 1073741824;
                                            freeVal.push(fv.toFixed(2))
                                        }
                                    }
                                }
                                if (key[c].indexOf('net.if.') >= 0) {
                                    if (JSON.parse(val[c]).result.length > 0) {
                                        if ((key[c].indexOf('net.if.in') >= 0)) {
                                            ramKey.push(key[c].split('[')[1].split(']')[0]);
                                            ramIn.push(JSON.parse(val[c]).result)
                                        } else {
                                            ramOut.push(JSON.parse(val[c]).result)
                                        }
                                    }
                                }
                                if (key[c].indexOf('custom.vfs.dev.iostats') >= 0) {
                                    if (JSON.parse(val[c]).result.length > 0) {
                                        if (key[c].indexOf('custom.vfs.dev.iostats.rps') >= 0) {
                                            rps = JSON.parse(val[c]).result
                                        }
                                        if (key[c].indexOf('custom.vfs.dev.iostats.wps') >= 0) {
                                            wps = JSON.parse(val[c]).result
                                        }
                                        if (key[c].indexOf('custom.vfs.dev.iostats.rkB') >= 0) {
                                            readReats = JSON.parse(val[c]).result
                                        }
                                        if (key[c].indexOf('custom.vfs.dev.iostats.wkB') >= 0) {
                                            writeReats = JSON.parse(val[c]).result
                                        }
                                    }
                                }
                            }
                            var wrlen = '', wrReatsTime = [], wReatsValues = [], rReatsValues = [];
                            if (readReats.length > writeReats.length) {
                                wrlen = writeReats.length
                            } else {
                                wrlen = readReats.length
                            }
                            for (var w = 0; w < wrlen; w++) {
                                wrReatsTime.push(timestampToTime(writeReats[w].clock));
                                wReatsValues.push(writeReats[w].value);
                                rReatsValues.push(readReats[w].value)
                            }
                            console.log(wps);
                            for (var j = 0; j < rps.length; j++) {
                                reqTime.push(timestampToTime(rps[j].clock));
                                rpsValue.push(rps[j].value);
                                wpsValue.push(wps[j].value);
                            }
                            vue1.ramKey = ramKey;
                            vue1.ramIn = ramIn;
                            vue1.ramOut = ramOut;
                            console.log(ramOut);
                            console.log(ramIn);
                            console.log(vue1.ramKeyModel);
                            var arr1 = [], arr2 = [], arr3 = [], ramlength = '';
                            if (ramIn[0].length > ramOut[0].length) {
                                ramlength = ramOut[0].length
                            } else {
                                ramlength = ramIn[0].length
                            }
                            if (ramIn.length > 0) {
                                for (var g = 0; g < ramlength; g++) {
                                    arr1.push(timestampToTime(ramIn[vue1.ramKeyModel][g].clock));
                                    arr2.push((parseInt(ramIn[vue1.ramKeyModel][g].value) / 1024).toFixed(2));
                                    arr3.push((parseInt(ramOut[vue1.ramKeyModel][g].value) / 1024).toFixed(2))
                                }
                            }
                            if (arr1.length > 0 && arr2.length > 0 && arr3.length > 0) {
                                option5.xAxis.data = arr1;
                                option5.series[0].data = arr2;
                                option5.series[1].data = arr3;
                                chart5.setOption(option5, true);
                                chart5.hideLoading();
                            } else {
                                option5.xAxis.data = [];
                                option5.series[0].data = [];
                                option5.series[1].data = [];
                                chart5.setOption(option5, true);
                                chart5.hideLoading();
                            }
                            for (var d = 0; d < used.length; d++) {
                                diskKey.push(used[d].split(',')[0].split('[')[1]);
                                usedVal2.push((parseFloat(usedVal[d]) + parseFloat(freeVal[d])).toFixed(2))
                            }
                            for (var e = 0; e < usedVal2.length; e++) {
                                freeVal2.push((parseFloat(usedVal[e]) / parseFloat(usedVal2[e])).toFixed(2))
                            }
                            for (var f = 0; f < freeVal2.length; f++) {
                                fv2.push(1 - parseFloat(freeVal2[f]))
                            }
                            if (diskKey.length > 0 && usedVal.length > 0 && freeVal.length > 0 && freeVal2.length > 0 && fv2.length > 0) {
                                option2.yAxis.data = diskKey;
                                option3.yAxis.data = diskKey;
                                option2.series[0].data = usedVal;
                                option2.series[1].data = freeVal;
                                option3.series[0].data = freeVal2;
                                option3.series[1].data = fv2;
                                chart2.setOption(option2, true);
                                chart3.setOption(option3, true);
                                chart2.hideLoading();
                                chart3.hideLoading();
                            } else {
                                option2.yAxis.data = [];
                                option3.yAxis.data = [];
                                option2.series[0].data = [];
                                option2.series[1].data = [];
                                option3.series[0].data = [];
                                option3.series[1].data = [];
                                chart2.setOption(option2, true);
                                chart3.setOption(option3, true);
                                chart2.hideLoading();
                                chart3.hideLoading();
                            }
                            if (data.crmUsed && data.crmFree) {
                                console.log(JSON.parse(data.crmFree).result);
                                console.log(data.crmUsed);
                                console.log(JSON.parse(data.crmUsed).result);
                                if (/*JSON.parse(data.crmFree).result.length > 0 &&*/ JSON.parse(data.crmUsed).result.length > 0) {
                                    var ramFree = JSON.parse(data.crmFree).result,
                                        ramUsed = JSON.parse(data.crmUsed).result,
                                        ramTime = [], free = [], ud = [];
                                    console.log(ramUsed);
                                    console.log(ramFree);
                                    for (var k = 0; k < ramUsed.length; k++) {
                                        ramTime.push(timestampToTime(ramUsed[k].clock));
                                        // free.push((parseInt(ramFree[k].value) / 8589934592).toFixed(2));
                                        ud.push((parseInt(ramUsed[k].value) / 8589934592).toFixed(2))
                                    }
                                    option7.xAxis.data = ramTime;
                                    option7.series[0].data = ud;
                                    // option7.series[1].data = free;
                                    chart7.setOption(option7, true);
                                    chart7.hideLoading();
                                } else {
                                    option7.xAxis.data = [];
                                    option7.series[0].data = [];
                                    chart7.setOption(option7, true);
                                    chart7.hideLoading();
                                }
                            } else {
                                // alert('未配置监控项');
                                chart7.hideLoading();
                            }
                            console.log(ramTime);
                            console.log(free);
                            console.log(ud);
                            if (typ == 'Windows') {
                                if (JSON.parse(data.Write).result.length > 0 && JSON.parse(data.Read).result.length > 0) {
                                    var write = JSON.parse(data.Write).result, read = JSON.parse(data.Read).result,
                                        writeVal = '',
                                        readVal = '', wrlen = '';
                                    console.log(write);
                                    console.log(read);
                                    if (write.length > read.length) {
                                        wrlen = read.length
                                    } else {
                                        wrlen = write.length
                                    }
                                    for (var b = 0; b < wrlen.length; b++) {
                                        option4.xAxis.data.push(timestampToTime(write[b].clock));
                                        writeVal = parseFloat(write[b].value) / 1024;
                                        readVal = parseFloat(read[b].value) / 1024;
                                        option4.series[0].data.push(readVal.toFixed(2));
                                        option4.series[1].data.push(writeVal.toFixed(2));
                                    }
                                    chart4.setOption(option4, true);
                                    chart4.hideLoading();
                                } else {
                                    option4.xAxis.data = [];
                                    option4.series[0].data = [];
                                    option4.series[1].data = [];
                                    chart4.setOption(option4, true);
                                    chart4.hideLoading();
                                }
                                option6.xAxis.data = [];
                                option6.series[0].data = [];
                                option6.series[1].data = [];
                                chart6.setOption(option6, true);
                                chart6.hideLoading();
                            } else if (typ == 'Linux') {
                                if (reqTime.length > 0 && rpsValue.length > 0 && wpsValue.length > 0) {
                                    option6.xAxis.data = reqTime;
                                    option6.series[0].data = rpsValue;
                                    option6.series[1].data = wpsValue;
                                    chart6.setOption(option6, true);
                                    chart6.hideLoading();
                                } else {
                                    option6.xAxis.data = [];
                                    option6.series[0].data = [];
                                    option6.series[1].data = [];
                                    chart6.setOption(option6, true);
                                    chart6.hideLoading();
                                }
                                if (wrReatsTime.length > 0 && wReatsValues.length > 0 && rReatsValues.length > 0) {
                                    option4.xAxis.data = wrReatsTime;
                                    option4.series[0].data = rReatsValues;
                                    option4.series[1].data = wReatsValues;
                                    chart4.setOption(option4, true);
                                    chart4.hideLoading();
                                } else {
                                    option4.xAxis.data = [];
                                    option4.series[0].data = [];
                                    option4.series[1].data = [];
                                    chart4.setOption(option4, true);
                                    chart4.hideLoading();
                                }
                            }
                        } else if (typ == 'Network') {
                            console.log(data);
                            var networkcpu = JSON.parse(data.cpuUtilization5m).result, networkcpuX = [],
                                networkcpuY = [];
                            for (var m = 0; m < networkcpu.length; m++) {
                                networkcpuX.push(timestampToTime(parseInt(networkcpu[m].clock)));
                                networkcpuY.push(networkcpu[m].value)
                            }
                            option8.xAxis.data = networkcpuX;
                            option8.series.data = networkcpuY;
                            chart8.setOption(option8, true);
                            chart8.hideLoading();
                            var networkRamFree = JSON.parse(data.MemoryPoolFree).result,
                                networkRamUsed = JSON.parse(data.MemoryPoolUsed).result, networkRamFreeData = [],
                                networkRamUsedData = [];
                            for (var n = 0; n < networkRamFree.length; n++) {
                                networkRamFreeData[n] = {
                                    name: timestampToTime2(parseInt(networkRamFree[n].clock)),
                                    value: [timestampToTime2(parseInt(networkRamFree[n].clock)), networkRamFree[n].value]
                                }
                            }
                            for (var u = 0; u < networkRamUsed.length; u++) {
                                networkRamUsedData[u] = {
                                    name: timestampToTime2(parseInt(networkRamUsed[u].clock)),
                                    value: [timestampToTime2(parseInt(networkRamUsed[u].clock)), networkRamUsed[u].value]
                                }
                            }
                            option9.series[0].data = networkRamUsedData;
                            option9.series[1].data = networkRamFreeData;
                            chart9.setOption(option9, true);
                            chart9.hideLoading();
                            console.log(data.NetWork);
                            console.log(JSON.parse(data.NetWork));
                            console.log(JSON.parse(data.keyId));
                            var networkValues = Object.values(JSON.parse(data.NetWork));
                            vue1.netWorkkeyss = JSON.parse(data.keyId);
                            vue1.netWorkkeys2 = Object.keys(JSON.parse(data.NetWork));
                            console.log(networkValues);
                            var networkin = [], networkout = [];
                            for (var v = 0; v < networkValues.length; v++) {
                                networkin.push(JSON.parse(networkValues[v].in).result);
                                networkout.push(JSON.parse(networkValues[v].out).result)
                            }
                            console.log(networkin);
                            console.log(networkout);
                            var networkindata = [], networkoutdata = [], networkindata1 = [], networkoutdata1 = [],
                                networkindata2 = [], networkoutdata2 = [], networkindata3 = [], networkoutdata3 = [];
                            for (var y = 0; y < networkin[0].length; y++) {
                                networkindata[y] = {
                                    name: timestampToTime2(parseFloat(networkin[0][y].clock)),
                                    value: [timestampToTime2(parseFloat(networkin[0][y].clock)), networkin[0][y].value]
                                };
                                networkindata1[y] = {
                                    name: timestampToTime2(parseFloat(networkin[1][y].clock)),
                                    value: [timestampToTime2(parseFloat(networkin[1][y].clock)), networkin[1][y].value]
                                };
                                networkindata2[y] = {
                                    name: timestampToTime2(parseFloat(networkin[2][y].clock)),
                                    value: [timestampToTime2(parseFloat(networkin[2][y].clock)), networkin[2][y].value]
                                };
                                networkindata3[y] = {
                                    name: timestampToTime2(parseFloat(networkin[3][y].clock)),
                                    value: [timestampToTime2(parseFloat(networkin[3][y].clock)), networkin[3][y].value]
                                };
                            }
                            for (var z = 0; z < networkout[0].length; z++) {
                                networkoutdata[z] = {
                                    name: timestampToTime2(parseFloat(networkout[0][z].clock)),
                                    value: [timestampToTime2(parseFloat(networkout[0][z].clock)), networkout[0][z].value]
                                };
                                networkoutdata1[z] = {
                                    name: timestampToTime2(parseFloat(networkout[1][z].clock)),
                                    value: [timestampToTime2(parseFloat(networkout[1][z].clock)), networkout[1][z].value]
                                };
                                networkoutdata2[z] = {
                                    name: timestampToTime2(parseFloat(networkout[2][z].clock)),
                                    value: [timestampToTime2(parseFloat(networkout[2][z].clock)), networkout[2][z].value]
                                };
                                networkoutdata3[z] = {
                                    name: timestampToTime2(parseFloat(networkout[3][z].clock)),
                                    value: [timestampToTime2(parseFloat(networkout[3][z].clock)), networkout[3][z].value]
                                };
                            }
                            console.log(networkindata);
                            console.log(networkoutdata);
                            console.log(Object.keys(JSON.parse(data.NetWork)));
                            // option10.title.subtext = Object.keys(JSON.parse(data.NetWork))[0];
                            option10.series[0].data = networkindata;
                            option10.series[1].data = networkoutdata;
                            // option11.title.subtext = Object.keys(JSON.parse(data.NetWork))[1];
                            option11.series[0].data = networkindata1;
                            option11.series[1].data = networkoutdata1;
                            // option12.title.subtext = Object.keys(JSON.parse(data.NetWork))[2];
                            option12.series[0].data = networkindata2;
                            option12.series[1].data = networkoutdata2;
                            // option13.title.subtext = Object.keys(JSON.parse(data.NetWork))[3];
                            option13.series[0].data = networkindata3;
                            option13.series[1].data = networkoutdata3;
                            chart10.setOption(option10, true);
                            chart11.setOption(option11, true);
                            chart12.setOption(option12, true);
                            chart13.setOption(option13, true);
                            vue1.networkin = networkin;
                            vue1.networkout = networkout;
                            chart10.hideLoading();
                            chart11.hideLoading();
                            chart12.hideLoading();
                            chart13.hideLoading();
                        }
                    },
                    error: function () {
                        alert('networkerror')
                    }
                });
            }
            vue1.showAgent = false;
            vue1.showSNMP = false;
            vue1.showIPMI = false;
            vue1.showHostid = i;
            $.post("/monitor/resourceShow ", {id: i}, function (data) {
                console.log(JSON.parse(JSON.parse(data)));
                var da = JSON.parse(JSON.parse(data));
                if (da.uptime) {
                } else {
                    da.uptime = '';
                    da.uname = '';
                }
                if (da.status == '0') {
                    da.status_ = '启用'
                } else {
                    da.status_ = '未启用'
                }
                if (da.mark) {

                } else {
                    da.mark = [];
                    da.createdate = '';
                }
                console.log(da);
                vue1.showHost = da.host;
                vue1.showHostId = da.hostid;
                vue1.showIp = da.interfaces;
                vue1.showName = da.name;
                vue1.showuname = da.uname;
                vue1.showstatus_ = da.status_;
                vue1.showuptime = da.uptime;
                vue1.showcreatedate = da.createdate;
                vue1.showmark = da.mark[0];
                console.log(vue1.showmark);
                vue1.showgroups = da.groups;
                vue1.showTemplates = da.parentTemplates;
                for (var a = 0; a < da.interfaces.length; a++) {
                    if (da.interfaces[a].type == '1') {
                        vue1.showAgent = true;
                    }
                    if (da.interfaces[a].type == '2') {
                        vue1.showSNMP = true;
                    }
                    if (da.interfaces[a].type == '3') {
                        vue1.showIPMI = true;
                    }
                }
            });
            $.post("/monitor/getTriggerById", {id: i, time: '3600'}, function (da) {
                var data = JSON.parse(JSON.parse(da)).result;
                var obj = {
                    0: '正常',
                    1: "异常"
                };
                console.log(data);
                if (data.length > 0) {
                    vue1.showda = false;
                    var arr = data;
                    for (var l = 0; l < data.length; l++) {
                        if (arr[l].priority == '0') {
                            arr[l].priority_ = '未分类'
                        } else if (data[l].priority == '1') {
                            arr[l].priority_ = '信息'
                        } else if (data[l].priority == '2') {
                            arr[l].priority_ = '警告'
                        } else if (data[l].priority == '3') {
                            arr[l].priority_ = '一般'
                        } else if (data[l].priority == '4') {
                            arr[l].priority_ = '高'
                        } else if (data[l].priority == '5') {
                            arr[l].priority_ = '灾难'
                        }
                        arr[l].lastchange = timestampToTime(parseInt(data[l].lastchange));
                        arr[l].value_ = obj[arr[l].value]
                    }
                    vue1.showEvent = arr
                } else {
                    vue1.showda = true;
                    vue1.showEvent = [];
                }
            });
            $.post("/monitor/getNewItem", {hostId: i}, function (da) {
                var d = JSON.parse(JSON.parse(da)).result;
                for (var a = 0; a < d.length; a++) {
                    d[a].lastTime = timestampToTime2(d[a].lastclock)
                }
                vue1.lastData = d;
                console.log(d);
            })
        },
        netWorkkeyschange: function (d, model, t, i, s, n) {
            d.showLoading();
            function timestampToTime2(timestamp) {
                var date = new Date(timestamp * 1000);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
                Y = date.getFullYear() + '/';
                M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '/';
                D = date.getDate() + ' ';
                h = date.getHours() + ':';
                if (date.getMinutes() >= 10) {
                    m = date.getMinutes() + ':';
                } else {
                    m = '0' + date.getMinutes() + ':';
                }
                if (date.getSeconds() >= 10) {
                    s2 = date.getSeconds();
                } else {
                    s2 = '0' + date.getSeconds();
                }
                return Y + M + D + h + m + s2;
            }
            console.log(n[model]);
            console.log(s[0].key.split('[')[1].split(']')[0]);
            var key =[],arr = [];
            for (var a = 0; a < s.length; a++) {
                if(n[model] == s[a].key.split('[')[1].split(']')[0]){
                    arr.push(s[a].id);
                    if(s[a].key.split('[')[0].indexOf('ifInOctets') >= 0){
                        arr[0] = s[a].id
                    }else{
                        arr[1] = s[a].id
                    }
                }
            }
            console.log(arr);
            $.ajax({
                type: 'post',
                url: '/monitor/getDrawByItemid',
                traditional: true,
                data: {itemid: arr, hostid: i,timeStamp:(Date.parse(new Date()) / 1000 - t)},
                success:function(data){
                    console.log(data);
                    var networkin = JSON.parse(data.in).result, networkout = JSON.parse(data.out).result;
                    console.log(networkin);
                    console.log(networkout);
                    var networkindata = [], networkoutdata = [];
                    for (var y = 0; y < networkin.length; y++) {
                        networkindata[y] = {
                            name: timestampToTime2(parseFloat(networkin[y].clock)),
                            value: [timestampToTime2(parseFloat(networkin[y].clock)), networkin[y].value]
                        };
                    }
                    for (var z = 0; z < networkout.length; z++) {
                        networkoutdata[z] = {
                            name: timestampToTime2(parseFloat(networkout[z].clock)),
                            value: [timestampToTime2(parseFloat(networkout[z].clock)), networkout[z].value]
                        };
                    }
                    option10.series[0].data = networkindata;
                    option10.series[1].data = networkoutdata;
                    d.setOption(option10,true);
                    d.hideLoading();
                }
            })
        },
        edit: function (i) {
            console.log(vue1.d[i]);
            vue1.type = vue1.d[i].typ;
            vue1.name2 = vue1.d[i].name;
            vue1.hosts2 = vue1.d[i].groups;
            vue1.edittemp = vue1.d[i].parentTemplates;
            vue1.mo1 = false;
            vue1.mo2 = false;
            vue1.mo3 = false;
            vue1.ehid = vue1.d[i].hostid;
            vue1.tip = vue1.d[i].mark;
            var arr1 = [], arr2 = [], arr3 = [];
            for (var a = 0; a < vue1.d[i].interfaces.length; a++) {
                if (vue1.d[i].interfaces[a].useIp == '1') {
                    vue1.d[i].interfaces[a].ipradio = true;
                    vue1.d[i].interfaces[a].dnsradio = false;
                } else {
                    vue1.d[i].interfaces[a].ipradio = false;
                    vue1.d[i].interfaces[a].dnsradio = true;
                }
                if (vue1.d[i].interfaces[a].type == '1') {
                    arr1.push(vue1.d[i].interfaces[a]);
                    vue1.mo1 = true;
                }
                if (vue1.d[i].interfaces[a].type == '2') {
                    arr2.push(vue1.d[i].interfaces[a]);
                    vue1.mo2 = true;
                    vue1.macro = vue1.d[i].macros
                } else {
                    vue1.macro = []
                }
                if (vue1.d[i].interfaces[a].type == '3') {
                    arr3.push(vue1.d[i].interfaces[a]);
                    vue1.mo3 = true;
                    vue1.e1 = vue1.d[i].ipmi_authtype;
                    vue1.e2 = vue1.d[i].ipmi_privilege;
                    vue1.e3 = vue1.d[i].ipmi_username;
                    vue1.e4 = vue1.d[i].ipmi_password;
                }
            }
            vue1.age = arr1;
            vue1.snm = arr2;
            vue1.ipm = arr3;
            for (var b = 0; b < arr1.length; b++) {
                if (arr1[b].main == '1') {
                    vue1.mmo1 = b.toString()
                }
            }
            for (var b2 = 0; b2 < arr2.length; b2++) {
                if (arr2[b2].main == '1') {
                    vue1.mmo2 = b2.toString()
                }
            }
            for (var b3 = 0; b3 < arr3.length; b3++) {
                if (arr3[b3].main == '1') {
                    vue1.mmo3 = b3.toString()
                }
            }
        },
        ad1: function () {
            console.log(vue1.age);
            vue1.age.push(vue1.obj1)
        },
        ad2: function () {
            vue1.snm.push(vue1.obj2)
        },
        ad3: function () {
            vue1.ipm.push(vue1.obj4)
        },
        ad4: function () {
            console.log(vue1.macro);
            console.log(vue1.obj3);
            vue1.macro.push(vue1.obj3)
        },
        de1: function (i) {
            var arr = [];
            for (var a = 0; a < vue1.age.length; a++) {
                arr[a] = a
            }
            if (arr.indexOf(i) >= 0) {
                vue1.age.splice(i, 1)
            }
        },
        dele2: function (i) {
            var arr = [];
            for (var a = 0; a < vue1.snm.length; a++) {
                arr[a] = a
            }
            if (arr.indexOf(i) >= 0) {
                vue1.snm.splice(i, 1)
            }
        },
        dele3: function (i) {
            var arr = [];
            for (var a = 0; a < vue1.ipm.length; a++) {
                arr[a] = a
            }
            if (arr.indexOf(i) >= 0) {
                vue1.ipm.splice(i, 1)
            }
        },
        dele4: function (i) {
            var arr = [];
            for (var a = 0; a < vue1.macro.length; a++) {
                arr[a] = a
            }
            if (arr.indexOf(i) >= 0) {
                vue1.macro.splice(i, 1)
            }
        },
        dele5: function (i) {
            var arr = [];
            for (var a = 0; a < vue1.edittemp.length; a++) {
                arr[a] = a
            }
            if (arr.indexOf(i) >= 0) {
                vue1.edittemp.splice(i, 1)
            }
        },
        st2: function () {
            for (var a = 0; a < vue1.templateModel.length; a++) {
                vue1.edittemp.push(vue1.template[vue1.templateModel[a]])
            }
            vue1.templateModel = []
        }
    },
    computed: {
        triggersea: function () {
            var search = this.triggerSearch.toLowerCase();
            if (search) {
                return this.trigger.filter(function (product) {
                    return Object.keys(product).some(function (key) {
                        return String(product[key]).toLowerCase().indexOf(search) > -1
                    })
                })
            }
            return this.trigger;
        }
    }
});
var chart0 = echarts.init(document.getElementById('chartFull'), 'dark');
var chart1 = echarts.init(document.getElementById('chart1'), 'macarons');
var option = {
    title: {
        text: 'CPU使用情况'
    },
    tooltip: {
        trigger: 'axis',
        axisPointer: {
            type: 'cross',
            label: {
                backgroundColor: '#6a7985'
            }
        }
    },
    toolbox: {
        show: true,
        feature: {
            mark: {show: true},
            dataView: {show: true, readOnly: false},
            magicType: {
                show: true,
                type: ['pie', 'funnel']
            },
            restore: {show: true},
            saveAsImage: {show: true},
            myTool1: {
                show: true,
                title: '查看大图',
                icon: 'path://M432.45,595.444c0,2.177-4.661,6.82-11.305,6.82c-6.475,0-11.306-4.567-11.306-6.82s4.852-6.812,11.306-6.812C427.841,588.632,432.452,593.191,432.45,595.444L432.45,595.444z M421.155,589.876c-3.009,0-5.448,2.495-5.448,5.572s2.439,5.572,5.448,5.572c3.01,0,5.449-2.495,5.449-5.572C426.604,592.371,424.165,589.876,421.155,589.876L421.155,589.876z M421.146,591.891c-1.916,0-3.47,1.589-3.47,3.549c0,1.959,1.554,3.548,3.47,3.548s3.469-1.589,3.469-3.548C424.614,593.479,423.062,591.891,421.146,591.891L421.146,591.891zM421.146,591.891',
                onclick: function () {
                    $('#seeChart').modal('show');
                    chart0.showLoading();
                    chart0.setOption(option, true);
                    chart0.hideLoading();
                }
            }
        }
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    xAxis:
        {
            type: 'category',
            boundaryGap: false,
            data: ''
        }
    ,
    yAxis:
        {
            type: 'value',
            axisLabel: {
                formatter: function (val) {
                    return val + '%';
                }
            }
        }
    ,
    series: {
        name: 'CPU使用率',
        type: 'line',
        smooth: true,
        areaStyle: {normal: {}},
        data: ''
    }
};
//图二
var chart2 = echarts.init(document.getElementById('chart2'), 'infographic');
var option2 = {
    title: {
        text: '磁盘使用量'
    },
    tooltip: {
        trigger: 'axis',
        axisPointer: {            // 坐标轴指示器，坐标轴触发有效
            type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
        }
    },
    toolbox: {
        show: true,
        feature: {
            mark: {show: true},
            dataView: {show: true, readOnly: false},
            magicType: {
                show: true,
                type: ['pie', 'funnel']
            },
            restore: {show: true},
            saveAsImage: {show: true},
            myTool1: {
                show: true,
                title: '查看大图',
                icon: 'path://M432.45,595.444c0,2.177-4.661,6.82-11.305,6.82c-6.475,0-11.306-4.567-11.306-6.82s4.852-6.812,11.306-6.812C427.841,588.632,432.452,593.191,432.45,595.444L432.45,595.444z M421.155,589.876c-3.009,0-5.448,2.495-5.448,5.572s2.439,5.572,5.448,5.572c3.01,0,5.449-2.495,5.449-5.572C426.604,592.371,424.165,589.876,421.155,589.876L421.155,589.876z M421.146,591.891c-1.916,0-3.47,1.589-3.47,3.549c0,1.959,1.554,3.548,3.47,3.548s3.469-1.589,3.469-3.548C424.614,593.479,423.062,591.891,421.146,591.891L421.146,591.891zM421.146,591.891',
                onclick: function () {
                    $('#seeChart').modal('show');
                    chart0.showLoading();
                    chart0.setOption(option2, true);
                    chart0.hideLoading();
                }
            }
        }
    },
    legend: {
        data: ['已使用', '未使用']
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    xAxis: {
        type: 'value'
    },
    yAxis: {
        type: 'category',
        data: ''
    },
    series: [
        {
            name: '已使用',
            type: 'bar',
            stack: '总量',
            label: {
                normal: {
                    show: true,
                    position: 'insideTopLeft',
                }
            },
            data: ''
        },
        {
            name: '未使用',
            type: 'bar',
            stack: '总量',
            label: {
                normal: {
                    show: true,
                    position: 'insideBottomRight',
                }
            },
            data: ''
        }
    ]
};
//图三
var chart3 = echarts.init(document.getElementById('chart3'), 'infographic');
var option3 = {
    title: {
        text: '磁盘使用率'
    },
    tooltip: {
        trigger: 'axis',
        axisPointer: {            // 坐标轴指示器，坐标轴触发有效
            type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
        }
    },
    legend: {
        data: ['已使用', '未使用']
    },
    toolbox: {
        show: true,
        feature: {
            mark: {show: true},
            dataView: {show: true, readOnly: false},
            magicType: {
                show: true,
                type: ['pie', 'funnel']
            },
            restore: {show: true},
            saveAsImage: {show: true},
            myTool1: {
                show: true,
                title: '查看大图',
                icon: 'path://M432.45,595.444c0,2.177-4.661,6.82-11.305,6.82c-6.475,0-11.306-4.567-11.306-6.82s4.852-6.812,11.306-6.812C427.841,588.632,432.452,593.191,432.45,595.444L432.45,595.444z M421.155,589.876c-3.009,0-5.448,2.495-5.448,5.572s2.439,5.572,5.448,5.572c3.01,0,5.449-2.495,5.449-5.572C426.604,592.371,424.165,589.876,421.155,589.876L421.155,589.876z M421.146,591.891c-1.916,0-3.47,1.589-3.47,3.549c0,1.959,1.554,3.548,3.47,3.548s3.469-1.589,3.469-3.548C424.614,593.479,423.062,591.891,421.146,591.891L421.146,591.891zM421.146,591.891',
                onclick: function () {
                    $('#seeChart').modal('show');
                    chart0.showLoading();
                    chart0.setOption(option3, true);
                    chart0.hideLoading();
                }
            }
        }
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    xAxis: {
        type: 'value'
    },
    yAxis: {
        type: 'category',
        data: ''
    },
    series: [
        {
            name: '已使用',
            type: 'bar',
            stack: '总量',
            label: {
                normal: {
                    show: true,
                    position: 'insideTopLeft',
                }
            },
            data: ''
        },
        {
            name: '未使用',
            type: 'bar',
            stack: '总量',
            label: {
                normal: {
                    show: true,
                    position: 'insideBottomRight',
                }
            },
            data: ''
        }
    ]
};
//图四
var chart4 = echarts.init(document.getElementById('chart4'), 'infographic');
var option4 = {
    title: {
        text: 'IO读写速率'
    },
    tooltip: {
        trigger: 'axis',
        axisPointer: {
            type: 'cross',
            label: {
                backgroundColor: '#6a7985'
            }
        }
    },
    legend: {
        data: ['读取速率', '写入速率']
    },
    toolbox: {
        show: true,
        feature: {
            mark: {show: true},
            dataView: {show: true, readOnly: false},
            magicType: {
                show: true,
                type: ['pie', 'funnel']
            },
            restore: {show: true},
            saveAsImage: {show: true},
            myTool1: {
                show: true,
                title: '查看大图',
                icon: 'path://M432.45,595.444c0,2.177-4.661,6.82-11.305,6.82c-6.475,0-11.306-4.567-11.306-6.82s4.852-6.812,11.306-6.812C427.841,588.632,432.452,593.191,432.45,595.444L432.45,595.444z M421.155,589.876c-3.009,0-5.448,2.495-5.448,5.572s2.439,5.572,5.448,5.572c3.01,0,5.449-2.495,5.449-5.572C426.604,592.371,424.165,589.876,421.155,589.876L421.155,589.876z M421.146,591.891c-1.916,0-3.47,1.589-3.47,3.549c0,1.959,1.554,3.548,3.47,3.548s3.469-1.589,3.469-3.548C424.614,593.479,423.062,591.891,421.146,591.891L421.146,591.891zM421.146,591.891',
                onclick: function () {
                    $('#seeChart').modal('show');
                    chart0.showLoading();
                    chart0.setOption(option4, true);
                    chart0.hideLoading();
                }
            }
        }
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    xAxis:
        {
            type: 'category',
            boundaryGap: false,
            data: []
        }
    ,
    yAxis:
        {
            type: 'value',
            axisLabel: {
                formatter: function (val) {
                    return val + ' KB/ms';
                }
            }
        }
    ,
    series: [{
        name: '读取速率',
        type: 'line',
        smooth: true,
        data: []
    }, {
        name: '写入速率',
        type: 'line',
        smooth: true,
        data: []
    }]
};
//图五
var chart5 = echarts.init(document.getElementById('chart5'), 'shine');
var option5 = {
    title: {
        text: '网络流量'
    },
    tooltip: {
        trigger: 'axis',
        axisPointer: {
            type: 'cross',
            label: {
                backgroundColor: '#6a7985'
            }
        }
    },
    legend: {
        data: ['流入', '流出']
    },
    toolbox: {
        show: true,
        feature: {
            mark: {show: true},
            dataView: {show: true, readOnly: false},
            magicType: {
                show: true,
                type: ['pie', 'funnel']
            },
            restore: {show: true},
            saveAsImage: {show: true},
            myTool1: {
                show: true,
                title: '查看大图',
                icon: 'path://M432.45,595.444c0,2.177-4.661,6.82-11.305,6.82c-6.475,0-11.306-4.567-11.306-6.82s4.852-6.812,11.306-6.812C427.841,588.632,432.452,593.191,432.45,595.444L432.45,595.444z M421.155,589.876c-3.009,0-5.448,2.495-5.448,5.572s2.439,5.572,5.448,5.572c3.01,0,5.449-2.495,5.449-5.572C426.604,592.371,424.165,589.876,421.155,589.876L421.155,589.876z M421.146,591.891c-1.916,0-3.47,1.589-3.47,3.549c0,1.959,1.554,3.548,3.47,3.548s3.469-1.589,3.469-3.548C424.614,593.479,423.062,591.891,421.146,591.891L421.146,591.891zM421.146,591.891',
                onclick: function () {
                    $('#seeChart').modal('show');
                    chart0.showLoading();
                    chart0.setOption(option5, true);
                    chart0.hideLoading();
                }
            }
        }
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    xAxis:
        {
            type: 'category',
            boundaryGap: false,
            data: ''
        }
    ,
    yAxis:
        {
            type: 'value',
            axisLabel: {
                formatter: function (val) {
                    return val + ' KB/s';
                }
            }
        }
    ,
    series: [{
        name: '流入',
        type: 'line',
        smooth: true,
        data: ''
    }, {
        name: '流出',
        type: 'line',
        smooth: true,
        data: ''
    }]
};
//图六
var chart6 = echarts.init(document.getElementById('chart6'), 'infographic');
var option6 = {
    title: {
        text: 'IO读写次数'
    },
    tooltip: {
        trigger: 'axis',
        axisPointer: {
            type: 'cross',
            label: {
                backgroundColor: '#6a7985'
            }
        }
    },
    legend: {
        data: ['读取次数', '写入次数']
    },
    toolbox: {
        show: true,
        feature: {
            mark: {show: true},
            dataView: {show: true, readOnly: false},
            magicType: {
                show: true,
                type: ['pie', 'funnel']
            },
            restore: {show: true},
            saveAsImage: {show: true},
            myTool1: {
                show: true,
                title: '查看大图',
                icon: 'path://M432.45,595.444c0,2.177-4.661,6.82-11.305,6.82c-6.475,0-11.306-4.567-11.306-6.82s4.852-6.812,11.306-6.812C427.841,588.632,432.452,593.191,432.45,595.444L432.45,595.444z M421.155,589.876c-3.009,0-5.448,2.495-5.448,5.572s2.439,5.572,5.448,5.572c3.01,0,5.449-2.495,5.449-5.572C426.604,592.371,424.165,589.876,421.155,589.876L421.155,589.876z M421.146,591.891c-1.916,0-3.47,1.589-3.47,3.549c0,1.959,1.554,3.548,3.47,3.548s3.469-1.589,3.469-3.548C424.614,593.479,423.062,591.891,421.146,591.891L421.146,591.891zM421.146,591.891',
                onclick: function () {
                    $('#seeChart').modal('show');
                    chart0.showLoading();
                    chart0.setOption(option6, true);
                    chart0.hideLoading();
                }
            }
        }
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    xAxis:
        {
            type: 'category',
            boundaryGap: false,
            data: []
        }
    ,
    yAxis:
        {
            type: 'value',
            axisLabel: {
                formatter: function (val) {
                    return val + ' req/ms';
                }
            }
        }
    ,
    series: [{
        name: '读取次数',
        type: 'line',
        smooth: true,
        data: []
    }, {
        name: '写入次数',
        type: 'line',
        smooth: true,
        data: []
    }]
};
//图七
var chart7 = echarts.init(document.getElementById('chart7'), 'macarons');
var option7 = {
    title: {
        text: '内存使用情况'
    },
    tooltip: {
        trigger: 'axis',
        axisPointer: {
            type: 'cross',
            label: {
                backgroundColor: '#6a7985'
            }
        }
    },
    legend: {
        data: ['已使用'/*, '未使用'*/]
    },
    toolbox: {
        show: true,
        feature: {
            mark: {show: true},
            dataView: {show: true, readOnly: false},
            magicType: {
                show: true,
                type: ['pie', 'funnel']
            },
            restore: {show: true},
            saveAsImage: {show: true},
            myTool1: {
                show: true,
                title: '查看大图',
                icon: 'path://M432.45,595.444c0,2.177-4.661,6.82-11.305,6.82c-6.475,0-11.306-4.567-11.306-6.82s4.852-6.812,11.306-6.812C427.841,588.632,432.452,593.191,432.45,595.444L432.45,595.444z M421.155,589.876c-3.009,0-5.448,2.495-5.448,5.572s2.439,5.572,5.448,5.572c3.01,0,5.449-2.495,5.449-5.572C426.604,592.371,424.165,589.876,421.155,589.876L421.155,589.876z M421.146,591.891c-1.916,0-3.47,1.589-3.47,3.549c0,1.959,1.554,3.548,3.47,3.548s3.469-1.589,3.469-3.548C424.614,593.479,423.062,591.891,421.146,591.891L421.146,591.891zM421.146,591.891',
                onclick: function () {
                    $('#seeChart').modal('show');
                    chart0.showLoading();
                    chart0.setOption(option7, true);
                    chart0.hideLoading();
                }
            }
        }
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    xAxis: {
        type: 'category',
        boundaryGap: false,
        data: ''
    },
    yAxis: {
        type: 'value',
        axisLabel: {
            formatter: function (val) {
                return val + 'G';
            }
        }
    },
    series: [{
        name: '已使用',
        type: 'line',
        smooth: true,
        areaStyle: {normal: {}},
        data: ''
    }/*, {
     name: '未使用',
     type: 'line',
     smooth: true,
     areaStyle: {normal: {}},
     data: ''
     }*/]
};
chart7.setOption(option7, true);
var chart8 = echarts.init(document.getElementById('chart8'), 'macarons');
var option8 = {
    title: {
        text: 'CPU使用情况'
    },
    tooltip: {
        trigger: 'axis',
        axisPointer: {
            type: 'cross',
            label: {
                backgroundColor: '#6a7985'
            }
        }
    },
    toolbox: {
        show: true,
        feature: {
            mark: {show: true},
            dataView: {show: true, readOnly: false},
            magicType: {
                show: true,
                type: ['pie', 'funnel']
            },
            restore: {show: true},
            saveAsImage: {show: true},
            myTool1: {
                show: true,
                title: '查看大图',
                icon: 'path://M432.45,595.444c0,2.177-4.661,6.82-11.305,6.82c-6.475,0-11.306-4.567-11.306-6.82s4.852-6.812,11.306-6.812C427.841,588.632,432.452,593.191,432.45,595.444L432.45,595.444z M421.155,589.876c-3.009,0-5.448,2.495-5.448,5.572s2.439,5.572,5.448,5.572c3.01,0,5.449-2.495,5.449-5.572C426.604,592.371,424.165,589.876,421.155,589.876L421.155,589.876z M421.146,591.891c-1.916,0-3.47,1.589-3.47,3.549c0,1.959,1.554,3.548,3.47,3.548s3.469-1.589,3.469-3.548C424.614,593.479,423.062,591.891,421.146,591.891L421.146,591.891zM421.146,591.891',
                onclick: function () {
                    $('#seeChart').modal('show');
                    chart0.showLoading();
                    chart0.setOption(option8, true);
                    chart0.hideLoading();
                }
            }
        }
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    xAxis:
        {
            type: 'category',
            boundaryGap: false,
            data: ''
        }
    ,
    yAxis:
        {
            type: 'value',
            axisLabel: {
                formatter: function (val) {
                    return val + '%';
                }
            }
        }
    ,
    series: {
        name: 'CPU使用率',
        type: 'line',
        smooth: true,
        areaStyle: {normal: {}},
        data: ''
    }
};
var chart9 = echarts.init(document.getElementById('chart9'), 'infographic');
var option9 = {
    title: {
        text: '内存使用情况'
    },
    tooltip: {
        trigger: 'axis',
        axisPointer: {
            type: 'cross',
            label: {
                backgroundColor: '#6a7985'
            }
        }
    },
    legend: {
        data: ['已使用', '未使用']
    },
    toolbox: {
        show: true,
        feature: {
            mark: {show: true},
            dataView: {show: true, readOnly: false},
            magicType: {
                show: true,
                type: ['pie', 'funnel']
            },
            restore: {show: true},
            saveAsImage: {show: true},
            myTool1: {
                show: true,
                title: '查看大图',
                icon: 'path://M432.45,595.444c0,2.177-4.661,6.82-11.305,6.82c-6.475,0-11.306-4.567-11.306-6.82s4.852-6.812,11.306-6.812C427.841,588.632,432.452,593.191,432.45,595.444L432.45,595.444z M421.155,589.876c-3.009,0-5.448,2.495-5.448,5.572s2.439,5.572,5.448,5.572c3.01,0,5.449-2.495,5.449-5.572C426.604,592.371,424.165,589.876,421.155,589.876L421.155,589.876z M421.146,591.891c-1.916,0-3.47,1.589-3.47,3.549c0,1.959,1.554,3.548,3.47,3.548s3.469-1.589,3.469-3.548C424.614,593.479,423.062,591.891,421.146,591.891L421.146,591.891zM421.146,591.891',
                onclick: function () {
                    $('#seeChart').modal('show');
                    chart0.showLoading();
                    chart0.setOption(option9, true);
                    chart0.hideLoading();
                }
            }
        }
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    xAxis: {
        type: 'time',
        splitLine: {
            show: false
        }
    },
    yAxis: {
        type: 'value',
        /* axisLabel: {
         formatter: function (val) {
         return val + 'GB';
         }
         }*/
    },
    series: [{
        name: '已使用',
        type: 'line',
        smooth: true,
        stack: '总量',
        areaStyle: {normal: {}},
        data: ''
    }, {
        name: '未使用',
        type: 'line',
        smooth: true,
        stack: '总量',
        areaStyle: {normal: {}},
        data: ''
    }]
};
var chart10 = echarts.init(document.getElementById('chart10'), 'macarons');
var option10 = {
    title: {
        text: '网络流量'
    },
    tooltip: {
        trigger: 'axis',
        axisPointer: {
            type: 'cross',
            label: {
                backgroundColor: '#6a7985'
            }
        }
    },
    legend: {
        data: ['流入', '流出']
    },
    toolbox: {
        show: true,
        feature: {
            mark: {show: true},
            dataView: {show: true, readOnly: false},
            magicType: {
                show: true,
                type: ['pie', 'funnel']
            },
            restore: {show: true},
            saveAsImage: {show: true},
            myTool1: {
                show: true,
                title: '查看大图',
                icon: 'path://M432.45,595.444c0,2.177-4.661,6.82-11.305,6.82c-6.475,0-11.306-4.567-11.306-6.82s4.852-6.812,11.306-6.812C427.841,588.632,432.452,593.191,432.45,595.444L432.45,595.444z M421.155,589.876c-3.009,0-5.448,2.495-5.448,5.572s2.439,5.572,5.448,5.572c3.01,0,5.449-2.495,5.449-5.572C426.604,592.371,424.165,589.876,421.155,589.876L421.155,589.876z M421.146,591.891c-1.916,0-3.47,1.589-3.47,3.549c0,1.959,1.554,3.548,3.47,3.548s3.469-1.589,3.469-3.548C424.614,593.479,423.062,591.891,421.146,591.891L421.146,591.891zM421.146,591.891',
                onclick: function () {
                    $('#seeChart').modal('show');
                    chart0.showLoading();
                    chart0.setOption(option10, true);
                    chart0.hideLoading();
                }
            }
        }
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    xAxis: {
        type: 'time',
        splitLine: {
            show: false
        }
    },
    yAxis: {
        type: 'value',
        axisLabel: {
            formatter: function (val) {
                return val + 'kb/s';
            }
        }
    },
    series: [{
        name: '流入',
        type: 'line',
        smooth: true,
        areaStyle: {normal: {}},
        data: ''
    }, {
        name: '流出',
        type: 'line',
        smooth: true,
        areaStyle: {normal: {}},
        data: ''
    }]
};
var option11 = {
    title: {
        text: '网络流量'
    },
    tooltip: {
        trigger: 'axis',
        axisPointer: {
            type: 'cross',
            label: {
                backgroundColor: '#6a7985'
            }
        }
    },
    legend: {
        data: ['流入', '流出']
    },
    toolbox: {
        show: true,
        feature: {
            mark: {show: true},
            dataView: {show: true, readOnly: false},
            magicType: {
                show: true,
                type: ['pie', 'funnel']
            },
            restore: {show: true},
            saveAsImage: {show: true},
            myTool1: {
                show: true,
                title: '查看大图',
                icon: 'path://M432.45,595.444c0,2.177-4.661,6.82-11.305,6.82c-6.475,0-11.306-4.567-11.306-6.82s4.852-6.812,11.306-6.812C427.841,588.632,432.452,593.191,432.45,595.444L432.45,595.444z M421.155,589.876c-3.009,0-5.448,2.495-5.448,5.572s2.439,5.572,5.448,5.572c3.01,0,5.449-2.495,5.449-5.572C426.604,592.371,424.165,589.876,421.155,589.876L421.155,589.876z M421.146,591.891c-1.916,0-3.47,1.589-3.47,3.549c0,1.959,1.554,3.548,3.47,3.548s3.469-1.589,3.469-3.548C424.614,593.479,423.062,591.891,421.146,591.891L421.146,591.891zM421.146,591.891',
                onclick: function () {
                    $('#seeChart').modal('show');
                    chart0.showLoading();
                    chart0.setOption(option11, true);
                    chart0.hideLoading();
                }
            }
        }
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    xAxis: {
        type: 'time',
        splitLine: {
            show: false
        }
    },
    yAxis: {
        type: 'value',
        axisLabel: {
            formatter: function (val) {
                return val + 'kb/s';
            }
        }
    },
    series: [{
        name: '流入',
        type: 'line',
        smooth: true,
        areaStyle: {normal: {}},
        data: ''
    }, {
        name: '流出',
        type: 'line',
        smooth: true,
        areaStyle: {normal: {}},
        data: ''
    }]
};
var option12 = {
    title: {
        text: '网络流量'
    },
    tooltip: {
        trigger: 'axis',
        axisPointer: {
            type: 'cross',
            label: {
                backgroundColor: '#6a7985'
            }
        }
    },
    legend: {
        data: ['流入', '流出']
    },
    toolbox: {
        show: true,
        feature: {
            mark: {show: true},
            dataView: {show: true, readOnly: false},
            magicType: {
                show: true,
                type: ['pie', 'funnel']
            },
            restore: {show: true},
            saveAsImage: {show: true},
            myTool1: {
                show: true,
                title: '查看大图',
                icon: 'path://M432.45,595.444c0,2.177-4.661,6.82-11.305,6.82c-6.475,0-11.306-4.567-11.306-6.82s4.852-6.812,11.306-6.812C427.841,588.632,432.452,593.191,432.45,595.444L432.45,595.444z M421.155,589.876c-3.009,0-5.448,2.495-5.448,5.572s2.439,5.572,5.448,5.572c3.01,0,5.449-2.495,5.449-5.572C426.604,592.371,424.165,589.876,421.155,589.876L421.155,589.876z M421.146,591.891c-1.916,0-3.47,1.589-3.47,3.549c0,1.959,1.554,3.548,3.47,3.548s3.469-1.589,3.469-3.548C424.614,593.479,423.062,591.891,421.146,591.891L421.146,591.891zM421.146,591.891',
                onclick: function () {
                    $('#seeChart').modal('show');
                    chart0.showLoading();
                    chart0.setOption(option12, true);
                    chart0.hideLoading();
                }
            }
        }
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    xAxis: {
        type: 'time',
        splitLine: {
            show: false
        }
    },
    yAxis: {
        type: 'value',
        axisLabel: {
            formatter: function (val) {
                return val + 'kb/s';
            }
        }
    },
    series: [{
        name: '流入',
        type: 'line',
        smooth: true,
        areaStyle: {normal: {}},
        data: ''
    }, {
        name: '流出',
        type: 'line',
        smooth: true,
        areaStyle: {normal: {}},
        data: ''
    }]
};
var option13 = {
    title: {
        text: '网络流量'
    },
    tooltip: {
        trigger: 'axis',
        axisPointer: {
            type: 'cross',
            label: {
                backgroundColor: '#6a7985'
            }
        }
    },
    legend: {
        data: ['流入', '流出']
    },
    toolbox: {
        show: true,
        feature: {
            mark: {show: true},
            dataView: {show: true, readOnly: false},
            magicType: {
                show: true,
                type: ['pie', 'funnel']
            },
            restore: {show: true},
            saveAsImage: {show: true},
            myTool1: {
                show: true,
                title: '查看大图',
                icon: 'path://M432.45,595.444c0,2.177-4.661,6.82-11.305,6.82c-6.475,0-11.306-4.567-11.306-6.82s4.852-6.812,11.306-6.812C427.841,588.632,432.452,593.191,432.45,595.444L432.45,595.444z M421.155,589.876c-3.009,0-5.448,2.495-5.448,5.572s2.439,5.572,5.448,5.572c3.01,0,5.449-2.495,5.449-5.572C426.604,592.371,424.165,589.876,421.155,589.876L421.155,589.876z M421.146,591.891c-1.916,0-3.47,1.589-3.47,3.549c0,1.959,1.554,3.548,3.47,3.548s3.469-1.589,3.469-3.548C424.614,593.479,423.062,591.891,421.146,591.891L421.146,591.891zM421.146,591.891',
                onclick: function () {
                    $('#seeChart').modal('show');
                    chart0.showLoading();
                    chart0.setOption(option13, true);
                    chart0.hideLoading();
                }
            }
        }
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    xAxis: {
        type: 'time',
        splitLine: {
            show: false
        }
    },
    yAxis: {
        type: 'value',
        axisLabel: {
            formatter: function (val) {
                return val + 'kb/s';
            }
        }
    },
    series: [{
        name: '流入',
        type: 'line',
        smooth: true,
        areaStyle: {normal: {}},
        data: ''
    }, {
        name: '流出',
        type: 'line',
        smooth: true,
        areaStyle: {normal: {}},
        data: ''
    }]
};
var chart11 = echarts.init(document.getElementById('chart11'), 'shine');
var chart12 = echarts.init(document.getElementById('chart12'), 'shine');
var chart13 = echarts.init(document.getElementById('chart13'), 'shine');
layui.use('element', function () {
    var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块

    //监听导航点击
    element.on('nav(demo)', function (elem) {
        //console.log(elem)
        layer.msg(elem.text());
    });
});
function cleanCookie() {
    $.cookie('hostName',null);
    $.cookie('type',null);
    window.location.href="Infrastructure.html";
}
function cleanCookies() {
    $.cookie('hostName',null);
    $.cookie('type',null);
}