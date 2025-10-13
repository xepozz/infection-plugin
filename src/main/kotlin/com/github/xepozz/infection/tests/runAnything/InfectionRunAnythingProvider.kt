package com.github.xepozz.infection.tests.runAnything

import com.github.xepozz.infection.InfectionBundle
import com.github.xepozz.infection.InfectionIcons
import com.github.xepozz.infection.tests.actions.InfectionRunCommandAction
import com.intellij.icons.AllIcons
import com.intellij.ide.actions.runAnything.activity.RunAnythingAnActionProvider
import com.intellij.openapi.actionSystem.DataContext

class InfectionRunAnythingProvider : RunAnythingAnActionProvider<InfectionRunCommandAction>() {
    override fun getCommand(value: InfectionRunCommandAction) =
        InfectionBundle.message("action.run.target.command", value.commandName)

    override fun getHelpCommandPlaceholder() = "infection <command>"

    override fun getCompletionGroupTitle() = "Infection"

    override fun getHelpCommand() = "infection"

    override fun getHelpGroupTitle() = "PHP"

    override fun getHelpIcon() = InfectionIcons.INFECTION

    override fun getIcon(value: InfectionRunCommandAction) = AllIcons.Actions.Execute

    override fun getValues(dataContext: DataContext, pattern: String) = listOf(InfectionRunCommandAction("run"))
}