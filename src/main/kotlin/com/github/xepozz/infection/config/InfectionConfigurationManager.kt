package com.github.xepozz.infection.config

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.openapi.project.Project
import com.jetbrains.php.tools.quality.QualityToolConfigurationManager

@Service(Service.Level.PROJECT)
class InfectionConfigurationManager(project: Project?) :
    QualityToolConfigurationManager<InfectionConfiguration>(project) {
    init {
        if (project != null) {
            myProjectManager = project.getService(InfectionProjectConfigurationManager::class.java)
        }
        myApplicationManager = ApplicationManager.getApplication().getService(InfectionAppConfigurationManager::class.java)
    }

    @Service(Service.Level.PROJECT)
    @State(name = "Infection", storages = [Storage("php-tools.xml")])
    internal class InfectionProjectConfigurationManager : InfectionConfigurationBaseManager()

    @Service(Service.Level.APP)
    @State(name = "Infection", storages = [Storage("php-tools.xml")])
    internal class InfectionAppConfigurationManager : InfectionConfigurationBaseManager()

    companion object {
        fun getInstance(project: Project): InfectionConfigurationManager =
            project.getService(InfectionConfigurationManager::class.java)
    }
}
