<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>


	<div id="navigation">
	
		<%-- Tracktacular Report --%>			
		<figure class="heading">
			<s:link class="tracktacular-report-link" beanclass="com.tracktacular.web.page.TracktacularReportActionBean">
				<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
				<img src="/static/tracktacular/img/page/tracktacular-report.png" width="126" height="79" alt="Tracktacular Report">
				Tracktacular Report
			</s:link>
		</figure>
		
		<%-- Tracker Navigation --%>			
		<nav class="side-nav">
			<ul>
			
				<c:set var="personalLinks">	
					<page:navigationLink tracker="${'BUCKET'}" />
					<page:navigationLink tracker="${'BUDGET'}" />
					<page:navigationLink tracker="${'CALENDAR'}" />
					<page:navigationLink tracker="${'DREAM'}" />
					<page:navigationLink tracker="${'GOAL'}" />
					<page:navigationLink tracker="${'JOURNAL'}" />
					<page:navigationLink tracker="${'RECIPE'}" />
					<page:navigationLink tracker="${'TASK'}" />
					<page:navigationLink tracker="${'WISH'}" />
				</c:set>
				
				<c:if test="${not empty personalLinks}">
					<li>
						<header>personal</header>
						<ul>
							<page:navigationLink tracker="${'BUCKET'}" />
							<page:navigationLink tracker="${'BUDGET'}" />
							<page:navigationLink tracker="${'CALENDAR'}" />
							<page:navigationLink tracker="${'DREAM'}" />
							<page:navigationLink tracker="${'GOAL'}" />
							<page:navigationLink tracker="${'JOURNAL'}" />
							<page:navigationLink tracker="${'RECIPE'}" />
							<page:navigationLink tracker="${'TASK'}" />
							<page:navigationLink tracker="${'WISH'}" />
						</ul>
					</li>
				</c:if>
				
				<c:set var="peopleLinks">	
					<page:navigationLink tracker="${'BIRTHDAY'}" />
					<page:navigationLink tracker="${'GIFT'}" />
				</c:set>
				
				<c:if test="${not empty peopleLinks}">
					<li>
						<header>people</header>
						<ul>
							<page:navigationLink tracker="${'BIRTHDAY'}" />
							<page:navigationLink tracker="${'GIFT'}" />
						</ul>
					</li>
				</c:if>
				
				<c:set var="healthLinks">	
					<page:navigationLink tracker="${'BODY'}" />
					<page:navigationLink tracker="${'BLOOD'}" />
					<page:navigationLink tracker="${'CHOLESTEROL'}" />
					<page:navigationLink tracker="${'EXERCISE'}" />
				</c:set>
				
				<c:if test="${not empty healthLinks}">
					<li>
						<header>health</header>
						<ul>
							<page:navigationLink tracker="${'BODY'}" />
							<page:navigationLink tracker="${'BLOOD'}" />
							<page:navigationLink tracker="${'CHOLESTEROL'}" />
							<page:navigationLink tracker="${'EXERCISE'}" />
						</ul>
					</li>
				</c:if>
				
				<c:set var="entertainmentLinks">	
					<page:navigationLink tracker="${'BOOK'}" />
					<page:navigationLink tracker="${'GAME'}" />
					<page:navigationLink tracker="${'MOVIE'}" />
					<page:navigationLink tracker="${'MUSIC'}" />
					<page:navigationLink tracker="${'RESTAURANT'}" />
					<page:navigationLink tracker="${'TV'}" />
				</c:set>
				
				<c:if test="${not empty entertainmentLinks}">
					<li>
						<header>entertainment</header>
						<ul>
							<page:navigationLink tracker="${'BOOK'}" />
							<page:navigationLink tracker="${'GAME'}" />
							<page:navigationLink tracker="${'MOVIE'}" />
							<page:navigationLink tracker="${'MUSIC'}" />
							<page:navigationLink tracker="${'RESTAURANT'}" />
							<page:navigationLink tracker="${'TV'}" />
						</ul>
					</li>
				</c:if>
				
			</ul>
		</nav>		
		
	</div>