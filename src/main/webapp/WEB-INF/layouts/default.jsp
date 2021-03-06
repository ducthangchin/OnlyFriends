<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

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
    <style type="text/css">
        body{

            text-align: left;
            background-color: #e2e8f0;
        }
    </style>


</head>
<body>

<c:url var="onlyfriends" value="/viewstatus"/>
<c:url var="home" value="/mystatus"/>
<c:url var="about" value="/profile"/>
<c:url var="postmanage" value="/viewstatus"/>
<c:url var="usermanage" value="/user-management"/>


<sec:authorize access="hasRole('ROLE_ADMIN')">
    <c:url var="onlyfriends" value="/profile"/>
</sec:authorize>



    <!-- Static navbar -->
    <nav class="navbar navbar-default navbar-fixed-top">
        <div class="container">

            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="${onlyfriends}">OnlyFriends</a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav">

                    <sec:authorize access="!isAuthenticated()">
                        <li class="active"><a href="${home}">Home</a></li>
                        <li><a href="${about}">About</a></li>
                    </sec:authorize>

                    <sec:authorize access="hasRole('ROLE_USER')">
                        <li class="active"><a href="${home}">Home</a></li>
                        <li><a href="${about}">About</a></li>
                    </sec:authorize>

                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <li class="active"><a href="${postmanage}">Post Management</a></li>
                        <li><a href="${usermanage}">User Management</a></li>
                    </sec:authorize>



                </ul>
                <ul class="nav navbar-nav navbar-right">

                    <sec:authorize access="!isAuthenticated()">
                        <li><a href="/login">Login</a></li>
                    </sec:authorize>

                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <li><a href="javascript:$('#logoutForm').submit()">Logout</a></li>
                    </sec:authorize>

                    <sec:authorize access="hasRole('ROLE_USER')">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"
                               role="button" aria-haspopup="true" aria-expanded="false">Account
                                <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="/addstatus">Add Status</a></li>
                                <li><a href="/mystatus">My Status</a></li>
                                <li><a href="javascript:$('#logoutForm').submit()">Logout</a></li>
                            </ul>
                        </li>
                    </sec:authorize>

                </ul>

            </div><!--/.nav-collapse -->

        </div> <!-- /container -->
    </nav>


    <form id="logoutForm" method="post" action="/logout">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    </form>






<div class="container margin-top">
    <tiles:insertAttribute name="content" />
</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script
        src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="${contextRoot}/js/bootstrap.min.js"></script>

</body>
</html>
