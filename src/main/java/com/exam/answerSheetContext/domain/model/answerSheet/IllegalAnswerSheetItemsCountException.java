package com.exam.answerSheetContext.domain.model.answerSheet;

public class IllegalAnswerSheetItemsCountException extends IllegalArgumentException {
    IllegalAnswerSheetItemsCountException(int size) {
        super("TooManyAnswerSheetItemsException: exception:5~20, actual:" + size);
    }
}

