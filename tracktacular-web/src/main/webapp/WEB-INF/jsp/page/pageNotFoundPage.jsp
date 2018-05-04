<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>

<s:layout-render 
	name="/WEB-INF/jsp/page/abstractTracktacularPage.jsp"
	title="Tracktacular > Page Not Found"
	>

	<s:layout-component name="contentHeading">		
		Page Not Found
	</s:layout-component>
	
	<s:layout-component name="contentBody">		
		
		<div class="message-box message-error cf">
			<div class="message-icon"><span class="icon-l l-error"></span></div>
			<div class="message-content">
				<div>Sorry, we couldn't find the page you were looking for!</div>
		    </div>
			<div class="message-close"></div>
		</div>
		
		<h2>Our apologies...</h2>
		
		<p style="margin: 25px 60px 0 60px;">
			We couldn't find the page you were looking for.
			Please ensure that the url you are pointing to is correct.
		</p>
		
	</s:layout-component>
	
</s:layout-render>