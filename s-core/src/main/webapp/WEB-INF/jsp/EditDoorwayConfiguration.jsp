<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="main">
    <div id="errors">
        <s:actionerror/>
        <s:actionmessage/>
        <s:fielderror/>
    </div>
    <s:form theme="simple" action="saveDoorwayConfiguration" method="POST">
        <s:hidden value="%{doorwayConfiguration.id}" name="doorwayConfiguration.id"/>
        <table>
                <%--general--%>
            <tr>
                <td class="header" colspan="2">General properties</td>
            </tr>
            <tr>
                <td><s:label for="doorwayConfiguration.name">Name:</s:label></td>
                <td><s:textfield value="%{doorwayConfiguration.name}" name="doorwayConfiguration.name"/></td>
            </tr>
            <tr>
                <td><s:label
                        for="doorwayConfiguration.generalConfiguration.fileExtension">File extension:</s:label></td>
                <td><s:textfield value="%{doorwayConfiguration.generalConfiguration.fileExtension}"
                                 name="doorwayConfiguration.generalConfiguration.fileExtension"/></td>
            </tr>
            <tr>
                <td><s:label for="doorwayConfiguration.generalConfiguration.encoding">Encoding:</s:label></td>
                <td><s:textfield value="%{doorwayConfiguration.generalConfiguration.encoding}"
                                 name="doorwayConfiguration.generalConfiguration.encoding"/></td>
            </tr>

                <%--index page--%>
            <tr>
                <td  class="header" colspan="2">Index page properties</td>
            </tr>
            <tr>
                <td><s:label for="doorwayConfiguration.indexPageConfiguration.title">Index page title:</s:label></td>
                <td><s:textfield name="doorwayConfiguration.indexPageConfiguration.title"
                                 value="%{doorwayConfiguration.indexPageConfiguration.title}"/></td>
            </tr>

                <%--content page--%>
            <tr>
                <td  class="header" colspan="2">Content page properties</td>
            </tr>
            <tr>
                <td><s:label
                        for="doorwayConfiguration.contentPageConfiguration.title">Content page title:</s:label></td>
                <td><s:textfield name="doorwayConfiguration.contentPageConfiguration.title"
                                 value="%{doorwayConfiguration.contentPageConfiguration.title}"/></td>
            </tr>
            <tr>
                <td><s:label
                        for="doorwayConfiguration.contentPageConfiguration.minNumberOfPages">Min number of pages:</s:label></td>
                <td><s:textfield name="doorwayConfiguration.contentPageConfiguration.minNumberOfPages"
                                 value="%{doorwayConfiguration.contentPageConfiguration.minNumberOfPages}"/></td>
            </tr>
            <tr>
                <td><s:label
                        for="doorwayConfiguration.contentPageConfiguration.maxNumberOfPages">Max number of pages:</s:label></td>
                <td><s:textfield name="doorwayConfiguration.contentPageConfiguration.maxNumberOfPages"
                                 value="%{doorwayConfiguration.contentPageConfiguration.maxNumberOfPages}"/></td>
            </tr>
            <tr>
                <td><s:label
                        for="doorwayConfiguration.contentPageConfiguration.minNumberOfContents">Min number of contents:</s:label></td>
                <td><s:textfield name="doorwayConfiguration.contentPageConfiguration.minNumberOfContents"
                                 value="%{doorwayConfiguration.contentPageConfiguration.minNumberOfContents}"/></td>
            </tr>
            <tr>
                <td><s:label
                        for="doorwayConfiguration.contentPageConfiguration.maxNumberOfContents">Max number of contents:</s:label></td>
                <td><s:textfield name="doorwayConfiguration.contentPageConfiguration.maxNumberOfContents"
                                 value="%{doorwayConfiguration.contentPageConfiguration.maxNumberOfContents}"/></td>
            </tr>
            <tr>
                <td><s:label
                        for="doorwayConfiguration.contentPageConfiguration.minContentLength">Min content length:</s:label></td>
                <td><s:textfield name="doorwayConfiguration.contentPageConfiguration.minContentLength"
                                 value="%{doorwayConfiguration.contentPageConfiguration.minContentLength}"/></td>
            </tr>
            <tr>
                <td><s:label
                        for="doorwayConfiguration.contentPageConfiguration.maxContentLength">Max content length:</s:label></td>
                <td><s:textfield name="doorwayConfiguration.contentPageConfiguration.maxContentLength"
                                 value="%{doorwayConfiguration.contentPageConfiguration.maxContentLength}"/></td>
            </tr>


            <%--category page--%>
            <tr>
                <td class="header" colspan="2">Category page properties</td>
            </tr>
            <tr>
                <td><s:label
                        for="doorwayConfiguration.categoryPageConfiguration.minNumberOfPages">Min number of category pages:</s:label></td>
                <td><s:textfield name="doorwayConfiguration.categoryPageConfiguration.minNumberOfPages"
                                 value="%{doorwayConfiguration.categoryPageConfiguration.minNumberOfPages}"/></td>
            </tr>
            <tr>
                <td><s:label
                        for="doorwayConfiguration.categoryPageConfiguration.maxNumberOfPages">Max number of category pages:</s:label></td>
                <td><s:textfield name="doorwayConfiguration.categoryPageConfiguration.maxNumberOfPages"
                                 value="%{doorwayConfiguration.categoryPageConfiguration.maxNumberOfPages}"/></td>
            </tr>
        </table>
        <s:submit/>
    </s:form>
</div>