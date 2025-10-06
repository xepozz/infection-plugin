package com.github.xepozz.infection

import com.intellij.openapi.components.Service
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.openapi.components.StoragePathMacros
import com.intellij.openapi.project.Project
import com.jetbrains.php.tools.quality.QualityToolBlackList

@State(name = "InfectionBlackList", storages = [Storage(StoragePathMacros.WORKSPACE_FILE)])
@Service(Service.Level.PROJECT)
class InfectionBlackList : QualityToolBlackList() {
    companion object {
        fun getInstance(project: Project): InfectionBlackList = project.getService(InfectionBlackList::class.java)
    }
}
