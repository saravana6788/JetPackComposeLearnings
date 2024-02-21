package com.sarav.jetpackcomposelearnings

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sarav.jetpackcomposelearnings.ui.theme.JetPackComposeLearningsTheme
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetPackComposeLearningsTheme {
                // A surface container using the 'background' color from the theme
               /* Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }*/

                Column(modifier = Modifier){
                    ImageCard(painter = painterResource(id = R.drawable.jetpack_compose_image),
                        contentDescription = "Jetpack Compose Image",
                        title = "Jetpack Compose")

                    Spacer(modifier = Modifier.height(20.dp))

                    SimpleTextWithDecorations(text = "JetPack Compose", modifier =  Modifier)

                    Spacer(modifier = Modifier.height(20.dp))

                    ChangeSameColorBox(modifier = Modifier)

                    Spacer(modifier = Modifier.height(20.dp))

                    Row(modifier = Modifier){
                        val changedColor = remember { mutableStateOf(Color.Green) }
                        ClickMeBox(modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)){
                            changedColor.value = it
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                        ColorMeBox(modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),changedColor.value)
                    }

                    Spacer(modifier = Modifier.height(20.dp))


                    // How to implement  textfield , buttin and the snackbar and scaffold.


                    val snackbarHostState = remember {SnackbarHostState()}
                    val scope = rememberCoroutineScope()
                    var textFieldState  by remember{mutableStateOf("")}
                    Scaffold(modifier = Modifier.fillMaxWidth()
                        .padding(12.dp)
                        .align(Alignment.CenterHorizontally),
                        snackbarHost = {
                            SnackbarHost(hostState = snackbarHostState)
                        })
                    {
                        Column(modifier = Modifier
                            .fillMaxWidth()
                            .padding(it)
                            ){

                            TextField(value = textFieldState ,
                                label = {Text(text = "Enter the text")},
                                onValueChange = {
                                    textFieldState = it
                                },
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                            Spacer(modifier = Modifier.width(20.dp))
                            Button(onClick = {
                                scope.launch {
                                    snackbarHostState.showSnackbar(
                                       "You Typed ::  $textFieldState","Ok",
                                        withDismissAction = true,
                                        duration = SnackbarDuration.Long
                                    )
                                }

                            },
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                                    ) {
                                Text(text = "Click Me")
                            }

                        }

                    }
                }



            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview
@Composable
fun GreetingPreview() {
    JetPackComposeLearningsTheme {
        Greeting("Android")
    }
}

@Preview(showBackground = true)
@Composable
fun ImageCardPreview() {
    JetPackComposeLearningsTheme {
        //Greeting("Android")
        ImageCard(painter = painterResource(R.drawable.jetpack_compose_image),
            contentDescription = "JetPack Compose Logo",
            title = "Jetpack Compose" )
    }
}


// How to add a new image as a card with gradient and text on top of it.
@Composable
fun ImageCard(
    painter: Painter,
    contentDescription:String,
    title:String,
    modifier: Modifier = Modifier) {
    Card(
        modifier = Modifier
            .width(200.dp)
            .height(145.dp)
            .background(color = Color.Transparent)
            .padding(16.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        )
    ){
        Box(modifier = modifier.fillMaxSize(),
            ){
            Image(painter = painter, 
                contentDescription = contentDescription )
            Box(modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black), startY = 145f,
                    )
                )){

            }
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(5.dp),
                contentAlignment = Alignment.BottomCenter
            ){
                Text(text = title,
                    fontSize = 10.sp,
                    color = Color.White
                )
            }

        }
    }
}

@Preview
@Composable
fun SimpleTextWithDecorationsPreview(){
    SimpleTextWithDecorations(text = "Jetpack Compose", modifier = Modifier)
}



/**
 * Applying different Text decorations on the text , AnnotatedBuilder for text
 */
@Composable
fun SimpleTextWithDecorations(text:String,modifier: Modifier){

    val spanStyle = SpanStyle(
        color = Color.Red,
        fontSize = 40.sp,
        fontWeight = FontWeight.ExtraBold,
        fontStyle = FontStyle.Italic,
        textDecoration = TextDecoration.Underline
    )
    Box(modifier = Modifier
        .fillMaxWidth()
        .background(Color.Black)
        .height(50.dp),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = spanStyle
                ) {
                    append("J")
                }
                append("etpack ")
                    withStyle(
                        style = spanStyle
                    ) {
                        append("C")
                    }
                append("ompose")

            },
            color = Color.White,
            fontSize = 30.sp,
            textAlign = TextAlign.Center,
            textDecoration = TextDecoration.Underline,
            fontWeight = FontWeight.Bold)
    }
}


/**
 * CHanging the color of the box using State
 */
@Composable
fun ChangeSameColorBox(modifier: Modifier) {
    val color = remember { mutableStateOf(Color.Black) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(color.value)
            .clickable {
                color.value = Color(
                    Random.nextFloat(),
                    Random.nextFloat(),
                    Random.nextFloat(),
                    1f
                )
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            "Click me",
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}


@Composable
fun ClickMeBox(modifier: Modifier,updateColor: (Color)->Unit){
    Box(modifier = modifier
        .height(100.dp)
        .padding(20.dp)
        .background(Color.Red)
        .clickable {
            updateColor(
                Color(
                    Random.nextFloat(),
                    Random.nextFloat(),
                    Random.nextFloat(),
                    1f

                )

            )
        },
        contentAlignment = Alignment.Center){
        Text(text = "Click Me",
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold)
    }
}

@Composable
fun  ColorMeBox(modifier: Modifier,boxColor:Color){
    Box(modifier = modifier
        .height(100.dp)
        .padding(20.dp)
        .background(boxColor),
        contentAlignment = Alignment.Center){
        Text(
            text = "Color Me", textAlign = TextAlign.Center,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}






