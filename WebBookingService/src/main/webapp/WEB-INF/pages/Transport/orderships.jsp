<%-- 
    Document   : orderships
    Created on : Aug 29, 2023, 3:42:44 PM
    Author     : Admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h1 class="text-center text-info mt-1">DANH SÁCH CHỈNH SỬA</h1>
<c:url value="/viewOrderShip/admin/orderships" var="action" />
<form:form modelAttribute="orderships" method="post" action="${action}" enctype="multipart/form-data">
    <form:errors path="*" element="div" cssClass="alert alert-danger" />
    <form:hidden path="idOrderShips" />

    <div class="form-floating mb-3 mt-3">
        <form:select path="idTrans" class="form-select" id="TransSelect" itemValue="idTrans" itemLabel="info" required="true">
            <form:option value="" label="Chọn Chuyến Gửi Hàng" />
            <form:options itemValue="idTrans" items="${trans}" itemLabel="name" />
        </form:select>
        <label for="coachStripsSelect">Chọn Chuyến Gửi Hàng</label>
        <form:errors path="idTrans" element="div" cssClass="text-danger" />
    </div>

    <div class="form-floating mb-3 mt-3">
        <form:select path="idCusSender" class="form-select" id="idCusSender" itemValue="idCusSender" itemLabel="info" required="true">
            <form:option value="" label="Người Gửi" />
            <form:options itemValue="idCustomer" items="${customers}" itemLabel="name" />
        </form:select>
        <label for="coachStripsSelect">Người Gửi</label>
        <form:errors path="idCusSender" element="div" cssClass="text-danger" />
    </div>

    <div class="form-floating mb-3 mt-3">
        <form:select path="idCusAccpect" class="form-select" id="idCusAccpect" itemValue="idCusAccpect" itemLabel="info" required="true">
            <form:option value="" label="Người Nhận" />
            <form:options itemValue="idCustomer" items="${customers}" itemLabel="name" />
        </form:select>
        <label for="coachStripsSelect">Người Nhận</label>
        <form:errors path="idCusAccpect" element="div" cssClass="text-danger" />
    </div>

    <div class="form-floating mb-3 mt-3">
        <form:select path="idStationBuy" class="form-select" id="StationBuySelect" itemValue="idStationBuy" itemLabel="info" required="true">
            <form:option value="" label="Chọn Bến Xe" />
            <form:options itemValue="idStations" items="${stations}" itemLabel="name" />
        </form:select>
        <label for="coachStripsSelect">Chọn Bến Xe Bán Vé</label>
        <form:errors path="idStationBuy" element="div" cssClass="text-danger" />
    </div>

    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control"  path="dateOrder" id="dateOrder" name="dateOrder" placeholder="Ngày Bán Vé" />
        <label for="dateOrder">Ngày Bán Vé</label>
        <form:errors path="dateOrder" element="div" cssClass="text-danger" />
    </div>

    <div class="form-floating mb-3 mt-3">
        <form:select path="idStaff" class="form-select" id="idStaff" itemValue="idStaff" itemLabel="info" required="true">
            <form:option value="" label="Nhân Viên Bán Vé" />
            <form:options itemValue="idStaff" items="${staffs}" itemLabel="nameStaff" />
        </form:select>
        <label for="coachStripsSelect">Chọn Nhân Viên Bán Vé</label>
        <form:errors path="idStaff" element="div" cssClass="text-danger" />
    </div>

    <div class="form-floating mb-3 mt-3">
        <form:select path="statusOrder" class="form-select" id="statusOrder" name="statusOrder" required="true">
            <form:option value="1" label="Đã Chuyển" />
            <form:option value="0" label="Chưa Chuyển" />
            <form:option value="2" label="Đang Chuyển" />
        </form:select>
        <label for="statusSeat">Thông Tin Vé</label>
        <form:errors path="statusOrder" element="div" cssClass="text-danger" />
    </div> 
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="amount" id="amount" placeholder="Số lượng vận chuyển" name="amount" />
        <label for="amount">Số lượng vận chuyển</label>
        <form:errors path="amount" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="amountSent" id="amountSent" placeholder="Tổng tiền" name="amountSent" />
        <label for="amountSent">Tổng Tiền</label>
        <form:errors path="amountSent" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3 container">
        <div class="text-center">
            <button type="submit" class="btn btn-info text-center">
                <c:choose>
                    <c:when test="${isUpdate == true}">Cập Nhật Vé</c:when>
                    <c:otherwise>Thêm Vé</c:otherwise>
                </c:choose>
            </button>
        </div>
    </div>
    <script src="<c:url value="/js/main.js" />"></script>
</form:form>