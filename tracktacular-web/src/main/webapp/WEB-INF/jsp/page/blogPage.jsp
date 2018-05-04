<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>

<i:out><c:set var="title" value="Tracktacular > Blog > ${actionBean.blogEntry.title}" /></i:out>

<s:layout-render 
	name="/WEB-INF/jsp/page/abstractTracktacularPage.jsp"
	title="${title}"
	>

	<s:layout-component name="contentHeading">		
		Blog
	</s:layout-component>
	
	<s:layout-component name="metaDescription">
		${actionBean.blogEntry.description}
	</s:layout-component>

	<s:layout-component name="metaKeywords" >
		${actionBean.blogEntry.keywords}
	</s:layout-component>
		
	<s:layout-component name="contentBody">		
		
		<div class="blog">
		
			<nav class="top">
				
				<c:forEach var="blogCategory" items="${actionBean.blogCategories.categories}">
					<c:if test="${blogCategory.blogEntryCount > 0}">
						
						<header>
							${blogCategory.name}
						</header>
						
						<ul>
							<c:forEach var="blogEntry" items="${blogCategory.blogEntries}">
								<li>
									<s:link beanclass="com.tracktacular.web.page.BlogActionBean" class="${actionBean.blogEntry == blogEntry ? 'active' : ''}">
										<s:param name="eid" value="${blogEntry.eid}"/>
										<s:param name="titleSlug" value="${blogEntry.titleSlug}"/>
										
										<c:if test="${actionBean.blogCategories.recentBlogEntry == blogEntry}">
											<span class="icon-s s-new"></span>
										</c:if>
										
										${blogEntry.title}
									</s:link>
								</li>
							</c:forEach>
						</ul>
					</c:if>
				</c:forEach>
				
			</nav>
			
			<h2>
				${actionBean.blogEntry.title}
			</h2>
			
			<br/>
			
			<h4>By Anthony DePalma on <i:date value="${actionBean.blogEntry.date}" pattern="MMMM d, yyyy" /></h4>
			
			<i:out> 
				${actionBean.blogEntry.content}
			</i:out> 
			
			<div class="cf post-content">
				
				<div class="left center">
					<img src="/static/tracktacular/img/page/logo-t.png"/>
					<s:link beanclass="com.tracktacular.web.page.account.JoinActionBean">
						<img src="/static/tracktacular/img/page/try.png"/>
					</s:link>	
				</div>
				
				<div class="right">
				
					<c:set var="blogUrl">
						https://www.tracktacular.com<s:url beanclass="com.tracktacular.web.page.BlogActionBean">
							<s:param name="eid" value="${actionBean.blogEntry.eid}"/>
							<s:param name="titleSlug" value="${actionBean.blogEntry.titleSlug}"/>
						</s:url>
					</c:set>
									
					<div class="social">
						<div class="inline-block">
							<a class="icon-l l-social-facebook"
							href="#"
							onclick="
							    window.open(
							      'https://www.facebook.com/sharer/sharer.php?u=${blogUrl}', 
							      'facebook-share-dialog', 
							      'width=626,height=436'); 
						   		return false;"></a>
						</div>
						<div class="inline-block">
							<a class="icon-l l-social-twitter" target="_blank"
								href="http://twitter.com/share?url=${blogUrl}&text=Check out this article '${actionBean.blogEntry.title}' via @Tracktacular"></a>
						</div>
						<div class="inline-block">
							<a class="icon-l l-social-stumble" target="_blank"
								href="http://www.stumbleupon.com/submit?url=${blogUrl}&title=${actionBean.blogEntry.title}"></a>
						</div>
						<div class="inline-block">
							<a class="icon-l l-social-google" target="_blank"
								href="https://plusone.google.com/_/+1/confirm?hl=en&url=${blogUrl}"></a>
						</div>
						<div class="inline-block">
							<s:link beanclass="com.tracktacular.web.page.BlogFeedActionBean" class="icon-l l-social-rss"><span class="none"></span></s:link>
						</div>
						<div class="inline-block">
							<a class="icon-l l-social-email" target="_blank"
							   href="mailto:?subject=Check out this article: ${actionBean.blogEntry.title}&body=${actionBean.blogEntry.description}%0D%0A %0D%0A ${blogUrl}" ></a>
						</div>
					</div>	
					
					<nav class="bottom">
					
						<header class="center">
							Recent Entries
						</header>
						
						<ul class="recent-links">
							<c:forEach var="blogEntry" items="${actionBean.recentBlogEntries}">
								<li>
									<span><i:date value="${blogEntry.date}" pattern="MM/dd" /> - </span>
									<s:link beanclass="com.tracktacular.web.page.BlogActionBean" class="${actionBean.blogEntry == blogEntry ? 'active' : ''}">
										<s:param name="eid" value="${blogEntry.eid}"/>
										<s:param name="titleSlug" value="${blogEntry.titleSlug}"/>
										${blogEntry.title}
									</s:link>
								</li>
							</c:forEach>
						</ul>
						
					</nav>
					
					<div class="subscribe">
						<s:link beanclass="com.tracktacular.web.page.account.JoinActionBean">Subscribe</s:link> for free email notifications
					</div>
					
				</div>
					
			</div>	
			
		</div>
		
		<div class="disqus">
		    <div id="disqus_thread"></div>
		    <script type="text/javascript">
		        var disqus_shortname = 'tracktacular';
		        (function() {
		            var dsq = document.createElement('script'); dsq.type = 'text/javascript'; dsq.async = true;
		            dsq.src = '//' + disqus_shortname + '.disqus.com/embed.js';
		            (document.getElementsByTagName('head')[0] || document.getElementsByTagName('body')[0]).appendChild(dsq);
		        })();
		    </script>
		</div>
		
	</s:layout-component>
	
</s:layout-render>