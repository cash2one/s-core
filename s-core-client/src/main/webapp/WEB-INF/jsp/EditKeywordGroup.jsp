<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="main">
    <div id="errors">
        <s:actionerror/>
        <s:actionmessage/>
        <s:fielderror/>
    </div>
    <s:form theme="simple" action="saveKeywordGroup" method="POST" enctype="multipart/form-data">
        <s:label for="request.name">Name:</s:label>
        <s:textfield name="request.name"/>
        <br/>
        <s:label for="request.content">Keywords file:</s:label>
        <s:file name="request.content"/>
        <br/>
        <s:submit/>
    </s:form>
</div>