package com.github.xepozz.infection.tests.run

import com.github.xepozz.infection.InfectionIcons
import com.intellij.execution.configurations.ConfigurationTypeBase

class InfectionRunConfigurationType : ConfigurationTypeBase(
    ID,
    "Infection Test",
    null,
    InfectionIcons.INFECTION,
) {
    init {
        addFactory(InfectionRunConfigurationFactory(this))
    }

    override fun getIcon() = InfectionIcons.INFECTION

    companion object {
        const val ID = "InfectionRunConfiguration"

        val INSTANCE = InfectionRunConfigurationType()
    }
}