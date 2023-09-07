<%-- 
    Document   : customers
    Created on : Aug 17, 2023, 10:49:28 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<h1 class="text-center text-info mt-1">DANH SÁCH CHỈNH SỬA</h1>
<c:url value="/viewCustomer/admin/customers" var="action" />
<form:form modelAttribute="customers" method="post" action="${action}" enctype="multipart/form-data">
    <form:errors path="*" element="div" cssClass="alert alert-danger" />
    <form:hidden path="idCustomer" />

    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="name" id="name" placeholder="Tên Khách Hàng" name="name" />
        <label for="name">Tên Khách Hàng</label>
        <form:errors path="name" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="addressCus" id="addressCus" placeholder="Địa chỉ" name="addressCus" />
        <label for="addressCus">Địa chỉ</label>
        <form:errors path="addressCus" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="phoneNumber" id="phoneNumber" placeholder="Địa chỉ" name="phoneNumber" />
        <label for="phoneNumber">Phone</label>
        <form:errors path="phoneNumber" element="div" cssClass="text-danger" />
    </div>

    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="userName" id="userName" placeholder="Tên Đăng Nhập" name="userName" />
        <label for="userName">Tên Đăng Nhập</label>
        <form:errors path="userName" element="div" cssClass="text-danger" />
    </div>
    <c:if test="${customers.isValidUsername}">
        <!-- Không hiển thị lỗi nếu tên đăng nhập hợp lệ -->
    </c:if>
    <c:if test="${!customers.isValidUsername}">
        <div class="text-danger">Yêu cầu: ít nhất 5 ký tự, chứa ít nhất 1 chữ thường và 1 số.</div>
    </c:if>

    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="passWord" id="passWord" placeholder="Mật Khẩu" name="passWord" />
        <label for="passWord">Mật Khẩu</label>
        <form:errors path="passWord" element="div" cssClass="text-danger" />
    </div>

    <div class="form-floating mb-3 mt-3 container">
        <div class="text-center">
            <button type="submit" class="btn btn-info text-center">
                <c:choose>
                    <c:when test="${isUpdate}">Cập nhật Khách Hàng</c:when>
                    <c:otherwise>Thêm Khách Hàng</c:otherwise>
                </c:choose>
            </button>
        </div>
    </div>

</form:form>
