package com.github.xepozz.infection

import com.intellij.openapi.options.ShowSettingsUtil
import com.intellij.openapi.project.Project
import com.jetbrains.php.composer.actions.log.ComposerLogMessageBuilder

class InfectionOpenSettingsProvider : ComposerLogMessageBuilder.Settings("\u300C") {
    override fun show(project: Project) {
        ShowSettingsUtil.getInstance()
            .showSettingsDialog(project, InfectionBundle.message("configurable.quality.tool.php.infection"))
    }

    companion object {
        val INSTANCE = InfectionOpenSettingsProvider()
    }
}
