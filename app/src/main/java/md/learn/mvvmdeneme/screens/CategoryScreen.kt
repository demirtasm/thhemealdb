package md.learn.mvvmdeneme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import md.learn.mvvmdeneme.services.CategoryApiResponse
import md.learn.mvvmdeneme.viewModel.MainViewModel

@Composable
fun CategoryScreen(modifier: Modifier= Modifier, viewState:MainViewModel.theMealDbState, navigationToDetail:(CategoryApiResponse)->Unit){


    Box (modifier = Modifier.fillMaxSize()){
        when{
            viewState.loading -> {
                CircularProgressIndicator()
            }
            viewState.error != null -> {
                Text(text = "ERROR OCCURED")
            }
            else -> {
                categoryScreen(categories = viewState.list, navigationToDetail)
            }
        }
    }

}

@Composable
fun categoryScreen(categories : List<CategoryApiResponse>, navigationToDetail:(CategoryApiResponse)->Unit){
    LazyVerticalGrid(GridCells.Fixed(2), Modifier.fillMaxSize()) {
        items(categories){
            categoryItem(category = it, navigationToDetail)
        }
    }
}

@Composable
fun categoryItem(category: CategoryApiResponse,  navigationToDetail:(CategoryApiResponse)->Unit) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
            .clickable {
                navigationToDetail(category) }, horizontalAlignment = Alignment.CenterHorizontally
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