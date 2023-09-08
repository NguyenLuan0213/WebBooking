<%-- 
    Document   : viewCoachStrips
    Created on : Aug 8, 2023, 1:46:16 PM
    Author     : Admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="se" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>



<c:url value="/viewCoachStrip" var="action" />
<section class="container">
    <h1 class="text-center text-success mt-1">DANH SÁCH CÁC CHUYẾN XE</h1>
    
    <c:if test="${pages > 1}">
        <ul class="pagination mt-1">
            <li class="page-item"><a class="page-link" href="${action}">Tất cả</a></li>
                <c:forEach begin="1" end="${pages}" var="i">
                    <c:url value="/viewCoachStrip" var="pageUrl">
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
        <a href="${searchUrl}" style="display: none">${c.idStationsStart.name || c.idStationsEnd.name}</a>
    </c:forEach>
        
    <section class="container">
        <table class="table table-info table-hover border border-dark border-2 p-3">
            <thead>
                <tr>
                    <th class="text-center">ID</th>
                    <th class="text-center">Tên Chuyến đi</th>
                    <th class="text-center">Khoảng Cách</th>
                    <th class="text-center">Bến Đi</th>
                    <th class="text-center">Bến Dừng</th>
                    <th class="text-center">Chức Năng</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${coachStrips}" var="cStrips">
                    <tr>
                        <th scope="row" class=" text-center">${cStrips.idCoachStrips}</th>
                        <td class="text-center">${cStrips.nameCS}</td>
                        <td class="text-center">${cStrips.distance} Km</td>
                        <td class="text-center">${cStrips.idStationsStart.name}</td>
                        <td class="text-center">${cStrips.idStationsEnd.name}</td>
                        <td class="text-center" >
                            <se:authorize access="hasAnyRole('ROLE_STAFF', 'ROLE_ADMIN','OWNER')">
                                <c:url value="/viewCoachStrip/admin/coachStrips/${cStrips.idCoachStrips}" var="api" />
                                <a href="${api}" class="btn btn-success">Cập nhật</a>
                                <a class="btn btn-danger" onclick="deleteEntry('coachStrips','${api}')">Xóa</a>
                            </se:authorize>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <div class="text-end">
            <se:authorize access="hasAnyRole('ROLE_STAFF', 'ROLE_ADMIN','OWNER')">
                <c:url value="/viewCoachStrip/admin/coachStrips/${cStrips.idCoachStrips}" var="api" />
                <a href="${api}" class="btn btn-primary" >Thêm Chuyến Đi</a>
            </se:authorize>
        </div>
    </section>
</section>
<script src="<c:url value="/js/main.js" />"></script>
