package umc.spring.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.service.TempQueryService;
import umc.spring.web.converter.TempConverter;
import umc.spring.web.dto.TempResponse;

@RestController
@RequestMapping("/temp")
@RequiredArgsConstructor
public class TemptRestController {
    private final TempQueryService tempQueryService;


    @GetMapping("/test")
    public ApiResponse<TempResponse.TempTestDTO> testAPI(){
        return ApiResponse.onSuccess(TempConverter.toTempTestDTO());
    }

    @GetMapping("/exception")
    public ApiResponse<TempResponse.TempExceptionDTO> exceptionAPI(@RequestParam("flag") Integer flag){
        tempQueryService.CheckFlag(flag);
        return ApiResponse.onSuccess(TempConverter.toTempExceptionDTO(flag));
    }

    @GetMapping("/test-error")
    public void testError() {
        throw new RuntimeException("테스트용 예외 발생");
    }
}
