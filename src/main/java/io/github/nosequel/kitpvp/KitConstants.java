package io.github.nosequel.kitpvp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;

public final class KitConstants {

    public static final Gson GSON = new GsonBuilder()
            .setLongSerializationPolicy(LongSerializationPolicy.STRING)
            .excludeFieldsWithoutExposeAnnotation()
            .setPrettyPrinting()
            .create();

}