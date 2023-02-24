package com.example.betadiccompose.data.local_database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.betadiccompose.data.network_database.model.DataVocabulary
import com.example.betadiccompose.data.network_database.model.DataWorld

@Entity(tableName ="vocabulary_table")
data class VocabularyEntity(

    @PrimaryKey()
    @ColumnInfo(name = "id") val id:Int,
    @ColumnInfo(name = "name") val name:String,
    @ColumnInfo(name = "path") val path:String,
    @ColumnInfo(name = "sub") val sub:Boolean,
    @ColumnInfo(name = "Img") val img:String,
    @ColumnInfo(name = "document") val document:Int,)

fun DataVocabulary.toDatabase() = VocabularyEntity(id = id,name=name,path=path,sub=sub, img = img,document= document)