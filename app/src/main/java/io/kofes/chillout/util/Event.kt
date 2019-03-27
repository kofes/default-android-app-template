package io.kofes.chillout.util

import android.content.Intent
import android.os.Bundle

interface Event

open class BaseEvent(val tag: String): Event

open class PairEvent<T>(tag: String, val value: T): BaseEvent(tag)

open class BundleEvent(tag: String, val bundle: Bundle): BaseEvent(tag)

data class ActivityResultEvent(val requestCode: Int, val resultCode: Int, val data: Intent?): Event

@Suppress("ArrayInDataClass")
data class PermissionResultEvent(val requestCode: Int, val permissions: Array<out String>, val grantResults: IntArray) : Event