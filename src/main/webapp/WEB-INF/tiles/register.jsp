<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<div class="container">

    <div class="content">


        <div style="max-width:520px;margin:0 auto;">

            <h3>New User Registration</h3>



                <div class="error">
                    <form:errors path="user.*"/>
                </div>




            <%--@elvariable id="user" type="com.ducthangchin.model.WebUser"--%>
            <form:form  class="userfrm" modelAttribute="user" method="POST" >

                <div class="form-group">
                    <label for="username">Username</label>
                    <form:input type="text" name="username" id="username" path="username" class="form-control" placeholder="username"/>
                </div>

                <div class="form-group">
                    <label for="password">Password</label>
                    <form:input type="password" name="password" id="password" path="password" class="form-control" placeholder="password" />
                </div>

                <div class="form-group">
                    <label for="passwordre">Confirm Password</label>
                    <input type="password" name="passwordre" id="passwordre" class="form-control" placeholder="confirm password" />
                </div>

                <div class="form-group">
                    <label for="email">Email</label>
                    <form:input type="email" name="email" id="email" path="email" class="form-control" placeholder="e-mail address" />
                </div>

                <input type="hidden" name="formaction" value="register" />
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

                <button type="submit" id="btnSubmit" class="btn btn-primary">Register Now</button>


            </form:form>

            <div class="successfullyRegistered" style="display:none;">
                <div class="alert alert-success" style="display:none;">
                </div>
                <p>Please check your email inbox, you'll receive an activation email in a few minutes. Click the activation link that is sent to your email.</p>
            </div>

            <div class="well well-sm">
                <p>After the registration, please check your email and click activation link.<br />
                    If you don't get the activation email, please check your junk email inbox too,<br />
                </p>
            </div>

        </div>

    </div>

</div>