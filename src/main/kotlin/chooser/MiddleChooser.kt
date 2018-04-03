package chooser

class MiddleChooser(numberOfVariables: Int) : Chooser(numberOfVariables) {
    override fun actualVariableIndex(variableIndex: Int) =
        numberOfVariables / 2 + (if (variableIndex % 2 == 0) 1 else -1) * (variableIndex + 1) / 2
}
