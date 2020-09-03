package com.exam.answerSheetContext.domain.model.answerSheet;

import com.exam.shared.ValueObject;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BlankQuiz implements ValueObject<BlankQuiz> {
    private int number;
    private String answer;
    private String referenceAnswer;
    private int score;

    @Override
    public boolean sameValueAs(BlankQuiz other) {
        return this.getNumber() == other.getNumber()
                && this.getAnswer().equals(other.getAnswer())
                && this.getScore() == other.getScore()
                && this.getReferenceAnswer().equals(other.getReferenceAnswer());
    }
}
