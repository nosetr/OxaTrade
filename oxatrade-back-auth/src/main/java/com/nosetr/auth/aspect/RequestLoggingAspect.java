package com.nosetr.auth.aspect;

import java.util.Date;
import java.util.function.Consumer;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import com.nosetr.auth.config.WebFilterConfig;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Signal;
import reactor.util.context.ContextView;

/**
 * The RequestLoggingAspect stands out for its adept handling of diverse return
 * types, including Flux, Mono, and non-Webflux, within a Spring Webflux
 * framework.
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.3
 * @see   https://medium.com/@dursunkoc/logging-incoming-requests-in-spring-webflux-46d9b105a5e7
 * @see   https://github.com/IliaIliukovich/simpleSpringBootProject/blob/master/src/main/java/com/telran/simplespringbootproject/aspect/CountryServiceAspect.java
 */
@Slf4j
@Aspect
@Component
public class RequestLoggingAspect {

	private static String TRX_ID = WebFilterConfig.ContextKey.TRX_ID.name();
	private static String PATH_URI = WebFilterConfig.ContextKey.PATH_URI.name();

	/**
	 * Method as {@code @Pointcut} for controllers execution.
	 * Can be removed, if we have only one.
	 * 
	 * @autor Nikolay Osetrov
	 * @since 0.1.3
	 */
	@Pointcut("execution (* com.nosetr.auth.controller.*Controller*.*(..))")
	public void controllerMethods() {}
	
	@Around("controllerMethods()")
  public Object specialTransaction(ProceedingJoinPoint joinPoint) {
      System.out.println("+++++++++++++++++++++Special Transaction start+++++++++++++++++++++");
//      boolean condition = false;
      boolean condition = true;
      try {
          if (condition) {
              Object methodResult = joinPoint.proceed();
              return methodResult;
          } else {
              System.out.println("+++++++++++++++++++++Alternative logic+++++++++++++++++++++");
          }
      } catch (Throwable e) {
          System.out.println("+++++++++++++++++++++Special logic for exceptions+++++++++++++++++++++");
          System.out.println("+++++++++++++++++++++Special Transaction rollback+++++++++++++++++++++");
      } finally {
          System.out.println("+++++++++++++++++++++Special Transaction end+++++++++++++++++++++");
      }
      return null;
  }

//	/**
//	 * Assuming that all the endpoints in our project are located within a package
//	 * named “controller” and that Controller classes end with the term
//	 * “Controller*”, we can craft an advice method as depicted below:
//	 * 
//	 * @autor            Nikolay Osetrov
//	 * @since            0.1.3
//	 * @param  joinPoint
//	 * @return
//	 * @throws Throwable
//	 */
//	//	@Around("execution (* com.nosetr.auth.controller.*..*.*Controller*.*(..))")
//	@Around("controllerMethods()")
//	public Object logInOut(ProceedingJoinPoint joinPoint) throws Throwable {
//		Class<?> clazz = joinPoint.getTarget()
//				.getClass();
//		Logger logger = LoggerFactory.getLogger(clazz);
//
//		Date start = new Date();
//		Object result = null;
//		Throwable exception = null;
//		try {
//			result = joinPoint.proceed();
//			if (result instanceof Mono<?> monoOut) {
//				return logMonoResult(joinPoint, clazz, logger, start, monoOut);
//			} else if (result instanceof Flux<?> fluxOut) {
//				return logFluxResult(joinPoint, clazz, logger, start, fluxOut);
//			} else { // non-Webflux
//				return result;
//			}
//		} catch (Throwable e) {
//			exception = e;
//			throw e;
//		} finally {
//			if (!(result instanceof Mono<?>) && !(result instanceof Flux<?>)) {
//				doOutputLogging(joinPoint, clazz, logger, start, result, exception);
//			}
//		}
//	}

	/**
	 * logMonoResult method, which efficiently logs with contextView to retrieve
	 * contextual data from the Webflux environment. This method adeptly handles
	 * Mono return types, capturing various scenarios while maintaining a structured
	 * logging approach. It gracefully integrates deferred contextual information
	 * and ensures seamless logging of different outcomes. From handling empty
	 * results to tracking successes and errors, the logMonoResult method seamlessly
	 * facilitates detailed logging within the Spring Webflux context:
	 * 
	 * @autor            Nikolay Osetrov
	 * @since            0.1.3
	 * @param  <T>
	 * @param  <L>
	 * @param  joinPoint
	 * @param  clazz
	 * @param  logger
	 * @param  start
	 * @param  monoOut
	 * @return           Mono<T>
	 */
	private <T, L> Mono<T> logMonoResult(
			ProceedingJoinPoint joinPoint, Class<L> clazz, Logger logger, Date start, Mono<T> monoOut
	) {
		return Mono.deferContextual(
				contextView -> monoOut
						.switchIfEmpty(
								Mono.<T>empty()
										.doOnSuccess(
												logOnEmptyConsumer(
														contextView, () -> doOutputLogging(joinPoint, clazz, logger, start, "[empty]", null)
												)
										)
						)
						.doOnEach(logOnNext(v -> doOutputLogging(joinPoint, clazz, logger, start, v, null)))
						.doOnEach(logOnError(e -> doOutputLogging(joinPoint, clazz, logger, start, null, e)))
						.doOnCancel(
								logOnEmptyRunnable(
										contextView, () -> doOutputLogging(joinPoint, clazz, logger, start, "[cancelled]", null)
								)
						)
		);
	}

	/**
	 * This method is orchestrating comprehensive logging while seamlessly
	 * incorporating the contextView to obtain contextual information from the
	 * Webflux environment. By accommodating diverse scenarios, such as empty
	 * results or cancellations, the logFluxResult method optimizes logging within
	 * the Spring Webflux ecosystem:
	 * 
	 * @autor            Nikolay Osetrov
	 * @since            0.1.3
	 * @param  <T>
	 * @param  joinPoint
	 * @param  clazz
	 * @param  logger
	 * @param  start
	 * @param  fluxOut
	 * @return           Flux<T>
	 */
	private <T> Flux<T> logFluxResult(
			ProceedingJoinPoint joinPoint, Class<?> clazz, Logger logger, Date start, Flux<T> fluxOut
	) {
		return Flux.deferContextual(
				contextView -> fluxOut
						.switchIfEmpty(
								Flux.<T>empty()
										.doOnComplete(
												logOnEmptyRunnable(
														contextView, () -> doOutputLogging(joinPoint, clazz, logger, start, "[empty]", null)
												)
										)
						)
						.doOnEach(logOnNext(v -> doOutputLogging(joinPoint, clazz, logger, start, v, null)))
						.doOnEach(logOnError(e -> doOutputLogging(joinPoint, clazz, logger, start, null, e)))
						.doOnCancel(
								logOnEmptyRunnable(
										contextView, () -> doOutputLogging(joinPoint, clazz, logger, start, "[cancelled]", null)
								)
						)
		);
	}

	/**
	 * The logOnNext method is designed to log information when a signal indicates a
	 * successful next event. It uses the signal's contextView to extract contextual
	 * variables such as transaction ID (TRX_ID) and path URI (PATH_URI).
	 * 
	 * @autor               Nikolay Osetrov
	 * @since               0.1.3
	 * @param  <T>
	 * @param  logStatement
	 * @return
	 */
	private static <T> Consumer<Signal<T>> logOnNext(Consumer<T> logStatement) {
		return signal -> {
			if (!signal.isOnNext()) return;

			String trxIdVar = signal.getContextView()
					.getOrDefault(TRX_ID, "");
			String pathUriVar = signal.getContextView()
					.getOrDefault(PATH_URI, "");
			try (
					MDC.MDCCloseable trx = MDC.putCloseable(TRX_ID, trxIdVar);
					MDC.MDCCloseable path = MDC.putCloseable(PATH_URI, pathUriVar)
			) {
				T t = signal.get();
				logStatement.accept(t);
			}
		};
	}

	/**
	 * The logOnError method mirrors the behavior of logOnNext, but it focuses on
	 * error events. It extracts the contextual variables from the signal's
	 * contextView and places them in the MDC. This ensures that errors are logged
	 * in the proper context, making it easier to identify the specific transaction
	 * and path associated with the error event. By encapsulating the error log
	 * statement within the MDC, this method ensures that error logs are informative
	 * and appropriately contextualized.
	 * 
	 * @autor                    Nikolay Osetrov
	 * @since                    0.1.3
	 * @param  <T>
	 * @param  errorLogStatement
	 * @return
	 */
	public static <T> Consumer<Signal<T>> logOnError(Consumer<Throwable> errorLogStatement) {
		return signal -> {
			if (!signal.isOnError()) return;
			String trxIdVar = signal.getContextView()
					.getOrDefault(TRX_ID, "");
			String pathUriVar = signal.getContextView()
					.getOrDefault(PATH_URI, "");
			try (
					MDC.MDCCloseable trx = MDC.putCloseable(TRX_ID, trxIdVar);
					MDC.MDCCloseable path = MDC.putCloseable(PATH_URI, pathUriVar)
			) {
				errorLogStatement.accept(signal.getThrowable());
			}
		};
	}

	/**
	 * {@code logOnEmptyConsumer} and {@code logOnEmptyRunnable} Methods: Both of
	 * these methods deal with scenarios where the signal is empty, indicating that
	 * there's no result to process.
	 * <p>The {@code logOnEmptyConsumer} method is designed to accept a Consumer and
	 * executes it when the signal is empty. It retrieves the contextual variables
	 * from the provided contextView and incorporates them into the MDC before
	 * executing the log statement.
	 * 
	 * @autor               Nikolay Osetrov
	 * @since               0.1.3
	 * @param  <T>
	 * @param  contextView
	 * @param  logStatement
	 * @return              Consumer<T>
	 */
	private static <T> Consumer<T> logOnEmptyConsumer(final ContextView contextView, Runnable logStatement) {
		return signal -> {
			if (signal != null) return;
			String trxIdVar = contextView.getOrDefault(TRX_ID, "");
			String pathUriVar = contextView.getOrDefault(PATH_URI, "");
			try (
					MDC.MDCCloseable trx = MDC.putCloseable(TRX_ID, trxIdVar);
					MDC.MDCCloseable path = MDC.putCloseable(PATH_URI, pathUriVar)
			) {
				logStatement.run();
			}
		};
	}

	/**
	 * Like a {@code logOnEmptyConsumer}
	 * 
	 * @autor               Nikolay Osetrov
	 * @since               0.1.3
	 * @param  contextView
	 * @param  logStatement
	 * @return
	 */
	private static Runnable logOnEmptyRunnable(final ContextView contextView, Runnable logStatement) {
		return () -> {
			String trxIdVar = contextView.getOrDefault(TRX_ID, "");
			String pathUriVar = contextView.getOrDefault(PATH_URI, "");
			try (
					MDC.MDCCloseable trx = MDC.putCloseable(TRX_ID, trxIdVar);
					MDC.MDCCloseable path = MDC.putCloseable(PATH_URI, pathUriVar)
			) {
				logStatement.run();
			}
		};
	}

	/**
	 * {@code doOutputLogging} serves as a conduit for logging incoming expressions,
	 * either via a tailored logger to match your scenario or potentially routed to
	 * a database or alternate platform. This method can be customized to align
	 * precisely with your distinct necessities and specifications.
	 * 
	 * @autor           Nikolay Osetrov
	 * @since           0.1.3
	 * @param <T>
	 * @param joinPoint
	 * @param clazz
	 * @param logger
	 * @param start
	 * @param result
	 * @param exception
	 */
	private <T> void doOutputLogging(
			final ProceedingJoinPoint joinPoint, final Class<?> clazz, final Logger logger,
			final Date start, final T result, final Throwable exception
	) {
		if (exception != null) {
			log.error(
					"Error occurred in method {} of class {}: {}", joinPoint.getSignature(), clazz.getSimpleName(), exception
							.getMessage(), exception
			);
		}
		// Logging additional information such as 'start', 'result', etc., if necessary.
		//db.insert(...);
	}
}
