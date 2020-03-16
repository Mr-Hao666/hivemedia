package cn.honghuroad.common.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.*;

/**
 * Created by chenzq
 * Date: 2019/3/1 上午1:08
 **/
public class JsonUtil {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public JsonUtil() {
    }

    public static boolean is(String json) {
        if(json != null && json.length() > 1) {
            try {
                int count = 0;

                for(JsonParser parser = (new ObjectMapper()).getJsonFactory().createJsonParser(json); parser.nextToken() != null && count < 10000; ++count) {
                    ;
                }

                return true;
            } catch (Throwable var3) {
                ;
            }
        }

        return false;
    }

    public static JsonNode read(String json) {
        try {
            return OBJECT_MAPPER.readTree(json);
        } catch (IOException var2) {
            var2.printStackTrace();
            return null;
        }
    }

    public static boolean not(String json) {
        return !is(json);
    }


    public static <T> T toObject(Class<T> clazz, String json) {
        if(clazz != null && StringUtils.isNotEmpty(json)) {
            try {
                return OBJECT_MAPPER.readValue(json, clazz);
            } catch (Throwable var3) {
                var3.printStackTrace();
            }
        }

        return null;
    }

    public static <T> List<T> toList(Class<T> clazz, String json) {
        if(clazz != null && StringUtils.isNotEmpty(json)) {
            try {
                return (List)OBJECT_MAPPER.readValue(json, OBJECT_MAPPER.getTypeFactory().constructCollectionType(ArrayList.class, clazz));
            } catch (Throwable var3) {
                var3.printStackTrace();
            }
        }

        return Collections.emptyList();
    }

    public static <T> Map<String, T> toMap(String json) {
        if(StringUtils.isNotEmpty(json)) {
            try {
                return (Map)OBJECT_MAPPER.readValue(json, Map.class);
            } catch (Throwable var2) {
                var2.printStackTrace();
            }
        }

        return Collections.emptyMap();
    }

    public static String toString(Object obj) {
        if(obj != null) {
            if(obj instanceof String) {
                return (String)obj;
            }

            try {
                return OBJECT_MAPPER.writeValueAsString(obj);
            } catch (Throwable var2) {
                var2.printStackTrace();
            }
        }

        return "";
    }

    static {
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        OBJECT_MAPPER.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        OBJECT_MAPPER.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
    }

    public static class LongAsStringSerializer extends JsonSerializer<Long> {
        public LongAsStringSerializer() {
        }

        public void serialize(Long _long, JsonGenerator jgen, SerializerProvider sp) throws IOException, JsonProcessingException {
            if(_long != null) {
                jgen.writeString(_long.toString());
            }

        }
    }

    public static class BigDecimalSerializer extends JsonSerializer<BigDecimal> {

        @Override
        public void serialize(BigDecimal amount, JsonGenerator generator, SerializerProvider sp) throws IOException {
            BigDecimal value = Optional.of(amount).orElse(BigDecimal.ZERO);
            generator.writeString(value.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());
        }
    }

    public static class BigDecimalTrimmedSerializer extends JsonSerializer<BigDecimal> {

        @Override
        public void serialize(BigDecimal amount, JsonGenerator generator, SerializerProvider sp) throws IOException {
            BigDecimal value = Optional.of(amount).orElse(BigDecimal.ZERO);
            //先转成字符串类型
            String plainString = value.toPlainString();

            if (!StringUtils.contains(plainString, '.')) {
                //不包含小数点，说明是整数，直接返回
                generator.writeString(plainString);
                return;
            }

            //如果包含小数点，截取末尾的0。例如 3.0120截取后变为3.012；3.1200截取后变成3.12
            String trimmedString = removeEnd(plainString, "0");

            if (StringUtils.endsWith(trimmedString, ".")) {
                //说明小数点后都是0，例如8.00
                trimmedString = StringUtils.removeEnd(trimmedString, ".");
                generator.writeString(trimmedString);
            } else {
                generator.writeString(trimmedString);
            }
        }
    }

    private static String removeEnd(String str, String remove) {
        if (StringUtils.endsWith(str, remove)) {
            str = StringUtils.removeEnd(str, remove);
            return removeEnd(str, remove);
        } else {
            return str;
        }
    }

    public static class DiscountRatioSerializer extends JsonSerializer<BigDecimal> {

        @Override
        public void serialize(BigDecimal ratio, JsonGenerator generator, SerializerProvider sp) throws IOException {
            BigDecimal discountRatio = Optional.of(ratio).orElse(BigDecimal.ZERO);
            generator.writeString(discountRatio.setScale(0, BigDecimal.ROUND_DOWN).toPlainString());
        }
    }

    public static final class DateFormatDeserializer extends JsonDeserializer<Date> {
        public DateFormatDeserializer() {
        }

        public Date deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException {
            String value = parser.getText();
            return value != null? TimeUtil.parse(value):null;
        }
    }

    public static final class StringURLDeserializer extends JsonDeserializer<String> {
        public StringURLDeserializer() {
        }

        public String deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException {
            String value = parser.getText();
            return value != null?new String(URLDecoder.decode(value, "UTF-8")):null;
        }
    }

    public static final class StringRawDeserializer extends JsonDeserializer<String> {
        public StringRawDeserializer() {
        }

        public String deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException {
            JsonNode node = parser.readValueAsTree();
            return node != null?(node.isObject()?node.toString():node.asText()):parser.getText();
        }
    }
}
