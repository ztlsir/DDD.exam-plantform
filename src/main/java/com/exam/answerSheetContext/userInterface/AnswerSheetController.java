package com.exam.answerSheetContext.userInterface;

import com.exam.answerSheetContext.application.AnswerSheetApplicationService;
import com.exam.answerSheetContext.application.command.CreateCommand;
import com.exam.answerSheetContext.application.command.HandInCommand;
import com.exam.answerSheetContext.application.command.SubmitCommand;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/answer-sheets")
public class AnswerSheetController {
    private AnswerSheetApplicationService applicationService;

    public AnswerSheetController(AnswerSheetApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping("/assign")
    public void assign(@RequestBody CreateCommand createCommand) {
        this.applicationService.assign(createCommand);
    }

    @PutMapping("/submit")
    public void submit(@RequestBody SubmitCommand submitCommand) {
        this.applicationService.submit(submitCommand);
    }

    @PutMapping("/hand-in")
    public void handIn(@RequestBody HandInCommand handInCommand) {
        this.applicationService.handIn(handInCommand);
    }
}
