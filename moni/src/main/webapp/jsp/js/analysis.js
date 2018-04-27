var vue = new Vue({
    el: '#analysis',
    data: {
        hostGroup: '',
        hostGroupModel: '',
        hostGroupView: '',
        host: '',
        hostModel: '',
        hostView: [],
        hostGroupModel2: '',
        host2Model: '',
        host2: '',
        application_Model: '',
        application: '',
        itemModel: '',
        item: '',
        item_: [],
        time: [],
        selectModel: '0',
        show3: false,
        show2: false,
        show1: true,
        show4: true,
        show5: true,
        show6: false,
        show7: false,
        show8: true,
        show9: true,
        show10: false,
        intervalModel: 3600,
        startTime: '',
        endTime: '',
        max: true,
        min: true,
        avg: true,
        performance: 'max',
        cycle: 'day',
        da: '',
        cpuavg: '',
        ramavg: '',
        cpumaxData: '',
        cpuhost: '',
        rammaxData: '',
        ramhost: '',
        cpuavgData: '',
        ramavgData: '',
        performance1Max: true,
        performance1Avg: false,
        maxTime: '',
        avgNum: '',
        floatmin: '',
        floatmax: '',
        hostidbyapp: '',
        excelData: '',
        excelUrl: '',
        cpuKeyData: [],
        ramKeyData: [],
        updateValue:0
    },
    beforeCreate: function () {
        var _this = this;
        $.post("/monitor/getHostGroup", function (data) {
            var da = JSON.parse(JSON.parse(data)).result;
            _this.hostGroup = da;
            console.log(da);
        });
        $.post("/monitor/host", function (data) {
            _this.host = data;
            console.log(data);
        });
        var key = Object.keys($.cookie()), val = Object.values($.cookie());
        var cpuval = [], ramval = [];
        for (var a = 0; a < key.length; a++) {
            if (key[a].indexOf('cpuKey') >= 0) {
                cpuval.push(val[a])
            }
            if (key[a].indexOf('ramKey') >= 0) {
                ramval.push(val[a])
            }
        }
        this.$nextTick(function () {
            if (cpuval.length > 1) {
                for (var d = 0; d < cpuval.length - 1; d++) {
                    _this.cpuKeyData.push(d)
                }
            }
            if (ramval.length > 1) {
                for (var e = 0; e < ramval.length - 1; e++) {
                    _this.ramKeyData.push(e)
                }
            }
            this.$nextTick(function () {
                for (var b = 0; b < cpuval.length; b++) {
                    $('.cpuKey').eq(b).val(cpuval[b])
                }
                for (var c = 0; c < ramval.length; c++) {
                    $('.ramKey').eq(c).val(ramval[c])
                }
            })
        });
    },
    methods: {
        delCommon: function (data, i) {
            data.splice(i, 1)
        },
        addCpuKey: function (d) {
            d.push(0)
        },
        switching: function () {
            vue.hostView = [];
            vue.show5 = true;
            vue.show6 = false;
        },
        excel: function () {
            console.log(vue.excelData);
            var temp = document.createElement("form");
            temp.action = 'writerExcel';
            temp.method = "post";
            temp.style.display = "none";
            var opt = document.createElement("textarea");
            opt.name = 'second';
            opt.value = vue.excelData;
            temp.appendChild(opt);
            var opt = document.createElement("textarea");
            opt.name = 'type';
            opt.value = 'xlsx';
            temp.appendChild(opt);
            document.body.appendChild(temp);
            temp.submit();
            return temp;
        },
        cycleChange: function () {
            var s = 0;
            if (vue.cycle == 'day') {
                s = 86400
            } else if (vue.cycle == 'week') {
                s = 608400
            } else if (vue.cycle == 'month') {
                s = 2592000
            }
            var laydate = layui.laydate;
            laydate.render({
                elem: '#hottime',
                type: 'datetime',
                value: new Date(new Date().getTime() - s * 1000)
            });
        },
        computSubmit: function () {
            function k(dom, str) {
                var cpuKey = [];
                for (var c = 0; c < dom.length; c++) {
                    if (dom.eq(c).val()) {
                        cpuKey.push(dom.eq(c).val());
                        $.cookie(str + c, dom.eq(c).val(), {expires: 365});
                    }
                }
                return cpuKey
            }

            function addCookie(dom, str) {
                for (var d = 0; d < dom.length; d++) {
                    if (dom.eq(d).val()) {
                        $.cookie(str + d, dom.eq(d).val(), {expires: 365});
                    }
                }
            }

            console.log(k($('.cpuKey')));
            console.log(k($('.ramKey')));

            function timestampToTime(timestamp) {
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
            var key1 = [],key2 = [];
            if(vue.updateValue == 0){
                key1 = ['perf_counter[\Processor(_Total)\% Processor Time]','systemcpu'];
                key2 = 'vm.memory.size[pused]'
            }else{
                key1 = k($('.cpuKey'));
                key2 = k($('.ramKey'))
            }

            if (vue.hostView.length > 0) {
                option2.legend.data = [];
                option2.series = [];
                option3.legend.data = [];
                option3.series = [];
                option4.legend.data = [];
                option4.series = [];
                option5.legend.data = [];
                option5.series = [];
                console.log(option3);
                console.log(option4);
                console.log(option2);
                console.log(option5);
                chart3.showLoading();
                chart4.showLoading();
                chart5.showLoading();
                chart2.showLoading();
                var date1 = Date.parse(new Date($('#hottime').val())) / 1000;
                var date2 = Date.parse(new Date($('#hottime2').val())) / 1000;
                var host = [];
                for (var b = 0; b < vue.hostView.length; b++) {
                    host.push(vue.hostView[b].name)
                }
                console.log(host);
                var cpuajax = $.ajax({
                    type: 'post',
                    url: '/monitor/getCPUItemByHost',
                    traditional: true,
                    data: {
                        host: host,
                        timeStart: date1,
                        timeEnd: date2,
                        select: vue.cycle,
                        cpu: key1
                    },
                    dataType: 'json',
                    success: function (data) {
                        addCookie($('.cpuKey'), 'cpuKey');
                        console.log(JSON.parse(data));
                        var id = Object.keys(JSON.parse(data)), val = Object.values(JSON.parse(data)), max = [],
                            avg = [],
                            host = [];
                        for (var a = 0; a < val.length; a++) {
                            avg.push(val[a][0]);
                            max.push(val[a][1]);
                        }
                        vue.cpuavg = avg;
                        for (var b = 0; b < vue.host.length; b++) {
                            if (id.indexOf(vue.host[b].hostid) >= 0) {
                                host.push(vue.host[b].name)
                            }
                        }
                        var maxData = [], name = [], value = [], avgname = [], avgvalue = [], avgData = [];
                        for (var c = 0; c < max.length; c++) {
                            name.push(Object.keys(max[c]));
                            value.push(Object.values(max[c]));
                            maxData[c] = [];
                            name[c].sort();
                            avgname.push(Object.keys(avg[c]));
                            avgvalue.push(Object.values(avg[c]));
                            avgname[c].sort();
                            avgData[c] = [];
                            for (var d = 0; d < name[c].length; d++) {
                                name[c][d] = [name[c][d]];
                                name[c][d].push(max[c][(name[c][d][0])]);
                                name[c][d][0] = timestampToTime(parseInt(name[c][d][0]));
                                maxData[c][d] = {name: name[c][d][0], value: name[c][d]}
                            }
                            for (var i = 0; i < avgname[c].length; i++) {
                                avgname[c][i] = [avgname[c][i]];
                                avgname[c][i].push(avg[c][(avgname[c][i][0])]);
                                avgname[c][i][0] = timestampToTime(parseInt(avgname[c][i][0]));
                                avgData[c][i] = {name: avgname[c][i][0], value: avgname[c][i]}
                            }
                        }
                        vue.cpuavgData = avgData;
                        vue.cpumaxData = maxData;
                        vue.cpuhost = host;

                        for (var e = 0; e < vue.cpumaxData.length; e++) {
                            option3.legend.data[e] = vue.cpuhost[e];
                            option3.series[e] = {
                                type: 'line',
                                data: vue.cpumaxData[e],
                                name: vue.cpuhost[e],
                                stack: '总量',
                                areaStyle: {normal: {}}
                            };
                            option4.legend.data[e] = vue.cpuhost[e];
                            option4.series[e] = {
                                type: 'line',
                                data: avgData[e],
                                name: vue.cpuhost[e],
                                stack: '总量',
                                areaStyle: {normal: {}}
                            }
                        }
                        console.log(option3);
                        console.log(option4);
                        chart3.setOption(option3, true);
                        chart4.setOption(option4, true);
                        chart3.hideLoading();
                        chart4.hideLoading();
                    }
                });
                var ramajax2 = $.ajax({
                    type: 'post',
                    url: '/monitor/getMenItemByHost',
                    traditional: true,
                    data: {
                        host: host,
                        timeStart: date1,
                        timeEnd: date2,
                        select: vue.cycle,
                        men: key2
                    },
                    dataType: 'json',
                    success: function (data) {
                        addCookie($('.ramKey'), 'ramKey');
                        console.log(JSON.parse(data));
                        var id = Object.keys(JSON.parse(data)), val = Object.values(JSON.parse(data)), max = [],
                            avg = [],
                            host = [];
                        for (var a = 0; a < val.length; a++) {
                            avg.push(val[a][0]);
                            max.push(val[a][1]);
                        }
                        vue.ramavg = avg;
                        for (var b = 0; b < vue.host.length; b++) {
                            if (id.indexOf(vue.host[b].hostid) >= 0) {
                                host.push(vue.host[b].name)
                            }
                        }
                        var maxData = [], name = [], value = [], avgname = [], avgvalue = [], avgData = [];
                        for (var c = 0; c < max.length; c++) {
                            name.push(Object.keys(max[c]));
                            value.push(Object.values(max[c]));
                            maxData[c] = [];
                            name[c].sort();
                            avgname.push(Object.keys(avg[c]));
                            avgvalue.push(Object.values(avg[c]));
                            avgname[c].sort();
                            avgData[c] = [];
                            for (var d = 0; d < name[c].length; d++) {
                                name[c][d] = [name[c][d]];
                                name[c][d].push(max[c][(name[c][d][0])]);
                                name[c][d][0] = timestampToTime(parseInt(name[c][d][0]));
                                maxData[c][d] = {name: name[c][d][0], value: name[c][d]}
                            }
                            for (var i = 0; i < avgname[c].length; i++) {
                                avgname[c][i] = [avgname[c][i]];
                                avgname[c][i].push(avg[c][(avgname[c][i][0])]);
                                avgname[c][i][0] = timestampToTime(parseInt(avgname[c][i][0]));
                                avgData[c][i] = {name: avgname[c][i][0], value: avgname[c][i]}
                            }
                        }
                        vue.rammaxData = maxData;
                        vue.ramavgData = avgData;
                        vue.ramhost = host;

                        for (var e = 0; e < vue.rammaxData.length; e++) {
                            option2.legend.data[e] = vue.ramhost[e];
                            option2.series[e] = {
                                type: 'line',
                                data: vue.rammaxData[e],
                                name: vue.ramhost[e],
                                stack: '总量',
                                areaStyle: {normal: {}},
                                smooth: true
                            };
                            option5.legend.data[e] = vue.ramhost[e];
                            option5.series[e] = {
                                type: 'line',
                                data: avgData[e],
                                name: vue.ramhost[e],
                                stack: '总量',
                                areaStyle: {normal: {}},
                                smooth: true
                            }
                        }
                        console.log(option2);
                        console.log(option5);
                        chart2.setOption(option2, true);
                        chart2.hideLoading();
                        chart5.setOption(option5, true);
                        chart5.hideLoading();
                    }
                });
                $.when(cpuajax, ramajax2).done(function () {
                    var rammaxValue = [], cpumaxValue = [], ramarray = [], cpuarray = [], ramavgValue = [],
                        cpuavgValue = [], ramavgarray = [], cpuavgarray = [];
                    for (var k = 0; k < vue.rammaxData.length; k++) {
                        rammaxValue[k] = [];
                        cpumaxValue[k] = [];
                        ramavgValue[k] = [];
                        cpuavgValue[k] = [];
                        for (var o = 0; o < vue.rammaxData[k].length; o++) {
                            rammaxValue[k].push(parseFloat(vue.rammaxData[k][o].value[1]));
                            cpumaxValue[k].push(parseFloat(vue.cpumaxData[k][o].value[1]));
                            ramavgValue[k].push(parseFloat(vue.ramavgData[k][o].value[1]));
                            cpuavgValue[k].push(parseFloat(vue.cpuavgData[k][o].value[1]));
                            ramarray[o] = 0;
                            cpuarray[o] = 0;
                            cpuavgarray[o] = 0;
                            ramavgarray[o] = 0
                        }
                    }

                    function arrayAdd(a, b) {
                        a.forEach(function (val, index) {
                            a[index] += b[index];
                        });
                    }

                    for (var g = 0; g < rammaxValue.length; g++) {
                        arrayAdd(ramarray, rammaxValue[g]);
                        arrayAdd(cpuarray, cpumaxValue[g]);
                        arrayAdd(cpuavgarray, cpuavgValue[g]);
                        arrayAdd(ramavgarray, ramavgValue[g]);
                    }

                    function getMaxOfArray(numArray) {
                        return Math.max.apply(null, numArray);
                    }

                    arrayAdd(ramarray, cpuarray);

                    function sum(arr) {
                        var sum = 0;
                        for (var i = 0; i < arr.length; i++) {
                            sum += arr[i];
                        }
                        return sum;
                    }

                    vue.maxTime = vue.rammaxData[0][ramarray.indexOf(getMaxOfArray(ramarray))].name;
                    arrayAdd(ramavgarray, cpuavgarray);
                    vue.avgNum = (sum(ramavgarray) / 2 / ramavgarray.length).toFixed(2) + '%';
                    vue.floatmin = ramavgarray[ramarray.indexOf(getMaxOfArray(ramarray))].toFixed(2) + '%';
                    vue.floatmax = ramarray[ramarray.indexOf(getMaxOfArray(ramarray))].toFixed(2) + '%';
                });
            } else {
                //alert('请选择主机')
                layui.use(['layer', 'form'], function () {
                    var layer = layui.layer
                        , form = layui.form;
                    layer.alert('主机与键值必填', {icon: 7});
                });
            }
        },
        performanceChange: function () {
            if (vue.performance == 'max') {
                vue.performance1Max = true;
                vue.performance1Avg = false;
            } else {
                vue.performance1Max = false;
                vue.performance1Avg = true;
            }
        },
        conditionSubmit: function () {
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

            chart.showLoading();
            if (vue.hostView.length > 0 && vue.item_.length > 0) {
                console.log(vue.item_);
                var hostid = [], itemid = [];
                for (var a = 0; a < vue.hostView.length; a++) {
                    hostid.push(vue.hostView[a].id)
                }
                for (var b = 0; b < vue.item_.length; b++) {
                    itemid.push(vue.item_[b].key)
                }
                if (vue.selectModel == '0') {
                    $.ajax({
                        type: 'post',
                        url: '/monitor/getModelSelete',
                        traditional: true,
                        data: {
                            hostids: hostid,
                            itemid: itemid,
                            startTime: $('#time').val(),
                            endTime: $('#time2').val()
                        },
                        dataType: 'json',
                        success: function (data) {
                            console.log(JSON.parse(data));
                            var da = JSON.parse(data), legend = [], value = [];
                            for (var a = 0; a < da.length; a++) {
                                for (var b = 0; b < vue.host.length; b++) {
                                    if (da[a].hostid == vue.host[b].hostid) {
                                        legend.push(vue.host[b].host)
                                    }
                                }
                                value.push(JSON.parse(da[a].value).result)
                            }
                            console.log(legend);
                            console.log(value);
                            var arr = [];
                            for (var c = 0; c < value.length; c++) {
                                arr[c] = [];
                                for (var d = 0; d < value[c].length; d++) {
                                    arr[c][d] = {
                                        name: timestampToTime2(parseInt(value[c][d].clock)),
                                        value: [timestampToTime2(parseInt(value[c][d].clock)), value[c][d].value]
                                    }
                                }
                            }
                            console.log(arr);
                            option.title.text = '同期对比';
                            option.legend.data = legend;
                            option.xAxis = {
                                type: 'time',
                                splitLine: {
                                    show: false
                                }
                            };
                            option.yAxis.axisLabel = {
                                formatter: function (val) {
                                    return val + JSON.parse(data)[0].units;
                                }
                            };
                            for (var e = 0; e < arr.length; e++) {
                                option.series[e] = {
                                    name: legend[e],
                                    type: 'line',
                                    smooth: true,
                                    data: arr[e]
                                }
                            }
                            chart.setOption(option, true);
                            chart.hideLoading();
                            /*var arr = [], keys = Object.keys(data), values = Object.values(data), keysarr = [],
                                valuesarr = [], yData = [], keyname = [];
                            for (var a = 0; a < keys.length; a++) {
                                keysarr.push(keys[a]);
                                valuesarr.push(JSON.parse(values[a]).result);
                                arr.push(valuesarr[a].length)
                            }
                            console.log(vue.host);
                            for (var f = 0; f < vue.host.length; f++) {
                                if (keysarr.indexOf(vue.host[f].hostid) >= 0) {
                                    keyname.push(vue.host[f].name)
                                }
                            }
                            console.log(keysarr);
                            console.log(valuesarr);
                            console.log(keyname);
                            var len = Math.min.apply(null, arr), xData = [];
                            for (var b = 0; b < len; b++) {
                                xData.push(timestampToTime(valuesarr[0][b].clock))
                            }
                            for (var c = 0; c < valuesarr.length; c++) {
                                yData[c] = [];
                                for (var d = 0; d < len; d++) {
                                    yData[c][d] = valuesarr[c][d].value;
                                }
                            }
                            var finalData = [];
                            for (var e = 0; e < valuesarr.length; e++) {
                                finalData[e] = {
                                    name: keyname[e],
                                    type: 'line',
                                    smooth: true,
                                    data: yData[e]
                                }
                            }
                            option.title.text = '同期对比';
                            option.legend.data = keyname;
                            option.xAxis.data = xData;
                            option.series = finalData;
                            chart.setOption(option, true);
                            chart.hideLoading();*/
                        }
                    });
                }
                if (vue.selectModel == '1') {
                    var startTime = [], endTime = [];
                    startTime.push($('#test').val());
                    endTime.push($('#test_').val());
                    for (var c = 0; c < $('.start').length; c++) {
                        startTime.push($('.start').eq(c).val());
                        endTime.push($('.end').eq(c).val())
                    }
                    $.ajax({
                        type: 'post',
                        url: '/monitor/getCycSelete',
                        traditional: true,
                        data: {
                            hostid: hostid,
                            itemid: itemid,
                            startTime: startTime,
                            endTime: endTime
                        },
                        dataType: 'json',
                        success: function (data) {
                            console.log(JSON.parse(data));
                            if (JSON.parse(data).length > 0) {
                                var da = JSON.parse(data), legend = [], value = [];
                                for (var a = 0; a < da.length; a++) {
                                    legend.push(timestampToTime2(parseInt(da[a].value.result[0].clock)) + ' - ' + timestampToTime2(parseInt(da[a].value.result[da[a].value.result.length - 1].clock)));
                                    value.push(da[a].value.result)
                                }
                                console.log(legend);
                                console.log(value);
                                var arr = [], len = [], xData = [],yData = [];
                                for (var c = 0; c < value.length; c++) {
                                    arr[c] = [];
                                    len.push(value[c].length);
                                }
                                var minlen = Math.min.apply(null, len);
                                for (var g = 0; g < value.length; g++) {
                                    yData[g] = [];
                                    for (var f = 0; f < minlen; f++) {
                                        xData[f] = f + 1;
                                        yData[g][f] = value[g][f].value;
                                    }
                                }
                                console.log(xData);
                                console.log(yData);
                                console.log(arr);
                                option.title.text = '周期对比';
                                option.legend.data = legend;
                                option.xAxis = {
                                    type: 'category',
                                    boundaryGap: false,
                                    data: xData
                                };
                                option.yAxis.axisLabel = {
                                    formatter: function (val) {
                                        return val + JSON.parse(data)[0].units;
                                    }
                                };
                                for (var e = 0; e < yData.length; e++) {
                                    option.series[e] = {
                                        name: legend[e],
                                        type: 'line',
                                        smooth: true,
                                        data: yData[e]
                                    }
                                }
                                chart.setOption(option, true);
                                chart.hideLoading();
                                /*var arr = [], arrlen = [];
                                for (var a = 0; a < data.length; a++) {
                                    if (JSON.parse(data[a]).result.length > 0) {
                                        arr.push(JSON.parse(data[a]).result);
                                        arrlen.push(JSON.parse(data[a]).result.length)
                                    }
                                }
                                console.log(arr);
                                var len = Math.min.apply(null, arrlen);
                                console.log(len);
                                var xdata = [], ydata = [], legend = [], array = [], leg = [];
                                for (var b = 0; b < arr.length; b++) {
                                    ydata[b] = [];
                                    var obj = {
                                        str: '',
                                        end: ''
                                    };
                                    legend[b] = obj;
                                    legend[b].end = timestampToTime(arr[b][0].clock);
                                    legend[b].str = timestampToTime(arr[b][len - 1].clock);
                                    for (var c = 0; c < len; c++) {
                                        xdata[c] = c + 1;
                                        ydata[b].push(arr[b][c].value);
                                    }
                                }
                                console.log(ydata);
                                option.title.text = '周期对比';
                                option.xAxis.data = xdata;
                                for (var d = 0; d < ydata.length; d++) {
                                    array[d] = {
                                        name: legend[d].str + '-' + legend[d].end,
                                        type: 'line',
                                        smooth: true,
                                        data: ydata[d]
                                    };
                                    leg[d] = legend[d].str + '至' + legend[d].end
                                }
                                console.log(array);
                                option.series = array;
                                option.legend.data = leg;
                                chart.setOption(option, true);
                                chart.hideLoading();*/
                            } else {
                                //alert('此监控项无数据');
                                layui.use(['layer', 'form'], function () {
                                    var layer = layui.layer
                                        , form = layui.form;
                                    layer.alert('此监控项无数据', {icon: 7});
                                });
                                chart.hideLoading();
                            }

                        }
                    });
                }
                if (vue.selectModel == '2') {
                    var hostName = [], key_ = [];
                    for (var d = 0; d < vue.hostView.length; d++) {
                        hostName.push(vue.hostView[d].name)
                    }
                    for (var e = 0; e < vue.item_.length; e++) {
                        key_.push(vue.item_[e].key)
                    }
                    $.ajax({
                        type: 'post',
                        url: '/monitor/getScreenData',
                        traditional: true,
                        data: {
                            itemkeys: key_,
                            hostnames: hostName,
                            hostid: hostid,
                            startTime: $('#time').val(),
                            endTime: $('#time2').val(),
                            max: true,
                            min: true,
                            avg: true
                        },
                        dataType: 'json',
                        success: function (data) {
                            console.log(data);
                            vue.excelData = data;
                            if(data.length > 0){
                                var arr = [], d = [];
                                for (var a = 0; a < data.length; a++) {
                                    arr.push(JSON.parse(data[a]))
                                }
                                console.log(arr);
                                for (var b = 0; b < arr.length; b++) {
                                    if (arr[b].item) {
                                        for (var c = 0; c < arr[b].item.length; c++) {
                                            d.push(arr[b].item[c])
                                        }
                                    }
                                }
                                console.log(d);
                                vue.da = d
                            }else{
                                layui.use(['layer', 'form'], function(){
                                    var layer = layui.layer
                                        ,form = layui.form;
                                    layer.alert('未查询到数据', {icon: 7});
                                });
                            }
                        }
                    });
                }
            } else {
                //alert('主机与监控项均为必选')
                layui.use(['layer', 'form'], function () {
                    var layer = layui.layer
                        , form = layui.form;
                    layer.alert('主机与监控项均为必选', {icon: 7});
                });
            }
        },
        intervalChange: function () {
            var laydate = layui.laydate;
            laydate.render({
                elem: '#test',
                type: 'datetime',
                value: new Date(new Date().getTime() - vue.intervalModel * 1000)
            });
            laydate.render({
                elem: '#test_',
                type: 'datetime',
                value: new Date()
            });
            for (var a = 0; a < vue.time.length; a++) {
                laydate.render({
                    elem: '#test' + a,
                    type: 'datetime',
                    value: new Date(new Date().getTime() - vue.intervalModel * 1000 - 60 * 60 * 1000 * 24 * (a + 1))
                });
                laydate.render({
                    elem: '#test_' + a,
                    type: 'datetime',
                    value: new Date(new Date().getTime() - 60 * 60 * 1000 * 24 * (a + 1))
                });
            }
        },
        delTime: function (i) {
            vue.time.splice(i, 1)
        },
        addTime: function () {
            vue.time.push(vue.time.length + 1);
            //vue循环完之后执行
            this.$nextTick(function () {
                var laydate = layui.laydate;
                for (var a = 0; a < vue.time.length; a++) {
                    laydate.render({
                        elem: '#test' + a,
                        type: 'datetime',
                        value: new Date(new Date().getTime() - vue.intervalModel * 1000 - 60 * 60 * 1000 * 24 * (a + 1))
                    });
                    laydate.render({
                        elem: '#test_' + a,
                        type: 'datetime',
                        value: new Date(new Date().getTime() - 60 * 60 * 1000 * 24 * (a + 1))
                    });
                }
            })

        },
        conditionChange: function () {
            vue.hostView = [];
            vue.item_ = [];
            vue.hostGroupModel2 = '';
            vue.application_Model = '';
            vue.host2 = [];
            vue.application = [];
            vue.item = [];
            if (vue.selectModel == '0') {
                vue.show3 = false;
                vue.show2 = false;
                vue.show1 = true;
                vue.show4 = true;
                vue.show5 = true;
                vue.show6 = false;
                vue.show7 = false;
                vue.show8 = true;
                vue.show9 = true;
                vue.show10 = false;
            }
            if (vue.selectModel == '1') {
                vue.show3 = false;
                vue.show2 = true;
                vue.show1 = false;
                vue.show4 = false;
                vue.show5 = false;
                vue.show6 = true;
                vue.show7 = false;
                vue.show8 = true;
                vue.show9 = true;
                vue.show10 = false;
            }
            if (vue.selectModel == '2') {
                vue.show3 = true;
                vue.show2 = false;
                vue.show1 = true;
                vue.show4 = true;
                vue.show5 = true;
                vue.show6 = false;
                vue.show7 = true;
                vue.show8 = false;
                vue.show9 = false;
                vue.show10 = true;
            }
        },
        addHostGroup: function () {
            var arr = [];
            for (var a = 0; a < vue.hostGroupModel.length; a++) {
                arr.push(vue.hostGroup[vue.hostGroupModel[a]])
            }
            vue.hostGroupView = arr
        },
        addHost: function () {
            var arr = [];
            if (vue.show5) {
                for (var a = 0; a < vue.hostModel.length; a++) {
                    if (vue.hostView.indexOf(vue.host2[vue.hostModel[a]]) >= 0) {

                    } else {
                        vue.hostView.push(vue.host2[vue.hostModel[a]])
                    }
                }
            } else {
                arr.push(vue.host2[vue.hostModel]);
                vue.hostView = arr
            }
            vue.hostModel = [];
            console.log(vue.hostView);
            var id = [];
            for (var b = 0; b < vue.hostView.length; b++) {
                id.push(vue.hostView[b].id)
            }
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
        getHostByHostGroup: function () {
            console.log(vue.hostGroupModel2);
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
        getItem: function () {
            console.log(vue.hostidbyapp);
            $.ajax({
                type: 'post',
                url: '/monitor/getItemByApp',
                traditional: true,
                data: {
                    applicationId: vue.application_Model,
                    hostid: vue.hostidbyapp
                },
                success: function (da) {
                    console.log(da);
                    var val = Object.values(da), key = Object.keys(da), data = [];
                    for (var a = 0; a < key.length; a++) {
                        data[a] = {key: key[a], val: val[a]}
                    }
                    if (data.length > 0) {

                    } else {
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
        itemPush: function () {
            var arr = [];
            if (vue.show7) {
                for (var a = 0; a < vue.itemModel.length; a++) {
                    if (vue.item_.indexOf(vue.item[vue.itemModel[a]]) >= 0) {

                    } else {
                        vue.item_.push(vue.item[vue.itemModel[a]])
                    }
                }
            } else {
                arr.push(vue.item[vue.itemModel]);
                vue.item_ = arr;
            }

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
        delHostGroup: function (i) {
            var arr = [];
            for (var a = 0; a < vue.hostGroupView.length; a++) {
                arr[a] = a
            }
            if (arr.indexOf(i) >= 0) {
                vue.hostGroupView.splice(i, 1)
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
layui.use('laydate', function () {
    var laydate = layui.laydate;
    laydate.render({
        elem: '#time',
        type: 'datetime',
        value: new Date(new Date().getTime() - 60 * 60 * 1000)
    });
    laydate.render({
        elem: '#time2',
        type: 'datetime',
        value: new Date()
    });
    var s = 0;
    if (vue.cycle == 'day') {
        s = 86400
    } else if (vue.cycle == 'week') {
        s = 608400
    } else if (vue.cycle == 'month') {
        s = 2592000
    }
    laydate.render({
        elem: '#hottime',
        type: 'datetime',
        value: new Date(new Date().getTime() - 86400 * 1000)
    });
    laydate.render({
        elem: '#hottime2',
        type: 'datetime',
        value: new Date()
    });
    laydate.render({
        elem: '#test',
        type: 'datetime',
        value: new Date(new Date().getTime() - vue.intervalModel * 1000)
    });
    laydate.render({
        elem: '#test_',
        type: 'datetime',
        value: new Date()
    });
});
var chart = echarts.init(document.getElementById('chart'), 'roma');
var option = {
    title: {
        text: ''
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
        data: []
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
            saveAsImage: {show: true}
        }
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    xAxis: '',
    yAxis: {
        show: true,
        type: 'value',
        axisLabel:''
    },
    series: []
};
var chart2 = echarts.init(document.getElementById('chart2'), 'roma');
var option2 = {
    title: {
        text: '内存峰值'
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
        data: []
    },
    toolbox: {
        feature: {
            saveAsImage: {}
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
    yAxis: [{
        type: 'value',
        axisLabel: {
            formatter: function (val) {
                return val + '%';
            }
        }
    }
    ],
    series: []
};
var chart3 = echarts.init(document.getElementById('chart3'), 'roma');
var option3 = {
    title: {
        text: 'CPU峰值'
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
        data: []
    },
    toolbox: {
        feature: {
            saveAsImage: {}
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
                return val + '%';
            }
        }
    },
    series: []
};
var chart4 = echarts.init(document.getElementById('chart4'), 'roma');
option4 = {
    title: {
        text: 'CPU负载'
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
        data: []
    },
    toolbox: {
        feature: {
            saveAsImage: {}
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
                return val + '%';
            }
        }
    },
    series: []
};
var chart5 = echarts.init(document.getElementById('chart5'), 'roma');
option5 = {
    title: {
        text: '内存负载'
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
        data: []
    },
    toolbox: {
        feature: {
            saveAsImage: {}
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
                return val + '%';
            }
        }
    },
    series: []
};
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