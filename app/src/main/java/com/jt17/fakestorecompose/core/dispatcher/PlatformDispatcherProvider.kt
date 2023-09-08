package com.jt17.fakestorecompose.core.dispatcher

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class PlatformDispatcherProvider : DispatcherProvider {
    override val ui: CoroutineDispatcher get() = Dispatchers.Main
    override val io: CoroutineDispatcher get() = Dispatchers.IO
    override val bg: CoroutineDispatcher get() = Dispatchers.Default
}