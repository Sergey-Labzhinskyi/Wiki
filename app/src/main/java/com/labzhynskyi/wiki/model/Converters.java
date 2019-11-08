package com.labzhynskyi.wiki.model;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class Converters {



    @TypeConverter
    public static String fromOrigin(Origin origin) {
        Gson gson = new Gson();
        String json = gson.toJson(origin);
        return json;
    }

    @TypeConverter
    public static Origin fromStringOrigin(String value) {
        Type type = new TypeToken<Origin>() {}.getType();
        return new Gson().fromJson(value, type);
    }

    @TypeConverter
    public static String fromListEpisode(List<String> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }

    @TypeConverter
    public static List<String> fromStringEpisode(String value) {
        Type listType = new TypeToken<List<String>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromLocation(Location location) {
        Gson gson = new Gson();
        String json = gson.toJson(location);
        return json;
    }

    @TypeConverter
    public static Location fromStringLocation(String value) {
        Type type = new TypeToken<Location>() {}.getType();
        return new Gson().fromJson(value, type);
    }
}
