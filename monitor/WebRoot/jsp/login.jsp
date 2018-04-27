<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
<head>
	<base href="<%=basePath%>">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="Mosaddek">
    <meta name="keyword" content="FlatLab, Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">
    <link rel="shortcut icon" href="img/favicon.html">

    <title>Monitor</title>

    <!-- Bootstrap core CSS -->
    <link href="jsp/css/bootstrap.min.css" rel="stylesheet">
        <style>
        * {
            margin: 0;
            padding: 0;
        }

        html, body {
            height: 100%;
        }

        body {
            background: linear-gradient(to bottom, #131313 0%, #02101c 100%);
        }

        .canvas-box {
            position: fixed;
            left: 0;
            top: 0;
            z-index: -1;
        }

        #canvas {
            background: url("jsp/img/backimage.png") no-repeat;
        }
        #login{
            position: fixed;
            top: 0;
            left: 0;
            background: transparent;
            color: white;
            width: 100%;
            height: 100%;
        }
        .login{
            background: transparent;
            width: 400px;
            margin-left:auto;
            margin-right:auto;
            margin-top: 10%;
        }
        .user,.pwd{
            position: relative;
        }
        .login-input{
            background: transparent;
            outline: none;
            border-color: #66afe9;
            box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075), 0 0 8px rgba(102, 175, 233, .6);
            border-radius: 10px;
            margin-bottom: 20px;
            padding-left: 40px;
            color: white;
        }
        .login-user{
            background-position: 8px 0;
        }
        .login-pwd{
            background-position: 8px -34px;
        }
        .login-ico{
            background-image:url("jsp/img/ico.png");
            background-repeat: no-repeat;
            width: 34px;
            height: 34px;
            position: absolute;
            left: 3px;
        }
        .login-btn{
            border: 1px solid #66afe9;
            background-color: #01cafe;
            border-radius: 10px;
            width: 100%;
            outline: none;
            font-weight: bold;
        }
        .login-btn:hover,.login-btn:focus{
            background: #31b0d5;
            border-color: #269abc;
            color: white;
        }
        .transparent{
            background: transparent;
        }
        .login-footer{
            margin-top: 200px;
            border: none;
            text-align: center;
        }
        .login-heading{
            text-align: center;
            margin-bottom: 50px;
        }
        .forpwd{
            color: white;
            margin-top: 20px;
        }
    </style>
</head>

  <body>
<div class="canvas-box">
    <canvas id="canvas">你的浏览器不支持canvas</canvas>
</div>
<div id="login">
    <form name="loginfrom" method="post" action="servlet/loginServlet">
        <div class="panel login">
            <div class="panel-heading login-heading">
                <img src="jsp/img/itop-logo-external.png">
            </div>
            <div class="panel-body">
                <div class="user">
                    <label for="loginUser" class="login-ico login-user"></label>
                    <input type="text" name="name" id="loginUser" class="form-control login-input" placeholder="请输入用户名">
                </div>
                <div class="pwd">
                    <label for="loginPwd" class="login-ico login-pwd"></label>
                    <input type="password" id="loginPwd" name="password" class="form-control login-input" placeholder="请输入密码">
                </div>
                <div class="lonin-submit">
                    <button class="btn login-btn">登&nbsp;&nbsp;&nbsp;&nbsp;录</button>
                </div>
                <div>
                    <span class="pull-left forpwd">${msg}</span>
                    <a href="#" class="btn btn-link forpwd pull-right">忘记密码?</a>
                </div>
            </div>
            <div class="panel-footer transparent login-footer">
                <p>©2017 - 2019,J&K SYSTEM. All Rights Reserved.</p>
            </div>
        </div>
    </form>
</div>
  </body>
</html>
<script src="jsp/js/jquery-3.2.1.min.js"></script>
<script src="jsp/js/vue.min.js"></script>
<script src="jsp/assets/layui/layui.js"></script>
<script>
    var WINDOW_WIDTH = document.body.offsetWidth;
    var WINDOW_HEIGHT = document.body.offsetHeight;
    var canvas, context;
    var num = 500;
    var stars = [];
    var mouseX = WINDOW_WIDTH / 2;
    var mouseY = WINDOW_HEIGHT / 2;
    var rnd;

    window.onload = function () {
        canvas = document.getElementById('canvas');
        canvas.width = WINDOW_WIDTH;
        canvas.height = WINDOW_HEIGHT;

        context = canvas.getContext('2d');

        addStar();
        setInterval(render, 33);
        liuxing();

        // render();
        document.body.addEventListener('mousemove', mouseMove);
    };

    function liuxing() {
        var time = Math.round(Math.random() * 3000 + 33);
        setTimeout(function () {
            rnd = Math.ceil(Math.random() * stars.length)
            liuxing();
        }, time)
    }

    function mouseMove(e) {
        //因为是整屏背景，这里不做坐标转换
        mouseX = e.clientX;
        mouseY = e.clientY;
    }

    function render() {
        context.fillStyle = 'rgba(0,0,0,0.1)';
        context.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        context.clearRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT)
        for (var i = 0; i < num; i++) {
            var star = stars[i];
            if (i == rnd) {
                star.vx = -5;
                star.vy = 20;
                context.beginPath();
                context.strokeStyle = 'rgba(255,255,255,' + star.alpha + ')';
                context.lineWidth = star.r;
                context.moveTo(star.x, star.y);
                context.lineTo(star.x + star.vx, star.y + star.vy);
                context.stroke();
                context.closePath();
            }
            star.alpha += star.ra;
            if (star.alpha <= 0) {
                star.alpha = 0;
                star.ra = -star.ra;
                star.vx = Math.random() * 0.2 - 0.1;
                star.vy = Math.random() * 0.2 - 0.1;
            } else if (star.alpha > 1) {
                star.alpha = 1;
                star.ra = -star.ra
            }
            star.x += star.vx;
            if (star.x >= WINDOW_WIDTH) {
                star.x = 0;
            } else if (star.x < 0) {
                star.x = WINDOW_WIDTH;
                star.vx = Math.random() * 0.2 - 0.1;
                star.vy = Math.random() * 0.2 - 0.1;
            }
            star.y += star.vy;
            if (star.y >= WINDOW_HEIGHT) {
                star.y = 0;
                star.vy = Math.random() * 0.2 - 0.1;
                star.vx = Math.random() * 0.2 - 0.1;
            } else if (star.y < 0) {
                star.y = WINDOW_HEIGHT;
            }
            context.beginPath();
            var bg = context.createRadialGradient(star.x, star.y, 0, star.x, star.y, star.r);
//			bg.addColorStop(0,'rgba(255,255,255,'+star.alpha+')');
//			bg.addColorStop(1,'rgba(255,255,255,0)');
            context.fillStyle = bg;
            context.arc(star.x, star.y, star.r, 0, Math.PI * 2, true);
            context.fill();
            context.closePath();
        }
    }

    function addStar() {
        for (var i = 0; i < num; i++) {
            var aStar = {
                x: Math.round(Math.random() * WINDOW_WIDTH),
                y: Math.round(Math.random() * WINDOW_HEIGHT),
                r: Math.random() * 3,
                ra: Math.random() * 0.05,
                alpha: Math.random(),
                vx: Math.random() * 0.2 - 0.1,
                vy: Math.random() * 0.2 - 0.1
            }
            stars.push(aStar);
        }
    }
</script>
