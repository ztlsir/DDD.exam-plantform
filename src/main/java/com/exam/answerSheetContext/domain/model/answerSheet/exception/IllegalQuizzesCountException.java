package com.exam.answerSheetContext.domain.model.answerSheet.exception;

public class IllegalQuizzesCountException extends IllegalArgumentException {
    public IllegalQuizzesCountException(int size) {
        super("TooManyQuizzesException: exception:5~20, actual:" + size);
    }
}
