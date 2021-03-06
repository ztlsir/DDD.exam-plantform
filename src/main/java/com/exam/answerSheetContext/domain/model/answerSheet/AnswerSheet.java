package com.exam.answerSheetContext.domain.model.answerSheet;

import com.exam.answerSheetContext.domain.model.answerSheet.exception.IllegalAnswerSheetItemNumberException;
import com.exam.answerSheetContext.domain.model.answerSheet.exception.IllegalAnswerSheetItemsCountException;
import com.exam.answerSheetContext.domain.model.answerSheet.exception.IllegalQuizzesCountException;
import com.exam.answerSheetContext.domain.model.answerSheet.exception.IllegalScoreException;
import com.exam.shared.Entity;
import lombok.EqualsAndHashCode;

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
                            && blankQuiz.getReferenceAnswer().equals(answerSheetItem.getAnswer()))
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

    public int getScore() {
        return this.score;
    }
}
