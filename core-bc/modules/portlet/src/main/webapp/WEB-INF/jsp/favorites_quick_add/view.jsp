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

<div class="favorites-quickadd">
	<c:choose>
		<c:when test="${isCurrentLayoutUserFavorite}">
		
			<portlet:actionURL name="deleteFavorite" var="deleteFavoriteUrl">
				<portlet:param name="action" value="deleteFavorite" />
				<portlet:param name="favoritePlid" value="${currentPlid}" />
			</portlet:actionURL>
			<a class="favorite-link favorite-link-remove" href="${deleteFavoriteUrl}">Ta bort favorit</a>
		</c:when>
		<c:otherwise>
	
			<portlet:actionURL name="addFavorite" var="addFavoriteUrl">
				<portlet:param name="action" value="addFavorite" />
				<portlet:param name="favoritePlid" value="${currentPlid}" />
			</portlet:actionURL>
		
			<a class="favorite-link favorite-link-add" href="${addFavoriteUrl}">L&auml;gg till favorit</a>
		</c:otherwise>
	</c:choose>
</div>