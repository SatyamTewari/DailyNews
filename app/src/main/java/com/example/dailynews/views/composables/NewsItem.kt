package com.example.dailynews.views.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.dailynews.R
import com.example.dailynews.models.Article

@Composable
fun NewsItem(data: Article, onClick: (newsTitle: String)->Unit) {
    Card(
        shape = RoundedCornerShape(10.dp),
        onClick = {onClick(data.title)}
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(10.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "news article image",
                modifier = Modifier
                    .height(50.dp)
                    .width(50.dp)
            )
            Column(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .fillMaxWidth(1f)
            ) {
                Text(text = data.title, maxLines = 1, style = MaterialTheme.typography.bodyLarge)
                Text(
                    text = data.description ?: "",
                    maxLines = 2,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 10.dp)
                )
            }
        }
    }
}