package hr.ferit.franjosipovic.myapplication.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import hr.ferit.franjosipovic.myapplication.R
import hr.ferit.franjosipovic.myapplication.data.Recipe
import hr.ferit.franjosipovic.myapplication.data.recipes
import hr.ferit.franjosipovic.myapplication.ui.theme.DarkGray
import hr.ferit.franjosipovic.myapplication.ui.theme.LightGray
import hr.ferit.franjosipovic.myapplication.ui.theme.Pink
import hr.ferit.franjosipovic.myapplication.ui.theme.White

@Composable
fun RecipesScreen(
    navigation: NavHostController
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        ScreenTitle(
            title = "What would you like to cook today?",
            subtitle = "Good morning, Matej"
        )
        SearchBar(iconResource = R.drawable.ic_search, labelText = "Search")
        RecipeCategories()
        RecipeList(navigation = navigation, recipes = recipes)
        IconButton(iconResource = R.drawable.ic_plus, onClick = { }, text = "Add new recipe")
    }
}

@Composable
fun ScreenTitle(
    title: String,
    subtitle: String
) {
    Box(
        modifier = Modifier
            .padding(top = 16.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = subtitle,
            style = TextStyle(
                color = Color.Magenta, fontSize = 12.sp,
                fontWeight = FontWeight.Light, fontStyle = FontStyle.Italic
            ),
            modifier = Modifier
                .padding(horizontal = 15.dp)
        )
        Text(
            text = title,
            style = TextStyle(
                color = Color.Black, fontSize = 26.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .padding(vertical = 16.dp, horizontal = 16.dp)
        )
    }
}

@Composable
fun SearchBar(
    @DrawableRes iconResource: Int,
    labelText: String,
    colors: TextFieldColors = TextFieldDefaults.textFieldColors(
        //containerColor = Color.Transparent,
        placeholderColor = DarkGray,
        textColor = DarkGray,
        unfocusedLabelColor = DarkGray,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent,
        backgroundColor = Color.Transparent
    )
) {
    var searchInput by remember {
        mutableStateOf("")
    }
    TextField(
        value = searchInput,
        onValueChange = { searchInput = it },
        label = {
            Text(labelText)
        },
        leadingIcon = {
            Icon(
                painter = painterResource(id = iconResource),
                contentDescription = labelText,
                tint = DarkGray,
                modifier = Modifier
                    .width(16.dp)
                    .height(16.dp)
            )
        },
        colors = colors,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    )
}

@Composable
fun TabButton(
    text: String,
    isActive: Boolean,
    modifier: Modifier,
    onClick: () -> Unit
) {
    Button(
        shape = RoundedCornerShape(24.dp),
        elevation = null,
        colors = if (isActive) ButtonDefaults.buttonColors(
            contentColor = White,
            backgroundColor = Pink
        ) else
            ButtonDefaults.buttonColors(contentColor = DarkGray, backgroundColor = LightGray),
        modifier = modifier.fillMaxHeight(),
        onClick = onClick
    ) {
        Text(text)
    }
}

@Composable
fun RecipeCategories() {
    var currentActiveButton by remember { mutableStateOf(0) }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 16.dp)
            .background(Color.Transparent)
            .fillMaxWidth()
            .height(44.dp)
    ) {
        TabButton(
            text = "All",
            isActive = currentActiveButton == 0,
            modifier = Modifier.weight(1f)
        ) {
            currentActiveButton = 0
        }
        TabButton(
            text = "Breakfast",
            isActive = currentActiveButton == 1,
            modifier = Modifier
                .weight(1f)
        ) {
            currentActiveButton = 1
        }
        TabButton("Lunch", currentActiveButton == 2, Modifier.weight(1f))
        {
            currentActiveButton = 2
        }
    }
}

@Composable
fun IconButton(
    @DrawableRes iconResource: Int,
    onClick: () -> Unit,
    colors: ButtonColors = ButtonDefaults.buttonColors(backgroundColor = Pink),
    textColor: Color = Color.White,
    iconColor: Color = White,
    text: String
) {
    Button(
        onClick = onClick,
        colors = colors,
        shape = RoundedCornerShape(20.dp)
    ) {
        Row {

            Text(
                text = text,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Light,
                    color = textColor
                )
            )
            Spacer(Modifier.width(2.dp))
            Icon(
                painter = painterResource(id = iconResource),
                contentDescription = text, tint = iconColor
            )

        }
    }
}


@Composable
fun Chip(
    text: String,
    backgroundColor: Color = Color.White,
    textColor: Color = Color.Magenta,
) {
    Box(
        modifier = Modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(12.dp)
            )
            .clip(RoundedCornerShape(12.dp))
            .padding(horizontal = 8.dp, vertical = 2.dp)
    ) {
        Text(
            text = text,
            style = TextStyle(
                color = textColor,
                fontSize = 12.sp
            )
        )
    }
}

@Composable
fun RecipeCard(
    @DrawableRes image: Int,
    title: String,
    time: String,
    ingredientsCount: Int,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .size(width = 215.dp, height = 326.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp)
    ) {
        Box(
            Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = image),
                modifier = Modifier.fillMaxSize(),
                contentDescription = "Background Image",
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
            ) {
                Column {
                    Text(title, style = TextStyle(color = White, fontSize = 14.sp))
                    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                        Chip(text = time)
                        Chip(text = "$ingredientsCount ingredients")
                    }
                }
            }
        }
    }
}

@Composable
fun RecipeList(
    recipes: List<Recipe>,
    navigation: NavController
) {
    Column {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Text(
                text = "7 recipes",
                style = TextStyle(
                    color = Color.DarkGray, fontSize =
                    14.sp
                )
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_flame),
                contentDescription = "Flame",
                tint = Color.DarkGray,
                modifier = Modifier
                    .width(18.dp)
                    .height(18.dp)
            )
        }
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            items(recipes.size) {
                RecipeCard(
                    image = recipes[it].image,
                    title = recipes[it].title,
                    time = recipes[it].cookingTime,
                    ingredientsCount = recipes[it].ingredients.size
                ) {
                    navigation.navigate(
                        Routes.getRecipeDetailsPath(it)
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}
