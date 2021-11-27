package pl.mkrew.app.mapper;

import org.mapstruct.Mapper;
import pl.mkrew.app.domain.Notification;
import pl.mkrew.app.dto.NotificationDto;

@Mapper(componentModel = "spring")
public interface NotificationStruckMapper {

    NotificationDto mapToDTO(Notification notification);

    Notification mapToNotification(NotificationDto notificationDto);
}
