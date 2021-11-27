package pl.mkrew.app.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.mkrew.app.dto.NotificationDto;
import pl.mkrew.app.service.NotificationService;

import java.util.List;

@RestController
@RequestMapping("/v1/notification")
public class NotificationController {

    private NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping
    public List<NotificationDto> getNotifications() {
        return notificationService.getNotifications();

    }

    @PostMapping
    public void addNotification(@RequestBody NotificationDto notificationDto) {

        notificationService.addNotofication(notificationDto);
    }

    @PutMapping("/{notificationId}")
    public void updateNotification(@PathVariable("notificationId") Long notificationId, @RequestBody NotificationDto notificationDto) {

        notificationService.updateNotification(notificationId, notificationDto);
    }

    @DeleteMapping("/{notificationId}")
    public void deleteNotification(@PathVariable("notificationId") Long notificationId) {

        notificationService.deleteNotification(notificationId);
    }

}
