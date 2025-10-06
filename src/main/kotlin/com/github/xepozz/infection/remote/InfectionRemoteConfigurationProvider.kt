package com.github.xepozz.infection.remote

import com.github.xepozz.infection.config.InfectionConfigurableForm
import com.github.xepozz.infection.config.InfectionConfiguration
import com.github.xepozz.infection.config.InfectionConfigurationProvider
import com.intellij.openapi.project.Project
import com.intellij.util.xmlb.XmlSerializer
import com.jetbrains.php.config.interpreters.PhpInterpreter
import com.jetbrains.php.remote.tools.quality.QualityToolByInterpreterConfigurableForm
import com.jetbrains.php.tools.quality.QualityToolConfigurableForm
import org.jdom.Element

class InfectionRemoteConfigurationProvider : InfectionConfigurationProvider() {
    override fun canLoad(tagName: String) = tagName == INFECTION_BY_INTERPRETER

    override fun load(element: Element) = XmlSerializer.deserialize(element, InfectionRemoteConfiguration::class.java)

    override fun createConfigurationForm(
        project: Project,
        settings: InfectionConfiguration,
    ): QualityToolConfigurableForm<*>? {
        if (settings !is InfectionRemoteConfiguration) {
            return null
        }

        val delegate = InfectionConfigurableForm(project, settings)
        return QualityToolByInterpreterConfigurableForm(
            project,
            settings,
            delegate,
        )
    }

    override fun createNewInstance(
        project: Project?,
        existingSettings: List<InfectionConfiguration>,
    ): InfectionConfiguration? = null

    override fun createConfigurationByInterpreter(interpreter: PhpInterpreter): InfectionConfiguration {
        val settings = InfectionRemoteConfiguration()
        settings.setInterpreterId(interpreter.id)
        return settings
    }

    companion object {
        private const val INFECTION_BY_INTERPRETER: String = "infection_by_interpreter"
    }
}
