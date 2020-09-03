package com.exam.answerSheetContext.application;

import com.exam.answerSheetContext.application.command.CreateCommand;
import com.exam.answerSheetContext.application.command.HandInCommand;
import com.exam.answerSheetContext.application.command.SubmitCommand;
import com.exam.answerSheetContext.domain.model.answerSheet.AnswerSheet;
import com.exam.answerSheetContext.domain.model.answerSheet.AnswerSheetRepository;
import org.springframework.stereotype.Component;

@Component
public class AnswerSheetApplicationService {
    private AnswerSheetRepository repository;

    public AnswerSheetApplicationService(AnswerSheetRepository repository) {
        this.repository = repository;
    }

    public void assign(CreateCommand createCommand) {
        AnswerSheet answerSheet = AnswerSheet.assign(createCommand.getAnswerSheetId(), createCommand.getStudentId(),
                createCommand.getExaminationId(), createCommand.getBlankQuizzes(), createCommand.getAnswerSheetItems());

        this.repository.save(answerSheet);
    }

    public void submit(SubmitCommand submitCommand) {
        AnswerSheet answerSheet = this.repository.find(submitCommand.getAnswerSheetId());

        answerSheet.submit(submitCommand.getNumber(), submitCommand.getAnswer());

        this.repository.save(answerSheet);
    }

    public void handIn(HandInCommand handInCommand) {
        AnswerSheet answerSheet = this.repository.find(handInCommand.getAnswerSheetId());

        answerSheet.handIn();

        this.repository.save(answerSheet);
    }
}
