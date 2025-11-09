-- ============================================
-- LIMPIEZA INICIAL (Opcional)
-- ============================================
SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE sustituciones;
TRUNCATE TABLE ingredientes;
TRUNCATE TABLE pasos_preparacion;
TRUNCATE TABLE preparaciones;
TRUNCATE TABLE cocteles;
TRUNCATE TABLE info_cocteles;
TRUNCATE TABLE insumos;
TRUNCATE TABLE bartenders;
TRUNCATE TABLE administradores;
TRUNCATE TABLE proveedores;
TRUNCATE TABLE direcciones;
TRUNCATE TABLE unidades_medida;
SET FOREIGN_KEY_CHECKS = 1;

-- ============================================
-- 1. UNIDADES DE MEDIDA
-- ============================================
INSERT INTO unidades_medida (nombre, abreviatura, tipo) VALUES
('Mililitros', 'ml', 'VOLUMEN'),
('Onzas', 'oz', 'VOLUMEN'),
('Litros', 'L', 'VOLUMEN'),
('Gramos', 'g', 'PESO'),
('Kilogramos', 'kg', 'PESO'),
('Unidades', 'un', 'CANTIDAD'),
('Dash', 'dash', 'CANTIDAD'),
('Cucharadas', 'cdta', 'CANTIDAD'),
('Gotas', 'gts', 'CANTIDAD');

-- ============================================
-- 2. DIRECCIONES
-- ============================================
INSERT INTO direcciones (calle, ciudad, pais, codigo_postal) VALUES
('Calle 50 #45-23', 'Medellín', 'Colombia', '050010'),
('Carrera 70 #32-10', 'Medellín', 'Colombia', '050015'),
('Avenida El Poblado #12-45', 'Medellín', 'Colombia', '050021');

-- ============================================
-- 3. PROVEEDORES
-- ============================================
INSERT INTO proveedores (nombre, telefono, email, activo, nit, direccion_id) VALUES
('Licores Premium SAS', '3001234567', 'ventas@licorespremium.com', true, '900123456-1', 1),
('Frutas y Verduras Frescas', '3007654321', 'pedidos@frutasfrescas.com', true, '900234567-2', 2),
('Distribuidora de Bebidas', '3009876543', 'info@distribebidas.com', true, '900345678-3', 3);

-- ============================================
-- 4. INSUMOS
-- ============================================

-- LICORES
INSERT INTO insumos (nombre, tipo, cantidad_disponible, cantidad_minima, precio, proveedor_id, unidad_id, codigo_barras) VALUES
('Vodka Absolut', 'LICOR', 2000, 500, 85000, 1, 1, '7312040017034'),
('Ron Havana Club 3 Años', 'LICOR', 1500, 500, 75000, 1, 1, '8501110080422'),
('Tequila Jose Cuervo Silver', 'LICOR', 1200, 400, 90000, 1, 1, '7501035010109'),
('Gin Bombay Sapphire', 'LICOR', 1000, 300, 95000, 1, 1, '5010677710015'),
('Whisky Jack Daniels', 'LICOR', 800, 300, 120000, 1, 1, '5099873089347'),
('Cointreau', 'LICOR', 500, 200, 85000, 1, 1, '3035542007003'),
('Triple Sec', 'LICOR', 0, 200, 35000, 1, 1, '7501035010116');

-- MIXERS
INSERT INTO insumos (nombre, tipo, cantidad_disponible, cantidad_minima, precio, proveedor_id, unidad_id) VALUES
('Jugo de Limón Natural', 'MIXER', 500, 200, 5000, 2, 1),
('Jugo de Naranja Natural', 'MIXER', 600, 200, 6000, 2, 1),
('Agua Tónica', 'MIXER', 2000, 500, 3000, 3, 1),
('Ginger Ale', 'MIXER', 1500, 400, 3500, 3, 1),
('Refresco de Cola', 'MIXER', 2500, 500, 2500, 3, 1),
('Agua Mineral con Gas', 'MIXER', 3000, 500, 2000, 3, 1);

-- JARABES
INSERT INTO insumos (nombre, tipo, cantidad_disponible, cantidad_minima, precio, proveedor_id, unidad_id) VALUES
('Jarabe Simple', 'JARABE', 800, 200, 8000, 2, 1),
('Granadina', 'JARABE', 400, 150, 12000, 1, 1);

-- FRUTAS Y GARNISH
INSERT INTO insumos (nombre, tipo, cantidad_disponible, cantidad_minima, precio, proveedor_id, unidad_id) VALUES
('Limones', 'FRUTA', 30, 10, 500, 2, 6),
('Naranjas', 'FRUTA', 25, 10, 600, 2, 6),
('Fresas', 'FRUTA', 20, 5, 8000, 2, 4),
('Cerezas Marrasquino', 'GARNISH', 50, 20, 15000, 2, 6),
('Aceitunas', 'GARNISH', 40, 15, 12000, 2, 6);

-- HIERBAS Y ESPECIAS
INSERT INTO insumos (nombre, tipo, cantidad_disponible, cantidad_minima, precio, proveedor_id, unidad_id) VALUES
('Hojas de Menta Fresca', 'HIERBAS', 50, 10, 3000, 2, 4),
('Albahaca', 'HIERBAS', 30, 10, 3500, 2, 4),
('Sal de Mar', 'ESPECIAS', 500, 100, 2000, 2, 4),
('Azúcar', 'ESPECIAS', 1000, 200, 3000, 2, 4);

-- BITTERS Y CREMAS
INSERT INTO insumos (nombre, tipo, cantidad_disponible, cantidad_minima, precio, proveedor_id, unidad_id) VALUES
('Angostura Bitters', 'BITTER', 300, 50, 25000, 1, 1),
('Crema de Coco', 'CREMA', 400, 150, 18000, 2, 1);

-- ============================================
-- 5. USUARIOS
-- ============================================

-- Bartenders (contraseña: bar123)
INSERT INTO bartenders (nombre, email, usuario, contrasena, especialidad, cocteles_preparados, fecha_registro, activo) VALUES
('Juan Pérez', 'juan@bar.com', 'juan_bartender', '$2a$10$Bvp8ngKMieBn9Mo6oXE53uNwEkVWzICVjqgmkbI.ON4kIhjAuug9G', 'Cocteles Clásicos', 0, NOW(), true),
('María González', 'maria@bar.com', 'maria_bartender', '$2a$10$Bvp8ngKMieBn9Mo6oXE53uNwEkVWzICVjqgmkbI.ON4kIhjAuug9G', 'Mixología Molecular', 0, NOW(), true),
('Carlos Ramírez', 'carlos@bar.com', 'carlos_bartender', '$2a$10$Bvp8ngKMieBn9Mo6oXE53uNwEkVWzICVjqgmkbI.ON4kIhjAuug9G', 'Cocteles Tropicales', 0, NOW(), true);

-- Administradores (contraseña: admin123)
INSERT INTO administradores (nombre, email, usuario, contrasena, nivel_acceso, fecha_registro, activo) VALUES
('Admin Principal', 'admin@bar.com', 'admin', '$2a$10$FC3jiuSLU3Ds8gJBqVnA8.zvfnw7.YUIuhRDNZwFI00dsWNjJCvP.', 'TOTAL', NOW(), true),
('Supervisor Stock', 'supervisor@bar.com', 'supervisor', '$2a$10$FC3jiuSLU3Ds8gJBqVnA8.zvfnw7.YUIuhRDNZwFI00dsWNjJCvP.', 'INTERMEDIO', NOW(), true);

-- ============================================
-- 6. PREPARACIONES
-- ============================================

-- Preparación para Mojito
INSERT INTO preparaciones (tipo, tiempo_estimado_minutos, utensilios_necesarios) VALUES
('MACERADO', 5, 'Vaso highball, mortero, cuchara mezcladora');

-- Preparación para Margarita
INSERT INTO preparaciones (tipo, tiempo_estimado_minutos, utensilios_necesarios) VALUES
('BATIDO', 3, 'Coctelera, colador, copa margarita');

-- Preparación para Cuba Libre
INSERT INTO preparaciones (tipo, tiempo_estimado_minutos, utensilios_necesarios) VALUES
('CONSTRUIDO', 2, 'Vaso highball, cuchara');

-- ============================================
-- 7. PASOS DE PREPARACIÓN
-- ============================================

-- Pasos del Mojito (preparacion_id = 1)
INSERT INTO pasos_preparacion (preparacion_id, orden, descripcion, duracion_segundos) VALUES
(1, 1, 'Colocar las hojas de menta en el vaso y agregar el azúcar', 30),
(1, 2, 'Machacar suavemente con el mortero para liberar los aceites', 30),
(1, 3, 'Agregar el jugo de limón y mezclar', 20),
(1, 4, 'Llenar el vaso con hielo picado', 15),
(1, 5, 'Agregar el ron y mezclar bien', 20),
(1, 6, 'Completar con agua con gas', 10),
(1, 7, 'Decorar con una ramita de menta y una rodaja de limón', 15);

-- Pasos de la Margarita (preparacion_id = 2)
INSERT INTO pasos_preparacion (preparacion_id, orden, descripcion, duracion_segundos) VALUES
(2, 1, 'Escarchar el borde de la copa con sal', 20),
(2, 2, 'En la coctelera agregar hielo, tequila, triple sec y jugo de limón', 15),
(2, 3, 'Agitar vigorosamente durante 15 segundos', 15),
(2, 4, 'Colar en la copa preparada', 10),
(2, 5, 'Decorar con una rodaja de limón', 10);

-- Pasos del Cuba Libre (preparacion_id = 3)
INSERT INTO pasos_preparacion (preparacion_id, orden, descripcion, duracion_segundos) VALUES
(3, 1, 'Llenar el vaso con hielo', 10),
(3, 2, 'Agregar el ron', 5),
(3, 3, 'Completar con cola', 10),
(3, 4, 'Exprimir media lima y decorar', 15);

-- ============================================
-- 8. INFO NUTRICIONAL
-- ============================================
INSERT INTO info_cocteles (calorias, azucares, alcohol_porcentaje, carbohidratos, proteinas, grasas) VALUES
(217, 25, 13.5, 28, 0.3, 0.1),
(168, 8, 18.9, 11, 0.2, 0),
(185, 20, 12.8, 22, 0, 0);

-- ============================================
-- 9. COCTELES
-- ============================================

-- MOJITO
INSERT INTO cocteles (nombre, descripcion, categoria, dificultad, preparacion_id, info_coctel_id, 
                      veces_preparado, calificacion_promedio, vaso, hielo) VALUES
('Mojito', 'Refrescante coctel cubano con menta, ron blanco, limón y soda', 
 'CLASICO', 'MEDIA', 1, 1, 0, 0.0, 'Highball', 'Picado');

-- MARGARITA
INSERT INTO cocteles (nombre, descripcion, categoria, dificultad, preparacion_id, info_coctel_id,
                      veces_preparado, calificacion_promedio, vaso, hielo) VALUES
('Margarita', 'Clásico mexicano con tequila, triple sec y limón en copa escarchada',
 'CLASICO', 'FACIL', 2, 2, 0, 0.0, 'Copa Margarita', 'Sin hielo');

-- CUBA LIBRE
INSERT INTO cocteles (nombre, descripcion, categoria, dificultad, preparacion_id, info_coctel_id,
                      veces_preparado, calificacion_promedio, vaso, hielo) VALUES
('Cuba Libre', 'Ron con cola y un toque de limón, el favorito de Cuba',
 'HIGHBALL', 'FACIL', 3, 3, 0, 0.0, 'Highball', 'Cubos');

-- ============================================
-- 10. INGREDIENTES DE COCTELES
-- ============================================

-- Ingredientes del MOJITO (coctel_id = 1)
INSERT INTO ingredientes (coctel_id, insumo_id, cantidad, unidad_id, es_opcional, orden, notas, costo_unitario) VALUES
(1, 2, 60, 1, false, 1, 'Ron blanco cubano preferiblemente', 2500),
(1, 8, 30, 1, false, 2, 'Jugo fresco recién exprimido', 300),
(1, 24, 20, 4, false, 3, 'Azúcar blanca', 100),
(1, 21, 10, 6, false, 4, 'Hojas frescas', 600),
(1, 13, 100, 1, false, 5, 'Para completar', 200);

-- Ingredientes de MARGARITA (coctel_id = 2)
INSERT INTO ingredientes (coctel_id, insumo_id, cantidad, unidad_id, es_opcional, orden, notas, costo_unitario) VALUES
(2, 3, 45, 1, false, 1, 'Tequila blanco 100% agave', 3375),
(2, 7, 30, 1, false, 2, 'Triple sec o Cointreau', 1312),
(2, 8, 20, 1, false, 3, 'Jugo de limón fresco', 200),
(2, 23, 5, 4, false, 4, 'Para escarchar el borde', 10);

-- Ingredientes de CUBA LIBRE (coctel_id = 3)
INSERT INTO ingredientes (coctel_id, insumo_id, cantidad, unidad_id, es_opcional, orden, notas, costo_unitario) VALUES
(3, 2, 50, 1, false, 1, 'Ron blanco o añejo', 2083),
(3, 12, 150, 1, false, 2, 'Coca-Cola o similar', 375),
(3, 16, 1, 6, false, 3, 'Media lima exprimida', 250);

-- ============================================
-- 11. SUSTITUCIONES
-- ============================================

-- Triple Sec puede sustituirse por Cointreau
INSERT INTO sustituciones (insumo_original_id, insumo_sustituto_id, factor_conversion, calidad_sustitucion, notas) VALUES
(7, 6, 0.9, 5, 'Cointreau es Triple Sec premium, usar 10% menos');

-- Cointreau puede sustituirse por Triple Sec
INSERT INTO sustituciones (insumo_original_id, insumo_sustituto_id, factor_conversion, calidad_sustitucion, notas) VALUES
(6, 7, 1.1, 5, 'Triple Sec es similar pero menos potente, usar 10% más');

-- Vodka puede sustituirse por Gin
INSERT INTO sustituciones (insumo_original_id, insumo_sustituto_id, factor_conversion, calidad_sustitucion, notas) VALUES
(1, 4, 1.0, 3, 'El gin añade sabor botánico, puede alterar el perfil');

-- Gin puede sustituirse por Vodka
INSERT INTO sustituciones (insumo_original_id, insumo_sustituto_id, factor_conversion, calidad_sustitucion, notas) VALUES
(4, 1, 1.0, 3, 'Vodka es más neutral que gin');

-- Agua tónica puede sustituirse por Ginger Ale
INSERT INTO sustituciones (insumo_original_id, insumo_sustituto_id, factor_conversion, calidad_sustitucion, notas) VALUES
(10, 11, 1.0, 3, 'Ginger Ale es más dulce, cambia el perfil');

-- ============================================
-- VERIFICACIÓN
-- ============================================
SELECT 'Datos insertados correctamente' AS resultado;