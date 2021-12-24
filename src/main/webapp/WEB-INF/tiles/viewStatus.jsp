<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="jwp" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>


<c:url var="url" value="/viewstatus"/>
<c:url var="img" value="/img" />

<link rel="stylesheet" href="${contextRoot}/css/newfeed.css">
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">

<div class="container">
    <div class="col-md-8 col-md-offset-2">
        <c:forEach var="statusUpdate" items="${page.content}" >

            <c:url var="addCommentLink" value="/addComment"/>
            <c:url var="deleteLink" value="/deletestatus?id=${statusUpdate.id}"/>


            <div class="social-feed-separated">
                <div class="social-avatar">
                    <a href="/profile/${statusUpdate.owner.user.id}">
                        <img alt="image" src="${img}${statusUpdate.owner.avatarURL}">
                    </a>
                </div>
                <div class="social-feed-box">
                    <div class="social-avatar">
                        <a href="/profile/${statusUpdate.owner.user.id}">
                                ${statusUpdate.owner.fullname}
                        </a>
                        <small class="text-muted">
                                <fmt:formatDate pattern="EEEE d MMMM y 'at' H:mm"
                                                value="${statusUpdate.added}"/>
                        </small>
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <a href="${deleteLink}">delete post</a>
                        </sec:authorize>
                    </div>
                    <div class="social-body">
                        <p class="homepage-status">
                                ${statusUpdate.text}
                        </p>
                        <c:if test="${statusUpdate.imgURL != null and statusUpdate.imgURL != ''}">
                            <img src="${img}${statusUpdate.imgURL}" class="img-responsive">
                        </c:if>
                        <div class="btn-group">
                            <button class="btn btn-white btn-xs"><i class="fa fa-thumbs-up"></i> Like this!</button>
                            <button class="btn btn-white btn-xs"><i class="fa fa-comments"></i> Comment</button>
                            <button class="btn btn-white btn-xs"><i class="fa fa-share"></i> Share</button>
                        </div>
                    </div>
                    <div class="social-footer">
                        <c:forEach var="comment" items="${postComments[statusUpdate.id]}">
                            <div class="social-comment">
                                <a href="/profile/${comment.commenter.user.id}" class="pull-left">
                                    <img alt="image" src="${img}${comment.commenter.avatarURL}">
                                </a>
                                <div class="media-body">
                                    <a href="/profile/${comment.commenter.user.id}">
                                        ${comment.commenter.fullname}
                                    </a>
                                    ${comment.text}
                                    <br>
                                    <a href="#" class="small"><i class="fa fa-thumbs-up"></i> Like this!</a> -
                                    <small class="text-muted">
                                        <fmt:formatDate pattern=" d MMMM y 'at' H:mm"
                                                        value="${comment.added}"/>
                                    </small>
                                    <sec:authorize access="hasRole('ROLE_USER')">
                                        <c:if test="${comment.commenter.id == user.id}">
                                            <a href="/deletecomment?id=${comment.id}" class="small" class="pull-right">delete</a>
                                        </c:if>
                                    </sec:authorize>

                                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                                        <a href="/deletecomment?id=${comment.id}" class="small" class="pull-right">delete comment</a>
                                    </sec:authorize>


                                </div>
                            </div>

                        </c:forEach>



                        <div class="social-comment">
                            <a href="/profile" class="pull-left">
                                <img alt="image" src="${img}${user.avatarURL}">
                            </a>
                            <div class="media-body">
                                <form method="post" action="${addCommentLink}">
                                    <input type="text" name="text"  class="form-control" placeholder="Write comment..."/>

                                    <input type="hidden" name="postId" value="${statusUpdate.id}">
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                </form>
                            </div>


                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>

        <div class="pull-right">
            <jwp:pagination url="${url}" page="${page}" size="1" />
        </div>



    </div>

</div>




