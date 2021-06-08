var themeKey = "dccdTheme";

var themeList = [ 'darkblue', 'ligntgray' , 'newBlue' ];

jQuery.extend({
	"getContextPath" : function() {
		var pathName = document.location.pathname;
		var index = pathName.substr(1).indexOf("/");
		var result = pathName.substr(0, index + 1);
		return result;
	},
	"getCache" : function(key) {
		return localStorage.getItem(key);
	},
	"setCache" : function(key, value) {
		localStorage.removeItem(key);
		localStorage.setItem(key, value);
	},

	"getTheme" : function() {
		var theme = $.getCache(themeKey);
		if (null == theme) {
			$.setTheme(themeKey, themeList[2]);
			theme = $.getCache(themeKey);
		}
		return theme;
	},
	"setTheme" : function(key, value) {
		$.setCache(key, value);
	},
	"setdarkblueTheme" : function() {
		$.setCache(themeKey, "darkblue");
	},
    "setligntgrayTheme" : function() {
    	$.setCache(themeKey, "ligntgray");
	},
    "setNewBlueTheme" : function() {
    	$.setCache(themeKey, "newBlue");
	},
    "isdarkblueTheme" : function() {
    	return "darkblue" === $.getTheme();
	},
    "isligntgrayTheme" : function() {
    	return "ligntgray" === $.getTheme();
	},
    "isNewBlue" : function() {
    	return "newBlue" === $.getTheme();
	}
});



/*$(function() {
	$("head").append( "<script type='text/javascript' src='"+$.getContextPath()+"/include/theme/" + $.getTheme() + "/js/style.js'>");
});
*/
jQuery.extend({
	"minValue" : function(a, b) {
		return a < b ? a : b;
	},
	"maxValue" : function(a, b) {
		return a > b ? a : b;
	}
});

jQuery.extend({
	"generatePreHousr" : function(pre) {
		// /<summary>
		// / 返回最近pre小时的时间序列数组
		// /</summary>
		return $.generateLast24Housr().slice(24 - pre);
	},
	"generateLast24Housr" : function() {
		// /<summary>
		// / 返回最近xx小时的时间序列数组
		// /</summary>
		var axisData = [];
		var myDate = new Date();
		var currHoursIndex = myDate.getHours();
		for ( var i = currHoursIndex; i > 0; i--) {
			axisData.push(i);
		}
		for ( var i = 24; i > currHoursIndex; i--) {
			axisData.push(i);
		}
		return axisData.reverse();
	},
	"getNowDate" : function() {
		// /<summary>
		// / 返回最当前时间
		// /</summary>
		return new Date();
	},
	"getPreDate" : function(pre) {
		var c = new Date();
		c.setDate(c.getDate() - pre);
		return c;
	},
	"formatDate" : function(date, format) {
		// /<summary>
		// / 格式化时间
		// / $.formatDate( $.getPreDate(2), "yyyy-MM-dd hh:mm:ss")
		// /</summary>
		var o = {
			"M+" : date.getMonth() + 1, // month
			"d+" : date.getDate(), // day
			"h+" : date.getHours(), // hour
			"m+" : date.getMinutes(), // minute
			"s+" : date.getSeconds(), // second
			"q+" : Math.floor((date.getMonth() + 3) / 3), // quarter
			"S" : date.getMilliseconds()
		// millisecond
		}

		if (/(y+)/.test(format)) {
			format = format.replace(RegExp.$1, (date.getFullYear() + "")
					.substr(4 - RegExp.$1.length));
		}

		for ( var k in o) {
			if (new RegExp("(" + k + ")").test(format)) {
				format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
						: ("00" + o[k]).substr(("" + o[k]).length));
			}
		}
		return format;
	}
});
$("head").append( "<script type='text/javascript' src='js/style.js' />");
