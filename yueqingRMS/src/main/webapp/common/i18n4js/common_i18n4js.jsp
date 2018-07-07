<%@ page language="java" pageEncoding="UTF-8" %>
<!-- demo_i18n4js.jsp i18n 2 jstl 4 Javascript -->

<c:set var="i18n_object_title" scope="request"><spring:message code="object.title" arguments="" /></c:set>

<!--查询-->
<c:set var="i18n_object_query" scope="request"><spring:message code="object.query" arguments="" /></c:set>
<!--搜索-->
<c:set var="i18n_object_search" scope="request"><spring:message code="object.search" arguments="" /></c:set>
<!--重置-->
<c:set var="i18n_object_reset" scope="request"><spring:message code="object.reset" arguments="" /></c:set>
<!--清空-->
<c:set var="i18n_object_clear" scope="request"><spring:message code="object.clear" arguments="" /></c:set>
<!--新增-->
<c:set var="i18n_object_add" scope="request"><spring:message code="object.add" arguments="" /></c:set>
<!--编辑-->
<c:set var="i18n_object_edit" scope="request"><spring:message code="object.edit" arguments="" /></c:set>
<!--删除-->
<c:set var="i18n_object_del" scope="request"><spring:message code="object.del" arguments="" /></c:set>
<!--查看-->
<c:set var="i18n_object_view" scope="request"><spring:message code="object.view" arguments="" /></c:set>
<!--取消-->
<c:set var="i18n_object_cancel" scope="request"><spring:message code="object.cancel" arguments="" /></c:set>
<!--关闭-->
<c:set var="i18n_object_shutDown" scope="request"><spring:message code="object.shutDown" arguments="" /></c:set>
<!--确定-->
<c:set var="i18n_object_determine" scope="request"><spring:message code="object.determine" arguments="" /></c:set>
<!--操作成功！-->
<c:set var="i18n_object_success" scope="request"><spring:message code="object.success" arguments="" /></c:set>
<!--操作失败！-->
<c:set var="i18n_object_error" scope="request"><spring:message code="object.error" arguments="" /></c:set>






<!--您确定要删除吗？-->
<c:set var="i18n_business_dialog_deleteConfirm" scope="request"><spring:message code="business.dialog.deleteConfirm" arguments="" /></c:set>


<script>
var i18n_object_title = "${i18n_object_title}";
var i18n_object_query = "${i18n_object_query}";
var i18n_object_search = "${i18n_object_search}";
var i18n_object_reset = "${i18n_object_reset}";
var i18n_object_clear = "${i18n_object_clear}";
var i18n_object_add = "${i18n_object_add}";
var i18n_object_edit = "${i18n_object_edit}";
var i18n_object_del = "${i18n_object_del}";
var i18n_object_view = "${i18n_object_view}";
var i18n_object_cancel = "${i18n_object_cancel}";
var i18n_object_shutDown = "${i18n_object_shutDown}";
var i18n_object_determine = "${i18n_object_determine}";

var i18n_object_success = "${i18n_object_success}";
var i18n_object_error = "${i18n_object_error}";
</script>
