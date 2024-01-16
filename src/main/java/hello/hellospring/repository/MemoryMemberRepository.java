package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

/*
동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */
public class MemoryMemberRepository implements MemberRepository {

    // save할 때, 저장할 곳
    private static Map<Long, Member> store = new HashMap<>();
    // sequence는 0,1,2..이렇게 key값을 생성
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        // store에 넣기 전 member의 Id 값을 세팅
        // save하기 전 이름(name)은 넘어온 상태라고 보면 됨
        member.setId(++sequence); // Id 세팅
        store.put(member.getId(), member); // store에 저장 -> Map에 저장됨
        return member; // 저장된 결과를 반환
    }

    @Override
    public Optional<Member> findById(Long id) {
        // store에서 꺼내기 위해 store에 id를 넣으면 됨
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        // 람다 사용
        // member.getName()이 파라미터로 넘어온 name과 같은지 확인
        // 같은 경우에만 필터링이 되고, 그 중에서 찾으면 반환
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
                // findAny() _ 하나라도 찾으면 결과가 Optional 반환, 루프를 끝까지 돌았는데 없으면 Optional에 null이 포함되어 반환
    }

    @Override
    public List<Member> findAll() {
        // 반환은 리스트로
        // store에 있는 values(member)가 반환됨.
        return new ArrayList<>(store.values());
    }

    // store를 비워주는 코드 (test 시에 사용)
    public void clearStore() {
        store.clear();
    }
}
