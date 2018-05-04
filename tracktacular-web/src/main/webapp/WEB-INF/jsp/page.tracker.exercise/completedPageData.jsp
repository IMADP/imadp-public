<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>


	<%-- Completed Page Data --%>
	<j:property name="completedRoutinesCount" value="${actionBean.itemsPageProvider.page.itemCount}"/>
	
	<%-- Routine Dtos --%>
	<j:array var="routineDto" name="completedRoutines" items="${actionBean.itemsPageProvider.page.items}" >
		<j:object>
			<i:include name="routineDto" value="${routineDto}" page="routineDtoData.jsp" />
			<j:property name="showStatisticsChart" value="${routineDto.exerciseStatisticsCount > 0}"/>     
	
			<j:object name="statisticsChartData">
				 <j:array name="exerciseStatisticsCollection" var="exerciseStatistics" 
				 	items="${routineDto.exerciseStatisticsCollection}">
				 	
				 	<j:object>
				 		<j:property name="exerciseName" value="${exerciseStatistics.exerciseName}"/> 		
						<j:property name="exerciseCount" value="${exerciseStatistics.exerciseCount}"/> 		
				 		<j:property name="entryCaloriesCount" value="${exerciseStatistics.entryCaloriesCount}"/> 		
				 		<j:property name="entryDistanceCount" value="${exerciseStatistics.entryDistanceCount}"/> 		
				 		<j:property name="entryDurationCount" value="${exerciseStatistics.entryDurationCount}"/> 		
				 		<j:property name="entryRepetitionCount" value="${exerciseStatistics.entryRepetitionCount}"/> 		
				 		<j:property name="entryWeightCount" value="${exerciseStatistics.entryWeightCount}"/>  				 		
				 		
				 		<j:array name="caloriesStatistics" var="statistics" items="${exerciseStatistics.caloriesStatistics}">
				 			<j:object>
					 				<j:property name="workoutDate" value="${statistics.workoutDate}"/> 		
								<j:property name="min" value="${statistics.min}"/> 		
								<j:property name="max" value="${statistics.max}"/> 		
								<j:property name="mean" value="${statistics.mean}"/>	
							</j:object>
				 		</j:array>
				 		
						<j:array name="distanceStatistics" var="statistics" items="${exerciseStatistics.distanceStatistics}">
				 			<j:object>
				 				<j:property name="workoutDate" value="${statistics.workoutDate}"/> 		
								<j:property name="min" value="${statistics.min}"/> 		
								<j:property name="max" value="${statistics.max}"/> 		
								<j:property name="mean" value="${statistics.mean}"/>		
							</j:object>
				 		</j:array>
				 		
						<j:array name="durationStatistics" var="statistics" items="${exerciseStatistics.durationStatistics}">
				 			<j:object>
				 				<j:property name="workoutDate" value="${statistics.workoutDate}"/> 		
								<j:property name="min" value="${statistics.min}"/> 		
								<j:property name="max" value="${statistics.max}"/> 		
								<j:property name="mean" value="${statistics.mean}"/>	
							</j:object>
				 		</j:array>
				 		
						<j:array name="repetitionsStatistics" var="statistics" items="${exerciseStatistics.repetitionsStatistics}">
				 			<j:object>
				 				<j:property name="workoutDate" value="${statistics.workoutDate}"/> 		
								<j:property name="min" value="${statistics.min}"/> 		
								<j:property name="max" value="${statistics.max}"/> 		
								<j:property name="mean" value="${statistics.mean}"/>	
							</j:object>
				 		</j:array>
				 		
						<j:array name="weightStatistics" var="statistics" items="${exerciseStatistics.weightStatistics}">
				 			<j:object>
				 				<j:property name="workoutDate" value="${statistics.workoutDate}"/> 		
								<j:property name="min" value="${statistics.min}"/> 		
								<j:property name="max" value="${statistics.max}"/> 		
								<j:property name="mean" value="${statistics.mean}"/> 	
							</j:object>
				 		</j:array>
				 		
				 	</j:object>
				 </j:array>
			</j:object>
			
		</j:object>		  
	</j:array>  
	
	
	<%-- Page Navigator --%>
	<page:pageNavigatorData pageProvider="${actionBean.itemsPageProvider}" />