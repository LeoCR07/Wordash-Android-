package com.example.betadiccompose.data.network_database.model

import com.example.betadiccompose.data.local_database.entity.VocabularyEntity

data class DataVocabulary(val id:Int,
                          val name : String,
                          val img: String,
                          val path:String,
                          val sub: Boolean,
                          val document:Int )

fun VocabularyEntity.toDomain() = DataVocabulary(id = id,name=name,path=path,sub=sub, img = img,document= document)