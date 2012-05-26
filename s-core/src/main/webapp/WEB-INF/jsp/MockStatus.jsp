<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Mock tasks status jsp page</title></head>
<body>
<ul>
    <s:iterator value="taskStatuses">
        <li>Id: <s:property value="id"/>, status: <s:property value="status"/>, messages size: <s:property value="messages.size"/></li>
        <ul>
            <s:iterator value="messages">
                <li><s:property/></li>
            </s:iterator>
        </ul>
    </s:iterator>
</ul>
</body>
</html>