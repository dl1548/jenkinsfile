<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
List triggerList = (List)request.getAttribute("triggerList");
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
    <link rel="stylesheet" href="jsp/assets/layui/css/layui.css">
</head>
<body>
<section id="container" class="">
    <%@ include file="header.jsp" %>
    <%@ include file="left.jsp" %>
    <section id="main-content">
        <section class="wrapper">
            <div class="row">
                <div class="col-lg-8">
                    <section class="panel">
                        <div class="panel-heading">1</div>
                    </section>
                </div>
                <div class="col-lg-2">
                    <section class="panel">
                        <div class="panel-heading clearfix">
                            <div class="task-progress"><h1>TOTAL</h1></div>
                            <div class="row m-bottom">
                                <div class="col-xs-4 col-xs-offset-4"><span class="n-big">32</span><span class="t-small">EA</span></div>
                            </div>
                            <div class="row l-height">
                                <div class="col-xs-offset-2 col-xs-2"><span class="color-normal common2">11</span></div>
                                <div class="col-xs-offset-2 col-xs-2"><div class="back-color"></div><span class="color-alarm common2">12</span></div>
                            </div>
                            <div class="row m-top">
                                <div class="col-xs-offset-2 col-xs-2"><p>Normal</p></div>
                                <div class="col-xs-offset-2 col-xs-2"><p>Alarm</p></div>
                            </div>
                        </div>
                    </section>
                </div>
                <div class="col-lg-2">
                    <section class="panel">
                        <table class="table table-hover personal-task">
                            <tbody>
                            <tr>
                                <td><span class="text1">Aws</span></td>
                                <td><span class="common color-normal">11</span></td>
                                <td><span class="common color-alarm">11</span></td>
                            </tr>
                            </tbody>
                        </table>
                    </section>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-8">
                    <section class="panel">
                        <div class="panel-body progress-panel">
                            <div class="task-progress pull-left">
                                <h1>CPU Usage TOP 5(LAST 1 hour-AVG)</h1>
                            </div>
                            <div class="task-option">
                                <select class="styled">
                                    <option>Anjelina Joli</option>
                                    <option>Tom Crouse</option>
                                    <option>Jhon Due</option>
                                </select>
                            </div>
                        </div>
                        <table class="table table-hover personal-task">
                            <tbody>
                            <tr>
                                <td>1</td>
                                <td>
                                    Target Sell
                                </td>
                                <td>
                                    <span class="badge bg-important">75%</span>
                                </td>
                                <td>
                                    <div id="work-progress1"></div>
                                </td>
                            </tr>
                            <tr>
                                <td>2</td>
                                <td>
                                    Product Delivery
                                </td>
                                <td>
                                    <span class="badge bg-success">43%</span>
                                </td>
                                <td>
                                    <div id="work-progress2"></div>
                                </td>
                            </tr>
                            <tr>
                                <td>3</td>
                                <td>
                                    Payment Collection
                                </td>
                                <td>
                                    <span class="badge bg-info">67%</span>
                                </td>
                                <td>
                                    <div id="work-progress3"></div>
                                </td>
                            </tr>
                            <tr>
                                <td>4</td>
                                <td>
                                    Work Progress
                                </td>
                                <td>
                                    <span class="badge bg-warning">30%</span>
                                </td>
                                <td>
                                    <div id="work-progress4"></div>
                                </td>
                            </tr>
                            <tr>
                                <td>5</td>
                                <td>
                                    Delivery Pending
                                </td>
                                <td>
                                    <span class="badge bg-primary">15%</span>
                                </td>
                                <td>
                                    <div id="work-progress5"></div>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </section>
                    <section class="panel">
                        <div class="panel-heading">
                            2
                        </div>
                    </section>
                </div>
                <div class="col-lg-4">
                    <section class="panel">
                        <div class="panel-heading">故障列表</div>
                        <div class="panel-body">
                            <ul class="layui-timeline">
                            <%for(int i=0;i<triggerList.size();i++){%>
                                    <p>
                                        <%=i+1%>
                                    </p>
                            	<li class="layui-timeline-item">
                                <i class="layui-icon layui-timeline-axis"></i>
                                <div class="layui-timeline-content layui-text">
                                    <h3 class="layui-timeline-title"><%=triggerList.get(i)%></h3>
                                </div>
                            </li>
                            <%}%>
                            </ul>
                        </div>
                    </section>
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