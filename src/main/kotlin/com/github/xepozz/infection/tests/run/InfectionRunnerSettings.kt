package com.github.xepozz.infection.tests.run

import com.intellij.util.xmlb.annotations.Attribute
import com.intellij.util.xmlb.annotations.Tag
import com.jetbrains.php.phpunit.coverage.PhpUnitCoverageEngine.CoverageEngine
import com.jetbrains.php.testFramework.run.PhpTestRunnerSettings

@Tag("InfectionRunner")
data class InfectionRunnerSettings(
    @Attribute("coverage_engine")
    var coverageEngine: CoverageEngine = CoverageEngine.XDEBUG,
    @Attribute("parallel_testing_enabled")
    var parallelTestingEnabled: Boolean = false,
) : PhpTestRunnerSettings() {
    companion object {
        @JvmStatic
        fun fromPhpTestRunnerSettings(settings: PhpTestRunnerSettings): InfectionRunnerSettings {
            val infectionSettings = InfectionRunnerSettings()

            infectionSettings.scope = settings.scope
            infectionSettings.selectedType = settings.selectedType
            infectionSettings.directoryPath = settings.directoryPath
            infectionSettings.filePath = settings.filePath
            infectionSettings.methodName = settings.methodName
            infectionSettings.isUseAlternativeConfigurationFile = settings.isUseAlternativeConfigurationFile
            infectionSettings.configurationFilePath = settings.configurationFilePath
            infectionSettings.testRunnerOptions = settings.testRunnerOptions

            return infectionSettings
        }
    }
}