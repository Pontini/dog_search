package pontinisystems.base_android

import androidx.lifecycle.ViewModel

abstract class BaseViewModel<VS, VA> : ViewModel() {
    abstract val viewState: VS
    abstract fun dispatchViewAction(viewAction: VA)



}
