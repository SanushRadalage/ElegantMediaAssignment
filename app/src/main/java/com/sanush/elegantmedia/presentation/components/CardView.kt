package com.sanush.elegantmedia.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sanush.elegantmedia.R
import com.sanush.elegantmedia.domain.model.Cat
import com.sanush.elegantmedia.util.loadPicture

const val DEFAULT_IMAGE_VIEW = R.drawable.empty_image

@Composable
fun CardView(
        cat: Cat,
        onClick: () -> Unit
) {
    Card(
            shape = MaterialTheme.shapes.small,
            modifier = Modifier
                    .padding(
                            bottom = 6.dp,
                            top = 6.dp,
                    )
                    .fillMaxWidth()
                    .clickable(onClick = onClick),
                    elevation = 8.dp,
    ) {
        Row {
            cat.image.small.let { url ->
                val image = loadPicture(url = url, defaultImage = DEFAULT_IMAGE_VIEW).value
                image?.let {
                    Image(
                            bitmap = image.asImageBitmap(),
                            modifier = Modifier
                                    .preferredWidth(75.dp)
                                    .preferredHeight(75.dp)
                                    .padding(top = 8.dp, start = 8.dp, bottom = 8.dp),
                            alignment = Alignment.CenterStart,
                            contentScale = ContentScale.Crop,
                    )
                }
            }
            cat.title.let { title ->
                Column(
                        modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp, bottom = 8.dp, start = 8.dp, end = 8.dp)
                ) {
                    Text(
                            text = title,
                            modifier = Modifier
                                    .fillMaxWidth(0.85f)
                                    .wrapContentWidth(Alignment.Start),
                            style = MaterialTheme.typography.h6
                    )
                    val address = cat.address
                    Text(
                            text = address,
                            modifier = Modifier
                                    .fillMaxWidth(),
                            fontSize = 13.sp
                    )
                }
            }
        }
    }
}
