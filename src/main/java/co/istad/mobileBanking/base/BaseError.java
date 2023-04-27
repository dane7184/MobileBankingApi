package co.istad.mobileBanking.base;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record BaseError<T>(Boolean status,
                           Integer code,
                           String massage,
                           LocalDateTime timestamp,
                           T errors) {
}
