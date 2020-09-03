package com.exam.answerSheetContext.userInterface;

import com.exam.BaseApiTest;
import com.exam.answerSheetContext.application.command.CreateCommand;
import com.exam.answerSheetContext.application.command.HandInCommand;
import com.exam.answerSheetContext.application.command.SubmitCommand;
import com.exam.answerSheetContext.domain.model.answerSheet.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AnswerSheetControllerTest extends BaseApiTest {
    @Autowired
    private AnswerSheetRepository answerSheetRepository;

    @Test
    void should_get_score_when_hand_in() {
        AnswerSheetId answerSheetId = AnswerSheetId.ceate();
        given()
                .contentType("application/json")
                .body(new CreateCommand(answerSheetId, "test_Student_id", "test_exam_id",
                        Arrays.asList(new BlankQuiz(1, "", "test_answet1", 10),
                                new BlankQuiz(2, "", "test_answet2", 20),
                                new BlankQuiz(3, "", "test_answet3", 30),
                                new BlankQuiz(4, "", "test_answet4", 20),
                                new BlankQuiz(5, "", "test_answet5", 20)),
                        Arrays.asList(new AnswerSheetItem(1, null, 0),
                                new AnswerSheetItem(2, null, 0),
                                new AnswerSheetItem(3, null, 0),
                                new AnswerSheetItem(4, null, 0),
                                new AnswerSheetItem(5, null, 0))))
                .when()
                .post("/answer-sheets/assign")
                .then().statusCode(200);
        given()
                .contentType("application/json")
                .body(new SubmitCommand(answerSheetId, 1, "test_answet1"))
                .when()
                .put("/answer-sheets/submit")
                .then().statusCode(200);
        given()
                .contentType("application/json")
                .body(new SubmitCommand(answerSheetId, 3, "test_answet3"))
                .when()
                .put("/answer-sheets/submit")
                .then().statusCode(200);

        given()
                .contentType("application/json")
                .body(new HandInCommand(answerSheetId))
                .when()
                .put("/answer-sheets//hand-in")
                .then().statusCode(200);

        AnswerSheet answerSheet = answerSheetRepository.find(answerSheetId);
        assertEquals(40, answerSheet.getScore());
    }
}
