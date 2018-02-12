package me.davidllorca.advancedandroid.test;

import com.squareup.moshi.Moshi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

import javax.inject.Inject;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 12/02/18.
 */

public class TestUtils {

    private final Moshi moshi;

    // We don't need to initialize Moshi like test/testutils/TestUtils.class because here is self
    // provided for ServiceModule.

    @Inject
    TestUtils(Moshi moshi) {
        this.moshi = moshi;
    }

    public <T> T loadJson(String path, Type type) {
        try {
            String json = getFileString(path);
            return (T) moshi.adapter(type).fromJson(json);

        } catch (IOException e) {
            throw new IllegalArgumentException("Could not deserialize: " + path + " into type: "
                    + type);
        }
    }

    public <T> T loadJson(String path, Class<T> clazz) {
        try {
            String json = getFileString(path);
            return moshi.adapter(clazz).fromJson(json);
        } catch (IOException e) {
            throw new IllegalArgumentException("Could not deserialize: " + path + " into class: "
                    + clazz);
        }
    }

    private String getFileString(String path) {
        try {
            StringBuilder sb = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(getClass()
                    .getClassLoader().getResourceAsStream(path)));
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (IOException e) {
            throw new IllegalArgumentException("Could not read from resource at: + path");
        }
    }
}
