<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="main">
    <div id="errors">
        <s:actionerror/>
        <s:actionmessage/>
        <s:fielderror/>
    </div>
    <div id="textSourceForm">
        <s:form theme="simple" action="saveTextSource" method="POST" enctype="multipart/form-data">
            <s:label for="request.name">Text source name:</s:label>
            <s:textfield name="request.name"/>
            <br/>
            <s:label for="request.content">Content file:</s:label>
            <s:file name="request.content"/>
            <br/>
            <s:submit/>
        </s:form>
    </div>
</div>