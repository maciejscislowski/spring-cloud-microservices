package pl.programmerid.microservices.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Maciej.Scislowski@gmail.com
 */
@RefreshScope
@RestController
public class ClientController {

    @Value("${test}")
    private String property;
    @GetMapping("/message")
    String property() {
        return property;
    }

}
