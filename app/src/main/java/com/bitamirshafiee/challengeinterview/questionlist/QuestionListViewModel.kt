package com.bitamirshafiee.challengeinterview.questionlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bitamirshafiee.challengeinterview.common.SingleLiveEvent
import com.bitamirshafiee.challengeinterview.common.helper.ImageLoader
import com.bitamirshafiee.challengeinterview.data.Question
import com.bitamirshafiee.challengeinterview.repository.SearchUseCase
import io.reactivex.rxjava3.disposables.Disposable

abstract class QuestionListViewModel : ViewModel() {

    private val showList: SingleLiveEvent<List<Question>> = SingleLiveEvent()
    fun getShowList(): SingleLiveEvent<List<Question>> = showList

    private val disposables = mutableListOf<Disposable>()

    abstract fun search(tag: String)
    fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.forEach { it.dispose() }
    }
}

class QuestionListViewModelImpl(
    private val searchUseCase: SearchUseCase,
    private val imageLoader: ImageLoader
) : QuestionListViewModel() {
    override fun search(tag: String) {
        addDisposable(
            searchUseCase.search("android", 1, 15).subscribe({
                getShowList().postValue(it.items)
            }, {
                it
            })
        )
    }

}