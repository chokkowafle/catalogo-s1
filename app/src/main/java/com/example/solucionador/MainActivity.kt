package com.example.solucionador

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.solucionador.ui.theme.SolucionadorTheme

// Modelo del producto con su categoría asociada
data class Producto(
    val nombre: String,
    val categoria: String
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SolucionadorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CatalogoScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun CatalogoScreen(modifier: Modifier = Modifier) {
    var textoBusqueda by remember { mutableStateOf("") }
    var categoriaSeleccionada by remember { mutableStateOf("Todas") }

    // Lista de categorías para el carrusel
    val categorias = remember {
        listOf("Zona Gamer", "Zona Otaku", "Zona Musical", "Zona Kawaii", "Todas")
    }

    // Inventario de productos clasificados
    val productos = remember {
        listOf(
            Producto("Camiseta de algodón", "Zona Gamer"),
            Producto("Pantalón de mezclilla", "Zona Gamer"),
            Producto("Polerón con capucha", "Zona Gamer"),
            Producto("Chaqueta cortaviento", "Zona Gamer"),
            Producto("Calcetines térmicos", "Zona Otaku"),
            Producto("Zapatillas deportivas", "Zona Otaku"),
            Producto("Zapatos casuales", "Zona Otaku"),
            Producto("Botas de montaña", "Zona Otaku"),
            Producto("Gorra clásica", "Zona Musical"),
            Producto("Mochila impermeable", "Zona Musical"),
            Producto("Billetera de cuero", "Zona Musical"),
            Producto("dih", "Zona Kawaii"),
            Producto("dihx2", "Zona Kawaii"),
            Producto("dihx3", "Zona Kawaii"),
            Producto("dihx4", "Zona Kawaii")
        )
    }

    // Filtro conjunto: coincidencia por texto y por categoría activa
    val productosFiltrados = productos.filter { item ->
        val coincideCategoria = (categoriaSeleccionada == "Todas" || item.categoria == categoriaSeleccionada)
        val coincideTexto = item.nombre.contains(textoBusqueda.trim(), ignoreCase = true)
        coincideCategoria && coincideTexto
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // 1. Barra de búsqueda superior
        OutlinedTextField(
            value = textoBusqueda,
            onValueChange = { nuevoTexto ->
                textoBusqueda = nuevoTexto
            },
            placeholder = { Text("Buscar en el catálogo...") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        // 2. Carrusel horizontal de cuadros de texto clickeables (Categorías)
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(categorias) { categoria ->
                val estaSeleccionada = (categoria == categoriaSeleccionada)

                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = if (estaSeleccionada) {
                            MaterialTheme.colorScheme.primary
                        } else {
                            MaterialTheme.colorScheme.surfaceVariant
                        }
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = if (estaSeleccionada) 4.dp else 1.dp
                    ),
                    modifier = Modifier.clickable {
                        categoriaSeleccionada = categoria
                    }
                ) {
                    Text(
                        text = categoria,
                        color = if (estaSeleccionada) {
                            MaterialTheme.colorScheme.onPrimary
                        } else {
                            MaterialTheme.colorScheme.onSurfaceVariant
                        },
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 3. Listado dinámico de productos resultantes
        if (productosFiltrados.isEmpty()) {
            Text(
                text = "No se encontraron resultados en '$categoriaSeleccionada' para '$textoBusqueda'",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(top = 16.dp)
            )
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(productosFiltrados) { producto ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = producto.nombre,
                                style = MaterialTheme.typography.bodyLarge
                            )
                            Text(
                                text = producto.categoria,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.secondary
                            )
                        }
                    }
                }
            }
        }
    }
}