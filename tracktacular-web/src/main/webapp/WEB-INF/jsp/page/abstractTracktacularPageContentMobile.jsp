<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>

	<div id="content" data-role="content" class="ui-content">					
		
		<%-- Content Header --%>
		<header id="content-header" class="heading">			
			<div class="holder">				
				<nav id="nav">
					<s:layout-component name="contentHeaderNavigation" />
				</nav>			
			</div>			
		</header>
		
		<%-- Content Load --%>
		<div id="content-load" class="none">
			<section>
				<div class="js-show center" style="padding: 10px; margin-top: 15px;">
					<img class="center" src="/static/tracktacular/img/page/busy-indicator.gif" alt="Loading"/>
				</div>
			</section>
		</div>
		
		<%-- Content Edit --%>
		<div id="content-edit" class="none"></div>
		
		<%-- Content View --%>
		<div id="content-view">
			
			<%-- Content Messages --%>
			<div id="content-messages"></div>
				
			<%-- Content Body --%>
			<div id="content-body">
				<s:layout-component name="contentBody">
					<section>
						<div class="js-show center" style="padding: 10px; margin-top: 15px;">
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