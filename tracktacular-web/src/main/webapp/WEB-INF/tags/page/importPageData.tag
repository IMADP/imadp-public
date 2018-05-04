<%@ include file="/WEB-INF/tags/includeTag.tag" %>
	
	<j:array var="property" name="importProperties" items="${actionBean.importProperties}" >
		<j:object>			
			<j:property name="name" value="${property.name}"/>
		</j:object>
	</j:array>
			
	<j:array var="importedItem" name="importedItems" items="${actionBean.importedItems}" >
		<j:object>		
			<j:property name="valid" value="${importedItem.valid}"/>
			<j:array name="messages" items="${importedItem.messages}" />
			
			<j:array var="row" name="rows" items="${actionBean.importProperties}" >
				<j:object>			
					<j:property name="value" value="${importedItem.item[row]}"/>
				</j:object>
			</j:array>
		</j:object>
	</j:array>