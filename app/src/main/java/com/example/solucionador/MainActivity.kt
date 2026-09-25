package com.example.solucionador

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
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

// Modelos de datos
data class Producto(val id: Int, val precio: String)
data class Categoria(val nombre: String)

@Composable
fun CatalogoScreen() {
    val colorAmarillo = Color(0xFFFFDF6C)
    val colorFondo = Color(0xFFF0F0F0)

    // Forma sencilla de agregar productos: mutableStateListOf permite
    // agregar elementos a la lista con productos.add(...) y la UI se actualizará automáticamente.
    val productos = remember {
        mutableStateListOf(
            Producto(1, "$1111"),
            Producto(2, "$1111"),
            Producto(3, "$1111"),
            Producto(4, "$1111"),
            Producto(5, "$1111"),
            Producto(6, "$1111")
        )
    }

    val categorias = listOf(
        Categoria("Bebidas"),
        Categoria("Guitarras"),
        Categoria("Chopper")
    )

    Scaffold(
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp),
                contentAlignment = Alignment.TopCenter
            ) {
                // Fondo de la barra inferior
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(colorAmarillo)
                        .border(1.dp, Color.Black)
                        .align(Alignment.BottomCenter)
                )
                // Botón central
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(colorAmarillo, CircleShape)
                        .border(1.dp, Color.Black, CircleShape),
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
            // Contenedor superior (Buscador y Categorías)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorAmarillo)
                    .border(1.dp, Color.Black)
                    .padding(bottom = 8.dp)
            ) {
                // Barra de búsqueda
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .background(Color.White)
                        .border(1.dp, Color.Black)
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.Search, contentDescription = "Buscar")
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Buscar....", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }

                // Fila de categorías
                LazyRow(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(categorias) { categoria ->
                        Row(
                            modifier = Modifier
                                .border(1.dp, Color.Black)
                                .background(colorAmarillo)
                                .padding(4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(24.dp)
                                    .background(Color.White, CircleShape)
                                    .border(1.dp, Color.Black, CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                // Placeholder de imagen de categoría
                                Icon(Icons.Default.Image, contentDescription = null, modifier = Modifier.size(16.dp))
                            }
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(categoria.nombre, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                        }
                    }
                }
            }

            // Grilla de Productos
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(productos) { producto ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(0.85f)
                            .border(1.dp, Color.Black),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        shape = RoundedCornerShape(0.dp) // Cuadrado, como en v.png
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            // Placeholder de foto del producto
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxWidth()
                                    .padding(8.dp)
                                    .background(Color.LightGray),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(Icons.Default.Image, contentDescription = "Imagen", modifier = Modifier.size(48.dp))
                            }
                            // Texto de Precio
                            Text(
                                text = producto.precio,
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                modifier = Modifier.padding(bottom = 12.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}