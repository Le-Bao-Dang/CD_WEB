<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 22/06/2023
  Time: 3:11 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>BHNFOODSERORRRRR</title>
    <style>body {
        overflow: hidden;
        padding: 0px;
        margin: 0px;
        width: 100%;
        height: 100%;
        min-height: 100vh;
        display: flex;
        flex-direction: column;
        justify-content:space-between;
    }

    .container {
        width: 100%;
        max-width: 1280px;
        margin-left: auto;
        margin-right: auto;
    }

    /* ///////////////////////// */

    :root {
        --primary-color: #333333;
    }

    .navigation {
        display: flex;
        height: 40px;
        width: 100%;
        margin-bottom: 66px;
    }

    .navigation h3 {
        font-family: 'Inconsolata', monospace;
        font-size: 24px;
        line-height: 25px;
        font-weight: bold;
        letter-spacing: -0.08em;
        text-transform: uppercase;
        color: var(--primary-color);
    }

    .page-content {
        display: flex;
        justify-content: space-between;
    }

    img {
        width: 100%;
        max-width: 48%;
        margin-right: 32px;
    }

    .content {
        display: flex;
        flex-direction: column;
        max-width: 40%;
        width: 100%;
    }

    .content h1 {
        font-family: 'Space Mono', monospace;
        font-weight: bold;
        font-size: 48px;
        line-height: 71px;
        margin-bottom: 18px;
        letter-spacing: -0.035em;
        color: var(--primary-color);
    }

    .content p {
        font-family: 'Space Mono', monospace;
        font-size: 18px;
        line-height: 27px;
        letter-spacing: -0.035em;
    }

    .content .buttons {
        padding-top: 24px;
    }

    .content .btn {
        text-align: center;
        padding: 14px 24px;
        background-color: var(--primary-color);
        color: white;
        font-family: 'Space Mono', monospace;
        font-weight: 700;
        font-size: 14px;
        line-height: 21px;
        letter-spacing: -0.035em;
        text-transform: uppercase;
        text-decoration: none;
    }

    .footer {
        display: flex;
        justify-content: center;
        padding-top: 32px;
    }

    .footer h4 {
        font-family: 'Montserrat', monospace;
        font-weight: 500;
        font-size: 14px;
        line-height: 17px;
        color: #BDBDBD;
    }
    </style>
</head>
<body>
<nav class="container navigation">
    <h3>404 not found</h3>
</nav>

<section class="container page-content">
    <img id="image" src="https://raw.githubusercontent.com/ilko1999/404Page/master/Scarecrow.png" alt="Scarecrow">

    <div class="content ">
        <h1>Bạn ơi có tin này hay lắm nè</h1>
        <p>
            Trang mà bạn đang kiếm á nó không có hoặc không khả dụng ạ!!
            Xin mời bạn quay trở lại trang chủ.
        </p>
        <div class="buttons">
            <a href="/index" class="btn">BACK TO HOMEPAGE</a>
        </div>
    </div>
</section>

<footer class="container footer">
</footer>
<script>
    const img = document.getElementById('image');
    var deg = 0;
    var rotate = 0.2;
    setInterval(function() {
        if(deg >= 15 || deg <= - 15) rotate*=-1;
        deg += rotate;
        img.style.transform = 'rotate('+deg+'deg)';
    },10)
</script>
</body>
</html>
