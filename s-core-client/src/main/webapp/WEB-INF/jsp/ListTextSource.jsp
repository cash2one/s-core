<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="main">
    <div id="control">
        <s:a action="createTextSource">create</s:a>
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
        <s:iterator value="textSources">
            <tr>
                <td><s:property value="id"/></td>
                <td><s:property value="name"/></td>
                <td>
                    <s:a action="deleteTextSource"><s:param name="id" value="id"/>delete</s:a>
                </td>
            </tr>
        </s:iterator>
        </tbody>
    </table>
</div>