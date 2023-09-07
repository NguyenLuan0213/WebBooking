<%-- 
    Document   : header
    Created on : Jul 31, 2023, 5:16:27 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="se" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url value="/" var="action" />

<style>
    .sticky-top {
        position: sticky;
        top: 0;
        z-index: 1000; /* Đảm bảo thanh nav hiển thị trước các phần khác */
    }
</style>

<container>
    <nav class="navbar navbar-expand-lg navbar-light bg-primary sticky-top">
        <div class="container">
            <a class="navbar-brand border bg-light ml-1" href="${action}">
                <img src="https://res.cloudinary.com/dkba7robk/image/upload/v1691853915/icon_mbfklk.jpg" width="50" height="auto" alt="trang chủ" />
                BOOKING COACH
            </a>

            <div class="collapse navbar-collapse" id="collapsibleNavbar">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0"> 

                    <se:authorize access="hasAnyRole('STAFF', 'ADMIN','OWNER')">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                Garage
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li>
                                    <c:url value="/viewGarage" var="action" />
                                    <a class="dropdown-item" href="${action}">Nhà Xe</a>
                                </li>
                                <li >
                                    <c:url value="/viewGarageTS" var="action" />
                                    <a class="dropdown-item" href="${action}">Chuyến Đi Của Nhà Xe</a>
                                </li>
                                <li><hr class="dropdown-divider"></li>
                                <li>
                                    <c:url value="/stas" var="action" />
                                    <a class="dropdown-item" href="${action}">Thống Kê</a>
                                </li>
                            </ul>
                        </li>

                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                Transport
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li>
                                    <c:url value="/viewProduct" var="action" />
                                    <a class="dropdown-item" href="${action}">Product</a>
                                </li>
                                <li>
                                    <c:url value="/viewTransporttruck" var="action" />
                                    <a class="dropdown-item" href="${action}">Chuyến Chở Hàng</a>
                                </li>
                                <li>
                                    <c:url value="/viewOrderShip" var="action" />
                                    <a class="dropdown-item" href="${action}">Vé Chở Hàng</a>
                                </li>
                                <li><hr class="dropdown-divider"></li>
                                <li><a class="dropdown-item" href="#">Something else here</a></li>
                            </ul>
                        </li>

                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                BUS
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li>
                                    <c:url value="/viewCoach" var="action" />
                                    <a class="dropdown-item" href="${action}">Xe</a>
                                </li>
                                <li>
                                    <c:url value="/viewCoachStrip" var="action" />
                                    <a class="dropdown-item" href="${action}">Chuyến Xe</a>
                                </li>
                                <li>
                                    <c:url value="/viewStation" var="action" />
                                    <a class="dropdown-item" href="${action}">Bến Xe</a>
                                </li>
                                <li><hr class="dropdown-divider"></li>
                                <li>
                                    <c:url value="/viewCSCS" var="action" />
                                    <a class="dropdown-item" href="${action}">Ghế Chuyến Xe</a>
                                </li>
                                <li>
                                    <c:url value="/viewTicket" var="action" />
                                    <a class="dropdown-item" href="${action}">Vé xe</a>
                                </li>
                            </ul>
                        </li>

                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                User
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li>
                                    <c:url value="/viewStaff" var="action" />
                                    <a class="dropdown-item" href="${action}">Nhân Viên</a>
                                </li>
                                <li>
                                    <c:url value="/viewCustomer" var="action" />
                                    <a class="dropdown-item" href="${action}">Khách Hàng</a>
                                </li>
                                <li><hr class="dropdown-divider"></li>
                                <li>
                                    <c:url value="/viewComment" var="action" />
                                    <a class="dropdown-item text-black " href="${action}">Bình Luận</a>
                                </li>
                            </ul>
                        </li> 
                    </se:authorize>
                    <c:choose>
                        <c:when test="${pageContext.request.userPrincipal.name != null}">
                            <li class="nav-item" >
                                <a class="nav-link text-light border" href="<c:url value="/logout" />">Chào ${pageContext.request.userPrincipal.name}!</a>
                            </li>
                            <li class="nav-item ">
                                <a class="nav-link text-danger border" style="margin-left: 10px " href="<c:url value="/logout" />">Đăng xuất</a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="nav-item">
                                <a class="nav-link" href="<c:url value="/login" />">Đăng nhập</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </ul>
                <form class="d-flex" action="${searchUrl}" >
                    <input class="form-control me-2" type="search" name="kw" placeholder="Search" aria-label="Search">
                    <button class="btn btn-info text-black" type="submit">Search</button>
                </form>
            </div>
        </div>
    </nav>
</container>
