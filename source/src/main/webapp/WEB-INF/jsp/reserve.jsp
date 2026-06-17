<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset='utf-8' />
<script
	src="https://cdn.jsdelivr.net/npm/fullcalendar@6.1.20/index.global.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/fullcalendar@6.1.20/locales/ja.global.min.js"></script>
<script>

function closeModal() {
	  document.getElementById("editModal").style.display = "none";
	}

function formatDate(str) {
	  if (!str) return "";

	  // "2026-06-15T10:30:00+09:00" → "2026-06-15T10:30"
	  return str.substring(0, 16);
	}


const events = [
	<c:forEach var="r" items="${list}" varStatus="status">
    {
      title: '${r.purpose}',
      start: '${r.sdate}',   // LocalDateTime → "2026-06-12T10:00:00" 形式
      end: '${r.fdate}',
      extendedProps: {
    	    username: '${r.username}',  // ← 名前はここに保持
    	    no: ${r.reservenumber}
    	}

    	    
    }<c:if test="${!status.last}">,</c:if>
  </c:forEach>
];


  document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');

    var calendar = new FullCalendar.Calendar(calendarEl, {
      locale:'ja',
      headerToolbar: {
        left: 'prev,next today',
        center: 'title',
        right: 'dayGridMonth,timeGridWeek,timeGridDay'
      },
      eventClick: function(info) {
    	  document.getElementById("editNo").value = info.event.extendedProps.no;
    	  document.getElementById("sedit").value = formatDate(info.event.startStr);
    	  document.getElementById("fedit").value = formatDate(info.event.endStr);
    	  document.getElementById("pedit").value = info.event.title;

    	  document.getElementById("editModal").style.display = "block";

    	},
      
      eventDidMount: function(info) {
    	    const username = info.event.extendedProps.username;

    	    // カレンダー上の表示をカスタム
    	    info.el.querySelector('.fc-event-title').innerHTML =
    	        info.event.title + '<br><span style="font-size:12px; color:#555;">' + username + '</span>';
    	},
      events: events
    });

    calendar.render();//カレンダーの更新
  });
</script>
<script>
const startInput = document.getElementById('start');
const endInput = document.getElementById('end');

startInput.addEventListener('change', function () {
    endInput.min = this.value;

    if (endInput.value && endInput.value < this.value) {
        endInput.value = this.value;
    }
});

</script>
<style>
body {
	margin: 40px 10px;
	padding: 0;
	font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
	font-size: 14px;
}

#calendar {
	max-width: 1100px;
	margin: 0 auto;
}

#editModal {
    display: none;
    position: fixed;
    top: 20%;
    left: 35%;
    background: white;
    padding: 20px;
    border: 1px solid #ccc;
    box-shadow: 0 0 10px rgba(0,0,0,0.3);
    z-index: 9999;
}
</style>
</head>
<body>
	<form id=carfrom method="get" action="/d1/ReserveServlet">
		<select class="ca" name="select" onchange="this.form.submit()">
			<c:forEach var="ca" items="${carlist}">
				<option value="${ca.carname}"
					${select == ca.carname ? 'selected' : ''}>${ca.carname}</option>
			</c:forEach>
		</select>
	</form>

	<div id='calendar'></div>

	<form id="reserve_form" method="POST" action="/d1/ReserveServlet">
		<table class="regist">
			<tr>
				<td><label><br> <input type="hidden"
						name="select" value="${select}"> <input type="hidden"
						name="reservenumber" value="（自動採番）" readonly="readonly"
						style="background-color: lightgray">
				</label></td>
				<td><label for="start">予約開始日時<br> <input
						type="datetime-local" id="start" name="sdate">
				</label></td>
				<td><label for="end">予約終了日時<br> <input
						type="datetime-local" id="end" name="fdate">
				</label></td>
				<td><label>目的<br> <input type="text" id="co"
						name="purpose" placeholder=" "> <!-- <input type="text" id="ur" name="url" placeholder=" "> -->
				</label></td>
			</tr>
			<tr>
				<td><input type="submit" id="register" name="regist" value="登録">
					<input type="reset" name="reset" value="リセット"></td>
			</tr>

		</table>
		<span id="error" class="error"></span>
	</form>

	<div id="editModal">
		<h3>予約編集</h3>

		<form method="POST" action="/d1/ReserveServlet">
			番号<input type="text" id="editNo" name="reservenumber"> 開始日時：<input
				type="datetime-local" id="sedit" name="sdate"><br> <br>
			終了日時：<input type="datetime-local" id="fedit" name="fdate"><br>
			<br> 目的：<input type="text" id="pedit" name="purpose"><br>
			<br> <input type="submit" name="submit" value="更新"> <input
				type="submit" name="submit" value="削除">
			<button type="button" onclick="closeModal()">閉じる</button>
		</form>
	</div>


</body>
<script>
		'use strict';
		let form = document.getElementById('reserve_form');
		let errorMessage = document.getElementById('error');

		form.onsubmit = function(event) {

		    errorMessage.textContent = "";
		    form.sdate.style.backgroundColor = "";
		    form.fdate.style.backgroundColor = "";
		    form.purpose.style.backgroundColor = "";

		    const purpose = form.purpose.value.trim();
		    const sdate = form.sdate.value;
		    const fdate = form.fdate.value;

		    //  目的
		    if (purpose === "") {
		        errorMessage.textContent = "※目的を入力してください！";
		        form.purpose.style.backgroundColor = "#ffe6e6";
		        event.preventDefault();
		        return;
		    }

		    //  開始日時
		    if (sdate === "") {
		        errorMessage.textContent = "※予約開始日時を入力してください！";
		        form.sdate.style.backgroundColor = "#ffe6e6";
		        event.preventDefault();
		        return;
		    }

		    //  終了日時
		    if (fdate === "") {
		        errorMessage.textContent = "※予約終了日時を入力してください！";
		        form.fdate.style.backgroundColor = "#ffe6e6";
		        event.preventDefault();
		        return;
		    }

		    //  時間チェック
		    if (new Date(sdate) >= new Date(fdate)) {
		        errorMessage.textContent = "※終了日時は開始日時より後にしてください！";
		        form.sdate.style.backgroundColor = "#ffe6e6";
		        form.fdate.style.backgroundColor = "#ffe6e6";
		        event.preventDefault();
		        return;
		    }
		};
		form.onreset = function() {
		    errorMessage.textContent = "";
		    form.purpose.style.backgroundColor = "";
		    form.sdate.style.backgroundColor = "";
		    form.fdate.style.backgroundColor = "";
		};
		</script>
</html>