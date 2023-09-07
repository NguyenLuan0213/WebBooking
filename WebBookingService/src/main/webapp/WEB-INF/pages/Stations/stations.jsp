<%-- 
    Document   : stations
    Created on : Aug 9, 2023, 10:58:59 PM
    Author     : Admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h1 class="text-center text-info mt-1">DANH SÁCH CHỈNH SỬA</h1>
<c:url value="/viewStation/admin/stations" var="action" />
<form:form modelAttribute="stations" method="post" action="${action}" enctype="multipart/form-data">
    <form:errors path="*" element="div" cssClass="alert alert-danger" />
    <form:hidden path="idStations" />
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="name" id="name" placeholder="Tên bến" name="name" />
        <label for="name">Tên bến</label>
        <form:errors path="name" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="address" id="address" placeholder="Địa chỉ" name="address" />
        <label for="address">Địa chỉ</label>
        <form:errors path="address" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3 container">
        <div class="text-center">
            <button type="submit" class="btn btn-info text-center">
                <c:choose>
                    <c:when test="${isUpdate}">Cập nhật bến xe</c:when>
                    <c:otherwise>Thêm Bến</c:otherwise>
                </c:choose>
            </button>
        </div>
    </div>
</form:form>
