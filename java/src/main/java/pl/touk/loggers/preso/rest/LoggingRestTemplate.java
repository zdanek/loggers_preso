package pl.touk.loggers.preso.rest;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LoggingRestTemplate implements ClientHttpRequestInterceptor {

    private final static Logger LOGGER = LoggerFactory.getLogger(LoggingRestTemplate.class);

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body,
                                        ClientHttpRequestExecution execution) throws IOException {
        traceRequest(request, body);
        ClientHttpResponse response = execution.execute(request, body);
        return traceResponse(response);
    }

    private void traceRequest(HttpRequest request, byte[] body) throws IOException {
        if (!LOGGER.isTraceEnabled()) {
            return;
        }

        LOGGER.trace("URI                 : {}", request.getURI());
        LOGGER.trace("Method            : {}", request.getMethod());
        LOGGER.trace("Headers         : {}", request.getHeaders());
        LOGGER.trace("Request body: {}", new String(body, "UTF-8"));
    }

    private ClientHttpResponse traceResponse(ClientHttpResponse response) throws IOException {
        if (!LOGGER.isTraceEnabled()) {
            return response;
        }
        final ClientHttpResponse responseWrapper = new BufferingClientHttpResponseWrapper(response);
        StringBuilder inputStringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(responseWrapper.getBody(), "UTF-8"));
        String line = bufferedReader.readLine();
        while (line != null) {
            inputStringBuilder.append(line);
            inputStringBuilder.append('\n');
            line = bufferedReader.readLine();
        }

        LOGGER.trace("Status code    : {}", responseWrapper.getStatusCode());
        LOGGER.trace("Status text    : {}", responseWrapper.getStatusText());
        LOGGER.trace("Headers            : {}", responseWrapper.getHeaders());
        LOGGER.trace("Response body: {}", inputStringBuilder.toString());

        return responseWrapper;
    }

}
