var vue_1 = new Vue({
    el: '#vue_1',
    data: {
        d: '',
        search: '',
        lvcss:{
            0: 'colorLv0Index',
            1: 'colorLv1Index',
            2: 'colorLv2Index',
            3: 'colorLv3Index',
            4: 'colorLv4Index',
            5: 'colorLv5Index'
        }
    },
    methods:{
        jump:function(n){
            $.cookie('hostName',n,{expires: 365});
            window.location.href="Infrastructure.html";
            $.cookie('type',null);
        }
    },

    beforeCreate: function () {
        var _this = this;
        var lv = {
            0: '未分类',
            1: '信息',
            2: '警告',
            3: '一般',
            4: '高',
            5: '灾难'
        };

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

        $.post("/monitor/getTriggerByGroup", function (data) {
            var d = JSON.parse(JSON.parse(data)).result;
            for (var a = 0; a < d.length; a++) {
                d[a].lastChangeTime = timestampToTime2(d[a].lastchange);
                d[a].difference = diff(d[a].lastChangeTime);
                d[a].hostGroupString = JSON.stringify(d[a].groups);
                d[a].errlv = lv[d[a].priority];
                d[a].lvcss = vue_1.lvcss[d[a].priority];
            }
            console.log(d);
            _this.d = d
        });
        setInterval(function () {
            $.post("/monitor/getTriggerByGroup", function (data) {
                var d = JSON.parse(JSON.parse(data)).result;
                for (var a = 0; a < d.length; a++) {
                    d[a].lastChangeTime = timestampToTime2(d[a].lastchange);
                    d[a].difference = diff(d[a].lastChangeTime);
                    d[a].hostGroupString = JSON.stringify(d[a].groups);
                    d[a].errlv = lv[d[a].priority];
                    d[a].lvcss = vue_1.lvcss[d[a].priority];
                }
                console.log(d);
                _this.d = d
            });
        }, 30000);
    },
    computed: {
        searchData: function () {
            var search = this.search.toLowerCase();
            if (search) {
                return this.d.filter(function (product) {
                    return Object.keys(product).some(function (key) {
                        return String(product[key]).toLowerCase().indexOf(search) > -1
                    })
                })
            }
            return this.d;
        }
    }
});
var vue_7 = new Vue({
    el: '#vue_7',
    data: {
        host: '',
        name: '',
        item: '',
        top5: [{val: 0}, {val: 0}, {val: 0}, {val: 0}, {val: 0}],
        applicationName: '',
        hostid: '',
        ar: '',
        sort: '0',
        fresh:'锁定刷新',
        num:'1',
        nums:'0',
        hostGroupModel:'',
        hoursModel:'now',
        whenLock:'0'
    },
    methods: {
        hostGroup: function () {
            console.log(vue_7.hostGroupModel);
            $.post('/monitor/topFive', {hostGroup: vue_7.hostGroupModel}, function (data2) {
                console.log(data2);
                var key = Object.keys(data2);
                var val = Object.values(data2);
                var arr = [];
                for (var a = 0; a < key.length; a++) {
                    arr[a] = {d: val[a], v: key[a]}
                }
                vue_7.name = arr;
                if (arr.length == 0) {
                    //alert('无可比监控项')
                    layui.use(['layer', 'form'], function () {
                        var layer = layui.layer
                            , form = layui.form;
                        layer.alert('无可比监控项', {icon: 7});
                    });
                }
            })
        },
        changeTime:function(){
            if(vue_7.hoursModel!='now'&&vue_7.num==1){
                vue_7.nums='1'
            }else{
                vue_7.nums='0'
            }
        },
        sub: function () {
            $('#load').mLoading("show");
            if ($('#v-1').val() && $('#v-2').val() && $('#hours').val() && $('#sel').val()) {
                if(vue_7.hoursModel=='now'){
                    sort='3'
                }
                $.post('/monitor/getValue',
                    {
                        hostGroup: vue_7.hostGroupModel,
                        key: vue_7.applicationName,
                        time: vue_7.hoursModel,
                        sort: vue_7.sort
                    },
                    function (data) {
                        console.log(data);
                        console.log(JSON.parse(JSON.parse(data)).result);
                        var da = JSON.parse(JSON.parse(data)).result;
                        var d =[];
                        for(var k = 0;k<da.length;k++){
                            if(da[k].hostid){
                                d.push(da[k])
                            }
                        }
                        console.log(d);
                        for (var a = 0; a < d.length; a++) {
                            for (var b = 0; b < vue_7.hostid.length; b++) {
                                if (vue_7.hostid[b].hostid == d[a].hostid) {
                                    d[a].name = vue_7.hostid[b].host
                                }
                            }
                        }
                        if ($('#sel').val() == 0) {
                            function desc(a, b) {
                                return b.lastvalue - a.lastvalue
                            }

                            d.sort(desc);
                        } else {
                            function desc(a, b) {
                                return a.lastvalue - b.lastvalue
                            }
                            d.sort(desc);
                        }
                        if (d.length > 5) {
                            var ar = [];
                            for (var v = 0; v < 5; v++) {
                                ar.push(d[v]);
                            }
                            vue_7.top5 = ar;
                            console.log(ar);
                            $('#load').mLoading("hide");
                        } else {
                            vue_7.top5 = d;
                            console.log(d);
                            $('#load').mLoading("hide");
                        }
                        $('#load').mLoading("hide");
                    })
            } else {
                $('#load').mLoading("hide");
                layui.use(['layer', 'form'], function () {
                    var layer = layui.layer
                        , form = layui.form;
                    layer.alert('提交失败', {icon: 5});
                });
            }

        },
        lock:function(){
            vue_7.num = !vue_7.num;
            if(vue_7.num==0){
                vue_7.fresh='解开锁定';

                timer=setInterval(function () {
                    $.post('/monitor/getValue',
                        {
                            hostGroup: vue_7.hostGroupModel,
                            key: vue_7.applicationName,
                            time: vue_7.hoursModel,
                            sort: vue_7.sort
                        },
                        function (data) {
                            console.log(data);
                            console.log(JSON.parse(JSON.parse(data)).result);
                            var da = JSON.parse(JSON.parse(data)).result;
                            var d =[];
                            for(var k = 0;k<da.length;k++){
                                if(da[k].hostid){
                                    d.push(da[k])
                                }
                            }
                            for (var a = 0; a < d.length; a++) {
                                for (var b = 0; b < vue_7.hostid.length; b++) {
                                    if (vue_7.hostid[b].hostid == d[a].hostid) {
                                        d[a].name = vue_7.hostid[b].host
                                    }
                                }
                            }
                            if ($('#sel').val() == 0) {
                                function desc(a, b) {
                                    return b.lastvalue - a.lastvalue
                                }

                                d.sort(desc);
                            } else {
                                function desc(a, b) {
                                    return a.lastvalue - b.lastvalue
                                }
                                d.sort(desc);
                            }
                            if (d.length > 5) {
                                var ar = [];
                                for (var v = 0; v < 5; v++) {
                                    ar.push(d[v]);
                                }
                                vue_7.top5 = ar;
                                console.log(ar);
                                $('#load').mLoading("hide");
                            } else {
                                vue_7.top5 = d;
                                console.log(d);
                                $('#load').mLoading("hide");
                            }
                            $('#load').mLoading("hide");
                        })
                }, 30000);
            }else{
                vue_7.fresh='锁定刷新';
                timer=clearInterval(timer);
            }
        }
    },
    beforeCreate: function () {
        var _this = this;
        $.post("/monitor/hostGroup", function (data) {
            _this.host = data
        });
        $.post("/monitor/host", function (data) {
            _this.hostid = data;
            console.log(data)
        });
        $.post("/monitor/sessions", function (data) {
            console.log(data)
        });
    }
});
var vue_5 = new Vue({
    el: '#vue_5',
    data: {
        getTriggerByGroup: '',
        getNumberOfGroup: '',
        d: ''
    },
    methods: {
        groupClcik: function (id) {
            vue_1.search = id
        }
    },
    beforeCreate: function () {
        var _this = this;
        var ajax1 = $.post("/monitor/getTriggerByGroup", function (data) {
            var d = JSON.parse(JSON.parse(data)).result, arr = [];
            for (var a = 0; a < d.length; a++) {
                for (var b = 0; b < d[a].groups.length; b++) {
                    arr.push(d[a].groups[b].groupid)
                }
            }
            arr.sort();
            var res = [];
            for (var u = 0; u < arr.length;) {
                var count = 0;
                for (var o = 0; o < arr.length; o++) {
                    if (arr[u] == arr[o]) {
                        count++
                    }
                }
                res.push([arr[u], count]);
                u += count
            }
            _this.getTriggerByGroup = res;
            console.log(d);
            console.log(arr);
            console.log(res);
        });
        var ajax2 = $.post("/monitor/getNumberOfGroup", function (data) {
            _this.getNumberOfGroup = JSON.parse(JSON.parse(data)).result
        });
        setInterval(function () {
            $.post("/monitor/getTriggerByGroup", function (data) {
                var d = JSON.parse(JSON.parse(data)).result, arr = [];
                for (var a = 0; a < d.length; a++) {
                    for (var b = 0; b < d[a].groups.length; b++) {
                        arr.push(d[a].groups[b].groupid)
                    }
                }
                arr.sort();
                var res = [];
                for (var u = 0; u < arr.length;) {
                    var count = 0;
                    for (var o = 0; o < arr.length; o++) {
                        if (arr[u] == arr[o]) {
                            count++
                        }
                    }
                    res.push([arr[u], count]);
                    u += count
                }
                _this.getTriggerByGroup = res;
                console.log(d);
                console.log(arr);
                console.log(res);
            });
            $.post("/monitor/getNumberOfGroup", function (data) {
                _this.getNumberOfGroup = JSON.parse(JSON.parse(data)).result
            });
        }, 30000);
        var q=[];
        $.when(ajax1, ajax2).done(function () {
            for (var c = 0; c < _this.getNumberOfGroup.length; c++) {
                _this.getNumberOfGroup[c].err = 0;
                for (var d = 0; d < _this.getTriggerByGroup.length; d++) {
                    if (_this.getNumberOfGroup[c].groupid == _this.getTriggerByGroup[d][0]) {
                        _this.getNumberOfGroup[c].err = _this.getTriggerByGroup[d][1];
                        if(_this.getNumberOfGroup[c].hosts.length!=0){
                            q.push(_this.getNumberOfGroup[c])
                        }
                    }
                }
            }
            _this.d = q;
            console.log(q);

        })

    }
});
var vue_6 = new Vue({//故障趋势切换
    el: '#vue_6',
    data: {
        timeModel: 2592000
    },
    methods: {
        time: function (t) {
            if(t == 2592000){
                $.post('getQushi', {time: (Date.parse(new Date()) / 1000 - t)}, function (data) {
                    console.log(JSON.parse(JSON.parse(data)));
                    var d = JSON.parse(JSON.parse(data)).result;
                    var arr = [], arr2 = [];
                    for (var a = 0; a < d.length; a++) {
                        if (d[a].value == '0') {//正常
                            arr.push(new Date(parseInt(d[a].lastchange) * 1000).toLocaleDateString())
                        } else {//故障
                            arr2.push(new Date(parseInt(d[a].lastchange) * 1000).toLocaleDateString())
                        }
                    }
                    console.log(arr);
                    console.log(arr2);

                    function fu(arr) {
                        var re = [];
                        for (var u = 0; u < arr.length;) {
                            var count = 0;
                            for (var o = 0; o < arr.length; o++) {
                                if (arr[u] == arr[o]) {
                                    count++
                                }
                            }
                            re.push([arr[u], count]);
                            u += count
                        }
                        return re
                    }
                    function fu2(arr) {
                        var array = [];
                        for (var b = 0; b < arr.length; b++) {
                            array[b] = {name:arr[b][0],value:arr[b]}
                        }
                        return array
                    }
                    console.log(fu2(fu(arr)));
                    console.log(fu2(fu(arr2)));
                    option1.series[0].data = fu2(fu(arr));
                    option1.series[1].data = fu2(fu(arr2));
                    myChart1.setOption(option1,true)
                })
            }else{
                function timestampToTime2(timestamp) {
                    var date = new Date(timestamp * 1000);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
                    Y = date.getFullYear() + '/';
                    M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '/';
                    D = date.getDate() + ' ';
                    h = date.getHours() + ':';
                    m = '00'+':';
                    s = '00';
                    return Y + M + D + h + m + s;
                }
                $.post('getQushi', {time: (Date.parse(new Date()) / 1000 - t)}, function (data) {
                    console.log(JSON.parse(JSON.parse(data)));
                    var d = JSON.parse(JSON.parse(data)).result;
                    var arr = [], arr2 = [];
                    for (var a = 0; a < d.length; a++) {
                        if (d[a].value == '0') {//正常
                            arr.push(timestampToTime2(d[a].lastchange))
                        } else {//故障
                            arr2.push(timestampToTime2(d[a].lastchange))
                        }
                    }
                    console.log(arr);
                    console.log(arr2);

                    function fu(arr) {
                        var re = [];
                        for (var u = 0; u < arr.length;) {
                            var count = 0;
                            for (var o = 0; o < arr.length; o++) {
                                if (arr[u] == arr[o]) {
                                    count++
                                }
                            }
                            re.push([arr[u], count]);
                            u += count
                        }
                        return re
                    }
                    function fu2(arr) {
                        var array = [];
                        for (var b = 0; b < arr.length; b++) {
                            array[b] = {name:arr[b][0],value:arr[b]}
                        }
                        return array
                    }
                    console.log(fu2(fu(arr)));
                    console.log(fu2(fu(arr2)));
                    option1.series[0].data = fu2(fu(arr));
                    option1.series[1].data = fu2(fu(arr2));
                    myChart1.setOption(option1,true)
                })
            }
        }
    },
    beforeCreate: function () {
        $.post('getQushi', {time: (Date.parse(new Date()) / 1000 - 2592000)}, function (data) {
            console.log(JSON.parse(JSON.parse(data)));
            var d = JSON.parse(JSON.parse(data)).result;
            var arr = [], arr2 = [];
            for (var a = 0; a < d.length; a++) {
                if (d[a].value == '0') {//正常
                    arr.push(new Date(parseInt(d[a].lastchange) * 1000).toLocaleDateString())
                } else {//故障
                    arr2.push(new Date(parseInt(d[a].lastchange) * 1000).toLocaleDateString())
                }
            }
            console.log(arr);
            console.log(arr2);

            function fu(arr) {
                var re = [];
                for (var u = 0; u < arr.length;) {
                    var count = 0;
                    for (var o = 0; o < arr.length; o++) {
                        if (arr[u] == arr[o]) {
                            count++
                        }
                    }
                    re.push([arr[u], count]);
                    u += count
                }
                return re
            }
            function fu2(arr) {
                var array = [];
                for (var b = 0; b < arr.length; b++) {
                    array[b] = {name:arr[b][0],value:arr[b]}
                }
                return array
            }
            console.log(fu2(fu(arr)));
            console.log(fu2(fu(arr2)));
            option1.series[0].data = fu2(fu(arr));
            option1.series[1].data = fu2(fu(arr2));
            myChart1.setOption(option1,true);
            window.addEventListener("resize",myChart1.resize)
        })
    }
});
var myChart1 = echarts.init(document.getElementById('chart'), 'macarons');
var option1 = {
    tooltip : {
        trigger: 'axis',
        axisPointer : {
            type : 'shadow'
        }
    },
    toolbox: {
        show: true,
        feature: {
            dataZoom: {
                show: true
            },
            restore: {show: true},
            dataView: {show: true, readOnly: true},
            magicType: {show: true, type: ['line', 'bar']},
            saveAsImage: {show: true}
        }
    },
    legend: {
        data: ['已修复数量', '故障数量']
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
        show: true,
        type: 'value'
    },
    series: [{
        name: '已修复数量',
        type: 'line',
        smooth: true,
        barWidth: 20,
        barGap: '-100%',
        areaStyle:'',
        color:'#5bc0de',
        data: ''
    }, {
        name: '故障数量',
        type: 'line',
        smooth: true,
        barWidth: 20,
        barGap: '-100%',
        color:'#ff666e',
        areaStyle:'',
        data: ''
    }]
};
var vue_0 = new Vue({
    el: '#vue_0',
    data: {
        d1: '',
        d2: '',
        d3: '',
        Windows: '',
        network: '',
        linux: ''
    },
    methods: {
        c: function () {
            vue_1.search = ''
        },
        jumptype:function(n){
            $.cookie('type',n,{expires: 365});
            console.log( $.cookie('type'));
            $.cookie('hostName',null);
            window.location.href="Infrastructure.html";
        }
    },
    beforeCreate: function () {
        var _this = this;
        var ajax1 = $.post("/monitor/hostNum", function (data) {
            console.log(data);
            _this.d1 = data;
        });
        var ajax2 = $.post("/monitor/notOkHost", function (data) {
            console.log(data);
            _this.d2 = data;
        });
        setInterval(function () {
            $.post("/monitor/hostNum", function (data) {
                console.log(data);
                _this.d1 = data;
            });
            $.post("/monitor/notOkHost", function (data) {
                console.log(data);
                _this.d2 = data;
            });
        }, 60000);
        $.when(ajax1, ajax2).done(function () {
            _this.d3 = _this.d1 - _this.d2;
        });
        $.post("/monitor/getNumByType", function (data) {
            console.log(JSON.parse(JSON.parse(data)));
            var d = JSON.parse(JSON.parse(data));
            _this.Windows = d.Windows;
            _this.network = d.network;
            _this.linux = d.Linux;
        });
    }
});
var vue = new Vue({
    el: '#vue',
    data: {
        business: ''
    },
    beforeCreate: function () {
        var _this = this;
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
                function desc(a, b) {
                    return b.maxFault - a.maxFault
                }
                data.sort(desc);
        });
        setInterval(function () {
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
                function desc(a, b) {
                    return b.maxFault - a.maxFault
                }
                data.sort(desc);
            });
        }, 60000);
    }
});


var vue_11 = new Vue({
    el: '#vue_11',
    data: {
    },
    beforeCreate: function () {
        $.post("/monitor/getPro", function (data) {
            var d = JSON.parse(JSON.parse(data));
            var chart11 = echarts.init(document.getElementById('chart11'));
            var option11 = {
                tooltip: {
                    trigger: 'item',
                    formatter: "{a} <br/>{b}: {c} ({d}%)"
                },
                legend: {
                    orient: 'vertical',
                    x: 'left',
                    itemWidth:8,
                    itemHeight:8,
                    textStyle: {
                        fontSize: '12'
                    },
                    data:['灾难', '高', '一般', '警告', '信息','未分类']
                },
                series: [
                    {
                        name:'故障视图',
                        type:'pie',
                        radius: ['50%', '70%'],
                        center:['60%','50%'],
                        avoidLabelOverlap: false,
                        label: {
                            normal: {
                                show: false,
                                position: 'center'
                            },
                            emphasis: {
                                show: true,
                                textStyle: {
                                    fontSize: '20',
                                    fontWeight: 'bold'
                                }
                            }
                        },
                        labelLine: {
                            normal: {
                                show: false
                            }
                        },
                        data:[
                            {value:d[5], name:'灾难',itemStyle:{ normal:{color:'#ff0202'}}},
                            {value:d[4], name:'高',itemStyle:{ normal:{color:'#ff666e'}}},
                            {value:d[3], name:'一般',itemStyle:{ normal:{color:'#ff9a02'}}},
                            {value:d[2], name:'警告',itemStyle:{ normal:{color:'#ffcc02'}}},
                            {value:d[1], name:'信息',itemStyle:{ normal:{color:'#38de8b'}}},
                            {value:d[0], name:'未分类',itemStyle:{ normal:{color:'#cacc9e'}}}
                        ]
                    }
                ]};
            chart11.setOption(option11,true);
            //window.onresize = chart11.resize;
            window.addEventListener("resize",chart11.resize);
            chart11.on('click', function (params) {
                vue_1.search=params.name;
            });
        });
        setInterval(function () {
            $.post("/monitor/getPro", function (data) {
                var d = JSON.parse(JSON.parse(data));
                var chart11 = echarts.init(document.getElementById('chart11'));
                var option11 = {
                    tooltip: {
                        trigger: 'item',
                        formatter: "{a} <br/>{b}: {c} ({d}%)"
                    },
                    legend: {
                        orient: 'vertical',
                        x: 'left',
                        itemWidth:8,
                        itemHeight:8,
                        textStyle: {
                            fontSize: '12'
                        },
                        data:['灾难', '高', '一般', '警告', '信息','未分类']
                    },
                    series: [
                        {
                            name:'故障视图',
                            type:'pie',
                            radius: ['50%', '70%'],
                            center:['60%','50%'],
                            avoidLabelOverlap: false,
                            label: {
                                normal: {
                                    show: false,
                                    position: 'center'
                                },
                                emphasis: {
                                    show: true,
                                    textStyle: {
                                        fontSize: '20',
                                        fontWeight: 'bold'
                                    }
                                }
                            },
                            labelLine: {
                                normal: {
                                    show: false
                                }
                            },
                            data:[
                                {value:d[5], name:'灾难',itemStyle:{ normal:{color:'#ff0202'}}},
                                {value:d[4], name:'高',itemStyle:{ normal:{color:'#ff666e'}}},
                                {value:d[3], name:'一般',itemStyle:{ normal:{color:'#ff9a02'}}},
                                {value:d[2], name:'警告',itemStyle:{ normal:{color:'#ffcc02'}}},
                                {value:d[1], name:'信息',itemStyle:{ normal:{color:'#38de8b'}}},
                                {value:d[0], name:'未分类',itemStyle:{ normal:{color:'#cacc9e'}}}
                            ]
                        }
                    ]};
                chart11.setOption(option11,true);
                //window.onresize = chart11.resize;
                window.addEventListener("resize",chart11.resize);
                chart11.on('click', function (params) {
                    vue_1.search=params.name;
                });
            });
        }, 30000);
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




