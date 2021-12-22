<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<div class="container">

    <div class="content">

        <div class="logindiv">
            <h3>User Login</h3>
            <c:if test="${param.error != null}">
            <div class="alert alert-danger">
                Incorrect Email or Password
            </div>
            </c:if>
            <div class="alert alert-success" style="display: none;">
            </div>

            <form role="form" name="frmLogin" id="frmLogin" action="/login" class="userfrm" method="POST" >

                <div class="form-group">
                    <label for="username">Email</label>
                    <input class="form-control" type="text" name="username" id="username" placeholder="Email"  autofocus required/>
                </div>

                <div class="form-group">
                    <label for="password">Password</label>
                    <input class="form-control" type="password" name="password" id="password" placeholder="Password" required />
                </div>

                <input type="hidden" name="formaction" value="login" />
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

                <button type="submit" id="btnSubmit" class="btn btn-primary">Sign In</button>
                <a href="recovery" title="Cannot Sign in"> Cannot Sign in?</a>

            </form>
            <br />
            <p>Don't have an account? <a href="/register">Register now!</a></p>

        </div>
        <br />

    </div>

</div>