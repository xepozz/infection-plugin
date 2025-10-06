package com.github.xepozz.infection

import com.github.xepozz.infection.config.InfectionConfiguration
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogPanel
import com.intellij.openapi.util.Pair
import com.intellij.ui.dsl.builder.AlignX
import com.intellij.ui.dsl.builder.bindText
import com.intellij.ui.dsl.builder.panel
import com.jetbrains.php.tools.quality.QualityToolCustomSettings

class InfectionCustomOptionsForm(
    private val project: Project,
    private val configuration: InfectionConfiguration,
) : QualityToolCustomSettings() {

    // TODO: maybe use InfectionConfiguration?
    data class Model(
        var customParameters: String = "",
    )

    private lateinit var panel: DialogPanel
    private val model = Model()

    override fun createComponent() = panel {
        row("Other parameters:") {
            expandableTextField()
                .align(AlignX.FILL)
                .bindText(model::customParameters)
        }
    }.also { panel = it }

    override fun isModified() = panel.isModified()

    override fun apply() {
        panel.apply()

        configuration.customParameters = model.customParameters
    }

    override fun reset() {
        model.customParameters = configuration.customParameters

        panel.reset()
    }

    override fun getDisplayName() = null

    override fun validate() = Pair.create(false, "")
}
