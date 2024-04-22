package app.catsriding.dev.common.infra;

import app.catsriding.dev.common.application.port.ClockHolder;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ClockHolderImpl implements ClockHolder {

    @Override
    public LocalDateTime now() {
        return LocalDateTime.now();
    }
}
