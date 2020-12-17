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
import com.bitamirshafiee.challengeinterview.databinding.FragmentQuestionBinding
import com.bitamirshafiee.challengeinterview.di.search.SearchModule
import javax.inject.Inject

class SearchFragment : Fragment() {

    private var _binding : FragmentQuestionBinding? = null
    private val binding get() = _binding

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

        _binding = FragmentQuestionBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.button?.setOnClickListener {
            viewModel.search(binding?.editTextSearch?.text.toString())
        }

        viewModel.getShowList().observe(viewLifecycleOwner, {
            adapter = QuestionAdapter(it) {
                Toast.makeText(
                    activity,
                    resources.getString(R.string.str_clicked),
                    Toast.LENGTH_SHORT
                ).show()
            }
            binding?.recyclerView?.adapter = adapter
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        @JvmStatic
        fun newInstance() = SearchFragment()
    }

}