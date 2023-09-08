<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="se" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<section class="container" >
    <div class="row">
        <div class="col-sm-2">
            <canvas id="canvas" width="200" height="200"
                    style="background-color:white">
            </canvas>
        </div>
        <div class="col-sm-8" >
            <h1 class="text-center">Welcom To Booking Coach</h1>
        </div>
    </div>


    <h1 class="text-center">Revenue Statistics</h1>

    <h2 class="text-center">Total Revenue: ${subTotal} VNĐ</h2>
    <div class="row">
        <div class="col-sm-6">
            <canvas id="myChart" style="width:200px;max-width:500px;max-height: 300px ;height:200px"></canvas>
        </div>
        <div class="col-sm-6 mt-5" >
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


    const canvas = document.getElementById("canvas");
    const clock = canvas.getContext("2d");
    let radius = canvas.height / 2;
    clock.translate(radius, radius);
    radius = radius * 0.90
    setInterval(drawClock, 1000);

    function drawClock() {
        drawFace(clock, radius);
        drawNumbers(clock, radius);
        drawTime(clock, radius);
    }

    function drawFace(clock, radius) {
        const grad = clock.createRadialGradient(0, 0, radius * 0.95, 0, 0, radius * 1.05);
        grad.addColorStop(0, '#333');
        grad.addColorStop(0.5, 'white');
        grad.addColorStop(1, '#333');
        clock.beginPath();
        clock.arc(0, 0, radius, 0, 2 * Math.PI);
        clock.fillStyle = 'white';
        clock.fill();
        clock.strokeStyle = grad;
        clock.lineWidth = radius * 0.1;
        clock.stroke();
        clock.beginPath();
        clock.arc(0, 0, radius * 0.1, 0, 2 * Math.PI);
        clock.fillStyle = '#333';
        clock.fill();
    }

    function drawNumbers(clock, radius) {
        clock.font = radius * 0.15 + "px arial";
        clock.textBaseline = "middle";
        clock.textAlign = "center";
        for (let num = 1; num < 13; num++) {
            let ang = num * Math.PI / 6;
            clock.rotate(ang);
            clock.translate(0, -radius * 0.85);
            clock.rotate(-ang);
            clock.fillText(num.toString(), 0, 0);
            clock.rotate(ang);
            clock.translate(0, radius * 0.85);
            clock.rotate(-ang);
        }
    }

    function drawTime(clock, radius) {
        const now = new Date();
        let hour = now.getHours();
        let minute = now.getMinutes();
        let second = now.getSeconds();
        //hour
        hour = hour % 12;
        hour = (hour * Math.PI / 6) +
                (minute * Math.PI / (6 * 60)) +
                (second * Math.PI / (360 * 60));
        drawHand(clock, hour, radius * 0.5, radius * 0.07);
        //minute
        minute = (minute * Math.PI / 30) + (second * Math.PI / (30 * 60));
        drawHand(clock, minute, radius * 0.8, radius * 0.07);
        // second
        second = (second * Math.PI / 30);
        drawHand(clock, second, radius * 0.9, radius * 0.02);
    }

    function drawHand(clock, pos, length, width) {
        clock.beginPath();
        clock.lineWidth = width;
        clock.lineCap = "round";
        clock.moveTo(0, 0);
        clock.rotate(pos);
        clock.lineTo(0, -length);
        clock.stroke();
        clock.rotate(-pos);
    }

</script>
<script src="<c:url value="/js/main.js" />"></script>
