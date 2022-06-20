package pontinisystems.dog.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import pontinisystems.dog.domain.entities.Dog
import pontinisystems.dog.presentation.databinding.DogDetailsFragmentBinding
import pontinisystems.dog.presentation.di.presentationModuleDependency
import pontinisystems.dog.presentation.viewaction.DogDetailsAction
import pontinisystems.dog.presentation.viewmodel.DogDetailsViewModel
import pontinisystems.dog.presentation.viewstate.DogDetailsViewState

class DogDetailsFragment : Fragment() {

    private val viewViewModel: DogDetailsViewModel by viewModel()
    private fun inject() = loadModules
    private lateinit var binding: DogDetailsFragmentBinding

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
    ) = DogDetailsFragmentBinding.inflate(inflater, container, false).apply {

        setupView()
        observeChanges()
        val temp = activity?.intent?.getSerializableExtra(DogSearchFragment.BUNDLE_DOG)
        viewViewModel.dispatchViewAction(
            DogDetailsAction.Init(
                temp
            )
        )
    }.root


    private fun DogDetailsFragmentBinding.setupView() {
        binding = this
    }

    private fun observeChanges() {
        viewViewModel.viewState.state.observe(viewLifecycleOwner, { state ->
            when (state) {
                is DogDetailsViewState.State.Success -> onSuccess(state.dog)
                is DogDetailsViewState.State.Error -> onError(state.message)
                DogDetailsViewState.State.Loading -> onLoading()
            }
        })

        viewViewModel.viewState.action.observe(viewLifecycleOwner, { action ->
            when (action) {
                is DogDetailsViewState.Action.LikeOrDislike -> {

                }
            }
        })
    }

    private fun onLoading() {
        //TODO processing can be done  in  here .kt and databinding

    }

    private fun onError(message: String) {
        //TODO processing can be done in  here .kt and databinding
        Log.i("ERROR--->", "ERROR---> $message")
    }

    private fun onSuccess(data: Dog) {
        bindBreedName(data)
        bindBreedFor(data)
        bindImage(data)
        bindTemperament(data)
        bindTvLifeSpan(data)
    }

    private fun bindBreedFor(data: Dog) {
        binding.tvBreedForDetails.text = data.breedName
    }

    private fun bindTvLifeSpan(data: Dog) {
        binding.tvLifeSpanDetails.text = data.lifeSpan
    }

    private fun bindTemperament(data: Dog) {
        binding.tvTemperamentDetails.text = data.temperament
    }

    private fun bindImage(data: Dog) {
        Glide.with(this).load(data.url)
            .into(binding.imgBackdropPath)
    }

    private fun bindBreedName(data: Dog) {
        binding.tvBreedNameDetail.text = data.breedName
    }
}