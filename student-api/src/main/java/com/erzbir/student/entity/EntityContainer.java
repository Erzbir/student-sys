package com.erzbir.student.entity;

import java.util.stream.Stream;

/**
 * @author Erzbir
 * @Data: 2024/5/29
 */
public interface EntityContainer<K, E> {
    void add(E e);

    E get(K key);

    void remove(K key);

    void update(E e);

    boolean contains(E e);

    Stream<E> stream();
}
