<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="jwp" tagdir="/WEB-INF/tags" %>


<c:url var="url" value="/viewstatus"/>
<div class="row">

    <div class="col-md-8 col-md-offset-2">


        <!-- loop over the page.getContent() -->
        <c:forEach var="statusUpdate" items="${page.content}" >


            <c:url var="editLink" value="/editstatus?id=${statusUpdate.id}"/>
            <c:url var="deleteLink" value="/deletestatus?id=${statusUpdate.id}"/>


            <div class="panel panel-default">

                <div class="panel-heading">

                    <div class="panel-title">
                        Status update added on
                        <fmt:formatDate pattern="EEEE d MMMM y 'at' H:mm:ss"
                                        value="${statusUpdate.added}"/>
                    </div>

                </div>

                <div class="panel-body">
                    <div>${statusUpdate.text}</div>

                    <div class="edit-links pull-right"><a href="${editLink}">edit</a> |  <a
                            onclick="return confirm(
                            'Once this Status is deleted, you can no longer to see it anymore. Are you sure you want to delete anyway?');"
                            href="${deleteLink}">delete</a></div>

                </div>

            </div>



        </c:forEach>


        <jwp:pagination url="${url}" page="${page}" size="6"/>



    </div>
</div>
