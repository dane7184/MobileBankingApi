package co.istad.mobileBanking.api.notificationRestController.controller;

import co.istad.mobileBanking.api.notificationRestController.service.NotificationService;
import co.istad.mobileBanking.api.notificationRestController.web.CreateNotificationDto;
import co.istad.mobileBanking.base.BaseRest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
public class NotificationRestController {

    private final NotificationService notificationService;

    @PostMapping
    public BaseRest<?> pushNotification(@RequestBody CreateNotificationDto notificationDto){

        notificationService.pushNotification(notificationDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .massage("Notification has been pushed")
                .timestamp(LocalDateTime.now())
                .data(notificationDto.contents())
                .build();
    }
}
