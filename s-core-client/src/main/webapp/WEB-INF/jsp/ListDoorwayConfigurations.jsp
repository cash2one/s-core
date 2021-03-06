<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="main">
    <div id="control_links">
        <s:a action="createDoorwayConfiguration">create</s:a>
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
        <s:iterator value="doorwayConfigurations">
            <tr>
                <td><s:property value="id"/></td>
                <td><s:property value="name"/></td>
                <td>
                    <s:a action="deleteDoorwayConfiguration"><s:param value="id" name="id"/>delete</s:a>
                    <s:a action="editDoorwayConfiguration"><s:param value="id" name="id"/>edit</s:a>
                </td>
            </tr>
        </s:iterator>
        </tbody>
    </table>
</div>