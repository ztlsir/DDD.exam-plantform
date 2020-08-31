package com.exam.shared;

public interface ValueObject<T> {
    boolean sameValueAs(T other);
}
