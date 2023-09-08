<%-- 
    Document   : tickets
    Created on : Aug 30, 2023, 4:32:27 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h1 class="text-center text-info mt-1">DANH SÁCH CHỈNH SỬA</h1>
<c:url value="/viewTicket/admin/tickets" var="action" />
<form:form modelAttribute="tickets" method="post" action="${action}" enctype="multipart/form-data">
    <form:errors path="*" element="div" cssClass="alert alert-danger" />
    <form:hidden path="idTicket" />

    <div class="form-floating mb-3 mt-3">
        <form:select path="idCoachStripCoachSeat" class="form-select" id="idCoachStripCoachSeat" itemValue="idCoachStripCoachSeat" itemLabel="info" required="true">
            <form:option value="" label="Chọn Vé Đi" />
            <form:options itemValue="idCSCS" items="${cscs}" itemLabel="idCSCS" />
        </form:select>
        <label for="coachStripsSelect">Chọn Vé Đi</label>
        <form:errors path="idCoachStripCoachSeat" element="div" cssClass="text-danger" />
    </div>

    <div class="form-floating mb-3 mt-3">
        <form:select path="idCustomer" class="form-select" id="idCustomer" itemValue="idCustomer" itemLabel="info" required="true">
            <form:option value="" label="Khách Hàng" />
            <form:options itemValue="idCustomer" items="${customers}" itemLabel="name" />
        </form:select>
        <label for="coachStripsSelect">Chọn Tên Khách Hàng </label>
        <form:errors path="idCustomer" element="div" cssClass="text-danger" />
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
        <form:input type="text" class="form-control"  path="bookingDate" id="bookingDate" name="bookingDate" placeholder="Ngày Bán Vé" />
        <label for="bookingDate">Ngày Bán Vé</label>
        <form:errors path="bookingDate" element="div" cssClass="text-danger" />
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
        <form:select path="status" class="form-select" id="status" name="status" required="true">
            <form:option value="1" label="Đã Chuyển" />
            <form:option value="0" label="Chưa Chuyển" />
            <form:option value="2" label="Đang Chuyển" />
        </form:select>
        <label for="status">Thông Tin Vé</label>
        <form:errors path="status" element="div" cssClass="text-danger" />
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