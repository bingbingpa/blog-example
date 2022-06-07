package me.bingbingpa.repository

import me.bingbingpa.entity.Member
import me.bingbingpa.entity.Member2
import me.bingbingpa.entity.Team
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class JpaTest {

    @Autowired
    private lateinit var memberRepository: MemberRepository
    @Autowired
    private lateinit var memberRepository2: MemberRepository2
    @Autowired
    private lateinit var teamRepository: TeamRepository

    @Test
    fun `객체 생성시 ID 를 null 로 쓸까 0으로 초기화 할까?`() {
        val member1 = memberRepository.save(Member(username = "username", age = 20))
        val member2 = memberRepository.save(Member(username = "username", age = 20))
        println("member1.id = ${member1.id}")
        println("member2.id = ${member2.id}")
    }

    @Test
    fun `create-auto 로 생성시 인식되지 않는 엔티티의 프로퍼티는 디비에 일치하는 컬럼이 있으면 읽을 수 있을까?`() {
        val team = teamRepository.findById(1).orElseThrow()
        println("team.test3 = ${team.test3}")
    }

    @Test
    fun `연관관계로 insert 할때와 fk id 로 insert 할 때 차이가 있을까?`() {
        val member = Member(username = "test", age = 33).apply { team = Team(1, "aaa") }
        memberRepository.save(member)
        memberRepository2.save(Member2(teamId = 1L, username = "test", age = 33))
    }
}