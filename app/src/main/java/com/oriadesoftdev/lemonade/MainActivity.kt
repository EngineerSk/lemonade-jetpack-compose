package com.oriadesoftdev.lemonade

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.oriadesoftdev.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonadeApp()
            }
        }
    }
}

@Preview
@Composable
fun LemonadeApp() {
    LemonadeWithTextAndButton(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(align = Alignment.Center)
    )
}

@Preview(showBackground = true)
@Composable
fun LemonadeWithTextAndButton(modifier: Modifier = Modifier) {
    var currentStep by remember {
        mutableStateOf(1)
    }
    var squeezeCount by remember {
        mutableStateOf(2)
    }
    when (currentStep) {
        1 -> {
            TextAndImageColumn(
                modifier,
                informationTextResource = R.string.lemon_tree_info,
                imageResource = R.drawable.lemon_tree,
                imageContentDescriptionResource = R.string.lemon_tree_content_description
            ) {
                currentStep = 2
                squeezeCount = (2..4).random()
            }
        }
        2 -> {
            TextAndImageColumn(
                modifier,
                informationTextResource = R.string.lemon_image_info,
                imageResource = R.drawable.lemon_squeeze,
                imageContentDescriptionResource = R.string.lemon_content_description
            ) {
                --squeezeCount
                if (squeezeCount == 0)
                    currentStep = 3
            }
        }
        3 -> {
            TextAndImageColumn(
                modifier,
                informationTextResource = R.string.lemonade_drink_info,
                imageResource = R.drawable.lemon_drink,
                imageContentDescriptionResource = R.string.glass_of_lemonade_content_description
            ) {

                currentStep = 4
            }
        }
        else -> {
            TextAndImageColumn(
                modifier,
                informationTextResource = R.string.empty_glass_info,
                imageResource = R.drawable.lemon_restart,
                imageContentDescriptionResource = R.string.empty_glass_content_description
            ) {
                currentStep = 1
            }
        }
    }
}

@Composable
fun TextAndImageColumn(
    modifier: Modifier,
    informationTextResource: Int,
    imageResource: Int,
    imageContentDescriptionResource: Int,
    onImageClick: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Log.d(
            "MainActivity",
            "LemonadeWithTextAndButton: ${stringResource(informationTextResource)}"
        )
        Text(
            text = stringResource(informationTextResource),
            style = TextStyle(fontSize = 18.sp)
        )
        Box(
            modifier = Modifier
                .padding(16.dp)
                .border(
                    shape = RoundedCornerShape(percent = 3),
                    border = BorderStroke(
                        width = 2.dp,
                        color = Color(red = 105, green = 205, blue = 216)
                    ),
                )
                .clickable {
                    onImageClick()
                }
        ) {
            Image(
                painter = painterResource(id = imageResource),
                modifier = Modifier
                    .padding(all = 16.dp)
                    .wrapContentSize(align = Alignment.Center),
                contentDescription = stringResource(
                    imageContentDescriptionResource
                ),
            )
        }
    }
}
