package pontinisystems.dog.presentation.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import pontinisystems.dog.presentation.viewaction.DogsAction
import org.koin.core.context.GlobalContext.loadKoinModules
import org.koin.core.context.GlobalContext.unloadKoinModules
import pontinisystems.dog.presentation.adapters.DogsAdapter
import android.view.*
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import pontinisystems.dog.presentation.activity.DogDetailsActivity
import pontinisystems.dog.presentation.databinding.DogListFragmentBinding
import pontinisystems.dog.presentation.di.presentationModuleDependency
import pontinisystems.dog.presentation.entities.DogUI
import pontinisystems.dog.presentation.viewmodel.DogsViewModel
import pontinisystems.dog.presentation.viewstate.DogsViewState


class DogListFragment : Fragment() {

    companion object {
        const val ROW_COUNT = 3
        const val BUNDLE_DOG = "BUNDLE_DOG"
    }

    private fun inject() = loadModules
    private lateinit var binding: DogListFragmentBinding
    private val viewModel: DogsViewModel by sharedViewModel()
    private val adapter by lazy { DogsAdapter(viewModel) }

    private val loadModules by lazy {
        unloadKoinModules(presentationModuleDependency)
        loadKoinModules(presentationModuleDependency)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = DogListFragmentBinding.inflate(inflater, container, false).apply {

        setupView()
        observeChanges()

    }.root


    private fun observeChanges() {
        viewModel.viewState.state.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is DogsViewState.State.Success -> onSuccess(state.dogs, state.isUpdate)
                is DogsViewState.State.Error -> onError(state.message)
                is DogsViewState.State.Loading -> onLoading()
                is DogsViewState.State.InputSearch -> onShowInputSearch()
            }
        })

        viewModel.viewState.action.observe(viewLifecycleOwner, Observer { action ->
            when (action) {
                is DogsViewState.Action.OpenDetails -> {
                    val temp = Intent(activity, DogDetailsActivity::class.java)
                    temp.putExtra(BUNDLE_DOG, action.data)
                    startActivity(temp)
                }
            }
        })
    }

    private fun onShowInputSearch() {
        //TODO processing can be done in  here .kt and databinding
    }

    private fun onError(message: String) {
        //TODO processing can be done in  here .kt and databinding
        Log.i("ERROR--->", "ERROR---> $message")
    }

    private fun onLoading() {
        //TODO processing can be done in  here .kt and databinding
    }

    private fun onSuccess(dogs: List<DogUI>,  isUpdate: Boolean) {
        //TODO processing can be done in  here .kt and databinding
        adapter.setData(dogs, isUpdate, )
    }

    private fun DogListFragmentBinding.setupView() {
        binding = this
        lifecycleOwner = viewLifecycleOwner
        viewState = viewModel.viewState
        binding.dogRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    viewModel.dispatchViewAction(DogsAction.LoadMore)
                }
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupInitialData()
    }

    private fun setupInitialData() {
        initAdapter()
        viewModel.dispatchViewAction(DogsAction.Init)
    }

    private fun initAdapter() {
        if (!adapter.hasObservers()) {
            adapter.setHasStableIds(true)
        }
        binding.dogRecyclerView.adapter = adapter
        binding.dogRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
    }
}