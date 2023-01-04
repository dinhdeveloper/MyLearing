package dinhtc.app.mylearning.ui.fragment

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.MainThread
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import dinhtc.app.mylearning.R
import dinhtc.app.mylearning.base.BaseFragment
import dinhtc.app.mylearning.common.NetworkResult
import dinhtc.app.mylearning.common.Status
import dinhtc.app.mylearning.databinding.FragmentHomeBinding
import dinhtc.app.mylearning.model.AccountModel
import dinhtc.app.mylearning.viewmodel.AccountViewModel
import dinhtc.app.mylearning.viewmodel.UserViewModel
import kotlinx.coroutines.*

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val userViewModel by viewModels<UserViewModel>()
    private val accountViewModel by viewModels<AccountViewModel>()

    override val layoutResourceId: Int
        get() = R.layout.fragment_home

    override fun viewCreatedFragment() {
        _binding.apply {
            btnSearch.setOnClickListener {
                //userViewModel.fetchUsers("dinhdeveloper")
                accountViewModel.getAccount("dinhdeveloper")
            }
        }
        accountViewModel.accountData.observe(this){
            when (it.status) {
                Status.SUCCESS -> {
                    _binding.progressbar.visibility = View.GONE
                }
                Status.LOADING -> {
                    _binding.progressbar.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    //Handle Error
                    _binding.progressbar.visibility = View.GONE
                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                }
            }
        }
        accountViewModel.accountDataOne.observe(this){
            when (it.status) {
                Status.SUCCESS -> {
                    _binding.progressbar.visibility = View.GONE
                    it.data?.let { users ->
                        Log.d("SSSSSSSSSSS_ONE", Gson().toJson(users))
                    }
                }
                Status.LOADING -> {
                    _binding.progressbar.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    //Handle Error
                    _binding.progressbar.visibility = View.GONE
                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                }
            }
        }
        accountViewModel.accountDataTwo.observe(this){
            when (it.status) {
                Status.SUCCESS -> {
                    _binding.progressbar.visibility = View.GONE
                    it.data?.let { users ->
                        Log.e("SSSSSSSSSSS_TWO", Gson().toJson(users))
                    }
                }
                Status.LOADING -> {
                    _binding.progressbar.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    //Handle Error
                    _binding.progressbar.visibility = View.GONE
                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                }
            }
        }

//        userViewModel.getUsers().observe(this) {
//            when (it.status) {
//                Status.SUCCESS -> {
//                    _binding.progressbar.visibility = View.GONE
//                    it.data?.let { users ->
//                        Log.e("SSSSSSSSSSSSS",Gson().toJson(users))
//                    }
//                }
//                Status.LOADING -> {
//                    _binding.progressbar.visibility = View.VISIBLE
//                }
//                Status.ERROR -> {
//                    //Handle Error
//                    _binding.progressbar.visibility = View.GONE
//                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
//                }
//            }
//        }
//        userViewModel.aResponse.observe(this) {
//            when (it) {
//                is NetworkResult.Loading -> {
//                    _binding.progressbar.isVisible = it.isLoading
//                }
//
//                is NetworkResult.Failure -> {
//                    Toast.makeText(context, it.errorMessage, Toast.LENGTH_SHORT).show()
//                    _binding.progressbar.isVisible = false
//                }
//
//                is NetworkResult.Success -> {
//                    _binding.progressbar.isVisible = false
//                    Log.e("SSSSSSSSSSSS", Gson().toJson(it.data))
//                }
//            }
//        }

    }
}