package com.github.xepozz.infection

import com.intellij.openapi.project.Project
import com.jetbrains.php.tools.quality.QualityToolAddToIgnoredAction

class InfectionAddToIgnoredAction : QualityToolAddToIgnoredAction() {
    override fun getQualityToolType(project: Project?) = InfectionQualityToolType.INSTANCE
}
