<%-- 
    Document   : viewStaff
    Created on : Aug 16, 2023, 10:56:39 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="se" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<style>
    .table-scrollable {
        max-height: 400px; /* Điều chỉnh kích thước tối đa của bảng */
        overflow-y: auto; /* Thêm thanh cuộn dọc */
    }
    .horizontal-table {
        display: flex;
        flex-direction: column;
    }
    .table-data {
        display: flex;
    }
    .table-row {
        border-top: 1px solid #dee2e6; /* Đường viền phân cách giữa các hàng */
        padding: 10px 0; /* Khoảng cách giữa các hàng */
    }
    .table-cell {
        flex: 1;
        padding: 5px;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
        border-right: 2px solid #dee2e6;
        border-color: black;
    }
</style>

<c:url value="/viewStaff" var="action" />
<section class="container">
    <h1 class="text-center text-success mt-1">DANH SÁCH Nhân Viên</h1>

    <c:if test="${pages > 1}">
        <ul class="pagination mt-1">
            <li class="page-item"><a class="page-link" href="${action}">Tất cả</a></li>
                <c:forEach begin="1" end="${pages}" var="i">
                    <c:url value="/viewStaff" var="pageUrl">
                        <c:param name="page" value="${i}" /> 
                    </c:url>
                <li class="page-item"><a class="page-link" href="${pageUrl}">${i}</a></li>
                </c:forEach>
        </ul>
    </c:if>

    <c:forEach items="${staffs}" var="staf">
        <c:url value="/" var="searchUrl">
            <c:param name="idStaff" value="${staf.idStaff}" /> 
        </c:url>   
        <a href="${searchUrl}" style="display: none">${staf.nameStaff || staf.gender || staf.phone || staf.addressUser || staf.roles.nameRoles}</a>
    </c:forEach>


    <div class="table-scrollable-x">
        <div class="table-responsive border border-dark border-2">
            <div class="table table-info table-hover">
                <div class="table-data ">
                    <div class="horizontal-table table-row ">
                        <div class="table-cell text- ">IMG</div>
                        <div class="table-cell ">ID</div>
                        <div class="table-cell ">Name Staff</div>
                        <div class="table-cell ">Gender</div>
                        <div class="table-cell ">Phone</div>
                        <div class="table-cell ">BrithDate</div>
                        <div class="table-cell ">Address</div>
                        <div class="table-cell ">Roles</div>
                        <div class="table-cell ">USERNAME</div>
                        <div class="table-cell ">PASSWORD</div>
                        <div class="table-cell ">OPTIONS</div>
                    </div>
                    <c:forEach items="${staffs}" var="staf">
                        <div class="table-row">
                            <div class="table-cell">
                                <img src="${staf.imgStaff}" alt="${staf.imgStaff}" width="120" height="100"/>
                            </div>
                            <div class="table-cell">${staf.idStaff}</div>
                            <div class="table-cell ">${staf.nameStaff}</div>
                            <div class="table-cell ">${staf.gender}</div>
                            <div class="table-cell ">${staf.phone}</div>
                            <div class="table-cell ">${staf.brithStaff}</div>
                            <div class="table-cell ">${staf.addressUser}</div>
                            <div class="table-cell ">${staf.roles.nameRoles}</div>
                            <se:authorize access="hasAnyRole('STAFF', 'ADMIN')">
                                <div class="table-cell ">${staf.userName}</div>
                                <div class="table-cell " type="password">${staf.passWord}</div>
                                <div class="table-cell ">
                                    <c:url value="/viewStaff/admin/staffs/${staf.idStaff}" var="api" />
                                    <a href="${api}" class="btn btn-success ">Cập nhật</a>
                                    <a class="btn btn-danger" onclick="deleteEntry('staffs', '${api}')">Xóa</a>
                                </div>
                            </se:authorize>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>

    <div class="text-end">
        <se:authorize access="hasAnyRole('STAFF', 'ADMIN')">
            <c:url value="/viewStaff/admin/staffs/${staf.idStaff}" var="api" />
            <a href="${api}" class="btn btn-primary" >Thêm Nhân viên</a>
        </se:authorize>
    </div>
</section>
</section>
<script src="<c:url value="/js/main.js" />"></script>

