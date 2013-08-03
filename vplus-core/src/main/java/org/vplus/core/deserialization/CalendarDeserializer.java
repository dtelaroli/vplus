package org.vplus.core.deserialization;

import java.lang.reflect.Type;
import java.util.Calendar;

import br.com.caelum.vraptor.ioc.Component;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

@Component
public class CalendarDeserializer implements JsonDeserializer<Calendar> {

	public CalendarDeserializer() {
	}

	public Calendar deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {

		JsonObject root = (JsonObject) new JsonParser().parse(json.toString());
		JsonElement jsonElement = root.get("time");
		long millis = jsonElement.getAsLong();

		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar;

	}

}
