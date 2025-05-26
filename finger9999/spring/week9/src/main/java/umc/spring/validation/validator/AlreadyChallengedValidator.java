package umc.spring.validation.validator;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.repository.MemberMissionRepository.MemberMissionRepository;
import umc.spring.validation.annotation.AlreadyChallenged;

@Component
@RequiredArgsConstructor
public class AlreadyChallengedValidator implements ConstraintValidator<AlreadyChallenged, Long> {

    private final MemberMissionRepository memberMissionRepository;
    private final HttpServletRequest request; // missionId 추출용

    @Override
    public boolean isValid(Long memberId, ConstraintValidatorContext context) {
        String uri = request.getRequestURI(); // e.g., /missions/5/challenge
        Long missionId = extractMissionId(uri);

        boolean exists = memberMissionRepository.existsByMemberIdAndMissionId(memberId, missionId);

        if (exists) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("이미 도전 중인 미션입니다.").addConstraintViolation();
        }

        return !exists;
    }

    private Long extractMissionId(String uri) {
        try {
            String[] parts = uri.split("/");
            return Long.parseLong(parts[2]);
        } catch (Exception e) {
            return -1L;
        }
    }
}

