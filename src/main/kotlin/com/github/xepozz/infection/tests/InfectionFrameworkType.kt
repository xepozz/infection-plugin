package com.github.xepozz.infection.tests

import com.github.xepozz.infection.InfectionBundle
import com.github.xepozz.infection.InfectionIcons
import com.jetbrains.php.testFramework.PhpTestFrameworkType

class InfectionFrameworkType : PhpTestFrameworkType() {
    override fun getDisplayName() = InfectionBundle.message("infection.local.run.display.name")

    override fun getID() = ID

    override fun getIcon() = InfectionIcons.INFECTION

//    override fun getHelpTopic() = "reference.webide.settings.project.settings.php.infection"

    override fun getComposerPackageNames() = arrayOf("infection/infection")

    companion object {
        val ID = "Infection"
        val INSTANCE: InfectionFrameworkType
            get() = getTestFrameworkType(ID) as InfectionFrameworkType
    }
}
