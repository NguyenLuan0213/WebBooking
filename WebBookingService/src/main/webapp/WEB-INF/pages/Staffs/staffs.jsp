<%-- 
    Document   : staffs
    Created on : Aug 16, 2023, 10:57:06 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<h1 class="text-center text-info mt-1">DANH SÁCH CHỈNH SỬA</h1>
<c:url value="/viewStaff/admin/staffs" var="action" />
<form:form modelAttribute="staffs" method="post" action="${action}" enctype="multipart/form-data">
    <form:errors path="*" element="div" cssClass="alert alert-danger" />
    <input type="hidden" name="_csrf.parameterName" value="$_crsf.token">
    <form:hidden path="idStaff" />
    <div class="form-floating mb-3 mt-3">
        <form:input type="file" class="form-control" path="file"  />
        <label for="imgStaff">Ảnh sản phẩm</label>
        <c:if test="${staf.imgStaff != null}">
            <img src="${staf.imgStaff}" width="120" />
        </c:if>
    </div>

    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="nameStaff" id="nameStaff" placeholder="Tên Nhân viên" name="nameStaff" />
        <label for="nameStaff">Tên Nhân Viên</label>
        <form:errors path="nameStaff" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="gender" id="gender" placeholder="Giới Tính" name="gender" />
        <label for="gender">Giới tính</label>
        <form:errors path="gender" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="phone" id="phone" placeholder="SĐT" name="phone" />
        <label for="phone">SĐT</label>
        <form:errors path="phone" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="date" class="form-control" path="brithStaff" id="brithStaff" name="brithStaff" />
        <label for="brithStaff">Ngày Sinh</label>
        <form:errors path="brithStaff" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="addressUser" id="addressUser" placeholder="Nơi Sinh" name="addressUser" />
        <label for="addressUser">Nơi Sinh</label>
        <form:errors path="addressUser" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="roles" id="roles" placeholder="Chức Vụ" name="roles" />
        <label for="roles">Chức Vụ</label>
        <form:errors path="roles" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="userName" id="userName" placeholder="Tên Đăng Nhập" name="userName" />
        <label for="userName">Tên Đăng Nhập</label>
        <form:errors path="userName" element="div" cssClass="text-danger" />
        <c:if test="${!staffs.isValidUsername}">
            <!-- Không hiển thị lỗi nếu tên đăng nhập hợp lệ -->
        </c:if>
        <c:if test="${staffs.isValidUsername}">
            <div class="text-danger">Yêu cầu: ít nhất 5 ký tự, chứa ít nhất 1 chữ thường và 1 số.</div>
        </c:if>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="passWord" id="passWord" placeholder="Mật Khẩu" name="passWord" />
        <label for="passWord">Mật Khẩu</label>
        <form:errors path="passWord" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3 container">
        <div class="text-center">
            <button type="submit" class="btn btn-info text-center">
                <c:choose>
                    <c:when test="${staffs.idStaff != null}">Cập nhật nhân viên</c:when>
                    <c:otherwise>Thêm Nhân viên</c:otherwise>
                </c:choose>
            </button>
        </div>
    </div>


</form:form>
