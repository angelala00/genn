<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<div class="footer"></div>
<script src="${WebUtil.staticresourceshost }/resources/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript">
// 	var menu = document.getElementById('menu'), menuLink = document
// 			.getElementById('menuLink'), layout = document
// 			.getElementById('layout'),

// 	toggleClass = function(element, className) {
// 		var classes = element.className.split(/\s+/), length = classes.length, i = 0;

// 		for (; i < length; i++) {
// 			if (classes[i] === className) {
// 				classes.splice(i, 1);
// 				break;
// 			}
// 		}
// 		// The className is not found
// 		if (length === classes.length) {
// 			classes.push(className);
// 		}

// 		element.className = classes.join(' ');
// 	};

// 	menuLink.onclick = function(e) {
// 		e.preventDefault();
// 		var active = 'active';
// 		toggleClass(layout, active);
// 		toggleClass(menu, active);
// 		toggleClass(menuLink, active);
// 	};
</script>




<!--[if !IE]> -->
<script src="${WebUtil.staticresourceshost }/resources-manager/js/jquery-2.0.3.min.js"></script>
<!-- <![endif]--> 
<!--[if IE]>
<script src="${WebUtil.staticresourceshost }/resources-manager/js/jquery-1.10.2.min.js"></script>
<![endif]--> 

<script type="text/javascript">
if("ontouchend" in document) document.write("<script src='${WebUtil.staticresourceshost }/resources-manager/js/jquery.mobile.custom.min.js'>"+"<"+"script>");
</script> 

<script src="${WebUtil.staticresourceshost }/resources-manager/js/bootstrap.min.js"></script> 
<script src="${WebUtil.staticresourceshost }/resources-manager/js/typeahead-bs2.min.js"></script> 

<!-- page specific plugin scripts --> 
<!--[if lte IE 8]>
<script src="${WebUtil.staticresourceshost }/resources-manager/js/excanvas.min.js"></script>
<![endif]-->
 
<script src="${WebUtil.staticresourceshost }/resources-manager/js/jquery-ui-1.10.3.custom.min.js"></script> 
<script src="${WebUtil.staticresourceshost }/resources-manager/js/jquery.ui.touch-punch.min.js"></script> 
<script src="${WebUtil.staticresourceshost }/resources-manager/js/jquery.slimscroll.min.js"></script> 
<script src="${WebUtil.staticresourceshost }/resources-manager/js/jquery.easy-pie-chart.min.js"></script> 
<script src="${WebUtil.staticresourceshost }/resources-manager/js/jquery.sparkline.min.js"></script>
 
<script src="${WebUtil.staticresourceshost }/resources-manager/js/flot/jquery.flot.min.js"></script> 
<script src="${WebUtil.staticresourceshost }/resources-manager/js/flot/jquery.flot.pie.min.js"></script> 
<script src="${WebUtil.staticresourceshost }/resources-manager/js/flot/jquery.flot.resize.min.js"></script> 
<!-- ace scripts --> 
<script src="${WebUtil.staticresourceshost }/resources-manager/js/ace-elements.min.js"></script> 
<script src="${WebUtil.staticresourceshost }/resources-manager/js/ace.min.js"></script> 

<%-- <script src="${WebUtil.staticresourceshost }/resources-manager/js/chosen.jquery.min.js"></script>  --%>

<%-- <script src="${WebUtil.staticresourceshost }/resources-manager/js/liquidmetal.js"></script>  --%>
<%-- <script src="${WebUtil.staticresourceshost }/resources-manager/js/jquery.flexselect.js"></script>  --%>


<script src="${WebUtil.staticresourceshost }/resources-manager/js/date-time/bootstrap-datepicker.min.js"></script>
<script src="${WebUtil.staticresourceshost }/resources-manager/js/date-time/bootstrap-timepicker.min.js"></script>
<script src="${WebUtil.staticresourceshost }/resources-manager/js/date-time/moment.min.js"></script>
<script src="${WebUtil.staticresourceshost }/resources-manager/js/date-time/daterangepicker.min.js"></script>


<script type="text/javascript">
jQuery(function($) {
	$('table th input:checkbox').on('click' , function(){
var that = this;
$(this).closest('table').find('tr > td:first-child input:checkbox')
.each(function(){
this.checked = that.checked;
$(this).closest('tr').toggleClass('selected');
});
}); 
});
</script>
<script>
initMenu();
</script>