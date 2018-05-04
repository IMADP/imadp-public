<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>


	<%-- Header --%>
	<div id="header" data-role="header" class="ui-header ui-bar-a">
			
		<a href="#" title="Menu" class="to-menu ui-btn-left ui-btn ui-btn-up-b ui-shadow ui-btn-corner-all ui-btn-inline ui-btn-icon-notext"
			 data-menu-form-template="headerMenu">
			<span class="ui-btn-inner">
				<span class="ui-icon ui-icon-gear ui-icon-shadow">&nbsp;</span>
			</span>
		</a>
	
		<h1 id="title" class="ui-title">
			<s:layout-component name="contentHeading">
				Tracktacular
			</s:layout-component>
		</h1>

		<a href="#" title="Home" class="to-home ui-btn-right ui-btn ui-btn-up-b ui-shadow ui-btn-corner-all ui-btn-icon-notext" >
			<span class="ui-btn-inner">
				<span class="ui-icon ui-icon-home ui-icon-shadow">&nbsp;</span>
			</span>
		</a>
		
	</div>
	
	<%-- Content Notification --%>
	<c:if test="${actionBean.displayTrackerUserNotification}">
		
		<%-- Logged out users --%>
		<c:if test="${!actionBean.context.loggedIn}">
			
			<article style="margin:15px 15px 0 15px; padding: 15px 5px;" class="bold message-warn item ui-body ui-body-d ui-corner-all center">
				You are viewing a demo of Tracktacular. Login or Join to start tracking your data. 
			</article>
			
			<div class="ui-grid-a">
	            <div class="ui-block-a">
					<div style="margin: 15px 0 0 10px;" class="ui-btn-text">
						<s:link beanclass="com.tracktacular.web.page.account.JoinActionBean" class="ui-btn ui-shadow ui-btn-corner-all ui-btn-up-a" data-external="true">
							<s:param name="mobile" value="true"/>
				    		<span class="ui-btn-inner">
								<span class="ui-btn-text">
									Join
								</span>
							</span>
				    	</s:link>
					</div>
	            </div>
	            <div class="ui-block-b">
					<div style="margin: 15px 10px 0 0;" class="ui-btn-text">
						<s:link beanclass="com.tracktacular.web.page.account.LoginActionBean" class="ui-btn ui-shadow ui-btn-corner-all ui-btn-up-a" data-external="true">
							<s:param name="mobile" value="true"/>
				    		<span class="ui-btn-inner">
								<span class="ui-btn-text">
									Login
								</span>
							</span>
				    	</s:link>
					</div>
	            </div>
	        </div>
	        
		</c:if>
		
		<%-- Logged in users --%>
		<c:if test="${actionBean.context.loggedIn}">
			
			<article style="margin:15px 15px 10px 15px; padding: 15px 5px;" class="bold message-warn item ui-body ui-body-d ui-corner-all center">
				Viewing public data of user: <b>${actionBean.trackerUserUsername}</b>
			</article>
			
			<div class="ui-grid-solo">
	            <div class="ui-block-a">
					<div class="ui-btn-text">
						<s:link beanclass="com.tracktacular.web.page.IndexMobileActionBean" 
							class="ui-btn ui-shadow ui-btn-corner-all ui-btn-up-a" 
							style="padding: 10px;margin: 5px 15px 0 15px" data-external="true" >   
				    		<s:param name="trackerUserUsername" value="${actionBean.userUsername}"/>
				    		View Your Data
				    	</s:link>
					</div>
				</div>
			</div>
		</c:if>
		
	</c:if>
	