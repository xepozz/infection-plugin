package com.github.xepozz.infection

import com.intellij.codeInspection.ex.ExternalAnnotatorBatchInspection
import com.intellij.openapi.util.Key
import com.jetbrains.php.tools.quality.QualityToolValidationGlobalInspection
import com.jetbrains.php.tools.quality.QualityToolXmlMessageProcessor.ProblemDescription

class InfectionGlobalInspection : QualityToolValidationGlobalInspection(), ExternalAnnotatorBatchInspection {
    override fun getAnnotator() = InfectionAnnotatorProxy.INSTANCE

    override fun worksInBatchModeOnly(): Boolean {
        return true
    }
    override fun getKey() = INFECTION_ANNOTATOR_INFO

    override fun getSharedLocalInspectionTool() = InfectionValidationInspection()

    companion object {
        private val INFECTION_ANNOTATOR_INFO = Key.create<List<ProblemDescription>>("ANNOTATOR_INFO_INFECTION")
    }
}
