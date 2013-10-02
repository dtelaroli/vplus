package br.com.caelum.vraptor.util.collections;

import static com.google.common.base.Predicates.instanceOf;
import static com.google.common.collect.Iterables.any;
import static java.util.Arrays.asList;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import br.com.caelum.vraptor.http.route.Route;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.resource.HttpMethod;
import br.com.caelum.vraptor.resource.ResourceMethod;

import com.google.common.base.Predicate;

public class Filters {
	public static Predicate<Interceptor> accepts(final ResourceMethod method) {
		return new Predicate<Interceptor>() {
			public boolean apply(Interceptor interceptor) {
				return interceptor.accepts(method);
			}
		};
	}

	public static Predicate<Route> canHandle(final Class<?> type,
			final Method method) {
		return new Predicate<Route>() {
			public boolean apply(Route route) {
				return route.canHandle(type, method);
			}
		};
	}

	public static Predicate<Route> canHandle(final String uri) {
		return new Predicate<Route>() {
			public boolean apply(Route route) {
				return route.canHandle(uri);
			}
		};
	}

	public static Predicate<Route> allow(final HttpMethod method) {
		return new Predicate<Route>() {
			public boolean apply(Route route) {
				return route.allowedMethods().contains(method);
			}
		};
	}

	public static Predicate<Annotation[]> hasAnnotation(
			final Class<?> annotation) {
		return new Predicate<Annotation[]>() {
			public boolean apply(Annotation[] param) {
				return any(asList(param), instanceOf(annotation));
			}
		};
	}
}
