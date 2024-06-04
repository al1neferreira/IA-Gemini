package br.com.aline.ia_gemini

import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import br.com.aline.ia_gemini.viewModel.MainViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val textResult = viewModel.textGenerationResult.collectAsState().value
            Column(
                modifier = androidx.compose.ui.Modifier
                    .background(Color(0xFF1C1C1C))
                    .fillMaxSize()
                    .padding(20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally


            ) {


                Text(text = textResult ?: "Gere sua lista de compras \r\n para uma dieta low carb",
                    style = TextStyle(
                        fontSize = 15.sp,
                        lineHeight = 30.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Monospace,
                        color = Color.White,


                    )
                )

                Spacer(modifier = Modifier. padding(20.dp))

               Image(painter = painterResource(id = R.drawable.baseline_shopping_cart_26),
                   contentDescription = null)

                Spacer(modifier = Modifier. padding(20.dp))

                Button(
                    onClick = {
                    viewModel.generateSuperMarketList()
                },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFD50000),
                        contentColor = Color.White,
                        disabledContentColor = Color.Gray,
                    ),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .height(40.dp)
                        .width(250.dp)

                ) {
                    Text(text = "Criar lista",
                        fontSize = 17.sp,
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.Bold
                        )



                }
            }
        }
    }
}



