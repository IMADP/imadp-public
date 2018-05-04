package com.imadp.service.metrics;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.imadp.core.CoreComponent;

/**
 * MetricsAspect
 *
 * Aspect to collect metrics data.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@Aspect
public class MetricsAspect extends CoreComponent implements ApplicationListener<ContextRefreshedEvent> {

	// metrics service
	private MetricsService metricsService;

	// initialization flag
	private boolean initialized;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		initialized = true;
	}

	/**
	 * Collects the duration of a method execution and adds a metric to the metricsService.
	 *
	 * @param joinPoint
	 * @return Object
	 * @throws Throwable
	 */
	@Around("execution(public * *(..)) && this(com.imadp.service.FacadeComponent)")
	public Object collectMetric(ProceedingJoinPoint joinPoint) throws Throwable {

		// only measure after the context has been initialized
		if(!initialized)
			return joinPoint.proceed();

		String className = joinPoint.getTarget().getClass().getSimpleName();
		String methodName = joinPoint.getSignature().getName();

		// measure duration
		long startTime = System.nanoTime();
		Object object = joinPoint.proceed();
		long duration = System.nanoTime() - startTime;

		// collect metric
		String name = className + "." + methodName;
		metricsService.addMetric(name, duration);
		return object;
	}

	// getters and setters
	public MetricsService getMetricsService() {
		return metricsService;
	}

	public void setMetricsService(MetricsService metricsService) {
		this.metricsService = metricsService;
	}

}
