package me.bingbingpa.repository

import me.bingbingpa.entity.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long>