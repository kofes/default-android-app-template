package io.kofes.chillout.foreground.common

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleObserver
import io.kofes.chillout.BR
import io.kofes.chillout.util.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

abstract class VMActivity<ViewModelClass: AppVM>: AppCompatActivity() {
    protected var viewModel: ViewModelClass? = null

    val eventBus: EventBus = EventBus.builder()
        .logNoSubscriberMessages(false)
        .sendNoSubscriberEvent(false)
        .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onCreateBinding(savedInstanceState)?.let {
            viewModel?.let { vm ->
                lifecycle.addObserver(vm as LifecycleObserver)
                it.setVariable(BR.viewmodel, vm)
            }
            it.lifecycleOwner = this
        }
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(LocaleHelper.onAttach(base!!))
    }

    protected abstract fun onCreateBinding(savedInstanceState: Bundle?): ViewDataBinding?


    override fun onStart() {
        super.onStart()
        eventBus.register(this)
    }

    override fun onStop() {
        super.onStop()
        eventBus.unregister(this)
    }

    @Subscribe
    fun onEvent(event: Event) {
        Log.d("onEvent", "event accrued")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        eventBus.post(ActivityResultEvent(requestCode, resultCode, data))
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        eventBus.post(PermissionResultEvent(requestCode, permissions, grantResults))
    }
}
