package pontinisystems.dog.presentation.di


import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import pontinisystems.dog.presentation.mapper.DogsToDogsUIMapper
import pontinisystems.dog.presentation.viewmodel.DogDetailsViewModel
import pontinisystems.dog.presentation.viewmodel.DogsViewModel

val presentationModuleDependency = module {
    factory { DogsToDogsUIMapper() }
    viewModel {
        DogsViewModel(
            get(), get(), get()
        )

    }
    viewModel {
        DogDetailsViewModel()
    }
}