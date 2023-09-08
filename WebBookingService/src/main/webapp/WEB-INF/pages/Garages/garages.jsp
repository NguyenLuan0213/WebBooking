<%-- 
    Document   : garages
    Created on : Aug 23, 2023, 7:15:53 PM
    Author     : Admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="se" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h1 class="text-center text-info mt-1">DANH SÁCH CHỈNH SỬA</h1>
<c:url value="/viewGarage/admin/garages" var="action" />
<form:form modelAttribute="garages" method="post" action="${action}" enctype="multipart/form-data">
    <form:errors path="*" element="div" cssClass="alert alert-danger" />
    <form:hidden path="idGarage" />
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="idStaff.nameStaff" id="name" placeholder="Tên Người Quản Lý" name="name" />
        <label for="idStaff.nameStaff">Tên Người Quản Lý</label>
        <form:errors path="idStaff.nameStaff" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="nameGara" id="name" placeholder="Tên Nhà Xe" name="nameGara" />
        <label for="nameGara">Tên Nhà Xe</label>
        <form:errors path="nameGara" element="div" cssClass="text-danger" />
        <form:errors path="addressGarage" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="addressGarage" id="addressGarage" placeholder="Địa chỉ" name="addressGarage" />
        <label for="addressGarage">Địa chỉ</label>
        <form:errors path="addressGarage" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3 container">
        <div class="text-center">
            <button type="submit" class="btn btn-info text-center">
                <c:choose>
                    <c:when test="${isUpdate}">Cập nhật Nhà Xe</c:when>
                    <c:otherwise>Thêm Nhà Xe</c:otherwise>
                </c:choose>
            </button>
        </div>
    </div>

</form:form>