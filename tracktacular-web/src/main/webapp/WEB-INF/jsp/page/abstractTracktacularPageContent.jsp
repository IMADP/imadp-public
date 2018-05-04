<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>

	<div id="content">
	
		<%-- Content Header --%>
		<header id="content-header" class="heading">			
			<div class="holder">				
				<h1>
					<s:layout-component name="contentHeading">Tracktacular</s:layout-component>
				</h1>				
				<nav id="nav">
					
					<%-- Content Header Navigation --%>								
					<s:layout-component name="contentHeaderNavigation">	
						<c:if test="${actionBean.contentHeaderLinks != null}">
							<ul>			
								<c:forEach var="link" items="${actionBean.contentHeaderLinks}" varStatus="status">
									<li class="hover ${link.beanClass == actionBean.beanClass ? 'active' : ''}">
										<i:link link="${link}"><span class="arrow">arrow</span></i:link>
									</li>
								</c:forEach>
							</ul>
						</c:if>				
					</s:layout-component>
					
				</nav>			
			</div>			
		</header>
		
		<%-- Content Edit --%>
		<div id="content-edit" class="none"></div>
		
		<%-- Content View --%>
		<div id="content-view">
			
			<%-- Content Notification --%>
			<c:if test="${actionBean.displayTrackerUserNotification}">
				<div class="cf" style="font-size: 12px; line-height: 21px; padding: 2px 10px; border-bottom: 1px solid #dfc269; background: #fff6a7;">
					<div class="left">
						<span class="icon-s s-warn-clear" style="margin-bottom: -3px;"></span> 
						Viewing public data of user: <b>${actionBean.trackerUserUsername}</b>
					</div>
					<div class="right">
						<c:if test="${actionBean.context.loggedIn}">
							Click here to <i:link link="${actionBean.userToggleLink}">View Your Data</i:link>
						</c:if>
						<c:if test="${!actionBean.context.loggedIn}">
							<s:link beanclass="com.tracktacular.web.page.account.LoginActionBean">Login</s:link> or  
							<s:link beanclass="com.tracktacular.web.page.account.JoinActionBean">Try it for free</s:link> 
							to track your data
						</c:if>
					</div>
				</div>	
			</c:if>
			
			<%-- Content Messages --%>
			<div id="content-messages"></div>
				
			<%-- Content Body --%>
			<div id="content-body">
				<s:layout-component name="contentBody">
					<section>
						<div class="js-show center report-indicator">
							<img class="center" src="/static/tracktacular/img/page/busy-indicator.gif" alt="Loading"/>
						</div>
					</section>
				</s:layout-component>
			</div>	
			
			<%-- Content Warning --%>
			<div class="message-box message-error cf js-hide">
				<div class="message-icon"><span class="icon-l l-error"></span></div>
				<div class="message-content">
					<div>Sorry, Tracktacular requires javascript enabled to work properly</div>
				</div>
			</div>
			
		</div>
			
	</div>