<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>

<?xml version="1.0"?> 

<rss version="2.0">

	<channel>
	
		<title>Tracktacular Blog</title>
		<description>Life Logging and Blogging</description>
		<link>https://www.tracktacular.com/blog</link>
		
		<c:forEach var="blogEntry" items="${actionBean.blogEntries}">
			<c:set var="title" value="${blogEntry.title}"/>
			<c:set var="description" value="${blogEntry.description}"/>
			
			<c:set var="link">
				https://www.tracktacular.com<s:url beanclass="com.tracktacular.web.page.BlogActionBean">
					<s:param name="eid" value="${blogEntry.eid}"/>
					<s:param name="titleSlug" value="${blogEntry.titleSlug}"/>
				</s:url>
			</c:set>
			
			<c:set var="pubDate">
				<i:date value="${blogEntry.date}" pattern="EEE, dd MMM yyyy HH:mm:ss zzz" />
			</c:set>
			
			<item>
				<title>${fn:trim(title)}</title>
				<description>${fn:trim(description)}</description>
				<link>${fn:trim(link)}</link>
				<pubDate>${fn:trim(pubDate)}</pubDate>
			</item>
			
		</c:forEach>
	
	</channel>

</rss>