package umc.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.constraintvalidation.SupportedValidationTarget;
import jakarta.validation.constraintvalidation.ValidationTarget;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.repository.MemberMissionRepository.MemberMissionRepository;
import umc.spring.validation.annotation.IsNotChallengedMission;

@Component
@RequiredArgsConstructor
@SupportedValidationTarget(ValidationTarget.PARAMETERS)
public class NotChallengedMissionValidator implements ConstraintValidator<IsNotChallengedMission, Object[]> {
    private final MemberMissionRepository memberMissionRepository;

    @Override
    public boolean isValid(Object[] parameters, ConstraintValidatorContext context) {
        if (parameters == null || parameters.length < 2) {
            return true;
        }
        Long memberId  = (Long) parameters[0];
        Long missionId = (Long) parameters[1];

        return !memberMissionRepository.existsByMemberIdAndMissionId(memberId, missionId);
    }
}
