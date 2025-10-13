package com.github.xepozz.infection.tests.actions

import com.github.xepozz.infection.InfectionBundle
import com.github.xepozz.infection.InfectionIcons
import com.github.xepozz.infection.tests.run.InfectionRunConfiguration
import com.github.xepozz.infection.tests.run.InfectionRunConfigurationProducer
import com.intellij.execution.Executor
import com.intellij.execution.RunManagerEx
import com.intellij.execution.runners.ExecutionUtil
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

class InfectionRunCommandAction(val commandName: String) : AnAction() {
    init {
        templatePresentation.setText(InfectionBundle.message("action.run.target.text", commandName), false)
        templatePresentation.description = InfectionBundle.message("action.run.target.description", commandName)
        templatePresentation.icon = InfectionIcons.INFECTION
    }

    override fun actionPerformed(event: AnActionEvent) {
        val project = event.project ?: return

        val runManager = RunManagerEx.getInstanceEx(project)
        val producer = InfectionRunConfigurationProducer()
        val configurationFactory = producer.configurationFactory

        val runConfiguration = InfectionRunConfiguration(
            project,
            configurationFactory,
//            InfectionBundle.message("action.run.target.command", commandName),
        )
//            .apply { settings.commandName = commandName }

        val configuration = runManager.createConfiguration(runConfiguration, configurationFactory)

        runManager.setTemporaryConfiguration(configuration)
        ExecutionUtil.runConfiguration(configuration, Executor.EXECUTOR_EXTENSION_NAME.extensionList.first())
    }
}