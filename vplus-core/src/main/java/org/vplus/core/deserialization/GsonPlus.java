package org.vplus.core.deserialization;

import java.io.InputStream;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import br.com.caelum.vraptor.deserialization.Deserializer;
import br.com.caelum.vraptor.deserialization.Deserializes;
import br.com.caelum.vraptor.deserialization.gson.GsonDeserialization;
import br.com.caelum.vraptor.http.ParameterNameProvider;
import br.com.caelum.vraptor.resource.ResourceMethod;

import com.google.gson.JsonDeserializer;

@Deserializes({ "application/json", "json" })
public class GsonPlus implements Deserializer {

	private GsonDeserialization gson;

	@SuppressWarnings("rawtypes")
	public GsonPlus(ParameterNameProvider paramNameProvider,
			HttpServletRequest request) {
		gson = new GsonDeserialization(paramNameProvider, new ArrayList<JsonDeserializer>(), request);
	}

	@Override
	public Object[] deserialize(InputStream inputStream, ResourceMethod method) {
		return gson.deserialize(inputStream, method);
	}

}