package md.learn.mvvmdeneme.services

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CategoryApiResponse(val idCategory: String,
                               val strCategory: String,
                               val strCategoryThumb: String,
                               val strCategoryDescription: String): Parcelable

data class CategoriesResponsesList(val categories: List<CategoryApiResponse>)