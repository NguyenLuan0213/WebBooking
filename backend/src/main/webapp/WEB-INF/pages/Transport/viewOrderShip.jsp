<%-- 
    Document   : viewOrderShip
    Created on : Aug 29, 2023, 3:42:34 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="se" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<c:url value="/viewOrderShip" var="action" />
<section class="container">
    <h1 class="text-center text-success mt-1">DANH SÁCH CÁC VÉ LẤY HÀNG</h1>

    <c:if test="${pages > 1}">
        <ul class="pagination mt-1">
            <li class="page-item"><a class="page-link" href="${action}">Tất cả</a></li>
                <c:forEach begin="1" end="${pages}" var="i">
                    <c:url value="/viewOrderShip" var="pageUrl">
                        <c:param name="page" value="${i}" /> 
                    </c:url>
                <li class="page-item"><a class="page-link" href="${pageUrl}">${i}</a></li>
                </c:forEach>
        </ul>
    </c:if>


    <c:forEach items="${orderships}" var="ord">
        <c:url value="/" var="searchUrl">
            <c:param name="idOrderShips" value="${ord.idOrderShips}" /> 
        </c:url>   
        <a href="${searchUrl}" style="display: none">${ord.idTrans.idTrans}</a>
    </c:forEach>

    <section class="container">

        <div class="text-end mb-3">
            <se:authorize access="hasAnyRole('ROLE_STAFF', 'ROLE_ADMIN')">
                <c:url value="/viewOrderShip/admin/orderships/${ord.idOrderShips}" var="api" />
                <a href="${api}" class="btn btn-primary" >Thêm Chuyến Chở</a>
            </se:authorize>
        </div>

        <table class="table table-info table-hover border border-dark border-2 p-3">
            <thead>
                <tr>
                    <th class="text-center">ID</th>
                    <th class="text-center">Tên Vé</th>
                    <th class="text-center">Tên Người Bán</th>
                    <th class="text-center">Tên Người Gửi</th>
                    <th class="text-center">Tên Người Nhận</th>
                    <th class="text-center">Địa Chỉ Bán</th>
                    <th class="text-center">Ngày Bán Vé</th>
                    <th class="text-center">Thông Tin Vé</th>
                    <th class="text-center">Số lượng vận chuyển</th>
                    <th class="text-center">Số Tiền</th>
                    <th class="text-center">Tên Xe Vận Chuyển</th>
                    <th class="text-center">Tên Nhà Xe Vận Chuyển</th>
                    <th class="text-center">Chức Năng</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${orderships}" var="ord">
                    <tr>
                        <th scope="row" class=" text-center">${ord.idOrderShips}</th>
                        <td class=" text-center">${ord.idTrans.idTrans}</td>
                        <td class=" text-center">${ord.idStaff.nameStaff}</td>
                        <td class=" text-center">${ord.idCusSender.name}</td>
                        <td class=" text-center">${ord.idCusAccpect.name}</td>
                        <td class=" text-center">${ord.idStationBuy.name}</td>
                        <td class="text-center">
                            <fmt:formatDate value="${ord.dateOrder}" pattern="yyyy-MM-dd HH:mm:ss" />
                        </td>
                        <td class=" text-center">
                            <c:choose>
                                <c:when test="${ord.statusOrder == 1}">
                                    Đã Chuyển
                                </c:when>
                                <c:when test="${ord.statusOrder == 0}">
                                    Chưa chuyển
                                </c:when>
                                    <c:when test="${ord.statusOrder == 2}">
                                    Đang Chuyển
                                </c:when>
                                <c:otherwise>
                                    Trạng thái không xác định
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td class=" text-center">${ord.amount}</td>
                        <td class=" text-center">${ord.amountSent}</td>
                        <td class=" text-center">${ord.idTrans.idCoach.numberCoach}</td>
                        <td class=" text-center">${ord.idTrans.idGarage.nameGara}</td>
                        <td class="text-center" >
                            <se:authorize access="hasAnyRole('ROLE_STAFF', 'ROLE_ADMIN')">
                                <c:url value="/viewOrderShip/admin/orderships/${ord.idOrderShips}" var="api" />
                                <a href="${api}" class="btn btn-success">Cập nhật</a>
                                <a class="btn btn-danger" onclick="deleteEntry('orderships', '${api}')">Xóa</a>
                            </se:authorize>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </section>
</section>
<script src="<c:url value="/js/main.js" />"></script>
