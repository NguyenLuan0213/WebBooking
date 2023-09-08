<%-- 
    Document   : viewGarage
    Created on : Aug 23, 2023, 7:15:44 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="se" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:url value="/viewGarage" var="action" />
<section class="container">
    <h1 class="text-center text-success mt-1">DANH SÁCH CÁC NHÀ XE</h1>

    <c:if test="${pages > 1}">
        <ul class="pagination mt-1">
            <li class="page-item"><a class="page-link" href="${action}">Tất cả</a></li>
                <c:forEach begin="1" end="${pages}" var="i">
                    <c:url value="/viewGarage" var="pageUrl">
                        <c:param name="page" value="${i}" /> 
                    </c:url>
                <li class="page-item"><a class="page-link" href="${pageUrl}">${i}</a></li>
                </c:forEach>
        </ul>
    </c:if>


    <c:forEach items="${garages}" var="gara">
        <c:url value="/" var="searchUrl">
            <c:param name="idGarage" value="${gara.idGarage}" /> 
        </c:url>   
        <a href="${searchUrl}" style="display: none">${gara.nameGara || gara.addressGarage}</a>
    </c:forEach>

    <section class="container">
        <table class="table table-info table-hover border border-dark border-2 p-3">
            <thead>
                <tr>
                    <th class="text-center">ID</th>
                    <th class="text-center">Tên Chủ Nhà Xe</th>
                    <th class="text-center">Tên Nhà Xe</th>
                    <th class="text-center">Địa Chỉ</th>
                    <th class="text-center">Chức Năng</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${garages}" var="gara">
                    <tr>
                        <th scope="row" class=" text-center">${gara.idGarage}</th>
                        <td class="text-center">${gara.idStaff.nameStaff}</td>
                        <td class="text-center">${gara.nameGara}</td>
                        <td class="text-center">${gara.addressGarage}</td>
                        <td class="text-center" >
                            <se:authorize access="hasAnyRole('ROLE_STAFF', 'ROLE_ADMIN','OWNER')">
                                <c:url value="/viewGarage/admin/garages/${gara.idGarage}" var="api" />
                                <a href="${api}" class="btn btn-success">Cập nhật</a>
                                <a class="btn btn-danger" onclick="deleteEntry('garages', '${api}')">Xóa</a>
                            </se:authorize>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <div class="text-end">
            <se:authorize access="hasAnyRole('ROLE_STAFF', 'ROLE_ADMIN','OWNER')">
                <c:url value="/viewGarage/admin/garages/${gara.idGarage}" var="api" />
                <a href="${api}" class="btn btn-primary" >Thêm Nhà Xe</a>
            </se:authorize>
        </div>
    </section>
</section>
<script src="<c:url value="/js/main.js" />"></script>