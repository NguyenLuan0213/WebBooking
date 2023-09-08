function deleteEntry(type, path) {
    if (confirm("Bạn chắc chắn xóa không?")) {
        fetch(path, {
            method: "DELETE"
        })
                .then(res => {
                    if (res.status === 204) {
                        location.reload();
                    } else {
                        alert("Hệ thống đang có lỗi! Vui lòng quay lại sau!");
                    }
                })
                .catch(error => {
                    console.error("Có lỗi xảy ra:", error);
                    alert("Có lỗi xảy ra khi thực hiện yêu cầu! Vui lòng thử lại hoặc liên hệ quản trị viên.");
                });
    }
}

function deleteCoach(path) {
    if (confirm("Bạn chắc chắn xóa không?")) {
        fetch(path, {
            method: "DELETE"
        })
                .then(res => {
                    if (res.status === 204) {
                        location.reload();
                    } else {
                        alert("Hệ thống đang có lỗi! Vui lòng quay lại sau!");
                    }
                })
                .catch(error => {
                    console.error("Có lỗi xảy ra:", error);
                    alert("Có lỗi xảy ra khi thực hiện yêu cầu! Vui lòng thử lại hoặc liên hệ quản trị viên.");
                });
    }
}

flatpickr("#dateTrans", {
    enableTime: true, // Cho phép nhập cả thời gian
    dateFormat: "Y-m-d H:i:S", // Định dạng ngày giờ
    time_24hr: true // Sử dụng định dạng 24 giờ
});

flatpickr("#dateOrder", {
    enableTime: true, // Cho phép nhập cả thời gian
    dateFormat: "Y-m-d H:i:S", // Định dạng ngày giờ
    time_24hr: true // Sử dụng định dạng 24 giờ
});
flatpickr("#departureTime", {
    enableTime: true, // Cho phép nhập cả thời gian
    dateFormat: "Y-m-d H:i:S", // Định dạng ngày giờ
    time_24hr: true // Sử dụng định dạng 24 giờ
});
flatpickr("#bookingDate", {
    enableTime: true, // Cho phép nhập cả thời gian
    dateFormat: "Y-m-d H:i:S", // Định dạng ngày giờ
    time_24hr: true // Sử dụng định dạng 24 giờ
});


const canvas = document.getElementById("canvas");
const ctx = canvas.getContext("2d");
let radius = canvas.height / 2;
ctx.translate(radius, radius);
radius = radius * 0.90
setInterval(drawClock, 1000);

function drawClock() {
    drawFace(ctx, radius);
    drawNumbers(ctx, radius);
    drawTime(ctx, radius);
}

function drawFace(ctx, radius) {
    const grad = ctx.createRadialGradient(0, 0, radius * 0.95, 0, 0, radius * 1.05);
    grad.addColorStop(0, '#333');
    grad.addColorStop(0.5, 'white');
    grad.addColorStop(1, '#333');
    ctx.beginPath();
    ctx.arc(0, 0, radius, 0, 2 * Math.PI);
    ctx.fillStyle = 'white';
    ctx.fill();
    ctx.strokeStyle = grad;
    ctx.lineWidth = radius * 0.1;
    ctx.stroke();
    ctx.beginPath();
    ctx.arc(0, 0, radius * 0.1, 0, 2 * Math.PI);
    ctx.fillStyle = '#333';
    ctx.fill();
}

function drawNumbers(ctx, radius) {
    ctx.font = radius * 0.15 + "px arial";
    ctx.textBaseline = "middle";
    ctx.textAlign = "center";
    for (let num = 1; num < 13; num++) {
        let ang = num * Math.PI / 6;
        ctx.rotate(ang);
        ctx.translate(0, -radius * 0.85);
        ctx.rotate(-ang);
        ctx.fillText(num.toString(), 0, 0);
        ctx.rotate(ang);
        ctx.translate(0, radius * 0.85);
        ctx.rotate(-ang);
    }
}

function drawTime(ctx, radius) {
    const now = new Date();
    let hour = now.getHours();
    let minute = now.getMinutes();
    let second = now.getSeconds();
    //hour
    hour = hour % 12;
    hour = (hour * Math.PI / 6) +
            (minute * Math.PI / (6 * 60)) +
            (second * Math.PI / (360 * 60));
    drawHand(ctx, hour, radius * 0.5, radius * 0.07);
    //minute
    minute = (minute * Math.PI / 30) + (second * Math.PI / (30 * 60));
    drawHand(ctx, minute, radius * 0.8, radius * 0.07);
    // second
    second = (second * Math.PI / 30);
    drawHand(ctx, second, radius * 0.9, radius * 0.02);
}

function drawHand(ctx, pos, length, width) {
    ctx.beginPath();
    ctx.lineWidth = width;
    ctx.lineCap = "round";
    ctx.moveTo(0, 0);
    ctx.rotate(pos);
    ctx.lineTo(0, -length);
    ctx.stroke();
    ctx.rotate(-pos);
}


$(document).ready(function () {
    $('#filterForm').on('submit', function (e) {
        e.preventDefault(); // Ngăn chặn gửi biểu mẫu một cách thường lệ

        // Lấy giá trị của trường "date" và "typeStat" từ biểu mẫu
        var dateValue = $('#date').val();
        var typeStatValue = $('#typeStat').val();

        // Thực hiện yêu cầu AJAX đến máy chủ để lấy dữ liệu mới
        $.ajax({
            url: '/stas/admin/revenue/filter', // Đường dẫn tới controller
            method: 'GET',
            data: {
                date: dateValue,
                typeStat: typeStatValue
            },
            success: function (data) {
                // Dữ liệu đã trả về từ máy chủ
                // Cập nhật bảng hoặc biểu đồ dựa trên dữ liệu này
            },
            error: function (error) {
                // Xử lý lỗi nếu có
            }
        });
    });
});