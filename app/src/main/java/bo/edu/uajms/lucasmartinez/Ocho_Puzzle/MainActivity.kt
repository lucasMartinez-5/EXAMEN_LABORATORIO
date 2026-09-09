package bo.edu.uajms.lucasmartinez.Ocho_Puzzle

import android.os.Bundle
import android.util.Log
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
        Tablero = Array(rows) { Array(cols) { "" } }
        TXVMessage= findViewById(R.id.TXVMessage)
        BTNRestart=findViewById(R.id.BTNRestart)
        BTNDisorder=findViewById(R.id.BTNDisorder)
        BTNVerify=findViewById(R.id.BTNVerify)

        //Events
        for (i in BTNTablero.indices){
            val row=i/rows
            val col=i%cols
            BTNTablero[i].setOnClickListener(){
                Log.d("Click","Hiciste click en ($row,$col)")
                click(row,col,BTNTablero[i])
            }
        }
        BTNRestart.setOnClickListener() {
            Log.d("Click","Hiciste click en Reiniciar")
            enableGame()
        }
        BTNDisorder.setOnClickListener() {
            Log.d("Click","Hiciste click en Desordenar")
            disorderGame()
        }
        BTNVerify.setOnClickListener() {
            Log.d("Click","Hiciste click en Verificar")
            verifyGame()
        }
        enableGame()
    }
    //functions
    private fun click(row: Int, col: Int, button: Button) {
        var emptyRow=-1
        var emptyCol=-1
        for(i in 0..<rows){
            for(j in 0..<cols){
                if (Tablero[i][j] == ""){
                    emptyRow=i
                    emptyCol=j
                }
            }
        }
        Log.d("Puzzle","Espacio vacío en ($emptyRow,$emptyCol)")
        val differenceRow= abs(row - emptyRow)
        val differenceCol= abs(col - emptyCol)
        if (differenceRow+differenceCol==1)
        {
            val temp = Tablero[row][col]
            Tablero[row][col]=Tablero[emptyRow][emptyCol]
            Tablero[emptyRow][emptyCol]=temp
            updateBoard()
            Log.d("Puzzle","Movimiento realizado")
        }
    }

    private fun updateBoard() {
    }

    private fun verifyGame() {
    }

    private fun disorderGame() {
    }

    private fun enableGame() {

    }


}