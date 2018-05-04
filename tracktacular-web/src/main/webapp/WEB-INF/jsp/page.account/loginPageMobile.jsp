<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>

<s:layout-render 
	name="/WEB-INF/jsp/page/abstractTracktacularPage.jsp"
	title="Tracktacular > Login">

	<s:layout-component name="contentHeading">		
		Login	
	</s:layout-component>
	
	<s:layout-component name="contentBody">	
	
		<s:form beanclass="com.tracktacular.web.page.account.LoginActionBean" class="dialog-form" >
				
			<div class="dialog-form-body">
			
				<p>Login to your Tracktacular account</p>
			
				<s:errors />
	
				<i:action name="loginAction"/>
				<s:hidden name="mobile" value="true"/>
				<s:hidden name="loginAction.lastUrl" value="${actionBean.loginAction.lastUrl}"/>
						
				<s:label class="primary required" for="loginAction.username">Username or Email</s:label>
				<div class="ui-input-text ui-shadow-inset ui-corner-all ui-btn-shadow ui-body-d">
					<s:text name="loginAction.username" value="${actionBean.loginAction.username}"/>
				</div>				
				
				<s:label class="primary required" for="loginAction.password">Password</s:label>				
				<div class="ui-input-text ui-shadow-inset ui-corner-all ui-btn-shadow ui-body-d">
					<s:password name="loginAction.password"/>
				</div>	
						
				<div style="margin-top:15px" class="ui-submit ui-btn ui-shadow ui-btn-corner-all ui-btn-up-b">
					<span class="ui-btn-inner">
						<span class="ui-btn-text">Submit</span>
						<span class="ui-icon ui-icon-grid ui-icon-shadow"></span>
					</span>
					<s:submit class="ui-btn-hidden" name="login" value="Submit" />
				</div>
				
			</div>
		</s:form>	
				
	</s:layout-component>
	
</s:layout-render>