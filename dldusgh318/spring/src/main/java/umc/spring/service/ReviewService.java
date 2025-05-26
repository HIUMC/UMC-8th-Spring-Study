package umc.spring.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.GeneralException;
import umc.spring.domain.Member;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.repository.MemberRepository.MemberRepository;
import umc.spring.repository.ReviewRepository.ReviewRepository;
import umc.spring.repository.StoreRepository.StoreRepository;
import umc.spring.web.converter.ReviewConverter;
import umc.spring.web.dto.ReviewRequestDTO;
import umc.spring.web.dto.ReviewResponseDTO;

import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReviewService {

    public final ReviewRepository reviewRepository;
    public final StoreRepository storeRepository;
    private final MemberRepository memberRepository;


    public Review createReview(Long storeId, ReviewRequestDTO.createReviewDTO request, Long memberId) {

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.STORE_NOT_FOUND));

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.MEMBER_NOT_FOUND));

        Review review = ReviewConverter.toEntity(request, store, member);
        return reviewRepository.save(review);
    }

    public ReviewResponseDTO.ReviewListDTO getMyReviews(Long memberId, Integer page) {
        Page<Review> reviewList = reviewRepository.findAllByMemberId(memberId, PageRequest.of(page, 10));

        return ReviewConverter.toReviewListDTO(reviewList);
    }
}
