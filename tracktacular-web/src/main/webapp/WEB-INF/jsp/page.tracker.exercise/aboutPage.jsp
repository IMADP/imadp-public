<%@ include file="/WEB-INF/jsp/includeJsp.jsp"%>

<s:layout-render
	name="/WEB-INF/jsp/page.tracker/trackerAboutPage.jsp">

	<s:layout-component name="metaDescription">
		Create and manage your exercise routines and workouts. Break workouts into exercises and sets, and track your progress over time.
	</s:layout-component>

	<s:layout-component name="metaKeywords">
		exercise tracker, exercise log, track exercise, track workouts, exercise routines
	</s:layout-component>

	<s:layout-component name="contentBodyTrackerContent">
		The Exercise Tracker logs the progress of your exercise routines. Start by creating a routine which describes your
		exercise regiment. Then after every workout, add the exercises you've done and track the entries by time, weight, repetitions,
		calories, distance, or any combination of them. After you've entered a workout once, you can clone it to save time for your
		next entry. Complete your routines and add new ones as your regiment changes over time.
	</s:layout-component>
	
</s:layout-render>