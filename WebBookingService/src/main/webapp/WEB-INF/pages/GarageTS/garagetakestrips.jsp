<%-- 
    Document   : garagetakestrips
    Created on : Aug 24, 2023, 12:45:13 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="se" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h1 class="text-center text-info mt-1">DANH SÁCH CHỈNH SỬA</h1>
<c:url value="/viewGarageTS/admin/garagetakestrips" var="action" />
<form:form modelAttribute="garagetakestrips" method="post" action="${action}" enctype="multipart/form-data">
    <form:errors path="*" element="div" cssClass="alert alert-danger" />
    <form:hidden path="idGTS" />

    <div class="form-floating mb-3 mt-3">
        <form:select path="idGarage" class="form-select" id="garageSelect" itemValue="idGarage">
            <form:option value="" label="Chọn nhà xe" />
            <form:options itemValue="idGarage" items="${garages}" itemLabel="nameGara" />
        </form:select>
        <label for="garageSelect">Tên Nhà Xe</label>
        <form:errors path="idGarage" element="div" cssClass="text-danger" />
    </div>

    <div class="form-floating mb-3 mt-3">
        <form:select path="idCoachStrips" class="form-select" id="coachStripsSelect" itemValue="idCoachStrips" itemLabel="info">
            <form:option value="" label="Chọn chuyến xe" />
            <form:options itemValue="idCoachStrips" items="${coachstrips}" itemLabel="nameCS" />
        </form:select>
        <label for="coachStripsSelect">Chọn Chuyến Xe</label>
        <form:errors path="idCoachStrips" element="div" cssClass="text-danger" />
    </div>


    <div class="form-floating mb-3 mt-3 container">
        <div class="text-center">
            <button type="submit" class="btn btn-info text-center">
                <c:choose>
                    <c:when test="${isUpdate}">Cập nhật sản phẩm</c:when>
                    <c:otherwise>Thêm Bến</c:otherwise>
                </c:choose>
            </button>
        </div>
    </div>
</form:form>
