package com.exam.answerSheetContext.application.command;

import com.exam.answerSheetContext.domain.model.answerSheet.AnswerSheetId;
import com.exam.answerSheetContext.domain.model.answerSheet.AnswerSheetItem;
import com.exam.answerSheetContext.domain.model.answerSheet.BlankQuiz;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCommand {
    private AnswerSheetId answerSheetId;
    private String studentId;
    private String examinationId;
    private List<BlankQuiz> blankQuizzes;
    private List<AnswerSheetItem> answerSheetItems;
}
