package com.github.xepozz.infection.config

import com.github.xepozz.infection.InfectionQualityToolType
import com.intellij.util.xmlb.XmlSerializer
import com.jetbrains.php.tools.quality.QualityToolConfigurationBaseManager
import org.jdom.Element

open class InfectionConfigurationBaseManager : QualityToolConfigurationBaseManager<InfectionConfiguration>() {
    override fun getQualityToolType() = InfectionQualityToolType.INSTANCE

    override fun getOldStyleToolPathName() = INFECTION_PATH

    override fun getConfigurationRootName() = INFECTION_ROOT_NAME

    override fun loadLocal(element: Element?): InfectionConfiguration? {
        if (element == null) return null

        return XmlSerializer.deserialize(element, InfectionConfiguration::class.java)
    }

    companion object {
        const val INFECTION = "Infection"
        const val INFECTION_PATH = "InfectionPath"
        const val INFECTION_ROOT_NAME = "Infection_settings"
    }
}
