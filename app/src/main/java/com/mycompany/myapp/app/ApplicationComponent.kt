package com.mycompany.myapp.app

import com.mycompany.myapp.data.DataModule
import com.mycompany.myapp.modules.CrashReporterModule
import com.mycompany.myapp.monitoring.LoggerModule
import com.mycompany.myapp.ui.ViewModelFactoryModule
import com.mycompany.myapp.ui.details.DetailsActivity
import com.mycompany.myapp.ui.main.MainActivity
// GENERATOR - MORE IMPORTS //
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
        VariantModule::class,
        AndroidModule::class,
        AppModule::class,
        LoggerModule::class,
        CrashReporterModule::class,
        DataModule::class,
        ViewModelFactoryModule::class])
interface ApplicationComponent : VariantApplicationComponent {
    fun inject(application: MainApplication)

    fun inject(activity: MainActivity)

    fun inject(detailsActivity: DetailsActivity)
    // GENERATOR - MORE ACTIVITIES //
}