<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>

	
	<%-- Recipe Tags Page Data --%>
	<j:property name="recipeTagSelected" value="${actionBean.recipeTagSelected}"/>
	<j:property name="recipeTag" value="${actionBean.recipeTag}"/>
		
	<j:object name="recipeTagCloud">
		<j:property name="tagCloudItemCount" value="${actionBean.tagCloud.tagCloudItemCount}"/>
		   
		<j:array var="tagCloudItem" name="tagCloudItems" items="${actionBean.tagCloud.tagCloudItems}" >
			<j:object>
				<j:property name="nameSlug" value="${tagCloudItem.nameSlug}"/>   
				<j:property name="frequency" value="${tagCloudItem.frequency}"/>   
				<j:property name="weight" value="${tagCloudItem.weight}"/>
				<j:property name="weightInPx" value="${tagCloudItem.weight * 7}"/> 
				 
				<j:property name="selectUrl">
					<s:url beanclass="com.tracktacular.web.page.tracker.recipe.RecipeTagsActionBean">
						<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
						<s:param name="recipeTag" value="${tagCloudItem.nameSlug}"/>
					</s:url>
				</j:property>
				
			</j:object>
		</j:array>	
		
	</j:object>
	
	<c:if test="${actionBean.recipeTagSelected}">	
		<j:property name="recipeTagCount" value="${actionBean.itemsPageProvider.page.itemCount}"/>
		
		<j:array var="chapter" name="allChapters" items="${actionBean.recipeBook.chapters}" >
			<j:object>
				<j:property name="id" value="${chapter.uid}"/>     
				<j:property name="title" value="${chapter.title}"/>
				<j:property name="recipeCount" value="${chapter.recipeCount}"/>
			</j:object>						  
		</j:array>  
		
		<j:array var="recipeTag" name="recipeTags" items="${actionBean.itemsPageProvider.page.items}" >
			<j:object>
				<j:property name="recipeCollapsed" value="true"/>     
				<c:set var="recipe" value="${recipeTag.taggable}" scope="request" />
				<jsp:include page="recipeData.jsp" />
			</j:object>						  
		</j:array>	
		
		
		<%-- Page Navigator --%>
		<page:pageNavigatorData pageProvider="${actionBean.itemsPageProvider}" />
			
	</c:if>
		
	