package com.project.myrecipe.pages.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "recipes")
class Recipe : Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    var title: String = ""
    var ingredients: String = ""
    var description: String? = null
    var isFavourite: Boolean = false
    var image: String? = null

    constructor()

    constructor(
        title: String,
        description: String?,
        isFavourite: Boolean,
        ingredients: String,
        image: String?
    ) {
        this.title = title
        this.description = description
        this.ingredients = ingredients
        this.isFavourite = isFavourite
        this.image = image
    }
}


@Entity(tableName = "ingredients", primaryKeys = ["id","title"])
class Ιngredients :Serializable {
    var id: Int =  0
    var title: String = ""
    var ingredients: String = ""


    constructor()

    constructor(
        id:Int,
        title:String,
        ingredients:String,

    ){
        this.id= id
        this.title=title
        this.ingredients=ingredients

    }
}



