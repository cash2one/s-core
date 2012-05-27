<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="main">
    <div id="control_links">
        <s:a action="clearProxyCache">clear cache</s:a>
    </div>
    <table class="content">
        <col width="600px"/>
        <col width="200px"/>
        <col width="200px"/>
        <thead>
        <tr>
            <th>Proxy</th>
            <th>Keys</th>
            <th>Creation date</th>
        </tr>
        </thead>
        <tbody>
        <s:iterator value="proxyMap">
            <tr>
                <td><s:property value="key"/></td>
                <td>
                    <ul>
                        <s:iterator value="value">
                            <li><s:property/></li>
                        </s:iterator>
                    </ul>
                </td>
                <td>
                    <s:date name="key.creationDate" format="dd-MMM HH:mm:ss"/>
                </td>
            </tr>
        </s:iterator>
        </tbody>
    </table>
</div>