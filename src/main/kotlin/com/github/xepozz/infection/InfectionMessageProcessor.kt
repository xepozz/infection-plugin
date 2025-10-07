package com.github.xepozz.infection

import com.intellij.codeHighlighting.HighlightDisplayLevel
import com.intellij.openapi.util.TextRange
import com.jetbrains.php.tools.quality.QualityToolAnnotatorInfo
import com.jetbrains.php.tools.quality.QualityToolMessage
import com.jetbrains.php.tools.quality.QualityToolMessageProcessor
import java.io.File

// it does analysis everytime when you change a file in the file editor
// should be optimized
class InfectionMessageProcessor(private val info: QualityToolAnnotatorInfo<*>) : QualityToolMessageProcessor(info) {
    override fun getQualityToolType() = InfectionQualityToolType.INSTANCE

//    override fun getMessagePrefix() = "Infection"

    override fun processStdErrMessages(error: StringBuffer?): Boolean {
        println("processStdErrMessages: $error")
        return super.processStdErrMessages(error)
    }
    override fun parseLine(line: String) {}

    override fun severityToDisplayLevel(severity: QualityToolMessage.Severity) =
        HighlightDisplayLevel.find(severity.name)

    override fun done() {
        println("done: ${info}")

        val project = info.project
        val resultContent = File("${project.basePath}/results.json").readText()
        val messageHandler = InfectionJsonMessageHandler(project)

//        println("resultContent length: ${resultContent.length}")

        messageHandler.parseJson(resultContent)
//            .apply { println("parsed problems: $this") }
            .filter { it.myFile == this.file.virtualFile.canonicalPath }
//            .apply { println("problemList: $this") }
            .forEach { problem ->
                addMessage(
                    QualityToolMessage(
                        this,
                        problem.lineNumber,
//                        TextRange(problem.startChar, problem.endChar),
                        problem.severity,
                        problem.message,
//                        MarkIgnoreAction(problem.code, problem.lineNumber)
                        ShowDiffAction(problem.code, problem.content, problem.lineNumber)
                    )
                )
            }
    }
}
