<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="value" required="true" %>
<%@ tag body-content="empty" %> 

<c:url var="url" value="${value}"/>
<script type="text/javascript" src="${url}"></script>