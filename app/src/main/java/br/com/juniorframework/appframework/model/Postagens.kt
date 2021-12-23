package br.com.juniorframework.appframework.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Postagens {

    @PrimaryKey
    var id: Int = 0

    var userId: Int = 0

    lateinit var title: String

    lateinit var body: String
}