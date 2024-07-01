package umc.spring.study.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.study.apiPayload.code.status.ErrorStatus;
import umc.spring.study.repository.MemberMissionRepository;
import umc.spring.study.validation.annotation.UniqueMemberMission;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UniqueMemberMissionValidator implements ConstraintValidator<UniqueMemberMission, List<Long>> {

    private final MemberMissionRepository memberMissionRepository;

    @Override
    public void initialize(UniqueMemberMission constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(List<Long> values, ConstraintValidatorContext context) {
        boolean isValid = values.stream()
                .allMatch(value -> !memberMissionRepository.existsById(value));

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.MEMBER_MISSION_ALREADY_EXISTS.toString()).addConstraintViolation();
        }

        return isValid;
    }
}