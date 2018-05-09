package com.mycompany.myapp.data.api.github.model

import com.google.gson.annotations.SerializedName

data class Job (
    @SerializedName("title") val jobTitle: String,
    @SerializedName("location") val jobLocation: String,
    @SerializedName("type") val jobType: String,
    @SerializedName("company") val jobCompany: String,
    @SerializedName("description") val jobDescription: String
) {
    val title: String
        get() = jobTitle

    val location: String
        get() = jobLocation

    val type: String
        get() = jobType

    val company: String
        get() = jobCompany

    val description: String
        get() = jobDescription
}
