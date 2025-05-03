package umc.spring.repository.StoreRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.spring.domain.QStore;
import umc.spring.domain.Store;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class StoreRepositoryImpl implements StoreRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;
    private final QStore qStore=QStore.store;

    @Override
    public List<Store> dynamicQueryWithBooleanBuilder(String name, Float rating){
        BooleanBuilder predicate=new BooleanBuilder();

        if(name != null){
            predicate.and(qStore.name.eq(name));
        }

        if(rating != null){
            predicate.and(qStore.rating.goe(4.0f));
        }

        return jpaQueryFactory
                .selectFrom(qStore)
                .where(predicate)
                .fetch();
    }
}
