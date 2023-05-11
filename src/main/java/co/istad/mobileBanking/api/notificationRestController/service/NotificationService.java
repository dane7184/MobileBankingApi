package co.istad.mobileBanking.api.notificationRestController.service;

import co.istad.mobileBanking.api.notificationRestController.web.CreateNotificationDto;
import co.istad.mobileBanking.api.notificationRestController.web.NotificationDto;

public interface NotificationService {
    /**
     *
     * @param notificationDto
     * @return
     */
    boolean pushNotification(CreateNotificationDto notificationDto);
}
