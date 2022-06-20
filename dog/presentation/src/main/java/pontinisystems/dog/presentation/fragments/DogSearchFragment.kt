package pontinisystems.dog.presentation.fragments

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import pontinisystems.dog.presentation.viewaction.DogsAction
import org.koin.core.context.GlobalContext.loadKoinModules
import org.koin.core.context.GlobalContext.unloadKoinModules
import pontinisystems.dog.presentation.adapters.DogsAdapter
import pontinisystems.dog.presentation.viewstate.DogsViewState
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import pontinisystems.dog.presentation.R
import pontinisystems.dog.presentation.activity.DogDetailsActivity
import pontinisystems.dog.presentation.databinding.DogSearchFragmentBinding
import pontinisystems.dog.presentation.di.presentationModuleDependency
import pontinisystems.dog.presentation.entities.DogUI
import pontinisystems.dog.presentation.viewmodel.DogsViewModel

class DogSearchFragment : Fragment(), SearchView.OnQueryTextListener {

    companion object {
        const val TIME = 700L
        const val ROW_COUNT = 3
        const val BUNDLE_DOG = "BUNDLE_DOG"
    }

    private val viewViewModel: DogsViewModel by sharedViewModel()
    private fun inject() = loadModules
    private lateinit var binding: DogSearchFragmentBinding
    private val adapter by lazy { DogsAdapter(viewViewModel) }
    private val mHandler = Handler()
    private var isCreatedView = true

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
    ) = DogSearchFragmentBinding.inflate(inflater, container, false).apply {

        setupView()
        observeChanges()
    }.root


    private fun observeChanges() {
        viewViewModel.viewState.state.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is DogsViewState.State.Success -> onSuccess(state.dogs, state.isUpdate)
                is DogsViewState.State.Error -> onError(state.message)
                DogsViewState.State.Loading -> onLoading()
                DogsViewState.State.InputSearch -> onShowInputSearch()
            }
        })

        viewViewModel.viewState.action.observe(viewLifecycleOwner, Observer { action ->
            when (action) {
                is DogsViewState.Action.OpenDetails -> {
                    val temp = Intent(activity, DogDetailsActivity::class.java)
                    temp.putExtra(BUNDLE_DOG, action.data)
                    startActivity(temp)
                }
                is DogsViewState.Action.ChangeVisualization -> {
                    when (action.visualization) {
                        DogsViewState.Visualization.GRID -> {
                            binding.btChangeVisualizer.setImageResource(R.drawable.ic_baseline_grid_on_24)
                            setLayoutManagerGrid()
                        }
                        DogsViewState.Visualization.LIST -> {
                            binding.btChangeVisualizer.setImageResource(R.drawable.ic_baseline_format_list_bulleted_24)
                            setLayoutManagerList()
                        }
                    }
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

    private fun onSuccess(dogs: List<DogUI>, isUpdate: Boolean) {
        //TODO processing can be done in  here .kt and databinding

        adapter.setData(dogs, isUpdate, )
    }

    private fun DogSearchFragmentBinding.setupView() {
        binding = this
        lifecycleOwner = viewLifecycleOwner
        viewState = viewViewModel.viewState
        binding.dogRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    viewViewModel.dispatchViewAction(DogsAction.LoadMore)
                }
            }
        })

        binding.btChangeVisualizer.setOnClickListener {
            viewViewModel.dispatchViewAction(DogsAction.ChangeVisualization)
        }
    }

    private fun setLayoutManagerList() {
        binding.dogRecyclerView.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupInitialData()
    }

    private fun setupInitialData() {
        initAdapter()
        initSearchView()
        viewViewModel.dispatchViewAction(DogsAction.Init)
    }

    private fun initSearchView() {
        binding.searchView.setOnQueryTextListener(this)
        binding.searchView.setQuery(viewViewModel.viewState.textSearch, false)

    }

    private fun initAdapter() {
        if (!adapter.hasObservers()) {
            adapter.setHasStableIds(true)
        }
        binding.dogRecyclerView.adapter = adapter

        viewViewModel.viewState.visualization.let {
            if (it == DogsViewState.Visualization.GRID) {
                setLayoutManagerGrid()
            } else {
                setLayoutManagerList()
            }
        }

    }

    private fun setLayoutManagerGrid() {
        binding.dogRecyclerView.layoutManager =
            GridLayoutManager(this.context, ROW_COUNT, GridLayoutManager.VERTICAL, false)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        viewViewModel.dispatchViewAction(DogsAction.Search(query))
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (isCreatedView.not()) {
            mHandler.removeCallbacksAndMessages(null)
            mHandler.postDelayed({
                viewViewModel.dispatchViewAction(DogsAction.Search(newText))
            }, TIME)
            return true
        }
        isCreatedView = isCreatedView.not()
        return false
    }

}