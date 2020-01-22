package pl.com.own.localiser;

import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.net.InetAddress;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(MockitoJUnitRunner.class)
public class RawDBLocationServiceTest {


    private DataBaseService dataBaseService = new DataBaseService();

    private RawDBLocationService rawDBLocationService;

    public RawDBLocationServiceTest() throws IOException {
    }


    @org.junit.Before
    public void setUp() throws Exception {
        rawDBLocationService = new RawDBLocationService(dataBaseService);
    }

    @org.junit.Test
    public void checksIntegrationBtwnDbAndRawDBLocationServiceHavingSameIp() throws IOException, GeoIp2Exception {
        //given
        String ip = "188.112.39.251";
        CityResponse response = dataBaseService.getDatabaseReader().city(InetAddress.getByName(ip));
        String name = response.getCity().getName();
        String latitude = response.getLocation().getLatitude().toString();
        String longitude = response.getLocation().getLongitude().toString();
        GeoIP expected = new GeoIP(ip, name, latitude, longitude);
        //when
        GeoIP result = rawDBLocationService.getLocation(ip);
        //then
        assertEquals(expected, result);
    }

    @org.junit.Test
    public void checksIntegrationBtwnDbAndRawDBLocationServiceHavingDifferentIpForDbAndRawDBLocationService() throws IOException, GeoIp2Exception {
        //given
        String ip = "188.112.39.251";
        CityResponse response = dataBaseService.getDatabaseReader().city(InetAddress.getByName(ip));
        String name = response.getCity().getName();
        String latitude = response.getLocation().getLatitude().toString();
        String longitude = response.getLocation().getLongitude().toString();
        GeoIP expected = new GeoIP(ip, name, latitude, longitude);
        //when
        GeoIP result = rawDBLocationService.getLocation("182.112.39.251");
        //then
        assertNotEquals(expected, result);
    }
}
