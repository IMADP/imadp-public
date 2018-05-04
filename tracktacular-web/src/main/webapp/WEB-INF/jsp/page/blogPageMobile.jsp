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
		
		<div class="blog" data-external="true">
		
			<h2>
				${actionBean.blogEntry.title}
			</h2>
			
			<h4>By Anthony DePalma on <i:date value="${actionBean.blogEntry.date}" pattern="MMM d, yyyy" /></h4>
			
			<i:out> 
				${actionBean.blogEntry.content}
			</i:out> 
			
			<ul id="index" class="ui-listview ui-listview-inset ui-corner-all ui-shadow">
			
				<c:forEach var="blogCategory" items="${actionBean.blogCategories.categories}">
					<c:if test="${blogCategory.blogEntryCount > 0}">
						
						<li class="ui-li ui-li-divider ui-bar-b">${blogCategory.name}</li>
						
						<c:forEach var="blogEntry" items="${blogCategory.blogEntries}">
							<li class="ui-btn ui-btn-icon-right ui-li-has-arrow ui-li ui-btn-up-c">
								<div class="ui-btn-inner ui-li">
									<div class="ui-btn-text">
										<s:link beanclass="com.tracktacular.web.page.BlogActionBean" data-external="true" class="ui-link-inherit ${actionBean.blogEntry == blogEntry ? 'active' : ''}">
											<s:param name="eid" value="${blogEntry.eid}"/>
											<s:param name="titleSlug" value="${blogEntry.titleSlug}"/>
											${blogEntry.title}
										</s:link>
									</div>
									<span class="ui-icon ui-icon-arrow-r ui-icon-shadow">&nbsp;</span>
								</div>
							</li>
							
							
						</c:forEach>
						
					</c:if>
				</c:forEach>
				
			</ul>
			
		</div>
		
	</s:layout-component>
	
</s:layout-render>