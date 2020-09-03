package com.exam.answerSheetContext.application.command;

import com.exam.answerSheetContext.domain.model.answerSheet.AnswerSheetId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SubmitCommand {
    private AnswerSheetId answerSheetId;
    private int number;
    private String answer;
}
