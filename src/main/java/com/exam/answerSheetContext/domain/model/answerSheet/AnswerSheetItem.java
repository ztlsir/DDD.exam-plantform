package com.exam.answerSheetContext.domain.model.answerSheet;

import com.exam.shared.ValueObject;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AnswerSheetItem implements ValueObject<AnswerSheetItem> {
    private int number;
    private String answer;
    private int score;

    @Override
    public boolean sameValueAs(AnswerSheetItem other) {
        return this.getNumber() == other.getNumber()
                && this.getAnswer().equals(other.getAnswer())
                && this.getScore() == other.getScore();
    }

    void setAnswer(String answer) {
        this.answer = answer;
    }

    void setScore(int score) {
        this.score = score;
    }
}