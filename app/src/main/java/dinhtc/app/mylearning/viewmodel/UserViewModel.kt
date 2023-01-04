package dinhtc.app.mylearning.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import dinhtc.app.mylearning.common.NetworkResult
import dinhtc.app.mylearning.common.Resource
import dinhtc.app.mylearning.model.AccountModel
import dinhtc.app.mylearning.repository.UserRepository
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val users = MutableLiveData<Resource<List<AccountModel>>>()

    fun getUsers(): LiveData<Resource<List<AccountModel>>> {
        return users
    }

    fun fetchUsers(edtUserName: String) {
        viewModelScope.launch {
            users.postValue(Resource.loading())
            try {

                val a = viewModelScope.async {
                    userRepository.fetchGetAccount2(edtUserName)
                }

                val b = viewModelScope.async {
                    userRepository.fetchGetAccount2(edtUserName)
                }

                viewModelScope.launch {
                    val c = awaitAll(a, b)
                    users.postValue(Resource.success(c))
                }

//                // coroutineScope is needed, else in case of any network error, it will crash
//                coroutineScope {
//                    val usersFromApiDeferred = async { userRepository.fetchGetAccount2(edtUserName) }
//                    val moreUsersFromApiDeferred = async { userRepository.fetchGetAccount2(edtUserName) }
//
//                    val usersFromApi : Deferred<AccountModel> = usersFromApiDeferred.await()
//                    val moreUsersFromApi : Deferred<AccountModel> = moreUsersFromApiDeferred.await()
//
//                    val abc = awaitAll(usersFromApi, moreUsersFromApi)
//
//                    users.postValue(Resource.success(abc))
//                }
            } catch (e: Exception) {
                users.postValue(Resource.error("Something Went Wrong"))
            }
        }
    }

//    private var _movieRe = MutableLiveData<NetworkResult<AccountModel>>()
//    val movieRe: MutableLiveData<NetworkResult<AccountModel>> = _movieRe
//
//    fun fetchAllMovies(edtUserName: String) {
//        viewModelScope.launch {
//            userRepository.fetchGetAccount(edtUserName).collect {
//                _movieRe.postValue(it)
//            }
//        }
//    }
//
//    private var _movieResponse = MutableLiveData<List<AccountModel>>()
//    val movieResponse: LiveData<List<AccountModel>> = _movieResponse
//
//    fun fetch(edtUserName: String) {
//        val a = viewModelScope.async {
//            userRepository.fetchGetAccount2(edtUserName)
//        }
//        viewModelScope.launch {
//            delay(5000)
//        }
//        val b = viewModelScope.async {
//            userRepository.fetchGetAccount2(edtUserName)
//        }
//
//        viewModelScope.launch {
//            val c = awaitAll(a, b)
//            _movieResponse.postValue(c)
//        }
//        // reponse ra UI
//
//    }
}