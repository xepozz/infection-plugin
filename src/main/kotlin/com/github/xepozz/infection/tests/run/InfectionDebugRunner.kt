package com.github.xepozz.infection.tests.run

import com.jetbrains.php.testFramework.run.PhpTestDebugRunner

class InfectionDebugRunner : PhpTestDebugRunner<InfectionRunConfiguration>(InfectionRunConfiguration::class.java) {
    override fun getRunnerId() = "InfectionDebugRunner"
}