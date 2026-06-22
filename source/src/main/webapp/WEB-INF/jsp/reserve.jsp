<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/style.css">
<head>
<meta charset='utf-8' />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/style.css">
<header>
	<img alt="SaleS" src="${pageContext.request.contextPath}/img/SaleS.png">
	<div class="hamburger-menu">
		<input type="checkbox" id="menu-btn-check"> <label
			for="menu-btn-check" class="menu-btn"></label>
		<!--ここからメニュー-->
		<div class="menu-content">
			<ul>
				<li>
					<p>メニュー</p>
				</li>
				<li><a href="/d1/ReserveServlet">📅予約</a></li>
				<li><a href="/d1/GasolineServlet">🔥ガソリン</a></li>
				<li><a href="/d1/TodoServlet">✅TO DO</a></li>
				<li><a href="/d1/StartendServlet">開始/終了</a></li>
				<li><a href="/d1/LoginServlet">🔚ログアウト</a></li>
				<li><a href="/d1/ContactServlet">❓お問い合わせ</a></li>
			</ul>
		</div>
		<!--ここまでメニュー-->
	</div>
</header>
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
    	    userid: '${r.userid}',
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
    	  const eventOwnerId = String(info.event.extendedProps.userid);
    	      	    const currentUserId =  "${sessionScope.userid}";

    	        	    console.log("クリックされたユーザーID:", eventOwnerId);

    	      	    if (eventOwnerId !== currentUserId) {
    	      	      return; }
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
</head>
<body class="body">
	<header>
		<div class="hamburger-menu">
			<input type="checkbox" id="menu-btn-check"> <label
				for="menu-btn-check" class="menu-btn"><span></span></label>
			<!--ここからメニュー-->
			<div class="menu-content">
				<ul>
					<li>
						<p>メニュー</p>
					</li>
					<li><a href="/d1/ReserveServlet">📅予約</a></li>
					<li><a href="/d1/GasolineServlet">🔥ガソリン</a></li>
					<li><a href="/d1/TodoServlet">✅TO DO</a></li>
					<li><a href="/d1/StartendServlet">開始/終了</a></li>
					<li><a href="/d1/LoginServlet">🔚ログアウト</a></li>
					<li><a href="/d1/ContactServlet">❓お問い合わせ</a></li>
				</ul>
			</div>
			<!--ここまでメニュー-->
		</div>
		<form id=carfrom method="get" action="/d1/ReserveServlet">
			<select class="ca" name="select" onchange="this.form.submit()">
				<c:forEach var="ca" items="${carlist}">
					<option value="${ca.carname}"
						${select == ca.carname ? 'selected' : ''}>${ca.carname}</option>
				</c:forEach>
			</select>
		</form>
	</header>
	<div id='calendar'></div>

	<form id="reserve_form" method="POST" action="/d1/ReserveServlet">
		<div class="form-container">
			<label><input type="hidden" name="select" value="${select}">
				<input type="hidden" name="reservenumber" value="（自動採番）"
				readonly="readonly" style="background-color: lightgray"></label>
			<!-- 開始日・終了日・目的を横並び -->
			<div class="form-row-group">
				<div class="form-group">
					<label for="start">開始日</label> <input type="datetime-local"
						id="start" name="sdate">
				</div>

				<div class="form-group">
					<label for="end">終了日</label> <input type="datetime-local" id="end"
						name="fdate">
				</div>

				<div class="form-group">
					<label for="co">目的</label> <input type="text" id="co"
						name="purpose" placeholder="">
				</div>
			</div>

			<!-- ボタン -->
			<div class="form-buttons">
				<input type="submit" name="regist" value="登録"> <input
					type="reset" name="reset" value="リセット">
			</div>
		</div>
		<span id="error" class="error"></span>
	</form>

	<!-- 編集モーダル -->
	<div id="editModal">
		<h3>予約編集</h3>
		<span id="editError" class="error"></span>
		<form id="edit_form" method="POST" action="/d1/ReserveServlet">
			<div class="form-row">
				<!--<label>番号</label>-->
				<input type="hidden" id="editNo" name="reservenumber">
			</div>

			<div class="form-row">
				<label>開始日時</label> <input type="datetime-local" id="sedit"
					name="sdate">
			</div>

			<div class="form-row">
				<label>終了日時</label> <input type="datetime-local" id="fedit"
					name="fdate">
			</div>

			<div class="form-row">
				<label>目的</label> <input type="text" id="pedit" name="purpose">
			</div>

			<div class="button-group">
				<input type="submit" name="submit" value="更新"> <input
					type="submit" name="submit" value="削除">
				<button type="button" onclick="closeModal()">閉じる</button>
			</div>
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
<script>
const editForm = document.getElementById('edit_form');
const editError = document.getElementById('editError');

editForm.onsubmit = function(event) {

    // 削除はチェックしない
    if (document.activeElement.value === "削除") {
        return;
    }

    editError.textContent = "";
    document.getElementById("pedit").style.backgroundColor = "";
    document.getElementById("sedit").style.backgroundColor = "";
    document.getElementById("fedit").style.backgroundColor = "";

    const purpose = document.getElementById("pedit").value.trim();
    const sdate = document.getElementById("sedit").value;
    const fdate = document.getElementById("fedit").value;

    if (purpose === "") {
        editError.textContent = "※目的を入力してください！";
        document.getElementById("pedit").style.backgroundColor = "#ffe6e6";
        event.preventDefault();
        return;
    }

    if (sdate === "") {
        editError.textContent = "※予約開始日時を入力してください！";
        document.getElementById("sedit").style.backgroundColor = "#ffe6e6";
        event.preventDefault();
        return;
    }

    if (sdate === "") {
        editError.textContent = "※予約開始日時を入力してください！";
        document.getElementById("sedit").style.backgroundColor = "#ffe6e6";
        event.preventDefault();
        return;
    }

    if (new Date(sdate) >= new Date(fdate)) {
        editError.textContent =
            "※終了日時は開始日時より後にしてください！";

        document.getElementById("sedit").style.backgroundColor = "#ffe6e6";
        document.getElementById("fedit").style.backgroundColor = "#ffe6e6";

        event.preventDefault();
        return;
    }
    
};
</script>
</html>