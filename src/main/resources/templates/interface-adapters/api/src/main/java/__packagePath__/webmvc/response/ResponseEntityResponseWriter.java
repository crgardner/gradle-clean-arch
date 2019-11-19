package @packageName@.webmvc.response;

import @packageName@.controller.greeting.shared.GreetingResource;
import @packageName@.controller.response.ResponseWriter;
import org.springframework.http.ResponseEntity;

public class ResponseEntityResponseWriter implements ResponseWriter {

    private ResponseEntity<Object> responseEntity;

    @Override
    public void ok(Object resource) {
        responseEntity = ResponseEntity.ok(resource);
    }


    public ResponseEntity<Object> getResponseEntity() {
        return responseEntity;
    }
}
