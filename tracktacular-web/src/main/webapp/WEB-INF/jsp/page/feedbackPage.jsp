<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>

<s:layout-render 
	name="/WEB-INF/jsp/page/abstractTracktacularPage.jsp"
	title="Tracktacular > Feedback"
	>

	<s:layout-component name="contentHeading">		
		Feedback
	</s:layout-component>
	
	<s:layout-component name="contentBody">		
	
		<section>
		
			<s:form class="dialog-form" beanclass="com.tracktacular.web.page.FeedbackActionBean">
				<div class="box-shadow">
					
					<div class="dialog-form-heading ui-dialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix">
						<span class="left ui-dialog-title" id="ui-dialog-title-2">Feedback</span>
					</div>
					
					<div class="dialog-form-body">
						<s:errors />
						
						<div>
							So what do you think about Tracktacular?  Whether its a suggestion or a complaint, praise or an insult, 
							we love getting feedback from our users.
						</div>
				
						<c:if test="${actionBean.context.loggedIn}">
							<br/>
							<div>
								<b>Just a heads up </b>- you are not logged in, so your feedback will be anonymous. To get in touch with us 
								personally please send an email to <a href="mailto:webmaster@tracktacular.com">webmaster@tracktacular.com</a>.
							</div>
						</c:if>
					
						<s:label class="primary required" for="feedback.content">Feedback</s:label>				
						<i:out><s:textarea id="feedback.content" name="feedback.content" rows="5"/></i:out>		
							
					</div>
					
					<hr>
					
					<div class="dialog-form-footer cf">
						<div class="dialog-form-footer-button">
							<s:submit class="dialog-form-button ui-state-default ui-priority-primary ui-corner-all" name="saveFeedback" value="Submit" />
						</div>
					</div>
					
				</div>
			</s:form>
			
		</section>
			
	</s:layout-component>
	
</s:layout-render>