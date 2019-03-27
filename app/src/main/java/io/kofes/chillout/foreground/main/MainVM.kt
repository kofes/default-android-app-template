package io.kofes.chillout.foreground.main

import android.app.Application
import io.kofes.chillout.foreground.common.AppVM
import org.greenrobot.eventbus.EventBus

class MainVM(application: Application, eventBus: EventBus): AppVM(application, eventBus, MainVM::class.java.simpleName) {
    val repository: MainRepository = MainRepositoryImpl()

}