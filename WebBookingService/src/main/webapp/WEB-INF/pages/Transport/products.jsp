<%-- 
    Document   : products
    Created on : Aug 28, 2023, 2:54:02 PM
    Author     : Admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h1 class="text-center text-info mt-1">DANH SÁCH CHỈNH SỬA</h1>
<c:url value="/viewProduct/admin/products" var="action" />
<form:form modelAttribute="products" method="post" action="${action}" enctype="multipart/form-data">
    <form:errors path="*" element="div" cssClass="alert alert-danger" />
    <form:hidden path="idProduct" />

    <div class="form-floating mb-3 mt-3">
        <form:select path="idOrderShip" class="form-select" id="OrderShipSelect" itemValue="idOrderShip" itemLabel="idOrderShip" required="true">
            <form:option value="" label="Chọn Vé Gửi Hàng" />
            <form:options itemValue="idOrderShips" items="${orderships}" itemLabel="idOrderShips" />
        </form:select>
        <label for="coachStripsSelect">Chọn Vé Gửi Hàng</label>
        <form:errors path="idOrderShip" element="div" cssClass="text-danger" />
    </div>

    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="infor" id="infor" placeholder="thông tin" name="infor" />
        <label for="infor">Thông tin</label>
        <form:errors path="infor" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3 container">
        <div class="text-center">
            <button type="submit" class="btn btn-info text-center">
                <c:choose>
                    <c:when test="${isUpdate}">Cập nhật sản phẩm</c:when>
                    <c:otherwise>Thêm Sản Phẩm</c:otherwise>
                </c:choose>
            </button>
        </div>
    </div>
</form:form>
