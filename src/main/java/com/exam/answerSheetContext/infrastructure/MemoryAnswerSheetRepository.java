package com.exam.answerSheetContext.infrastructure;

import com.exam.answerSheetContext.domain.model.answerSheet.AnswerSheet;
import com.exam.answerSheetContext.domain.model.answerSheet.AnswerSheetId;
import com.exam.answerSheetContext.domain.model.answerSheet.AnswerSheetRepository;
import com.exam.answerSheetContext.domain.model.answerSheet.exception.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MemoryAnswerSheetRepository implements AnswerSheetRepository {
    private List<AnswerSheet> list;

    @Override
    public AnswerSheet find(AnswerSheetId answerSheetId) {
        return this.list.stream()
                .filter(answerSheet -> answerSheet.getId().sameValueAs(answerSheetId))
                .findFirst()
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void save(AnswerSheet answerSheet) {
        if (this.list.stream().anyMatch(answerSheetOld -> answerSheetOld.sameIdentityAs(answerSheet))) {
            this.list.removeIf(answerSheetOld->answerSheetOld.sameIdentityAs(answerSheet));
        }

        this.list.add(answerSheet);
    }

    @Override
    public List<AnswerSheet> getAll() {
        return this.list;
    }
}
