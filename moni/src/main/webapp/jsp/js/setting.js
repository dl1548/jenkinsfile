var vue1 = new Vue({
    el: '#a2',
    data: {
        changed: '1',
        changed2: '',
        sh: false,
        sh2: false,
        userData: '',
        search: '',
        userModel: [],
        checked: false,
        np1: '',
        np2: '',
        np: true,
        np_: true,
        us: true,
        us2: true,
        u: '',
        m1: '',
        m2: '',
        m3: '',
        m4: '',
        m5: '',
        m6: '',
        m7: '',
        test9: '',
        m8: '',
        userid: '',
        usersData: '',
        groupSearch: '',
        chm: false,
        chin: [],
        grou: '',
        groupSearch2: '',
        se1: [],
        se2: [],
        se3: [],
        sl1: '',
        v1: '',
        v2: '',
        v3: '',
        n1: '',
        n2: '0',
        see: '',
        us1: '',
        k1: '',
        k2: '',
        k3: [],
        k4: [],
        k5: [],
        k6: '',
        k7: '',
        e1_: '',
        e2_: '',
        e3_: '',
        groupid: '',
        uss: '',
        uss2: '',
        usgro: '',
        userg: '',
        email: false,
        message: false,
        wechat: false,
        ch1: '',
        ch2: '',
        ch3: '',
        subid:''
    },
    beforeCreate: function () {
        // $('#load').mLoading("show");
        var _this = this;
        $.post("/monitor/getUsers", function (data) {
            var obj = {
                1:'用户',
                2:'管理员',
                3:'超级管理员'
            };
            for(var a = 0;a<data.length;a++){
                data[a].type_ = obj[data[a].type]
            }
            _this.userData = data;
            console.log(data);
        });
        $.post("/monitor/getUserGroup", function (data) {
            _this.usersData = data;
            console.log(data);
        });
        $.post("/monitor/hostGroup", function (data) {
            _this.grou = data;
            console.log(data);
        });
    },
    methods: {
        createUser: function () {
            vue1.uss2 = '';
            vue1.u = '';
            vue1.np1 = '';
            vue1.np2 = '';
            vue1.changed = '1';
            $('#name').val('');
            $('#tel').val('');
            $('#em').val('');
        },
        deluserspan: function (i) {
            vue1.uss2.splice(i, 1)
        },
        deluserspan2: function (i) {
            vue1.userg.splice(i, 1)
        },
        ao: function () {
            var arr = [];
            console.log(vue1.usgro);
            for (var a = 0; a < vue1.usgro.length; a++) {
                arr.push(vue1.usersData[vue1.usgro[a]])
            }
            vue1.userg = arr
        },
        tj: function () {
            var arr = [];
            for (var a = 0; a < vue1.uss.length; a++) {
                arr.push(vue1.usersData[vue1.uss[a]])
            }
            vue1.uss2 = arr;
        },
        se: function (index) {
            vue1.see = vue1.sea2[index].users;
        },
        ed: function (index) {
            vue1.k1 = vue1.sea2[index].name;
            vue1.k2 = vue1.sea2[index].users_status;
            vue1.groupid = vue1.sea2[index].usrgrpid;
            var k0 = [], k3 = [], k4 = [], k5 = [], k1 = [];
            var arr = vue1.sea2[index].rights;
            for (var a = 0; a < arr.length; a++) {
                k0.push(arr[a].groupid);
                if (arr[a].permission == '3') {
                    k3.push(arr[a])
                } else if (arr[a].permission == '2') {
                    k4.push(arr[a])
                } else {
                    k5.push(arr[a])
                }
            }
            vue1.k3 = k3;
            vue1.k4 = k4;
            vue1.k5 = k5;
            for (var b = 0; b < vue1.grou.length; b++) {
                if (k0.indexOf(vue1.grou[b].groupid) >= 0) {

                } else {
                    k1.push(vue1.grou[b])
                }
            }
            vue1.k6 = k1;
        },
        j1: function () {
            if (vue1.k7.length > 0) {
                var arr = [];
                for (var a = 0; a < vue1.k7.length; a++) {
                    vue1.k3.push(vue1.k6[vue1.k7[a]]);
                }
                for (var n = 0; n < vue1.k6.length; n++) {
                    if (vue1.k7.indexOf(n) >= 0) {

                    } else {
                        arr.push(vue1.k6[n])
                    }
                }
                vue1.k6 = arr;
                vue1.k7 = []
            }
        },
        j2: function () {
            if (vue1.k7.length > 0) {
                var arr = [];
                for (var a = 0; a < vue1.k7.length; a++) {
                    vue1.k4.push(vue1.k6[vue1.k7[a]]);
                }
                for (var n = 0; n < vue1.k6.length; n++) {
                    if (vue1.k7.indexOf(n) >= 0) {

                    } else {
                        arr.push(vue1.k6[n])
                    }
                }
                vue1.k6 = arr;
                vue1.k7 = []

            }
        },
        j3: function () {
            if (vue1.k7.length > 0) {
                var arr = [];
                for (var a = 0; a < vue1.k7.length; a++) {
                    vue1.k5.push(vue1.k6[vue1.k7[a]]);
                }
                for (var n = 0; n < vue1.k6.length; n++) {
                    if (vue1.k7.indexOf(n) >= 0) {

                    } else {
                        arr.push(vue1.k6[n])
                    }
                }
                vue1.k6 = arr;
                vue1.k7 = []
            }
        },
        e1: function () {
            if (vue1.e1_.length > 0) {
                var arr = [];
                for (var a = 0; a < vue1.e1_.length; a++) {
                    vue1.k6.push(vue1.k3[vue1.e1_[a]])
                }
                for (var n = 0; n < vue1.k3.length; n++) {
                    if (vue1.e1_.indexOf(n) >= 0) {

                    } else {
                        arr.push(vue1.k3[n])
                    }
                }
                vue1.k3 = arr;
                vue1.e1_ = [];
            }

        },
        e2: function () {
            if (vue1.e2_.length > 0) {
                var arr = [];
                for (var a = 0; a < vue1.e2_.length; a++) {
                    vue1.k6.push(vue1.k4[vue1.e2_[a]])
                }
                for (var n = 0; n < vue1.k4.length; n++) {
                    if (vue1.e2_.indexOf(n) >= 0) {

                    } else {
                        arr.push(vue1.k4[n])
                    }
                }
                vue1.k4 = arr;
                vue1.e2_ = []
            }
        },
        e3: function () {
            if (vue1.e3_.length > 0) {
                var arr = [];
                for (var a = 0; a < vue1.e3_.length; a++) {
                    vue1.k6.push(vue1.k5[vue1.e3_[a]])
                }
                for (var n = 0; n < vue1.k5.length; n++) {
                    if (vue1.e3_.indexOf(n) >= 0) {

                    } else {
                        arr.push(vue1.k5[n])
                    }
                }
                vue1.k5 = arr;
                vue1.e3_ = []
            }
        },
        ji: function () {
            if (vue1.k1) {
                var a1 = [], a2 = [], a3 = [];

                function pu(a, b) {
                    if (b.length > 0) {
                        for (var i = 0; i < b.length; i++) {
                            a.push(b[i].groupid)
                        }
                    }
                };
                pu(a1, vue1.k3);
                pu(a2, vue1.k4);
                pu(a3, vue1.k5);
                console.log(a1);
                console.log(a2);
                console.log(a3);
                $.ajax({
                    type: 'post',
                    url: '/monitor/updateUserGroup',
                    traditional: true,
                    data: {
                        id: vue1.groupid,
                        name: vue1.k1,
                        userStatus: vue1.k2,
                        read_write: a1,
                        read_only: a2,
                        deny: a3
                    },
                    dataType: 'json',
                    success: function (data) {
                        console.log(data);
                        if (data) {
                            //alert('修改成功');
                            layui.use(['layer', 'form'], function () {
                                var layer = layui.layer
                                    , form = layui.form;
                                layer.alert('修改成功', {icon: 6});
                            });
                            $.post("/monitor/getUserGroup", function (data) {
                                vue1.usersData = data;
                            });
                        }
                    }
                });
            } else {
                //alert('用户群组名称为必填项')
                layui.use(['layer', 'form'], function () {
                    var layer = layui.layer
                        , form = layui.form;
                    layer.alert('用户群组名称为必填项', {icon: 7});
                });
            }
        },
        checkedAll: function (event) {
            var _this = this;
            if (!event.currentTarget.checked) {
                this.userModel = [];
            } else { //实现全选
                _this.userModel = [];
                _this.userData.forEach(function (item, i) {
                    _this.userModel.push(i);
                });
            }
        },
        chall: function (event) {
            var _this = this;
            if (!event.currentTarget.checked) {
                this.chin = [];
            } else { //实现全选
                _this.chin = [];
                _this.usersData.forEach(function (item, i) {
                    _this.chin.push(i);
                });
            }
        },
        refe: function () {
            var _this = this;
            $.post("/monitor/getUserGroup", function (data) {
                _this.usersData = data;
                console.log(data);
            });
        },
        dele: function () {
            var _this = this;
            var arr = [], id = [];
            var len = _this.usersData.length;
            for (var i = 0; i < len; i++) {
                if (_this.chin.indexOf(i) >= 0) {

                } else {
                    arr.push(_this.usersData[i])
                }
            }
            for (var n = 0; n < _this.chin.length; n++) {
                id.push(_this.usersData[_this.chin[n]].usrgrpid)
            }
            console.log(id);
            if (id.length > 0) {
                $.ajax({
                    type: 'post',
                    url: '/monitor/deleteUserGroup',
                    traditional: true,
                    data: {usergroupid: id},
                    dataType: 'json',
                    success: function (data) {
                        console.log(data);
                        if (data.msg == '返回成功结果') {
                            //alert('删除成功');
                            layui.use(['layer', 'form'], function () {
                                var layer = layui.layer
                                    , form = layui.form;
                                layer.alert('删除成功', {icon: 6});
                            });
                            _this.usersData = arr;
                            _this.chin = [];
                            $('#confirm2').modal('hide')
                        } else {
                            //alert(data.data)
                            layui.use(['layer', 'form'], function () {
                                var layer = layui.layer
                                    , form = layui.form;
                                layer.alert(data.data, {icon: 7});
                            });
                        }
                    }
                });
            } else {
                //alert('请选择要删除的用户组')
                layui.use(['layer', 'form'], function () {
                    var layer = layui.layer
                        , form = layui.form;
                    layer.alert('请选择要删除的用户组', {icon: 7});
                });
            }
        },
        radioClick: function () {
            if (vue1.changed == '0') {
                vue1.sh = true
            } else {
                vue1.sh = false
            }
        },
        re: function () {
            var _this = this;
            $.post("/monitor/getUsers", function (data) {
                _this.userData = data;
            });
        },
        deleteUsersById: function () {
            var _this = this;
            var arr = [], id = [];
            var len = _this.userData.length;
            for (var i = 0; i < len; i++) {
                if (_this.userModel.indexOf(i) >= 0) {

                } else {
                    arr.push(_this.userData[i])
                }
            }
            for (var n = 0; n < _this.userModel.length; n++) {
                id.push(_this.userData[_this.userModel[n]].userid)
            }
            ;
            console.log(id);
            if (id.length > 0) {
                $.ajax({
                    type: 'post',
                    url: '/monitor/deleteUsersById',
                    traditional: true,
                    data: {ids: id},
                    dataType: 'json',
                    success: function (data) {
                        console.log(data);
                        if (data.code = 200) {
                            //alert('删除成功');
                            layui.use(['layer', 'form'], function () {
                                var layer = layui.layer
                                    , form = layui.form;
                                layer.alert('删除成功', {icon: 6});
                            });
                            $('#userDel').modal('hide');
                            _this.userData = arr;
                            _this.userModel = [];
                        } else {
                            //alert('删除失败')
                            layui.use(['layer', 'form'], function () {
                                var layer = layui.layer
                                    , form = layui.form;
                                layer.alert('删除失败', {icon: 5});
                            });
                        }
                    },
                    error: function () {
                        //alert('error')
                        layui.use(['layer', 'form'], function () {
                            var layer = layui.layer
                                , form = layui.form;
                            layer.alert('error', {icon: 5});
                        });
                    }
                });
            } else {
                //alert('请选择要删除的用户')
                layui.use(['layer', 'form'], function () {
                    var layer = layui.layer
                        , form = layui.form;
                    layer.alert('请选择要删除的用户', {icon: 7});
                });
            }
        },
        subm: function () {
            console.log(vue1.email);
            console.log(vue1.message);
            console.log(vue1.wechat);
            var ar = [], id = '', arr = [];
            for (var a = 0; a < vue1.uss2.length; a++) {
                ar.push(vue1.uss2[a].usrgrpid);
                id = ar.toString()
            }
            console.log(id);
            var arr = [];
            if (vue1.email) {
                arr.push({
                    active: vue1.changed,
                    severity: $('#ale').val(),
                })
            } else if (vue1.message) {

            } else if (vue1.wechat) {

            }
            if (ar.length > 0) {
                if (vue1.np || vue1.us) {
                    //alert('请确认用户名与密码');
                    layui.use(['layer', 'form'], function () {
                        var layer = layui.layer
                            , form = layui.form;
                        layer.alert('请确认用户名与密码', {icon: 7});
                    });
                } else {
                    var str = {
                        type: $('#zabbixUser').val(),
                        "alias": $('#userName').val(),
                        "passwordid": $('#pwd').val(),
                        "nickname": $('#name').val(),
                        // "deptid": "5",
                        "phone": $('#tel').val(),
                        // "jobid": "6",
                        "sendto": $('#em').val(),
                        "active": vue1.changed,
                        "period": $('#test9').val(),
                        "severity": $('#ale').val(),
                        'groupid': id,
                        'email': vue1.email,
                        'message': vue1.message,
                        'wechat': vue1.wechat
                    };
                    console.log(JSON.stringify(str));
                    $.ajax({
                        type: 'post',
                        url: '/monitor/createUser',
                        traditional: true,
                        contentType: 'application/json',
                        data: JSON.stringify(str),
                        dataType: 'json',
                        success: function (data) {
                            console.log(data);
                            if (data[0] == '创建成功') {
                                //alert('新建成功');
                                layui.use(['layer', 'form'], function () {
                                    var layer = layui.layer
                                        , form = layui.form;
                                    layer.alert('新建成功', {icon: 6});
                                });
                                $('#myModa2').modal('hide');
                                $.post("/monitor/getUsers", function (data) {
                                    vue1.userData = data;
                                });
                            } else {
                                //alert('新建失败');
                                layui.use(['layer', 'form'], function () {
                                    var layer = layui.layer
                                        , form = layui.form;
                                    layer.alert('新建失败', {icon: 6});
                                });
                            }
                        }
                    });
                }
            } else {
                //alert('必须绑定用户组')
                layui.use(['layer', 'form'], function () {
                    var layer = layui.layer
                        , form = layui.form;
                    layer.alert('必须绑定用户组', {icon: 7});
                });
            }
        },
        edit: function (id) {
            console.log(vue1.sea[id]);
            vue1.subid = vue1.sea[id].id;
            vue1.m1 = vue1.sea[id].type;
            vue1.m7 = vue1.sea[id].alias;
            vue1.m2 = vue1.sea[id].nickname;
            vue1.m3 = vue1.sea[id].phone;
            vue1.m4 = vue1.sea[id].sendto;
            vue1.m5 = vue1.sea[id].passwordid;
            vue1.m6 = vue1.sea[id].passwordid;
            vue1.changed2 = vue1.sea[id].active;
            vue1.test9 = vue1.sea[id].period;
            vue1.m8 = vue1.sea[id].severity;
            vue1.ch1 = JSON.parse(vue1.sea[id].email);
            vue1.ch2 = JSON.parse(vue1.sea[id].message);
            vue1.ch3 = JSON.parse(vue1.sea[id].wechat);
            vue1.userid = vue1.sea[id].userid;
            var arr = [], array = vue1.sea[id].groupid.split(","), d = [], arr2 = [];
            for (var a = 0; a < vue1.usersData.length; a++) {
                arr.push(vue1.usersData[a].usrgrpid);
            }
            for (var b = 0; b < array.length; b++) {
                if (arr.indexOf(array[b]) >= 0) {
                    arr2.push(arr.indexOf(array[b]));
                }
            }
            for (var c = 0; c < arr2.length; c++) {
                d.push(vue1.usersData[arr2[c]])
            }
            vue1.userg = d;
        },
        sub1: function () {
            console.log(vue1.userid);
            var ar = [], id = '';
            for (var a = 0; a < vue1.userg.length; a++) {
                ar.push(vue1.userg[a].usrgrpid);
                id = ar.toString()
            }
            if (ar.length > 0) {
                var obj = {
                    "type": vue1.m1,
                    "alias": vue1.m7,
                    "passwordid": vue1.m6,
                    "nickname": vue1.m2,
                    "phone": vue1.m3,
                    "sendto": vue1.m4,
                    "active": vue1.changed2,
                    "period": vue1.test9,
                    "severity": vue1.m8,
                    'userid': vue1.userid,
                    'groupid': id,
                    'email': vue1.ch1,
                    'message': vue1.ch2,
                    'wechat': vue1.ch3,
                    'id':vue1.subid
                };
                console.log(obj);
                $.ajax({
                    type: 'post',
                    url: '/monitor/updateUserById',
                    traditional: true,
                    contentType: 'application/json',
                    data: JSON.stringify(obj),
                    dataType: 'json',
                    success: function (data) {
                        console.log(data);
                        if (JSON.parse(data).result) {
                            //alert('修改成功');
                            layui.use(['layer', 'form'], function () {
                                var layer = layui.layer
                                    , form = layui.form;
                                layer.alert('修改成功', {icon: 6});
                            });
                            $('#edit').modal('hide');
                            $.post("/monitor/getUsers", function (data) {
                                vue1.userData = data;
                            });
                        } else {
                            alert(JSON.parse(data).error.data);
                        }
                    }
                });
            } else {
                //alert('主机组必选')
                layui.use(['layer', 'form'], function () {
                    var layer = layui.layer
                        , form = layui.form;
                    layer.alert('用户组必选', {icon: 7});
                });
            }
        },
        s1: function () {
            if (vue1.sl1.length > 0) {
                var arr = [];
                console.log(vue1.sl1);
                for (var i = 0; i < vue1.sl1.length; i++) {
                    vue1.se1.push(vue1.sea3[vue1.sl1[i]])
                }
                for (var n = 0; n < vue1.sea3.length; n++) {
                    if (vue1.sl1.indexOf(n) >= 0) {

                    } else {
                        arr.push(vue1.sea3[n])
                    }
                }
                vue1.grou = arr;
                vue1.sl1 = [];
            }
        },
        s2: function () {
            if (vue1.sl1.length > 0) {
                var arr = [];
                console.log(vue1.sl1);
                for (var i = 0; i < vue1.sl1.length; i++) {
                    vue1.se2.push(vue1.sea3[vue1.sl1[i]])
                }
                for (var n = 0; n < vue1.sea3.length; n++) {
                    if (vue1.sl1.indexOf(n) >= 0) {

                    } else {
                        arr.push(vue1.sea3[n])
                    }
                }
                vue1.grou = arr;
                vue1.sl1 = [];
            }
        },
        s3: function () {
            if (vue1.sl1.length > 0) {
                var arr = [];
                console.log(vue1.sl1);
                for (var i = 0; i < vue1.sl1.length; i++) {
                    vue1.se3.push(vue1.sea3[vue1.sl1[i]])
                }
                for (var n = 0; n < vue1.sea3.length; n++) {
                    if (vue1.sl1.indexOf(n) >= 0) {

                    } else {
                        arr.push(vue1.sea3[n])
                    }
                }
                vue1.grou = arr;
                vue1.sl1 = [];
            }
        },
        d1: function () {
            if (vue1.v1.length > 0) {
                var arr = [];
                for (var i = 0; i < vue1.v1.length; i++) {
                    vue1.grou.push(vue1.se1[vue1.v1[i]])
                }
                for (var n = 0; n < vue1.se1.length; n++) {
                    if (vue1.v1.indexOf(n) >= 0) {

                    } else {
                        arr.push(vue1.se1[n])
                    }
                }
                vue1.se1 = arr;
                vue1.v1 = [];
            }
        },
        d2: function () {
            if (vue1.v2.length > 0) {
                var arr = [];
                for (var i = 0; i < vue1.v2.length; i++) {
                    vue1.grou.push(vue1.se2[vue1.v2[i]])
                }
                for (var n = 0; n < vue1.se2.length; n++) {
                    if (vue1.v2.indexOf(n) >= 0) {

                    } else {
                        arr.push(vue1.se2[n])
                    }
                }
                vue1.se2 = arr;
                vue1.v2 = [];
            }
        },
        d3: function () {
            if (vue1.v3.length > 0) {
                var arr = [];
                for (var i = 0; i < vue1.v3.length; i++) {
                    vue1.grou.push(vue1.se3[vue1.v3[i]])
                }
                for (var n = 0; n < vue1.se3.length; n++) {
                    if (vue1.v3.indexOf(n) >= 0) {

                    } else {
                        arr.push(vue1.se3[n])
                    }
                }
                vue1.se3 = arr;
                vue1.v3 = [];
            }
        },
        ti: function () {
            if (vue1.n1) {
                var a1 = [], a2 = [], a3 = [];

                function pu(a, b) {
                    if (b.length > 0) {
                        for (var i = 0; i < b.length; i++) {
                            a.push(b[i].groupid)
                        }
                    }
                };
                pu(a1, vue1.se1);
                pu(a2, vue1.se2);
                pu(a3, vue1.se3);
                console.log(a1);
                console.log(a2);
                console.log(a3);
                $.ajax({
                    type: 'post',
                    url: '/monitor/createUserGroup',
                    traditional: true,
                    data: {userGroupName: vue1.n1, userStatus: vue1.n2, read_write: a1, read_only: a2, deny: a3},
                    dataType: 'json',
                    success: function (data) {
                        console.log(data);
                        if (data) {
                            //alert(data);
                            //if(data='User group "test" already exists.'){
                            //    layui.use(['layer', 'form'], function () {
                            //        var layer = layui.layer
                            //            , form = layui.form;
                            //        layer.alert('用户组已存在', {icon: 7});
                            //});
                            //}else{
                            layui.use(['layer', 'form'], function () {
                                var layer = layui.layer
                                    , form = layui.form;
                                layer.alert(data, {icon: 7});
                            });
                            //}
                            vue1.n1 = '';
                            vue1.n2 = '0';
                            vue1.se1 = [];
                            vue1.se2 = [];
                            vue1.se3 = [];
                            $.post("/monitor/getUserGroup", function (data) {
                                vue1.usersData = data;
                            });
                        }
                    }
                });
            } else {
                //alert('用户群组名称为必填项')
                layui.use(['layer', 'form'], function () {
                    var layer = layui.layer
                        , form = layui.form;
                    layer.alert('用户群组名称为必填项', {icon: 7});
                });
            }
        }
    },
    watch: {
        'np2': function (a) {
            if (a) {
                if (vue1.np1 != a) {
                    vue1.np = true
                } else {
                    vue1.np = false
                }
            } else {
                vue1.np = true
            }
        },
        'np1': function (a) {
            if (a) {
                if (vue1.np2 != a) {
                    vue1.np = true
                } else {
                    vue1.np = false
                }
            } else {
                vue1.np = true
            }
        },
        'm5': function (a) {
            if (a) {
                if (vue1.m6 != a) {
                    vue1.np_ = true
                } else {
                    vue1.np_ = false
                }
            } else {
                vue1.np_ = true
            }
        },
        'm6': function (a) {
            if (a) {
                if (vue1.m5 != a) {
                    vue1.np_ = true
                } else {
                    vue1.np_ = false
                }
            } else {
                vue1.np_ = true
            }
        },
        'u': function (a) {
            if (a) {
                vue1.us = false
            } else {
                vue1.us = true
            }
        },
        'm7': function (a) {
            if (a) {
                vue1.us2 = false
            } else {
                vue1.us2 = true
            }
        },
        'changed2': function (a) {
            if (a) {
                if (a == "0") {
                    vue1.sh2 = true
                } else {
                    vue1.sh2 = false
                }
            }
        }
    },
    computed: {
        sea: function () {
            var search = this.search.toLowerCase();
            if (search) {
                return this.userData.filter(function (product) {
                    return Object.keys(product).some(function (key) {
                        return String(product[key]).toLowerCase().indexOf(search) > -1
                    })
                })
            }
            return this.userData;
        },
        sea2: function () {
            var search = this.groupSearch.toLowerCase();
            if (search) {
                return this.usersData.filter(function (product) {
                    return Object.keys(product).some(function (key) {
                        return String(product[key]).toLowerCase().indexOf(search) > -1
                    })
                })
            }
            return this.usersData;
        },
        sea3: function () {
            var search = this.groupSearch2.toLowerCase();
            if (search) {
                return this.grou.filter(function (product) {
                    return Object.keys(product).some(function (key) {
                        return String(product[key]).toLowerCase().indexOf(search) > -1
                    })
                })
            }
            return this.grou;
        }
    }
});
var vue2 = new Vue({
    el: '#a1',
    data: {
        group: '',
        checkboxModel: [],
        checked: false,
        search: '',
        host: '',
        hostModel: [],
        hostChecked: false,
        hostSearch: '',
        ho: '',
        ho2: '',
        groupid: '',
        groupName: '',
        groupStyle: '',
        hoModel: '',
        ho2Model: ''
    },
    beforeCreate: function () {
        var _this = this;
        $.post("/monitor/hostGroupGet", function (data) {
            _this.group = data;
        });
        $.post("/monitor/hostGetAll", function (data) {
            _this.host = data;
        });
    },
    watch: {//深度 watcher
        'checkboxModel': {
            handler: function (val, oldVal) {
                if (this.checkboxModel.length === this.group.length) {
                    this.checked = true;
                } else {
                    this.checked = false;
                }
            },
            deep: true
        },
        'hostModel': {
            handler: function (val, oldVal) {
                if (this.hostModel.length === this.host.length) {
                    this.hostChecked = true;
                } else {
                    this.hostChecked = false;
                }
            },
            deep: true
        }
    },
    methods: {
        checkedAll: function (event) {
            var _this = this;
            if (!event.currentTarget.checked) {
                this.checkboxModel = [];
            } else { //实现全选
                _this.checkboxModel = [];
                _this.group.forEach(function (item, i) {
                    _this.checkboxModel.push(i);
                });
            }
        },
        del: function () {
            console.log(vue2.checkboxModel);
            if (vue2.checkboxModel.length > 0) {
                var _this = this;
                var arr = [], id = [];
                var len = _this.searchData.length;
                for (var i = 0; i < len; i++) {
                    if (_this.checkboxModel.indexOf(i) >= 0) {

                    } else {
                        arr.push(_this.searchData[i])
                    }
                }
                for (var n = 0; n < _this.checkboxModel.length; n++) {
                    id.push(_this.searchData[_this.checkboxModel[n]].id)
                }
                $.ajax({
                    type: 'post',
                    url: '/monitor/hostGroupDelete',
                    traditional: true,
                    data: {hostgroupid: id},
                    dataType: 'json',
                    success: function (data) {
                        if (data.msg == '返回成功结果') {
                            //alert('删除成功')
                            layui.use(['layer', 'form'], function () {
                                var layer = layui.layer
                                    , form = layui.form;
                                layer.alert('删除成功', {icon: 6});
                            });
                        } else {
                            alert(data.data)
                        }
                    }
                });
                vue2.group = arr;
                vue2.checkboxModel = [];
            } else {
                //alert('请选择要删除的主机组')
                layui.use(['layer', 'form'], function () {
                    var layer = layui.layer
                        , form = layui.form;
                    layer.alert('请选择要删除的主机组', {icon: 7});
                });
            }
        },
        sinDel: function (r, d) {
            var _this = this;
            var arr = [];
            arr.push(d);
            $.ajax({
                type: 'post',
                url: '/monitor/hostGroupDelete',
                traditional: true,
                data: {hostgroupid: arr},
                dataType: 'json',
                success: function (data) {
                    console.log(data);
                    if (data.msg == '返回成功结果') {
                        //alert('删除成功');
                        layui.use(['layer', 'form'], function () {
                            var layer = layui.layer
                                , form = layui.form;
                            layer.alert('删除成功', {icon: 6});
                        });
                        if (_this.checkboxModel.indexOf(r) >= 0) {
                            _this.searchData.splice(r, 1);
                            _this.checkboxModel = [];
                        }
                    } else {
                        //alert(data.data)
                        layui.use(['layer', 'form'], function () {
                            var layer = layui.layer
                                , form = layui.form;
                            layer.alert(data.data, {icon: 7});
                        });
                    }
                }
            })
        },
        refresh: function () {
            var _this = this;
            $.post("/monitor/hostGroupGet", function (data) {
                _this.group = data
            });
        },
        hostClick: function (event) {
            var _this = this;
            if (!event.currentTarget.checked) {
                _this.hostModel = [];
                console.log(_this.hostModel)
            } else { //实现全选
                _this.hostModel = [];
                _this.host.forEach(function (item, i) {
                    _this.hostModel.push(i);
                });
            }
        },
        bind: function (id) {
            var _this = this;
            _this.groupid = id;
            console.log(id);
            $.ajax({
                type: 'post',
                url: '/monitor/hostGet',
                data: {hostGroupId: id},
                dataType: 'text',
                success: function (da) {
                    console.log(JSON.parse(da));
                    console.log(vue2.host);
                    var data = data = JSON.parse(da);
                    var key = Object.keys(data), val = Object.values(data);
                    var arr = [], arr2 = [];
                    for (var a = 0; a < key.length; a++) {
                        arr[a] = {id: key[a], name: val[a]}
                    }
                    vue2.ho = arr;
                    console.log(arr);
                    for (var b = 0; b < vue2.host.length; b++) {
                        if (key.indexOf((vue2.host[b].id).toString()) >= 0) {

                        } else {
                            arr2.push(vue2.host[b])
                        }
                    }
                    vue2.ho2 = arr2;
                }
            });
        },
        binds: function (a, b, c, i, id) {
            console.log(a);
            console.log(b);
            console.log(c);
            var arr = [], arr2 = [];
            for (var d = 0; d < a.length; d++) {
                arr.push(parseInt(b[a[d]].id));
                // c.push(b[a[d]]);
            }
            console.log(arr);
            for (var e = 0; e < b.length; e++) {
                if (arr.indexOf(parseInt(b[e].id)) >= 0) {

                } else {
                    arr2.push(b[e])
                }
            }
            console.log(arr2);
            console.log(i);
            if (i == 1) {
                vue2.ho = arr2;
                $.ajax({
                    type: 'post',
                    url: '/monitor/massremove',
                    traditional: true,
                    data: {hostGroupId: id, hostIds: arr},
                    dataType: 'json',
                    success: function (data) {
                        console.log(JSON.parse(data));
                        if (JSON.parse(data).groupids) {
                            //alert('修改成功');
                            layui.use(['layer', 'form'], function () {
                                var layer = layui.layer
                                    , form = layui.form;
                                layer.alert('修改成功', {icon: 6});
                            });
                            for (var d = 0; d < a.length; d++) {
                                c.push(b[a[d]]);
                            }
                        } else {
                            //alert(JSON.parse(data).data)
                            layui.use(['layer', 'form'], function () {
                                var layer = layui.layer
                                    , form = layui.form;
                                layer.alert(JSON.parse(data).data, {icon: 7});
                            });
                        }
                    }
                });
            } else {
                vue2.ho2 = arr2;
                $.ajax({
                    type: 'post',
                    url: '/monitor/massAdd',
                    traditional: true,
                    data: {hostGroupId: id, hostIds: arr},
                    dataType: 'json',
                    success: function (data) {
                        console.log(JSON.parse(data));
                        if (JSON.parse(data).result) {
                            //alert('修改成功');
                            layui.use(['layer', 'form'], function () {
                                var layer = layui.layer
                                    , form = layui.form;
                                layer.alert('修改成功', {icon: 6});
                            });
                            for (var d = 0; d < a.length; d++) {
                                c.push(b[a[d]]);
                            }
                        } else {
                            //alert(JSON.parse(data).error.data)
                            layui.use(['layer', 'form'], function () {
                                var layer = layui.layer
                                    , form = layui.form;
                                layer.alert(JSON.parse(data).data, {icon: 7});
                            });
                        }
                    }
                });
            }
            vue2.hoModel = [];
            vue2.ho2Model = [];
        },
        newGroup: function () {
            var _this = this, arr = [];
            for (var a = 0; a < _this.hostModel.length; a++) {
                arr.push(_this.host[_this.hostModel[a]].id)
            }
            if (vue2.groupName && vue2.groupStyle) {
                if (arr.length > 0) {
                    $.post("/monitor/hostGroupcreate", {
                        name: vue2.groupName,
                        describe: vue2.groupStyle
                    }, function (data) {
                        console.log(data);
                        if (data.result) {
                            $.ajax({//绑定
                                type: 'post',
                                url: '/monitor/massAdd',
                                traditional: true,
                                data: {hostGroupId: data.result.groupids[0], hostIds: arr},
                                dataType: 'json',
                                success: function (data2) {
                                    console.log(data2);
                                    if (JSON.parse(data2).result) {
                                        //alert('新建分组成功');
                                        layui.use(['layer', 'form'], function () {
                                            var layer = layui.layer
                                                , form = layui.form;
                                            layer.alert('新建分组成功', {icon: 6});
                                        });
                                        _this.hostModel = [];
                                        vue2.groupName = '';
                                        vue2.groupStyle = '';
                                        $.post("/monitor/hostGroupGet", function (data) {
                                            vue2.group = data
                                        });
                                        $('#grouping1').modal('hide');
                                    } else {
                                        //alert(JSON.parse(data2).error)
                                        layui.use(['layer', 'form'], function () {
                                            var layer = layui.layer
                                                , form = layui.form;
                                            layer.alert(JSON.parse(data2).error, {icon: 7});
                                        });
                                    }
                                }
                            })
                        } else {
                            //alert(data.error)
                            layui.use(['layer', 'form'], function () {
                                var layer = layui.layer
                                    , form = layui.form;
                                layer.alert(data.error, {icon: 7});
                            });
                        }

                    });
                } else {
                    $.post("/monitor/hostGroupcreate", {
                        name: vue2.groupName,
                        describe: vue2.groupStyle
                    }, function (data) {
                        if (typeof data.result.groupids[0] == 'number') {
                            //alert('新建分组成功');
                            layui.use(['layer', 'form'], function () {
                                var layer = layui.layer
                                    , form = layui.form;
                                layer.alert('新建分组成功', {icon: 6});
                            });
                            _this.hostModel = [];
                            vue2.groupName = '';
                            vue2.groupStyle = '';
                            $.post("/monitor/hostGroupGet", function (data) {
                                vue2.group = data
                            });
                        } else {
                            //alert('新建分组失败')
                            layui.use(['layer', 'form'], function () {
                                var layer = layui.layer
                                    , form = layui.form;
                                layer.alert('新建分组失败', {icon: 5});
                            });
                        }
                    })
                }
            } else {
                //alert('分组名称与分组描述必填')
                layui.use(['layer', 'form'], function () {
                    var layer = layui.layer
                        , form = layui.form;
                    layer.alert('分组名称与分组描述必填', {icon: 7});
                });
            }
        }
    },
    computed: {
        searchData: function () {
            var search = this.search.toLowerCase();
            if (search) {
                return this.group.filter(function (product) {
                    return Object.keys(product).some(function (key) {
                        return String(product[key]).toLowerCase().indexOf(search) > -1
                    })
                })
            }
            return this.group;
        },
        searchHost: function () {
            var hostSearch = this.hostSearch.toLowerCase();
            if (hostSearch) {
                return this.host.filter(function (product) {
                    return Object.keys(product).some(function (key) {
                        return String(product[key]).toLowerCase().indexOf(hostSearch) > -1
                    })
                })
            }
            return this.host;
        }
    }
});
var vue3 = new Vue({
    el: '#a3',
    data: {
        mo: '',
        search: '',
        search2: '',
        zi: '',
        ho: '',
        ind: '',
        cha: '0',
        tr: true,
        tr2: false,
        da1: '',
        da2: '',
        da3: '',
        da4: '',
        da5: '',
        da6: '',
        da7: '',
        da8: '',
        z1: true,
        z2: false,
        radi: '0',
        q1: '0',
        q2: '0',
        q3: '',
        q4: '故障{TRIGGER.STATUS},服务器:{HOSTNAME1}发生: {TRIGGER.NAME}故障!',
        q5: '恢复{TRIGGER.STATUS}, 服务器:{HOSTNAME1}: {TRIGGER.NAME}已恢复!',
        us: '',
        usg: '',
        select1: '',
        select2: '',
        us1: [[]],
        usg1: [[]],
        min: '',
        usid: [],
        usgin: [],
        action: '',
        r1: false,
        r2: [],
        edse: '',
        t1: '',
        t2: '',
        t3: '',
        t4: '',
        t5: '',
        t6: '',
        t7: '',
        t8: '',
        t9: '',
        t10: '',
        t11: '',
        actionid: '',
        createTrigger: [],
        actionShow: false,
        hostName: '',
        hostGroupName: '',
        hostGroupModel: '',
        hostModel: '',
        performactions: [0],
        item: [],
        userItem: [],
        performIndex: ''
    },
    beforeCreate: function () {
        var _this = this;

        function unique(arr) {
            var res = [];
            var json = {};
            for (var i = 0; i < arr.length; i++) {
                if (!json[arr[i]]) {
                    res.push(arr[i]);
                    json[arr[i]] = 1;
                }
            }
            return res;
        }

        $.post("/monitor/hostGroup", function (data) {
            _this.mo = data;
            console.log(data);
        });
        $.post("/monitor/host", function (data) {
            _this.ho = data;
            console.log(data);
        });
        $.post("/monitor/getUsers", function (data) {
            _this.us = data;
            console.log(vue3.us);
        });
        $.post("/monitor/getUserGroup", function (data) {
            _this.usg = data;
            console.log(vue3.usg);
        });
        $.post("/monitor/getAction", function (data) {
            var d = JSON.parse(JSON.parse(data)), hostid = [], hostGroupid = [], host = [], hostGroup = [];
            for (var a = 0; a < d.length; a++) {
                for (var b = 0; b < d[a].filter.conditions.length; b++) {
                    if (d[a].filter.conditions[b].conditiontype == '0') {
                        hostGroupid.push(d[a].filter.conditions[b].value);
                    }
                    if (d[a].filter.conditions[b].conditiontype == '1') {
                        hostid.push(d[a].filter.conditions[b].value);
                    }
                }
            }
            unique(hostid).sort();
            unique(hostGroupid).sort();
            for (var c = 0; c < _this.ho.length; c++) {
                if (unique(hostid).indexOf(_this.ho[c].hostid) >= 0) {
                    host.push(_this.ho[c].host)
                }
            }
            for (var e = 0; e < _this.mo.length; e++) {
                if (unique(hostGroupid).indexOf(_this.mo[e].groupid) >= 0) {
                    hostGroup.push(_this.mo[e].name)
                }
            }
            console.log(host);
            console.log(unique(hostid));
            console.log(hostGroup);
            console.log(unique(hostGroupid));
            for (var f = 0; f < d.length; f++) {
                d[f].hostname = [];
                d[f].hostGroupname = [];
                for (var g = 0; g < d[f].filter.conditions.length; g++) {
                    if (unique(hostid).indexOf(d[f].filter.conditions[g].value) >= 0) {
                        d[f].hostname.push(host[unique(hostid).indexOf(d[f].filter.conditions[g].value)]);
                    }
                    if (unique(hostGroupid).indexOf(d[f].filter.conditions[g].value) >= 0) {
                        d[f].hostGroupname.push(hostGroup[unique(hostGroupid).indexOf(d[f].filter.conditions[g].value)])
                    }
                }
            }
            console.log(d);
            _this.action = d;
        });
    },
    methods: {
        createTrugger: function () {
            vue3.performactions = [0];
            vue3.us1 = [[]];
            vue3.usg1 = [[]];
            vue3.item = [];
        },
        delData: function (data, i, t, u, ug) {
            data.splice(i, 1);
            t.splice(i, 1);
            u.splice(i, 1);
            ug.splice(i, 1);
        },
        addData: function (data, user, userGroup) {
            data.push(0);
            user.push([]);
            userGroup.push([]);
        },
        editGroup: function (d, i, s) {
            console.log(d);
            console.log(i);
            console.log(s);
            for (var a = 0; a < i.length; a++) {
                d.push(s[i[a]])
            }
        },
        delteme: function (i, data) {
            data.splice(i, 1)
        },
        delzi: function (i) {
            vue3.zi.splice(i, 1)
        },
        deleteActionById: function () {
            var _this = this;
            console.log(_this.sea3);
            console.log(_this.r2);
            var arr = [], id = [];
            var len = _this.sea3.length;
            for (var i = 0; i < len; i++) {
                if (_this.r2.indexOf(i) >= 0) {

                } else {
                    arr.push(_this.sea3[i])
                }
            }
            for (var n = 0; n < _this.r2.length; n++) {
                id.push(_this.sea3[_this.r2[n]].actionid)
            }
            console.log(arr);
            if (id.length > 0) {
                $.ajax({
                    type: 'post',
                    url: '/monitor/deleteActionById',
                    traditional: true,
                    data: {ids: id},
                    dataType: 'json',
                    success: function (data) {
                        console.log(data);
                        vue3.action = arr;
                        vue3.r2 = [];
                    }
                });
            } else {
                //alert('请选择要删除的监测器')
                layui.use(['layer', 'form'], function () {
                    var layer = layui.layer
                        , form = layui.form;
                    layer.alert('请选择要删除的监测器', {icon: 7});
                });
            }

        },
        checkedAll: function (event) {
            var _this = this;
            if (!event.currentTarget.checked) {
                this.r2 = [];
            } else { //实现全选
                _this.r2 = [];
                _this.sea3.forEach(function (item, i) {
                    _this.r2.push(i);
                });
            }
        },
        su: function () {
            var arr = [];
            arr.push(vue3.sea[$('#na').val()]);
            var groupid = vue3.sea[$('#na').val()].groupid;
            vue3.zi = arr;
            console.log(groupid);
            $.ajax({
                type: 'post',
                url: '/monitor/getItemByHostGroup',
                data: {hostGroup: groupid},
                dataType: 'json',
                success: function (data) {
                    if (data.length > 0) {
                        vue3.ind = data;
                    } else {
                        //alert('主机或主机组不包含监控项，请重新选择')
                        layui.use(['layer', 'form'], function () {
                            var layer = layui.layer
                                , form = layui.form;
                            layer.alert('主机或主机组不包含监控项，请重新选择', {icon: 7});
                        });
                    }
                }
                /*error: function () {
                    alert('主机组不包含监控项，请重新选择')
                }*/
            })
        },
        bm: function () {
            var arr = [], array = [];
            for (var i = 0; i < $('#na2').val().length; i++) {
                arr.push(vue3.sea2[$('#na2').val()[i]]);
                array.push(vue3.sea2[$('#na2').val()[i]].hostid)
            }
            vue3.zi = arr;
            $.ajax({
                type: 'post',
                url: '/monitor/getItemByHost',
                traditional: true,
                data: {hostArr: array},
                dataType: 'json',
                success: function (data) {
                    if (data.length > 0) {
                        vue3.ind = data;
                    } else {
                        //alert('主机或主机组不包含指标，请重新选择')
                        layui.use(['layer', 'form'], function () {
                            var layer = layui.layer
                                , form = layui.form;
                            layer.alert('主机或主机组不包含指标，请重新选择', {icon: 7});
                        });
                    }
                }
                /* error: function () {
                     alert('主机不包含监控项，请重新选择')
                 }*/
            })
        },
        rad: function () {
            if (vue3.cha == '0') {
                vue3.tr = true;
                vue3.tr2 = false;
            } else {
                vue3.tr = false;
                vue3.tr2 = true
            }
        },
        ra: function () {
            if (vue3.radi == '0') {
                vue3.z1 = true;
                vue3.z2 = false;
            } else {
                vue3.z1 = false;
                vue3.z2 = true
            }
        },
        st1: function () {
            var inp = '';
            if (vue3.cha == '0') {
                inp = vue3.da3
            } else {
                inp = vue3.da4
            }
            $.ajax({
                type: 'post',
                url: '/monitor/createTrigger',
                data: {
                    key: vue3.da1,
                    type: vue3.da2,
                    select: vue3.cha,
                    input: inp,
                    fuhao: vue3.da5,
                    number: vue3.da6,
                    alertLv: vue3.da7,
                    descript: vue3.da8
                },
                dataType: 'json',
                success: function (data) {
                    console.log(data);
                    vue3.actionShow = true;
                    var key = Object.keys(data), value = Object.values(data), valueArr = [], valueArr2 = [];
                    console.log(key);
                    console.log(value);
                    var obj = {
                        result: '成功',
                        error: '失败'
                    };
                    for (var a = 0; a < value.length; a++) {
                        if (JSON.parse(value[a]).error) {
                            valueArr2.push(JSON.parse(value[a]).error)
                        } else {
                            valueArr2.push(JSON.parse(value[a]).result)
                        }
                        valueArr[a] = {
                            host: key[a],
                            status: obj[Object.keys(JSON.parse(value[a]))[1]],
                            data: valueArr2[a]
                        };
                    }
                    console.log(valueArr);
                    vue3.createTrigger = valueArr
                }
            })
        },
        selectUser: function (i) {
            vue3.performIndex = i
        },
        c1: function (item, us, model, index) {
            for (var a = 0; a < model.length; a++) {
                item[index].push(us[model[a]])
            }
        },
        st2: function () {
            console.log(vue3.zi);
            console.log(vue3.createTrigger);
            var host = [], group = [];
            for (var p = 0; p < vue3.zi.length; p++) {
                if (vue3.zi[p].groupid) {
                    group.push(vue3.zi[p].groupid)
                } else {
                    host.push(vue3.zi[p].hostid)
                }
            }
            var arr = [], groupId = [], hostId = [];
            for (var i = 0; i < vue3.performactions.length; i++) {
                groupId[i] = [];
                hostId[i] = [];
                for (var k = 0; k < vue3.usg1[i].length; k++) {
                    groupId[i][k] = vue3.usg1[i][k].usrgrpid
                }
                for (var l = 0; l < vue3.us1[i].length; l++) {
                    hostId[i][l] = vue3.us1[i][l].userid
                }
            }
            for (var n = 0; n < vue3.createTrigger.length; n++) {
                if (vue3.createTrigger[n].data.triggerids) {
                    arr.push(vue3.createTrigger[n].data.triggerids[0])
                }
            }
            console.log(arr);
            var action = [];
            for (var j = 0; j < vue3.performactions.length; j++) {
                action[j] = {
                    time: vue3.item[j],
                    user: hostId[j],
                    usGroup: groupId[j],
                }
            }
            console.log(action);
            if (arr.length > 0) {
                var a = '', b = '', c = '', d = '', e = '', f = '', g = '', h = '';
                if (vue3.radi == '0') {
                    b = '故障{TRIGGER.STATUS},服务器:{HOSTNAME1}发生: {TRIGGER.NAME}故障!';
                    c = '恢复{TRIGGER.STATUS}, 服务器:{HOSTNAME1}: {TRIGGER.NAME}已恢复!';
                    d = '告警主机:{HOSTNAME1}\n' +
                        '告警时间:{EVENT.DATE} {EVENT.TIME}\n' +
                        '告警等级:{TRIGGER.SEVERITY}\n' +
                        '告警信息: {TRIGGER.NAME}\n' +
                        '告警项目:{TRIGGER.KEY1}\n' +
                        '问题详情:{ITEM.NAME}:{ITEM.VALUE}\n' +
                        '当前状态:{TRIGGER.STATUS}:{ITEM.VALUE1}\n' +
                        '事件ID:{EVENT.ID}';
                    e = '告警主机:{HOSTNAME1}\n' +
                        '告警时间:{EVENT.DATE} {EVENT.TIME}\n' +
                        '告警等级:{TRIGGER.SEVERITY}\n' +
                        '告警信息: {TRIGGER.NAME}\n' +
                        '告警项目:{TRIGGER.KEY1}\n' +
                        '问题详情:{ITEM.NAME}:{ITEM.VALUE}\n' +
                        '当前状态:{TRIGGER.STATUS}:{ITEM.VALUE1}\n' +
                        '事件ID:{EVENT.ID}';
                } else {
                    b = vue3.q4;
                    c = vue3.q5;
                    d = $('#tx1').val();
                    e = $('#tx2').val();
                }
                a = vue3.q3;
                f = vue3.q2;
                if (vue3.usid) {
                    g = vue3.usid
                } else {
                    g = []
                }
                if (vue3.usgid) {
                    h = vue3.usgid
                } else {
                    h = []
                }
                if (a && b && c && d && e && f) {
                    $.ajax({
                        type: 'post',
                        url: '/monitor/createAction',
                        traditional: true,
                        dataType: 'json',
                        data: {
                            name: a,
                            def_shortdata: b,
                            def_longdata: d,
                            status: f,
                            subject: c,
                            message: e,
                            groupIds: group,
                            hostIds: host,
                            triggerId: arr,
                            action: JSON.stringify(action)
                        },
                        success: function (data) {
                            console.log(JSON.parse(data));
                            if (JSON.parse(data).result) {
                                //alert('创建成功');
                                layui.use(['layer', 'form'], function () {
                                    var layer = layui.layer
                                        , form = layui.form;
                                    layer.alert('创建成功', {icon: 6});
                                });
                                $('#alertModel').modal('hide');
                                $.post("/monitor/getAction", function (data) {
                                    var d = JSON.parse(JSON.parse(data)), hostid = [], hostGroupid = [], host = [],
                                        hostGroup = [];
                                    for (var a = 0; a < d.length; a++) {
                                        for (var b = 0; b < d[a].filter.conditions.length; b++) {
                                            if (d[a].filter.conditions[b].conditiontype == '0') {
                                                hostGroupid.push(d[a].filter.conditions[b].value);
                                            }
                                            if (d[a].filter.conditions[b].conditiontype == '1') {
                                                hostid.push(d[a].filter.conditions[b].value);
                                            }
                                        }
                                    }
                                    hostid.sort();
                                    hostGroupid.sort();
                                    for (var c = 0; c < vue3.ho.length; c++) {
                                        if (hostid.indexOf(vue3.ho[c].hostid) >= 0) {
                                            host.push(vue3.ho[c].host)
                                        }
                                    }
                                    for (var e = 0; e < vue3.mo.length; e++) {
                                        if (hostGroupid.indexOf(vue3.mo[e].groupid) >= 0) {
                                            hostGroup.push(vue3.mo[e].name)
                                        }
                                    }
                                    console.log(host);
                                    console.log(hostid);
                                    console.log(hostGroup);
                                    console.log(hostGroupid);
                                    for (var f = 0; f < d.length; f++) {
                                        d[f].hostname = [];
                                        d[f].hostGroupname = [];
                                        for (var g = 0; g < d[f].filter.conditions.length; g++) {
                                            if (hostid.indexOf(d[f].filter.conditions[g].value) >= 0) {
                                                d[f].hostname.push(host[hostid.indexOf(d[f].filter.conditions[g].value)])
                                            }
                                            if (hostGroupid.indexOf(d[f].filter.conditions[g].value) >= 0) {
                                                d[f].hostGroupname.push(hostGroup[hostGroupid.indexOf(d[f].filter.conditions[g].value)])
                                            }
                                        }
                                    }
                                    console.log(d);
                                    vue3.action = d;
                                });
                            } else {
                                  layui.use(['layer', 'form'], function () {
                                      var layer = layui.layer
                                          , form = layui.form;
                                      layer.alert(JSON.parse(data).error.data, {icon: 7});
                                  });
                            }
                        }
                    })
                } else {
                    //alert('至少需要通知一个用户或用户组，其他均为必填项')
                    layui.use(['layer', 'form'], function () {
                        var layer = layui.layer
                            , form = layui.form;
                        layer.alert('至少需要通知一个用户或用户组，其他均为必填项', {icon: 7});
                    });
                }
            } else {
                //alert('定义告警条件失败')
                layui.use(['layer', 'form'], function () {
                    var layer = layui.layer
                        , form = layui.form;
                    layer.alert('定义告警条件失败', {icon: 5});
                });
            }
            a
        },
        dlecom: function (data, i) {
            data.splice(i, 1)
        },
        editji: function (index) {
            console.log(vue3.sea3[index]);

            function unique(arr) {
                var res = [];
                var json = {};
                for (var i = 0; i < arr.length; i++) {
                    if (!json[arr[i]]) {
                        res.push(arr[i]);
                        json[arr[i]] = 1;
                    }
                }
                return res;
            }

            var hosts = [], hostGroups = [], cfqid = [];
            for (var q = 0; q < vue3.mo.length; q++) {
                if (vue3.sea3[index].hostGroupname.indexOf(vue3.mo[q].name) >= 0) {
                    hostGroups.push({groupid: vue3.mo[q].groupid, name: vue3.mo[q].name})
                }
            }
            for (var w = 0; w < vue3.ho.length; w++) {
                if (vue3.sea3[index].hostname.indexOf(vue3.ho[w].host) >= 0) {
                    hosts.push({hostid: vue3.ho[w].hostid, host: vue3.ho[w].host})
                }
            }
            console.log(hosts);
            console.log(hostGroups);
            vue3.hostName = hosts;
            vue3.hostGroupName = hostGroups;
            vue3.textareaValue = vue3.sea3[index].hostGroupname;
            vue3.actionid = vue3.sea3[index].actionid;
            vue3.t1 = vue3.sea3[index].name;
            vue3.t2 = vue3.sea3[index].status;
            vue3.t3 = vue3.sea3[index].def_shortdata;
            vue3.t4 = vue3.sea3[index].r_shortdata;
            vue3.t5 = vue3.sea3[index].def_longdata;
            vue3.t6 = vue3.sea3[index].r_longdata;
            vue3.t7 = parseInt(vue3.sea3[index].esc_period) / 60;
            var usid = [], usgid = [], usarr = [], usgarr = [];
            vue3.performactions = vue3.sea3[index].operations;
            for (var c = 0; c < vue3.sea3[index].operations.length; c++) {
                vue3.item[c] = parseInt(vue3.sea3[index].operations[c].esc_period) / 60;
                vue3.us1[c] = vue3.sea3[index].operations[c].opmessage_usr;
                vue3.usg1[c] = vue3.sea3[index].operations[c].opmessage_usr;
                usid[c] = vue3.sea3[index].operations[c].opmessage_usr;
                usgid[c] = vue3.sea3[index].operations[c].opmessage_grp;
            }
            console.log(vue3.us);
            console.log(usid);
            console.log(usgid);
            for (var z = 0; z < vue3.sea3[index].operations.length; z++) {
                usarr[z] = [];
                usgarr[z] = [];
                for (var x = 0; x < usid[z].length; x++) {
                    for (var v = 0; v < vue3.us.length; v++) {
                        if (usid[z][x].userid == vue3.us[v].userid) {
                            usarr[z][x] = {userid: usid[z][x].userid, alias: vue3.us[v].alias}
                        }
                    }
                }
                for (var y = 0; y < usgid[z].length; y++) {
                    for (var r = 0; r < vue3.usg.length; r++) {
                        if (usgid[z][y].usrgrpid == vue3.usg[r].usrgrpid) {
                            usgarr[z][y] = {userid: usgid[z][y].usrgrpid, name: vue3.usg[r].name}
                        }
                    }
                }
            }
            for (var u = 0; u < vue3.sea3[index].operations.length; u++) {
                vue3.us1 = usarr;
                vue3.usg1 = usgarr;
            }
            console.log(usarr);
            console.log(usgarr)
        },
        c8: function () {
            var arr = [];
            for (var a = 0; a < vue3.t8.length; a++) {
                arr.push(vue3.us[vue3.t8[a]])
            }
            vue3.t11 = arr;
        },
        c9: function () {
            var arr = [];
            for (var a = 0; a < vue3.t9.length; a++) {
                arr.push(vue3.usg[vue3.t9[a]])
            }
            vue3.t10 = arr;
        },
        emt: function () {
            vue3.t11 = [];
        },
        emt2: function () {
            vue3.t10 = [];
        },
        ye: function (h, hg) {
            var hostids = [], groupids = [];
            for (var c = 0; c < h.length; c++) {
                hostids.push(h[c].hostid)
            }
            for (var u = 0; u < hg.length; u++) {
                groupids.push(hg[u].groupid)
            }
            var action = [], groupId = [], hostId = [];
            for (var i = 0; i < vue3.performactions.length; i++) {
                groupId[i] = [];
                hostId[i] = [];
                for (var k = 0; k < vue3.usg1[i].length; k++) {
                    groupId[i][k] = vue3.usg1[i][k].usrgrpid
                }
                for (var l = 0; l < vue3.us1[i].length; l++) {
                    hostId[i][l] = vue3.us1[i][l].userid
                }
            }
            for (var j = 0; j < vue3.performactions.length; j++) {
                action[j] = {
                    time: vue3.item[j],
                    user: hostId[j],
                    usGroup: groupId[j],
                }
            }
            console.log(hostids);
            console.log(groupids);
            console.log(vue3.t1);
            console.log(vue3.t2);
            console.log(vue3.t3);
            console.log(vue3.t4);
            console.log(vue3.t5);
            console.log(vue3.t6);
            console.log(vue3.t7);
            console.log(vue3.actionid);
            var usid = [], usgid = [];
            for (var a = 0; a < vue3.t10.length; a++) {
                usgid.push(vue3.t10[a].usrgrpid)
            }
            for (var b = 0; b < vue3.t11.length; b++) {
                usid.push(vue3.t11[b].userid)
            }
            console.log(usid);
            console.log(usgid);
            $.ajax({
                type: 'post',
                url: '/monitor/updateAction',
                traditional: true,
                data: {
                    name: vue3.t1,
                    status: vue3.t2,
                    def_shortdata: vue3.t3,
                    subject: vue3.t4,
                    def_longdata: vue3.t5,
                    message: vue3.t6,
                    id: vue3.actionid,
                    groupIds: groupids,
                    hostIds: hostids,
                    action:JSON.stringify(action)
                },
                dataType: 'json',
                success: function (data) {
                    var obj = eval('(' + data + ')');
                    console.log(obj);
                    if (obj.result) {
                        //alert('修改成功')
                        layui.use(['layer', 'form'], function () {
                            var layer = layui.layer
                                , form = layui.form;
                            layer.alert('修改成功', {icon: 6});
                        });
                        $('#editji').modeal('hide');
                    } else {
                        //alert('修改失败')
                        layui.use(['layer', 'form'], function () {
                            var layer = layui.layer
                                , form = layui.form;
                            layer.alert('修改失败', {icon: 5});
                        });
                    }
                }
            })
        }
    },
    computed: {
        sea: function () {
            var search = this.search.toLowerCase();
            if (search) {
                return this.mo.filter(function (product) {
                    return Object.keys(product).some(function (key) {
                        return String(product[key]).toLowerCase().indexOf(search) > -1
                    })
                })
            }
            return this.mo;
        },
        sea2: function () {
            var search2 = this.search2.toLowerCase();
            if (search2) {
                return this.ho.filter(function (product) {
                    return Object.keys(product).some(function (key) {
                        return String(product[key]).toLowerCase().indexOf(search2) > -1
                    })
                })
            }
            return this.ho;
        },
        sea3: function () {
            var search3 = this.edse.toLowerCase();
            if (search3) {
                return this.action.filter(function (product) {
                    return Object.keys(product).some(function (key) {
                        return String(product[key]).toLowerCase().indexOf(search3) > -1
                    })
                })
            }
            return this.action;
        }
    }
});
var vue6 = new Vue({
    el: '#a6',
    data: {
        select: [
            {a: "agent.hostname", b: "客户端主机名。返回字符串"},
            {a: "agent.ping", b: "客户端可达性检查。返回 nothing - 不可达；1 - 可达"},
            {a: "agent.version", b: "zabbix客户端（agent）的版本。返回字符串"},
            {a: "kernel.maxfiles", b: "操作系统最大的文件打开数量。返回整数"},
            {a: "kernel.maxproc", b: "操作系统最大的进程数。返回整数"},
            {
                a: "net.dns[<ip>,zone,<type>,<timeout>,<count>,<protocol>]",
                b: "检查 DNS 服务是否开启。返回 0 - DNS 服务关闭（服务未响应或DNS解析失败）；1 - DNS 服务开启"
            },
            {a: "net.dns.record[<ip>,zone,<type>,<timeout>,<count>,<protocol>]", b: "执行DNS查询。返回字符串信息"},
            {a: "net.if.collisions[if]", b: "网络冲突数量。返回整型"},
            {a: "net.if.in[if,<mode>]", b: "网络接口上传流量统计。返回 整数"},
            {a: "net.if.list", b: "网络接口列表（包括接口类型，状态，IPv4地址，说明）。返回文本"},
            {a: "net.if.out[if,<mode>]", b: "上行流量统计。返回整数"},
            {a: "net.if.total[if,<mode>]", b: "客户端主机名。返回字符串"},
            {a: "net.tcp.listen[port]", b: "检查 TCP 端口 是否处于侦听状态。返回 0 - 未侦听；1 - 正在侦听"},
            {a: "net.tcp.port[<ip>,port]", b: "检查是否能建立 TCP 连接到指定端口。返回 0 - 不能连接；1 - 可以连接"},
            {a: "net.tcp.service[service,<ip>,<port>]", b: "检查服务是否运行并接受 TCP 连接。返回 0 - 服务关闭；1 - 服务运行"},
            {a: "net.tcp.service.perf[service,<ip>,<port>]", b: "检查 TCP 服务的性能，当服务 down 时返回 0，否则返回连接服务花费的秒数"},
            {a: "net.udp.listen[port]", b: "检查 UDP 端口 是否处于侦听状态。返回 0 - 未侦听；1 - 正在侦听"},
            {a: "net.udp.service[service,<ip>,<port>]", b: "检查服务是否运行并响应 UDP 请求。返回 0 - 服务关闭；1 - 服务运行"},
            {a: "net.udp.service.perf[service,<ip>,<port>]", b: "检查 UDP 服务的性能，当服务 down 时返回 0，否则返回连接到服务花费的秒数"},
            {a: "perf_counter[counter,<interval>]", b: "所有Windows计数器值。返回 整形、浮点、字符串、文本"},
            {a: "proc.cpu.util[<name>,<user>,<type>,<cmdline>,<mode>,<zone>]", b: "CPU进程百分比。返回浮点值"},
            {a: "proc.mem[<name>,<user>,<mode>,<cmdline>,<memtype>]", b: "进程内存，以字节为单位。返回整数"},
            {a: "proc.num[<name>,<user>,<state>,<cmdline>]", b: "进程数。返回整数"},
            {a: "proc_info[process,<attribute>,<type>]", b: "各种有关特定进程的信息。它返回一个浮点值。"},
            {a: "sensor[device,sensor,<mode>]", b: "硬件传感器读数。返回浮点型"},
            {
                a: "service.info[service,<param>]",
                b: "Information about a service. Returns integer with param as state, startup; string - with param as displayname, path, user; text - with param as description; Specifically for state: 0 - running, 1 - paused, 2 - start pending, 3 - pause pending, 4 - continue pending, 5 - stop pending, 6 - stopped, 7 - unknown, 255 - no such service; Specifically for startup: 0 - automatic, 1 - automatic delayed, 2 - manual, 3 - disabled, 4 - unknown"
            },
            {a: "services[<type>,<state>,<exclude>]", b: "列表服务。返回0表示空，如果是列表则是每行一个内容"},
            {a: "system.boottime", b: "系统启动时间。返回时间戳"},
            {a: "system.cpu.intr", b: "设备的中断数。返回整数"},
            {a: "system.cpu.load[<cpu>,<mode>]", b: "CPU 负载。返回浮点数"},
            {a: "system.cpu.num[<type>]", b: "CPU 数量，返回整数"},
            {a: "system.cpu.switches", b: "上下文的数量进行切换。它返回一个整数值。"},
            {a: "system.cpu.util[<cpu>,<type>,<mode>]", b: "CPU 使用率。返回浮点数"},
            {a: "system.hostname[<type>]", b: "系统主机名。返回字符串"},
            {a: "system.hw.chassis[<info>]", b: "机架信息。返回字符串"},
            {a: "system.hw.cpu[<cpu>,<info>]", b: "CPU 信息。返回字符串或整数"},
            {a: "system.hw.devices[<type>]", b: "PCI或者USB设备列表。返回文本"},
            {a: "system.hw.macaddr[<interface>,<format>]", b: "MAC地址。返回字符串"},
            {a: "system.localtime[<type>]", b: "系统时间。返回的是UTC整数。是服务器本地的时间"},
            {a: "system.run[command,<mode>]", b: "即在主机上指定的命令的执行。返回命令执行结果的文本值。如果指定NOWAIT的模式，这将返回执行命令的结果1。"},
            {a: "system.stat[resource,<type>]", b: "系统统计数据。返回整数值或者浮点值"},
            {a: "system.sw.arch", b: "软件架构信息。返回字符串"},
            {a: "system.sw.os[<info>]", b: "操作系统信息。返回字符串"},
            {a: "system.sw.packages[<package>,<manager>,<format>]", b: "安装包列表。返回文本"},
            {a: "system.swap.in[<device>,<type>]", b: "在交换分区(swap)（从设备到内存）统计数据。返回整数"},
            {a: "system.swap.out[<device>,<type>]", b: "交换分区（从内存到设备）的统计数据。返回整数"},
            {a: "system.swap.size[<device>,<type>]", b: "交换分区空间大小，字节或从总百分比。从字节返回整数;对于浮动比例"},
            {a: "system.uname", b: "主机详细信息。返回字符串"},
            {a: "system.uptime", b: "系统启动时间。返回整数"},
            {a: "system.users.num", b: "已登录的用户数量，返回整数"},
            {
                a: "vfs.dev.read[<device>,<type>,<mode>]",
                b: "磁盘读取数据。类型是sectors, operations, bytes;返回整数，类型是 sps, ops, bps则返回浮点。"
            },
            {
                a: "vfs.dev.write[<device>,<type>,<mode>]",
                b: "磁盘写入数据。类型是sectors, operations, bytes;返回整数，类型是 sps, ops, bps则返回浮点。"
            },
            {a: "vfs.file.cksum[file]", b: "文件效验，unix标准算法。返回整数"},
            {a: "vfs.file.contents[file,<encoding>]", b: "搜索文件内容。返回文本"},
            {a: "vfs.file.exists[file]", b: "检查文件是否存在。返回 0 - 未找到文件；1 - 常规文件或链接（软/硬）存在"},
            {a: "vfs.file.md5sum[file]", b: "文件的MD5校验。返回字符串（该文件的MD5哈希值）"},
            {
                a: "vfs.file.regexp[file,regexp,<encoding>,<start line>,<end line>,<output>]",
                b: "查找文件中的字符串，返回内容是被匹配内容的整行字符串，或者其他可选参数"
            },
            {a: "vfs.file.regmatch[file,regexp,<encoding>,<start line>,<end line>]", b: "查找文件中的字符串，如果有则返回1，没有则返回0。"},
            {a: "vfs.file.size[file]", b: "文件大小（单位bytes）。返回整数"},
            {a: "vfs.file.time[file,<mode>]", b: "文件事件信息，返回的是时间戳 整数"},
            {a: "vfs.fs.inode[fs,<mode>]", b: "数或inode的百分比。返回数字，如果是浮点则是百分比"},
            {a: "vfs.fs.size[fs,<mode>]", b: "磁盘容量。如果返回的是字节则是整数，如果返回的是百分比则是浮点"},
            {a: "vm.memory.size[<mode>]", b: "从字节或总百分比的内存大小。它返回一个整数值，如果字节，只要百分比返回浮点值。"},
            {a: "web.page.get[host,<path>,<port>]", b: "获取网页。返回信息为网页源码或者TXT"},
            {a: "web.page.perf[host,<path>,<port>]", b: "全网页加载时间（秒）。返回浮点型"},
            {
                a: "web.page.regexp[host,<path>,<port>,<regexp>,<length>,<output>]",
                b: "查找网页中的字符串，返回内容是被匹配内容的整行字符串，或者其他可选参数"
            },
            {a: "wmi.get[<namespace>,<query>]", b: "执行 WMI 查询返回第一个对象。返回整形、浮点、字符串或者文本内容"},
        ],
        template: '',
        hostGroup: '',
        userSee: '',
        hgm: '',
        hgh: '',
        hshModel: '',
        host_: '',
        createHost: [],
        hostIndex: '',
        Templatename: '',
        hostGroupModel: '',
        hostGroupSpan: [],
        application: '',
        templateModel: [],
        search: '',
        applicationModel: [],
        templateId: '',
        applicationName: '',
        item: '',
        itemSearch: '',
        itemModel: [],
        trigger: '',
        status: [],
        typeModel: '0',
        show0: true,
        show1: false,
        show2: false,
        show3: false,
        radioModel: '',
        selectText: '选择',
        selectValuesShow: false,
        itemValues: '',
        itemName: '',
        SNMPOID: 'interfaces.ifTable.ifEntry.ifInOctets.1',
        SNMPcommunity: 'public',
        itemPort: '',
        itemFormula: '',
        ipmiSensor: '',
        messageType: '3',
        dataType: '3',
        itemUnit: '',
        itemMultiple: '',
        dataInterval: 30,
        keepTime: 90,
        dataCirle: 365,
        saveData: '0',
        newItem: '',
        itemModelId: '',
        itemtemplateid: '',
        itemStatus: true,
        triggerName: '',
        triggerExpression: '',
        multipleProblems: '',
        triggerDescription: '',
        severity: '',
        triggerStatus: '',
        itemid: '',
        itemType: {
            0: 'Agent',
            7: 'Zabbix客户端(主动式)',
            3: '简单检查',
            1: 'SNMPv1 客户端',
            4: 'SNMPv2 客户端',
            6: 'SNMPv3 客户端',
            17: 'SNMP trap',
            5: 'Zabbix内部',
            2: 'Zabbix采集器',
            8: 'Zabbix整合',
            10: '外部检查',
            11: '数据库监控',
            12: 'IPMI客户端',
            13: 'SSH 客户端',
            14: 'TELNET客户端',
            16: 'JMX agent代理程序',
            15: '可计算的'
        },
        itemModelIndex: '',
        app: '',
        triggerSearch: '',
        triggerModel: [],
        getTriggerName: '',
        datatriggerid: '',
        seeTriggerName: ''
    },
    beforeCreate: function () {
        var _this = this;
        $.post("/monitor/getTemplates", function (data) {
            var da = JSON.parse(JSON.parse(data)).result;
            _this.template = da;
            console.log(da);
        });
        $.post("/monitor/getHostGroup", function (data) {
            var da = JSON.parse(JSON.parse(data)).result;
            _this.hostGroup = da;
            console.log(da);
        });
        $.post("/monitor/host", function (data) {
            _this.host_ = data;
            console.log(data);
        });
    },
    methods: {
        seeTrigger: function (id) {
            $.post("/monitor/getTriggerByItem", {itemId: id}, function (data) {
                console.log(JSON.parse(JSON.parse(data)).result);
                vue6.seeTriggerName = JSON.parse(JSON.parse(data)).result
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
                    triggerId: vue6.datatriggerid
                },
                dataType: 'json',
                success: function (data) {
                    console.log(data);
                    if (JSON.parse(data).result) {
                        //alert('创建成功');
                        layui.use(['layer', 'form'], function () {
                            var layer = layui.layer
                                , form = layui.form;
                            layer.alert('创建成功', {icon: 6});
                        });
                        $('#updateTrigger').modal('hide');
                        $.post("/monitor/getTriggerByTem", {template: vue6.getTriggerName}, function (data) {
                            var d = JSON.parse(JSON.parse(data)).result;
                            console.log(d);
                            vue6.trigger = d;
                            for (var a = 0; a < d.length; a++) {
                                if (d[a].status == '0') {
                                    vue6.status.push(a)
                                }
                            }
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
        getTriggerData: function (i, data, id) {
            console.log(data[i]);
            vue6.datatriggerid = id;
            var obj = {
                0: true,
                1: false
            };
            vue6.triggerName = data[i].description;
            vue6.triggerExpression = data[i].expression;
            vue6.multipleProblems = obj[data[i].type];
            vue6.triggerDescription = data[i].comments;
            vue6.severity = data[i].priority;
            vue6.triggerStatus = obj[data[i].status];
        },
        createTriggers: function () {
            vue6.triggerName = '';
            vue6.triggerExpression = '';
            vue6.multipleProblems = false;
            vue6.triggerDescription = '';
            vue6.severity = '';
            vue6.triggerStatus = true;
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
        getItemByapp: function (id) {
            $.post("/monitor/getItemByApplication", {applicationId: id}, function (data) {
                console.log(data);
                var d = JSON.parse(JSON.parse(data)).result;
                vue6.item = d;
            });
        },
        delItemByindex: function (i, data) {
            data.splice(i, 1)
        },
        selectApp: function (i, data) {
            var arr = [];
            for (var a = 0; a < i.length; a++) {
                arr.push(data[i[a]])
            }
            vue6.app = arr;
            console.log(arr)
        },
        delItemByTr: function (i, data) {
            if (i.length > 0) {
                var arr = [], ar = [];
                for (var a = 0; a < i.length; a++) {
                    arr.push(data[i[a]].itemid)
                }
                for (var b = 0; b < data.length; b++) {
                    if (i.indexOf(b) >= 0) {

                    } else {
                        ar.push(data[b])
                    }
                }
                $.ajax({
                    type: 'post',
                    url: '/monitor/deleteItem',
                    traditional: true,
                    data: {itemIds: arr},
                    success: function (da) {
                        if (JSON.parse(JSON.parse(da)).result) {
                            alert('删除成功');
                            vue6.itemModel = [];
                            vue6.item = ar;
                        }
                    }
                });
            } else {
                //alert('请选择监控项')
                layui.use(['layer', 'form'], function () {
                    var layer = layui.layer
                        , form = layui.form;
                    layer.alert('请选择监控项', {icon: 7});
                });
            }
        },
        editItem: function (i, data, itemid) {
            vue6.itemid = itemid;
            $.post("/monitor/getApplicationByTemplateId", {templateId: vue6.itemtemplateid}, function (data) {
                var d = JSON.parse(JSON.parse(data)).result;
                vue6.application = d;
            });
            var obj = {
                    0: '0',
                    1: '1',
                    12: '2',
                    15: '3'
                },
                sta = {
                    0: true,
                    1: false
                };
            if (obj[data[i].type] == '0') {
                vue6.show0 = true;
                vue6.show1 = false;
                vue6.show2 = false;
                vue6.show3 = false;
            } else if (obj[data[i].type] == '1') {
                vue6.show0 = false;
                vue6.show1 = true;
                vue6.show2 = false;
                vue6.show3 = false;
            } else if (obj[data[i].type] == '2') {
                vue6.show0 = false;
                vue6.show1 = false;
                vue6.show2 = true;
                vue6.show3 = false;
            } else if (obj[data[i].type] == '3') {
                vue6.show0 = false;
                vue6.show1 = false;
                vue6.show2 = false;
                vue6.show3 = true;
            }
            console.log(i);
            console.log(data[i]);
            vue6.itemName = data[i].name;
            vue6.typeModel = obj[data[i].type];
            vue6.itemValues = data[i].key_;
            vue6.SNMPOID = data[i].SNMPOID;
            vue6.SNMPcommunity = data[i].snmpCommunity;
            vue6.itemPort = data[i].port;
            vue6.itemFormula = data[i].params;
            vue6.ipmiSensor = data[i].ipmiSensor;
            vue6.messageType = data[i].value_type;
            vue6.dataType = data[i].data_type;
            vue6.itemUnit = data[i].units;
            vue6.itemMultiple = data[i].formula;
            vue6.dataInterval = data[i].delay;
            vue6.keepTime = data[i].history;
            vue6.dataCirle = data[i].trends;
            vue6.saveData = data[i].delta;
            vue6.itemStatus = sta[data[i].status];
            vue6.app = data[i].applications;

        },
        updateItem: function (itemName, typeModel,
                              itemValues, SNMPOID, SNMPcommunity, itemPort, itemFormula, ipmiSensor, messageType,
                              dataType, itemUnit, itemMultiple, dataInterval, keepTime, dataCirle, saveData, newItem,
                              itemModelId, itemStatus) {
            var arr = [];
            for (var a = 0; a < vue6.app.length; a++) {
                arr.push(vue6.app[a].applicationid)
            }
            console.log(arr);
            $.ajax({
                type: 'post',
                url: '/monitor/updateItem',
                traditional: true,
                data: {
                    name: itemName,
                    type: typeModel,
                    key: itemValues,
                    value_type: messageType,
                    data_type: dataType,
                    units: itemUnit,
                    formula: itemMultiple,
                    delay: dataInterval,
                    history: keepTime,
                    trends: dataCirle,
                    delta: saveData,
                    applications: arr,
                    port: itemPort,
                    snmpOid: SNMPOID,
                    snmpCommunity: SNMPcommunity,
                    ipmiSensor: ipmiSensor,
                    params: itemFormula,
                    hostid: vue6.itemtemplateid,
                    itemid: vue6.itemid,
                    status: itemStatus
                },
                dataType: 'json',
                success: function (data) {
                    console.log(data);
                    if (JSON.parse(data).result) {
                        //alert('修改成功');
                        layui.use(['layer', 'form'], function () {
                            var layer = layui.layer
                                , form = layui.form;
                            layer.alert('修改成功', {icon: 6});
                        });
                        $('#editItem').modal('hide');
                        var arr = [];
                        arr.push(vue6.itemtemplateid);
                        $.ajax({
                            type: 'post',
                            url: '/monitor/getItemByApplicationId',
                            traditional: true,
                            data: {hostIds: arr},
                            dataType: 'json',
                            success: function (data) {
                                vue6.item = JSON.parse(data).result
                            }
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
            })

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
                        //alert('创建成功');
                        layui.use(['layer', 'form'], function () {
                            var layer = layui.layer
                                , form = layui.form;
                            layer.alert('创建成功', {icon: 6});
                        });
                        $('#createTrigger').modal('hide');
                        $.post("/monitor/getTriggerByTem", {template: vue6.getTriggerName}, function (data) {
                            var d = JSON.parse(JSON.parse(data)).result;
                            console.log(d);
                            vue6.trigger = d;
                            for (var a = 0; a < d.length; a++) {
                                if (d[a].status == '0') {
                                    vue6.status.push(a)
                                }
                            }
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
            })
        },
        createItemIn: function (newItem) {
            $.post("/monitor/createApplication", {
                name: newItem,
                hostId: vue6.itemtemplateid
            }, function (data) {
                $.post("/monitor/getApplicationByTemplateId", {templateId: vue6.itemtemplateid}, function (data) {
                    var d = JSON.parse(JSON.parse(data)).result;
                    console.log(d);
                    vue6.application = d;
                });
            })
        },
        createItem: function (itemName, typeModel,
                              itemValues, SNMPOID, SNMPcommunity, itemPort, itemFormula, ipmiSensor, messageType,
                              dataType, itemUnit, itemMultiple, dataInterval, keepTime, dataCirle, saveData, newItem,
                              itemModelId, itemStatus) {
            console.log(itemName);
            console.log(typeModel);
            console.log(itemValues);
            console.log(SNMPOID);
            console.log(SNMPcommunity);
            console.log(itemPort);
            console.log(itemFormula);
            console.log(ipmiSensor);
            console.log(messageType);
            console.log(dataType);
            console.log(itemUnit);
            console.log(itemMultiple);
            console.log(keepTime);
            console.log(dataCirle);
            console.log(saveData);
            console.log(newItem);
            console.log(itemModelId);
            console.log(itemStatus);
            console.log(vue6.itemtemplateid);
            var arr = [];
            for (var a = 0; a < vue6.app.length; a++) {
                arr.push(vue6.app[a].applicationid)
            }
            $.ajax({
                type: 'post',
                url: '/monitor/createItem',
                traditional: true,
                data: {
                    name: itemName,
                    type: typeModel,
                    key: itemValues,
                    value_type: messageType,
                    data_type: dataType,
                    units: itemUnit,
                    formula: itemMultiple,
                    delay: dataInterval,
                    history: keepTime,
                    trends: dataCirle,
                    delta: saveData,
                    applications: arr,
                    port: itemPort,
                    snmpOid: SNMPOID,
                    snmpCommunity: SNMPcommunity,
                    ipmiSensor: ipmiSensor,
                    params: itemFormula,
                    hostid: vue6.itemtemplateid,
                    status: itemStatus
                },
                dataType: 'json',
                success: function (data) {
                    console.log(data);
                    if (JSON.parse(data).result) {
                        //alert('创建成功');
                        layui.use(['layer', 'form'], function () {
                            var layer = layui.layer
                                , form = layui.form;
                            layer.alert('创建成功', {icon: 6});
                        });
                        $('#createItem').modal('hide');
                        var arr = [];
                        arr.push(vue6.itemtemplateid);
                        $.ajax({
                            type: 'post',
                            url: '/monitor/getItemByApplicationId',
                            traditional: true,
                            data: {hostIds: arr},
                            dataType: 'json',
                            success: function (data) {
                                vue6.item = JSON.parse(data).result
                            }
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
            })
        },
        radioChange: function (m) {
            console.log(m);
            vue6.itemValues = m
        },
        selectValues: function () {
            vue6.selectValuesShow = !vue6.selectValuesShow;
            if (vue6.selectValuesShow == false) {
                vue6.selectText = '选择'
            } else {
                vue6.selectText = '关闭'
            }
        },
        typeChange: function (m) {
            if (m == '0') {
                vue6.show0 = true;
                vue6.show1 = false;
                vue6.show2 = false;
                vue6.show3 = false;
            } else if (m == '1') {
                vue6.show0 = false;
                vue6.show1 = true;
                vue6.show2 = false;
                vue6.show3 = false;
            } else if (m == '2') {
                vue6.show0 = false;
                vue6.show1 = false;
                vue6.show2 = true;
                vue6.show3 = false;
            } else if (m == '3') {
                vue6.show0 = false;
                vue6.show1 = false;
                vue6.show2 = false;
                vue6.show3 = true;
            }
        },
        deleteTemplate: function () {
            console.log(vue6.templateModel);
            var arr = [];
            for (var a = 0; a < vue6.templateModel.length; a++) {
                arr.push(vue6.sea[vue6.templateModel[a]].templateid)
            }
            if (arr.length > 0) {
                $.ajax({
                    type: 'post',
                    url: '/monitor/deleteTemplate',
                    traditional: true,
                    data: {templateId: arr},
                    dataType: 'json',
                    success: function (data) {
                        console.log(data);
                        if (JSON.parse(data).result) {
                            layui.use(['layer', 'form'], function () {
                                var layer = layui.layer
                                    , form = layui.form;
                                layer.alert('删除成功', {icon: 6});
                            });
                            $('#deleteTemplate').modal('hide');
                            $.post("/monitor/getTemplates", function (data) {
                                var da = JSON.parse(JSON.parse(data)).result;
                                vue6.template = da;
                                console.log(da);
                            });
                        } else {
                            layui.use(['layer', 'form'], function () {
                                var layer = layui.layer;
                                layer.alert('删除失败', {icon: 5});
                            });
                        }
                    }
                });
            } else {
                //alert('请选择要删除的模板')
                layui.use(['layer', 'form'], function () {
                    var layer = layui.layer
                        , form = layui.form;
                    layer.alert('请选择要删除的模板', {icon: 7});
                });
            }
        },
        getTrigger: function (name) {
            var obj = {
                    0: "未分类",
                    1: "信息",
                    2: "警告",
                    3: "一般严重",
                    4: "严重",
                    5: "灾难"
                },
                obj2 = {
                    0: '启用',
                    1: "未启用"
                };
            vue6.getTriggerName = name;
            $.post("/monitor/getTriggerByTem", {template: name}, function (data) {
                var d = JSON.parse(JSON.parse(data)).result;
                console.log(d);
                for (var a = 0; a < d.length; a++) {
                    d[a].status_ = obj2[d[a].status];
                    d[a].priority_ = obj[d[a].priority]
                }
                vue6.trigger = d;
            });
        },
        itemGet: function (i) {
            vue6.itemtemplateid = i;
            var arr = [];
            arr.push(i);
            $.ajax({
                type: 'post',
                url: '/monitor/getItemByApplicationId',
                traditional: true,
                data: {hostIds: arr},
                dataType: 'json',
                success: function (data) {
                    var d = JSON.parse(data).result;
                    console.log(d);
                    for (var a = 0; a < d.length; a++) {
                        d[a].type_ = vue6.itemType[d[a].type];
                    }
                    vue6.item = d
                }
            });
        },
        createApplication: function () {
            if (vue6.applicationName) {
                $.post("/monitor/createApplication", {
                    name: vue6.applicationName,
                    hostId: vue6.templateId
                }, function (data) {
                    console.log(data);
                    if (data.applicationids) {
                        //alert('新建成功')
                        layui.use(['layer', 'form'], function () {
                            var layer = layui.layer
                                , form = layui.form;
                            layer.alert('新建成功', {icon: 6});
                        });
                    }
                    $.post("/monitor/getApplicationByTemplateId", {templateId: vue6.templateId}, function (data) {
                        var d = JSON.parse(JSON.parse(data)).result;
                        vue6.application = d;
                    });
                });
            } else {
                //alert('输入应用集名称')
                layui.use(['layer', 'form'], function () {
                    var layer = layui.layer
                        , form = layui.form;
                    layer.alert('输入应用集名称', {icon: 7});
                });
            }
        },
        deleteApplication: function () {
            var arr = [], id = [];
            var len = vue6.application.length;
            if (vue6.applicationModel.length > 0) {
                for (var i = 0; i < len; i++) {
                    if (vue6.applicationModel.indexOf(i) >= 0) {

                    } else {
                        arr.push(vue6.application[i])
                    }
                }
                for (var a = 0; a < vue6.applicationModel.length; a++) {
                    id.push(vue6.application[vue6.applicationModel[a]].applicationid)
                }
                $.ajax({
                    type: 'post',
                    url: '/monitor/deleteApplication',
                    traditional: true,
                    data: {id: id},
                    dataType: 'json',
                    success: function (data) {
                        if (JSON.parse(data).result) {
                            vue6.application = arr;
                            vue6.applicationModel = [];
                            //alert("删除成功")
                            layui.use(['layer', 'form'], function () {
                                var layer = layui.layer
                                    , form = layui.form;
                                layer.alert('删除成功', {icon: 6});
                            });
                        } else {
                            //alert("删除失败")
                            layui.use(['layer', 'form'], function () {
                                var layer = layui.layer
                                    , form = layui.form;
                                layer.alert('删除失败', {icon: 5});
                            });
                        }
                    }
                })
            }
        },
        checkApplication: function (event) {
            var _this = this;
            if (!event.currentTarget.checked) {
                this.applicationModel = [];
            } else { //实现全选
                _this.applicationModel = [];
                _this.application.forEach(function (item, i) {
                    _this.applicationModel.push(i);
                });
            }
        },
        checkedAll: function (event) {
            var _this = this;
            if (!event.currentTarget.checked) {
                this.templateModel = [];
            } else { //实现全选
                _this.templateModel = [];
                _this.template.forEach(function (item, i) {
                    _this.templateModel.push(i);
                });
            }
        },
        itemCheckAll: function (event) {
            var _this = this;
            if (!event.currentTarget.checked) {
                this.itemModel = [];
            } else { //实现全选
                _this.itemModel = [];
                _this.item.forEach(function (item, i) {
                    _this.itemModel.push(i);
                });
            }
        },
        applicationSet: function (i) {
            console.log(i);
            vue6.itemtemplateid = i;
            vue6.templateId = i;
            vue6.typeModel = '0';
            vue6.itemValues = '';
            vue6.SNMPOID = 'interfaces.ifTable.ifEntry.ifInOctets.1';
            vue6.SNMPcommunity = 'public';
            vue6.itemPort = '';
            vue6.itemFormula = '';
            vue6.ipmiSensor = '';
            vue6.messageType = '3';
            vue6.dataType = '3';
            vue6.itemUnit = '';
            vue6.itemMultiple = '';
            vue6.dataInterval = 30;
            vue6.keepTime = 90;
            vue6.dataCirle = 365;
            vue6.saveData = '0';
            vue6.app = [];
            vue6.itemStatus = true;
            $.post("/monitor/getApplicationByTemplateId", {templateId: i}, function (data) {
                var d = JSON.parse(JSON.parse(data)).result;
                console.log(d);
                vue6.application = d;
            });
        },
        hostGroupChange: function () {
            if (vue6.hostGroupSpan.indexOf(vue6.hostGroup[vue6.hostGroupModel]) >= 0) {

            } else {
                vue6.hostGroupSpan.push(vue6.hostGroup[vue6.hostGroupModel])
            }
        },
        hostGroupDel: function (i) {
            vue6.hostGroupSpan.splice(i, 1)
        },
        hostSee: function (i) {
            vue6.userSee = vue6.sea[i].hosts
        },
        hosgro: function () {
            var host = vue6.hostGroup[vue6.hgm].hosts, arr = [], arr2 = [], arr3 = [];
            for (var a = 0; a < host.length; a++) {
                arr.push(host[a].hostid)
            }
            for (var b = 0; b < vue6.host_.length; b++) {
                if (arr.indexOf(vue6.host_[b].hostid) >= 0) {
                    arr2.push(vue6.host_[b].name)
                }
            }
            for (var c = 0; c < arr.length; c++) {
                var hostObj = {
                    name: '',
                    id: ''
                };
                arr3[c] = hostObj;
                arr3[c].name = arr2[c];
                arr3[c].id = arr[c]
            }
            console.log(arr3);
            vue6.hgh = arr3;
        },
        addhsh: function () {
            var arr = [];
            console.log(vue6.createHost);
            console.log(vue6.hgh);
            console.log(vue6.hshModel);
            if (vue6.hshModel.length > 0) {
                for (var a = 0; a < vue6.hshModel.length; a++) {
                    if (vue6.createHost.indexOf(vue6.hgh[vue6.hshModel[a]]) >= 0) {

                    } else {
                        vue6.createHost.push(vue6.hgh[vue6.hshModel[a]])
                    }
                }
                vue6.hshModel = [];
            }
        },
        hostDel: function () {
            var arr = [];
            for (var a = 0; a < vue6.createHost.length; a++) {
                if (vue6.hostIndex.indexOf(a) >= 0) {

                } else {
                    arr.push(vue6.createHost[a])
                }
            }
            vue6.createHost = arr;
            vue6.hostIndex = [];
        },
        ts: function () {
            var arr = [], arr2 = [];
            for (var a = 0; a < vue6.createHost.length; a++) {
                arr.push(vue6.createHost[a].id)
            }
            console.log(arr);
            for (var b = 0; b < vue6.hostGroupSpan.length; b++) {
                arr2.push(vue6.hostGroupSpan[b].groupid)
            }
            console.log(arr2);
            if (vue6.Templatename && arr2.length > 0) {
                $.ajax({
                    type: 'post',
                    url: '/monitor/createTemplate',
                    traditional: true,
                    data: {
                        host: vue6.Templatename,
                        hostId: arr,
                        hostgroupid: arr2
                    },
                    dataType: 'json',
                    success: function (data) {
                        console.log(JSON.parse(data));
                        if (JSON.parse(data).error) {
                            //alert(JSON.parse(data).error.data)
                            layui.use(['layer', 'form'], function () {
                                var layer = layui.layer
                                    , form = layui.form;
                                layer.alert(JSON.parse(data).error.data, {icon: 7});
                            });

                        } else {
                            //alert('创建成功')
                            layui.use(['layer', 'form'], function () {
                                var layer = layui.layer
                                    , form = layui.form;
                                layer.alert('创建成功', {icon: 6});
                            });
                            $('#createTemplate').modal('hide');
                            $.post("/monitor/getTemplates", function (data) {
                                var da = JSON.parse(JSON.parse(data)).result;
                                vue6.template = da;
                                console.log(da);
                            });
                        }
                    }
                })
            } else {
                //alert('模板名称与主机组必填')
                layui.use(['layer', 'form'], function () {
                    var layer = layui.layer
                        , form = layui.form;
                    layer.alert('模板名称与主机组必填', {icon: 7});
                });
            }
        }
    },
    computed: {
        sea: function () {
            var search = this.search.toLowerCase();
            if (search) {
                return this.template.filter(function (product) {
                    return Object.keys(product).some(function (key) {
                        return String(product[key]).toLowerCase().indexOf(search) > -1
                    })
                })
            }
            return this.template;
        },
        sea2: function () {
            var search = this.itemSearch.toLowerCase();
            if (search) {
                return this.item.filter(function (product) {
                    return Object.keys(product).some(function (key) {
                        return String(product[key]).toLowerCase().indexOf(search) > -1
                    })
                })
            }
            return this.item;
        },
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
layui.use(['laypage', 'layer', 'laydate'], function () {
    var laypage = layui.laypage, layer = layui.layer, laydate = layui.laydate;
    laypage.render({
        elem: 'Pagination1'
        , count: 100
        , layout: ['count', 'prev', 'page', 'next', 'limit']
        , jump: function (obj) {
        }
    });
    laypage.render({
        elem: 'Pagination2'
        , count: 100
        , layout: ['count', 'prev', 'page', 'next', 'limit']
        , jump: function (obj) {
        }
    });
    laypage.render({
        elem: 'Pagination3'
        , count: 100
        , layout: ['count', 'prev', 'page', 'next', 'limit']
        , jump: function (obj) {
        }
    });
    laypage.render({
        elem: 'Pagination4'
        , count: 100
        , layout: ['count', 'prev', 'page', 'next', 'limit']
        , jump: function (obj) {
        }
    });
    laydate.render({
        elem: '#test9'
        , type: 'time'
        , range: true
    });
    laydate.render({
        elem: '#test10'
        , type: 'time'
        , range: true
    });
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
