package com.yuta0124.wantedlyapp.core.common

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class AppDispatcher(val appDispatcher: AppDispatchers)

enum class AppDispatchers {
    IO,
    DEFAULT,
    MAIN,
}
