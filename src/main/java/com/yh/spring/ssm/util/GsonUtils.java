package com.yh.spring.ssm.util;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

/**
 * 用GSON解析Json数组
 */
public class GsonUtils {


    // 将Json数据解析成相应的映射对象
    public static <T> T parseJsonWithGson(String jsonData, Class<T> type) {
        Gson gson = new Gson();
        T result = gson.fromJson(jsonData, type);
        return result;
    }

    // 将Json数组解析成相应的映射对象列表
    public static <T> List<T> parseJsonArrayWithGson1(String jsonData,
            Class<T> type) {
        Gson gson = new Gson();
        List<T> result = gson.fromJson(jsonData, new TypeToken<List<T>>() {
        }.getType());
        return result;
    }
    
    public static <T> List<T> parseJsonArrayWithGson(String message, String jsonHead,Class<T> cls){ //这里是Class<T>
        JsonObject jsonObject = new JsonParser().parse(message).getAsJsonObject();
        JsonArray jsonArray = jsonObject.getAsJsonArray(jsonHead);

        Gson gson = new Gson();
        List<T> list = new ArrayList<>();
        for (JsonElement jsonElement : jsonArray) {
            list.add(gson.fromJson(jsonElement,cls)); //cls
        }
        return list;
    }

}


