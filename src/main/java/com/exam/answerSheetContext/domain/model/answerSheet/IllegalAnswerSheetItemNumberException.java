package com.exam.answerSheetContext.domain.model.answerSheet;

class IllegalAnswerSheetItemNumberException extends IllegalArgumentException {
    IllegalAnswerSheetItemNumberException(int number) {
        super("UnknownNumberException: actual:" + number);
    }
}
