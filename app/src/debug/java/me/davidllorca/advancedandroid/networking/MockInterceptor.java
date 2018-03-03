package me.davidllorca.advancedandroid.networking;

import java.io.IOException;

import javax.inject.Inject;

import me.davidllorca.advancedandroid.settings.DebugPreferences;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 3/03/18.
 */

public class MockInterceptor implements Interceptor {

    private final MockResponseFactory mockResponseFactory;
    private final DebugPreferences debugPreferences;

    @Inject
    MockInterceptor(MockResponseFactory mockResponseFactory, DebugPreferences debugPreferences) {
        this.mockResponseFactory = mockResponseFactory;
        this.debugPreferences = debugPreferences;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        if (debugPreferences.useMockResponseEnabled()) {
            String mockResponse = mockResponseFactory.getMockResponse(chain.request());
            if (mockResponse != null) {
                return new Response.Builder()
                        .message("")
                        .protocol(Protocol.HTTP_1_1)
                        .request(chain.request())
                        .code(200)
                        .body(ResponseBody.create(MediaType.parse("text/json"), mockResponse))
                        .build();
            }
        }
        return chain.proceed(chain.request());

    }
}
