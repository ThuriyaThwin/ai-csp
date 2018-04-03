package problem

fun normalBoard(numberOfVariables: Int) = List(numberOfVariables) {
    Variable(0, Domain(numberOfVariables))
}

fun middleValueBoard(numberOfVariables: Int) = List(numberOfVariables) {
    Variable(0, Domain(numberOfVariables) {
        MiddleIterator(it)
    })
}
