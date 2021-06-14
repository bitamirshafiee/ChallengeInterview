package com.bitamirshafiee.challengeinterview.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bitamirshafiee.challengeinterview.common.helper.ImageLoader
import com.bitamirshafiee.challengeinterview.questionlist.QuestionListViewModel
import com.bitamirshafiee.challengeinterview.questionlist.QuestionListViewModelImpl
import com.bitamirshafiee.challengeinterview.repository.SearchUseCase
import java.lang.IllegalArgumentException
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class ViewModelFactory @Inject constructor(private val searchUseCase: SearchUseCase, private val imageLoader: ImageLoader) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>) = with(modelClass) {

        when {
            isAssignableFrom(QuestionListViewModel::class.java) -> QuestionListViewModelImpl(
                searchUseCase, imageLoader
            )
            else -> throw IllegalArgumentException("class $modelClass is not Expected")
        }
    } as T
}