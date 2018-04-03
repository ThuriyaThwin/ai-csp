package chooser

open class Chooser(protected val numberOfVariables: Int) {
    open fun actualVariableIndex(variableIndex: Int) = variableIndex
}
