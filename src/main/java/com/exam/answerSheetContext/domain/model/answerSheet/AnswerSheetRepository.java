package com.exam.answerSheetContext.domain.model.answerSheet;

import java.util.List;

public interface AnswerSheetRepository {
    AnswerSheet find(AnswerSheetId answerSheetId);

    void save(AnswerSheet answerSheet);

    List<AnswerSheet> getAll();
}
