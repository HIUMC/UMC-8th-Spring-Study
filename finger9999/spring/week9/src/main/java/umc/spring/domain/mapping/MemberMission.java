package umc.spring.domain.mapping;
import jakarta.persistence.*;
import lombok.*;
import jakarta.persistence.Id;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Fetch;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.common.BaseEntity;
import umc.spring.domain.enums.MissionStatus;

@Entity
@DynamicUpdate
@DynamicInsert
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberMission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private MissionStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private Mission mission;

    public void setId(Long id) {
        this.id = id;
    }

    public void setStatus(MissionStatus status) {
        this.status = status;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public void setMission(Mission mission) {
        this.mission = mission;
    }
}
