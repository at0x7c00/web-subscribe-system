<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!-- include comman here... -->


<th text-align="right"  data-sortfield="username" class="${nfn:sortClass(pageBean,'username')}">
	<spring:message code="props.me.huqiao.wss.sys.entity.User.username" />
</th>
<th data-sortfield="chineseName" class="${nfn:sortClass(pageBean,'chineseName')}">
	<spring:message code="props.me.huqiao.wss.sys.entity.User.chineseName" />
</th>
<th data-sortfield="status" class="${nfn:sortClass(pageBean,'status')}">
	<spring:message code="props.me.huqiao.wss.sys.entity.User.status" />
</th>
<th data-sortfield="phone" class="${nfn:sortClass(pageBean,'email')}">
	<spring:message code="props.me.huqiao.wss.sys.entity.User.email" />
</th>
<th data-sortfield="phone" class="${nfn:sortClass(pageBean,'phone')}">
	<spring:message code="props.me.huqiao.wss.sys.entity.User.phone" />
</th>
<th data-sortfield="dept" class="${nfn:sortClass(pageBean,'dept')}">
	<spring:message code="props.me.huqiao.wss.sys.entity.User.dept" />
</th>
