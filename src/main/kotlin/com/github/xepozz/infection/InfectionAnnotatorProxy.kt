package com.github.xepozz.infection

import com.intellij.codeInspection.InspectionProfile
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFile
import com.jetbrains.php.tools.quality.QualityToolAnnotator
import com.jetbrains.php.tools.quality.QualityToolAnnotatorInfo
import com.jetbrains.php.tools.quality.QualityToolConfiguration

open class InfectionAnnotatorProxy : QualityToolAnnotator<com.github.xepozz.infection.InfectionValidationInspection>() {
    companion object {
        private val LOG: Logger = Logger.getInstance(InfectionAnnotatorProxy::class.java)

        val INSTANCE = InfectionAnnotatorProxy()

        fun getFormatOptions(projectPath: String, files: Collection<String>) = buildList {
            add("--workspace=$projectPath")

            add("fmt")
            addAll(files)
        }
//            .apply { println("format options: ${this.joinToString(" ")}") }

        fun getAnalyzeOptions(projectPath: String, filePath: String?) = buildList {
            add("--workspace=$projectPath")

            add("analyze")
            add("--reporting-format=json")
//            filePath?.let { add(it) }
        }
//            .apply { println("analyze options: ${this.joinToString(" ")}") }
    }

    override fun getOptions(
        filePath: String?,
        inspection: com.github.xepozz.infection.InfectionValidationInspection,
        profile: InspectionProfile?,
        project: Project,
    ): List<String> {
        val projectPath = project.basePath ?: return emptyList()

        return getAnalyzeOptions(projectPath, filePath)
    }

    override fun createAnnotatorInfo(
        file: PsiFile?,
        tool: com.github.xepozz.infection.InfectionValidationInspection,
        inspectionProfile: InspectionProfile,
        project: Project,
        configuration: QualityToolConfiguration,
        isOnTheFly: Boolean,
    ): QualityToolAnnotatorInfo<com.github.xepozz.infection.InfectionValidationInspection> {
        if (!isOnTheFly) {
            LOG.warn("isOnTheFly is False")
        }

        return QualityToolAnnotatorInfo(file, tool, inspectionProfile, project, configuration, false)
    }

    override fun getQualityToolType() = _root_ide_package_.com.github.xepozz.infection.InfectionQualityToolType.INSTANCE

    override fun createMessageProcessor(collectedInfo: QualityToolAnnotatorInfo<com.github.xepozz.infection.InfectionValidationInspection>) =
        _root_ide_package_.com.github.xepozz.infection.InfectionMessageProcessor(collectedInfo)

    override fun getPairedBatchInspectionShortName() = qualityToolType.inspectionId

    override fun runOnTempFiles() = false
}
