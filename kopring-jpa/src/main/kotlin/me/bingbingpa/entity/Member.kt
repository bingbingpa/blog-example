package me.bingbingpa.entity

import javax.persistence.*

@Entity
class Member(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val username: String,
    val age: Int,
) {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teamId")
    lateinit var team: Team
}
