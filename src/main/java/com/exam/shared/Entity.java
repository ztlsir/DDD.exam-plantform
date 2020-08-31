package com.exam.shared;

public interface Entity<T> {
    boolean sameIdentityAs(T other);
}
