package com.github.xepozz.infection

import com.intellij.codeInsight.intention.IntentionAction
import com.intellij.codeInsight.intention.preview.IntentionPreviewInfo
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.LogicalPosition
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFile

class ShowDiffAction(val code: String, val content: String, val line: Int) : IntentionAction {
    override fun getFamilyName() = "Infection"
    override fun getText() = "Apply Mutator `${code}`"

    override fun generatePreview(project: Project, editor: Editor, file: PsiFile): IntentionPreviewInfo {
        val originalText = content
            .lines()
            .filter { it.startsWith('-') }
            .joinToString("\n") { it.substring(1) }
        val modifiedText = content
            .lines()
            .filter { it.startsWith('+') }
            .joinToString("\n") { it.substring(1) }

//        println("generatePreview: $originalText, $modifiedText")

        return IntentionPreviewInfo.CustomDiff(file.fileType, originalText, modifiedText)
    }
    override fun invoke(project: Project, editor: Editor, file: PsiFile?) {
//        editor.document.insertString(
//            editor.logicalPositionToOffset(LogicalPosition(line, 0)),
//            "/** @infection-ignore analysis:${code} */\n"
//        )
//        PsiDocumentManager.getInstance(project).commitDocument(editor.document)
//        DaemonCodeAnalyzer.getInstance(project).restart()
    }

    override fun startInWriteAction() = false
    override fun isAvailable(project: Project, editor: Editor, file: PsiFile) = true
}