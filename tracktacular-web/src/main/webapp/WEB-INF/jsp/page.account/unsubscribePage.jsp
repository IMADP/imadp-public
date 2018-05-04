<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>

<s:layout-render 
	name="/WEB-INF/jsp/page/abstractTracktacularPage.jsp"
	title="Tracktacular > Unsubscribe"
	>

	<s:layout-component name="contentHeading">		
		Unsubscribe	
	</s:layout-component>
	
	<s:layout-component name="contentBody">
		
		
		<section>
		
			<s:form class="dialog-form" beanclass="com.tracktacular.web.page.account.UnsubscribeActionBean">
				<i:action name="unsubscribeAction"/>
				
				<div class="box-shadow">
					
					<div class="dialog-form-heading ui-dialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix">
						<span class="left ui-dialog-title">Unsubscribe</span>
					</div>
					
					<div class="dialog-form-body">
						
						<s:errors />
							
						<div class="center">
							Thanks for being a part of Tracktacular. Clicking submit will cancel your subscription.
							You will not be billed again, but your subscription will remain active until the end of your billing period.
						</div>
				
					</div>
					
					<hr>
					
					<div class="dialog-form-footer cf">
						<div class="dialog-form-footer-button">
							<s:submit class="dialog-form-button ui-state-default ui-priority-primary ui-corner-all" 
							name="unsubscribe" value="Submit" />
						</div>
					</div>
					
				</div>
			</s:form>
			
		</section>
			
	</s:layout-component>
	
</s:layout-render>