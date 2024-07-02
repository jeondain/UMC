package umc.spring.study.apiPayload.code.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.spring.study.apiPayload.code.BaseErrorCode;
import umc.spring.study.apiPayload.code.ErrorReasonDTO;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

    // 가장 일반적인 응답
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST,"COMMON400","잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"COMMON401","인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),


    // 멤버 관려 에러
    MEMBER_NOT_FOUND(HttpStatus.BAD_REQUEST, "MEMBER4001", "사용자가 없습니다."),
    NICKNAME_NOT_EXIST(HttpStatus.BAD_REQUEST, "MEMBER4002", "닉네임은 필수 입니다."),

    // 예시,,,
    ARTICLE_NOT_FOUND(HttpStatus.NOT_FOUND, "ARTICLE4001", "게시글이 없습니다."),
    TEMP_EXCEPTION(HttpStatus.BAD_REQUEST, "TEMP100", "임시 예외 발생"),

    // FoodCategory Error
    FOOD_CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "FOOD_CATEGORY4001", "음식 카테고리가 없습니다."),

    // MemberMission Error
    MEMBER_MISSION_ALREADY_EXISTS(HttpStatus.CONFLICT, "MEMBER_MISSION_4001", "해당 미션이 이미 멤버에게 할당되어 있습니다."),

    //Mission Error
    MISSION_NOT_FOUND(HttpStatus.CONFLICT, "MISSION_4001", "해당하는 미션이 존재하지 않습니다."),

    // Store Error
    REGION_NOT_FOUND(HttpStatus.NOT_FOUND, "STORE4001", "해당하는 지역이 존재하지 않습니다."),
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "STORE_4002","해당하는 가게가 없습니다."),

    // Page Error
    PAGE_NOT_VALID(HttpStatus.BAD_REQUEST, "PAGE_4001","해당하는 페이지 번호가 존재하지 않습니다.");


    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build()
                ;
    }
}