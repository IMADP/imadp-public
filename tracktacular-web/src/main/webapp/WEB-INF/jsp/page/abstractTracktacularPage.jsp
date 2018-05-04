<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>

<s:layout-definition>

	<%-- Store the template data --%>
	<c:if test="${!actionBean.error}">
		<c:set var="templateData">    		
			<j:object escapeXml="false"> 
				
				<%-- Global content data --%>
				<j:property name="loggedIn"     value="${actionBean.context.loggedIn}"/>
				<j:property name="formAction"   value="${actionBean.context.lastUrl}"/>
				<j:property name="formToken"    value="${actionBean.context.randomToken}"/>
				<j:property name="sessionToken" value="${actionBean.context.sessionToken}"/>
				<j:property name="sourcePage"   value="${actionBean.context.encryptedSourcePage}"/>
				<j:property name="publicMode"   value="${actionBean.publicMode}"/>
				
				<%-- Global mobile content data --%>
				<c:if test="${actionBean.mobile}">
					<%@ include file="/WEB-INF/jsp/page/abstractTracktacularPageContentMobileData.jsp" %>
				</c:if>
				
				<%-- The optional template that determines whether the page body is rendered on the client --%>
				<j:property name="contentBodyTemplate"	value="${contentBodyTemplate}"/>
				
				<%-- Content messages data --%>
				<j:object name="contentMessages">
					<j:object name="errorMessages">	
						<j:property name="level" value="error"/>
						<j:property name="messagesFound" value="${actionBean.context.errorMessagesFound}"/>
						<j:array name="messages" items="${actionBean.context.errorMessagesLocalized}"/>
					</j:object>
					<j:object name="warnMessages">	
						<j:property name="level" value="warn"/>
						<j:property name="messagesFound" value="${actionBean.context.warnMessagesFound}"/>
						<j:array name="messages" items="${actionBean.context.warnMessagesLocalized}"/>
					</j:object>
					<j:object name="infoMessages">	
						<j:property name="level" value="info"/>
						<j:property name="messagesFound" value="${actionBean.context.infoMessagesFound}"/>
						<j:array name="messages" items="${actionBean.context.infoMessagesLocalized}"/>
					</j:object>
					<j:object name="successMessages">	
						<j:property name="level" value="success"/>
						<j:property name="messagesFound" value="${actionBean.context.successMessagesFound}"/>
						<j:array name="messages" items="${actionBean.context.successMessagesLocalized}"/>
					</j:object>
				</j:object>
				
				<%-- Content body data (only loaded for ajax requests) --%>
				<j:object name="contentBody">
					<j:property name="noData" value="${contentBodyNoData}"/>
					<c:if test="${actionBean.context.ajax}">
						<s:layout-component name="contentData"/>
					</c:if>
				</j:object>
				
			</j:object>	
		</c:set>	
	</c:if>	
	
	<%-- If this is an ajax request, return only the template data json --%>
	<c:if test="${actionBean.context.ajax}">
		<i:out>${templateData}</i:out>
	</c:if>		
	
	<%-- If this is not an ajax request, return the non mobile html page --%>
	<c:if test="${!actionBean.context.ajax && !actionBean.mobile}">
			
		<!DOCTYPE html>
		
		<!--[if lt IE 7]> 		<html class="no-js ie6 oldie" lang="en" data-tracker="${tracker}"> <![endif]-->
		<!--[if IE 7]> 			<html class="no-js ie7 oldie" lang="en" data-tracker="${tracker}"> <![endif]-->
		<!--[if IE 8]> 			<html class="no-js ie8 oldie" lang="en" data-tracker="${tracker}"> <![endif]-->
		<!--[if gt IE 8]><!--> 	<html class="no-js" lang="en" data-tracker="${tracker}">       <!--<![endif]-->
		
			<head>
			
				<%-- Meta Tags --%>
								
				<c:set var="metaDescription">
					<s:layout-component name="metaDescription">
						Organize your life in one place with Tracktacular.
					</s:layout-component>
				</c:set>
				
				<c:set var="metaKeywords">
					<s:layout-component name="metaKeywords" >
						tracktacular, trackers, life logging, life logger, <c:forEach var="tracker" items="${actionBean.allTrackers}"> ${tracker.name} tracker, </c:forEach>
					</s:layout-component>
				</c:set>
				
				<meta charset="utf-8" />
				<meta name="description" content="${metaDescription}" />
				<meta name="keywords" content="${metaKeywords}" />
				<meta name="author" content="Tracktacular" />
				<meta name="viewport" content="width=device-width,initial-scale=1" />
			
				<%-- Title --%>
				<title>${title}</title>
			
				<%-- CSS Resources --%>
				<link rel="stylesheet" href="/static/tracktacular/css/page-${actionBean.pageVersion}.css" />
				
				<%-- Favicon Resources --%>
				<link rel="icon" type="image/vnd.microsoft.icon" href="/static/tracktacular/img/favicon.ico">
				<link rel="SHORTCUT ICON" href="/static/tracktacular/img/favicon.ico"/>
				
				<%-- Modernizr to enable HTML5 elements --%>
				<script src="/static/tracktacular/js/lib/modernizr-2.0.6.min.js"></script>
				
			</head>
			
			<body>
			
				<div id="wrapper">
					
					<%-- Header --%>
					<%@ include file="/WEB-INF/jsp/page/abstractTracktacularPageHeader.jsp" %>
					
					<div id="main">
						
						<%-- Content --%>
						<%@ include file="/WEB-INF/jsp/page/abstractTracktacularPageContent.jsp" %>
					
						<%-- Navigation --%>
						<%@ include file="/WEB-INF/jsp/page/abstractTracktacularPageNavigation.jsp" %>
					
					</div>				
					
					<%-- Footer --%>
					<%@ include file="/WEB-INF/jsp/page/abstractTracktacularPageFooter.jsp" %>				
					
				</div>			
			
				<%-- Template Data --%>		
				<div id="templateData" data-template-data="${templateData}"></div>
				
				<s:layout-component name="jsResources" />
					  
		 		<%-- Google Analytics --%>
				<script>
					window._gaq = [['_setAccount','UA-34098204-1'],['_trackPageview'],['_trackPageLoadTime']];
						Modernizr.load({
							load: ('https:' == location.protocol ? '//ssl' : '//www') + '.google-analytics.com/ga.js'
					});
				</script>
				
				<%-- IE 6 support via Chrome Frame --%>
				<!--[if lt IE 7 ]>
					<script src="//ajax.googleapis.com/ajax/libs/chrome-frame/1.0.3/CFInstall.min.js"></script>
					<script>window.attachEvent('onload',function(){CFInstall.check({mode:'overlay'})})</script>
				<![endif]-->
			  
			</body>
		
		</html>
	
	</c:if>		
	
	<%-- If this is not an ajax request, return the mobile html page --%>
	<c:if test="${!actionBean.context.ajax && actionBean.mobile}">
			
		<!DOCTYPE html>
		
		<html data-tracker="${tracker}">
		
			<head>
			
				<%-- Meta Tags --%>
				<meta http-equiv="cleartype" content="on">
				<meta name="HandheldFriendly" content="True">
		        <meta name="MobileOptimized" content="320">
		        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=0"/>
		        <meta name="apple-mobile-web-app-capable" content="yes">
		        <meta name="apple-mobile-web-app-status-bar-style" content="black">
		        <meta name="apple-mobile-web-app-title" content="">
		        <meta name="msapplication-TileImage" content="/static/tracktacular/img/icon/icon-144.png">
		        
				<%-- Title --%>
				<title>${title}</title>
			
				<%-- CSS Resources --%>
				<link rel="stylesheet" href="/static/tracktacular/css/page.mobile-${actionBean.pageMobileVersion}.css" />
				
				<%-- Favicon Resources --%>
				<link rel="icon" type="image/vnd.microsoft.icon" href="/static/tracktacular/img/favicon.ico">
				<link rel="apple-touch-icon-precomposed" sizes="144x144" href="/static/tracktacular/img/icon/icon-144.png">
		        <link rel="apple-touch-icon-precomposed" sizes="114x114" href="/static/tracktacular/img/icon/icon-114.png">
		        <link rel="apple-touch-icon-precomposed" sizes="72x72" href="/static/tracktacular/img/icon/icon-72.png">
		        <link rel="apple-touch-icon-precomposed" href="/static/tracktacular/img/icon/icon-57.png">
		        <link rel="shortcut icon" href="/static/tracktacular/img/icon/icon-57.png">
		
		        <!-- This script prevents links from opening in Mobile Safari. https://gist.github.com/1042026 -->
		        <script>(function(a,b,c){if(c in b&&b[c]){var d,e=a.location,f=/^(a|html)$/i;a.addEventListener("click",function(a){d=a.target;while(!f.test(d.nodeName))d=d.parentNode;"href"in d&&(d.href.indexOf("http")||~d.href.indexOf(e.host))&&(a.preventDefault(),e.href=d.href)},!1)}})(document,window.navigator,"standalone")</script>
		       
			</head>
			
			<body class="ui-mobile-viewport ui-overlay-c">
			
				<div data-role="page">

					<%@ include file="/WEB-INF/jsp/page/abstractTracktacularPageHeaderMobile.jsp" %>
					
					<%@ include file="/WEB-INF/jsp/page/abstractTracktacularPageContentMobile.jsp" %>
					
					<%@ include file="/WEB-INF/jsp/page/abstractTracktacularPageFooterMobile.jsp" %>
				
				</div>
				
				<%-- Template Data --%>		
				<div id="templateData" data-template-data="${templateData}"></div>
			  
		  		<%-- JavaScript Resources --%>
				<script src="/static/tracktacular/js/page.mobile-${actionBean.pageMobileVersion}.js"></script>
			 	<script src="/static/tracktacular/js/template.mobile-${actionBean.pageMobileVersion}.js"></script>
			 	<s:layout-component name="jsResources" />
				
				<%-- Google Analytics --%>
				<script type="text/javascript">				
					var _gaq = _gaq || [];
					_gaq.push(['_setAccount', 'UA-34098204-1']);
					_gaq.push(['_trackPageview']);				
					(function() {
					  var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
					  ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
					  var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
					})();				
				</script>
				
			</body>
		
		</html>
	
	</c:if>	
	
</s:layout-definition>