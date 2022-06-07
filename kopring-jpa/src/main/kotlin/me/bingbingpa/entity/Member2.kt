package me.bingbingpa.entity

import javax.persistence.*

@Entity
class Member2(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val teamId: Long,
    val username: String,
    val age: Int,
) {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teamId", insertable = false, updatable = false)
    lateinit var team: Team
}
