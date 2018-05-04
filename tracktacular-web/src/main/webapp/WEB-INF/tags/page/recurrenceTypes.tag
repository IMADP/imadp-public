<%@ include file="/WEB-INF/tags/includeTag.tag" %>

	<j:array name="recurrenceTypes">
		<j:object>
			<j:property name="name" value="Day(s)"/>
			<j:property name="value" value="DAY"/>
		</j:object>
		<j:object>
			<j:property name="name" value="Week(s)"/>
			<j:property name="value" value="WEEK"/>
		</j:object>
		<j:object>
			<j:property name="name" value="Month(s)"/>
			<j:property name="value" value="MONTH"/>
		</j:object>
		<j:object>
			<j:property name="name" value="Year(s)"/>
			<j:property name="value" value="YEAR"/>
		</j:object>
	</j:array>