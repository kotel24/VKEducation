package ru.sumin.vkeducation.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.sumin.vkeducation.data.appdetails.AppDetailsRepositoryImpl
import ru.sumin.vkeducation.data.applist.AppsListRepositoryImpl
import ru.sumin.vkeducation.domain.appdetails.AppDetailsRepository
import ru.sumin.vkeducation.domain.applist.AppsListRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindAppsListRepository(
        appsListRepositoryImpl: AppsListRepositoryImpl
    ): AppsListRepository

    @Binds
    @Singleton
    abstract fun bindAppDetailsRepository(
        appDetailsRepositoryImpl: AppDetailsRepositoryImpl
    ): AppDetailsRepository
}