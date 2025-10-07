package com.github.xepozz.infection

import com.intellij.codeInspection.InspectionProfile
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFile
import com.jetbrains.php.config.interpreters.PhpSdkFileTransfer
import com.jetbrains.php.tools.quality.QualityToolAnnotator
import com.jetbrains.php.tools.quality.QualityToolAnnotatorInfo
import com.jetbrains.php.tools.quality.QualityToolConfiguration
import com.jetbrains.php.tools.quality.QualityToolMessageProcessor

open class InfectionAnnotatorProxy : QualityToolAnnotator<InfectionValidationInspection>() {
    companion object {
        private val LOG: Logger = Logger.getInstance(InfectionAnnotatorProxy::class.java)

        val INSTANCE = InfectionAnnotatorProxy()

        fun getAnalyzeOptions(projectPath: String, filePath: String?) = buildList {
//            ./vendor/bin/infection run --no-progress -q --logger-gitlab=$(pwd)/results.json
            add("run")
            add("--no-progress")
            add("-q")
            add("--logger-gitlab=$projectPath/results.json")
//            filePath?.let { add(it) }
        }
            .apply { println("analyze options: ${this.joinToString(" ")}, file: $filePath") }
    }

    override fun collectInformation(
        file: PsiFile,
        editor: Editor,
        hasErrors: Boolean
    ): QualityToolAnnotatorInfo<InfectionValidationInspection>? {
        val collectInformation = super.collectInformation(file, editor, hasErrors)
        println("collectInformation: $collectInformation for file: ${file.virtualFile.path}")
        return collectInformation
    }
    override fun getOptions(
        filePath: String?,
        inspection: InfectionValidationInspection,
        profile: InspectionProfile?,
        project: Project,
    ): List<String> {
        val projectPath = project.basePath ?: return emptyList()

        return getAnalyzeOptions(projectPath, filePath)
    }

    override fun createAnnotatorInfo(
        file: PsiFile?,
        tool: InfectionValidationInspection,
        inspectionProfile: InspectionProfile,
        project: Project,
        configuration: QualityToolConfiguration,
        isOnTheFly: Boolean,
    ): QualityToolAnnotatorInfo<InfectionValidationInspection> {
        if (!isOnTheFly) {
            LOG.warn("isOnTheFly is False")
        }

        return QualityToolAnnotatorInfo(file, tool, inspectionProfile, project, configuration, false)
    }

    override fun getQualityToolType() = InfectionQualityToolType.INSTANCE

    override fun createMessageProcessor(collectedInfo: QualityToolAnnotatorInfo<InfectionValidationInspection>) =
        InfectionMessageProcessor(collectedInfo)

    override fun getPairedBatchInspectionShortName() = qualityToolType.inspectionId

    override fun runOnTempFiles() = false
}
