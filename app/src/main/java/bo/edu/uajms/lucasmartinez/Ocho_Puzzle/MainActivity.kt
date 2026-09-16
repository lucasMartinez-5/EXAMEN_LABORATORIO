package bo.edu.uajms.lucasmartinez.Ocho_Puzzle

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.abs

class MainActivity: AppCompatActivity()
{
    private lateinit var BTNTablero : Array<Button>
    private lateinit var TXVMessage: TextView
    private lateinit var BTNRestart: Button
    private lateinit var BTNDisorder: Button
    private lateinit var BTNVerify: Button
    private lateinit var Tablero: Array<Array<String>>
    //Variables
    private val rows=4
    private val cols=4
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Initialize Variables
        BTNTablero = arrayOf(
            findViewById(R.id.BTN00),
            findViewById(R.id.BTN01),
            findViewById(R.id.BTN02),
            findViewById(R.id.BTN03),
            findViewById(R.id.BTN10),
            findViewById(R.id.BTN11),
            findViewById(R.id.BTN12),
            findViewById(R.id.BTN13),
            findViewById(R.id.BTN20),
            findViewById(R.id.BTN21),
            findViewById(R.id.BTN22),
            findViewById(R.id.BTN23),
            findViewById(R.id.BTN30),
            findViewById(R.id.BTN31),
            findViewById(R.id.BTN32),
            findViewById(R.id.BTN33)
        )
        TXVMessage = findViewById(R.id.TXVMessage)

        BTNRestart = findViewById(R.id.BTNRestart)
        BTNDisorder = findViewById(R.id.BTNDisorder)
        BTNVerify = findViewById(R.id.BTNVerify)
        Tablero = Array(rows) { Array(cols) { "" } }

        //Events

        for (i in BTNTablero.indices)
        {
            val row = i / cols
            val col = i % cols

            BTNTablero[i].setOnClickListener()
            {
                Log.d("Click","Hiciste click en ($row,$col)")

                click(row, col, BTNTablero[i])
            }
        }

        BTNRestart.setOnClickListener()
        {
            Log.d("Click", "Hiciste click en Reiniciar")

            enableGame()
        }

        BTNDisorder.setOnClickListener()
        {
            Log.d("Click", "Hiciste click en Desordenar")

            disorderGame()
        }

        BTNVerify.setOnClickListener()
        {
            Log.d("Click", "Hiciste click en Verificar")

            verifyGame()
        }

        enableGame()
    }


    //Functions

    private fun click(row: Int, col: Int, button: Button)
    {
        var emptyRow = -1
        var emptyCol = -1

        for (i in 0..<rows)
        {
            for (j in 0..<cols)
            {
                if (Tablero[i][j] == "")
                {
                    emptyRow = i
                    emptyCol = j
                }
            }
        }

        Log.d(
            "Puzzle",
            "Espacio vacío en ($emptyRow,$emptyCol)"
        )

        val differenceRow = abs(row-emptyRow)
        val differenceCol = abs(col - emptyCol)

        if (differenceRow + differenceCol == 1)
        {
            val temp = Tablero[row][col]

            Tablero[row][col] = Tablero[emptyRow][emptyCol]

            Tablero[emptyRow][emptyCol] = temp

            updateBoard()

            Log.d(
                "Puzzle",
                "Movimiento realizado"
            )
        }
    }

    private fun updateBoard()
    {
        for (i in 0..<rows)
        {
            for (j in 0..<cols)
            {
                val position = i * cols + j

                BTNTablero[position].setText(Tablero[i][j])
            }
        }
    }

    private fun disorderGame()
    {
        for (movimiento in 0..<100)
        {
            var emptyRow = -1
            var emptyCol = -1

            for (i in 0..<rows)
            {
                for (j in 0..<cols)
                {
                    if (Tablero[i][j] == "")
                    {
                        emptyRow = i
                        emptyCol = j
                    }
                }
            }

            val possibleRows = ArrayList<Int>()
            val possibleCols = ArrayList<Int>()

            if (emptyRow > 0)
            {
                possibleRows.add(emptyRow - 1)
                possibleCols.add(emptyCol)
            }

            if (emptyRow < rows - 1)
            {
                possibleRows.add(emptyRow + 1)
                possibleCols.add(emptyCol)
            }

            if (emptyCol > 0)
            {
                possibleRows.add(emptyRow)
                possibleCols.add(emptyCol - 1)
            }

            if (emptyCol < cols - 1)
            {
                possibleRows.add(emptyRow)
                possibleCols.add(emptyCol + 1)
            }

            val randomPosition = (0..<possibleRows.size).random()

            val newRow = possibleRows[randomPosition]
            val newCol = possibleCols[randomPosition]

            val temp = Tablero[newRow][newCol]

            Tablero[newRow][newCol] = ""

            Tablero[emptyRow][emptyCol] = temp
        }

        updateBoard()

        TXVMessage.setText("Completado")
    }


    private fun verifyGame()
    {

        if (Tablero[0][0] != "1" ||
            Tablero[0][1] != "2" ||
            Tablero[0][2] != "3" ||
            Tablero[0][3] != "4")
        {
            TXVMessage.setText("Juego Desordenado")
            return
        }

        if (Tablero[1][0] != "12" ||
            Tablero[1][1] != "13" ||
            Tablero[1][2] != "14" ||
            Tablero[1][3] != "5")
        {
            TXVMessage.setText("Juego Desordenado")
            return
        }

        if (Tablero[2][0] != "11" ||
            Tablero[2][1] != "" ||
            Tablero[2][2] != "15" ||
            Tablero[2][3] != "6")
        {
            TXVMessage.setText("Juego Desordenado")
            return
        }

        if (Tablero[3][0] != "10" ||
            Tablero[3][1] != "9" ||
            Tablero[3][2] != "8" ||
            Tablero[3][3] != "7")
        {
            TXVMessage.setText("Juego Desordenado")
            return
        }

        TXVMessage.setText("Juego Ordenado")


        Log.d("Puzzle", "El puzzle fue resuelto correctamente")
    }


    private fun enableGame()
    {
        Tablero = Array(rows) { Array(cols) { "" } }

        Tablero[0][0] = "1"
        Tablero[0][1] = "2"
        Tablero[0][2] = "3"
        Tablero[0][3] = "4"

        Tablero[1][0] = "12"
        Tablero[1][1] = "13"
        Tablero[1][2] = "14"
        Tablero[1][3] = "5"

        Tablero[2][0] = "11"
        Tablero[2][1] = ""
        Tablero[2][2] = "15"
        Tablero[2][3] = "6"

        Tablero[3][0] = "10"
        Tablero[3][1] = "9"
        Tablero[3][2] = "8"
        Tablero[3][3] = "7"

        updateBoard()

        for (i in BTNTablero.indices)
        {
            BTNTablero[i].isEnabled = true
        }

        BTNRestart.visibility = View.VISIBLE

        TXVMessage.setText("Juego Reiniciado")

    }
}