<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
<head>
	<base href="<%=basePath%>">
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="jsp/css/bootstrap.min.css">
    <link rel="stylesheet" href="jsp/assets/font-awesome-4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="jsp/css/style.css">
    <link rel="stylesheet" href="jsp/css/style_.css">
    <link rel="stylesheet" href="jsp/css/layui.css">
</head>
<body>
<section id="container" class="">
    <%@ include file="header.jsp" %>
    <%@ include file="left.jsp" %>
  
    <section id="main-content">
        <section class="wrapper">
            <div class="row">
                <div class="col-lg-2">
                    <section class="panel" id="a-management">
                        <div class="panel-body"><h4 class="c-title">配置清单</h4></div>
                        <div class="list-group">
                            <a href="#a1" class="list-group-item" data-toggle="tab">分组管理</a>
                            <a href="#a2" class="list-group-item" data-toggle="tab">用户管理</a>
                            <a href="#a3" class="list-group-item" data-toggle="tab">监测器</a>
                            <a href="#a4" class="list-group-item" data-toggle="tab">告警设置</a>
                            <a href="#a5" class="list-group-item" data-toggle="tab">凭证管理</a>
                            <a href="#a6" class="list-group-item" data-toggle="tab">模板设置</a>
                        </div>
                    </section>
                </div>
                <div class="col-lg-10 tab-content">
                    <section class="tab-pane fade in active panel" id="a1">分组管理</section>
                    <section class="tab-pane fade panel" id="a2">
                        <div class="panel-body t-management"><h4 class="c-title">用户管理</h4></div>
                        <div class="panel-body">
                            <ul class="nav nav-tabs user-nav">
                                <li class="active"><a href="#userGroup" data-toggle="tab">用户群组</a></li>
                                <li><a href="#user" data-toggle="tab">用户</a></li>
                            </ul>
                            <div class="tab-content">
                                <div class="tab-pane fade in active" id="userGroup">
                                    <div class="userBorder">
                                        <div class="panel">
                                            <div class="panel-heading">
                                                <button class="btn btn-default" type="submit"><i class="fa fa-plus font-r color1"></i>新建用户群组</button>
                                                <button class="btn btn-default" type="submit"><i class="fa fa-refresh color1"></i></button>
                                                <button class="btn btn-default color3" type="submit"><i class="fa fa-trash font-r"></i>删除</button>
                                                <input type="text" class="form-control u-search" placeholder="请输入账号名称">
                                                <button class="btn btn-default color1" type="submit">查询</button>
                                                <button class="btn btn-default color2" type="button" data-toggle="modal" data-target="#myModal">编辑</button>
                                            </div>
                                            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                                                <div class="modal-dialog" role="document">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                                            <h3 class="modal-title color4" id="myModalLabel">用户群组管理</h3>
                                                        </div>
                                                        <div class="modal-body">
                                                            <div class="row mbottom">
                                                                <div class="col-xs-6">
                                                                    <span class="span1">用户群组名称:</span>
                                                                    <input type="text" class="form-control usersName">
                                                                </div>
                                                                <div class="col-xs-6">
                                                                    <span class="span1">状态:</span>
                                                                    <input type="radio" id="start1" name="users"  checked="checked"><label for="start1" class="color5">启用</label>
                                                                    <input type="radio" id="close1" name="users"><label for="close1" class="color2">关闭</label>
                                                                </div>
                                                            </div>
                                                            <div class="row mbottom">
                                                                <div class="col-xs-12 span1">权限设置:</div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-xs-4">
                                                                    <div class="row color6">
                                                                        <div class="col-xs-12">读写</div>
                                                                    </div>
                                                                    <div class="row">
                                                                        <div class="col-xs-12">
                                                                            <button type="button" class="btn btn-primary">添加</button>
                                                                            <button type="button" class="btn btn-danger">删除</button>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div class="col-xs-4">
                                                                    <div class="row color5">
                                                                        <div class="col-xs-12">只读</div>
                                                                    </div>
                                                                    <div class="row">
                                                                        <div class="col-xs-12">
                                                                            <button type="button" class="btn btn-primary">添加</button>
                                                                            <button type="button" class="btn btn-danger">删除</button>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div class="col-xs-4">
                                                                    <div class="row color2">
                                                                        <div class="col-xs-12">拒绝</div>
                                                                    </div>
                                                                    <div class="row">
                                                                        <div class="col-xs-12">
                                                                            <button type="button" class="btn btn-primary">添加</button>
                                                                            <button type="button" class="btn btn-danger">删除</button>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="modal-footer">
                                                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                                            <button type="button" class="btn btn-primary">Save changes</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="panel-body">
                                                <table class="table table-striped">
                                                    <tr>
                                                        <td><input type="checkbox"></td>
                                                        <td>群组名称</td>
                                                        <td>状态</td>
                                                        <td>子用户数量</td>
                                                        <td>子用户数量明细</td>
                                                        <td>读写</td>
                                                        <td>只读</td>
                                                        <td>拒绝</td>
                                                    </tr>
                                                </table>
                                                <div id="Pagination1"></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="tab-pane fade" id="user">
                                    <div class="userBorder">
                                        <div class="panel">
                                            <div class="panel-heading">
                                                <button class="btn btn-default" type="submit"><i class="fa fa-plus font-r color1"></i>新增用户</button>
                                                <button class="btn btn-default" type="submit"><i class="fa fa-refresh color1"></i></button>
                                                <button class="btn btn-default color3" type="submit"><i class="fa fa-trash font-r"></i>删除</button>
                                                <input type="text" class="form-control u-search" placeholder="请输入账号名称">
                                                <button class="btn btn-default color1" type="submit">查询</button>
                                                <button class="btn btn-default color2" type="submit" data-toggle="modal" data-target="#myModa2">编辑</button>
                                            </div>
                                            <div class="modal fade" id="myModa2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                                                <div class="modal-dialog" role="document">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                                            <h3 class="modal-title color4" id="myModalLabel2">用户管理</h3>
                                                        </div>
                                                        <div class="modal-body">
                                                            <div class="row mbottom2">
                                                                <div class="col-xs-4">
                                                                    <span class="userText marginl">用户角色:</span>
                                                                    <select class="form-control userInput" name="" id="">
                                                                        <option>管理员</option>
                                                                    </select>
                                                                </div>
                                                            </div>
                                                            <div class="row mbottom2">
                                                                <div class="col-xs-4">
                                                                    <span class="userText marginl">用户名:</span>
                                                                    <input type="text" class="form-control userInput">
                                                                </div>
                                                            </div>
                                                            <div class="row mbottom2">
                                                                <div class="col-xs-4">
                                                                    <span class="userText marginl color6">新密码:</span>
                                                                    <input type="password" class="form-control userInput">
                                                                </div>
                                                                <div class="col-xs-4">
                                                                    <span class="userText marginl">OR 密码:</span>
                                                                    <button type="button" class="btn btn-default">修改密码</button>
                                                                </div>
                                                            </div>
                                                            <div class="row mbottom2">
                                                                <div class="col-xs-4">
                                                                    <span class="userText marginl color6">确认密码:</span>
                                                                    <input type="password" class="form-control userInput">
                                                                </div>
                                                            </div>
                                                            <div class="row mbottom2">
                                                                <div class="col-xs-4">
                                                                    <i class="fa fa-user fa-2x fa-fw v-middle"></i>
                                                                    <span class="userText">姓名:</span>
                                                                    <input type="text" class="form-control userInput">
                                                                </div>
                                                                <div class="col-xs-4">
                                                                    <i class="fa fa-mobile fa-2x fa-fw v-middle"></i>
                                                                    <span class="userText">电话:</span>
                                                                    <input type="tel" class="form-control userInput">
                                                                </div>
                                                                <div class="col-xs-4">
                                                                    <i class="fa fa-envelope fa-2x fa-fw v-middle"></i>
                                                                    <span class="userText">邮箱:</span>
                                                                    <input type="email" class="form-control userInput">
                                                                </div>
                                                            </div>
                                                            <div class="row mbottom">
                                                                <div class="col-xs-4">
                                                                    <i class="fa fa-connectdevelop fa-2x fa-fw v-middle"></i>
                                                                    <span class="userText">部门:</span>
                                                                    <input type="text" class="form-control userInput">
                                                                </div>
                                                                <div class="col-xs-4">
                                                                    <i class="fa fa-vcard-o fa-2x fa-fw v-middle"></i>
                                                                    <span class="userText">职位:</span>
                                                                    <input type="text" class="form-control userInput">
                                                                </div>
                                                            </div>
                                                            <div class="row"  id="vue-1">
                                                                <div class="col-xs-4">
                                                                    <span>告警通知：</span>
                                                                    <input type="radio" id="start2" value="show" v-model="changed" v-on:change="radioClick"><label for="start2" class="color5">启用</label>
                                                                    <input type="radio" id="close2" value="hide" v-model="changed" v-on:change="radioClick"><label for="close2" class="color2">关闭</label>
                                                                </div>
                                                                <div class="col-xs-4" v-show="sh">
                                                                    <span class="userText marginl">允许告警时间</span>
                                                                    <div class="layui-inline">
                                                                        <div class="layui-input-inline">
                                                                            <input type="text" class="layui-input" id="test9" placeholder=" - ">
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div class="col-xs-4" v-show="sh">
                                                                    <span class="userText marginl">允许告警等级</span>
                                                                    <select class="form-control userInput">
                                                                        <option>未分类</option>
                                                                        <option>信息</option>
                                                                        <option>警告</option>
                                                                        <option>一般严重</option>
                                                                        <option>严重</option>
                                                                        <option>灾难</option>
                                                                    </select>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="modal-footer">
                                                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                                            <button type="button" class="btn btn-primary">Save changes</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="panel-body">
                                                <table class="table table-striped">
                                                    <tr>
                                                        <td><input type="checkbox"></td>
                                                        <td>用户名称</td>
                                                        <td>状态</td>
                                                        <td>姓名</td>
                                                        <td>用户类型</td>
                                                        <td>是否在线</td>
                                                        <td>最近登录时间</td>
                                                    </tr>
                                                </table>
                                                <div id="Pagination2"></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
                    <section class="tab-pane fade panel" id="a3">监测器</section>
                    <section class="tab-pane fade panel" id="a4">告警设置</section>
                    <section class="tab-pane fade panel" id="a5">凭证管理</section>
                    <section class="tab-pane fade panel" id="a6">模板设置</section>
                </div>
            </div>
        </section>
    </section>
</section>
</body>
</html>
<script src="jsp/js/jquery-3.2.1.min.js"></script>
<script src="jsp/js/bootstrap.min.js"></script>
<script src="jsp/js/vue.min.js"></script>
<script src="jsp/js/jquery.sparkline.js" type="text/javascript"></script>
<script src="jsp/js/sparkline-chart.js"></script>
<script src="jsp/assets/layui/layui.js"></script>
<script>
    var vue1 = new Vue({
        el: '#vue-1',
        data: {
            changed: 'hide',
            sh:false
        },
        methods:{
            radioClick:function () {
                if(vue1.changed == 'show'){
                    vue1.sh = true
                }else{
                    vue1.sh = false
                }
            }
        }
    });
    layui.use(['laypage', 'layer','laydate'], function(){
        var laypage = layui.laypage,layer = layui.layer,laydate = layui.laydate;
        laypage.render({
            elem: 'Pagination1'
            ,count: 100
            ,layout: ['count', 'prev', 'page', 'next', 'limit']
            ,jump: function(obj){
                console.log(obj)
            }
        });
        laypage.render({
            elem: 'Pagination2'
            ,count: 100
            ,layout: ['count', 'prev', 'page', 'next', 'limit']
            ,jump: function(obj){
                console.log(obj)
            }
        });
        laydate.render({
            elem: '#test9'
            ,type: 'time'
            ,range: true
        });
    });
</script>
