package dinhtc.app.mylearning.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dinhtc.app.mylearning.common.Resource
import dinhtc.app.mylearning.model.AccountModel
import dinhtc.app.mylearning.model.BranchModel
import dinhtc.app.mylearning.repository.ApiHelper
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val apiHelper: ApiHelper
) : ViewModel() {

    private var _accountData = MutableLiveData<Resource<List<AccountModel>>>()
    val accountData: LiveData<Resource<List<AccountModel>>> = _accountData

    private var _accountDataOne = MutableLiveData<Resource<AccountModel>>()
    val accountDataOne: LiveData<Resource<AccountModel>> = _accountDataOne

    private var _accountDataTwo = MutableLiveData<Resource<List<BranchModel>>>()
    val accountDataTwo: LiveData<Resource<List<BranchModel>>> = _accountDataTwo



    fun getAccount(accString: String) {
        viewModelScope.launch {
            _accountData.postValue(Resource.loading())
            try {
                val dataOne = viewModelScope.async {
                    apiHelper.getAccount(accString)
                }

                val dataName = dataOne.await().name
                val dataTwo = viewModelScope.async {
                    apiHelper.getBranches(accString,dataName)
                }

                viewModelScope.launch {
                    _accountDataOne.postValue(Resource.success(dataOne.await()))
                    //delay(10000)
                    //_accountDataTwo.postValue(Resource.success(dataTwo.await()))

//                    val c = awaitAll(dataOne, dataTwo)
//                    _accountData.postValue(Resource.success(c))
                }
            } catch (e: Exception) {
                _accountData.postValue(Resource.error("Something Went Wrong"))
            }
        }
    }
}