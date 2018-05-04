<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>

<s:layout-render 
	name="/WEB-INF/jsp/page/abstractTracktacularPage.jsp"
	title="Tracktacular > Mobile"
	>

	<s:layout-component name="contentBody">		
	
		<style>
		
			.ui-btn-text {
				position: relative;
				z-index: 1;
				width: 100%;
				-moz-user-select: none;
				-webkit-user-select: none;
				-ms-user-select: none;
			}
			
			.ui-btn-inner {
				-webkit-border-radius: inherit;
				border-radius: inherit;
				font-size: 16px;
				padding: .6em 20px;
				min-width: .75em;
				display: block;
				position: relative;
				text-overflow: ellipsis;
				overflow: hidden;
				white-space: nowrap;
				zoom: 1;
				border-top: 1px solid #FFF;
				border-color: 
			}
			
			.ui-btn {
				color: white !important;
				display: block;
				text-align: center;
				cursor: pointer;
				position: relative;
				margin: .5em 0;
				padding: 0;
				-moz-box-shadow: 0 1px 3px rgba(0,0,0,.2);
				-webkit-box-shadow: 0 1px 3px rgba(0, 0, 0, .2);
				box-shadow: 0 1px 3px rgba(0, 0, 0, .2);
				-webkit-background-clip: padding;
				background-clip: padding-box;
				-webkit-border-radius: 1em;
				border-radius: 1em;
				font-family: Helvetica, Arial, sans-serif;
				text-decoration: none !important;
				border: 1px solid #044062;
				background: #396B9E;
				font-weight: bold;
				color: #FFF;
				text-shadow: 0 1px 0 #194B7E;
				background-image: -webkit-gradient(linear, left top, left bottom, from(#5F9CC5), to(#396B9E));
				background-image: -webkit-linear-gradient(#5F9CC5 , #396B9E);
				background-image: -moz-linear-gradient(#5F9CC5 , #396B9E);
				background-image: -ms-linear-gradient(#5F9CC5 , #396B9E);
				background-image: -o-linear-gradient(#5F9CC5 , #396B9E);
				background-image: linear-gradient(#5F9CC5 , #396B9E);
			}	
			
		</style>
			
		<div class="home-content">
		
			<h2>Track your data on the go!</h2>
						
			<div class="text">
				Now you can track your data anywhere with <b>Tracktacular Mobile</b>, 
				optimized for tracking your data on devices with limited screen sizes. 
			</div>
			
			<div style="margin-top: 25px;" class="cf">
				<div class="left" style="width: 350px;">
					
					<div class="text"  style="padding-top: 0; padding-right: 0px;">
						Our supplemental mobile app makes reading and entering data quick and painless on the move.
						Simply bookmark the mobile site to your home screen and use it like any other app.
					</div> 
					
					<div class="text"  style="padding-right: 0px;">
						<span class="left icon-l l-iphone-bookmark"></span> <span style="font-size: 24px;margin-left: 10px;"><b>iPhones</b></span> 
					</div>  
					
					<div class="text"  style="padding-right: 0px;padding-top: 10px;">
						 Visit the mobile site from the link below, and press the bookmark icon for a menu. <br/>Select <b>Add to Home Screen</b>.
					</div> 
					
					<div class="text"  style="padding-right: 0px;">
						<span class="left icon-l l-android-bookmark"></span> <span style="font-size: 24px;margin-left: 10px;"><b>Android</b></span>
					</div> 
					
					<div class="text"  style="padding-right: 0px;padding-top: 10px;">
						Visit the mobile site from the link below, and press the bookmark icon. Then on the Bookmarks
						screen, press and hold the bookmark for a menu.<br/> Select <b>Add to Home Screen</b>.
					</div> 
					
					<div class="text center" style="padding-right: 0px;">
						<div class="center" style="margin-top: 25px;">
							<s:link class="ui-btn" beanclass="com.tracktacular.web.page.IndexMobileActionBean">
								<span class="ui-btn-inner">
									<span class="ui-btn-text">Visit The Mobile Site</span>
								</span>
							</s:link>
							<div class="subscript">Switch back to the full site with the settings cog</div>
						</div>
					</div> 
				</div>
				<div class="right" style="width: 415px;">
					<img src="/static/tracktacular/img/page/screenshot.png"/>
				</div>
			</div>
			
			<div class="center title" style="margin-top: 35px;">
				Please note the mobile site is still in beta and is subject to change. <br/>
				We appreciate any feedback for features not working for certain devices.
			</div>
			
		</div>
			
	</s:layout-component>
	
</s:layout-render>