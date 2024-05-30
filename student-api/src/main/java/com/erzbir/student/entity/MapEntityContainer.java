package com.erzbir.student.entity;

import java.util.Map;
import java.util.stream.Stream;

/**
 * @author Erzbir
 * @Data: 2024/5/29
 */
public interface MapEntityContainer<K, E> extends EntityContainer<K, E> {
    Map<K, E> getItems();

    @Override
    default Stream<E> stream() {
        return getItems().values().stream();
    }
}
