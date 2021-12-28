package cn.vove7.plugin.rest.action

import com.intellij.icons.AllIcons
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

import cn.vove7.plugin.rest.tool.RequestExecutor

class StopAction @JvmOverloads constructor(
        private val executor: RequestExecutor? = null
) : AnAction(AllIcons.Actions.Suspend) {

    override fun update(e: AnActionEvent) {
        super.update(e)
        if (executor == null) {
            e.presentation.isEnabled = false
        } else {
            e.presentation.isEnabled = executor.isRunning
        }
    }

    override fun actionPerformed(e: AnActionEvent) {
        if (executor != null && executor.isRunning) {
            executor.stop()
        }
    }

    companion object {
        val ID = "rest.action.stop"
    }

}
