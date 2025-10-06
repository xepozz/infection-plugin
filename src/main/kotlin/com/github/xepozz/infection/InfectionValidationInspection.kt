package com.github.xepozz.infection

import com.github.xepozz.infection.config.InfectionConfigurationBaseManager.Companion.INFECTION
import com.jetbrains.php.tools.quality.QualityToolValidationInspection

class InfectionValidationInspection : QualityToolValidationInspection<InfectionValidationInspection>() {
    override fun getAnnotator() = InfectionAnnotatorProxy.INSTANCE

    override fun getToolName() = INFECTION
}
