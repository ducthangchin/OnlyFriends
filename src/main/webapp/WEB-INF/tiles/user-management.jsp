<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<c:url var="img" value="/img" />

<c:set var="records" value="${profiles}"/>
<jsp:useBean id="records" type="java.util.List"/>
<c:set var="record" value='<%=records.size()%>'/>

<div class="row">

    <div class="col-md-12">
        <div class="title m-b-1">Showing ${record} out of ${record} records.</div>

        <table class="table table-hover">
            <thead >

                <tr class="header-background">
                    <th class="col-md-1">ID</th>
                    <th class="col-md-1 text-center">Avatar</th>
                    <th class="col-md-2">Email</th>
                    <th class="col-md-2">Full name</th>
                    <th class="col-md-2">Occupation</th>
                    <th class="col-md-2">Mobile</th>
                    <th class="col-md-1">Address</th>
                    <th class="col-md-1">role</th>
                    <th class="col-md-1">Status</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="profile" items="${profiles}">

                <tr>
                    <td>${profile.user.id}</td>
                    <td><a href="/profile/${profile.user.id}"><img alt="image" src="${img}${profile.avatarURL}" class="img-thumbnail"></a></td>
                    <td>${profile.user.email}</td>
                    <td>${profile.fullname}</td>
                    <td>${profile.about}</td>
                    <td>${profile.phone}</td>
                    <td>${profile.address}</td>
                    <td>
                        <c:set var="role" value="${profile.user.role}"/>
                        <jsp:useBean id="role" type="java.lang.String"/>

                        <c:choose>
                            <c:when test='<%=role.equals("ROLE_ADMIN")%>'>
                                ADMIN
                            </c:when>
                            <c:otherwise>
                                USER
                            </c:otherwise>
                        </c:choose>
                        <br>
                        <small>
                            <a href="/changerole?id=${profile.user.id}">
                                <c:choose>
                                    <c:when test='<%=role.equals("ROLE_ADMIN")%>'>
                                        authorize user
                                    </c:when>
                                    <c:otherwise>
                                        authorize admin
                                    </c:otherwise>
                                </c:choose>
                            </a>
                        </small>

                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${profile.user.enabled}">
                                Enabled
                            </c:when>
                            <c:otherwise>
                                Disabled
                            </c:otherwise>
                        </c:choose>
                        <small>
                            <a href="/changeauth?id=${profile.user.id}">
                                <c:choose>
                                    <c:when test="${profile.user.enabled}">
                                        disable
                                    </c:when>
                                    <c:otherwise>
                                        enable
                                    </c:otherwise>
                                </c:choose>
                            </a>
                        </small>

                    </td>

                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
