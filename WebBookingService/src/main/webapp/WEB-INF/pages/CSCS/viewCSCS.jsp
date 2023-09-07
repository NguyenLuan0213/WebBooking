<%-- 
    Document   : viewCSCS
    Created on : Aug 30, 2023, 2:08:07 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="se" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<c:url value="/viewCSCS" var="action" />
<section class="container">
    <h1 class="text-center text-success mt-1">DANH SÁCH CÁC GHẾ XE</h1>

    <c:if test="${pages > 1}">
        <ul class="pagination mt-1">
            <li class="page-item"><a class="page-link" href="${action}">Tất cả</a></li>
                <c:forEach begin="1" end="${pages}" var="i">
                    <c:url value="/viewCSCS" var="pageUrl">
                        <c:param name="page" value="${i}" /> 
                    </c:url>
                <li class="page-item"><a class="page-link" href="${pageUrl}">${i}</a></li>
                </c:forEach>
        </ul>
    </c:if>


    <c:forEach items="${cscs}" var="csc">
        <c:url value="/" var="searchUrl">
            <c:param name="idCSCS" value="${csc.idCSCS}" /> 
        </c:url>   
        <a href="${searchUrl}" style="display: none">${csc.nameSeat}</a>
    </c:forEach>

    <section class="container">
        <div class="text-end mb-3">
            <se:authorize access="hasAnyRole('ROLE_STAFF', 'ROLE_ADMIN')">
                <c:url value="/viewCSCS/admin/cscs/${csc.idCSCS}" var="api" />
                <a href="${api}" class="btn btn-primary" >Thêm Chuyến Chở</a>
            </se:authorize>
        </div>
        <table class="table table-info table-hover border border-dark border-2 p-3">
            <thead>
                <tr>
                    <th class="text-center">ID</th>
                    <th class="text-center">Tên Ghế</th>
                    <th class="text-center">Tên Chuyến Xe</th>
                    <th class="text-center">Tên Xe</th>
                    <th class="text-center">Tên Người Lái</th>
                    <th class="text-center">Ngày Đi</th>
                    <th class="text-center">Thông Tin Vé</th>
                    <th class="text-center">Số Tiền</th>
                    <th class="text-center">Chức Năng</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${cscs}" var="csc">
                    <tr>
                        <th scope="row" class=" text-center">${csc.idCSCS}</th>
                        <td class=" text-center">${csc.nameSeat}</td>
                        <td class=" text-center">${csc.idCoachStrips.nameCS}</td>
                        <td class=" text-center">${csc.idCoach.numberCoach}</td>
                        <td class=" text-center">${csc.idStaff.nameStaff}</td>
                        <td class="text-center">
                            <fmt:formatDate value="${csc.departureTime}" pattern="yyyy-MM-dd HH:mm:ss" />
                        <td class="text-center">
                            <c:choose>
                                <c:when test="${csc.statusSeat == 1}">
                                    Đã Đặt
                                </c:when>
                                <c:when test="${csc.statusSeat == 0}">
                                    Chưa Đặt
                                </c:when>
                                <c:otherwise>
                                    Trạng thái không xác định
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td class=" text-center">${csc.price}</td>
                        <td class="text-center" >
                            <se:authorize access="hasAnyRole('ROLE_STAFF', 'ROLE_ADMIN')">
                                <c:url value="/viewCSCS/admin/cscs/${csc.idCSCS}" var="api" />
                                <a href="${api}" class="btn btn-success">Cập nhật</a>
                                <a class="btn btn-danger" onclick="deleteEntry('cscs', '${api}')">Xóa</a>
                            </se:authorize>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </section>
</section>
<script src="<c:url value="/js/main.js" />"></script>
