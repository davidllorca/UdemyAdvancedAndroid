package me.davidllorca.advancedandroid.networking;

import android.content.Context;
import android.support.annotation.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import timber.log.Timber;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 3/03/18.
 */

class MockResourceLoader {

    private MockResourceLoader() {

    }

    @Nullable
    static String getResponseStrign(Context context, String method, String[] endPointParts) {
        try {
            String currentPath = "mock";
            Set<String> mockList = new HashSet<>(Arrays.asList(context.getAssets().list
                    (currentPath)));
            for (String endPointPart : endPointParts) {
                if (mockList.contains(endPointPart)) {
                    currentPath = currentPath + "/" + endPointPart;
                    mockList = new HashSet<>(Arrays.asList(context.getAssets().list(currentPath)));
                }
            }

            // At this stage, our mock list will be the list of files in the matching directory
            // for the endpoints parts.
            String finalPath = null;
            for (String path : mockList) {
                if (path.contains(method.toLowerCase())) {
                    finalPath = currentPath + "/" + path;
                    break;
                }
            }

            if (finalPath != null) {
                return responseFromPath(context, finalPath);
            }
            return null;
        } catch (IOException e) {
            Timber.e(e, "Error loading mock responses from assets");
            return null;
        }
    }

    private static String responseFromPath(Context context, String path) {
        StringBuilder sb = new StringBuilder();
        try (InputStream assetStream = context.getAssets().open(path);
             BufferedReader reader = new BufferedReader(new InputStreamReader(assetStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            Timber.e(e, "Error reading mock response");
        }
        return sb.toString();
    }
}
