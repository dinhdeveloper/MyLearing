package dinhtc.app.mylearning.repository

import dinhtc.app.mylearning.common.NetworkResult
import dinhtc.app.mylearning.model.AccountModel
import dinhtc.app.mylearning.service.ApiService
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepository @Inject constructor(private val apiService: ApiService) {

    //suspend fun fetchGetAccount(edtUserName : String) = apiService.getAccount(edtUserName)

    suspend fun fetchGetAccount(edtUserName: String) = flow {
        emit(NetworkResult.Loading(true))
        val response = apiService.getAccount(edtUserName)
        emit(NetworkResult.Success(response))
    }.catch { e ->
        emit(NetworkResult.Failure(e.message ?: "Unknown Error"))
    }

    suspend fun fetchGetAccount2(edtUserName: String): AccountModel {
        return apiService.getAccount(edtUserName)
    }

}