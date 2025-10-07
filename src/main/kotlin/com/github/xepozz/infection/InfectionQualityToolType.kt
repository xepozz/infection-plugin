package com.github.xepozz.infection

import com.github.xepozz.infection.config.InfectionConfigurable
import com.github.xepozz.infection.config.InfectionConfigurableForm
import com.github.xepozz.infection.config.InfectionConfiguration
import com.github.xepozz.infection.config.InfectionConfigurationBaseManager.Companion.INFECTION
import com.github.xepozz.infection.config.InfectionConfigurationManager
import com.github.xepozz.infection.config.InfectionConfigurationProvider
import com.github.xepozz.infection.config.InfectionProjectConfiguration
import com.intellij.codeInspection.InspectionProfile
import com.intellij.openapi.options.Configurable
import com.intellij.openapi.project.Project
import com.intellij.profile.codeInspection.InspectionProjectProfileManager
import com.intellij.util.ObjectUtils.tryCast
import com.jetbrains.php.tools.quality.QualityToolType
import com.jetbrains.php.tools.quality.QualityToolValidationGlobalInspection

class InfectionQualityToolType : QualityToolType<InfectionConfiguration>() {
    override fun getDisplayName() = INFECTION

    override fun getQualityToolBlackList(project: Project) = InfectionBlackList.getInstance(project)

    override fun getConfigurationManager(project: Project) = InfectionConfigurationManager.getInstance(project)

    public override fun getInspection() = InfectionValidationInspection()

    override fun getConfigurationProvider() = InfectionConfigurationProvider.getInstances()

    override fun createConfigurableForm(project: Project, settings: InfectionConfiguration) =
        InfectionConfigurableForm(project, settings)

    override fun getToolConfigurable(project: Project): Configurable = InfectionConfigurable(project)

    public override fun getProjectConfiguration(project: Project) =
        InfectionProjectConfiguration.getInstance(project)

    override fun createConfiguration() = InfectionConfiguration()

//    override fun getInspectionId() = "InfectionGlobal"

    override fun getHelpTopic() = "reference.settings.php.infection"

//    override fun getGlobalTool(project: Project, profile: InspectionProfile?): QualityToolValidationGlobalInspection? {
//        val newProfile = profile ?: InspectionProjectProfileManager.getInstance(project).currentProfile
//
//        println("newProfile: $newProfile")
//        val inspectionTool = newProfile.getInspectionTool(inspectionId, project) ?: return null
//
//        println("inspectionTool: $inspectionTool")
//        return tryCast(inspectionTool.tool, InfectionGlobalInspection::class.java)
//            .apply { println("tryCast: $this") }
//    }

//    override fun getInspectionShortName(project: Project) = getGlobalTool(project, null)?.shortName
//        ?: inspection.shortName

    companion object {
        val INSTANCE = InfectionQualityToolType()
    }
}
