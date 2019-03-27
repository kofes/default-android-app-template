package io.kofes.chillout.foreground.common

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import org.greenrobot.eventbus.EventBus
import java.text.SimpleDateFormat
import java.util.*

open class AppVM(
    application: Application,
    val eventBus: EventBus,
    protected val LOG_TAG: String = AppVM::class.java.simpleName
): AndroidViewModel(application), LifecycleObserver {

    private val DATE_FORMATTER = SimpleDateFormat("hh:mm:ss", Locale.US)

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    open fun onCreate() {
        Log.d(LOG_TAG, "created ${DATE_FORMATTER.format(Date())}")
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    open fun onStart() {
        Log.d(LOG_TAG, "started ${DATE_FORMATTER.format(Date())}")
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    open fun onResume() {
        Log.d(LOG_TAG, "resumed ${DATE_FORMATTER.format(Date())}")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    open fun onPause() {
        Log.d(LOG_TAG, "paused ${DATE_FORMATTER.format(Date())}")
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    open fun onStop() {
        Log.d(LOG_TAG, "stopped ${DATE_FORMATTER.format(Date())}")
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    open fun onDestroy() {
        Log.d(LOG_TAG, "destroyed ${DATE_FORMATTER.format(Date())}")
    }
}