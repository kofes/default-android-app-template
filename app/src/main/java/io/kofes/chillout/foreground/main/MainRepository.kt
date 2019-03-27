package io.kofes.chillout.foreground.main

import io.kofes.chillout.foreground.common.Repository
import io.kofes.chillout.foreground.common.RepositoryImpl

interface MainRepository: Repository {

}

class MainRepositoryImpl: MainRepository, RepositoryImpl() {

}