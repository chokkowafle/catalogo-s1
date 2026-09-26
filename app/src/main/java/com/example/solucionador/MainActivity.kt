package com.example.solucionador

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                CatalogoScreen()
            }
        }
    }
}

// 1. Se añade 'imagenRes' a los modelos de datos
data class Producto(val id: Int, val nombre: String, val precio: String, val categoria: String, val imagenRes: Int)
data class Categoria(val nombre: String, val imagenRes: Int)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatalogoScreen() {
    val colorAmarillo = Color(0xFFFFDF6C)
    val colorFondo = Color(0xFFE5E5E5)

    var searchText by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf<String?>(null) }

    // 2. Se asignan las imágenes de la carpeta drawable (reemplaza R.drawable.ic_launcher_foreground por tus imágenes reales)
    val productos = remember {
        mutableStateListOf(
            Producto(8, "Disco Shadow of Colossus", "$34990", "Zona Gamer", R.drawable.goc),
            Producto(9, "PSVita", "$149990", "Zona Gamer", R.drawable.psvita),
            Producto(10, "LOZ Switch Edition", "$54990", "Zona Gamer", R.drawable.loz),
            Producto(1, "Peluche Chopper", "$6769", "Zona Kawai", R.drawable.chopper),
            Producto(7, "Peluche Valheim", "$24990", "Zona Kawai", R.drawable.valheim),
            Producto(2, "Ericcson 2", "$13990", "Zona Musical", R.drawable.ericcson2),
            Producto(4, "Toca Discos", "$69670", "Zona Musical", R.drawable.tocadiscos),
            Producto(6, "Disco Simplemente", "$11990", "Zona Musical", R.drawable.simplemente),
            Producto(11, "Poleron JJK", "$29990", "Zona Otaku", R.drawable.camisaanime),
            Producto(13, "Pack Mangas Demon Slayer", "$39990", "Zona Otaku", R.drawable.mangasdsl),
            Producto(16, "Posters JJK", "$8990", "Zona Otaku", R.drawable.posterjjk),
            Producto(5, "Shampoo", "$5990", "Zona Random", R.drawable.shampoo),
            Producto(3, "Silla Ergonomica", "$59790", "Zona Setup", R.drawable.sillaergo),
            Producto(12, "Audifonos Minecraft", "$45990", "Zona Setup", R.drawable.cascosmc),
            Producto(14, "Posa Audifonos Minecraft", "$15990", "Zona Setup", R.drawable.pedestalespada),
            Producto(15, "Combo Red Dragon", "$79990", "Zona Setup", R.drawable.comboredragon)
        )
    }

    val categorias = listOf(
        Categoria("Zona Gamer", R.drawable.gamer),
        Categoria("Zona Kawai", R.drawable.kawaii),
        Categoria("Zona Musical", R.drawable.musica),
        Categoria("Zona Otaku", R.drawable.otaku),
        Categoria("Zona Setup", R.drawable.setup),
        Categoria(nombre = "Zona Random", imagenRes = R.drawable.random)
    )

    val productosFiltrados = productos.filter { producto ->
        val coincideCategoria = selectedCategory == null || producto.categoria == selectedCategory
        val coincideBusqueda = searchText.isBlank() || producto.nombre.contains(searchText, ignoreCase = true)
        coincideCategoria && coincideBusqueda
    }

    Scaffold(
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding() // Adapta la altura según los gestos o botones de navegación de cada dispositivo
                    .height(80.dp),
                contentAlignment = Alignment.TopCenter
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(colorAmarillo)
                        .border(1.dp, Color.Black)
                        .align(Alignment.BottomCenter)
                )
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(colorAmarillo, CircleShape)
                        .border(1.dp, Color.Black, CircleShape)
                        .clickable {
                            searchText = ""
                            selectedCategory = null
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = "Inicio",
                        modifier = Modifier.size(32.dp),
                        tint = Color.Black
                    )
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorFondo)
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorAmarillo)
                    .border(1.dp, Color.Black)
                    .padding(bottom = 12.dp)
            ) {
                OutlinedTextField(
                    value = searchText,
                    onValueChange = { searchText = it },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Buscar", tint = Color.Black) },
                    placeholder = { Text("Buscar....", color = Color.Black, fontWeight = FontWeight.Bold) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedIndicatorColor = Color.Black,
                        unfocusedIndicatorColor = Color.Black,
                        cursorColor = Color.Black
                    ),
                    singleLine = true,
                    shape = RoundedCornerShape(0.dp)
                )

                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(categorias) { categoria ->
                        val isSelected = selectedCategory == categoria.nombre
                        Row(
                            modifier = Modifier
                                .border(1.dp, Color.Black)
                                .background(if (isSelected) Color.White else colorAmarillo)
                                .padding(horizontal = 8.dp, vertical = 6.dp)
                                .clickable {
                                    selectedCategory = if (isSelected) null else categoria.nombre
                                },
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(24.dp)
                                    .background(Color.White, CircleShape)
                                    .border(1.dp, Color.Black, CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                // 3. Imagen de categoría
                                Image(
                                    painter = painterResource(id = categoria.imagenRes),
                                    contentDescription = categoria.nombre,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = categoria.nombre,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                                color = Color.Black
                            )
                        }
                    }
                }
            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(productosFiltrados) { producto ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(0.80f)
                            .border(1.dp, Color.Black),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        shape = RoundedCornerShape(0.dp)
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxWidth()
                                    .padding(12.dp)
                                    .border(1.dp, Color.Black)
                                    .background(Color.White),
                                contentAlignment = Alignment.Center
                            ) {
                                // 4. Imagen del producto
                                Image(
                                    painter = painterResource(id = producto.imagenRes),
                                    contentDescription = "Imagen de ${producto.nombre}",
                                    modifier = Modifier.fillMaxSize()
                                )
                            }
                            Text(
                                text = producto.nombre,
                                fontSize = 14.sp,
                                color = Color.DarkGray,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(horizontal = 4.dp),
                                maxLines = 1
                            )
                            Text(
                                text = producto.precio,
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                color = Color.Black,
                                modifier = Modifier.padding(bottom = 12.dp, top = 2.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}