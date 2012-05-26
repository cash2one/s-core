<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<html>
<head>
    <script type="text/javascript">
/*
        dojo.event.topic.subscribe("/update", function(data, type, request) {
            alert(type);
        });
*/

    </script>
    <title><tiles:insertAttribute name="title"/></title>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1251">
    <tiles:importAttribute name="styles" scope="request"/>
    <s:iterator value="#request['styles']">
        <link rel="stylesheet" type="text/css" href="<s:property/>"/>
    </s:iterator>
    <tiles:importAttribute name="scripts" scope="request"/>
    <s:iterator value="#request['scripts']">
        <script type="text/javascript" src="<s:property/>"></script>
    </s:iterator>
    <script type="text/javascript">
    </script>
</head>

<body class="tundra">
<tiles:insertAttribute name="menu"/>
<tiles:insertAttribute name="body"/>
</body>

</html>