package com.exam.answerSheetContext.domain.model.answerSheet;

import com.exam.answerSheetContext.domain.model.answerSheet.exception.IllegalAnswerSheetItemNumberException;
import com.exam.answerSheetContext.domain.model.answerSheet.exception.IllegalAnswerSheetItemsCountException;
import com.exam.answerSheetContext.domain.model.answerSheet.exception.IllegalQuizzesCountException;
import com.exam.answerSheetContext.domain.model.answerSheet.exception.IllegalScoreException;
import com.exam.shared.Entity;
import com.exam.shared.ValueObject;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;
import java.util.Optional;

@EqualsAndHashCode(of = {"answerSheetId"})
public class AnswerSheet implements Entity<AnswerSheet> {
    private AnswerSheetId answerSheetId;
    private String studentId;
    private String examinationId;
    private int score;
    private List<BlankQuiz> blankQuizzes;
    private List<AnswerSheetItem> answerSheetItems;

    private AnswerSheet(AnswerSheetId answerSheetId, String studentId, String examinationId, List<BlankQuiz> blankQuizzes, List<AnswerSheetItem> answerSheetItems) {
        this.answerSheetId = answerSheetId;
        this.studentId = studentId;
        this.examinationId = examinationId;
        this.blankQuizzes = blankQuizzes;
        this.answerSheetItems = answerSheetItems;
    }

    public static AnswerSheet assign(AnswerSheetId answerSheetId, String studentId, String examinationId, List<BlankQuiz> blankQuizzes, List<AnswerSheetItem> answerSheetItems) {
        validateQuizzes(blankQuizzes);
        validateAnswerSheetItems(answerSheetItems);

        return new AnswerSheet(answerSheetId, studentId, examinationId, blankQuizzes, answerSheetItems);
    }

    private static void validateAnswerSheetItems(List<AnswerSheetItem> answerSheetItems) {
        if (answerSheetItems.size() > 20 || answerSheetItems.size() < 5) {
            throw new IllegalAnswerSheetItemsCountException(answerSheetItems.size());
        }
    }

    private static void validateQuizzes(List<BlankQuiz> blankQuizzes) {
        if (blankQuizzes.size() > 20 || blankQuizzes.size() < 5) {
            throw new IllegalQuizzesCountException(blankQuizzes.size());
        }

        int totalScore = blankQuizzes.stream().mapToInt(BlankQuiz::getScore).sum();
        if (totalScore != 100) {
            throw new IllegalScoreException(totalScore);
        }
    }

    public AnswerSheetId getId() {
        return this.answerSheetId;
    }

    public void submit(int number, String answer) {
        this.answerSheetItems.stream()
                .filter(answerSheetItem -> answerSheetItem.getNumber() == number)
                .findFirst()
                .orElseThrow(() -> new IllegalAnswerSheetItemNumberException(number))
                .setAnswer(answer);
    }

    public void handIn() {
        this.review();
    }

    public void review() {
        this.answerSheetItems.forEach(answerSheetItem -> {
            Optional<BlankQuiz> optionalBlankQuiz = this.blankQuizzes.stream()
                    .filter(blankQuiz -> blankQuiz.getNumber() == answerSheetItem.getNumber()
                            && blankQuiz.getAnswer().equals(answerSheetItem.getAnswer()))
                    .findFirst();

            if (optionalBlankQuiz.isPresent()) {
                answerSheetItem.setScore(optionalBlankQuiz.get().getScore());
            } else {
                answerSheetItem.setScore(0);
            }
        });

        this.totalScore();
    }

    private void totalScore() {
        this.score = this.answerSheetItems.stream().mapToInt(AnswerSheetItem::getScore).sum();
    }

    @Override
    public boolean sameIdentityAs(AnswerSheet other) {
        return this.answerSheetId.sameValueAs(other.answerSheetId);
    }

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
}
