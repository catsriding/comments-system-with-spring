package app.catsriding.dev.common.api.resopnse;

import app.catsriding.dev.common.api.enums.RestApiStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestApiResponse<T> {

    private int code;
    private String phrase;
    private T payload;

    public static <DATA> RestApiResponse<?> write(RestApiStatus status, DATA payload) {
        return RestApiResponse.builder()
                .code(status.getValue())
                .phrase(status.getPhrase())
                .payload(payload)
                .build();
    }

    public static RestApiResponse<?> write(RestApiStatus status) {
        return RestApiResponse.builder()
                .code(status.getValue())
                .phrase(status.getPhrase())
                .build();
    }

    public static RestApiResponse<?> write(RestApiStatus status, String phrase) {
        return RestApiResponse.builder()
                .code(status.getValue())
                .phrase(phrase)
                .build();
    }
}