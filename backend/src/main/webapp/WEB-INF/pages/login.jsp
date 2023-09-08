<%-- 
    Document   : login
    Created on : Aug 13, 2023, 3:36:56 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:if test="${param.error != null}">
    <div class="alert alert-danger">
        Đã có lỗi!! Vui lòng đăng nhập lại!
    </div>
</c:if>

<c:if test="${param.accessDenied != null}">
    <div class="alert alert-danger">
        Không có quyền chỉnh sửa!
    </div>
</c:if>
<c:url value="/login" var="action" />
<form method="post" action="${action}">
    <div class="form-floating mb-3 mt-3">
        <input type="text" class="form-control" id="name" placeholder="Tên đăng nhập" name="userName">
        <label for="name">Tên đăng nhập</label>
    </div>

    <div class="form-floating mt-3 mb-3">
        <input type="password" class="form-control" id="pwd" placeholder="Mật khẩu" name="passWord">
        <label for="pwd">Mật khẩu</label>
    </div>
    <div class="form-floating mt-3 mb-3 text-center">
        <input type="submit" value="Đăng nhập" class="btn btn-danger" />
    </div>
</form>