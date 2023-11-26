package hr.ferit.franjosipovic.myapplication.data

import androidx.annotation.DrawableRes

data class Recipe(
    @DrawableRes val image: Int,
    val title: String,
    val category: String,
    val cookingTime: Int,
    val energy: Int,
    val rating: Float,
    val description: String,
    val reviews: List<String>,
    val ingredients: List<Ingredient>
)
