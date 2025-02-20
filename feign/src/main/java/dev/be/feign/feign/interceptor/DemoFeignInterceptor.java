package dev.be.feign.feign.interceptor;

import feign.Request;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;

/*
    need to be registered as BEAN in Config
 */
@RequiredArgsConstructor(staticName = "of")
public class DemoFeignInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        // request method is "get"
        if (template.method() == Request.HttpMethod.GET.name()) {
            System.out.println("[GET] [DemoFeignInterceptor] queries: " + template.queries());
            return;
        }

        // request method is "post"
        String encodedReqBody = StringUtils.toEncodedString(template.body(), StandardCharsets.UTF_8);
        System.out.println("[POST] [DemoFeignInterceptor] requestBody: " + encodedReqBody);

        // change body value by POST
            // additional process to change body with encodedReqBody
        String convertedBody = encodedReqBody;
        template.body(convertedBody);

    }
}
