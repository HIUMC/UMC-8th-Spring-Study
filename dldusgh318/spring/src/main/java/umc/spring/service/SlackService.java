package umc.spring.service;

import com.slack.api.Slack;
import com.slack.api.model.Attachment;
import com.slack.api.model.Field;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import umc.spring.apiPayload.exception.GeneralException;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.slack.api.webhook.WebhookPayloads.payload;

@Slf4j
@Component
public class SlackService {

    private final Slack slack=Slack.getInstance();

    @Value("${slack.webhook.url}")
    private String webhookUrl;

    public void sendSlack(GeneralException e, HttpServletRequest request){
        try{
            slack.send(webhookUrl, payload(p->p
                    .text("서버에 에러가 감지되었습니다. 확인 필요")
                    .attachments(List.of(generateSlackAttachment(e,request))
                    )
            ));
        }catch (IOException ex){
            log.debug("슬랙 통신 과정에 예외 발생");
        }
    }

    private Attachment generateSlackAttachment(GeneralException e, HttpServletRequest request){
        String requestTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS").format(LocalDateTime.now());
        String xffHeader = request.getHeader("X-FORWARDED-FOR");
        return Attachment.builder()
                .color("ff0000")
                .title(requestTime + " 발생 에러 로그")
                .fields(List.of(
                                generateSlackField("Request IP", xffHeader == null ? request.getRemoteAddr() : xffHeader),
                                generateSlackField("Request URL", request.getRequestURL() + " " + request.getMethod()),
                                generateSlackField("Error Code", e.getCode().getReasonHttpStatus().toString()),
                                generateSlackField("Error Message", e.getCode().getReason().toString())
                        )
                )
                .build();
    }

    private Field generateSlackField(String title, String value) {
        return Field.builder()
                .title(title)
                .value(value)
                .valueShortEnough(false)
                .build();
    }
}
