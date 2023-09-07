<%-- 
    Document   : cscs
    Created on : Aug 30, 2023, 2:08:22 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h1 class="text-center text-info mt-1">DANH SÁCH CHỈNH SỬA</h1>
<c:url value="/viewCSCS/admin/cscs" var="action" />
<form:form modelAttribute="cscs" method="post" action="${action}" enctype="multipart/form-data">
    <form:errors path="*" element="div" cssClass="alert alert-danger" />
    <form:hidden path="idCSCS" />

    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="nameSeat" id="nameSeat" placeholder="Tên Ghế" name="nameSeat" />
        <label for="nameSeat">Tên Ghế</label>
        <form:errors path="nameSeat" element="div" cssClass="text-danger" />
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
        <form:select path="idStaff" class="form-select" id="idStaff" itemValue="idStaff" itemLabel="info" required="true">
            <form:option value="" label="Nhân Viên Lái Xe" />
            <form:options itemValue="idStaff" items="${staffs}" itemLabel="nameStaff" />
        </form:select>
        <label for="coachStripsSelect">Chọn Nhân Viên Lái Xe</label>
        <form:errors path="idStaff" element="div" cssClass="text-danger" />
    </div>

    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control"  path="departureTime" id="departureTime" name="Ngày Đi" placeholder="Ngày Vận Chuyển" />
        <label for="departureTime">Ngày Đi</label>
        <form:errors path="departureTime" element="div" cssClass="text-danger" />
    </div>

    <div class="form-floating mb-3 mt-3">
        <form:select path="statusSeat" class="form-select" id="statusSeat" name="statusSeat" required="true">
            <form:option value="1" label="Đã đặt" />
            <form:option value="0" label="Chưa đặt" />
        </form:select>
        <label for="statusSeat">Thông Tin Ghế</label>
        <form:errors path="statusSeat" element="div" cssClass="text-danger" />
    </div>


    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="price" id="price" placeholder="Số tiền" name="price" />
        <label for="price">Số tiền</label>
        <form:errors path="price" element="div" cssClass="text-danger" />
    </div>

    <div class="form-floating mb-3 mt-3 container">
        <div class="text-center">
            <button type="submit" class="btn btn-info text-center">
                <c:choose>
                    <c:when test="${isUpdate == true}">Cập Nhật Chuyến Đi</c:when>
                    <c:otherwise>Thêm Chuyến Đi</c:otherwise>
                </c:choose>
            </button>
        </div>
    </div>
    <script src="<c:url value="/js/main.js" />"></script>
</form:form>