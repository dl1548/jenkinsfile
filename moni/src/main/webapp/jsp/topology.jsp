<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
                <div class="div1 pull-left">
                    <div class="panel topologyBorder">
                        <select class="form-control userInput selectBorder">
                            <option>拓扑图1</option>
                            <option>拓扑图1</option>
                            <option>拓扑图1</option>
                        </select>
                        <spqn>|</spqn>
                        <i class="fa fa-plus fa-2x v-middle color6 i-margin"></i>
                        <i class="fa fa-minus fa-2x v-middle color6 i-margin"></i>
                        <i class="fa fa-edit fa-2x v-middle color6 i-margin"></i>
                        <i class="fa fa-arrows-alt fa-2x v-middle color6 i-margin"></i>
                        <span>显示故障级别:</span>
                        <select class="form-control userInput selectBorder">
                            <option>严重</option>
                            <option>严重</option>
                            <option>严重</option>
                        </select>
                    </div>
                </div>
                <div class="div1 pull-right">
                    <div class="panel topologyBorder">
                        <select class="form-control userInput selectBorder">
                            <option>拓扑图1</option>
                            <option>拓扑图1</option>
                            <option>拓扑图1</option>
                        </select>
                        <spqn>|</spqn>
                        <i class="fa fa-clock-o fa-2x v-middle color6 i-margin"></i>
                        <select class="form-control userInput selectBorder">
                            <option>间隔5分钟</option>
                            <option>间隔10分钟</option>
                            <option>间隔15分钟</option>
                        </select>
                        <button type="button" class="btn btn-info v-middle btn-margin btn-padding">轮巡</button>
                    </div>
                </div>

            </div>
            <div class="row">
                <div class="col-xs-12">
                    <div class="panel topo-p topologyBorder"></div>
                </div>
            </div>
        </section>
    </section>
</section>
<script src="jsp/js/jquery-3.2.1.min.js"></script>
<script src="jsp/js/bootstrap.min.js"></script>
<script src="jsp/js/vue.min.js"></script>
<script src="jsp/js/jquery.sparkline.js" type="text/javascript"></script>
<script src="jsp/js/sparkline-chart.js"></script>
<script src="jsp/assets/layui/layui.js"></script>
</body>
</html>