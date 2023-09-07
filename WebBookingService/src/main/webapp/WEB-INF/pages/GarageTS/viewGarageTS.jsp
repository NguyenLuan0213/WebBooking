<%-- 
    Document   : viewGarageTS
    Created on : Aug 24, 2023, 12:44:57 PM
    Author     : Admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="se" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<c:url value="/viewGarageTS" var="action" />
<section class="container">
    <h1 class="text-center text-success mt-1">DANH SÁCH CÁC CHUYẾN ĐI CỦA NHÀ XE</h1>

    <c:if test="${pages > 1}">
        <ul class="pagination mt-1">
            <li class="page-item"><a class="page-link" href="${action}">Tất cả</a></li>
                <c:forEach begin="1" end="${pages}" var="i">
                    <c:url value="/viewGarageTS" var="pageUrl">
                        <c:param name="page" value="${i}" /> 
                    </c:url>
                <li class="page-item"><a class="page-link" href="${pageUrl}">${i}</a></li>
                </c:forEach>
        </ul>
    </c:if>
    
    <c:forEach items="${garagetakestrips}" var="garaTS">
        <c:url value="/" var="searchUrl">
            <c:param name="idGTS" value="${garaTS.idGTS}" /> 
        </c:url>   
        <a href="${searchUrl}" style="display: none">${garaTS.idGarage.nameGara}</a>
    </c:forEach>

    <section class="container">
        <table class="table table-info table-hover border border-dark border-2 p-3">
            <thead>
                <tr>
                    <th class="text-center">ID</th>
                    <th class="text-center">Tên Nhà Xe</th>
                    <th class="text-center">Tên Chuyến Xe</th>
                    <th class="text-center">Tên Bến Xe Đi</th>
                    <th class="text-center">Tên Bến Xe Dừng</th>
                    <th class="text-center">Chức Năng</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${garagetakestrips}" var="garaTS">
                    <tr>
                        <th scope="row" class=" text-center">${garaTS.idGTS}</th>
                        <td class="text-center">${garaTS.idGarage.nameGara}</td>
                        <td class="text-center">${garaTS.idCoachStrips.nameCS}</td>
                        <td class="text-center">${garaTS.idCoachStrips.idStationsStart.name}</td>
                        <td class="text-center">${garaTS.idCoachStrips.idStationsEnd.name}</td>
                        <td class="text-center" >
                            <se:authorize access="hasAnyRole('ROLE_STAFF', 'ROLE_ADMIN','OWNER')">
                                <c:url value="/viewGarageTS/admin/garagetakestrips/${garaTS.idGTS}" var="api" />
                                <a href="${api}" class="btn btn-success">Cập nhật</a>
                                <a class="btn btn-danger" onclick="deleteEntry('garagetakestrips', '${api}')">Xóa</a>
                            </se:authorize>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <div class="text-end">
            <se:authorize access="hasAnyRole('ROLE_STAFF', 'ROLE_ADMIN','OWNER')">
                <c:url value="/viewGarageTS/admin/garagetakestrips/${garaTS.idGTS}" var="api" />
                <a href="${api}" class="btn btn-primary" >Thêm Nhà Xe</a>
            </se:authorize>
        </div>
    </section>
</section>
<script src="<c:url value="/js/main.js" />"></script>
