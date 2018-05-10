package com.mycompany.myapp.data.api.github.model

import com.google.gson.annotations.SerializedName

data class Job (
    @SerializedName("title") val title: String,
    @SerializedName("location") val location: String,
    @SerializedName("type") val type: String,
    @SerializedName("company") val company: String,
    @SerializedName("description") val description: String
)