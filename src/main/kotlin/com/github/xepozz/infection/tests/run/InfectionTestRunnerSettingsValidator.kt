package com.github.xepozz.infection.tests.run

import com.intellij.psi.PsiFile
import com.jetbrains.php.lang.PhpFileType
import com.jetbrains.php.testFramework.run.PhpDefaultTestRunnerSettingsValidator

object InfectionTestRunnerSettingsValidator: PhpDefaultTestRunnerSettingsValidator(
    listOf(PhpFileType.INSTANCE),
    PhpTestMethodFinder { file: PsiFile, sss: String ->
        println("file: $file: strings: $sss")
        true
    },
    false,
    false,
)