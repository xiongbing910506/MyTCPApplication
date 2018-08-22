package httpapplication.nicechina.com.httpapplication.global;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import httpapplication.nicechina.com.httpapplication.model.ResultJson;

/**
 * Created by diaoyuhang on 2017-06-03.
 */

public class ResultJsonUtils <T> {
    public ResultJson<T> getResultModel(String value,Class<T> clazz){
        Gson gson = new Gson();
        ResultJson<T> resultJson = new ResultJson<T>();
        Type objectType = type(ResultJson.class, clazz);
        return gson.fromJson(value,objectType);
    }

    static ParameterizedType type(final Class raw, final Type... args) {
        return new ParameterizedType() {
            public Type getRawType() {
                return raw;
            }

            public Type[] getActualTypeArguments() {
                return args;
            }

            public Type getOwnerType() {
                return null;
            }
        };
    }

}
