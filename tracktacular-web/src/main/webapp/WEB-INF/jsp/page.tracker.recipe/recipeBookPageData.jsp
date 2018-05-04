<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>


	<%-- Recipe Book Page Data --%>
	<j:property name="chaptersSortable" value="${actionBean.recipeBook.chapterCount > 1}"/>
	<j:property name="recipeCount" value="${actionBean.recipeBook.recipeCount}"/>     
	<j:property name="chapterCount" value="${actionBean.recipeBook.chapterCount}"/>  		
			
	<j:object name="navigationChart">
		<j:property name="chartId" value="all"/>
		<j:property name="chartLabel" value="All"/>
		<j:property name="selected" value="${actionBean.selectedRecipeChapter == 'all'}"/>
		<j:property name="selectedAll" value="true"/>
		<j:property name="selectedContainerClass" value="recipe-chapter-panel"/>
		
		<j:property name="selectUrl">
			<s:url beanclass="com.tracktacular.web.page.tracker.recipe.RecipeBookActionBean" >
				<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>		
			</s:url>
		</j:property>
		
		<j:object name="chartData">
			<j:property name="chapterId" value="all"/>
			
			<j:array var="chapter" name="allChapters" items="${actionBean.recipeBook.chapters}" >
				<j:object>
					<j:property name="recipeCount" value="${chapter.recipeCount}"/>
				</j:object>						  
			</j:array> 
		</j:object>
		
	</j:object> 
	
	<%-- Chapters --%>
	<j:array var="chapter" name="chapters" items="${actionBean.recipeBook.chapters}" >
		<j:object>
			<j:property name="id" value="${chapter.uid}"/>     
			<j:property name="title" value="${chapter.title}"/>
			<j:property name="description" value="${chapter.description}"/>
			<j:property name="chapterCollapsed" value="${chapter.collapsed}"/>
			<j:property name="hidden" value="${actionBean.selectedRecipeChapter != 'all' && actionBean.selectedRecipeChapter != chapter.uid}"/>
			<j:property name="recipeCount" value="${chapter.recipeCount}"/>     
			<j:property name="recipesSortable" value="${chapter.recipeCount > 1}"/>
			<j:property name="recipesCollapsible" value="${chapter.recipeCount > 0}"/>
			<j:property name="deletable" value="${chapter.recipeCount == 0}"/>
				
			<j:object name="newRecipe">
				<j:property name="id" value="${chapter.uid}-newRecipe"/>
				<j:property name="chapter" value="${chapter.uid}"/>
			</j:object>   
			
			<%-- Navigation Chart Data --%>
			<j:object name="navigationChart">
				<j:property name="chartId" value="${chapter.uid}"/>
				<j:property name="chartLabel" value="${chapter.title}"/>
				<j:property name="selected" value="${actionBean.selectedRecipeChapter != 'all' && actionBean.selectedRecipeChapter == chapter.uid}"/>
				<j:property name="selectedTargetId" value="recipe-chapter-${chapter.uid}"/>
				<j:property name="selectedContainerClass" value="recipe-chapter-panel"/>
				
				<j:object name="chartData">
					<j:property name="chapterId" value="${chapter.uid}"/>
					
					<j:array var="chartChapter" name="allChapters" items="${actionBean.recipeBook.chapters}" >
						<j:object>
							<j:property name="chapterId" value="${chartChapter.uid}"/>     
							<j:property name="recipeCount" value="${chartChapter.recipeCount}"/>
						</j:object>						  
					</j:array>
				</j:object>
				
				<j:property name="selectUrl">
					<s:url beanclass="com.tracktacular.web.page.tracker.recipe.RecipeBookActionBean">
						<s:param name="selectedRecipeChapter" value="${chapter.uid}"/>
						<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
					</s:url>
				</j:property>
				
			</j:object> 
			
			<%-- Recipes --%>
			<j:array var="recipe" name="recipes" items="${chapter.recipes}" >
				<j:object>
 					<j:property name="id" value="${recipe.uid}"/>     
					<j:property name="name" value="${recipe.name}"/>     
					<j:property name="notes" value="${recipe.notes}"/>     
					<j:property name="description" value="${recipe.description}"/>     
					<j:property name="ingredients" value="${recipe.ingredients}"/>     
					<j:property name="directions" value="${recipe.directions}"/>     
					<j:property name="favorite" value="${recipe.favorite}"/>     
					<j:property name="recipeTagsAsString" value="${recipe.recipeTagsAsString}"/> 
					<j:property name="chapter" value="${recipe.chapter.uid}"/>
					
					<j:array var="tag" name="tags" items="${recipe.tags}" >
						<j:object>
							<j:property name="name" value="${tag.nameSlug}"/> 
						</j:object>
					</j:array>	
					     
					<j:property name="viewRecipeUrl">
						<s:url beanclass="com.tracktacular.web.page.tracker.recipe.RecipeActionBean">
							<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
							<s:param name="recipe" value="${recipe.uid}"/>
							<s:param name="nameSlug" value="${recipe.nameSlug}"/>
						</s:url>
					</j:property>
					
				</j:object>						  
			</j:array> 
		</j:object>						  
	</j:array>
