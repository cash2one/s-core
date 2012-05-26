<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="main">
    <div id="errors">
        <s:actionerror/>
        <s:actionmessage/>
        <s:fielderror/>
    </div>
    <div id="autoConfigForm">
        <s:form theme="simple" action="saveAutoConfig" method="POST" enctype="multipart/form-data">
            <s:label for="request.name">Auto Config name:</s:label>
            <s:textfield name="request.name"/>
            <br/>
            <s:label for="request.content">Content</s:label>
            <s:file name="request.content"/>
            <br/>
            <s:submit/>
        </s:form>
    </div>
</div>