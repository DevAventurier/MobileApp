package com.yerima.vendredi.data

data class Post (
    var titre: String,
    var description:String,
    var image:ByteArray){

    var id:Int=-1 //pr dire kil es pas null

    constructor(id:Int, titre:String, description: String, image: ByteArray):this(titre,description,image){
        this.id=id
    }
}

//reecrire les fonctiion ds le postAdapter