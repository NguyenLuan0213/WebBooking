<%-- 
    Document   : viewCoach
    Created on : Aug 7, 2023, 4:45:07 PM
    Author     : Admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="se" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


    
<c:url value="/viewCoach" var="action" />
<section class="container">
    <h1 class="text-center text-success mt-1">DANH SÁCH CÁC XE</h1>
    
    <c:if test="${pageCoach > 1}">
        <ul class="pagination mt-1">
            <li class="page-item"><a class="page-link" href="${action}">Tất cả</a></li>
                <c:forEach begin="1" end="${pageCoach}" var="i">
                    <c:url value="/viewCoach" var="pageUrl">
                        <c:param name="page" value="${i}" /> 
                    </c:url>
                <li class="page-item"><a class="page-link" href="${pageUrl}">${i}</a></li>
                </c:forEach>
        </ul>
    </c:if>
    
    
        
    <section class="container d-flex flex-column">
        <table class="table table-info table-hover border border-dark border-2 p-3">
            <thead>
                <tr>
                    <th class="text-center">Ảnh</th>
                    <th class="text-center">ID</th>
                    <th class="text-center">Tên Xe</th>
                    <th class="text-center">Sức chứa</th>
                    <th class="text-center">Loại xe</th>
                    <th class="text-center">Tên Nhà Xe</th>
                    <th class="text-center">Chức Năng</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${coachs}" var="coach">
                    <tr >
                        <th class="text-center">
                            <img src="${coach.imgCoach}" alt="${coach.imgCoach}" width="120" height="100"/>
                        </th>
                        <th scope="row" class=" text-center">${coach.idCoach}</th>
                        <td class="text-center">${coach.numberCoach}</td>
                        <td class="text-center">${coach.capacity}</td>
                        <td class="text-center">${coach.typeOfCoach.nameTypeOfCoach}</td>
                        <td class="text-center">${coach.idGarage.nameGara}</td>
                        <td class="text-center" >
                            <se:authorize access="hasAnyRole('ROLE_STAFF', 'ROLE_ADMIN','OWNER')">
                                <c:url value="/viewCoach/admin/coachs/${coach.idCoach}" var="api" />
                                <a href="${api}" class="btn btn-success">Cập nhật</a>
                                <a class="btn btn-danger" onclick="deleteCoach('${api}')">Xóa</a>
                            </se:authorize>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <div class="text-end">
            <se:authorize access="hasAnyRole('ROLE_STAFF', 'ROLE_ADMIN','OWNER')">
                <c:url value="/viewCoach/admin/coachs/${coach.idCoach}" var="api" />
                <a href="${api}" class="btn btn-primary" >Thêm Xe</a>
            </se:authorize>
        </div>
    </section>
    
</section>
    <script src="<c:url value="/js/main.js" />"></script>