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

<c:if test="${isSignedIn}">
	<div class="favorites-quickadd">
		<c:choose>
			<c:when test="${isCurrentLayoutUserFavorite}">
			
				<portlet:actionURL name="deleteFavorite" var="deleteFavoriteUrl">
					<portlet:param name="action" value="deleteFavorite" />
					<portlet:param name="favoritePlid" value="${currentPlid}" />
				</portlet:actionURL>
				<span class="favorite-trigger favorite-trigger-remove">
					<span class="link-label">
						Ta bort favorit
					</span>
					<span class="icon-star"></span>
				</span>
			</c:when>
			<c:otherwise>
		
				<portlet:actionURL name="addFavorite" var="addFavoriteUrl">
					<portlet:param name="action" value="addFavorite" />
					<portlet:param name="favoritePlid" value="${currentPlid}" />
				</portlet:actionURL>
			
				<span class="favorite-trigger favorite-trigger-add">
					<span class="link-label">
						L&auml;gg till favorit
					</span>
					<span class="icon-star-empty"></span>
				</span>
			</c:otherwise>
		</c:choose>
		
		<div class="quick-add-panel">
			<div class="quick-add-panel-inner">
			
				<div class="link-button-wrap">
					<c:choose>
						<c:when test="${isCurrentLayoutUserFavorite}">
						
							<portlet:actionURL name="deleteFavorite" var="deleteFavoriteUrl">
								<portlet:param name="action" value="deleteFavorite" />
								<portlet:param name="favoritePlid" value="${currentPlid}" />
							</portlet:actionURL>
							
							<a class="link-button" href="${deleteFavoriteUrl}">
								<span class="icon-minus-sign"></span>
								<span>
									Ta bort
								</span>
							</a>
						</c:when>
						<c:otherwise>
					
							<portlet:actionURL name="addFavorite" var="addFavoriteUrl">
								<portlet:param name="action" value="addFavorite" />
								<portlet:param name="favoritePlid" value="${currentPlid}" />
							</portlet:actionURL>
							
							<a class="link-button" href="${addFavoriteUrl}">
								<span class="icon-plus-sign"></span>
								<span>
									L&auml;gg till
								</span>
							</a>						
						
						</c:otherwise>
					</c:choose>
				</div>
			
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
				
				<c:if test="${favoriteLayoutsTotal > paginatorItemsPerPage}">
					<div id="<portlet:namespace />paginator" class="pagination pagination-medium"></div>
				</c:if>
			
				
			</div>
		</div>
		
	</div>

    <aui:script use="aui-base, event-outside, aui-pagination">

        var portletNamespace = '<portlet:namespace />';
        var paginatorItemsPerPage = ${paginatorItemsPerPage};
        var paginatorItemsTotal = ${favoriteLayoutsTotal};
        var paginatorTotalPages = Math.ceil(paginatorItemsTotal / paginatorItemsPerPage);

        var portletNode, quickAddPanel, quickAddWrap, favoritesList, favoriteTriggers, paginator;

        initFavoritesQuickAdd();
        initPaginator();

        function initFavoritesQuickAdd() {

            portletNode = A.one('#p_p_id' + portletNamespace);
            quickAddPanel = portletNode.one('.quick-add-panel');
            quickAddWrap = portletNode.one('.favorites-quickadd');
            favoritesList = portletNode.one('.favorites-list');

            favoriteTriggers = portletNode.all('.favorite-trigger');


            favoriteTriggers.on('click', function(e) {
                e.halt();
                quickAddWrap.toggleClass('favorites-quickadd-open');
            });

            quickAddPanel.on('clickoutside', function(e) {
                if(quickAddWrap.hasClass('favorites-quickadd-open')) {
                    quickAddWrap.removeClass('favorites-quickadd-open');
                }
            });

        }

        function initPaginator() {
            var paginatorBox = A.one('#' + portletNamespace + 'paginator');

            if(paginatorBox) {
                paginator = new A.Pagination({
                    boundingBox: paginatorBox,
                    on: {
                        changeRequest: function(event) {
                            this.setState(event.state);
                            showFavoritesPage(event.state.page)
                        }
                    },
                    page: 1,
                    strings: {
                        next: '&rarr;',
                        prev: '&larr;'
                    },
                    total: paginatorTotalPages
                }).render();
            }
        }

        function showFavoritesPage(pageIndex) {
            var favoriteItems = favoritesList.all('> li');

            /* Hide all*/
            favoriteItems.hide();

            var firstItemToShow = (pageIndex -1) * paginatorItemsPerPage;

            var lastItemToShow = pageIndex * paginatorItemsPerPage - 1;

            for(i = firstItemToShow; i <= lastItemToShow; i++) {
                if(i < paginatorItemsTotal) {
                    favoriteItems.item(i).show();
                }
            }

        }
</aui:script>

</c:if>