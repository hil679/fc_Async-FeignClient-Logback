package dev.be.feign.feign.logger;

import feign.Logger; // path check
import feign.Request;
import feign.Response;
import feign.Util;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

import static feign.Util.*;
import static feign.Util.UTF_8;

@RequiredArgsConstructor
public class FeignCustomLogger extends Logger {
    private static final int DEFAULT_SLOW_API_TIME = 3_000; //3sec
    private static final String SLOW_API_NOTICE = "Slow API";

    @Override
    protected void log(String configKey, String format, Object... args) {
        // log를 어떤 형식으로 남길지 정해준다.
        // methodTag is in Logger
        System.out.println(String.format(methodTag(configKey) + format, args)) ;
    }

    @Override
    // only deal with request
    protected void logRequest(String configKey, Level logLevel, Request request) {
//        super.logRequest(configKey, logLevel, request);
        System.out.println("[logRequest] " + request);
    }

    @Override
    // deal with both request and response
    //elapsedTime : the time between request and response
    // !!!!! handling elapsedTime is very important !!!!!
    protected Response logAndRebufferResponse(String configKey, Level logLevel, Response response, long elapsedTime) throws IOException {
//        return super.logAndRebufferResponse(configKey, logLevel, response, elapsedTime);

        // protocol is important
        String protocolVersion = resolveProtocolVersion(response.protocolVersion());
        String reason =
                response.reason() != null && logLevel.compareTo(Level.NONE) > 0
                        ? " " + response.reason()
                        : "";
        int status = response.status();
        log(configKey, "<--- %s %s%s (%sms)", protocolVersion, status, reason, elapsedTime);
        if (logLevel.ordinal() >= Level.HEADERS.ordinal()) { // Log "the headers, body, and metadata" or "basic info"

            for (String field : response.headers().keySet()) {
                if (shouldLogResponseHeader(field)) {
                    for (String value : valuesOrEmpty(response.headers(), field)) {
                        log(configKey, "%s: %s", field, value);
                    }
                }
            }

            int bodyLength = 0;
            if (response.body() != null && !(status == 204 || status == 205)) {
                // HTTP 204 No Content "...response MUST NOT include a message-body"
                // HTTP 205 Reset Content "...response MUST NOT include an entity"
                if (logLevel.ordinal() >= Level.HEADERS.ordinal()) {
                    log(configKey, ""); // CRLF
                }
                byte[] bodyData = Util.toByteArray(response.body().asInputStream());
                ensureClosed(response.body());
                bodyLength = bodyData.length;
                if (logLevel.ordinal() >= Level.HEADERS.ordinal() && bodyLength > 0) {
                    log(configKey, "%s", decodeOrDefault(bodyData, UTF_8, "Binary data"));
                }
                if (elapsedTime > DEFAULT_SLOW_API_TIME) {
                    log(configKey, "[%s] elapsedTime : %s", SLOW_API_NOTICE, elapsedTime);
                }
                log(configKey, "<--- END HTTP (%s-byte body)", bodyLength);
                return response.toBuilder().body(bodyData).build();
            } else {
                log(configKey, "<--- END HTTP (%s-byte body)", bodyLength);
            }
        }
        return response;
    }
}
