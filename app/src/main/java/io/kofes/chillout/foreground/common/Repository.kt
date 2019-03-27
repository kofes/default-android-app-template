package io.kofes.chillout.foreground.common

import io.kofes.chillout.common.API
import io.kofes.chillout.common.Router.retrofit

interface Repository {
    val api: API
}

open class RepositoryImpl: Repository {
    override val api: API = retrofit.create(API::class.java)
}