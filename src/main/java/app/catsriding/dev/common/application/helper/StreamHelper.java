package app.catsriding.dev.common.application.helper;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamHelper {

    public static <T, R> List<R> transform(List<T> elements, Function<? super T, ? extends R> map) {
        return elements.stream()
                .map(map)
                .collect(Collectors.toList());
    }
}
