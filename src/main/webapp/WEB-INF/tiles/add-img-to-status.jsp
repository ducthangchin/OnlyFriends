<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<c:url value="/add-img-to-status" var="addPhotoToStatusLink"/>


<div class="row">

    <div class="col-md-6 col-md-offset-3">


        <div class="panel panel-default">

            <div class="panel-heading">

                <div class="panel-title">Add Photo to Status</div>

            </div>

            <form method="post" enctype="multipart/form-data"  action="${addPhotoToStatusLink}">
                <div class="form-group">Select Photo:  <input type="file" accept="image/*" name="file" value="Select Profile Photo"/></div>
                <div class="form-group"><input type="submit" value="upload" /></div>
                <input type="hidden" name="id" value="${id}">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            </form>

        </div>


    </div>

</div>