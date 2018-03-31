class LatinSquareProblem(
        private val n: Int,
        private val domains: List<List<Domain>> = List(n) { List(n) { List(n) { it + 1 } } },
        private val square: Array<Array<Int>> = Array(n) { Array(n) { 0 } }
) : Problem {

    override val numberOfVariables = n * n

    override val currentResult: String
        get() = square.contentDeepToString()

    override val someDomainEmpty: Boolean
        get() = domains.any { it.isEmpty() }

    override fun domainOfVariable(variableIndex: Int): Domain {
        val rowIndex = variableIndex / n
        val columnIndex = variableIndex % n
        return domains[rowIndex][columnIndex]
    }

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
        val newDomains = domains.toMutableList()
        newDomains[rowIndex] = newDomainsInRow(rowIndex, value)
        val newDomains2 = updateDomainsInColumn(columnIndex, value, newDomains)
        return LatinSquareProblem(n, newDomains2, square) // todo: Clone?
    }

    private fun updateDomainsInColumn(columnIndex: Int, value: Int, newDomains: List<List<Domain>>) =
            newDomains.map { updateRow(it, columnIndex, value) }

    private fun updateRow(row: List<Domain>, columnIndex: Int, value: Int): List<Domain> =
            row.mapIndexed { index, domain ->
                if (index == columnIndex) {
                    domain - value
                } else domain
            }

    private fun newDomainsInRow(rowIndex: Int, valueToRemove: Int) = domains[rowIndex].map { it - valueToRemove }
}
