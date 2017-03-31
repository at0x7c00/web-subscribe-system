var i18nMsg  = {
	yes : '是',
	no : '否',
	cancel : '取消',
	sure : '确定',
	close : '关闭',
	sureof : '确认要',
	clear : '清空',
	ma : '吗?',
	confirmTip : '确认提示',
	info : '提示',
	error : '提示',
	ajaxLoading:'加载中，请稍后...',
	pleaseSelect : '请选择数据',
	sessionTimeOut : '未登录或会话超时，请登录',
	mustselectone : '请选择一条数据',
	onlyselectone : '最多只能选择一个数据',
	select2 : {
		nomatches : "无匹配数据",
		pleaseEnter : "请输入",
		largeThan : "个以上字符",
		pleaseDelete : "请删除",
		character : '字符',
		canOnlySelect : "最多只能选择",
		item : "条数据",
		loading : "加载中...",
		searching : "搜索中..."
		
	},
	formValidateFailed : "提交数据不完整，请改正后再提交!",
	tofast : "系统正在处理中，请稍等.",
	unitMustBeContinuous : "必须选择连续的U位!"
};

$(function(){
	$.extend($.validator.messages, {
		required: "必填字段",
		idCard: "身份证号码不合法",
		remote: "请修正该字段",
		email: "请输入正确格式的电子邮件",
		url: "请输入合法的网址",
		date: "请输入合法的日期",
		dateISO: "请输入合法的日期 (ISO)",
		number: "请输入合法的数字",
		digits: "只能输入整数",
		creditcard: "请输入合法的信用卡号",
		equalTo: "请再次输入相同的值",
		accept: "请输入拥有合法后缀名的字符串",
		maxlength: $.validator.format("长度最多是 {0} 的字符串"),
		minlength: $.validator.format("长度最少是 {0} 的字符串"),
		rangelength: $.validator.format("长度介于 {0} 和 {1} 之间的字符串"),
		range: $.validator.format("请输入一个介于 {0} 和 {1} 之间的值"),
		max: $.validator.format("请输入一个最大为 {0} 的值"),
		min: $.validator.format("请输入一个最小为 {0} 的值"),
		alphanumeric: "字母、数字、下划线",
		lettersonly: "必须是字母",
	});
});