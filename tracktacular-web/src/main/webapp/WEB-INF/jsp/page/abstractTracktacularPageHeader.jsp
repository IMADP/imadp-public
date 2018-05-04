<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>


	<%-- Header --%>
	<header id="header">
	
		<strong class="logo">
			<s:link beanclass="com.tracktacular.web.page.IndexActionBean">Tracktacular</s:link>
		</strong>
			
		<c:choose>		
			<c:when test="${actionBean.context.loggedIn}">
				
				<%-- Navigation for logged in users --%>
				<nav class="user-profile">
				
					<div class="option right">
						<s:link class="${actionBean.beanClass.name == 'com.tracktacular.web.page.BlogActionBean' ? 'active' : ''}" beanclass="com.tracktacular.web.page.BlogActionBean">
							<span class="option-icon">
								<span class="icon-l l-blog"></span>
							</span>
							Blog
						</s:link>
					</div>
					
					<div class="option right">
						<s:link class="${actionBean.beanClass.name == 'com.tracktacular.web.page.MobileActionBean' ? 'active' : ''}" beanclass="com.tracktacular.web.page.MobileActionBean">
							<span class="option-icon">
								<span class="icon-l l-mobile"></span>
							</span>
							Mobile
						</s:link>
					</div>
					
					<div class="option right">
						<s:link beanclass="com.tracktacular.web.page.account.LogoutActionBean">
							<span class="option-icon">
								<span class="icon-l l-logout"></span>
							</span>
							Logout
						</s:link>
					</div>
				
					<div class="option right">
						<s:link class="${actionBean.beanClass.name == 'com.tracktacular.web.page.account.AccountActionBean' ? 'active' : ''}" beanclass="com.tracktacular.web.page.account.AccountActionBean">
							<span class="option-icon">
								<span class="icon-l l-account"></span>
							</span>
							Account
						</s:link>
					</div>
					
					<div class="header-info right">
						<div>Welcome <b>${actionBean.userUsername}</b></div>
						<div>
							<c:if test="${actionBean.publicMode}">
								Viewing data of user: <b>${actionBean.trackerUserUsername}</b>
							</c:if>
							<c:if test="${!actionBean.publicMode}">
								Viewing your data
							</c:if>
						</div>
						<div>
							<c:if test="${actionBean.publicMode}">
								Click here to <i:link link="${actionBean.userToggleLink}">View Your Data</i:link>
							</c:if>
							<c:if test="${!actionBean.publicMode}">
								Click here to <i:link link="${actionBean.userToggleLink}">View Demo Data</i:link>
							</c:if>
						</div>
					</div>
					
				</nav>
				
			</c:when>						
			<c:otherwise>
				
				<%-- Navigation for non logged in users --%>
				<nav class="user-profile">
				
					<div class="option right">
						<s:link class="${actionBean.beanClass.name == 'com.tracktacular.web.page.BlogActionBean' ? 'active' : ''}" beanclass="com.tracktacular.web.page.BlogActionBean">
							<span class="option-icon">
								<span class="icon-l l-blog"></span>
							</span>
							Blog
						</s:link>
					</div>
					
					<div class="option right">
						<s:link class="${actionBean.beanClass.name == 'com.tracktacular.web.page.MobileActionBean' ? 'active' : ''}" beanclass="com.tracktacular.web.page.MobileActionBean">
							<span class="option-icon">
								<span class="icon-l l-mobile"></span>
							</span>
							Mobile
						</s:link>
					</div>
					
					<div class="option right">
						<s:link class="${actionBean.beanClass.name == 'com.tracktacular.web.page.account.LoginActionBean' ? 'active' : ''}" beanclass="com.tracktacular.web.page.account.LoginActionBean">
							<span class="option-icon">
								<span class="icon-l l-login"></span>
							</span>
							Login
						</s:link>
					</div>
				
					<div class="option right">
						<s:link class="${actionBean.beanClass.name == 'com.tracktacular.web.page.account.JoinActionBean' ? 'active' : ''}" beanclass="com.tracktacular.web.page.account.JoinActionBean">
							<span class="option-icon">
								<span class="icon-l l-join"></span>
							</span>
							Join
						</s:link>
					</div>
					
					<div class="header-info right">
						<div>You are not logged in</div>
						<div>
							Viewing data of user: <b>${actionBean.trackerUserUsername}</b>
						</div>
						<div>
							<s:link beanclass="com.tracktacular.web.page.account.LoginActionBean">Login</s:link> or  
							<s:link beanclass="com.tracktacular.web.page.account.JoinActionBean">Try it for free</s:link> 
							to track your data
						</div>
					</div>
					
				</nav>
				
			</c:otherwise> 			
		</c:choose>	
	
	</header>
	