package com.github.xepozz.infection.tests.run

import com.github.xepozz.infection.InfectionIcons
import com.intellij.execution.configurations.ConfigurationFactory
import com.intellij.openapi.project.Project

class InfectionRunConfigurationFactory(private val configurationType: InfectionRunConfigurationType) :
    ConfigurationFactory(configurationType) {
    override fun getId() = InfectionRunConfigurationType.ID
    override fun getName() = configurationType.displayName
    override fun getIcon() = InfectionIcons.INFECTION

    override fun createTemplateConfiguration(project: Project) =
        InfectionRunConfiguration(project, this)
}