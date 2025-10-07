package com.github.xepozz.infection

import com.google.gson.JsonParser
import com.jetbrains.php.tools.quality.QualityToolMessage

class InfectionJsonMessageHandler(val project: com.intellij.openapi.project.Project) {
    fun parseJson(line: String): List<InfectionProblemDescription> {
        return JsonParser.parseString(line)
//            .apply { println("parseJson: $this") }
            .apply { if (this == null || this.isJsonNull) return emptyList() }
            .asJsonArray
            .map { it.asJsonObject }
            .map { issue ->
                val location = issue.get("location").asJsonObject

                InfectionProblemDescription(
                    levelToSeverity(issue.get("severity").asString),
                    location.get("lines").asJsonObject.get("begin").asInt,
                    0,
                    0,
                    "Infection: ${issue.get("description").asString.trimEnd('.')}",
                    "${project.basePath}/${location.get("path").asString}",
                    issue.get("check_name").asString,
                    issue.get("content").asString,
                )
            }
    }

    fun levelToSeverity(level: String?) = when (level) {
        "major" -> QualityToolMessage.Severity.ERROR
        else -> null
    }
}

/**
 * {
 *     "type": "issue",
 *     "fingerprint": "d39f7f31059e2d3f21d8f04f057b7c48",
 *     "check_name": "ConcatOperandRemoval",
 *     "description": "Escaped Mutant for Mutator ConcatOperandRemoval",
 *     "content": "@@ @@\n     }\n     public function __toString(): string\n     {\n-        return 'Bird{' . 'species=' . $this->species . ', sex=' . $this->sex . ', age=' . $this->age . ', legs=' . $this->getLegsCount() . '}';\n+        return 'Bird{' . 'species=' . $this->species . $this->sex . ', age=' . $this->age . ', legs=' . $this->getLegsCount() . '}';\n     }\n }",
 *     "categories": [
 *       "Escaped Mutant"
 *     ],
 *     "location": {
 *       "path": "src\/Bird.php",
 *       "lines": {
 *         "begin": 64
 *       }
 *     },
 *     "severity": "major"
 *   },
 */