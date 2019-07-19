package pl.programmerid.microservices.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author Maciej.Scislowski@gmail.com
 */
@RefreshScope
@RestController
public class ClientController {

    @Value("${test}")
    private String property;
    @Autowired
    private ConfigClient configClient;

    @GetMapping("/message")
    String property() {
        return property + " " + configClient.getConfig();
    }

    @FeignClient("config-server")
    interface ConfigClient {
        @RequestMapping(value = "/client-service/master", method = GET)
        String getConfig();
    }

}
