package com.github.xepozz.infection.config

import com.github.xepozz.infection.InfectionQualityToolType
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.openapi.components.StoragePathMacros
import com.intellij.openapi.project.Project
import com.intellij.util.xmlb.XmlSerializerUtil
import com.jetbrains.php.tools.quality.QualityToolProjectConfiguration

@Service(Service.Level.PROJECT)
@State(name = "InfectionProjectConfiguration", storages = [Storage(StoragePathMacros.WORKSPACE_FILE)])
class InfectionProjectConfiguration : QualityToolProjectConfiguration<InfectionConfiguration>(),
    PersistentStateComponent<InfectionProjectConfiguration> {

    override fun getState() = this

    override fun loadState(state: InfectionProjectConfiguration) {
        XmlSerializerUtil.copyBean(state, this)
    }

    override fun getQualityToolType() = InfectionQualityToolType.INSTANCE

    companion object {
        fun getInstance(project: Project): InfectionProjectConfiguration =
            project.getService(InfectionProjectConfiguration::class.java)
    }
}