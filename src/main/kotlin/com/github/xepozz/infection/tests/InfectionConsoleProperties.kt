package com.github.xepozz.infection.tests

import com.github.xepozz.infection.InfectionBundle
import com.github.xepozz.infection.tests.run.InfectionRunConfiguration
import com.intellij.execution.Executor
import com.intellij.execution.testframework.sm.runner.SMTRunnerConsoleProperties

class InfectionConsoleProperties(
    config: InfectionRunConfiguration,
    executor: Executor,
) : SMTRunnerConsoleProperties(config, InfectionBundle.message("infection.local.run.display.name"), executor) {

    override fun isPrintTestingStartedTime() = false
}