package pl.com.own.localiser;

import com.maxmind.geoip2.DatabaseReader;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
@Data
public class DataBaseService {


    private DatabaseReader databaseReader;

    public DataBaseService() throws IOException {
        String mmdbSourcePath = "ścieżka do pliku z bazą lokalizacji ip: GeoLite2-City.mmdb - do pobrania z sieci";
        File database = new File(mmdbSourcePath);
        this.databaseReader = new DatabaseReader.Builder(database).build();
    }
}
