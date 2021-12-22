<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="page" required="true"  type="org.springframework.data.domain.Page" %>
<%@ attribute name="url" required="true" %>

<!-- Number of page numbers to display -->
<%@ attribute name="size" required="true"%>


<c:set var="block" value="${empty param.b ? 0 : param.b}"/>

<c:set var="size" value="${empty size ? 0 : size}"/>

<c:set var="startPage" value="${block * size + 1}"/>

<c:set var="endPage" value="${(block + 1) * size > page.totalPages ? page.totalPages : (block + 1) * size}"/>


<c:if test="${page.totalPages != 1}">

<ul class="pagination">

    <c:if test="${block != 0}">
        <li class="page-item disabled">
            <span class="page-link">
                <a href="${url}?p=${(block-1)*size+1}&b=${block-1}">Previous</a>
            </span>
        </li>

    </c:if>



    <c:forEach var="pageNumber" begin="${startPage}" end="${endPage}">

        <c:choose>

            <c:when test="${page.number != pageNumber-1}">

                <li class="page-item">
                    <a class="page-link" href="${url}?p=${pageNumber}&b=${block}">
                        <c:out value="${pageNumber}"/>
                    </a>
                </li>



            </c:when>

            <c:otherwise>


                <li class="page-item active">
                     <span class="page-link">

                         <strong><c:out value="${pageNumber}"/></strong>
                         <span class="sr-only">(current)</span>

                      </span>
                </li>



            </c:otherwise>
        </c:choose>

    </c:forEach>

    <c:if test="${endPage != page.totalPages}">

        <li class="page-item">
            <a class="page-link" href="${url}?p=${endPage+1}&b=${block+1}">Next</a>
        </li>


    </c:if>

</c:if>

</ul>

