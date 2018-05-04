<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>

<s:layout-render 
	name="/WEB-INF/jsp/page/abstractTracktacularPage.jsp"
	title="Tracktacular > Getting Started"
	>

	<s:layout-component name="contentHeading">		
		Getting Started	
	</s:layout-component>
	
	<s:layout-component name="contentBody">		
	
		<div class="home-content">
				
			<h2>Welcome to Tracktacular!</h2>
						
			<div class="text" style="padding-bottom: 15px;">
				The mobile app is designed to be supplemental tool to the Tracktacular web site. Please visit the full site for
				tutorials and a more thorough Getting Started guide.
			</div>
				
			<div class="ui-navbar ui-mini">
				<ul class="ui-grid-solo"> 		
					<li class="ui-block-a">
						<s:link data-external="true" class="ui-btn ui-btn-inline ui-btn-hover-c ui-btn-up-c ui-btn-active" beanclass="com.tracktacular.web.page.IndexMobileActionBean">
							<span class="ui-btn-inner">
								<span class="ui-btn-text">Home</span>
							</span>
						</s:link>
					</li>
				</ul>
			</div>
			
		</div>
			
	</s:layout-component>
	
</s:layout-render>