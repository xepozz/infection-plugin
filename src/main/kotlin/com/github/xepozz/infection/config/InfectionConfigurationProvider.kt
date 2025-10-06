package com.github.xepozz.infection.config

import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.extensions.ExtensionPointName
import com.jetbrains.php.tools.quality.QualityToolConfigurationProvider

abstract class InfectionConfigurationProvider : QualityToolConfigurationProvider<InfectionConfiguration>() {
    companion object {
        private val LOG: Logger = Logger.getInstance(InfectionConfigurationProvider::class.java)
        private val EP_NAME: ExtensionPointName<InfectionConfigurationProvider> =
            ExtensionPointName.create("com.github.xepozz.infection.infectionConfigurationProvider")

        fun getInstances(): InfectionConfigurationProvider? {
            val extensions: Array<InfectionConfigurationProvider> = EP_NAME.extensions
            if (extensions.size > 1) {
                LOG.error("Several providers for remote Infection configuration was found")
            }
            return if (extensions.isEmpty()) null else extensions[0]
        }
    }
}
