package com.mycompany.myapp.ui

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.mycompany.myapp.ui.details.DetailsViewModel
import com.mycompany.myapp.ui.main.MainViewModel
// GENERATOR - MORE IMPORTS //
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelFactoryModule: VariantViewModelFactoryModule() {
    @Binds internal abstract fun bindViewModelFactory(factory: DaggerViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindsMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailsViewModel::class)
    abstract fun bindsDetailsViewModel(detailsViewModel: DetailsViewModel): ViewModel

    // GENERATOR - MORE VIEW MODELS //
}