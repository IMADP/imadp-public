<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>

<s:layout-render 
	name="/WEB-INF/jsp/page/abstractTracktacularPage.jsp"
	title="Tracktacular > Admin > Blog Entries"
	>

	<s:layout-component name="contentHeading">		
		Blog Entries	
	</s:layout-component>
	
	<s:layout-component name="contentBody">	
	
		<h2>Admin Controls</h2>
		
		<s:form style="border: 1px solid black;padding:20px;" class="dialog-form" beanclass="com.tracktacular.web.page.admin.AdminBlogEntryActionBean">
			
			<b>Add Blog Entry</b>
			
			<br/>
				<br/>
				
				<div class="cf">
					<div class="half">
						<s:label for="blogEntry.category" name="Category"/>
						<s:select name="blogEntry.category">
							<s:options-collection collection="${actionBean.blogCategories.categories}" value="eid" label="name"/>
						</s:select>
					</div>
					<div class="half">
						<s:label for="blogEntry.sort" name="Sort"/>
						<s:text name="blogEntry.sort"/>
					</div>
				</div>
				
				<s:label for="blogEntry.title" name="Title"/>
				<s:text name="blogEntry.title"/>
		
				<br/>
				<br/>
				
				<s:label for="blogEntry.description" name="Description"/>
				<s:text name="blogEntry.description"/>
		
				<br/>
				<br/>
				
				<s:label for="blogEntry.keywords" name="Keywords"/>
				<s:text name="blogEntry.keywords"/>
		
				<br/>
				<br/>
				
				<s:label for="blogEntry.content" name="Content"/>
				<i:out><s:textarea rows="10" name="blogEntry.content"/></i:out>
				
				<br/>
				<br/>
				
				<s:submit name="saveBlogEntry" value="Save Blog Entry" />
			
		</s:form>
		
		<c:forEach var="blogEntry" items="${actionBean.blogCategories.blogEntries}">
			<s:form style="border: 1px solid black;padding:20px;" class="dialog-form" beanclass="com.tracktacular.web.page.admin.AdminBlogEntryActionBean">
				
				<b>Edit Blog Entry</b>
				<s:hidden name="blogEntry" value="${blogEntry.eid}"/>
				
				<br/>
				<br/>
				
				<div class="cf">
					<div class="half">
						<s:label for="blogEntry.category" name="Category"/>
						<s:select name="blogEntry.category" value="${blogEntry.category.eid}">
							<s:options-collection collection="${actionBean.blogCategories.categories}" value="eid" label="name"/>
						</s:select>
					</div>
					<div class="half">
						<s:label for="blogEntry.sort" name="Sort"/>
						<s:text name="blogEntry.sort" value="${blogEntry.sort}"/>
					</div>
				</div>
				
				<s:label for="blogEntry.title" name="Title"/>
				<s:text name="blogEntry.title" value="${blogEntry.title}"/>
		
				<br/>
				<br/>
				
				<s:label for="blogEntry.description" name="Description"/>
				<s:text name="blogEntry.description" value="${blogEntry.description}"/>
		
				<br/>
				<br/>
				
				<s:label for="blogEntry.keywords" name="Keywords"/>
				<s:text name="blogEntry.keywords" value="${blogEntry.keywords}"/>
		
				<br/>
				<br/>
				
				<s:label for="blogEntry.content" name="Content"/>
				<i:out><s:textarea rows="10" name="blogEntry.content" value="${blogEntry.content}"/></i:out>
				
				<br/>
				<br/>
				
				<s:submit name="saveBlogEntry" value="Save Blog Entry" />
				<s:submit name="sendBlogEntryNotification" value="Send Blog Entry Notification" onclick="return confirm('are you sure?')"/>
				<s:submit name="deleteBlogEntry" value="Delete Blog Entry" onclick="return confirm('are you sure?')"/>
				
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