package com.github.xepozz.infection.config

import com.github.xepozz.infection.InfectionCustomOptionsForm
import com.github.xepozz.infection.InfectionQualityToolType
import com.github.xepozz.infection.config.InfectionConfigurationBaseManager.Companion.INFECTION
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.Pair
import com.jetbrains.php.PhpBundle
import com.jetbrains.php.tools.quality.QualityToolConfigurableForm
import com.jetbrains.php.tools.quality.QualityToolConfiguration
import com.jetbrains.php.tools.quality.QualityToolType

// TODO: change to PhpStanOptionsPanel
class InfectionConfigurableForm(project: Project, configuration: InfectionConfiguration) :
    QualityToolConfigurableForm<InfectionConfiguration>(project, configuration, INFECTION, "Infection") {
    override fun getQualityToolType(): QualityToolType<QualityToolConfiguration> {
        try {
            @Suppress("UNCHECKED_CAST")
            return InfectionQualityToolType.INSTANCE as QualityToolType<QualityToolConfiguration>
        } catch (e: Throwable) {
            throw e
        }
    }

    override fun getCustomConfigurable(project: Project, configuration: InfectionConfiguration) =
        InfectionCustomOptionsForm(project, configuration)

    override fun getHelpTopic() = "reference.settings.php.infection"

    override fun validateWithNoAnsi() = false

    override fun validateMessage(message: String): Pair<Boolean, String> {
        val regex = Regex("^infection (?<version>.+)$")

        return regex.find(message)?.groups?.get("version")
            ?.let { Pair.create(true, "OK, Infection version ${it.value}") }
            ?: Pair.create(false, PhpBundle.message("quality.tool.can.not.determine.version", message))
    }
}
