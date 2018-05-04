<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>

	
	<%-- Report --%>		
	<page:trackerReport>	
		<j:property name="recipeCount" value="${trackerReport.recipeBook.recipeCount}"/>
		<j:property name="chapterCount" value="${trackerReport.recipeBook.chapterCount}"/>
		
		<j:object name="navigationChart">
			<j:property name="chartId" value="all"/>
			<j:property name="chartLabel" value="All"/>
			<j:property name="externalLink" value="true"/>
			
			<j:property name="selectUrl">
				<s:url beanclass="com.tracktacular.web.page.tracker.recipe.RecipeBookActionBean" >
					<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>		
				</s:url>
			</j:property>
			
			<j:object name="chartData">
				<j:property name="chapterId" value="all"/>
				
				<j:array var="chapter" name="allChapters" items="${trackerReport.recipeBook.chapters}" >
					<j:object>
						<j:property name="recipeCount" value="${chapter.recipeCount}"/>
					</j:object>						  
				</j:array> 
			</j:object>
			
		</j:object>
		
		<%-- Chapters --%>
		<j:array var="chapter" name="chapters" items="${trackerReport.recipeBook.chapters}" >
			<j:object>
			
				<%-- Navigation Chart Data --%>
				<j:object name="navigationChart">
					<j:property name="chartId" value="${chapter.uid}"/>
					<j:property name="chartLabel" value="${chapter.title}"/>
					<j:property name="externalLink" value="true"/>
					
					<j:object name="chartData">
						<j:property name="chapterId" value="${chapter.uid}"/>
						
						<j:array var="chartChapter" name="allChapters" items="${trackerReport.recipeBook.chapters}" >
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
				
			</j:object>						  
		</j:array>   
	</page:trackerReport>
	
	