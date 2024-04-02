package md.learn.mvvmdeneme.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import md.learn.mvvmdeneme.services.CategoryApiResponse
import md.learn.mvvmdeneme.services.categoriesApiService

class MainViewModel : ViewModel() {
    private val _categoriesState = mutableStateOf(theMealDbState())
    val categoriesState: State<theMealDbState> = _categoriesState

    init {
        fetchMealCategories()
    }

    private fun fetchMealCategories() {
        viewModelScope.launch {
            try {
                val categoriesResponse = categoriesApiService.getCategories()
                _categoriesState.value =  _categoriesState.value.copy(list = categoriesResponse.categories, loading = false, error = null)
            }catch (e:Exception){
                _categoriesState.value = _categoriesState.value.copy(loading = false,  error= "Error fetcging categories ${e.message}")
            }
        }
    }
    data class theMealDbState(
        val loading:Boolean = true,
        val list: List<CategoryApiResponse> = emptyList(),
        val error: String?= null
    )
}

