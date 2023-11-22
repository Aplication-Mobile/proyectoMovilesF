package com.will.bananap.entities

class cls_Research {

    var title: String = ""
    var areaOfInterest: String = ""
    var briefDescription: String = ""
    var pdfURL: String = ""
    var images: List<String> = emptyList()
    var conclusions: String = ""
    var recommendations: String = ""
    var userId: String = ""

    constructor(){}
    constructor(
        title: String,
        areaOfInterest: String,
        briefDescription: String,
        pdfURL: String,
        images: List<String>,
        conclusions: String,
        recommendations: String,
        userId: String
    ) {
        this.title = title
        this.areaOfInterest = areaOfInterest
        this.briefDescription = briefDescription
        this.pdfURL = pdfURL
        this.images = images
        this.conclusions = conclusions
        this.recommendations = recommendations
        this.userId = userId
    }


}