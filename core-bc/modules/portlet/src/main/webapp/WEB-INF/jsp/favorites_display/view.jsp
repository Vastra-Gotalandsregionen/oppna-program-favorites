<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<portlet:defineObjects />
<liferay-theme:defineObjects />	

<p>Favorites Display in view mode.</p>

<c:choose>
	<c:when test="${fn:length(favoriteLayouts) gt 0 }">
		<p>Found favorites</p>
		<ul>
			<c:forEach var="favoriteLayout" items="${favoriteLayouts}">
				<li>
					
					<a href="${favoriteLayout.friendlyURL}">
						${favoriteLayout.name}
					</a>
				</li>
			</c:forEach>
		</ul>
	
		
	</c:when>
	<c:otherwise>
		<p>Did not find any favorites</p>
	</c:otherwise>
</c:choose>