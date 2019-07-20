package pl.programmerid.microservices.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
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

    @FeignClient(name = "config-server", fallback = ConfigServerFallback.class)
    interface ConfigClient {
        @RequestMapping(value = "/client-service/master", method = GET, consumes = MediaType.APPLICATION_JSON_VALUE)
        String getConfig();
    }

    @Component
    class ConfigServerFallback implements ConfigClient {

        @Override
        public String getConfig() {
            return "UNAVAILABLE";
        }
    }

}
