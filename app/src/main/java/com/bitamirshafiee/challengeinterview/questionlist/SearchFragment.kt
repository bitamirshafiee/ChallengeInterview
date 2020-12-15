package com.bitamirshafiee.challengeinterview.questionlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bitamirshafiee.challengeinterview.MainActivity
import com.bitamirshafiee.challengeinterview.R
import com.bitamirshafiee.challengeinterview.di.search.SearchModule
import kotlinx.android.synthetic.main.fragment_question.*
import javax.inject.Inject

class SearchFragment : Fragment() {

    @Inject
    lateinit var factory: QuestionListViewModelImpl.Factory

    private val viewModel: QuestionListViewModel by viewModels { factory }

    private var adapter: QuestionAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (activity as MainActivity).getNetworkingComponent()
            .provideSearchComponent(SearchModule())
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_question, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button.setOnClickListener {
            viewModel.search(editTextSearch.text.toString())
        }

        viewModel.getShowList().observe(viewLifecycleOwner, {
            adapter = QuestionAdapter(it) {
                Toast.makeText(
                    activity,
                    resources.getString(R.string.str_clicked),
                    Toast.LENGTH_SHORT
                ).show()
            }
            recyclerView.adapter = adapter
        })
    }

    companion object {

        @JvmStatic
        fun newInstance() = SearchFragment()
    }

}