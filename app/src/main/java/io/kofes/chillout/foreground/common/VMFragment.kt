package io.kofes.chillout.foreground.common

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ObservableField
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleObserver
import io.kofes.chillout.BR
import io.kofes.chillout.util.ActivityResultEvent
import io.kofes.chillout.util.Event
import io.kofes.chillout.util.PermissionResultEvent
import io.kofes.chillout.util.addOnPropertyChanged
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

abstract class VMFragment<ViewModelClass: AppVM>: Fragment() {
    protected var viewModel: ViewModelClass? = null

    protected val eventBus: EventBus = EventBus.builder()
        .logNoSubscriberMessages(false)
        .sendNoSubscriberEvent(false)
        .build()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return onCreateBinding(inflater, container, savedInstanceState)?.also { viewDataBinding ->
            viewModel?.let { vm ->
                lifecycle.addObserver(vm as LifecycleObserver)
                viewDataBinding.setVariable(BR.viewmodel, vm)
                viewDataBinding.lifecycleOwner = this
            }
        }?.root
    }

    protected abstract fun onCreateBinding(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): ViewDataBinding?

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
