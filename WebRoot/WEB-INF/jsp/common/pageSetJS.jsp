<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript">
pageSetUp('${targetPanel}');
$(function(){
maintainTypechange();
installTypeChange();
});
function setFlag(){
$("#changeFlag").val("true");
}
function installTypeChange(){
var installType =$("#installType").val();
 if(installType=='tar'){//源码
 $("#_compressionFormat").show();
 $("#_compileConfig").show();
 $("select[name='compressionFormat']").addClass("required");
 $("textarea[name='compileConfig']").addClass("required");
 $("#_scriptName").hide();
  $("#_installStep").hide();
   $("textarea[name='installStep']").removeClass("required");
   $("textarea[name='installStep']").val("");
   $("#script").val("");
 }else if(installType=='script'){//脚本
  $("#_compressionFormat").hide();
    $("select[name='compressionFormat']").removeClass("required");
   $("textarea[name='compileConfig']").removeClass("required");
 $("textarea[name='scriptName']").addClass("required");
 $("#_compileConfig").hide();
 $("#_scriptName").show();
  $("#_installStep").hide();
   $("textarea[name='installStep']").removeClass("required");
    $("textarea[name='compileConfig']").val("");
     $("textarea[name='installStep']").val("");
 }else if(installType=='other'){//其他 
  $("#_compressionFormat").hide();
   $("select[name='compressionFormat']").removeClass("required");
 $("#_compileConfig").hide();
 $("textarea[name='compileConfig']").removeClass("required");
 $("textarea[name='scriptName']").removeClass("required");
 $("#_scriptName").hide();
 $("#_installStep").show();
 $("textarea[name='installStep']").addClass("required");
 $("select[name='compressionFormat']").val("");
  $("textarea[name='compileConfig']").val("");
  $("#script").val("");
 }else{
 $("#_compressionFormat").hide();
   $("select[name='compressionFormat']").removeClass("required");
   $("#_compileConfig").hide();
   $("textarea[name='compileConfig']").removeClass("required");
   $("textarea[name='scriptName']").removeClass("required");
   $("#_scriptName").hide();
   $("#_installStep").hide();
   $("textarea[name='installStep']").removeClass("required");
    $("textarea[name='compileConfig']").val("");
     $("textarea[name='installStep']").val("");
     $("select[name='compressionFormat']").val("");
     $("#script").val("");
     
 }
 // 源码
}
function maintainTypechange(){

var maintainType = $("#maintainType").val();
if(maintainType=='standard'){//标准

$("input[name='softwareVersion']").removeClass("required");
$("input[name='softwareVersion']").attr("disabled",true);
$("#_version").hide();
}else if(maintainType=='nostandard'){
$("input[name='softwareVersion']").addClass("required");
$("input[name='softwareVersion']").attr("disabled",false);
$("#_version").show();
}
}
function checkSoftware(){
var supportSoftware = $("table.supportsoftware>tbody>tr").length;
var supportSoftwaretd =  $("table.supportsoftware>tbody>tr>td").length;
if(supportSoftware>0&&supportSoftwaretd>1){
return true;
}else{
 alertMsg.info("支持的软件不能为空");
 $(".smart-form-submit-btn").removeClass("disabled");
 return false;
}
};
function checkJSName(){
if($("#_scriptName").is(":visible")){
var scriptname = $("#script").val();
if(scriptname.length<=0){
alertMsg.info("脚本名称不为空");
$(".smart-form-submit-btn").removeClass("disabled");
return false;

}else{
return true;
}
}else{
return true;
}


};

$("select[name='supportType']").change(function(){
$("#supportSoftwaresSelectList").find("#dt_basic").find("tbody").empty();
});
</script>