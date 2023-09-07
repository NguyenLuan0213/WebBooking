<%-- 
    Document   : revernue
    Created on : Sep 1, 2023, 11:00:57 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="se" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:url value="/stas" var="action" />
<section class="container">
    <h1 class="text-center">Revenue Statistics</h1>

    <h2 class="text-center">Total Revenue: ${subTotal} VNĐ</h2>
    <div class="row">
        <div class="col-sm-6">
            <canvas id="myChart" style="width:200px;max-width:500px;max-height: 300px ;height:200px"></canvas>
        </div>
        <div class="col-sm-6 mt-5" >

            <se:authorize access="hasAnyRole('ROLE_STAFF', 'ROLE_ADMIN')">
                <c:url value="/stas/admin/filter" var="action" />
                <form:form  method="GET" action="${action}">
                    <label for="date">Date:</label>
                    <input type="text" id="date" name="date" placeholder="yyyy-MM-dd" required>

                    <label for="typeStat">Type:</label>
                    <select id="typeStat" name="typeStat">
                        <option value="1">Quý</option>
                        <option value="2">Năm</option>
                        <option value="3">Tháng</option>
                    </select>

                    <button type="submit">Filter</button>
                </form:form>
            </se:authorize>

            <table class="table table-bordered table-striped mt-2">
                <thead>
                    <tr>
                        <th>Year</th>

                        <th>Month/Quarter</th>

                        <th>Tổng doanh thu</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${revenues}" var="revenue">
                        <tr>
                            <td>${revenue[0]}</td>
                            <td>${revenue[1]}</td>
                            <td>${revenue[2]}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

        </div>
    </div>

    <h1 class="text-center">Revenue Statistics Order</h1>


    <h2 class="text-center">Total Revenue: ${totalAmountSent} VNĐ</h2>

    <div class="row">
        <div class="col-sm-6">
            <canvas id="myChartOrder" style="width:200px;max-width:500px;max-height: 300px ;height:200px"></canvas>
        </div>
        <div class="col-sm-6 mt-5" >

            <se:authorize access="hasAnyRole('ROLE_STAFF', 'ROLE_ADMIN')">
                <c:url value="/stas/admin/filter" var="action" />
                <form:form  method="GET" action="${action}">
                    <label for="date">Date:</label>
                    <input type="text" id="date" name="date" placeholder="yyyy-MM-dd" required>

                    <label for="typeStat">Type:</label>
                    <select id="typeStat" name="typeStat">
                        <option value="1">Quý</option>
                        <option value="2">Năm</option>
                        <option value="3">Tháng</option>
                    </select>

                    <button type="submit">Filter</button>
                </form:form>
            </se:authorize>

            <table class="table table-bordered table-striped mt-2">
                <thead>
                    <tr>
                        <th>Year</th>
                        <th>Month/Quarter</th>
                        <th>Tổng doanh thu</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${revenuesorder}" var="revenue">
                        <tr>
                            <td>${revenue[0]}</td>
                            <td>${revenue[1]}</td>
                            <td>${revenue[2]}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
</section>
<script>
    const ctx = document.getElementById('myChart');
    var labels = [];
    var data = [];

    <c:forEach items="${revenues}" var="revenue">
    labels.push("${revenue[1]}");
    data.push(${revenue[2]});
    </c:forEach>

    new Chart(ctx, {
        type: 'bar',
        data: {
            labels: labels,
            datasets: [{
                    label: 'Tiền VND',
                    data: data, // Sử dụng mảng data
                    borderWidth: 1
                }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });

    const myc = document.getElementById('myChartOrder');
    var labels = [];
    var data = [];

    <c:forEach items="${revenuesorder}" var="revenue">
    labels.push("${revenue[1]}");
    data.push(${revenue[2]});
    </c:forEach>

    new Chart(myc, {
        type: 'bar',
        data: {
            labels: labels,
            datasets: [{
                    label: 'Tiền VND',
                    data: data, // Sử dụng mảng data
                    borderWidth: 1
                }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });

    $("#typeStat, #date").change(function () {
        updateChartData();
    });

    function updateChartData() {
        var typeStat = $("#typeStat").val();
        var date = $("#date").val();

        // Gửi yêu cầu Ajax để lấy dữ liệu mới từ máy chủ
        $.ajax({
            type: "GET",
            url: "/stas/admin/filter",
            data: {typeStat: typeStat, date: date},
            success: function (data) {
                // Cập nhật biểu đồ hoặc bảng dữ liệu bằng dữ liệu mới từ máy chủ
                $("#chartContainer").html(data);
            },
            error: function (error) {
                console.log(error);
            }
        });
    }

// Khởi đầu, cập nhật dữ liệu ban đầu
    updateChartData();
</script>


<script src="<c:url value="/js/stas.js" />"></script>
