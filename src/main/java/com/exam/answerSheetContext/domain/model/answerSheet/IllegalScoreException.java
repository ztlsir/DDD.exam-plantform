package com.exam.answerSheetContext.domain.model.answerSheet;

class IllegalScoreException extends IllegalArgumentException {
    IllegalScoreException(int score) {
        super("IllegalScoreException: exception score is not 100, actual:" + score);
    }
}
