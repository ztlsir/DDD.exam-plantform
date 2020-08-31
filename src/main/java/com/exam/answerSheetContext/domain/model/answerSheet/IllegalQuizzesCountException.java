package com.exam.answerSheetContext.domain.model.answerSheet;

class IllegalQuizzesCountException extends IllegalArgumentException {
    IllegalQuizzesCountException(int size) {
        super("TooManyQuizzesException: exception:5~20, actual:" + size);
    }
}
