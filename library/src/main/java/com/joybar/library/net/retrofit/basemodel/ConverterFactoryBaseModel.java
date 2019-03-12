package com.joybar.library.net.retrofit.basemodel;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by joybar on 5/23/16.
 */
public class ConverterFactoryBaseModel extends Converter.Factory {

    private Class<?> mJsonElement;

    public ConverterFactoryBaseModel(Gson gson, Class<?> jsonElement) {
        if (gson == null) throw new NullPointerException("gson == null");
        this.mJsonElement = jsonElement;
        this.gson = gson;
    }

    /**
     * Create an instance using a default {@link Gson} instance for conversion. Encoding to JSON and
     * decoding from JSON (when no charset is specified by a header) will use UTF-8.
     */
    public static ConverterFactoryBaseModel create(Class<?> jsonElement) {
        return create(new Gson(), jsonElement);
    }

    /**
     * Create an instance using {@code gson} for conversion. Encoding to JSON and
     * decoding from JSON (when no charset is specified by a header) will use UTF-8.
     */
    public static ConverterFactoryBaseModel create(Gson gson, Class<?> jsonElement) {
        return new ConverterFactoryBaseModel(gson, jsonElement);
    }

    private final Gson gson;


    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new GsonResponseBodyConverterBaseModel<>(adapter,mJsonElement);
    }


    public Converter<?, RequestBody> requestBodyConverter(Type type,
														  Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new GsonRequestBodyConverter<>(gson, adapter);
    }


}
