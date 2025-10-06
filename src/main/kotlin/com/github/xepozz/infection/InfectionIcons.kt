package com.github.xepozz.infection

import com.intellij.openapi.util.IconLoader

// https://intellij-icons.jetbrains.design
// https://plugins.jetbrains.com/docs/intellij/icons.html#new-ui-tool-window-icons
// https://plugins.jetbrains.com/docs/intellij/icons-style.html
object InfectionIcons {
    @JvmField
    val INFECTION = IconLoader.getIcon("/icons/infection/icon.svg", this::class.java)
}