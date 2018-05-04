
	{@when test="'{type}' == 'DAY'"}
		{@when test="{length} == 1"} {length} day{:else} {length} days{/when}
	{/when}

	{@when test="'{type}' == 'WEEK'"}
		{@when test="{length} == 1"} {length} week{:else} {length} weeks{/when}
	{/when}

	{@when test="'{type}' == 'MONTH'"}
		{@when test="{length} == 1"} {length} month{:else} {length} months{/when}
	{/when}

	{@when test="'{type}' == 'YEAR'"}
		{@when test="{length} == 1"} {length} year{:else} {length} years{/when}
	{/when}