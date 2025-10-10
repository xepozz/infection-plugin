package com.github.xepozz.infection

import com.jetbrains.php.tools.quality.QualityToolMessage
import com.jetbrains.php.tools.quality.QualityToolXmlMessageProcessor

class InfectionProblemDescription(
    severity: QualityToolMessage.Severity?,
    startLine: Int,
    val startColumn: Int,
    val endLine: Int,
    val endColumn: Int,
    var myMessage: String,
    val myFile: String,
    val code: String,
    val content: String,
) : QualityToolXmlMessageProcessor.ProblemDescription(
    severity,
    startLine,
    startColumn,
    myMessage,
    myFile,
)
