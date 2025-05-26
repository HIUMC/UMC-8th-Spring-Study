package umc.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.GeneralException;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.repository.MemberMissionRepository.MemberMissionRepository;
import umc.spring.repository.MemberRepository.MemberRepository;
import umc.spring.repository.MissionRepository.MissionRepository;
import umc.spring.repository.StoreRepository.StoreRepository;
import umc.spring.web.converter.MissionConverter;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.MissionResponseDTO;

import static umc.spring.domain.QMember.member;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;

    public void addMission(Long storeId, MissionRequestDTO.createMissionDTO request) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.STORE_NOT_FOUND));
        Mission mission = MissionConverter.toMission(request, store);
        missionRepository.save(mission);
    }

    public void challengeMission(Long missionId, Long memberId) {
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.MISSION_NOT_FOUND));

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.MEMBER_NOT_FOUND));

        MemberMission memberMission = MemberMission.builder()
                .member(member)
                .mission(mission)
                .status(MissionStatus.CHALLENGING)
                .build();

        memberMissionRepository.save(memberMission);
    }

    public MissionResponseDTO.MissionListDTO getStoreMissions(Long storeId, int pageZero) {

        Page<Mission> page = missionRepository.findAllByStoreId(
                storeId,
                PageRequest.of(pageZero, 10)
        );
        return MissionConverter.toMissionListDTO(page);
    }

    public MissionResponseDTO.MissionListDTO getMyChallengingMissions(Long memberId, int pageZero) {

        Page<MemberMission> page = memberMissionRepository.findAllByMemberIdAndStatus(
                memberId, MissionStatus.CHALLENGING,
                PageRequest.of(pageZero, 10)
        );

        Page<Mission> missionPage = page.map(MemberMission::getMission);
        return MissionConverter.toMissionListDTO(missionPage);
    }

    public MissionResponseDTO.MissionListDTO getStoreMissionsSlice(Long storeId, int pageZero) {
        Slice<Mission> slice = missionRepository.findSliceByStoreId(
                storeId,
                PageRequest.of(pageZero, 10)
        );

        Page<Mission> emulatedPage = new PageImpl<>(
                slice.getContent(),
                slice.getPageable(),
                slice.hasNext() ? (long) ((pageZero + 2) * slice.getSize()) : (long) ((pageZero + 1) * slice.getSize())
        );

        return MissionConverter.toMissionListDTO(emulatedPage);

    }
}
