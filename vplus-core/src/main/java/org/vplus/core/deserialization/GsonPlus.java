package org.vplus.core.deserialization;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.caelum.vraptor.deserialization.Deserializer;
import br.com.caelum.vraptor.deserialization.Deserializes;
import br.com.caelum.vraptor.deserialization.gson.GsonDeserialization;
import br.com.caelum.vraptor.http.ParameterNameProvider;
import br.com.caelum.vraptor.resource.ResourceMethod;
import br.com.caelum.vraptor.view.ResultException;

import com.google.common.base.Objects;
import com.google.common.base.Strings;
import com.google.common.io.CharStreams;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 *
 * @author Renan Reis
 * @author Guilherme Mangabeira
 */

@Deserializes({ "application/json", "json" })
public class GsonPlus implements Deserializer {

	private static final Logger logger = LoggerFactory.getLogger(GsonDeserialization.class);

	private final ParameterNameProvider paramNameProvider;

	private final Collection<JsonDeserializer> adapters;

	private final HttpServletRequest request;

	public GsonPlus(ParameterNameProvider paramNameProvider, List<JsonDeserializer> adapters, HttpServletRequest request) {
		this.paramNameProvider = paramNameProvider;
		this.adapters = adapters;
		this.request = request;
	}

	public Object[] deserialize(InputStream inputStream, ResourceMethod method) {
		Method jMethod = method.getMethod();
		Class<?>[] types = getTypes(method);
		if (types.length == 0) {
			throw new IllegalArgumentException(
					"Methods that consumes representations must receive just one argument");
		}

		Gson gson = getGson();

		Object[] params = new Object[types.length];
		String[] parameterNames = paramNameProvider.parameterNamesFor(jMethod);

		try {
			String content = getContentOfStream(inputStream);
			logger.debug("json retrieved: {}", content);

			JsonParser parser = new JsonParser();
			JsonObject root = (JsonObject) parser.parse(content);

			for (int i = 0; i < types.length; i++) {
				String name = parameterNames[i];
				JsonElement node = root.get(name);
				if (isWithoutRoot(types, node)) {
					params[i] = gson.fromJson(root, types[i]);
					logger.info("json without root deserialized");
				}
				else if(node != null){
					params[i] = gson.fromJson(node, types[i]);
				}
				logger.debug("json deserialized: {}", params[i]);
			}
		} catch (Exception e) {
			throw new ResultException("Unable to deserialize data", e);
		}
		
		return params;
	}

	protected Class<?>[] getTypes(ResourceMethod method) {
		Class<?>[] parameterTypes = method.getMethod().getParameterTypes();
		Type superclass = method.getResource().getType().getGenericSuperclass();
		if(superclass instanceof ParameterizedType) {
			parameterTypes[0] = (Class<?>) getFirstGenericType(superclass);
		}
		return parameterTypes;
	}
	
	private boolean isWithoutRoot(Class<?>[] types, JsonElement node) {
		return node == null && types.length == 1;
	}

	protected Gson getGson() {
		GsonBuilder builder = new GsonBuilder();

		for (JsonDeserializer<?> adapter : adapters) {
			builder.registerTypeHierarchyAdapter(getAdapterType(adapter), adapter);
		}

		return builder.create();
	}

	private Class<?> getAdapterType(JsonDeserializer<?> adapter) {
		Type[] genericInterfaces = adapter.getClass().getGenericInterfaces();
		Type actualType = getFirstGenericType(genericInterfaces[0]);

		return (Class<?>) actualType;
	}
	
	private Type getFirstGenericType(Type type) {
		return getGenericsTypes(type)[0];
	}
	
	private Type[] getGenericsTypes(Type type) {
		ParameterizedType paramType = (ParameterizedType) type;
		return paramType.getActualTypeArguments();
	}

	private String getContentOfStream(InputStream input) throws IOException {
		String charset = getRequestCharset();
		logger.debug("Using charset {}", charset);
		return CharStreams.toString(new InputStreamReader(input, charset));
	}

	private String getRequestCharset() {
		String charset = Objects.firstNonNull(request.getHeader("Accept-Charset"), "UTF-8");
		return charset.split(",")[0];
	}
}