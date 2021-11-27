package pl.mkrew.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mkrew.app.domain.Notification;
import pl.mkrew.app.dto.NotificationDto;
import pl.mkrew.app.repository.NotificationRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationService {

    @Deprecated
    private List<NotificationDto> notifications = new ArrayList<>();
    private final NotificationRepository notificationRepository;
    private final SmsSenderService smsSenderService;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository, SmsSenderService smsSenderService) {
        this.notificationRepository = notificationRepository;
        this.smsSenderService = smsSenderService;
    }

    public void addNotofication(NotificationDto notificationDto) {
        Notification notification = Notification.builder()
                .name(notificationDto.getName())
                .date(notificationDto.getDate())
                .type(notificationDto.getType())
                .message(notificationDto.getMessage())
                .build();
        notificationRepository.save(notification);
        smsSenderService.sendSms("Dodano nowe zadanie: " + notification.getName() + ", opis zadania " + notification.getType());
    }

    public void showNotifications(NotificationDto notificationDto) {
        System.out.println(notificationDto);
    }

    public List<NotificationDto> getNotifications() {
        return notificationRepository.findAll()
                .stream()
                .map(notification -> new NotificationDto(
                        notification.getId(),
                        notification.getDate(),
                        notification.getName(),
                        notification.getType(),
                        notification.getMessage()
                )).collect(Collectors.toList());
    }

    public void updateNotification(Long notificationId, NotificationDto newNotification) {
        notificationRepository.findById(notificationId)
                .ifPresent(notification -> {
                    notification.setName(newNotification.getName());
                    notification.setType(newNotification.getType());
                    notification.setDate(newNotification.getDate());
                    notification.setMessage(newNotification.getMessage());
                    notificationRepository.save(notification);
                });
    }

    public void deleteNotification(Long notificationId) {
        notificationRepository.findById(notificationId)
                .ifPresent(notification -> {
                    notificationRepository.delete(notification);
                });
    }

}
