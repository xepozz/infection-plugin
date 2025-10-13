package com.github.xepozz.infection.tests.run

import com.github.xepozz.infection.InfectionBundle
import com.github.xepozz.infection.tests.InfectionConsoleProperties
import com.github.xepozz.infection.tests.InfectionFrameworkType
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.execution.Executor
import com.intellij.execution.configurations.ConfigurationFactory
import com.intellij.execution.testframework.sm.runner.SMTRunnerConsoleProperties
import com.intellij.openapi.project.Project
import com.intellij.util.TextFieldCompletionProvider
import com.jetbrains.php.config.commandLine.PhpCommandLinePathProcessor
import com.jetbrains.php.run.PhpAsyncRunConfiguration
import com.jetbrains.php.run.remote.PhpRemoteInterpreterManager
import com.jetbrains.php.testFramework.PhpTestFrameworkConfiguration
import com.jetbrains.php.testFramework.run.PhpTestRunConfiguration
import com.jetbrains.php.testFramework.run.PhpTestRunConfigurationSettings
import com.jetbrains.php.testFramework.run.PhpTestRunnerConfigurationEditor

class InfectionRunConfiguration(project: Project, factory: ConfigurationFactory) : PhpTestRunConfiguration(
    project,
    factory,
    InfectionBundle.message("infection.local.run.display.name"),
    InfectionFrameworkType.INSTANCE,
    InfectionTestRunnerSettingsValidator,
    InfectionRunConfigurationHandler.INSTANCE,
), PhpAsyncRunConfiguration {
    override fun createMethodFieldCompletionProvider(editor: PhpTestRunnerConfigurationEditor): TextFieldCompletionProvider {
        println("createMethodFieldCompletionProvider $editor")
        return object : TextFieldCompletionProvider() {
            override fun addCompletionVariants(text: String, offset: Int, prefix: String, result: CompletionResultSet) {
                println("addCompletionVariants: $text, $offset, $prefix")
            }
        }
    }

    override fun getWorkingDirectory(
        project: Project,
        settings: PhpTestRunConfigurationSettings,
        config: PhpTestFrameworkConfiguration?
    ) = project.basePath


    override fun createTestConsoleProperties(executor: Executor): SMTRunnerConsoleProperties {
        println("createTestConsoleProperties")
        val manager = PhpRemoteInterpreterManager.getInstance()

        val pathProcessor = when (this.interpreter?.isRemote) {
            true -> manager?.createPathMapper(this.project, interpreter!!.phpSdkAdditionalData)
            else -> null
        } ?: PhpCommandLinePathProcessor.LOCAL

        val pathMapper = pathProcessor.createPathMapper(this.project)
        return InfectionConsoleProperties(
            this,
            executor,
//            InfectionLocationProvider(pathMapper, this.project, this.getConfigurationFileRootPath())
        )
    }

    companion object {
        const val ID = "InfectionConsoleCommandRunConfiguration"

//        val INSTANCE = InfectionRunConfiguration()
    }
}