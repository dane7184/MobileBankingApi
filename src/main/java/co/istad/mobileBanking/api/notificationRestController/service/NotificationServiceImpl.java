package co.istad.mobileBanking.api.notificationRestController.service;

import co.istad.mobileBanking.api.notificationRestController.web.CreateNotificationDto;
import co.istad.mobileBanking.api.notificationRestController.web.NotificationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class NotificationServiceImpl implements NotificationService{

    @Value("${onesignal.rest-api-key}")
    private String restApiKey;

    @Value("${onesignal.app-id}")
    private String appID;
    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public boolean pushNotification(CreateNotificationDto notificationDto) {


        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("accept", "application/json");
        httpHeaders.set("content-type", "application/json");
        httpHeaders.set("Authorization", "Basic " + restApiKey);
        NotificationDto body = NotificationDto.builder()
                .appId(appID)
                .includedSegments(notificationDto.includedSegments())
                .contents(notificationDto.contents()).build();
        HttpEntity<NotificationDto> requestBody = new HttpEntity<>(body, httpHeaders);


        ResponseEntity<?> response = restTemplate.postForEntity(
                "https://onesignal.com/api/v1/notifications",
                requestBody,
                Map.class
        );

        return response.getStatusCode() == HttpStatus.OK;
    }
}
