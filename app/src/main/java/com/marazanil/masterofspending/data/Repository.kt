package com.marazanil.masterofspending.data

import javax.inject.Inject

class Repository @Inject constructor(
    localDataSource: LocalDataSource
){

    val local =  localDataSource

}