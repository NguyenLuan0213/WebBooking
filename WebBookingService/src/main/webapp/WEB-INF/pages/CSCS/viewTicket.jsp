<%-- 
    Document   : viewTicket
    Created on : Aug 30, 2023, 4:32:38 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="se" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<style>
    .pagination-list {
        display: flex;
        flex-wrap: wrap;
        list-style-type: none;
        padding: 0;
        margin: 0;
    }

    .pagination-list-item {
        margin-right: 5px; /* Điều chỉnh khoảng cách theo ý muốn */
    }
</style>
<c:url value="/viewTicket" var="action" />
<section class="container">
    <h1 class="text-center text-success mt-1">DANH SÁCH CÁC VÉ ĐI</h1>

    <c:if test="${pages > 1}">
        <ul class="pagination mt-1 pagination-list">
            <li class="page-item"><a class="page-link" href="${action}">Tất cả</a></li>
                <c:forEach begin="1" end="${pages}" var="i">
                    <c:url value="/viewTicket" var="pageUrl">
                        <c:param name="page" value="${i}" /> 
                    </c:url>
                <li class="page-item pagination-list-item"><a class="page-link ${i % 30 == 0 && i < pages ? 'page-link-spacer' : ''}" href="${pageUrl}">${i}</a></li>
                </c:forEach>
        </ul>
    </c:if>

    <c:forEach items="${tickets}" var="tk">
        <c:url value="/" var="searchUrl">
            <c:param name="idTicket" value="${tk.idTicket}" /> 
        </c:url>   
        <a href="${searchUrl}" style="display: none">${tk.idCoachStripCoachSeat.nameSeat}</a>
    </c:forEach>

    <section class="container">

        <div class="text-end mb-3">
            <se:authorize access="hasAnyRole('ROLE_STAFF', 'ROLE_ADMIN')">
                <c:url value="/viewTicket/admin/tickets/${tk.idTicket}" var="api" />
                <a href="${api}" class="btn btn-primary" >Thêm Vé Đi</a>
            </se:authorize>
        </div>

        <table class="table table-info table-hover border border-dark border-2 p-3">
            <thead>
                <tr>
                    <th class="text-center">ID</th>
                    <th class="text-center">Tên Vé</th>
                    <th class="text-center">Tên Nhân Viên Bán Vé</th>
                    <th class="text-center">Tên Người Mua Vé</th>
                    <th class="text-center">Địa Chỉ Bán</th>
                    <th class="text-center">Ngày Bán Vé</th>
                    <th class="text-center">Tên Xe</th>
                    <th class="text-center">Thông Tin Vé</th>
                    <th class="text-center">Số Tiền</th>
                    <th class="text-center">Chức Năng</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${tickets}" var="tk">
                    <tr>
                        <th scope="row" class=" text-center">${tk.idTicket}</th>
                        <td class=" text-center">${tk.idCoachStripCoachSeat.idCSCS}</td>
                        <td class=" text-center">${tk.idStaff.nameStaff}</td>
                        <td class=" text-center">${tk.idCustomer.name}</td>
                        <td class=" text-center">${tk.idStationBuy.name}</td>
                        <td class="text-center">
                            <fmt:formatDate value="${tk.bookingDate}" pattern="yyyy-MM-dd HH:mm:ss" />
                        </td>
                        <td class=" text-center">${tk.idCoachStripCoachSeat.idCoach.numberCoach}</td> 
                        <td class=" text-center">
                            <c:choose>
                                <c:when test="${tk.status == 1}">
                                    Đã Lấy Vé
                                </c:when>
                                <c:when test="${tk.status == 0}">
                                    Hết Hiệu Lực
                                </c:when>
                                <c:when test="${tk.status == 2}">
                                    Chưa Lấy vé
                                </c:when>
                                <c:otherwise>
                                    Trạng thái không xác định
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td class=" text-center">${tk.idCoachStripCoachSeat.price}</td>
                        <td>
                            <se:authorize access="hasAnyRole('ROLE_STAFF', 'ROLE_ADMIN')">
                                <c:url value="/viewTicket/admin/tickets/${tk.idTicket}" var="api" />
                                <a href="${api}" class="btn btn-success">Cập nhật</a>
                                <a class="btn btn-danger" onclick="deleteEntry('tickets', '${api}')">Xóa</a>
                            </se:authorize>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </section>
</section>
<script src="<c:url value="/js/main.js" />"></script>
