package com.bpn.trainme.presentation.features.login

import com.bpn.trainme.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
) : BaseViewModel<LoginState, LoginUiEvent>() {

}
