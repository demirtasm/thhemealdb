package md.learn.mvvmdeneme.screenNavigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import md.learn.mvvmdeneme.services.CategoryApiResponse
import md.learn.mvvmdeneme.screens.CategoryDetailScreen
import md.learn.mvvmdeneme.screens.CategoryScreen
import md.learn.mvvmdeneme.viewModel.MainViewModel

@Composable
fun TheMealApp(navController: NavHostController) {
    val categoryViewModel: MainViewModel = viewModel()
    val viewState by categoryViewModel.categoriesState

    NavHost(navController = navController, startDestination = ScreenRoot.CategoryScreen.route) {
        composable(route = ScreenRoot.CategoryScreen.route) {
            CategoryScreen(viewState = viewState, navigationToDetail = {
                navController.currentBackStackEntry?.savedStateHandle?.set("cat", it)
                navController.navigate(ScreenRoot.CategoryDetailScreen.route)
            })
        }
        composable(route = ScreenRoot.CategoryDetailScreen.route) {
            val category =
                navController.previousBackStackEntry?.savedStateHandle?.get<CategoryApiResponse>("cat")
                    ?: CategoryApiResponse("", "", "", "")
            CategoryDetailScreen(category = category)
        }
    }
}