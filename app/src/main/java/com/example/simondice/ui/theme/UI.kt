package com.example.simondice.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simondice.Data
import com.example.simondice.MyColors
import com.example.simondice.MyViewModel
import com.example.simondice.R
import com.example.simondice.State

/**
 * User interface of the game
 * @param miViewModel view model of the game
 */
@Composable
fun UserInterface(miViewModel: MyViewModel) {
    miViewModel
    Column {
        round(miViewModel)
        botonesSimon(miViewModel)
        startIncreaseRound(miViewModel)
    }
}
/**
 * Show the secuence
 */
@Composable
fun round(myViewModel: MyViewModel){
    Column {
        // Row with the text "Record" and "Round"
        Row {
            Text(
                modifier = Modifier.padding(20.dp,0.dp,0.dp,0.dp),
                text = "Record",
                fontSize = 25.sp,
                color = Color.White
            )
            Text(
                modifier = Modifier.padding(240.dp,0.dp,0.dp,0.dp),
                text = "Round",
                fontSize = 25.sp,
                color = Color.White
            )
        }
        // Row with the record and the round
        Row {
            Text(
                modifier = Modifier.padding(20.dp,0.dp,0.dp,0.dp),
                text = "${myViewModel.getRecord()}",
                fontSize = 25.sp,
                color = Color.White
            )
            Text(
                text = "${myViewModel.getRound()}",
                modifier = Modifier.padding(if(myViewModel.getRound()<10) 295.dp else 290.dp,0.dp,0.dp,0.dp),
                fontSize = 25.sp,
                color = Color.White
            )
        }

    }
}

/**
 * Show the buttons of the game
 */
@Composable
fun botonesSimon(myViewModel: MyViewModel){
    Row (modifier = Modifier.padding(0.dp,100.dp,0.dp,0.dp)){
        columnButtonSimon(color = MyColors.BLUE.color,myViewModel)
        columnButtonSimon(color = MyColors.GREEN.color, myViewModel)
    }
    Row (){
        columnButtonSimon(color = MyColors.RED.color, myViewModel)
        columnButtonSimon(color = MyColors.YELLOW.color, myViewModel)
    }
}

/**
 * Show the buttons of the game
 */
@Composable
fun columnButtonSimon(color: MutableState<Color>, myViewModel: MyViewModel){
    Column {
        Button(
            onClick = {
                if (Data.state != State.SEQUENCE) {
                    myViewModel.increaseUserSecuence(Data.colors.indexOf(color))
                }
            },
            shape = CircleShape,
            modifier = Modifier
                .height(200.dp)
                .width(200.dp)
                .padding(20.dp, 20.dp)
            ,
            colors = ButtonDefaults.buttonColors(color.value)
        ){

        }
    }
}

/**
 * Show the buttons "Start" and "Increase Round"
 */
@Composable
fun startIncreaseRound(miViewModel: MyViewModel){
    Row {
        Column {
            Button(
                onClick = {
                    // change the play status
                    miViewModel.changePlayStatus()
                },
                modifier = Modifier
                    .height(200.dp)
                    .width(200.dp)
                    .padding(50.dp, 50.dp)
            ){
                Text(
                    text = miViewModel.getPlayStatus(), textAlign = TextAlign.Center
                )
            }
        }
        Column {
            Button(
                onClick = {
                    if (miViewModel.getPlayStatus().equals("Start")){

                    } else {
                        miViewModel.checkSecuence()
                    }

                },
                modifier = Modifier
                    .height(200.dp)
                    .width(200.dp)
                    .padding(50.dp, 50.dp)
            ){
                Image(
                    painter = painterResource(id = R.drawable.arrow),
                    contentDescription = "Arrow",
                )
            }
        }
    }
}