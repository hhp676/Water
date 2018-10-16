
function showtime() {
	var today, hour, second, minute, year, month, date;
	var strDate;
	today = new Date();
	var n_day = today.getDay();
	switch (n_day) {
	  case 0:
		strDate = "\u661f\u671f\u65e5";
		break;
	  case 1:
		strDate = "\u661f\u671f\u4e00";
		break;
	  case 2:
		strDate = "\u661f\u671f\u4e8c";
		break;
	  case 3:
		strDate = "\u661f\u671f\u4e09";
		break;
	  case 4:
		strDate = "\u661f\u671f\u56db";
		break;
	  case 5:
		strDate = "\u661f\u671f\u4e94";
		break;
	  case 6:
		strDate = "\u661f\u671f\u516d";
		break;
	  case 7:
		strDate = "\u661f\u671f\u65e5";
		break;
	}
	year = today.getFullYear();
	month = today.getMonth() + 1;
	date = today.getDate();
	hour = today.getHours();
	minute = today.getMinutes();
	second = today.getSeconds();
	document.getElementById("timeDiv").innerHTML = year + "\u5e74" + month + "\u6708" + date + "\u65e5 " + addZero(hour) + ":" + addZero(minute) + ":" + addZero(second) + " (" + strDate + ")&nbsp;"; //显示时间
	setTimeout("showtime();", 1000); //设定函数自动执行时间为 1000 ms(1 s)
}
function addZero(val){
	if(val < 10){
		return "0" + val;
	}else{
		return val;
	}
}
$(function(){showtime()});

