package dev.masterdeveloper.telecurso2000

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _contador: MutableLiveData<Int> = MutableLiveData(0)
    val contador: LiveData<Int>
        get() = _contador

    private val _status: MutableLiveData<Status> = MutableLiveData(Status.NAO_INICIADO)
    val status: LiveData<Status>
        get() = _status

    fun iniciar() {
        viewModelScope.launch {
            _status.value = Status.INICIADO
            while (_status.value == Status.INICIADO) {
                _contador.postValue(_contador.value!! + 1)
                delay(1000L)
            }
        }
    }

    enum class Status {
        NAO_INICIADO, INICIADO, PAUSADO
    }

}