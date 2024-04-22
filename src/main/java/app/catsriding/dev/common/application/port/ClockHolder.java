package app.catsriding.dev.common.application.port;

import java.time.LocalDateTime;

public interface ClockHolder {

    LocalDateTime now();
}
