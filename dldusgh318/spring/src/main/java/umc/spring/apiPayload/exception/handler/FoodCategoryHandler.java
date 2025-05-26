package umc.spring.apiPayload.exception.handler;

import umc.spring.apiPayload.code.BaseErrorCode;
import umc.spring.apiPayload.code.status.ErrorStatus;

public class FoodCategoryHandler extends RuntimeException {

    public FoodCategoryHandler(BaseErrorCode errorCode) {
        super(errorCode.getReason().toString());
    }
}
