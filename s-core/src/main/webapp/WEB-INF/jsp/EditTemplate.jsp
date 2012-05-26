<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="main">
    <div id="errors">
        <s:actionerror/>
        <s:actionmessage/>
        <s:fielderror/>
    </div>
    <div id="templateForm">
        <s:form theme="simple" action="saveTemplate" method="POST" enctype="multipart/form-data">
            <s:label for="request.name">Template name:</s:label>
            <s:textfield name="request.name"/>
            <br/>
            <s:label for="request.indexTemplate">Index template</s:label>
            <s:file name="request.indexTemplate"/>
            <br/>
            <s:label for="request.contentTemplate">Content template</s:label>
            <s:file name="request.contentTemplate"/>
            <br/>
            <s:label for="request.categoryTemplate">Category template</s:label>
            <s:file name="request.categoryTemplate"/>
            <br/>
            <s:label for="request.robotsTemplate">Robots template</s:label>
            <s:file name="request.robotsTemplate"/>
            <br/>
            <s:label for="request.sitemapTemplate">Sitemap template</s:label>
            <s:file name="request.sitemapTemplate"/>
            <br/>
            <s:label for="request.sitemapXmlTemplate">sitemap.xml template</s:label>
            <s:file name="request.sitemapXmlTemplate"/>
            <br/>
            <s:label for="request.media">Media</s:label>
            <s:file name="request.media"/>
            <br/>
            <s:submit/>
        </s:form>
    </div>
</div>