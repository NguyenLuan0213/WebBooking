<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h1 class="text-center text-info mt-1">DANH SÁCH CHỈNH SỬA</h1>
<c:url value="/viewComment/admin/comments" var="action" />
<form:form modelAttribute="comments" method="post" action="${action}" enctype="multipart/form-data">
    <form:errors path="*" element="div" cssClass="alert alert-danger" />
    <form:hidden path="idComment" />

    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="idCustomer.name" id="idCustomer" placeholder="idCustomer" name="idCustomer" />
        <label for="idCustomer.name">Tên Khách Hàng</label>
        <form:errors path="idCustomer.name" element="div" cssClass="text-danger" />
    </div>

    <div class="form-floating mb-3 mt-3">
        <form:select path="idGarage" class="form-select" id="garageSelect" itemValue="idGarage">
            <form:option value="" label="Chọn nhà xe" />
            <form:options itemValue="idGarage" items="${garages}" itemLabel="nameGara" />
        </form:select>
        <label for="garageSelect">Tên Nhà Xe</label>
        <form:errors path="idGarage" element="div" cssClass="text-danger" />
    </div>
    
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="comments" id="comments" placeholder="comments" name="comments" />
        <label for="comments">Bình Luận</label>
        <form:errors path="comments" element="div" cssClass="text-danger" />
    </div>

    <div class="form-floating mb-3 mt-3 container">
        <div class="text-center">
            <button type="submit" class="btn btn-info text-center">
                <c:choose>
                    <c:when test="${isUpdate}">Cập nhật bình luận</c:when>
                    <c:otherwise>Thêm Bình Luận</c:otherwise>
                </c:choose>
            </button>
        </div>
    </div>
</form:form>
