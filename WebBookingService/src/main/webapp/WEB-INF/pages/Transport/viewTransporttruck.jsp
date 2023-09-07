<%-- 
    Document   : viewTransporttruck
    Created on : Aug 28, 2023, 10:20:16 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="se" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<c:url value="/viewTransporttruck" var="action" />
<section class="container">
    <h1 class="text-center text-success mt-1">DANH SÁCH CÁC CHUYẾN CHỞ HÀNG</h1>

    <c:if test="${pages > 1}">
        <ul class="pagination mt-1">
            <li class="page-item"><a class="page-link" href="${action}">Tất cả</a></li>
                <c:forEach begin="1" end="${pages}" var="i">
                    <c:url value="/viewTransporttruck" var="pageUrl">
                        <c:param name="page" value="${i}" /> 
                    </c:url>
                <li class="page-item"><a class="page-link" href="${pageUrl}">${i}</a></li>
                <c:if test="${i % 30 == 0 && i < pages}"><br /></c:if>
            </c:forEach>
        </ul>
    </c:if>



    <c:forEach items="${transts}" var="tr">
        <c:url value="/" var="searchUrl">
            <c:param name="idTrans" value="${tr.idTrans}" /> 
        </c:url>   
        <a href="${searchUrl}" style="display: none">${tr.idTrans}</a>
    </c:forEach>

    <section class="container">
        <div class="text-end mb-3">
            <se:authorize access="hasAnyRole('ROLE_STAFF', 'ROLE_ADMIN')">
                <c:url value="/viewTransporttruck/admin/transts/${tr.idTrans}" var="api" />
                <a href="${api}" class="btn btn-primary" >Thêm Chuyến Chở</a>
            </se:authorize>
        </div>
        <table class="table table-info table-hover border border-dark border-2 p-3">
            <thead>
                <tr>
                    <th class="text-center">ID</th>
                    <th class="text-center">Tên Chuyến Hàng</th>
                    <th class="text-center">Tên Chuyến Xe</th>
                    <th class="text-center">Tên Xe</th>
                    <th class="text-center">Tên Nhà Xe</th>
                    <th class="text-center">Tên Người Lái</th>
                    <th class="text-center">Ngày Vận Chuyển</th>
                    <th class="text-center">Quyền chuyển</th>
                    <th class="text-center">Số Tiền 1 Lần Chuyển</th>
                    <th class="text-center">Chức Năng</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${transts}" var="tr">
                    <tr>
                        <th scope="row" class=" text-center">${tr.idTrans}</th>
                        <td class=" text-center">${tr.idTrans}</td>
                        <td class=" text-center">${tr.idCoachStrips.nameCS}</td>
                        <td class=" text-center">${tr.idCoach.numberCoach}</td>
                        <td class=" text-center">${tr.idGarage.nameGara}</td>
                        <td class=" text-center">${tr.idStaffDrive.nameStaff}</td>
                        <td class="text-center">
                            <fmt:formatDate value="${tr.dateTrans}" pattern="yyyy-MM-dd HH:mm:ss" />
                        </td>
                        <td class=" text-center"><c:choose>
                                <c:when test="${tr.accpectSent == 1}">
                                    Cho Chuyển
                                </c:when>
                                <c:when test="${tr.accpectSent == 0}">
                                    Chưa Cho Phép
                                </c:when>
                                <c:otherwise>
                                    Trạng thái không xác định
                                </c:otherwise>
                            </c:choose></td>
                        <td class=" text-center">${tr.priceTran}</td>
                        <td class="text-center" >
                            <se:authorize access="hasAnyRole('ROLE_STAFF', 'ROLE_ADMIN')">
                                <c:url value="/viewTransporttruck/admin/transts/${tr.idTrans}" var="api" />
                                <a href="${api}" class="btn btn-success">Cập nhật</a>
                                <a class="btn btn-danger" onclick="deleteEntry('transts', '${api}')">Xóa</a>
                            </se:authorize>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </section>
</section>
<script src="<c:url value="/js/main.js" />"></script>
