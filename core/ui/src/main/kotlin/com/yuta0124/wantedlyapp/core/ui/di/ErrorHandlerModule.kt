package com.yuta0124.wantedlyapp.core.ui.di

import com.yuta0124.wantedlyapp.core.ui.ErrorHandler
import com.yuta0124.wantedlyapp.core.ui.IErrorHandler
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class ErrorHandlerModule {

    @Binds
    @ViewModelScoped
    abstract fun bindErrorHandler(errorHandler: ErrorHandler): IErrorHandler
}
