package com.github.vove7.retrofitrestclient2.services

import com.intellij.openapi.project.Project
import com.github.vove7.retrofitrestclient2.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
