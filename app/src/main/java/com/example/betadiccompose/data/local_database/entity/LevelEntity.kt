package com.example.betadiccompose.data.local_database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.betadiccompose.data.network_database.model.DataNiveles

@Entity(tableName ="level_table")
data class LevelEntity(
    @PrimaryKey()
    @ColumnInfo(name = "id") val id:Int,
    @ColumnInfo(name = "cats") val doc:String,
    @ColumnInfo(name = "name") val name:String,
    @ColumnInfo(name = "animation") val animation:String,
    @ColumnInfo(name = "stars") val stars:Int,
    @ColumnInfo(name = "dir") val dir:String,)

fun DataNiveles.toDatabase() = LevelEntity(
    id = id,
    dir= dir,
    name=name,
    animation= animation,
    stars=stars,
    doc = doc,
 )