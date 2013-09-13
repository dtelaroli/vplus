<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="value" required="true" %>
<c:url var="url" value="${value}"/>
<link rel="stylesheet" href="${url}"/>