package io.kofes.chillout.foreground.main

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import io.kofes.chillout.R
import io.kofes.chillout.foreground.common.VMActivity
import io.kofes.chillout.foreground.crush.CrushFragment

class MainActivity : VMActivity<MainVM>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_layout, CrushFragment.instance())
            .commit()
    }

    override fun onCreateBinding(savedInstanceState: Bundle?): ViewDataBinding? {
        viewModel = MainVM(application, eventBus)
        return DataBindingUtil.setContentView(this, R.layout.activity_main)
    }
}
