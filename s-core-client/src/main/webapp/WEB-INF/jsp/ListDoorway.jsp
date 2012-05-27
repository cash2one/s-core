<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="main">
    <div id="control_links">
    </div>
    <s:form theme="simple" action="processDoorwayCommand">
        <table class="content">
            <col width="100px"/>
            <col width="100px"/>
            <col width="400px"/>
            <col width="100px"/>
            <thead>
            <tr>
                <th>
                    <label for="ids_all">All</label>
                    <input type="checkbox" id="ids_all"/>
                </th>
                <th>State</th>
                <th>URL</th>
                <th>Index</th>
            </tr>
            </thead>
            <tbody>
            <s:iterator value="doorways">
                <s:if test="doorwayState.toString() != 'ARCHIVED'">
                    <tr>
                        <td>
                            <s:label for="ids"><s:property value="id"/></s:label>
                            <s:checkbox cssClass="idcheckbox" fieldValue="%{id}" name="ids" label="%{id}"/>
                        </td>
                        <td><s:property value="doorwayState"/></td>
                        <td><a href="<s:property value="url"/>"><s:property value="url"/></a></td>
                        <td><s:property value="yandexIndex"/></td>
                    </tr>
                </s:if>
            </s:iterator>
            </tbody>
        </table>
        <s:select list="commands" name="command" headerKey="" headerValue="[choose one]"/>
        <s:submit/>
    </s:form>
</div>