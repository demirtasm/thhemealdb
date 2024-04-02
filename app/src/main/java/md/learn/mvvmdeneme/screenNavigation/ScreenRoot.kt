package md.learn.mvvmdeneme.screenNavigation

sealed class ScreenRoot(val route: String){
    object CategoryScreen: ScreenRoot("categoryScreen")
    object CategoryDetailScreen: ScreenRoot("categoryDetailScreen")

}
