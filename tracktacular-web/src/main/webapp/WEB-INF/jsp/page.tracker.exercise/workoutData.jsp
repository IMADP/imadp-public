<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>


	<%-- Workout --%>
	<page:persistableData persistable="${workout}">
		<j:property name="name" value="${workout.name}"/>
		<j:property name="notes" value="${workout.notes}"/>
		<j:property name="workoutCollapsed" value="${workout.collapsed}"/>     
		<j:property name="dateTimeString" value="${workout.dateTimeString}"/>
		<j:property name="exercisesSortable" value="${workout.exercisesSortable}"/>			
		<j:property name="routineId" value="${workout.routine.uid}"/>
				
		<j:object name="newExercise">
			<j:property name="id" value="newExercise-${workout.uid}"/>
			<j:property name="workoutId" value="${workout.uid}"/>
			<j:property name="routineId" value="${workout.routine.uid}"/>
		</j:object>
		
		<j:property name="date">
			<i:date value="${workout.date}" pattern="MMM dd, yyyy - h:mm a" />
		</j:property>
		
		<j:property name="workoutLink">
			<s:url beanclass="com.tracktacular.web.page.tracker.exercise.WorkoutActionBean">
				<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
				<s:param name="workout" value="${workout.uid}"/>
				<s:param name="nameSlug" value="${workout.nameSlug}"/>
			</s:url>
		</j:property>
		
		<%-- Exercises --%>
		<j:array var="exercise" name="exercises" items="${workout.exercises}" >
			<j:object>
				<j:property name="id" value="${exercise.uid}"/>
				<j:property name="name" value="${exercise.name}"/>
				<j:property name="notes" value="${exercise.notes}"/>
				<j:property name="trackCalories" value="${exercise.trackCalories}"/>
				<j:property name="trackDistance" value="${exercise.trackDistance}"/>
				<j:property name="trackDuration" value="${exercise.trackDuration}"/>
				<j:property name="trackRepetitions" value="${exercise.trackRepetitions}"/>
				<j:property name="trackWeight" value="${exercise.trackWeight}"/>
				<j:property name="entriesSortable" value="${exercise.entriesSortable}"/>			
				<j:property name="workoutId" value="${workout.uid}"/>
				<j:property name="routineId" value="${workout.routine.uid}"/>
						
				<j:object name="newEntry">
					<j:property name="id" value="newEntry-${exercise.uid}"/>
					<j:property name="routineId" value="${exercise.workout.routine.uid}"/>
					<j:property name="workoutId" value="${exercise.workout.uid}"/>
					<j:property name="exerciseId" value="${exercise.uid}"/>
					<j:property name="trackCalories" value="${exercise.trackCalories}"/>
					<j:property name="trackDistance" value="${exercise.trackDistance}"/>
					<j:property name="trackDuration" value="${exercise.trackDuration}"/>
					<j:property name="trackRepetitions" value="${exercise.trackRepetitions}"/>
					<j:property name="trackWeight" value="${exercise.trackWeight}"/>
					<j:property name="entriesSortable" value="${exercise.entriesSortable}"/>			
				</j:object>
				
				<%-- Entries --%>
				<j:array var="entry" name="entries" items="${exercise.entries}" >
					<j:object>
						<j:property name="id" value="${entry.uid}"/>
						<j:property name="routineId" value="${exercise.workout.routine.uid}"/>
						<j:property name="workoutId" value="${exercise.workout.uid}"/>
						<j:property name="exerciseId" value="${exercise.uid}"/>
						<j:property name="trackCalories" value="${exercise.trackCalories}"/>
						<j:property name="trackDistance" value="${exercise.trackDistance}"/>
						<j:property name="trackDuration" value="${exercise.trackDuration}"/>
						<j:property name="trackRepetitions" value="${exercise.trackRepetitions}"/>
						<j:property name="trackWeight" value="${exercise.trackWeight}"/>
						<j:property name="entriesSortable" value="${exercise.entriesSortable}"/>			
				
						<c:if test="${exercise.trackRepetitions}">
							<j:property name="repetitions" value="${entry.repetitions}"/>
						</c:if>
						
						<c:if test="${exercise.trackWeight}">
							<j:property name="weight" value="${entry.weight}"/>
						</c:if>
						
						<c:if test="${exercise.trackDuration}">
							<j:property name="hours" value="${entry.hours}"/>
							<j:property name="minutes" value="${entry.minutes}"/>
							<j:property name="seconds" value="${entry.seconds}"/>
						</c:if>
						
						<c:if test="${exercise.trackDistance}">
							<j:property name="distance" value="${entry.distance}"/>
						</c:if>
						
						<c:if test="${exercise.trackCalories}">
							<j:property name="calories" value="${entry.calories}"/>
						</c:if>
						
						<j:property name="time">
							<i:time value="${entry.timeModified}" pattern="h:mm:ss a" />
						</j:property>	
							
					</j:object>
				</j:array>
			</j:object>
		</j:array>
		
	</page:persistableData>
		