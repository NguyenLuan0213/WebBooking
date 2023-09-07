<%-- 
    Document   : viewCustomer
    Created on : Aug 17, 2023, 10:48:56 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="se" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:url value="/viewCustomer" var="action" />
<section class="container">
    <h1 class="text-center text-success mt-1">DANH SÁCH CÁC KHÁCH HÀNG</h1>

    <c:if test="${pages > 1}">
        <ul class="pagination mt-1">
            <li class="page-item"><a class="page-link" href="${action}">Tất cả</a></li>
                <c:forEach begin="1" end="${pages}" var="i">
                    <c:url value="/viewCustomer" var="pageUrl">
                        <c:param name="page" value="${i}" /> 
                    </c:url>
                <li class="page-item"><a class="page-link" href="${pageUrl}">${i}</a></li>
                </c:forEach>
        </ul>
    </c:if>


    <c:forEach items="${customers}" var="cus">
        <c:url value="/" var="searchUrl">
            <c:param name="idCustomer" value="${cus.idCustomer}" /> 
        </c:url>   
        <a href="${searchUrl}" style="display: none">${cus.name||cus.phoneNumber||cus.addressCus}</a>
    </c:forEach>

    <div class="text-end mb-3">
        <se:authorize access="hasAnyRole('ROLE_STAFF', 'ROLE_ADMIN')">
            <c:url value="/viewCustomer/admin/customers/${cus.idCustomer}" var="api" />
            <a href="${api}" class="btn btn-primary" >Thêm Khách Hàng</a>
        </se:authorize>
    </div>

    <section class="container">
        <table class="table table-info table-hover border border-dark border-2 p-3">
            <thead>
                <tr>
                    <th class="text-center">ID</th>
                    <th class="text-center">Tên Khách Hàng</th>
                    <th class="text-center">SĐT</th>
                    <th class="text-center">Địa Chỉ</th>
                        <se:authorize access="hasAnyRole('ROLE_STAFF', 'ROLE_ADMIN')">
                        <th class="text-center">Tên Đăng Nhập</th>
                        <th class="text-center">Mật Khẩu</th>
                        </se:authorize>
                    <th class="text-center">Chức Năng</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${customers}" var="cus">
                    <tr>
                        <th scope="row" class=" text-center">${cus.idCustomer}</th>
                        <td class="text-center">${cus.name}</td>
                        <td class="text-center">${cus.phoneNumber}</td>
                        <td class="text-center">${cus.addressCus}</td>

                        <se:authorize access="hasAnyRole('ROLE_STAFF', 'ROLE_ADMIN')">
                            <td class="text-center">${cus.userName}</td>
                            <td class="text-center">${cus.passWord}</td>
                            <td class="text-center" >
                                <c:url value="/viewCustomer/admin/customers/${cus.idCustomer}" var="api" />
                                <a href="${api}" class="btn btn-success">Cập nhật</a>
                                <a class="btn btn-danger" onclick="deleteEntry('customers', '${api}')">Xóa</a>
                            </se:authorize>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <div class="text-end">
            <se:authorize access="hasAnyRole('ROLE_STAFF', 'ROLE_ADMIN')">
                <c:url value="/viewCustomer/admin/customers/${cus.idCustomer}" var="api" />
                <a href="${api}" class="btn btn-primary" >Thêm Khách Hàng</a>
            </se:authorize>
        </div>
    </section>
</section>
<script src="<c:url value="/js/main.js" />"></script>
