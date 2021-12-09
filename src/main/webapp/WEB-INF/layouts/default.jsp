<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-rquiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title><tiles:insertAttribute name="title" /></title>
    <link rel = "icon" href =
            "https://static3.bigstockphoto.com/0/9/3/large2/390753395.jpg"
          type = "image/x-icon">


    <c:set var="contextRoot" value="${pageContext.request.contextPath}"/>
    <!-- Bootstrap -->
    <link rel="stylesheet" href="${contextRoot}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${contextRoot}/css/main.css">


</head>
<body>



    <!-- Static navbar -->
    <nav class="navbar navbar-default nav-bar-static-top">
        <div class="container">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="/viewstatus">OnlyFriends</a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="/home">Home</a></li>
                    <li><a href="/about">About</a></li>


                </ul>
                <ul class="nav navbar-nav navbar-right">

                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"
                           role="button" aria-haspopup="true" aria-expanded="false">Status
                            <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="/addstatus">Add Status</a></li>
                            <li><a href="/viewstatus">My Status</a></li>
                        </ul>
                    </li>

                </ul>

            </div><!--/.nav-collapse -->
        </div><!--/.container-fluid -->
        </div> <!-- /container -->
    </nav>






<div class="container">
    <tiles:insertAttribute name="content" />
</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script
        src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="${contextRoot}/js/bootstrap.min.js"></script>

</body>
</html>
