package API_yandex;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;
import java.util.stream.Collectors;

public class HttpClientExample {
    public static void main(String[] args) {
        try {
            // Создаем клиент
            HttpClient client = HttpClient.newHttpClient();

            // String url = "https://yandex.ru/maps/api/masstransit/getVehiclesInfoWithRegion?ajax=1&csrfToken=4fd11ee112192bfdf089f9dbc4be284d60851b67%3A1774418302&lang=ru&lineId=2079775734&ll=49.132378%2C55.803212&locale=ru_RU&s=716464481&sessionId=1774418301922270-10466358929881314970-balancer-l7leveler-kubr-yp-vla-107-BAL&spn=0.108662%2C0.094172&type=bus";

            // String url = "https://yandex.com/maps/api/masstransit/getVehiclesInfoWithRegion?ajax=1&csrfToken=b72f555c32b7b029900054bcef34249e72770e2f%3A1774430664&lang=ru&lineId=2079775734&ll=49.141261%2C55.810027&locale=ru_DE&s=2263401226&sessionId=1774430664491238-10440396876083746001-balancer-l7leveler-kubr-yp-vla-153-BAL&spn=0.128746%2C0.027599&type=bus";

            String url = "https://yandex.com/maps/api/masstransit/getVehiclesInfoWithRegion?ajax=1&csrfToken=8e8da379db24eb125bc56fca128d261c7acd8db3%3A1774432360&lang=ru&lineId=2079775734&ll=49.103112%2C55.846879&locale=ru_DE&s=1460036573&sessionId=1774432360351267-10008567214648659754-balancer-l7leveler-kubr-yp-vla-153-BAL&spn=0.514984%2C0.072239&type=bus";

            // Создаем запрос
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .header("Accept", "text/plain")
                    .header("cookie","yandexuid=1659841911751642007; yuidss=1659841911751642007; yashr=3250264251758455628; receive-cookie-deprecation=1; gdpr=0; _ym_uid=1772820116598121689; _ym_d=1772820343; pi=/ffdRzHwUxaGsapuCKlPVwNtOVRLRIi4chiZAvRgsmMFkNS16TD97M0bee/WvzP12vAtC2rnjSpo4fuju7lEkCXiBT4=; i=Wf2O+FH2PcP41zE7hrKWl6dFu1gOzi/Y02HRhp+3hzQ2g5qOQwgwFhnWX77FFsNcpocUUNM+R5+joasUGhym3kbLw+s=; _ym_isad=1; yp=1774516452.yu.1659841911751642007; ymex=1777022052.oyu.1659841911751642007#1800464009.yrts.1768928009; _yasc=MBit//R85iM8w9PhFe/KrY4MS2K3K6s2mdGnHRHQqktokQZg/PWZ0rSkKKQyn+eh8alT; cycada=uH2OYJ8eIi6yBBDADK9s/2ul0rTcMA25gRjx+Xdv7Ck=; is_gdpr=1; is_gdpr_b=CIrYeRC0+wIYAQ==; maps_session_id=1774430664491238-10440396876083746001-balancer-l7leveler-kubr-yp-vla-153-BAL; yandex_expboxes=1500299%2C0%2C25%3B1486299%2C0%2C11%3B187288%2C0%2C78%3B1510733%2C0%2C41%3B912284%2C0%2C42%3B1515149%2C0%2C92%3B1509088%2C0%2C44%3B663872%2C0%2C11%3B663818%2C0%2C61%3B1257223%2C0%2C54%3B1513989%2C0%2C7; bh=EkEiQ2hyb21pdW0iO3Y9IjE0NiIsICJOb3QtQS5CcmFuZCI7dj0iMjQiLCAiR29vZ2xlIENocm9tZSI7dj0iMTQ2IhoFIng4NiIiECIxNDYuMC43NjgwLjE1NCIqAj8wMgIiIjoJIldpbmRvd3MiQggiMTAuMC4wIkoEIjY0IlJdIkNocm9taXVtIjt2PSIxNDYuMC43NjgwLjE1NCIsICJOb3QtQS5CcmFuZCI7dj0iMjQuMC4wLjAiLCAiR29vZ2xlIENocm9tZSI7dj0iMTQ2LjAuNzY4MC4xNTQiWgI/MGDT047OBmoe3Mrh/wiS2KGxA5/P4eoD+/rw5w3r//32D5jXzocI")
                    .build();

            // Отправляем запрос и получаем ответ
            HttpResponse<String> response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString()
            );

            // Получаем статус и тело ответа
            System.out.println("Status: " + response.statusCode());

            String body = response.body();

            System.out.println("Body: " + response.body());

            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            File jsonFile = new File("C:\\ITISproga\\season26\\25.03\\src\\main\\java\\API_yandex\\Vehicles.json");
            Vehicles vehicles = mapper.readValue(jsonFile, Vehicles.class);
            //System.out.println(vehicles);


            //task 1
            vehicles.getData().getVehicles().stream()
                    .map(b -> b.getProperties().getVehicleMetaData().getTransport().getName())
                    .collect(Collectors.groupingBy(
                            r -> r,
                            Collectors.counting()
                    )).forEach((r, c) -> System.out.println("Маршрут " + r + " : " + c + " (кол-во);"));

/*

            //task 2
            vehicles.getData().getVehicles().stream()
                    .collect(Collectors.groupingBy(
                            v -> v.getProperties().getVehicleMetaData().getTransport().getName(),
                            Collectors.flatMapping(
                                    v -> v.getFeatures().stream()
                                            .map(f -> f.getGeometry().getCoordinates()),
                                    Collectors.toList()
                            )
                    )).forEach((r, c) -> System.out.println("Маршрут " + r + " , его координаты:  " + c)); //как вывести тут список координат?
*/
/*

            //task 3
            vehicles.getData().getVehicles().stream() //flatMap преобразует потоки в один
                    .flatMap(v -> {
                        String routeName = v.getProperties().getVehicleMetaData().getTransport().getName();
                        return v.getFeatures().stream()
                                .flatMap(f -> f.getGeometry().getCoordinates().stream())
                                .map(c -> new AbstractMap.SimpleEntry<>(c, routeName)); // готовый класс для хранения одной пары данных
                    }).forEach(entry -> {
                        Double[] coord = entry.getKey();
                        String route = entry.getValue();
                        System.out.println("[ " + coord[0] + ", " + coord[1] + " ] -> Маршрут " + route);
                    });
*/

            //task 4

            double userLat = 55.792300;
            double userLon = 49.130000;

            Optional<Vehicle> neareatVehicle = vehicles.getData().getVehicles().stream()
                    .min(Comparator.comparingDouble(v -> {
                        List<Double[]> coords = v.getFeatures().get(0).getGeometry().getCoordinates();
                        Double[] busPos = coords.get(0);

                        double dLat = busPos[1] - userLat;
                        double dLon = busPos[0] - userLon;

                        return Math.sqrt(dLat * dLat + dLon * dLon);
                    }));

            if (neareatVehicle.isPresent()) {
                Vehicle v = neareatVehicle.get();
                String routeName = v.getProperties().getVehicleMetaData().getTransport().getName();
                System.out.println(routeName);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/*
1. Вывести номера маршрутов и кол-во транспорта на каждом
2. Вывести номер маршрута и список координат транспорта на маршруте
3. Вывести список координат и номер маршрута
4. Найти ближайший автобус по выбранным координатам
* */