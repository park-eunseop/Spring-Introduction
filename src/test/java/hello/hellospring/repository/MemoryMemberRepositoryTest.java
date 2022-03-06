package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 순서는 보장이 안된다.
 * 순서에 의존적으로 설계하면 절대 안된다.
 *
 * 그래서 테스트가 하나 끝나면 데이터를 클리어 시켜줘야한다.
 *
 */
class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    /**
     * 메서드가 끝날때마다 동작하는 메서드
     */
    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){

        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result =  repository.findById(member.getId()).get();


        Assertions.assertEquals(member,result);
        org.assertj.core.api.Assertions.assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("Spring1");
        repository.save(member1);


        /**
         * shift + F6  >> 변수 한번에 rename
         */
        Member member2 = new Member();
        member2.setName("Spring2");
        repository.save(member2);

        Member result = repository.findByName("Spring2").get();

        org.assertj.core.api.Assertions.assertThat(result).isEqualTo(member2);

    }

    @Test
    public void findAll(){
        //리스트 사이즈 비교

    }

}
