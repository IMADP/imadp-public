<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>


	<%-- Recipe Data --%>
	<page:persistableData persistable="${recipe}">
		<j:property name="name" value="${recipe.name}"/>     
		<j:property name="notes" value="${recipe.notes}"/>     
		<j:property name="chapterTitle" value="${recipe.chapterTitle}"/>     
		<j:property name="chapter" value="${recipe.chapter.uid}"/>     
		<j:property name="description" value="${recipe.description}"/>     
		<j:property name="ingredients" value="${recipe.ingredients}"/>     
		<j:property name="directions" value="${recipe.directions}"/>     
		<j:property name="favorite" value="${recipe.favorite}"/>     
		<j:property name="recipeTagsAsString" value="${recipe.recipeTagsAsString}"/>
		
		<j:array var="ingredientItem" name="ingredientItems" items="${recipe.ingredientItems}" >
			<j:object>
				<j:property name="name" value="${ingredientItem.name}"/>     
				<j:array name="items" items="${ingredientItem.items}" /> 
			</j:object>
		</j:array>
		
		<j:array var="directionItem" name="directionItems" items="${recipe.directionItems}" >
			<j:object>
				<j:property name="name" value="${directionItem.name}"/>     
				<j:array name="items" items="${directionItem.items}" /> 
			</j:object>
		</j:array>
		
		<j:array var="tag" name="tags" items="${recipe.tags}" >
			<j:object>
				<j:property name="name" value="${tag.nameSlug}"/> 
			</j:object>
		</j:array>
	
	</page:persistableData>