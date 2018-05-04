<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>

<s:layout-render 
	name="/WEB-INF/jsp/page/abstractTracktacularPage.jsp"
	title="Tracktacular > Login"
	>

	<s:layout-component name="contentHeading">		
		Login	
	</s:layout-component>
	
	<s:layout-component name="contentBody">	
	
		<section>
		
			<s:form class="dialog-form" beanclass="com.tracktacular.web.page.account.LoginActionBean">
				<div class="box-shadow">
					
					<div class="dialog-form-heading ui-dialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix">
						<span class="left ui-dialog-title" id="ui-dialog-title-2">Login</span>
					</div>
					
					<div class="dialog-form-body">
						<div class="center">
							Login to Tracktacular, or 
							<s:link beanclass="com.tracktacular.web.page.account.JoinActionBean">try it for free</s:link> 
							if you don't have an account.
						</div>
				
						<s:errors />
			
						<i:action name="loginAction"/>
						<s:hidden name="loginAction.lastUrl" value="${actionBean.loginAction.lastUrl}"/>
								
						<div class="cf">
							<div class="half">
								<s:label class="primary required" for="loginAction.username">Username or Email</s:label>				
								<i:out><s:text name="loginAction.username" value="${actionBean.loginAction.username}"/></i:out>
							</div>
							<div class="half">
								<s:label class="primary required" for="loginAction.password">Password</s:label>				
								<s:password name="loginAction.password"/>	
							</div>
						</div>		
								
						<br/>
						
						<s:checkbox class="inline" name="loginAction.rememberMe" checked="${actionBean.loginAction.rememberMe}"  />				
						<s:label for="loginAction.rememberMe" style="display:inline;">Remember Me?</s:label>				
						
						<br/>
						<br/>
						
						<%-- Reset Password --%>
						<div class="center">
							Trouble logging in? Click here to
							<a href="#" class="to-dialog-form" data-dialog-form-template="resetPasswordDialog">
								Reset your password
							</a>
						</div>
							
					</div>
					
					<hr>
					
					<div class="dialog-form-footer cf">
						<div class="dialog-form-footer-button">
							<s:submit class="dialog-form-button ui-state-default ui-priority-primary ui-corner-all" name="login" value="Submit" />
						</div>
					</div>
					
				</div>
			</s:form>
			
		</section>		
				
	</s:layout-component>	
	
	<%-- JS Resources --%>	
	<s:layout-component name="jsResources">
		
		<%-- JavaScript CDN Resources --%>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.16/jquery-ui.min.js"></script>			
		
		<%-- JavaScript Fallback Resources --%>
		<script>//<![CDATA[
           	window.jQuery||document.write('<script src="/static/tracktacular/js/lib/jquery-1.7.1.min.js"><\/script>')//]]>
		</script>					
		<script>//<![CDATA[
           	window.jQuery.ui||document.write('<script src="/static/tracktacular/js/lib/jquery.ui-1.8.16.min.js"><\/script>')//]]>
           </script>			
		
		<%-- JavaScript Resources --%>
		<script src="/static/tracktacular/js/page-${actionBean.pageVersion}.js"></script>			 	
	 	<script src="/static/tracktacular/js/template-${actionBean.pageVersion}.js"></script>
	 	
 	 </s:layout-component>
	
</s:layout-render>