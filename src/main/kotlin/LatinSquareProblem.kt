class LatinSquareProblem(
        private val n: Int,
        override val domains: List<Domain> = List(n * n) { List(n) { it + 1 } },
        private val square: Array<Array<Int>> = Array(n) { Array(n) { 0 } }
) : Problem {

    override val numberOfVariables = n * n

    override val currentResult: String
        get() = square.contentDeepToString()

    override val someDomainEmpty: Boolean
        get() = domains.any { it.isEmpty() }

    override fun domainOfVariable(variableIndex: Int): Domain = domains[variableIndex]

    override fun setVariable(variableIndex: Int, value: Int) {
        square[variableIndex / n][variableIndex % n] = value
    }

    override fun areConstrainsSatisfied(variableIndex: Int, value: Int): Boolean {
        val rowIndex = variableIndex / n
        val columnIndex = variableIndex % n
        return !alreadyInRow(rowIndex, columnIndex, value) && !alreadyInColumn(rowIndex, columnIndex, value)
    }

    private fun alreadyInRow(rowIndex: Int, columnIndex: Int, value: Int): Boolean {
        for (previousColumns in 0 until columnIndex) {
            if (square[rowIndex][previousColumns] == value) {
                return true
            }
        }
        return false
    }

    private fun alreadyInColumn(rowIndex: Int, columnIndex: Int, value: Int): Boolean {
        for (previousRow in 0 until rowIndex) {
            if (square[previousRow][columnIndex] == value) {
                return true
            }
        }
        return false
    }

    override fun updateDomains(variableIndex: Int, value: Int): Problem { // todo:
        val rowIndex = variableIndex / n
        val columnIndex = variableIndex % n
        updateDomainsInRow(rowIndex)
        val newDomains = domains.toMutableList()
        var increment = 1
        for (columnIndex in (variableIndex + 1) until numberOfVariables) {
            newDomains[columnIndex] = newDomains[columnIndex] - value
            newDomains[columnIndex] = newDomains[columnIndex] - (value + increment)
            newDomains[columnIndex] = newDomains[columnIndex] - (value - increment)
            ++increment
        }
        return LatinSquareProblem(n, newDomains, square) // todo: Clone?
    }

    private fun updateDomainsInRow(rowIndex: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
