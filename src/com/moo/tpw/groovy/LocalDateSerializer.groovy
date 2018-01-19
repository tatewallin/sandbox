package com.moo.tpw.groovy

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import org.joda.time.LocalDate
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter

import java.lang.reflect.Type

class LocalDateSerializer implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {

    private static final String PATTERN = "yyyy-MM-dd";
    final DateTimeFormatter fmt = DateTimeFormat.forPattern(PATTERN);


    @Override
    public JsonElement serialize(LocalDate src, Type typeOfSrc, JsonSerializationContext context) {
        String retVal = fmt.print(src);
        return new JsonPrimitive(retVal);
    }


    @Override
    public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        return fmt.parseLocalDate(json.toString().replace("\"", ""));
    }
}
