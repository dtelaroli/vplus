<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ tag body-content="empty" %> 

<%@ attribute name="value" required="true" %>
<c:url var="url" value="${value}"/>
<link rel="stylesheet" href="${url}"/>