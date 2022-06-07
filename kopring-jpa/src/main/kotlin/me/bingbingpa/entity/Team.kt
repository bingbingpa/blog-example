package me.bingbingpa.entity

import javax.persistence.*

@Entity
class Team(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    val id: Long = 0,
    val name: String,
) {
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "team")
    val members : List<Member> = listOf()

    val test1: Int = 10
        get() = if (field > 0) field else 10

    val test2: Int = 0

    val test3: Int
        get() = 30
}
