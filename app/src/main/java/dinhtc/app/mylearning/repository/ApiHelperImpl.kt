package dinhtc.app.mylearning.repository

import dinhtc.app.mylearning.model.AccountModel
import dinhtc.app.mylearning.model.BranchModel
import dinhtc.app.mylearning.service.ApiService
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {

    override suspend fun getAccount(accString: String): AccountModel =
        apiService.getAccount(accString)

    override suspend fun getBranches(accString: String, detail: String): List<BranchModel> =
        apiService.getBranches(accString, detail)

}