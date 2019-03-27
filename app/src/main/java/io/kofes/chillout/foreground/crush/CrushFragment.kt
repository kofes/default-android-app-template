package io.kofes.chillout.foreground.crush

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import io.kofes.chillout.R
import io.kofes.chillout.foreground.common.VMFragment

class CrushFragment: VMFragment<CrushVM>() {

    companion object {
        fun instance() = CrushFragment()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onCreateBinding(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): ViewDataBinding? {
        viewModel = CrushVM(context?.applicationContext as Application, eventBus)
        return DataBindingUtil.inflate(inflater, R.layout.crush_fragment, container, false)
    }

}