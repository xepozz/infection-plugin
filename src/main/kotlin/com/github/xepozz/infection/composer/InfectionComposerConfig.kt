package com.github.xepozz.infection.composer

import com.github.xepozz.infection.InfectionOpenSettingsProvider
import com.github.xepozz.infection.InfectionQualityToolType
import com.github.xepozz.infection.InfectionValidationInspection
import com.github.xepozz.infection.config.InfectionConfiguration
import com.github.xepozz.infection.config.InfectionConfigurationManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.SystemInfo
import com.jetbrains.php.tools.quality.QualityToolsComposerConfig

class InfectionComposerConfig : QualityToolsComposerConfig<InfectionConfiguration, InfectionValidationInspection>(
    PACKAGE,
    RELATIVE_PATH
) {
    override fun getQualityInspectionShortName() = InfectionQualityToolType.Companion.INSTANCE.inspectionId

    override fun getConfigurationManager(project: Project) = InfectionConfigurationManager.Companion.getInstance(project)

    override fun getSettings() = InfectionOpenSettingsProvider.Companion.INSTANCE

    companion object {
        private const val PACKAGE: String = "infection/infection"
        private val RELATIVE_PATH: String = "bin/infection${if (SystemInfo.isWindows) ".exe" else ""}"
    }
}
