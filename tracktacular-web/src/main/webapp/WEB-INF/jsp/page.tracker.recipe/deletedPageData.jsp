<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>


	<%-- Deleted Page Data --%>
	<j:property name="deletedRecipesCount" value="${actionBean.itemsPageProvider.page.itemCount}"/>
	
	<j:array var="recipe" name="deletedRecipes" items="${actionBean.itemsPageProvider.page.items}" >
		<j:object>
			<i:include name="recipe" value="${recipe}" page="recipeData.jsp" />
			<j:property name="persistableState" value="ACTIVE"/>
		</j:object>					  
	</j:array>	
	
	
	<%-- All Chapters --%>
	<j:array var="chapter" name="chapters" items="${actionBean.recipeBook.chapters}" >
		<j:object>
			<j:property name="id" value="${chapter.uid}"/>     
			<j:property name="title" value="${chapter.title}"/>
			<j:property name="recipeCount" value="${chapter.recipeCount}"/>
		</j:object>						  
	</j:array>   
	
	
	<%-- Page Navigator --%>
	<page:pageNavigatorData pageProvider="${actionBean.itemsPageProvider}" />