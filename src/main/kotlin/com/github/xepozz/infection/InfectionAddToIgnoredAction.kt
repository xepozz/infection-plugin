package com.github.xepozz.infection

import com.github.xepozz.infection.config.InfectionConfiguration
import com.intellij.openapi.project.Project
import com.jetbrains.php.tools.quality.QualityToolAddToIgnoredAction
import com.jetbrains.php.tools.quality.QualityToolType

class InfectionAddToIgnoredAction : QualityToolAddToIgnoredAction() {
    override fun getQualityToolType(project: Project?): QualityToolType<InfectionConfiguration> {
        return _root_ide_package_.com.github.xepozz.infection.InfectionQualityToolType.INSTANCE
    }
}
