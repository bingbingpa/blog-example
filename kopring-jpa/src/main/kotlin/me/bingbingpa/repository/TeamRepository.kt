package me.bingbingpa.repository

import me.bingbingpa.entity.Team
import org.springframework.data.jpa.repository.JpaRepository

interface TeamRepository : JpaRepository<Team, Long>