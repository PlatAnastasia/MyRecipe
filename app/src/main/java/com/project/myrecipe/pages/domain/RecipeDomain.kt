package com.project.myrecipe.pages.domain

import com.project.myrecipe.pages.database.Recipe

data class RecipeDomain(
    var id: Int ,
    var title: String ,
    var ingredients: String ,
    var description: String?,
    var isFavourite:Boolean
)

fun Recipe.toDomain():RecipeDomain{
    return RecipeDomain(
        id= this.id,
        title= this.title,
        ingredients= this.ingredients,
        description= this.description,
        isFavourite= this.isFavourite,
    )
}