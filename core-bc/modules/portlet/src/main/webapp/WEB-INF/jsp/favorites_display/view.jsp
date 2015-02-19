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

<div class="content-box">
	<h2>Mina favoriter</h2>
	<div class="content-box-bd">
		<c:choose>
			<c:when test="${fn:length(favoriteLayouts) gt 0 }">
				<ul class="favorites-list">
					<c:forEach var="favoriteLayout" items="${favoriteLayouts}">
						<li>
							
							<a href="${favoriteLayout.friendlyURL}">
								${favoriteLayout.name} &raquo;
							</a>
						</li>
					</c:forEach>
				</ul>
			</c:when>
			<c:otherwise>
				<div class="portlet-msg-info">
					Du har inte lagt till n&aring;gra favoriter &auml;nnu.
				</div>
			</c:otherwise>
		</c:choose>	
	</div>
</div>

