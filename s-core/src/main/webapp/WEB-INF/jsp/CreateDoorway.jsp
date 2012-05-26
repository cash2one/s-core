<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="main">
    <s:form action="createDoorway" theme="simple">
        <s:label for="request.doorwayConfigurationId">Doorway configuration: </s:label>
        <s:select name="request.doorwayConfigurationId" list="doorwayConfigurations" listKey="id" listValue="name"/>
        <br/>
        <s:label for="request.templateId">Template: </s:label>
        <s:select name="request.templateId" list="templates" listKey="id" listValue="name"/>
        <br/>
        <s:label for="request.keywordGroupId">Keyword group: </s:label>
        <s:select name="request.keywordGroupId" list="keywordGroups" listKey="id" listValue="name"/>
        <br/>
        <s:label for="request.textSourceId">Text source: </s:label>
        <s:select name="request.textSourceId" list="textSources" listKey="id" listValue="name"/>
        <br/>
        <s:label for="request.redirectScriptId">Redirect script: </s:label>
        <s:select name="request.redirectScriptId" list="redirectScripts" listKey="id" listValue="name"/>
        <br/>
        <s:label for="request.doorwayCount">Doorway count: </s:label>
        <s:textfield name="request.doorwayCount" value="%{request.doorwayCount}"/>
        <br/>
        <p>Available FTP Accounts: <s:property value="ftpAccountCount"/></p>
        <br>
        <s:submit/>
    </s:form>
</div>