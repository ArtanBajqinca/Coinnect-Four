package artan.lavdim_connect_4_group_4.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.garrit.android.multiplayer.Player


import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel

class SharedViewModel : ViewModel() {
    var currentPlayer: Player? by mutableStateOf(null)
}

class ViewModel {
    var currentPlayer: Player? by mutableStateOf(null)

    companion object {
        lateinit var currentPlayer: Player
    }
}
