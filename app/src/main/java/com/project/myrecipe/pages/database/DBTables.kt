package com.project.myrecipe.pages.database

import androidx.room.Entity
import java.io.Serializable

@Entity(tableName = "recipes", primaryKeys = ["id"])
 class Recipe :Serializable {
    var id: Int =  0
    var title: String = ""
    var description: String? = null
    var ingredients: List<String> = emptyList()
    var isFavourite:Boolean = false

    constructor()

    constructor(
        id:Int,
        title:String,
        description:String?,
        ingredients:List<String>,
        isFavourite:Boolean
    ){
        this.id= id
        this.title=title
        this.description=description
        this.ingredients=ingredients
        this.isFavourite=isFavourite
    }
 }


