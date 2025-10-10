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
    var startParsing = false
    val buffer = StringBuffer()

    override fun getQualityToolType() = InfectionQualityToolType.INSTANCE

    override fun processStdErrMessages(error: StringBuffer?): Boolean {
        println("processStdErrMessages: $error")
        return super.processStdErrMessages(error)
    }

    override fun parseLine(line: String) {
        val outputLine = line.trim()

//        println("parseLine $outputLine for $info")
        if (!startParsing) {
            if (!outputLine.startsWith("[")) {
                return
            }
            startParsing = true
        }

        buffer.append(outputLine)
    }

    override fun severityToDisplayLevel(severity: QualityToolMessage.Severity) =
        HighlightDisplayLevel.find(severity.name)

    override fun done() {
//        println("done: ${info}")

//        println("resultContent length: ${resultContent.length}")

        InfectionJsonMessageHandler(info.project)
            .parseJson(buffer.toString())
            .filter { it.myFile == this.file.virtualFile.canonicalPath }
            .forEach { problem ->
                val document = this.file.fileDocument
                addMessage(
                    QualityToolMessage(
                        this,
                        TextRange(
                            document.getLineStartOffset(problem.lineNumber - 1) + problem.startColumn - 1,
                            document.getLineStartOffset(problem.endLine - 1) + problem.endColumn,
                        ),
                        problem.severity,
                        problem.message,
                        ShowDiffAction(problem.code, problem.content, problem.lineNumber)
                    )
                )
                return
            }
    }
}
