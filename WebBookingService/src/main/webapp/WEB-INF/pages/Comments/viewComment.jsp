<%-- 
    Document   : ViewComment
    Created on : Aug 21, 2023, 9:48:38 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="se" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>



<c:url value="/viewComment" var="action" />
<section class="container">
    <h1 class="text-center text-success mt-1">DANH SÁCH BÌNH LUẬN</h1>

    <c:if test="${pages > 1}">
        <ul class="pagination mt-1">
            <li class="page-item"><a class="page-link" href="${action}">Tất cả</a></li>
                <c:forEach begin="1" end="${pages}" var="i">
                    <c:url value="/viewComment" var="pageUrl">
                        <c:param name="page" value="${i}" /> 
                    </c:url>
                <li class="page-item"><a class="page-link" href="${pageUrl}">${i}</a></li>
                </c:forEach>
        </ul>
    </c:if>


    <div class="text-end mb-3">
        <se:authorize access="hasAnyRole('ROLE_STAFF', 'ROLE_ADMIN')">
            <c:url value="/viewComment/admin/comments/${com.idComment}" var="api" />
            <a href="${api}" class="btn btn-primary" >Thêm Bình Luận</a>
        </se:authorize>
    </div>
    <section class="container d-flex flex-column">
        <table class="table table-info table-hover border border-dark border-2 p-3">
            <thead>
                <tr>
                    <th class="text-center">ID comment</th>
                    <th class="text-center">ID Customer</th>
                    <th class="text-center">ID Garage</th>
                    <th class="text-center">Comments</th>
                    <th class="text-center">Chức năng</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${comments}" var="come">
                    <tr >
                        <th scope="row" class=" text-center">${come.idComment}</th>
                        <td class="text-center">${come.idCustomer.name}</td>
                        <td class="text-center">${come.idGarage.nameGara}</td>
                        <td class="text-center">${come.comments}</td>
                        <td class="text-center" >
                            <se:authorize access="hasAnyRole('ROLE_STAFF', 'ROLE_ADMIN')">
                                <c:url value="/viewComment/admin/comments/${come.idComment}" var="api" />
                                <a href="${api}" class="btn btn-success">Cập nhật</a>
                                <a class="btn btn-danger" onclick="deleteEntry('comments', '${api}')">Xóa</a>
                            </se:authorize>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </section>

</section>
<script src="<c:url value="/js/main.js" />"></script>
