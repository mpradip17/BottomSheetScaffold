package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.layout.ContentScale
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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.view.WindowCompat
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.myapplication.model.Transaction
import com.example.myapplication.model.Transactions
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import androidx.compose.ui.res.colorResource as colorResource1

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window?.let { WindowCompat.setDecorFitsSystemWindows(it, false) }
        } else {
            @Suppress("DEPRECATION")
            window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        }
        setContent {
            ShowBottomSheet()
         //   DetailsContent()
        }
    }
}

@Composable
fun EmployeeCard(emp: Transaction) {
    Card(
        modifier = Modifier
            .padding(horizontal = 5.dp, vertical = 5.dp)
            .fillMaxWidth(),
        elevation = 5.dp,
        backgroundColor = Color.White,
        shape = RoundedCornerShape(corner = CornerSize(7.dp))) {
        ConstraintLayout {
            val (image, title, line, fdref,icra,fdrefval,icraval) = createRefs()
            Image(modifier = Modifier
                    .height(40.dp)
                    .width(40.dp)
                    .constrainAs(image) {
                        top.linkTo(parent.top, margin = 10.dp)
                        start.linkTo(parent.start, margin = 15.dp)
                        bottom.linkTo(title.top, margin = 2.dp)
                    },
                painter = painterResource(id = R.drawable.ic_otp_verify),
                contentScale = ContentScale.FillBounds,
                contentDescription = null
            )
            Text(
                text = "Bajaj Finance Ltd.",
                textAlign = TextAlign.Start,
                color = colorResource1(R.color.black_shade),
                style = typographyBold().h1,
                fontSize = dpToSp(13.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(title) {
                        top.linkTo(image.bottom, margin = 5.dp)
                        start.linkTo(image.start)
                        bottom.linkTo(line.top, margin = 5.dp)
                    }
            )
            Divider(modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(line) {
                        centerHorizontallyTo(parent)
                        top.linkTo(title.bottom, margin = 5.dp)
                        bottom.linkTo(fdref.top, margin = 15.dp)
                    },
                color = colorResource1(R.color.dividerline),
                thickness = 1.5.dp)

            Text(
                "FD Ref#",
                textAlign = TextAlign.Start,
                color = colorResource1(R.color.grey),
                style = typographyBold().h1,
                fontSize = dpToSp(12.dp),
                modifier = Modifier
                    .constrainAs(fdref) {
                        top.linkTo(line.bottom, margin = 5.dp)
                        start.linkTo(image.start, margin = 15.dp)
                        end.linkTo(icra.start, margin = 0.dp)
                    }
                    .fillMaxWidth(.5f)
                   // .background(Color.Blue)
            )
            Text(
                "ICRA Rating",
                textAlign = TextAlign.Start,
                color = colorResource1(R.color.grey),
                style = typographyBold().h1,
                fontSize = dpToSp(12.dp),
                modifier = Modifier
                    .constrainAs(icra) {
                        top.linkTo(line.bottom, margin = 5.dp)
                        start.linkTo(fdref.end)
                        end.linkTo(parent.end,margin = 0.dp)
                    }
                    .fillMaxWidth(.5f)
                 //   .background(Color.Green)
            )

            Text(
                "FD1241",
                textAlign = TextAlign.Start,
                color = colorResource1(R.color.black_shade),
                style = typographyBold().h1,
                fontSize = dpToSp(12.dp),
                modifier = Modifier
                    .constrainAs(fdrefval) {
                        top.linkTo(fdref.bottom, margin = 5.dp)
                        start.linkTo(fdref.start, margin = 0.dp)
                    }
                    .fillMaxWidth(.5f)
                  //  .background(Color.Blue)
            )

            Text(
                "MAAA",
                textAlign = TextAlign.Start,
                color = colorResource1(R.color.black_shade),
                style = typographyBold().h1,
                fontSize = dpToSp(12.dp),
                modifier = Modifier
                    .constrainAs(icraval) {
                        top.linkTo(icra.bottom, margin = 5.dp)
                        start.linkTo(icra.start)
                        end.linkTo(parent.end,margin = 0.dp)
                    }
                    .fillMaxWidth(.5f)
                 //  .background(Color.Green)
            )
        }
    }

    /*Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        elevation = 2.dp,
        backgroundColor = Color.LightGray,
        shape = RoundedCornerShape(corner = CornerSize(16.dp))

    ) {

       Row(modifier = Modifier.padding(20.dp)) {
            Column(
                modifier = Modifier.weight(1f),
                Arrangement.Center
            ) {
                Text(
                    text = emp.title,
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                    )
                )
                Text(
                    text = "Age :- " + emp.age.toString(),
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 15.sp
                    )
                )
                Text(
                    text = "Sex :- " + emp.sex,
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 15.sp
                    )
                )

                Text(
                    text = "Description :- " + emp.description,
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 15.sp
                    )
                )
            }
            Image(
                painter = painterResource(emp.ImageId),
                contentDescription = "Profile Image",
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .padding(8.dp)
                    .size(110.dp)
                    .clip((CircleShape))
            )
        }
    }*/
}

@Composable
fun DetailsContent() {
    val employees = remember { Transactions.TransactionsList }
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(employees) {
            EmployeeCard(emp = it)
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
        sheetGesturesEnabled = false,
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
                val columnWidth = 100.dp
                //val icon = imageResource(R.drawable.ic_otp_verify)
                FloatingActionButton(
                    //  val icon = +imageResource(R.drawable.ic_add_icon)
                    // FloatingActionButton(icon = icon, color = Color.Red, elevation = 8.dp)
                    modifier = Modifier
                        .constrainAs(textTopCenter) {
                            top.linkTo(parent.top, 0.dp)
                            start.linkTo(parent.start, 0.dp)
                            end.linkTo(parent.end, 0.dp)
                        }
                        .width(columnWidth)
                        .height(columnWidth),
                    onClick = {

                    },
                    shape = CircleShape
                ) {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        painter = painterResource(id = R.drawable.ic_otp_verify),
                        contentScale = ContentScale.FillBounds,
                        contentDescription = null
                    )
                    //   LottieAnimations()
                }
                Image(
                    modifier = Modifier
                        .constrainAs(textTopEnd) {
                            top.linkTo(parent.top, 8.dp)
                            bottom.linkTo(parent.bottom, 8.dp)
                            end.linkTo(parent.end, 8.dp)
                        }
                        .clickable {
                            /*  scope.launch {
                                  if (sheetState.isCollapsed) {
                                      sheetState.expand()
                                  } else {
                                      sheetState.collapse()
                                  }
                              }*/
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

@Composable
fun openSansBold() = FontFamily(Font(R.font.opensansbold, weight = FontWeight.Normal))

@Composable
fun openSansRegular() = FontFamily(Font(R.font.opensansregular, weight = FontWeight.Normal))

@Composable
fun dpToSp(dp: Dp) = with(LocalDensity.current) { dp.toSp() }

@Composable
fun typographyBold() = Typography(
    h1 = TextStyle(
        fontFamily = openSansBold(),
        fontWeight = FontWeight.Normal
    )
)

@Composable
fun typographyRegular() = Typography(
    body1 = TextStyle(
        fontFamily = openSansRegular(),
        fontWeight = FontWeight.Normal
    )
)


@SuppressLint("CoroutineCreationDuringComposition", "UnrememberedMutableState")
@Composable
fun BottomSheetContent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        ConstraintLayout {
            val (title, desc, line, otpTxt, smsView, resend, otpExpire, btn) = createRefs()
            Text(
                text = "OTP Verification",
                textAlign = TextAlign.Center,
                color = colorResource1(R.color.black_shade),
                style = typographyBold().h1,
                fontSize = dpToSp(19.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(title) {
                        top.linkTo(parent.top, margin = 45.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )
            Text(
                text = "An OTP is sent to sridhar@fundsindia.com and +919782741412 (Your contact details registered at RTA for this folio). Use OTP sent to either email or mobile number to proceed",
                color = colorResource1(R.color.bermuda_grey),
                style = typographyRegular().body1,
                fontSize = dpToSp(13.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(desc) {
                        top.linkTo(title.bottom, margin = 15.dp)
                        start.linkTo(title.start)
                        end.linkTo(title.end)
                    }
            )
            Divider(
                modifier = Modifier
                    .width(25.dp)
                    .constrainAs(line) {
                        centerHorizontallyTo(parent)
                        top.linkTo(desc.bottom, margin = 10.dp)
                    }
                    .border(25.dp, colorResource1(R.color.bermuda_grey)),
                color = colorResource1(R.color.bermuda_grey),
                thickness = 3.dp
            )
            Text(text = "Enter OTP",
                color = colorResource1(R.color.black_shade),
                style = typographyBold().h1,
                fontSize = dpToSp(15.dp),
                modifier = Modifier.constrainAs(otpTxt) {
                    top.linkTo(line.bottom, margin = 10.dp)
                    centerHorizontallyTo(parent)

                })

            val isNextBtnStatus by remember {
                mutableStateOf(false)
            }

            val otp = remember { mutableStateOf("") }

            RegistrationCodeInput(
                modifier = Modifier.constrainAs(smsView) {
                    top.linkTo(otpTxt.bottom, margin = 10.dp)
                    centerHorizontallyTo(parent)
                },
                codeLength = 6,
                initialCode = otp.value,
                onTextChanged = { otp.value = it })
            /*SmsCodeView(
                modifier = Modifier.constrainAs(smsView) {
                    top.linkTo(otpTxt.bottom, margin = 10.dp)
                    centerHorizontallyTo(parent)
                },
                smsCodeLength = 6,
                smsFulled = {
                    smsCodeNumber = it
                    isNextBtnStatus = it.length == 6
                }
            )*/

            /*  val (pinValue,onPinValueChange) = remember{
                  mutableStateOf("")
              }

              Surface(color = MaterialTheme.colors.background, modifier = Modifier.constrainAs(smsView) {
                  top.linkTo(otpTxt.bottom, margin = 10.dp)
                  centerHorizontallyTo(parent)
              }) {
                  PinView(pinText =pinValue , onPinTextChange = onPinValueChange, type = PIN_VIEW_TYPE_BORDER )
              }*/

            Text(
                text = "Resend code",
                color = colorResource1(R.color.blue),
                style = typographyRegular().body1,
                fontSize = dpToSp(12.dp),
                modifier = Modifier.constrainAs(resend) {
                    top.linkTo(smsView.bottom, margin = 10.dp)
                    end.linkTo(smsView.end, margin = 10.dp)
                })

            var targetValue by remember { mutableStateOf("") }

            //     Log.e("targetValue", "targetValue" + targetValue)

            var visible by remember { mutableStateOf(false) }
            if (!visible) {
                Text(buildAnnotatedString {
                    withStyle(style = SpanStyle(color = colorResource1(R.color.bermuda_grey))) {
                        append("OTP expire in")
                    }
                    withStyle(style = SpanStyle(color = colorResource1(R.color.green))) {
                        append(" " + targetValue)
                    }
                    withStyle(style = SpanStyle(color = colorResource1(R.color.bermuda_grey))) {
                        append(" mins")
                    }
                },
                    style = typographyRegular().body1,
                    fontSize = dpToSp(12.dp),
                    modifier = Modifier.constrainAs(otpExpire) {
                        top.linkTo(resend.bottom, margin = 5.dp)
                        start.linkTo(title.start)
                        end.linkTo(title.end)
                    })
            }
            if (targetValue == "00:01") {
                visible = true
            }
            val totalSeconds = TimeUnit.MINUTES.toSeconds(2)
            val tickSeconds = 1
            LaunchedEffect(Unit) {
                //  Log.e("LaunchedEffect", "targetValue" + targetValue)
                for (second in totalSeconds downTo tickSeconds) {
                    val time = String.format(
                        "%02d:%02d", TimeUnit.SECONDS.toMinutes(second),
                        second - TimeUnit.MINUTES.toSeconds(TimeUnit.SECONDS.toMinutes(second))
                    )
                    delay(1000)
                    targetValue = time
                }
            }
            ButtonWithRoundCornerShape(isNextBtnStatus, modifier = Modifier
                .constrainAs(btn) {
                    top.linkTo(smsView.bottom, margin = 55.dp)
                    start.linkTo(title.start)
                    end.linkTo(title.end)
                }
                .fillMaxWidth()
                .height(60.dp)
                .padding(10.dp, 10.dp, 10.dp, 10.dp))
        }
    }
}

@Composable
fun ButtonWithRoundCornerShape(enabled: Boolean, modifier: Modifier = Modifier) {
    Button(
        onClick = {

        }, enabled = enabled,

        colors =
        if (enabled) {
            ButtonDefaults.buttonColors(
                backgroundColor = colorResource1(R.color.blue)
            )
        } else {
            ButtonDefaults.buttonColors(
                backgroundColor = colorResource1(R.color.light_blue)
            )
        },
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 5.dp,
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
    LaunchedEffect(key1 = digit3) {
        if (digit3.isNotEmpty()) {
            focusManager.moveFocus(focusDirection = FocusDirection.Next)
        }
    }

    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
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
        modifier = Modifier.size(100.dp)
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

const val PIN_VIEW_TYPE_UNDERLINE = 0
const val PIN_VIEW_TYPE_BORDER = 1

@Composable
fun PinView(
    pinText: String,
    onPinTextChange: (String) -> Unit,
    digitColor: Color = MaterialTheme.colors.onBackground,
    digitSize: TextUnit = 16.sp,
    containerSize: Dp = digitSize.value.dp * 2,
    digitCount: Int = 4,
    type: Int = PIN_VIEW_TYPE_UNDERLINE,
) {
    BasicTextField(value = pinText,
        onValueChange = onPinTextChange,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        decorationBox = {
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                repeat(digitCount) { index ->
                    DigitView(index, pinText, digitColor, digitSize, containerSize, type = type)
                    Spacer(modifier = Modifier.width(5.dp))
                }
            }
        })
}


@Composable
private fun DigitView(
    index: Int,
    pinText: String,
    digitColor: Color,
    digitSize: TextUnit,
    containerSize: Dp,
    type: Int = PIN_VIEW_TYPE_UNDERLINE,
) {
    val modifier = if (type == PIN_VIEW_TYPE_BORDER) {
        Modifier
            .width(containerSize)
            .border(
                width = 1.dp,
                color = digitColor,
                shape = MaterialTheme.shapes.medium
            )
            .padding(bottom = 3.dp)
    } else Modifier.width(containerSize)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = if (index >= pinText.length) "" else pinText[index].toString(),
            color = digitColor,
            modifier = modifier,
            style = MaterialTheme.typography.body1,
            fontSize = digitSize,
            textAlign = TextAlign.Center
        )
        if (type == PIN_VIEW_TYPE_UNDERLINE) {
            Spacer(modifier = Modifier.height(2.dp))
            Box(
                modifier = Modifier
                    .background(digitColor)
                    .height(1.dp)
                    .width(containerSize)
            )
        }
    }
}

@Composable
fun RegistrationCodeInput(
    modifier: Modifier = Modifier,
    codeLength: Int,
    initialCode: String,
    onTextChanged: (String) -> Unit
) {
    val code = remember(initialCode) {
        mutableStateOf(TextFieldValue(initialCode, TextRange(initialCode.length)))
    }
    val focusRequester = FocusRequester()
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        BasicTextField(
            value = code.value,
            onValueChange = { onTextChanged(it.text) },
            modifier = Modifier.focusRequester(focusRequester = focusRequester),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            decorationBox = {
                // if (codeLength>=code.value.text.length){
                CodeInputDecoration(code.value.text, codeLength)
                // }
            }
        )
    }
}

@Composable
private fun CodeInputDecoration(code: String, length: Int) {
    Box(modifier = Modifier) {
        Row(horizontalArrangement = Arrangement.Center) {
            for (i in 0 until length) {
                val text = if (i < code.length) code[i].toString() else ""
                //  if (text.isNotEmpty())
                CodeEntry(text, i, length)
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun CodeEntry(text: String, index: Int, length: Int) {
    val focusRequesters: List<FocusRequester> = remember {
        (0 until length).map { FocusRequester() }
    }
    val enteredNumbers = remember {
        mutableStateListOf(
            *((0 until length).map { "" }.toTypedArray())
        )
    }
    Box(
        modifier = Modifier
            .padding(4.dp)
            .width(42.dp)
            .height(55.dp)
            .focusRequester(focusRequesters[index])
            .focusRequester(focusRequester = focusRequesters[index])
            .onKeyEvent { keyEvent: KeyEvent ->
                val currentValue = enteredNumbers.getOrNull(index) ?: ""
                if (keyEvent.key == Key.Backspace) {
                    if (currentValue.isNotEmpty()) {
                        enteredNumbers[index] = ""
                        enteredNumbers.joinToString(separator = "")
                        //  smsFulled.invoke(enteredNumbers.joinToString(separator = ""))
                    } else {
                        focusRequesters
                            .getOrNull(index.minus(1))
                            ?.requestFocus()
                    }
                }
                false
            },
        contentAlignment = Alignment.Center
    ) {
        val color = animateColorAsState(
            targetValue = if (text.isEmpty()) androidx.compose.ui.res.colorResource(R.color.light_blue)
            else androidx.compose.ui.res.colorResource(R.color.blue)
        )

        Box(
            Modifier
                .align(Alignment.BottomCenter)
                .padding(4.dp)
                //   .padding(start = 6.dp, end = 6.dp, bottom = 8.dp)
                .width(42.dp)
                .height(55.dp)
                .fillMaxWidth()
                .border(width = 1.dp, color = color.value)
                .background(androidx.compose.ui.res.colorResource(R.color.light_blue))
        )

        Text(
            modifier = Modifier.align(Alignment.Center),
            text = text,
            color = colorResource1(R.color.blue),
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium
        )
    }
}




