<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>

<s:layout-definition>

	<s:layout-render name="/WEB-INF/jsp/page.tracker/abstractTrackerPage.jsp">

		<s:layout-component name="contentBody">

			<%-- Tracker Content --%>
			<header class="cf tracker-content">
				<div class="left">
					<h2>${actionBean.trackerTitle} Tracker</h2>
					<h4>Version ${actionBean.pageTrackerVersion}</h4>
				</div>
				<div class="right">
					<div class="icon-l l-${actionBean.tracker.name}"></div>
				</div>
			</header>
			
			<article class="tracker-content">
				<p class="text">
					<s:layout-component name="contentBodyTrackerContent" />
				</p>
			</article>
			
			<%-- Tracker Pages --%>
			<section>
				<h2>Pages</h2>
				<div class="pages">
					<c:forEach var="trackerPage" items="${actionBean.trackerPages}" varStatus="status">
						<c:set var="trackerPageName">
							<fmt:message key="${trackerPage.trackerPageKey}"/>
						</c:set>
						<page:page page="${trackerPageName}">
							<fmt:message key="${trackerPage.trackerPageKey}.summary"/>
						</page:page>
					</c:forEach>
					<page:page page="Share">Provides sharing options (for public trackers only). </page:page>
				</div>
			</section>
	
		</s:layout-component>
		
	</s:layout-render>

</s:layout-definition>