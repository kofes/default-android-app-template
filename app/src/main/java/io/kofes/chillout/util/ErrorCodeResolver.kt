package io.kofes.chillout.util

import io.kofes.chillout.common.API

object ErrorResponseResolver {
    fun resolve(error: API.ErrorResponse?) {
        when (error?.error?.code) { }
    }
}