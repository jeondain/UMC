package umc.spring.study.converter;

import org.springframework.data.domain.Page;
import umc.spring.study.domain.Member;
import umc.spring.study.domain.Review;
import umc.spring.study.domain.enums.Gender;
import umc.spring.study.web.dto.MemberRequestDTO;
import umc.spring.study.web.dto.MemberResponseDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MemberConverter {

    public static MemberResponseDTO.JoinResultDTO toJoinResultDTO(Member member){
        return MemberResponseDTO.JoinResultDTO.builder()
                .memberId(member.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Member toMember(MemberRequestDTO.JoinDto request){

        Gender gender = null;

        switch (request.getGender()){
            case 1:
                gender = Gender.MALE;
                break;
            case 2:
                gender = Gender.FEMALE;
                break;
            case 3:
                gender = Gender.NONE;
                break;
        }

        return Member.builder()
                .address(request.getAddress())
                .specAddress(request.getSpecAddress())
                .gender(gender)
                .name(request.getName())
                .memberPreferList(new ArrayList<>())
                .memberMissionList(new ArrayList<>())
                .build();
    }

    public static MemberResponseDTO.MyReviewPreViewDTO myReviewPreViewDTO(Review review) {
        return MemberResponseDTO.MyReviewPreViewDTO.builder()
                .ownerNickname(review.getMember().getName())
                .score(review.getScore())
                .createdAt(review.getCreatedAt().toLocalDate())
                .body(review.getBody())
                .build();
    }

    public static MemberResponseDTO.MyReviewPreViewListDTO myReviewPreViewListDTO(Page<Review> reviewList) {

        List<MemberResponseDTO.MyReviewPreViewDTO> myReviewPreViewDTOList = reviewList.stream()
                .map(MemberConverter::myReviewPreViewDTO).collect(Collectors.toList());

        return MemberResponseDTO.MyReviewPreViewListDTO.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(myReviewPreViewDTOList.size())
                .reviewList(myReviewPreViewDTOList)
                .build();
    }
}