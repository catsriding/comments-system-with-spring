package app.catsriding.dev.common.api.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@RequiredArgsConstructor
public enum RestApiStatus {

    OK(200, "OK", "DESC"),

    PERMANENT_REDIRECT(308, "Permanent Redirect", "DESC"),

    BAD_REQUEST(400, "Bad Request", "DESC"),
    UNAUTHORIZED(401, "Unauthorized", "DESC"),
    FORBIDDEN(403, "Forbidden", "DESC"),
    NOT_FOUND(404, "Not Found", "DESC"),
    NOT_ACCEPTABLE(406, "Not Acceptable", "DESC"),

    INTERNAL_SERVER_ERROR(500, "Internal Server Error", "DESC"),
    SERVICE_UNAVAILABLE(503, "Service Unavailable", "DESC");

    private final int value;
    private final String phrase;
    private final String description;

}