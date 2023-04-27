package co.istad.mobileBanking.api.user.wep;

import lombok.Builder;

@Builder
public record UserDto(String name,
                      String gender,
                      String studentCardId,
                      Boolean isStudent) {
}
