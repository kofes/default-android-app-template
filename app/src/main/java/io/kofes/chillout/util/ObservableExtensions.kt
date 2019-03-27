package io.kofes.chillout.util

import androidx.databinding.Observable
import io.reactivex.disposables.Disposables

fun <T: Observable> T.addOnPropertyChanged(callback: (T) -> Unit) =
    object : Observable.OnPropertyChangedCallback() {
        override fun onPropertyChanged(sender: Observable?, i: Int) = callback(sender as T)
    }.also { addOnPropertyChangedCallback(it) }.let {
        Disposables.fromAction {removeOnPropertyChangedCallback(it)}
    }