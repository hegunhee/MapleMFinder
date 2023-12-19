package com.hegunhee.maplemfinder.feature.main

import androidx.lifecycle.ViewModel
import com.hegunhee.maplemfinder.core.domain.usecase.GetThreeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor(private val getThreeUseCase: GetThreeUseCase) : ViewModel() {

    fun getThree() : Int {
        return getThreeUseCase()
    }

    //TEST
}