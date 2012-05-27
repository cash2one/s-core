<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="main">
    <div id="run_config">
        <s:form theme="simple" action="runAutoConfig">
            <s:select name="autoConfigId" list="autoConfigs" listKey="id" listValue="name"/><s:textfield name="times"/>
            <br/>
            <s:submit/> 
        </s:form>
    </div>
    <div id="control_links">
        <s:a action="createAutoConfig">create</s:a>
    </div>
    <table class="content">
        <thead>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <s:iterator value="autoConfigs">
            <tr>
                <td><s:property value="id"/></td>
                <td><s:property value="name"/></td>
                <td>
                    <s:a action="deleteAutoConfig"><s:param name="id" value="id"/>delete</s:a>
                </td>
            </tr>
        </s:iterator>
        </tbody>
    </table>
</div>