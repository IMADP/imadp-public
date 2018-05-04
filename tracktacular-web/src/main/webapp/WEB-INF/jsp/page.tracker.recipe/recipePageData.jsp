<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>


	<%-- Recipe Page Data --%>
	<j:array var="chapter" name="chapters" items="${actionBean.recipeBook.chapters}" >
		<j:object>
			<j:property name="id" value="${chapter.uid}"/>     
			<j:property name="title" value="${chapter.title}"/>
			<j:property name="recipeCount" value="${chapter.recipeCount}"/>
		</j:object>						  
	</j:array>   
	
	<j:property name="backUrl">
		<s:url beanclass="com.tracktacular.web.page.tracker.recipe.RecipeBookActionBean">
			<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
		</s:url>
	</j:property>
	
	
	<%-- Recipe --%>
	<j:object name="recipe">
		<i:include name="recipe" value="${actionBean.recipe}" page="recipeData.jsp" />
	</j:object>	
