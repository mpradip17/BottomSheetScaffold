package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import androidx.compose.ui.res.colorResource as colorResource1

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        setContent {
            ShowBottomSheet()
        }
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun ShowBottomSheet() {
    val sheetState = rememberBottomSheetState(
        initialValue = BottomSheetValue.Collapsed
    )
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = sheetState
    )
    val peekHeightPx by remember { mutableStateOf(0) }
    BottomSheetScaffold(
        sheetElevation = 8.dp,
        sheetShape = RoundedCornerShape(
            bottomStart = 0.dp,
            bottomEnd = 0.dp,
            topStart = 12.dp,
            topEnd = 12.dp
        ),
        // sheetPeekHeight = 400.dp,
        // If we don't have a value yet, just render it at the default value
        sheetPeekHeight = if (peekHeightPx == 0) {
            BottomSheetScaffoldDefaults.SheetPeekHeight
        } else {
            // The value from onGloballyPositioned is in px
            // and needs to be converted back to a dp value,
            // and 8 needs to be added for the padding and 8 for the spacing between
            (with(LocalDensity.current) { peekHeightPx / density } + 16).dp
        },
        scaffoldState = scaffoldState,
        sheetBackgroundColor = colorResource1(id = R.color.white),
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                //     .border(1.dp, Color.White)
            ) {
                val (textTopCenter, textTopEnd) = createRefs()
                FloatingActionButton(
                    modifier = Modifier.constrainAs(textTopCenter) {
                        top.linkTo(parent.top, 8.dp)
                        start.linkTo(parent.start, 8.dp)
                        end.linkTo(parent.end, 8.dp)
                    },
                    onClick = {

                    },
                    shape = CircleShape
                ) {
                    LottieAnimations()
                }
                Image(
                    modifier = Modifier
                        .constrainAs(textTopEnd) {
                            top.linkTo(parent.top, 8.dp)
                            bottom.linkTo(parent.bottom, 8.dp)
                            end.linkTo(parent.end, 8.dp)
                        }
                        .clickable {
                            scope.launch {
                                if (sheetState.isCollapsed) {
                                    sheetState.expand()
                                } else {
                                    sheetState.collapse()
                                }
                            }
                        },
                    painter = painterResource(R.drawable.ic_close_otp),
                    contentDescription = null,

                    )
            }
        },
        sheetContent = {
            BottomSheetContent()
        }) {
        Scaffold(
            topBar = { TopBar() },
            backgroundColor = colorResource1(id = R.color.black_shade)
        ) { padding ->
            Box(modifier = Modifier.padding(padding)) {
                scope.launch {
                    sheetState.expand()
                }
            }
        }
    }
    BackHandler {
        scope.launch {
            sheetState.collapse()
        }
    }
}

@SuppressLint("CoroutineCreationDuringComposition", "UnrememberedMutableState")
@Composable
fun BottomSheetContent() {
    val fonts = FontFamily(
        Font(R.font.opensansbold, weight = FontWeight.Bold),
        Font(R.font.opensansregular, weight = FontWeight.Normal),
        Font(R.font.opensanssemibold, weight = FontWeight.SemiBold)
    )
    val typography = Typography(
        body1 = TextStyle(
            fontFamily = fonts, fontWeight = FontWeight.Bold,
            fontSize = 19.sp
        ), body2 = TextStyle(
            fontFamily = fonts, fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        ), h1 = TextStyle(
            fontFamily = fonts, fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp
        )
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            //  .border(1.dp, Color.Black)
            .padding(10.dp)
    ) {

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        )

        Text(
            text = "OTP Verification",
            color = colorResource1(R.color.black_shade),
            style = typography.body1,
            fontSize = 16.sp,
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
        )
        Text(
            text = "An OTP is sent to sridhar@fundsindia.com and +919782741412 (Your contact details registered at RTA for this folio). Use OTP sent to either email or mobile number to proceed",
            color = colorResource1(R.color.bermuda_grey),
            style = typography.body1,
            fontSize = 16.sp,
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .padding(10.dp)
        )

        Divider(
            Modifier
                .width(25.dp)
                .align(alignment = Alignment.CenterHorizontally)
                .border(25.dp, colorResource1(R.color.bermuda_grey)),
            color = colorResource1(R.color.bermuda_grey),
            thickness = 3.dp
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )

        Text(
            text = "Enter OTP",
            color = colorResource1(R.color.black_shade),
            style = typography.h1,
            fontSize = 17.sp,
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        )
        var isNextBtnStatus by remember {
            mutableStateOf(false)
        }
        var smsCodeNumber by remember {
            mutableStateOf("")
        }
        SmsCodeView(
            smsCodeLength = 6,
            smsFulled = {
                smsCodeNumber = it
                isNextBtnStatus = it.length == 6
            }
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(3.dp)
        )
        Text(
            text = "Resend code",
            color = colorResource1(R.color.blue),
            style = typography.h1,
            fontSize = 14.sp,
            modifier = Modifier.align(alignment = Alignment.End)
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(3.dp)
        )

        var targetValue by remember { mutableStateOf("") }
        MultipleColorInText(targetValue)

        val totalSeconds = TimeUnit.MINUTES.toSeconds(2)
        val tickSeconds = 1
        LaunchedEffect(Unit) {
            for (second in totalSeconds downTo tickSeconds) {
                val time = String.format(
                    "%02d:%02d",
                    TimeUnit.SECONDS.toMinutes(second),
                    second - TimeUnit.MINUTES.toSeconds(TimeUnit.SECONDS.toMinutes(second))
                )
                delay(1000)
                targetValue = time

            }
        }
        ButtonWithRoundCornerShape(isNextBtnStatus)
    }

}

@Composable
fun MultipleColorInText(value: String) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            buildAnnotatedString {
                withStyle(style = SpanStyle(color = colorResource1(R.color.bermuda_grey))) {
                    append("OTP expire in")
                }
                withStyle(style = SpanStyle(color = colorResource1(R.color.green))) {
                    append(" " + value)
                }
                withStyle(style = SpanStyle(color = colorResource1(R.color.bermuda_grey))) {
                    append(" mins")
                }
            },
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun ButtonWithRoundCornerShape(enabled:Boolean) {
    Button(
        onClick = {

        },enabled=enabled,

        colors =
        if (enabled){
            ButtonDefaults.buttonColors(
                backgroundColor = colorResource1(R.color.blue)
            )
        }else{
            ButtonDefaults.buttonColors(
                backgroundColor = colorResource1(R.color.light_blue)
            )
        }
        ,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(10.dp, 10.dp, 10.dp, 10.dp),
        shape = RoundedCornerShape(20.dp),

        /* colors = ButtonDefaults.outlinedButtonColors(
             contentColor = colorResource(R.color.blue)
         ),*/
        elevation = ButtonDefaults.elevation(
            defaultElevation = 10.dp,
            pressedElevation = 15.dp,
            disabledElevation = 0.dp
        )
    ) {
        Text(text = "VERIFY OTP", color = colorResource1(R.color.white))
    }
}

@Composable
fun OtpScreen() {
    val focusManager = LocalFocusManager.current
    val (digit1, setDigit1) = remember {
        mutableStateOf("")
    }
    val (digit2, setDigit2) = remember {
        mutableStateOf("")
    }
    val (digit3, setDigit3) = remember {
        mutableStateOf("")
    }
    val (digit4, setDigit4) = remember {
        mutableStateOf("")
    }
    LaunchedEffect(
        key1 = digit1,
    ) {
        if (digit1.isNotEmpty()) {
            focusManager.moveFocus(
                focusDirection = FocusDirection.Next,
            )
        }
    }
    LaunchedEffect(
        key1 = digit2,
    ) {
        if (digit2.isNotEmpty()) {
            focusManager.moveFocus(
                focusDirection = FocusDirection.Next,
            )
        }
    }
    LaunchedEffect(
        key1 = digit3,
    ) {
        if (digit3.isNotEmpty()) {
            focusManager.moveFocus(
                focusDirection = FocusDirection.Next,
            )
        }
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
    ) {
        OutlinedTextField(
            value = digit1,
            onValueChange = {
                setDigit1(it)
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.NumberPassword,
                imeAction = ImeAction.Next,
            ),
            modifier = Modifier.width(64.dp),
        )
        OutlinedTextField(
            value = digit2,
            onValueChange = {
                setDigit2(it)
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.NumberPassword,
                imeAction = ImeAction.Next,
            ),
            modifier = Modifier.width(64.dp),
        )
        OutlinedTextField(
            value = digit3,
            onValueChange = {
                setDigit3(it)
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.NumberPassword,
                imeAction = ImeAction.Next,
            ),
            modifier = Modifier.width(64.dp),
        )
        OutlinedTextField(
            value = digit4,
            onValueChange = {
                setDigit4(it)
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.NumberPassword,
                imeAction = ImeAction.Done,
            ),
            modifier = Modifier.width(64.dp),
        )
    }
}

@Composable
fun CommonOtpTextField(otp: MutableState<String>) {
    val max = 1
    OutlinedTextField(
        value = otp.value, singleLine = true,
        onValueChange = { if (it.length <= max) otp.value = it },
        //       keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        /* placeholder = {
             Text(
                 text = stringResource(id = R.string.app_name),
                 style = TextStyle(
                     color = MaterialTheme.colors.primaryVariant,
                     textAlign = TextAlign.Center
                 )
             )
         },*/
        shape = RoundedCornerShape(1.dp), modifier = Modifier
            .width(45.dp)
            .height(55.dp),
        maxLines = 1,
        textStyle = LocalTextStyle.current.copy(
            textAlign = TextAlign.Center,
            color = colorResource1(id = R.color.blue),
            fontWeight = FontWeight.Bold,
            fontSize = 19.sp
        ),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            cursorColor = colorResource1(id = R.color.blue),
            focusedBorderColor = colorResource1(id = R.color.blue),
            backgroundColor = colorResource1(id = R.color.light_blue),
            unfocusedBorderColor = colorResource1(id = R.color.light_blue)
        )
    )
}

@Composable
fun LottieAnimations() {
    val isLottiePlaying by remember {
        mutableStateOf(true)
    }
    val animationSpeed by remember {
        mutableStateOf(1f)
    }
    val composition by rememberLottieComposition(
        LottieCompositionSpec
            .RawRes(R.raw.congratulations)
    )
    val lottieAnimation by animateLottieCompositionAsState(
        composition,
        //   iterations = LottieConstants.IterateForever,
        isPlaying = isLottiePlaying,
        speed = animationSpeed,
        restartOnPlay = false
    )
    LottieAnimation(
        composition,
        lottieAnimation,
        modifier = Modifier.size(50.dp)
    )
}

@Composable
fun TopBar() {
    TopAppBar(
        title = { Text(text = stringResource(R.string.app_name), fontSize = 18.sp) },
        backgroundColor = colorResource1(id = R.color.black_shade),
        contentColor = Color.White
    )
}



