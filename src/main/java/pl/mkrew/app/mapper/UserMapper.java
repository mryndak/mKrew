package pl.mkrew.app.mapper;

import org.springframework.stereotype.Service;
import pl.mkrew.app.domain.UserEntity;
import pl.mkrew.app.dto.UserDto;

@Service
public class UserMapper {
    public UserDto mapToDTO(UserEntity user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .phoneNumber(user.getPhoneNumber())
                .bloodGroup(user.getBloodGroup())
                .email(user.getEmail())
                .login(user.getLogin())
                .build();
    }
    public UserEntity mapToUser(UserDto userDTO){
        return UserEntity.builder()
                .name(userDTO.getName())
                .surname(userDTO.getSurname())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .bloodGroup(userDTO.getBloodGroup())
                .login(userDTO.getLogin())
                .phoneNumber(userDTO.getPhoneNumber())
                .build();
    }
}
