package co.istad.mobileBanking.api.notificationRestController.web;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Value;

public record CreateNotificationDto(@JsonProperty("included_segments") String[] includedSegments,
                                    @JsonProperty("app_id")
                                    @Value("${onesignal.app-id}")
                                    String appId,
                                    ContentDto contents) {
}
