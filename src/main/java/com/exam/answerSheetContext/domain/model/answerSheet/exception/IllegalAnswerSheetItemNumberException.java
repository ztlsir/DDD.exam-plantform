package com.exam.answerSheetContext.domain.model.answerSheet.exception;

public class IllegalAnswerSheetItemNumberException extends IllegalArgumentException {
    public IllegalAnswerSheetItemNumberException(int number) {
        super("UnknownNumberException: actual:" + number);
    }
}
