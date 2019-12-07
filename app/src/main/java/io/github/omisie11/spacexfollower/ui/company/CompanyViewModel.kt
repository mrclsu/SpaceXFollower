package io.github.omisie11.spacexfollower.ui.company

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.omisie11.spacexfollower.data.model.Company
import kotlinx.coroutines.*

class CompanyViewModel(private val repository: CompanyRepository) : ViewModel() {

    private val companyData by lazy { repository.getCompanyInfo() }
    private val _isCompanyInfoLoading by lazy { repository.getCompanyInfoLoadingStatus() }
    private val _snackBar: MutableLiveData<String> = repository.getCompanyInfoSnackbar()

    fun getCompanyInfo(): LiveData<Company> = companyData

    fun getCompanyInfoLoadingStatus() = _isCompanyInfoLoading

    fun refreshCompanyInfo() = viewModelScope.launch { repository.refreshCompanyInfo() }

    fun refreshIfCompanyDataOld() = viewModelScope.launch { repository.refreshIfCompanyDataOld() }

    fun deleteCompanyInfo() = viewModelScope.launch { repository.deleteCompanyInfo() }

    /**
     * Request a snackbar to display a string.
     */
    val snackbar: LiveData<String>
        get() = _snackBar

    /**
     * Called immediately after the UI shows the snackbar.
     */
    fun onSnackbarShown() {
        _snackBar.value = null
    }
}