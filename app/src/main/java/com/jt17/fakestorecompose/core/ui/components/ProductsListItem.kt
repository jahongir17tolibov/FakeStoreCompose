package com.jt17.fakestorecompose.core.ui.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.jt17.fakestorecompose.core.ui.theme.FakeStoreComposeTheme
import com.jt17.fakestorecompose.domain.model.Products

@Composable
fun ProductsListItem(
    products: Products,
    onFavouriteClick: () -> Unit,
    expendedState: Boolean,
) {

    val productPainter = rememberAsyncImagePainter(
        model = products.image,
        contentScale = ContentScale.Crop,
    )
    var columnExpendedState by rememberSaveable { mutableStateOf(expendedState) }

    Card(
        modifier = Modifier
            .wrapContentHeight()
            .padding(6.dp)
            .clip(RoundedCornerShape(10.dp)),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
    ) {

        MainContent(
            title = products.title,
            price = products.price.toString(),
            imagePainter = productPainter,
            rating = products.rate.toString(),
            category = products.category,
        )

        if (columnExpendedState) {
            //SPACE🗿
            Spacer(modifier = Modifier.height(10.dp))

            ExpandableDetailText(description = products.description)
        }
        //SPACE🗿
        Spacer(modifier = Modifier.height(10.dp))

        DetailContent(
            isFavourite = products.isFavourite,
            expendedState = columnExpendedState,
            onDetailButtonClick = { columnExpendedState = !columnExpendedState },
            onFavouriteIconClick = { onFavouriteClick() }
        )

    }

}

@Composable
fun MainContent(
    title: String,
    price: String,
    category: String,
    rating: String,
    imagePainter: AsyncImagePainter,
) = Row(
    modifier = Modifier
        .fillMaxWidth()
        .padding(top = 12.dp, end = 12.dp, start = 12.dp),
    verticalAlignment = Alignment.Top,
    horizontalArrangement = Arrangement.Center
) {

    //IMAGE
    Image(
        painter = imagePainter,
        modifier = Modifier
            .size(width = 140.dp, height = 180.dp)
            .clip(RoundedCornerShape(10.dp))
            .border(
                1.dp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                shape = RoundedCornerShape(10.dp)
            ),
        contentScale = ContentScale.Crop,
        contentDescription = "products_image"
    )

    //SPACE🗿
    Spacer(modifier = Modifier.width(6.dp))

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {

        //TITLE
        Text(
            text = title,
            modifier = Modifier.padding(all = 6.dp),
            style = TextStyle(
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 16.sp,
                fontFamily = primaryFont(),
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Start,
            ),
            overflow = TextOverflow.Ellipsis,
            maxLines = 3,
            minLines = 3
        )

        //CATEGORY
        AnnotatedTextView(
            startText = "category",
            endText = category,
        )

        //RATING
        AnnotatedTextView(
            startText = "rating",
            endText = rating,
        )

        //CATEGORY
        AnnotatedTextView(
            startText = "price",
            endText = "$$price",
            textSize = 20
        )

    }


}

@Composable
fun ExpandableDetailText(description: String) = Column(
    modifier = Modifier
        .padding(horizontal = 12.dp)
        .fillMaxWidth()
        .animateContentSize(
            animationSpec = tween(
                durationMillis = 1000,
                easing = LinearOutSlowInEasing
            )
        )
) {

    Text(
        text = description,
        fontSize = 14.sp,
        fontFamily = primaryFont(),
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )

}

@Composable
fun DetailContent(
    isFavourite: Boolean,
    expendedState: Boolean,
    onDetailButtonClick: () -> Unit,
    onFavouriteIconClick: () -> Unit,
) = Row(
    modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 12.dp, end = 12.dp, start = 12.dp),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceAround
) {

    val isFavouriteState by remember(isFavourite) { mutableStateOf(isFavourite) }

    val interactionSourceForButton = remember { MutableInteractionSource() }
    val interactionSourceForIcon = remember { MutableInteractionSource() }

    Button(
        onClick = { onDetailButtonClick() },
        interactionSource = interactionSourceForButton,
        modifier = Modifier
            .scaleOnPress(interactionSourceForButton)
            .padding(vertical = 6.dp)
            .weight(3f)
            .height(32.dp),
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
        shape = CircleShape
    ) {

        Text(
            text = if (expendedState) "Less detail" else "Detail",
            modifier = Modifier.align(Alignment.CenterVertically),
            fontFamily = primaryFont(),
            fontSize = 16.sp,
            fontWeight = FontWeight.Light
        )

    }

    //add to favourites button
    IconButton(
        onClick = { onFavouriteIconClick() },
        modifier = Modifier
            .scaleOnPress(interactionSourceForIcon)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {},
        colors = IconButtonDefaults.iconButtonColors(
            contentColor = if (isFavouriteState) {
                MaterialTheme.colorScheme.error
            } else {
                MaterialTheme.colorScheme.primary
            }
        ),
        interactionSource = interactionSourceForIcon,
        content = {
            Icon(
                imageVector = if (isFavouriteState) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                modifier = Modifier
                    .padding(horizontal = 6.dp)
                    .size(32.dp),
                contentDescription = "favourite_icon"
            )
        },
    )

}

@RequiresApi(Build.VERSION_CODES.P)
@Preview(showBackground = true)
@Composable
fun ProductsItemPrev() = FakeStoreComposeTheme {
    ProductsListItem(
        products = Products(
            id = 1,
            title = "Samsung 49-Inch CHG90 144Hz Curved ",
            price = 109.95,
            description = "Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday",
            category = "men's clothing",
            image = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
            rate = 3.9,
            count = 120,
            isFavourite = false
        ),
        onFavouriteClick = { },
        expendedState = false
    )
}