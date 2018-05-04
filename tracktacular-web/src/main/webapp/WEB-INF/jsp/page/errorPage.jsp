<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>

<s:layout-render 
	name="/WEB-INF/jsp/page/abstractTracktacularPage.jsp"
	title="Tracktacular > Error"
	>

	<s:layout-component name="contentHeading">		
		Error	
	</s:layout-component>
	
	<s:layout-component name="contentBody">		
	
		<div class="message-box message-error cf">
			<div class="message-icon"><span class="icon-l l-error"></span></div>
			<div class="message-content">
				<div>Please excuse us, something has gone horribly wrong!</div>
		    </div>
			<div class="message-close"></div>
		</div>
		
		<h2>Our apologies...</h2>
		
		<p style="margin: 25px 60px 0 60px;">
			Something seems to have gone terribly wrong with Tracktacular.
			Our techs have been notified and beaten to make sure this doesn't happpen again.
			Please try again later, or feel free to contact us if the problem persists.
		</p>
		
	</s:layout-component>
	
</s:layout-render>