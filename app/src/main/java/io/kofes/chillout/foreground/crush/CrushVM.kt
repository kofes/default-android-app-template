package io.kofes.chillout.foreground.crush

import android.app.Application
import com.crashlytics.android.Crashlytics
import io.kofes.chillout.foreground.common.AppVM
import org.greenrobot.eventbus.EventBus

class CrushVM(application: Application, eventBus: EventBus): AppVM(application, eventBus, CrushVM::class.java.simpleName) {
    fun onClick() {
        Crashlytics.getInstance().crash() // Force a crash
    }
}