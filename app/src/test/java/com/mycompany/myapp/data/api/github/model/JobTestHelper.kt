package com.mycompany.myapp.data.api.github.model

object JobTestHelper {
    fun stubJob(title: String, location: String, type: String, company: String, description: String): Job {
        return Job(title, location, type, company, description)
    }
}