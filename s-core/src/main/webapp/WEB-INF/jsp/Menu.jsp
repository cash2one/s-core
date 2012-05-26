<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="menu">
    <table id="menu_table">
        <col width="220px"/>
        <tr>
            <td><s:a action="viewTaskStatus">view task status</s:a></td>
        </tr>
        <tr>
            <td><s:a action="viewCachedProxies">cached proxies</s:a></td>
        </tr>
        <tr>
            <td><s:a action="listDoorways">doorways</s:a></td>
        </tr>
        <tr>
            <td><s:a action="listDoorwayConfigurations">doorway configurations</s:a></td>
        </tr>
        <tr>
            <td><s:a action="listTemplates">templates</s:a></td>
        </tr>
        <tr>
            <td><s:a action="listKeywordGroups">keyword groups</s:a></td>
        </tr>
        <tr>
            <td><s:a action="listTextSources">text sources</s:a></td>
        </tr>
        <tr>
            <td><s:a action="listAutoConfigs">auto configs</s:a></td>
        </tr>
        <tr>
            <td><s:a action="listRedirectScripts">redirect scripts</s:a></td>
        </tr>
        <tr>
            <td><s:a action="openCreateDoorwayPage">create doorway</s:a></td>
        </tr>
    </table>
</div>