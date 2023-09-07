<%-- 
    Document   : viewStation
    Created on : Aug 9, 2023, 10:49:54 PM
    Author     : Admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="se" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<c:url value="/viewStation" var="action" />
<section class="container">
    <h1 class="text-center text-success mt-1">DANH SÁCH CÁC BẾN XE</h1>

    <c:if test="${pages > 1}">
        <ul class="pagination mt-1">
            <li class="page-item"><a class="page-link" href="${action}">Tất cả</a></li>
                <c:forEach begin="1" end="${pages}" var="i">
                    <c:url value="/viewStation" var="pageUrl">
                        <c:param name="page" value="${i}" /> 
                    </c:url>
                <li class="page-item"><a class="page-link" href="${pageUrl}">${i}</a></li>
                </c:forEach>
        </ul>
    </c:if>
    
    
    <c:forEach items="${stations}" var="stas">
        <c:url value="/" var="searchUrl">
            <c:param name="idStations" value="${stas.idStations}" /> 
        </c:url>   
        <a href="${searchUrl}" style="display: none">${stas.address}</a>
    </c:forEach>

    <section class="container">
        <table class="table table-info table-hover border border-dark border-2 p-3">
            <thead>
                <tr>
                    <th class="text-center">ID</th>
                    <th class="text-center">Tên bến</th>
                    <th class="text-center">Địa chỉ</th>
                    <th class="text-center">Chức Năng</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${stations}" var="stas">
                    <tr>
                        <th scope="row" class=" text-center">${stas.idStations}</th>
                        <td class="text-center">${stas.name}</td>
                        <td class="text-center">${stas.address}</td>
                        <td class="text-center" >
                            <se:authorize access="hasAnyRole('ROLE_STAFF', 'ROLE_ADMIN')">
                                <c:url value="/viewStation/admin/stations/${stas.idStations}" var="api" />
                                <a href="${api}" class="btn btn-success">Cập nhật</a>
                                <a class="btn btn-danger" onclick="deleteEntry('stations', '${api}')">Xóa</a>
                            </se:authorize>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <div class="text-end">
            <se:authorize access="hasAnyRole('ROLE_STAFF', 'ROLE_ADMIN')">
                <c:url value="/viewStation/admin/stations/${stas.idStations}" var="api" />
                <a href="${api}" class="btn btn-primary" >Thêm Bến Xe</a>
            </se:authorize>
        </div>
    </section>
</section>
<script src="<c:url value="/js/main.js" />"></script>
