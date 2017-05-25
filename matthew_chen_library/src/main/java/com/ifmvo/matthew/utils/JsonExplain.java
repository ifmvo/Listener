package com.ifmvo.matthew.utils;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.ifmvo.matthew.base.bean.BaseBean;
import com.ifmvo.matthew.utils.print.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonExplain {

    public class StringConverter implements JsonSerializer<String>,
            JsonDeserializer<String> {
        public JsonElement serialize(String src, Type typeOfSrc,
                                     JsonSerializationContext context) {
            if (src == null) {
                return new JsonPrimitive("");
            } else {
                return new JsonPrimitive(src.toString());
            }
        }

        public String deserialize(JsonElement json, Type typeOfT,
                                  JsonDeserializationContext context)
                throws JsonParseException {
            return json.getAsJsonPrimitive().getAsString();
        }
    }

    public static <T> T[] explainArrayJson(String josnData, Class<T[]> clazz) {
        try {
            T[] arr = getGson().fromJson(josnData, clazz);
            return arr;
        } catch (Exception e) {
            return null;
        }
    }

    public static <T> List<T> explainListJson(String josnData, Class<? extends BaseBean[]> clazz) {
        try {
            BaseBean[] arr = getGson().fromJson(josnData, clazz);
            return (List<T>) Arrays.asList(arr);
        } catch (Exception e) {
            Logger.e(e.toString());
            return new ArrayList<T>();
        }
        // or return Arrays.asList(new
        // Gson().fromJson(s, clazz)); for a
        // one-liner
    }

    public static <T> T explainJson(String jsonData, Class<T> c) {
        try {
            Gson gson = getGson();
            T obj = gson.fromJson(jsonData, c);
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Gson getGson() {
//        GsonBuilder gb = new GsonBuilder();
//        gb.registerTypeAdapter(String.class, new StringConverter());
//        Gson gson = gb.create();
        return new Gson();
    }


    /**
     * JSon数据解析
     *
     * @param
     * @param key
     * @return
     */
    public static String getStringValue(String response, String key) {

        try {
            if (StringUtil.isEmpty(response)) {
                return "";
            }
            JSONObject ob = new JSONObject(response);
            String value = ob.getString(key);
            if ("null".equals(value)) {
                value = "";
            }
            return value;
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            Logger.e(e.toString());
            return "";
        }
    }

    /**
     * JSon数据解析
     *
     * @param
     * @param key
     * @return
     */
    public static Integer getIntValue(String response, String key) {

        try {
            JSONObject ob = new JSONObject(response);
            int value = ob.getInt(key);
            return value;
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            Logger.e(e.toString());
            return -1;
        }
    }

    /**
     * JSon数据解析
     *
     * @param
     * @param key
     * @return
     */
    public static Long getLongValue(String response, String key) {

        try {
            JSONObject ob = new JSONObject(response);
            return ob.getLong(key);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            Logger.e(e.toString());
            return -1l;
        }
    }

    /**
     * JSon数据解析
     *
     * @param
     * @param key
     * @return
     */
    public static Double getDoubleValue(String response, String key) {

        try {
            JSONObject ob = new JSONObject(response);
            double value = ob.getDouble(key);
            return value;
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            Logger.e(e.toString());
            return -1d;
        }
    }

    /**
     * 把对象转json
     *
     * @param object
     * @return
     */
    public static String toJson(Object object) {
        if (object == null) return "";
        Gson gson = new Gson();
        return gson.toJson(object);
    }

}
