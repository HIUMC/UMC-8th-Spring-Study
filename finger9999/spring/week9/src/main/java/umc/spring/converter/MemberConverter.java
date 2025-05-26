package umc.spring.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.domain.enums.Gender;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.web.dto.Member.MemberRequestDTO;
import umc.spring.web.dto.Member.MemberResponseDTO;
import umc.spring.web.dto.MemberMission.MemberMissionResponseDTO;

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
                .build();
    }

    public static MemberResponseDTO.MyReviewPreViewDTO reviewPreViewDTO(Review review){
        return MemberResponseDTO.MyReviewPreViewDTO.builder()
                .storeName(review.getStore().getName())
                .score(review.getScore())
                .createdAt(review.getCreatedAt() != null ? review.getCreatedAt().toLocalDate() : null)
                .body(review.getBody())
                .build();
    }

    public static MemberResponseDTO.MyReviewPreViewListDTO reviewPreViewListDTO(Page<Review> myReviewList) {
        List<MemberResponseDTO.MyReviewPreViewDTO> myReviewPreViewDTOList = myReviewList.stream()
                .map(MemberConverter::reviewPreViewDTO).collect(Collectors.toList());

        return MemberResponseDTO.MyReviewPreViewListDTO.builder()
                .isLast(myReviewList.isLast())
                .isFirst(myReviewList.isFirst())
                .totalPage(myReviewList.getTotalPages())
                .totalElements(myReviewList.getTotalElements())
                .listSize(myReviewPreViewDTOList.size())
                .reviewList(myReviewPreViewDTOList)
                .build();
    }


}
