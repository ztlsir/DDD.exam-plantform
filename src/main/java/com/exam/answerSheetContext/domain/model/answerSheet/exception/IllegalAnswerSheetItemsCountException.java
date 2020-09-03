package com.exam.answerSheetContext.domain.model.answerSheet.exception;

public class IllegalAnswerSheetItemsCountException extends IllegalArgumentException {
    public IllegalAnswerSheetItemsCountException(int size) {
        super("TooManyAnswerSheetItemsException: exception:5~20, actual:" + size);
    }
}

