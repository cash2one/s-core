<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="main">
    <div id="control">
        <s:a href="clearTasks">clear tasks</s:a>
    </div>
    <div id="tasks">
        <table class="content">
            <col width="100px"/>
            <col width="250px"/>
            <col width="60px"/>
            <col width="800px"/>
            <col width="120px"/>
            <thead>
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Status</th>
                <th>Last message</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <s:iterator value="taskStatuses">
                <tr>
                    <td><s:property value="id"/></td>
                    <td><s:property value="name"/></td>
                    <td><s:property value="status"/></td>
                    <td><s:property value="lastMessage.content"/></td>
                    <td>
                        <s:if test="status == 'WAITING' || status == 'RUNNING'">
                            <s:a action="interruptTask"><s:param name="id" value="id"/>cancel</s:a>
                        </s:if>
                        <s:a action="viewTaskMessages"><s:param name="id" value="id"/>details</s:a>
                    </td>
                </tr>
            </s:iterator>
            </tbody>
        </table>
    </div>
    <s:if test="taskStatus != null">
        <div id="task_details">
            Task id: <s:property value="taskStatus.id"/>
            <br/>
            Task status: <s:property value="taskStatus.status"/>
            <br/>
            Messages:
            <br/>
            <ul>
                <s:iterator value="taskStatus.messages">
                    <li><s:property value="content"/></li>
                </s:iterator>
            </ul>
        </div>
    </s:if>
</div>