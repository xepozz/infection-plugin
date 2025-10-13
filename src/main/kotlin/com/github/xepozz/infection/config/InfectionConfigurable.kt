package com.github.xepozz.infection.config

import com.github.xepozz.infection.InfectionBundle
import com.github.xepozz.infection.InfectionQualityToolType
import com.intellij.codeInsight.daemon.HighlightDisplayKey
import com.intellij.openapi.options.Configurable
import com.intellij.openapi.options.ex.ConfigurableExtensionPointUtil
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogPanel
import com.intellij.profile.codeInspection.InspectionProfileManager
import com.intellij.profile.codeInspection.ui.ErrorsConfigurable
import com.intellij.profile.codeInspection.ui.ErrorsConfigurableProvider
import com.intellij.ui.components.OnOffButton
import com.intellij.ui.dsl.builder.bindSelected
import com.intellij.ui.dsl.builder.panel
import com.jetbrains.php.lang.inspections.PhpInspectionsUtil

class InfectionConfigurable(val project: Project) : Configurable {
    val settings = project.getService(InfectionProjectConfiguration::class.java)
    val inspectionProfileManager = InspectionProfileManager.getInstance(project)

    lateinit var myPanel: DialogPanel

    override fun getDisplayName() = "Infection"

    override fun getHelpTopic() = "reference.settings.php.infection"

    fun getQualityToolType() = InfectionQualityToolType.INSTANCE

    fun getInspectionShortName() = getQualityToolType().getInspectionShortName(project)

    override fun createComponent(): DialogPanel {

        return panel {

            group("Features") {
                row {
                    cell(
                        PhpInspectionsUtil.createPanelWithSettingsLink(
                            InfectionBundle.message("quality.tool.settings.link.inspection", "Infection"),
                            ErrorsConfigurable::class.java,
                            {
                                ConfigurableExtensionPointUtil.createProjectConfigurableForProvider(
                                    project,
                                    ErrorsConfigurableProvider::class.java
                                ) as ErrorsConfigurable?
                            },
                            { it.selectInspectionTool(getInspectionShortName()) })
                    )
                    cell(OnOffButton())
                        .bindSelected({
                            inspectionProfileManager.currentProfile
                                .isToolEnabled(HighlightDisplayKey.find(getInspectionShortName()))
                        }, {
                            inspectionProfileManager.currentProfile
                                .setToolEnabled(getInspectionShortName(), it)
                        })
                }
            }
            group("Options") {
                row {
                    label("Path to Infection executable")
                    textField()
                    browserLink("Download Infection", "https://github.com/infection/infection")
                }
                row {
                    label("Additional parameters")
                    textField()
                }
            }
        }.also { myPanel = it }
    }

    override fun isModified(): Boolean {
        return this.myPanel.isModified()
    }

    override fun apply() {
        this.myPanel.apply()
    }

    override fun reset() {
        this.myPanel.reset()
    }
}

