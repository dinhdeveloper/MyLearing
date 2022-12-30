package dinhtc.app.mylearning.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dinhtc.app.mylearning.model.UserModel
import dinhtc.app.mylearning.repository.UserRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {


    private val _userResponse = MutableLiveData<List<UserModel>>()
    val userResponse: LiveData<List<UserModel>> = _userResponse

    fun getListCategory(){
        viewModelScope.launch {
            userRepository.getListCustomer().let { dataResponse ->
                _userResponse.postValue(dataResponse)
            }
        }
    }
}