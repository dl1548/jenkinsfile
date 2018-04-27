var vue = new Vue({
    el: '#fault',
    data: {
        hostGroup: '',
        host: '',
        hostGroupModel: '',
        hostModel: '',
        falutSearc: '',
        fautChecked: '',
        falutTime: '',
        minFalut: '',
        falutSearch: '',
        falut: '',
        lv: {
            0: '未分类',
            1: '信息',
            2: '警告',
            3: '一般',
            4: '高',
            5: '灾难'
        },
        lvcss:{
            0: 'errorcolor1',
            1: 'errorcolor2',
            2: 'errorcolor3',
            3: 'errorcolor4',
            4: 'errorcolor5',
            5: 'errorcolor61'
        },
        status: {
            0: '启用',
            1: '未启用'
        },
        value_: {
            0: '正常',
            1: '故障'
        },
        stacss:{
            0: 'errorcolor1',
            1: 'errorcolor61'
        },
        type: {
            0: '所有',
            1: 'Email',
            2: 'Jabber',
            3: 'sendmail',
            4: 'SMS',
            5: 'weixin'
        },
        confirm: {
            0: '未确认',
            1: '确认'
        },
        user: '',
        maintain: {
            0: '未维护',
            1: '已维护'
        },
        c: '',
        m: '',
        u: '',
        t: '',
        hisFal: '1'
    },
    beforeCreate: function () {
        var _this = this;
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
        $('#load').mLoading("show");
        function diff(time) {
            var date1 = time;
            var date2 = new Date();
            var date3 = date2.getTime() - new Date(date1).getTime();
            var days = Math.floor(date3 / (24 * 3600 * 1000));
            var leave1 = date3 % (24 * 3600 * 1000);
            var hours = Math.floor(leave1 / (3600 * 1000));
            var leave2 = leave1 % (3600 * 1000);
            var minutes = Math.floor(leave2 / (60 * 1000));
            return days + "d " + hours + "h " + minutes + "m";
        }
        $.post("/monitor/getHostGroup", function (data) {
            var da = JSON.parse(JSON.parse(data)).result;
            _this.hostGroup = da;
            console.log(da);
            $('#load').mLoading("hide");
        });
        $.post("/monitor/host", function (data) {
            _this.host = data;
            console.log(data);
        });
        $.post("/monitor/getUsers", function (data) {
            _this.user = data;
            console.log(data);
        });
        $.post("/monitor/getTrigger",
            function (data) {
            console.log(JSON.parse(JSON.parse(data)));
            var d = JSON.parse(JSON.parse(data)).trigger.result;
            for (var a = 0; a < d.length; a++) {
                d[a].status_ = vue.status[d[a].status];
                d[a].val = vue.value_[d[a].value];
                d[a].lasttime = timestampToTime(parseInt(d[a].lastchange));
                d[a].time = diff(d[a].lasttime);
                d[a].lv = vue.lv[d[a].priority];
                d[a].groupsname = [];
                d[a].lvcss = vue.lvcss[d[a].priority];
                d[a].stacss = vue.stacss[d[a].value];
                for (var b = 0; b < d[a].groups.length; b++) {
                    d[a].groupsname.push(d[a].groups[b].name)
                }
            }
            console.log(d);
            _this.falut = d
        });
    },
    methods: {
        jump:function(n){
            $.cookie('hostName',n,{expires: 365});
            $.cookie('type',null);
            window.location.href="Infrastructure.html";
        },
        show: function (t, i, e) {
            var arr2 = [];
            arr2.push(i);
            $.ajax({
                type: 'post',
                url: '/monitor/getEventByTrigger',
                traditional: true,
                data: {
                    hostId: arr2,
                    triggerId: t,
                    eventId: e
                },
                dataType: 'json',
                success: function (data) {
                    var obj = {
                        0: '所有',
                        1: 'Email',
                        2: 'Jabber',
                        3: 'SMS',
                        4: 'sendmail',
                        5: 'weixin'
                    };
                    console.log(JSON.parse(data).result);
                    var d = JSON.parse(data).result;
                    if(d.length > 0){
                        for (var a = 0; a < d[0].alerts.length; a++) {
                            d[0].alerts[a].typ = obj[d[0].alerts[a].mediatypeid];
                            for (var b = 0; b < vue.user.length; b++) {
                                if(d[0].alerts[a].userid == vue.user[b].userid){
                                    d[0].alerts[a].name = vue.user[b].alias;
                                }
                            }
                        }
                        vue.u = d[0].alerts;
                        console.log(vue.u)
                    }else{
                        vue.u = []
                    }
                    /* var d2 = JSON.parse(data).result, arr = [], userobj = {};
                     for (var i = 0; i < vue.user.length; i++) {
                         userobj[vue.user[i].userid] = vue.user[i].alias
                     }
                     for (var b = 0; b < d2.length; b++) {
                         arr[b] = {
                             id: d2[b].objectid,
                             host: d2[b].hosts[0].name,
                             confirm: vue.confirm[parseInt(d2[b].acknowledged)],
                             maintain: vue.maintain[parseInt(d2[b].hosts[0].maintenance_type)]
                         };
                         if (d2[b].alerts.length > 0) {
                             arr[b].way = vue.type[parseInt(d2[b].alerts[0].mediatypeid)];
                             arr[b].userid = [];
                             arr[b].username = [];
                             for (var c = 0; c < d2[b].alerts.length; c++) {
                                 if ((arr[b].userid.indexOf(d2[b].alerts[c].userid) == -1)) {
                                     arr[b].userid.push(d2[b].alerts[c].userid);
                                     if (userobj[d2[b].alerts[c].userid]) {
                                         arr[b].username.push(userobj[d2[b].alerts[c].userid]);
                                     }
                                 }
                             }
                         } else {
                             arr[b].way = '';
                             arr[b].userid = [];
                             arr[b].username = []
                         }
                     }
                     console.log(arr);
                     vue.c = arr[0].confirm;
                     vue.m = arr[0].maintain;
                     vue.u = arr[0].username;
                     vue.t = arr[0].way;*/
                }
            });
        },
        hostGroupChange: function (i) {
            if (typeof i == "number") {
                vue.falutSearch = vue.hostGroup[i].name
            }
        },
        hostChange: function (i) {
            if (typeof i == "number") {
                vue.falutSearch = vue.host[i].name
            }
        },
        falutSubmit: function (g, h, l, d, f) {
            console.log(f);

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

            function diff(time) {
                var date1 = time;
                var date2 = new Date();
                var date3 = date2.getTime() - new Date(date1).getTime();
                var days = Math.floor(date3 / (24 * 3600 * 1000));
                var leave1 = date3 % (24 * 3600 * 1000);
                var hours = Math.floor(leave1 / (3600 * 1000));
                var leave2 = leave1 % (3600 * 1000);
                var minutes = Math.floor(leave2 / (60 * 1000));
                return days + "d " + hours + "h " + minutes + "m";
            }

            var groupid = '', hostid = '';
            if (typeof g == "number") {
                groupid = vue.hostGroup[g].groupid
            }
            if (typeof h == "number") {
                hostid = vue.host[h].hostid
            }
            console.log(groupid);
            console.log(hostid);
            console.log(l);
            console.log(d);
            if (groupid || hostid || l || d || f) {
                console.log(f);
                $.post("/monitor/selectTriggerBySelect", {
                    hostgroup: groupid,
                    time: d,
                    host: hostid,
                    priority: l,
                    everyOne: f
                }, function (data) {
                    console.log(JSON.parse(JSON.parse(data)));
                    var d = JSON.parse(JSON.parse(data)).result;
                    console.log(d);
                    for (var a = 0; a < d.length; a++) {
                        d[a].status_ = vue.status[d[a].status];
                        d[a].val = vue.value_[d[a].value];
                        d[a].lasttime = timestampToTime(parseInt(d[a].lastchange));
                        d[a].time = diff(d[a].lasttime);
                        d[a].lv = vue.lv[d[a].priority];
                        d[a].groupsname = [];
                        d[a].lvcss = vue.lvcss[d[a].priority];
                        d[a].stacss = vue.stacss[d[a].value];
                        for (var b = 0; b < d[a].groups.length; b++) {
                            d[a].groupsname.push(d[a].groups[b].name)
                        }
                    }
                    console.log(d);
                    vue.falut = d
                });
            } else {
                //alert('请选择查询条件');
                layui.use(['layer', 'form'], function(){
                    var layer = layui.layer
                        ,form = layui.form;
                    layer.alert('请选择查询条件', {icon: 7});
                });

            }
        }
    },
    computed: {
        sea: function () {
            var search = this.falutSearch.toLowerCase();
            if (search) {
                return this.falut.filter(function (product) {
                    return Object.keys(product).some(function (key) {
                        return String(product[key]).toLowerCase().indexOf(search) > -1
                    })
                })
            }
            return this.falut;
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
    $.cookie('hostName',null);
    $.cookie('type',null);
    window.location.href="Infrastructure.html";
}