package dev.be.feign.feign.decoder;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;

public class DemoFeignErrorDecoder implements ErrorDecoder {
    private final ErrorDecoder errorDecoder = new Default();
    @Override
    public Exception decode(String methodKey, Response response) {
        HttpStatus httpStatus = HttpStatus.resolve(response.status());

        // handling exception
        if (httpStatus == HttpStatus.NOT_FOUND) {
            System.out.println("[DemoFeignErrorDecoder] HttpStatus = " + httpStatus);
            throw new RuntimeException(String.format("[RuntimeException] HttpStatus is %s", httpStatus ));
        }

        //feign default defined errorDecoder
        return errorDecoder.decode(methodKey, response);
    }
}
