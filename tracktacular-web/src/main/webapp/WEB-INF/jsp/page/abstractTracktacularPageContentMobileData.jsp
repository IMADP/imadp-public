<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>

	<j:property name="homeLinkUrl">
		<s:url beanclass="com.tracktacular.web.page.IndexMobileActionBean"/>
	</j:property>
	
	<j:property name="toNonMobileLinkUrl">
		<s:url beanclass="${actionBean.beanClass}">	
			<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
			<s:param name="mobile" value="false"/>
    	</s:url>
	</j:property>
	
	<j:property name="logoutLinkUrl">
		<s:url beanclass="com.tracktacular.web.page.account.LogoutActionBean"/>
	</j:property>
	
	<j:property name="blogUrl">
		<s:url beanclass="com.tracktacular.web.page.BlogActionBean">
			<s:param name="mobile" value="true"/>
		</s:url>
	</j:property>
	
	<j:array name="personalLinks">
		<page:navigationLinkMobile tracker="${'BUCKET'}" />
		<page:navigationLinkMobile tracker="${'BUDGET'}" />
		<page:navigationLinkMobile tracker="${'CALENDAR'}" />
		<page:navigationLinkMobile tracker="${'DREAM'}" />
		<page:navigationLinkMobile tracker="${'GOAL'}" />
		<page:navigationLinkMobile tracker="${'JOURNAL'}" />
		<page:navigationLinkMobile tracker="${'RECIPE'}" />
		<page:navigationLinkMobile tracker="${'TASK'}" />
		<page:navigationLinkMobile tracker="${'WISH'}" />
	</j:array>
	
	<j:array name="peopleLinks">
		<page:navigationLinkMobile tracker="${'BIRTHDAY'}" />
		<page:navigationLinkMobile tracker="${'GIFT'}" />
	</j:array>
	
	<j:array name="healthLinks">
		<page:navigationLinkMobile tracker="${'BODY'}" />
		<page:navigationLinkMobile tracker="${'BLOOD'}" />
		<page:navigationLinkMobile tracker="${'CHOLESTEROL'}" />
		<page:navigationLinkMobile tracker="${'EXERCISE'}" />
	</j:array>
	
	<j:array name="entertainmentLinks">
		<page:navigationLinkMobile tracker="${'BOOK'}" />
		<page:navigationLinkMobile tracker="${'GAME'}" />
		<page:navigationLinkMobile tracker="${'MOVIE'}" />
		<page:navigationLinkMobile tracker="${'MUSIC'}" />
		<page:navigationLinkMobile tracker="${'RESTAURANT'}" />
		<page:navigationLinkMobile tracker="${'TV'}" />
	</j:array>