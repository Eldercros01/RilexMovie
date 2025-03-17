package com.example.rilexmovie.Activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rilexmovie.R
import androidx.compose.runtime.*

class LoginActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginScreen(onLoginClick = {
                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            })
        }
    }
}

@Composable
fun LoginScreenPreview() {
    LoginScreen(onLoginClick = {})
}

@Composable
fun LoginScreen(onLoginClick: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize().background(color = colorResource(R.color.blackBackground))) {
        Image(
            painter = painterResource(id = R.drawable.bg1),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier.matchParentSize()
        )
        Column(
            modifier = Modifier.fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 32.dp, vertical = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(128.dp))
            TitleText("Log In")
            Spacer(modifier = Modifier.height(128.dp))
            GradientTextField(hint = "Username")
            Spacer(modifier = Modifier.height(16.dp))
            GradientTextField(hint = "Password")
            Spacer(modifier = Modifier.height(16.dp))
            ForgotPasswordText()
            Spacer(modifier = Modifier.height(64.dp))
            GradientButton(text = "Login", onClick = onLoginClick)
        }
    }
}

@Composable
fun TitleText(text: String) {
    Text(
        text = text,
        style = TextStyle(
            color = Color.White,
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold
        ),
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun ForgotPasswordText() {
    Text(
        text = "Forgot Your Password?",
        style = TextStyle(
            color = Color.White,
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        ),
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun GradientButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier.fillMaxWidth().height(50.dp),
        shape = RoundedCornerShape(60.dp),
        border = BorderStroke(
            width = 4.dp, brush = Brush.linearGradient(
                colors = listOf(colorResource(R.color.pink), colorResource(R.color.green))
            )
        ),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent, contentColor = Color.White)
    ) {
        Text(text = text, fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color.White)
    }
}

@Composable
fun GradientTextField(
    hint: String,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    var text by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .height(60.dp)
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(colorResource(R.color.pink), colorResource(R.color.green))
                ),
                shape = RoundedCornerShape(50.dp)
            )
            .padding(4.dp)
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            placeholder = {
                Text(
                    text = hint,
                    color = Color.White,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            },
            singleLine = true,
            textStyle = TextStyle(
                color = Color.White,
                textAlign = TextAlign.Center
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                cursorColor = Color.White,
                focusedLabelColor = Color.White,
                unfocusedLabelColor = Color.White
            ),
            keyboardOptions = keyboardOptions,
            modifier = modifier
                .fillMaxWidth()
                .background(color = colorResource(R.color.black1), shape = RoundedCornerShape(50.dp))
                .align(Alignment.Center)
        )
    }
}
