package md.learn.mvvmdeneme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import md.learn.mvvmdeneme.api.CategoryApiResponse
import md.learn.mvvmdeneme.viewModel.MainViewModel

@Composable
fun TheMealCategoryScreen(modifier: Modifier = Modifier){
    val categoriesViewModel: MainViewModel = viewModel()
    val viewState by categoriesViewModel.categoriesState

    Box (modifier = Modifier.fillMaxSize()){
        when{
            viewState.loading -> {
                CircularProgressIndicator(modifier.align(Alignment.Center))
            }
            viewState.error != null -> {
                Text(text = "ERROR OCCURED")
            }
            else -> {
                categoryScreen(categories = viewState.list)
            }
        }
    }

}

@Composable
fun categoryScreen(categories : List<CategoryApiResponse>){
    LazyVerticalGrid(GridCells.Fixed(2), Modifier.fillMaxSize()) {
        items(categories){
            categoryItem(category = it)
        }
    }
}

@Composable
fun categoryItem(category: CategoryApiResponse) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(painter = rememberAsyncImagePainter(category.strCategoryThumb), contentDescription =null, modifier = Modifier
            .fillMaxSize()
            .aspectRatio(1f) )
        Text(
            text = category.strCategory,
            color = Color.Blue,
            style = TextStyle(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}