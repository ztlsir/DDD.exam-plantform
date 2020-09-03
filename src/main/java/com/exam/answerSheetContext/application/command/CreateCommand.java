package com.exam.answerSheetContext.application.command;

import com.exam.answerSheetContext.domain.model.answerSheet.AnswerSheet;
import com.exam.answerSheetContext.domain.model.answerSheet.AnswerSheetId;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateCommand {
    private AnswerSheetId answerSheetId;
    private String studentId;
    private String examinationId;
    private List<AnswerSheet.BlankQuiz> blankQuizzes;
    private List<AnswerSheet.AnswerSheetItem> answerSheetItems;

}
