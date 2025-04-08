package com.project.myrecipe.pages.mainMenu

import androidx.lifecycle.ViewModel
import dagger.hilt.android.internal.lifecycle.HiltViewModelMap
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainMenuViewModel @Inject constructor(
    private val mainMenuInteractor: MainMenuInteractor
) :ViewModel(){


}