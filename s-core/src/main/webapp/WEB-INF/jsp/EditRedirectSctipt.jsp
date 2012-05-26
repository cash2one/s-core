<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="main">
    <div id="errors">
        <s:actionerror/>
        <s:actionmessage/>
        <s:fielderror/>
    </div>
    <div id="redirectScriptForm">
        <s:form theme="simple" action="saveRedirectScript" method="POST">
            <s:hidden name="redirectScript.id" value="%{redirectScript.id}"/>
            <s:label for="redirectScript.name">Redirect Script name:</s:label>
            <s:textfield name="redirectScript.name" value="%{redirectScript.name}"/>
            <br/>
            <s:label for="redirectScript.fileName">Redirect Script file name:</s:label>
            <s:textfield name="redirectScript.fileName" value="%{redirectScript.fileName}"/>
            <br/>
            <s:label for="redirectScript.content">Content:</s:label>
            <s:textarea rows="10" cols="10" name="redirectScript.content" value="%{redirectScript.content}"/>
            <br/>
            <s:submit/>
        </s:form>
    </div>
</div>