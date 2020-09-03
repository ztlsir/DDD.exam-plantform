package com.exam.answerSheetContext.application.command;

import com.exam.answerSheetContext.domain.model.answerSheet.AnswerSheetId;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubmitCommand {
    private AnswerSheetId answerSheetId;
    private int number;
    private String answer;
}