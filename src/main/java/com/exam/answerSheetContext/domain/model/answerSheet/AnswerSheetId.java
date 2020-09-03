package com.exam.answerSheetContext.domain.model.answerSheet;

import com.exam.shared.ValueObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnswerSheetId implements ValueObject<AnswerSheetId> {
    private String id;

    public static AnswerSheetId ceate()
    {
        return new AnswerSheetId(UUID.randomUUID().toString().replace("-",""));
    }

    @Override
    public boolean sameValueAs(AnswerSheetId other) {
        return equals(other);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnswerSheetId paperId = (AnswerSheetId) o;
        return Objects.equals(id, paperId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return id;
    }
}
