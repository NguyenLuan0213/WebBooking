<%-- 
    Document   : coach
    Created on : Aug 7, 2023, 1:31:22 AM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<h1 class="text-center text-info mt-1">DANH SÁCH CHỈNH SỬA</h1>
<c:url value="/viewCoach/admin/coachs" var="action" />
<form:form modelAttribute="coachs" method="post" action="${action}" enctype="multipart/form-data">
    <form:errors path="*" element="div" cssClass="alert alert-danger" />
    <form:hidden path="idCoach" />
    <div class="form-floating mb-3 mt-3">
        <form:input type="file" class="form-control" path="file"  />
        <label for="imgCoach">Ảnh sản phẩm</label>
        <c:if test="${coachs.imgCoach != null}">
            <img src="${coachs.imgCoach}" width="120" />
        </c:if>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="numberCoach" id="numberCoach" placeholder="Số xe" name="numberCoach" />
        <label for="numberCoach">Số xe</label>
        <form:errors path="numberCoach" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="capacity" id="capacity" placeholder="Loại xe" name="capacity" />
        <label for="capacity">Số ghế</label>
        <form:errors path="capacity" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="typeOfCoach.nameTypeOfCoach" id="typeOfCoach" placeholder="Loại xe" name="typeOfCoach" />
        <label for="typeOfCoach.nameTypeOfCoach">Loại xe</label>
        <form:errors path="typeOfCoach.nameTypeOfCoach" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:select class="form-control" path="idGarage.idGarage">
            <form:option value="" label="Chọn Nhà Xe" />
            <form:options items="${garages}" itemValue="idGarage" itemLabel="nameGara" />
        </form:select>
        <form:errors path="idGarage.idGarage" element="div" cssClass="text-danger" />
    </div>


    <div class="form-floating mb-3 mt-3 container">
        <div class="text-center">
            <button type="submit" class="btn btn-info text-center">
                <c:choose>
                    <c:when test="${isUpdate}">Cập nhật xe</c:when>
                    <c:otherwise>Thêm Xe</c:otherwise>
                </c:choose>
            </button>
        </div>
    </div>

</form:form>