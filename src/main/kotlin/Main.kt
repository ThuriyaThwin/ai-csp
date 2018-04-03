import algorithm.BacktrackingExecutor
import javafx.application.Application
import javafx.scene.chart.NumberAxis
import javafx.scene.layout.GridPane
import problem.QueensProblem
import tornadofx.App
import tornadofx.View
import tornadofx.linechart
import tornadofx.multiseries
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    Application.launch(ChartApp::class.java, *args)
}

class ChartApp : App() {
    override val primaryView = ChartView::class
}

class ChartView : View("Algorytm genetyczny") {
    override val root = GridPane()

    init {
        linechart(
            "Tytuł wykresu",
            NumberAxis(),
            NumberAxis()
        ) {
            createSymbols = false
            multiseries("backtracking", "forward checking") {
                for (n in 0..10) {
                    val problem = QueensProblem(n)
                    val backtracking = BacktrackingExecutor(problem)
                    val backtrackingTime = measureTimeMillis {
                        backtracking.countAll()
                    }
                    val forwardChecking = BacktrackingExecutor(problem)
                    val forwardChecingTime = measureTimeMillis {
                        forwardChecking.countAll()
                    }
                    backtracking.findFirst()
                    data(n, backtrackingTime, forwardChecingTime)
                }
            }
        }
    }
}
