<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<base href="/">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="${'$'}{WebUtil.staticresourceshost }/resources/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${'$'}{WebUtil.staticresourceshost }/resources/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="${'$'}{WebUtil.staticresourceshost }/resources/css/main.css">
<script src="${'$'}{WebUtil.staticresourceshost }/resources/js/jquery-1.10.2.min.js"></script>

<script type="text/javascript">
function exeTextKeyUp(t){
	var max = parseInt(t.attr('maxlength'));
	if(t.length>0){
		if(t.val().length > max){
			t.val(t.val().substr(0, t.attr('maxlength')));
		}
		if (t.parent().find(".cf30.abc").length>0)
		{
			t.parent().find(".cf30.abc").html(max - t.val().length);
		} else if (t.parent().parent().find(".cf30.abc").length>0) {
			t.parent().parent().find(".cf30.abc").html(max - t.val().length);
		}
	} else {
		
	}
}

$(document).ready(function(){
	$('textarea[maxlength]').on('keyup',function(){
		exeTextKeyUp($(this));
	});
	$('textarea[maxlength]').on('blur',function(){
		exeTextKeyUp($(this));
	});
	
	$('input[maxlength]').on('keyup',function(){
		exeTextKeyUp($(this));
	});
	$('input[maxlength]').on('blur',function(){
		exeTextKeyUp($(this));
	});
	
	$.each($('textarea[maxlength]'),function(i,n){
		exeTextKeyUp($(this));
	});
	$.each($('input[maxlength]'),function(i,n){
		exeTextKeyUp($(this));
	});
});

</script>
<!--[if lt IE 9]>
<script src="http://static.test.zcooler.com/resources/js/html5shiv.js"></script>
<script src="http://static.test.zcooler.com/resources/js/respond.min.js"></script>
<![endif]-->