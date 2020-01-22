package pl.com.own.localiser;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class LocationController {

    private RawDBLocationService service;

    public LocationController() throws IOException {
        service=new RawDBLocationService();
    }

    @PostMapping("/location")
    public GeoIP getLocation(@RequestParam(value="ipAddress") String ipAddress) throws Exception {

        return service.getLocation(ipAddress);
    }
}
