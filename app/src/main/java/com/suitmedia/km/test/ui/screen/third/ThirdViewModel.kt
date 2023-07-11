package com.suitmedia.km.test.ui.screen.third

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.suitmedia.km.test.data.remote.UserDataItem
import com.suitmedia.km.test.data.remote.UserDto
import com.suitmedia.km.test.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.suitmedia.km.test.utils.Result
import kotlinx.coroutines.flow.Flow

@HiltViewModel
class ThirdViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    fun getUsers(): Flow<PagingData<UserDataItem>> = repository.getUser()
}