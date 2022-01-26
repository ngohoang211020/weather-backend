import com.oddle.app.weather.dto.CityModel;
import com.oddle.app.weather.dto.CurrentWeatherModel;
import com.oddle.app.weather.dto.WeatherDescModel;
import com.oddle.app.weather.dto.WeatherModel;
import com.oddle.app.weather.repository.JpaWeatherRepositoryAdapter;
import com.oddle.app.weather.repository.RestOpenWeatherMapRepository;
import com.oddle.app.weather.service.WeatherService;
import com.oddle.app.weather.service.WeatherServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WeatherService.class)
public class WeatherServiceImplTest {
    @Mock
    private RestOpenWeatherMapRepository restWeatherService;

    @Mock
    private JpaWeatherRepositoryAdapter jpaWeatherRepositoryAdapter;

    @InjectMocks
    private WeatherServiceImpl weatherService;

    private static final String CITY = "city";
    private static final Long WEATHER_ID = 1L;
    private static final int ID = 1;
    private static final String COUNTRY_CODE = "countryCode";
    private static final double LATITUDE = 100.0;
    private static final double LONGITUDE = 100.0;
    private static final String NAME = "name";
    private static final int TIMEZONE = 100;
    private static final float HUMIDITY = 100;
    private static final float PRESSURE = 100;
    private static final float TEMP = 100;
    private static final float TEMP_MAX = 100;
    private static final float TEMP_MIN = 100;
    private static final String MAIN = "main";
    private static final String DESCRIPTION = "description";
    private static final String ICON = "icon";
    private static final LocalDateTime FROM = LocalDateTime.now();
    private static final LocalDateTime TO = LocalDateTime.now().plusDays(7);

    @Test
    public void givenValidCity_whenSearchWeatherToday_thenSuccess() {
        CurrentWeatherModel currentWeatherModel = buildMockCurrentWeatherModel();

        Mockito.when(restWeatherService.searchWeatherToday(CITY)).thenReturn(currentWeatherModel);

        CurrentWeatherModel actual = weatherService.searchWeatherToday(CITY);

        Mockito.verify(restWeatherService).searchWeatherToday(CITY);

        assertEquals(actual.getCity().getId(), currentWeatherModel.getCity().getId());
        assertEquals(actual.getCity().getCountryCode(), currentWeatherModel.getCity().getCountryCode());
        assertEquals(actual.getCity().getLatitude(), currentWeatherModel.getCity().getLatitude(), 0.01);
        assertEquals(actual.getCity().getLongitude(), currentWeatherModel.getCity().getLongitude(), 0.01);
        assertEquals(actual.getCity().getName(), currentWeatherModel.getCity().getName());
        assertEquals(actual.getCity().getTimezone(), currentWeatherModel.getCity().getTimezone());
        assertEquals(actual.getWeather().getId(), currentWeatherModel.getWeather().getId());
        assertEquals(actual.getWeather().getDate(), currentWeatherModel.getWeather().getDate());
        assertEquals(actual.getWeather().getCity(), currentWeatherModel.getWeather().getCity());
        assertEquals(actual.getWeather().getHumidity(), currentWeatherModel.getWeather().getHumidity(), 0.01);
        assertEquals(actual.getWeather().getPressure(), currentWeatherModel.getWeather().getPressure(), 0.01);
        assertEquals(actual.getWeather().getTemp(), currentWeatherModel.getWeather().getTemp(), 0.01);
        assertEquals(actual.getWeather().getTempMax(), currentWeatherModel.getWeather().getTempMax(), 0.01);
        assertEquals(actual.getWeather().getTempMin(), currentWeatherModel.getWeather().getTempMin(), 0.01);
        assertEquals(actual.getWeatherDescModels(), currentWeatherModel.getWeatherDescModels());
    }

    @Test
    public void givenValidCity_whenSaveWeather_thenSuccess() {
        CurrentWeatherModel currentWeatherModel = buildMockCurrentWeatherModel();

        Mockito.when(restWeatherService.searchWeatherToday(CITY)).thenReturn(currentWeatherModel);

        CurrentWeatherModel actual = weatherService.saveWeather(CITY);

        Mockito.verify(restWeatherService).searchWeatherToday(CITY);
        Mockito.verify(jpaWeatherRepositoryAdapter).saveWeather(currentWeatherModel.getWeather(), currentWeatherModel.getCity());

        assertEquals(actual.getCity().getId(), currentWeatherModel.getCity().getId());
        assertEquals(actual.getCity().getCountryCode(), currentWeatherModel.getCity().getCountryCode());
        assertEquals(actual.getCity().getLatitude(), currentWeatherModel.getCity().getLatitude(), 0.01);
        assertEquals(actual.getCity().getLongitude(), currentWeatherModel.getCity().getLongitude(), 0.01);
        assertEquals(actual.getCity().getName(), currentWeatherModel.getCity().getName());
        assertEquals(actual.getCity().getTimezone(), currentWeatherModel.getCity().getTimezone());
        assertEquals(actual.getWeather().getId(), currentWeatherModel.getWeather().getId());
        assertEquals(actual.getWeather().getDate(), currentWeatherModel.getWeather().getDate());
        assertEquals(actual.getWeather().getCity(), currentWeatherModel.getWeather().getCity());
        assertEquals(actual.getWeather().getHumidity(), currentWeatherModel.getWeather().getHumidity(), 0.01);
        assertEquals(actual.getWeather().getPressure(), currentWeatherModel.getWeather().getPressure(), 0.01);
        assertEquals(actual.getWeather().getTemp(), currentWeatherModel.getWeather().getTemp(), 0.01);
        assertEquals(actual.getWeather().getTempMax(), currentWeatherModel.getWeather().getTempMax(), 0.01);
        assertEquals(actual.getWeather().getTempMin(), currentWeatherModel.getWeather().getTempMin(), 0.01);
        assertEquals(actual.getWeatherDescModels(), currentWeatherModel.getWeatherDescModels());
    }

    @Test
    public void givenValidDateFromAndTo_whenGetHistoricalWeather_thenSuccess() {
        List<WeatherModel> list = new ArrayList<>();
        list.add(buildMockWeatherModel());

        Mockito.when(jpaWeatherRepositoryAdapter.getHistoricalWeather(FROM, TO)).thenReturn(list);

        List<WeatherModel> actual = weatherService.getHistoricalWeather(FROM, TO);

        Mockito.verify(jpaWeatherRepositoryAdapter).getHistoricalWeather(FROM, TO);

        assertEquals(actual, list);
    }

    @Test
    public void givenValidFromAndTo_whenDeleteHistoricalWeather_thenSuccess() {
        weatherService.deleteHistoricalWeather(FROM, TO);

        Mockito.verify(jpaWeatherRepositoryAdapter).deleteHistoricalWeather(FROM, TO);
    }

    @Test
    public void givenValidId_whenDeleteHistoricalWeatherById_thenSuccess() {
        weatherService.deleteHistoricalWeatherById(WEATHER_ID);

        Mockito.verify(jpaWeatherRepositoryAdapter).deleteHistoricalWeatherById(WEATHER_ID);
    }

    @Test
    public void givenValidIdAndWeatherModel_whenUpdateHistoricalWeather_thenSuccess() {
        WeatherModel weatherModel = buildMockWeatherModel();
        weatherService.updateHistoricalWeather(WEATHER_ID, weatherModel);

        Mockito.verify(jpaWeatherRepositoryAdapter).updateHistoricalWeather(WEATHER_ID, weatherModel);
    }

    private CurrentWeatherModel buildMockCurrentWeatherModel() {
        return CurrentWeatherModel.builder()
                .weather(buildMockWeatherModel())
                .city(buildMockCityModel())
                .weatherDescModels(buildListWeatherDescModels())
                .build();
    }

    private WeatherModel buildMockWeatherModel() {
        return WeatherModel.builder()
                .city(buildMockCityModel())
                .date(LocalDateTime.now())
                .humidity(HUMIDITY)
                .pressure(PRESSURE)
                .temp(TEMP)
                .tempMax(TEMP_MAX)
                .tempMin(TEMP_MIN)
                .id(WEATHER_ID)
                .build();
    }

    private CityModel buildMockCityModel() {
        return CityModel.builder()
                .id(ID)
                .name(NAME)
                .countryCode(COUNTRY_CODE)
                .latitude(LATITUDE)
                .longitude(LONGITUDE)
                .timezone(TIMEZONE)
                .build();
    }

    private List<WeatherDescModel> buildListWeatherDescModels() {
        List<WeatherDescModel> list = new ArrayList<>();
        list.add(WeatherDescModel.builder()
                .icon(ICON)
                .main(MAIN)
                .description(DESCRIPTION)
                .build());
        return list;
    }
}
