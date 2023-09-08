<%-- 
    Document   : transts
    Created on : Aug 28, 2023, 10:20:25 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h1 class="text-center text-info mt-1">DANH SÁCH CHỈNH SỬA</h1>
<c:url value="/viewTransporttruck/admin/transts" var="action" />
<form:form modelAttribute="transts" method="post" action="${action}" enctype="multipart/form-data">
    <form:errors path="*" element="div" cssClass="alert alert-danger" />
    <form:hidden path="idTrans" />

    <div class="form-floating mb-3 mt-3">
        <form:select path="idGarage" class="form-select" id="garaSelect" itemValue="idGarage" itemLabel="info" required="true">
            <form:option value="" label="Chọn Nhà Xe" />
            <form:options itemValue="idGarage" items="${garages}" itemLabel="nameGara" />
        </form:select>
        <label for="coachSelect">Chọn Xe</label>
        <form:errors path="idGarage" element="div" cssClass="text-danger" />
    </div>

    <div class="form-floating mb-3 mt-3">
        <form:select path="idCoachStrips" class="form-select" id="coachStripsSelect" itemValue="idCoachStrips" itemLabel="info" required="true">
            <form:option value="" label="Chọn chuyến xe" />
            <form:options itemValue="idCoachStrips" items="${coachstrips}" itemLabel="nameCS" />
        </form:select>
        <label for="coachStripsSelect">Chọn Chuyến Xe</label>
        <form:errors path="idCoachStrips" element="div" cssClass="text-danger" />
    </div>

    <div class="form-floating mb-3 mt-3">
        <form:select path="idCoach" class="form-select" id="coachSelect" itemValue="idCoach" itemLabel="info" required="true">
            <form:option value="" label="Chọn xe" />
            <form:options itemValue="idCoach" items="${coachs}" itemLabel="numberCoach" />
        </form:select>
        <label for="coachSelect">Chọn Xe</label>
        <form:errors path="idCoach" element="div" cssClass="text-danger" />
    </div>

    <div class="form-floating mb-3 mt-3">
        <form:select path="idStaffDrive" class="form-select" id="idStaffDrive" itemValue="idStaffDrive" itemLabel="info" required="true">
            <form:option value="" label="Nhân Viên Lái Xe" />
            <form:options itemValue="idStaff" items="${staffs}" itemLabel="nameStaff" />
        </form:select>
        <label for="coachStripsSelect">Chọn Nhân Viên Lái Xe</label>
        <form:errors path="idStaffDrive" element="div" cssClass="text-danger" />
    </div>

    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control"  path="dateTrans" id="dateTrans" name="dateTrans" placeholder="Ngày Vận Chuyển" />
        <label for="dateTrans">Ngày Vận Chuyển</label>
        <form:errors path="dateTrans" element="div" cssClass="text-danger" />
    </div>
    
    <div class="form-floating mb-3 mt-3">
        <form:select path="accpectSent" class="form-select" id="accpectSent" name="accpectSent" required="true">
            <form:option value="1" label="Cho Chuyển" />
            <form:option value="0" label="Chưa Cho Phép" />
        </form:select>
        <label for="statusSeat">Thông Tin Ghế</label>
        <form:errors path="accpectSent" element="div" cssClass="text-danger" />
    </div>

    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="priceTran" id="priceTran" placeholder="Quyền chuyển" name="priceTran" />
        <label for="priceTran">Số Tiền 1 Lần Chuyển</label>
        <form:errors path="priceTran" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3 container">
        <div class="text-center">
            <button type="submit" class="btn btn-info text-center">
                <c:choose>
                    <c:when test="${transts.idTrans != null}">Cập Nhật Chuyến Chở</c:when>
                    <c:otherwise>Thêm Chuyến Chở</c:otherwise>
                </c:choose>
            </button>
        </div>
    </div>
    <script src="<c:url value="/js/main.js" />"></script>
</form:form>