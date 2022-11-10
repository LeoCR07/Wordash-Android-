package com.example.betadiccompose.Domain

import android.provider.ContactsContract.Data
import com.example.betadiccompose.data.network.model.DataCategory
import com.example.betadiccompose.data.network.model.DataVocabulary
import com.example.betadiccompose.data.network.model.QuoteMode
import com.example.betadiccompose.data.repository.CategoryRespository
import javax.inject.Inject

class GetCategorys @Inject constructor(private val resposity:CategoryRespository){

    //private val resposity = CategoryRespository()
    suspend operator fun invoke ():List<DataVocabulary>? = resposity.getAllCategorys()

}