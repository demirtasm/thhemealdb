package md.learn.mvvmdeneme.api

data class CategoryApiResponse(val idCategory: String,
                               val strCategory: String,
                               val strCategoryThumb: String,
                               val strCategoryDescription: String)

data class CategoriesResponsesList(val categories: List<CategoryApiResponse>)