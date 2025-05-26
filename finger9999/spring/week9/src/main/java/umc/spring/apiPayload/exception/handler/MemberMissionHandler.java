package umc.spring.apiPayload.exception.handler;

import umc.spring.apiPayload.code.BaseErrorCode;
import umc.spring.apiPayload.exception.GeneralException;

public class MemberMissionHandler extends GeneralException {
    public MemberMissionHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
