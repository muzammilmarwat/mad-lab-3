package com.example.lab3

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun friendsr(nav:NavController) {
    val data = Data.DataObject.data
    val desc = "Click on a eligible single user to learn more about him, and learn if you are compitable to date"
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Text(text = "Friendsr", fontSize = 52.sp)
        Text(text = desc, fontSize = 20.sp)

        LazyVerticalGrid(columns = GridCells.Fixed(2), content = {
            items(data.size){
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.clickable {
                        nav.navigate("/details_friends/${it}")
                    }
                ) {
                    Image(
                        painter = painterResource(id = data[it].pic as Int),
                        contentDescription = "Image",
                        modifier = Modifier
                            .width(200.dp)
                            .height(200.dp)
                            .padding(10.dp),
                        contentScale = ContentScale.FillBounds
                    )
                    Text(text = data[it].name, fontSize = 20.sp)
                }
            }
        })
    }


}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun friendsrPreview() {
//    friendsr()
}