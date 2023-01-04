package dinhtc.app.mylearning.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import dinhtc.app.mylearning.repository.ApiHelper
import dinhtc.app.mylearning.repository.ApiHelperImpl
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
interface DependenciesBindings {
    @Binds
    fun mainRepository(mainRepositoryImpl: ApiHelperImpl) : ApiHelper
}