package com.example.lab3

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun friendsr_detail(
    id:Int,
    nav:NavController,
    context: Context = LocalContext.current,
) {
    var fr = Data.DataObject.data[id]
    var rating by remember {
        mutableIntStateOf(fr.rating)
    }
    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "DetailsActivity", fontWeight = FontWeight.Bold) }, colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black, titleContentColor = Color.White, )) }
    ) {
        
        Column(
            modifier = Modifier.padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
//            Starts
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                for(i in 1..5){
                    // GOld Colorr Star
                    IconButton(onClick = {
                        rating = i
                    }){
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            tint = if (rating >= i) Color(red = 255, green = 215, blue = 0) else Color.Black,
                            modifier=Modifier.size(60.dp)
                        )
                    }
                }
            }

            SelectionContainer {
                Image(painter = painterResource(id = fr.pic), contentDescription = "", modifier = Modifier.size(450.dp))
            }
            Text(text = fr.desc, fontSize = 20.sp, modifier = Modifier.padding(17.dp))
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                ,onClick = {
                    Data.DataObject.data[id].rating = rating
                    Toast.makeText(context, "Rating Updated!", Toast.LENGTH_SHORT).show()
                    nav.popBackStack()
                }) {
                Text(text = "OK", fontSize = 20.sp)
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun friendsr_detailPreview(){
//    friendsr_detail(
//        fr = FriendsrModel(
//            name = "chandler",
//            pic = R.drawable.chandler,
//            desc = "Born in April 8, 1968, and Worked in Friends as an actor",
//            rating = 2
//        )
//    )
}