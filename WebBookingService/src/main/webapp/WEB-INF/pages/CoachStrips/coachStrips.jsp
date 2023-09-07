<%-- 
    Document   : coachStrips
    Created on : Aug 8, 2023, 3:51:14 PM
    Author     : Admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<h1 class="text-center text-info mt-1">DANH SÁCH CHỈNH SỬA</h1>
<c:url value="/viewCoachStrip/admin/coachStrips" var="action" />
<form:form modelAttribute="coachStrips" method="post" action="${action}" enctype="multipart/form-data">
    <form:errors path="*" element="div" cssClass="alert alert-danger" />
    <form:hidden path="idCoachStrips" />
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="distance" id="distance" placeholder="Khoảng Cách" name="distance" />
        <label for="distance">khoảng Cách</label>
        <form:errors path="distance" element="div" cssClass="text-danger" />
    </div>
    
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="nameCS" id="nameCS" placeholder="Tên Chuyến Đi" name="nameCS" />
        <label for="nameCS">Tên Chuyến Đi</label>
        <form:errors path="nameCS" element="div" cssClass="text-danger" />
    </div>

    <div class="form-floating mb-3 mt-3">
        <form:select class="form-control" path="idStationsStart.idStations" id="idStationsStart" name="idStationsStart">
            <form:option value="" label="Chọn Bến Đi" />
            <form:options items="${stations}" itemValue="idStations" itemLabel="name" />
        </form:select>
        <label for="idStationsStart">Bến Đi</label>
        <form:errors path="idStationsStart" element="div" cssClass="text-danger" />
    </div>

    <div class="form-floating mb-3 mt-3">
        <form:select class="form-control" path="idStationsEnd.idStations" id="idStationsEnd" name="idStationsEnd">
            <form:option value="" label="Chọn Bến Dừng" />
            <form:options items="${stations}" itemValue="idStations" itemLabel="name" />
        </form:select>
        <label for="idStationsEnd">Bến Dừng</label>
        <form:errors path="idStationsEnd" element="div" cssClass="text-danger" />
    </div>

</div>
<div class="form-floating mb-3 mt-3 container">
    <input type="hidden" name="idCoachStrips" value="${cStrips.idCoachStrips}"/>
    <div class="text-center">
        <button type="submit" class="btn btn-info">
            <c:choose>
                <c:when test="${isUpdate}">Cập nhật sản phẩm</c:when>
                <c:otherwise>Thêm Chuyến Xe</c:otherwise>
            </c:choose>
        </button>
    </div>
</div>

</form:form>

