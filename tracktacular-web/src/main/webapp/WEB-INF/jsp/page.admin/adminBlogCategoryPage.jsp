<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>

<s:layout-render 
	name="/WEB-INF/jsp/page/abstractTracktacularPage.jsp"
	title="Tracktacular > Admin > Blog Categories"
	>

	<s:layout-component name="contentHeading">		
		Blog Categories	
	</s:layout-component>
	
	<s:layout-component name="contentBody">	
	
		<h2>Admin Controls</h2>
		
		<s:form style="border: 1px solid black;padding:20px;" class="dialog-form" beanclass="com.tracktacular.web.page.admin.AdminBlogCategoryActionBean">
			
			<b>Add Blog Category</b>
			
			<br/>
			<br/>
			
			<div class="cf">
				<div class="half">
					<s:label for="blogCategory.name" name="Name"/>
					<s:text name="blogCategory.name"/>
				</div>
				<div class="half">
					<s:label for="blogCategory.sort" name="Sort"/>
					<s:text name="blogCategory.sort"/>
				</div>
			</div>
			
			<br/>
			
			<s:submit name="saveBlogCategory" />
			
		</s:form>
		
		<c:forEach var="blogCategory" items="${actionBean.blogCategories.categories}">
			<s:form style="border: 1px solid black;padding:20px;" class="dialog-form" beanclass="com.tracktacular.web.page.admin.AdminBlogCategoryActionBean">
				
				<b>Edit Blog Category</b>
				<s:hidden name="blogCategory" value="${blogCategory.eid}"/>
				
				<br/>
				<br/>
				
				<div class="cf">
					<div class="half">
						<s:label for="blogCategory.name" name="Name"/>
						<s:text name="blogCategory.name" value="${blogCategory.name}"/>
					</div>
					<div class="half">
						<s:label for="blogCategory.sort" name="Sort"/>
						<s:text name="blogCategory.sort" value="${blogCategory.sort}"/>
					</div>
				</div>
				
				<br/>
				
				<s:submit name="saveBlogCategory" value="Save Blog Category" />
				
				<c:if test="${blogCategory.blogEntryCount == 0}">
					<s:submit name="deleteBlogCategory" value="Delete Blog Category" onclick="return confirm('are you sure?')"/>
				</c:if>
				
			</s:form>
		</c:forEach>
	 	
	</s:layout-component>	
	
	<%-- JS Resources --%>	
	<s:layout-component name="jsResources">
		
		<%-- JavaScript CDN Resources --%>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.16/jquery-ui.min.js"></script>			
		
		<%-- JavaScript Fallback Resources --%>
		<script>//<![CDATA[
           	window.jQuery||document.write('<script src="/static/tracktacular/js/lib/jquery-1.7.1.min.js"><\/script>')//]]>
		</script>					
		<script>//<![CDATA[
           	window.jQuery.ui||document.write('<script src="/static/tracktacular/js/lib/jquery.ui-1.8.16.min.js"><\/script>')//]]>
        </script>			
		
		<%-- JavaScript Resources --%>
		<script src="/static/tracktacular/js/page-${actionBean.pageVersion}.js"></script>			 	
	 	<script src="/static/tracktacular/js/template-${actionBean.pageVersion}.js"></script>
	 	
 	 </s:layout-component>
	
</s:layout-render>