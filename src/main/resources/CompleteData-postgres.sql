SET session_replication_role = 'replica';
-- ============================================
-- LIMPIEZA INICIAL (Opcional)
-- ============================================
TRUNCATE TABLE sustituciones CASCADE;
TRUNCATE TABLE ingredientes CASCADE;
TRUNCATE TABLE pasos_preparacion CASCADE;
TRUNCATE TABLE preparaciones CASCADE;
TRUNCATE TABLE cocteles CASCADE;
TRUNCATE TABLE info_cocteles CASCADE;
TRUNCATE TABLE insumos CASCADE;
TRUNCATE TABLE bartenders CASCADE;
TRUNCATE TABLE administradores CASCADE;
TRUNCATE TABLE proveedores CASCADE;
TRUNCATE TABLE direcciones CASCADE;
TRUNCATE TABLE unidades_medida CASCADE;

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

-- LICORES (se mantienen igual)
INSERT INTO insumos (id, nombre, tipo, cantidad_disponible, cantidad_minima, precio, proveedor_id, unidad_id, codigo_barras) VALUES
(1, 'Vodka Absolut', 'LICOR', 2000, 500, 85000, 1, 1, '7312040017034'),
(2, 'Ron Havana Club 3 Años', 'LICOR', 1500, 500, 75000, 1, 1, '8501110080422'),
(3, 'Tequila Jose Cuervo Silver', 'LICOR', 1200, 400, 90000, 1, 1, '7501035010109'),
(4, 'Gin Bombay Sapphire', 'LICOR', 1000, 300, 95000, 1, 1, '5010677710015'),
(5, 'Whisky Jack Daniels', 'LICOR', 800, 300, 120000, 1, 1, '5099873089347'),
(6, 'Cointreau', 'LICOR', 500, 200, 85000, 1, 1, '3035542007003'),
(7, 'Triple Sec', 'LICOR', 0, 200, 35000, 1, 1, '7501035010116'),

-- NUEVOS INSUMOS (desde id = 27)
(27, 'Vermut Rojo', 'LICOR', 1000, 300, 65000, 1, 1, '8410023000012'),
(28, 'Vermut Seco', 'LICOR', 1000, 300, 60000, 1, 1, '8410023000029'),
(29, 'Amaretto', 'LICOR', 800, 200, 85000, 1, 1, '8001110012345'),
(30, 'Licor de Café', 'LICOR', 700, 200, 75000, 1, 1, '7501110023456'),
(31, 'Licor de Cereza', 'LICOR', 500, 150, 80000, 1, 1, '5012345000123'),
(32, 'Ron Oscuro', 'LICOR', 1000, 300, 90000, 1, 1, '5099873089348'),
(33, 'Bourbon', 'LICOR', 900, 300, 95000, 1, 1, '5099873089350'),
(34, 'Cognac', 'LICOR', 500, 150, 120000, 1, 1, '3052910043014'),
(35, 'Absenta', 'LICOR', 300, 100, 110000, 1, 1, '8412345678901'),
(36, 'Vermut Blanco', 'LICOR', 800, 200, 62000, 1, 1, '8410023000036');

-- MIXERS (se mantienen igual)
INSERT INTO insumos (id, nombre, tipo, cantidad_disponible, cantidad_minima, precio, proveedor_id, unidad_id) VALUES
(8, 'Jugo de Limón Natural', 'MIXER', 500, 200, 5000, 2, 1),
(9, 'Jugo de Naranja Natural', 'MIXER', 600, 200, 6000, 2, 1),
(10, 'Agua Tónica', 'MIXER', 2000, 500, 3000, 3, 1),
(11, 'Ginger Ale', 'MIXER', 1500, 400, 3500, 3, 1),
(12, 'Refresco de Cola', 'MIXER', 2500, 500, 2500, 3, 1),
(13, 'Agua Mineral con Gas', 'MIXER', 3000, 500, 2000, 3, 1),

-- NUEVOS MIXERS
(37, 'Clara de Huevo', 'MIXER', 50, 10, 2000, 2, 6),
(38, 'Jugo de Tomate', 'MIXER', 1000, 200, 4000, 2, 1),
(39, 'Salsa Worcestershire', 'MIXER', 200, 50, 8000, 2, 1),
(40, 'Salsa Tabasco', 'MIXER', 200, 50, 9000, 2, 1),
(41, 'Espresso', 'MIXER', 300, 50, 2500, 2, 1),
(43, 'Zumo de Piña', 'MIXER', 800, 200, 6000, 2, 1);

-- JARABES (se mantienen igual)
INSERT INTO insumos (id, nombre, tipo, cantidad_disponible, cantidad_minima, precio, proveedor_id, unidad_id) VALUES
(14, 'Jarabe Simple', 'JARABE', 800, 200, 8000, 2, 1),
(15, 'Granadina', 'JARABE', 400, 150, 12000, 1, 1),

-- NUEVOS JARABES
(42, 'Jarabe de Almendra', 'JARABE', 400, 100, 10000, 2, 1),
(44, 'Granadina Oscura', 'JARABE', 300, 100, 13000, 2, 1);

-- FRUTAS Y GARNISH (se mantienen igual)
INSERT INTO insumos (id, nombre, tipo, cantidad_disponible, cantidad_minima, precio, proveedor_id, unidad_id) VALUES
(16, 'Limones', 'FRUTA', 30, 10, 500, 2, 6),
(17, 'Naranjas', 'FRUTA', 25, 10, 600, 2, 6),
(18, 'Fresas', 'FRUTA', 20, 5, 8000, 2, 4),
(19, 'Cerezas Marrasquino', 'GARNISH', 50, 20, 15000, 2, 6),
(20, 'Aceitunas', 'GARNISH', 40, 15, 12000, 2, 6);

-- HIERBAS Y ESPECIAS (se mantienen igual)
INSERT INTO insumos (id, nombre, tipo, cantidad_disponible, cantidad_minima, precio, proveedor_id, unidad_id) VALUES
(21, 'Hojas de Menta Fresca', 'HIERBAS', 50, 10, 3000, 2, 4),
(22, 'Albahaca', 'HIERBAS', 30, 10, 3500, 2, 4),
(23, 'Sal de Mar', 'ESPECIAS', 500, 100, 2000, 2, 4),
(24, 'Azúcar', 'ESPECIAS', 1000, 200, 3000, 2, 4);

-- BITTERS Y CREMAS (se mantienen igual)
INSERT INTO insumos (id, nombre, tipo, cantidad_disponible, cantidad_minima, precio, proveedor_id, unidad_id) VALUES
(25, 'Angostura Bitters', 'BITTER', 300, 50, 25000, 1, 1),
(26, 'Crema de Coco', 'CREMA', 400, 150, 18000, 2, 1);

-- CORRECCIÓN: Cambiar 'CANTIDAD' por 'GARNISH'
INSERT INTO insumos (id, nombre, tipo, cantidad_disponible, cantidad_minima, precio, proveedor_id, unidad_id) VALUES
(45, 'Hielo en Cubos', 'GARNISH', 500, 100, 1000, 2, 6);

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

-- Preparaciones originales
INSERT INTO preparaciones (id, tipo, tiempo_estimado_minutos, utensilios_necesarios) VALUES
(1, 'MACERADO', 5, 'Vaso highball, mortero, cuchara mezcladora'),
(2, 'BATIDO', 3, 'Coctelera, colador, copa margarita'),
(3, 'CONSTRUIDO', 2, 'Vaso highball, cuchara');

-- NUEVAS PREPARACIONES (desde id = 4)
INSERT INTO preparaciones (id, tipo, tiempo_estimado_minutos, utensilios_necesarios) VALUES
(4, 'BATIDO', 3, 'Vaso Old Fashioned, cuchara mezcladora, cubos de hielo'),
(5, 'BATIDO', 3, 'Vaso mezclador, cuchara, colador, copa Old Fashioned'),
(6, 'BATIDO', 4, 'Vaso mezclador, colador, copa Martini'),
(7, 'BATIDO', 5, 'Coctelera, colador, copa Sour'),
(8, 'BATIDO', 5, 'Coctelera, colador fino, copa Martini'),
(9, 'BATIDO', 6, 'Coctelera grande, vaso Collins'),
(10, 'BATIDO', 6, 'Coctelera, vaso tiki o alto'),
(11, 'CONSTRUIDO', 4, 'Vaso highball, cuchara larga'),
(12, 'BATIDO', 5, 'Vaso Old Fashioned, cucharilla, vaso mezclador'),
(13, 'BATIDO', 4, 'Coctelera, colador, copa Martini'),
(14, 'BATIDO', 4, 'Vaso mezclador, colador, copa Old Fashioned'),

-- PREPARACIONES PARA CÓCTELES EXPERTOS
(15, 'BATIDO', 8, 'Coctelera, colador fino, vaso highball, termómetro'),
(16, 'BATIDO', 7, 'Coctelera, colador fino, licuadora, vaso tiki, mechero'),
(17, 'BATIDO', 6, 'Vaso mezclador, cuchara, colador, copa Old Fashioned');

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

-- PASOS DE PREPARACIÓN NUEVOS (resumen representativo)
INSERT INTO pasos_preparacion (preparacion_id, orden, descripcion, duracion_segundos) VALUES
(4, 1, 'Colocar azúcar y bitters en el vaso Old Fashioned', 30),
(4, 2, 'Agregar un chorrito de agua y disolver', 20),
(4, 3, 'Añadir bourbon y hielo en cubos', 20),
(4, 4, 'Remover suavemente y decorar con cáscara de naranja', 15),

(5, 1, 'Verter gin, vermut rojo y Campari en vaso mezclador con hielo', 30),
(5, 2, 'Remover hasta enfriar y colar en vaso Old Fashioned', 20),
(5, 3, 'Decorar con rodaja de naranja', 10),

(6, 1, 'Agregar bourbon, vermut rojo y angostura en vaso mezclador con hielo', 30),
(6, 2, 'Remover bien y colar en copa de cóctel', 20),
(6, 3, 'Decorar con cereza', 10),

(7, 1, 'Agregar whisky, jugo de limón y jarabe simple en coctelera', 30),
(7, 2, 'Incorporar clara de huevo y agitar en seco', 20),
(7, 3, 'Agregar hielo, agitar nuevamente y colar', 20),

(8, 1, 'Preparar espresso y dejar enfriar ligeramente', 20),
(8, 2, 'Agregar vodka, licor de café y espresso en coctelera con hielo', 20),
(8, 3, 'Agitar y colar en copa Martini', 15),

(9, 1, 'Combinar todos los licores y el jugo de limón en coctelera', 30),
(9, 2, 'Agitar con hielo y colar en vaso alto con refresco de cola', 20),

(10, 1, 'Verter ron oscuro, ron blanco, curaçao, jugos y jarabe en coctelera', 30),
(10, 2, 'Agitar con hielo y colar en vaso tiki con hielo picado', 20),
(10, 3, 'Decorar con menta y cereza', 10),

(11, 1, 'En vaso alto agregar vodka y jugo de tomate con hielo', 20),
(11, 2, 'Añadir Worcestershire y tabasco, remover suavemente', 15),
(11, 3, 'Decorar con apio y limón', 10),

(12, 1, 'Enfriar vaso Old Fashioned con hielo', 10),
(12, 2, 'Agregar absenta, remover y desechar exceso', 20),
(12, 3, 'Añadir whisky y bitter de Peychaud, remover y servir', 30),

(13, 1, 'Agregar gin, licor de cereza, marasquino y limón a la coctelera', 30),
(13, 2, 'Agitar con hielo y colar en copa Martini', 20),

(14, 1, 'Agregar bourbon, Campari y vermut rojo en vaso mezclador', 20),
(14, 2, 'Remover con hielo y colar en copa Old Fashioned', 15),

-- Pasos del Ramos Gin Fizz (EXPERTO)
(15, 1, 'Agitar todos los ingredientes SIN hielo por 2 minutos (agitado en seco)', 120),
(15, 2, 'Agregar hielo y agitar vigorosamente por 1 minuto más', 60),
(15, 3, 'Colar cuidadosamente en vaso highball sin hielo', 20),
(15, 4, 'Dejar reposar 1 minuto para que se forme la espuma característica', 60),

-- Pasos del Zombie (EXPERTO)
(16, 1, 'En coctelera, agregar todos los rones, licores y jugos', 30),
(16, 2, 'Agitar vigorosamente con hielo por 20 segundos', 20),
(16, 3, 'Colar en vaso tiki lleno de hielo picado', 15),
(16, 4, 'Flambear con ron 151 para la capa superior', 30),
(16, 5, 'Decorar con rama de menta, cerezas y especias', 25),

-- Pasos del Vieux Carré (EXPERTO)
(17, 1, 'En vaso mezclador, agregar whisky, coñac y vermuts', 25),
(17, 2, 'Añadir hielo y remover suavemente por 30 segundos', 30),
(17, 3, 'Agregar bitters y remover 5 veces más', 10),
(17, 4, 'Colar en copa Old Fashioned con un cubo de hielo grande', 15),
(17, 5, 'Exprimir cáscara de limón sobre la bebida y decorar', 10);

-- ============================================
-- 8. INFO NUTRICIONAL
-- ============================================
INSERT INTO info_cocteles (id, calorias, azucares, alcohol_porcentaje, carbohidratos, proteinas, grasas) VALUES
(1, 217, 25, 13.5, 28, 0.3, 0.1),
(2, 168, 8, 18.9, 11, 0.2, 0),
(3, 185, 20, 12.8, 22, 0, 0),

-- INFO COCTELES NUEVOS (desde id = 4)
(4, 180, 2, 32.0, 3, 0, 0),
(5, 190, 5, 28.5, 5, 0, 0),
(6, 200, 4, 30.0, 6, 0, 0),
(7, 160, 8, 22.0, 10, 0.2, 0),
(8, 170, 6, 25.0, 9, 0.3, 0),
(9, 230, 15, 27.0, 20, 0, 0),
(10, 240, 18, 26.5, 22, 0, 0),
(11, 120, 10, 12.0, 14, 0.4, 0),
(12, 210, 4, 35.0, 5, 0, 0),
(13, 190, 12, 24.0, 11, 0, 0),
(14, 200, 5, 29.0, 6, 0, 0),

-- INFO COCTELES EXPERTOS
(15, 210, 18, 16.0, 20, 2.5, 3.0),
(16, 320, 25, 22.5, 28, 0, 0),
(17, 245, 8, 31.0, 10, 0, 0);

-- ============================================
-- 9. COCTELES
-- ============================================

-- COCTELES ORIGINALES
INSERT INTO cocteles (id, nombre, descripcion, categoria, dificultad, preparacion_id, info_coctel_id, 
                      veces_preparado, calificacion_promedio, vaso, hielo) VALUES
(1, 'Mojito', 'Refrescante coctel cubano con menta, ron blanco, limón y soda', 
 'CLASICO', 'MEDIA', 1, 1, 0, 0.0, 'Highball', 'Picado'),
(2, 'Margaaaaaaaaaaarita', 'Clásico mexicano con tequila, triple sec y limón en copa escarchada',
 'CLASICO', 'FACIL', 2, 2, 0, 0.0, 'Copa Margarita', 'Sin hielo'),
(3, 'Cuba Libre', 'Ron con cola y un toque de limón, el favorito de Cuba',
 'HIGHBALL', 'FACIL', 3, 3, 0, 0.0, 'Highball', 'Cubos'),

-- NUEVOS COCTELES CORREGIDOS (cambiar 'ALTA' por 'DIFICIL')
(4, 'Old Fashioned', 'Coctel clásico con bourbon, azúcar y bitters', 'CLASICO', 'DIFICIL', 4, 4, 0, 0.0, 'Old Fashioned', 'Cubos'),
(5, 'Negroni', 'Equilibrio perfecto entre gin, Campari y vermut rojo', 'APERITIVO', 'DIFICIL', 5, 5, 0, 0.0, 'Old Fashioned', 'Cubos'),
(6, 'Manhattan', 'Whisky bourbon con vermut rojo y bitter aromático', 'CLASICO', 'DIFICIL', 6, 6, 0, 0.0, 'Copa Cóctel', 'Sin hielo'),
(7, 'Whisky Sour', 'Cóctel ácido y sedoso con whisky, limón y clara de huevo', 'SOUR', 'DIFICIL', 7, 7, 0, 0.0, 'Copa Sour', 'Cubos'),
(8, 'Espresso Martini', 'Martini con espresso, vodka y licor de café', 'CONTEMPORANEO', 'DIFICIL', 8, 8, 0, 0.0, 'Copa Martini', 'Sin hielo'),
(9, 'Long Island Iced Tea', 'Potente mezcla de licores con toque de limón y cola', 'TIKI', 'DIFICIL', 9, 9, 0, 0.0, 'Vaso Alto', 'Cubos'),
(10, 'Mai Tai', 'Exótico cóctel tropical con ron y almendra', 'TIKI', 'DIFICIL', 10, 10, 0, 0.0, 'Vaso Tiki', 'Picado'),
(11, 'Bloody Mary', 'Cóctel salado y picante con vodka y jugo de tomate', 'CONTEMPORANEO', 'DIFICIL', 11, 11, 0, 0.0, 'Highball', 'Cubos'),
(12, 'Sazerac', 'Antiguo cóctel de Nueva Orleans con whisky y absenta', 'CLASICO', 'EXPERTO', 12, 12, 0, 0.0, 'Old Fashioned', 'Sin hielo'),
(13, 'Aviation', 'Cóctel floral y cítrico con gin y licor de cereza', 'CLASICO', 'EXPERTO', 13, 13, 0, 0.0, 'Copa Martini', 'Sin hielo'),
(14, 'Boulevardier', 'Versión con whisky del Negroni', 'APERITIVO', 'DIFICIL', 14, 14, 0, 0.0, 'Old Fashioned', 'Cubos'),

-- COCTELES EXPERTOS
(15, 'Ramos Gin Fizz', 'Clásico de Nueva Orleans con gin, crema, clara de huevo, agua de azahar y esencias florales. Requiere agitado prolongado.', 'CLASICO', 'EXPERTO', 15, 15, 0, 0.0, 'Highball', 'Sin hielo'),
(16, 'Zombie', 'Poderoso cóctel tiki creado por Donn Beach. Mezcla múltiples rones, jugos tropicales y especias. Límite de 2 por persona.', 'TIKI', 'EXPERTO', 16, 16, 0, 0.0, 'Vaso Tiki', 'Picado'),
(17, 'Vieux Carré', 'Elegante cóctel de Nueva Orleans que combina whisky, coñac, vermuts y bitters. Nombrado en honor al French Quarter.', 'CLASICO', 'EXPERTO', 17, 17, 0, 0.0, 'Old Fashioned', 'Cubos');

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

-- INGREDIENTES DE COCTELES NUEVOS
INSERT INTO ingredientes (coctel_id, insumo_id, cantidad, unidad_id, es_opcional, orden, notas, costo_unitario) VALUES
(4, 33, 60, 1, false, 1, 'Usar bourbon de calidad', 2500),
(4, 24, 5, 4, false, 2, 'Azúcar blanca o terrón', 100),
(4, 27, 2, 1, true, 3, 'Toque opcional de agua', 50),

(5, 4, 30, 1, false, 1, 'Gin seco', 1800),
(5, 27, 30, 1, false, 2, 'Vermut rojo italiano', 1200),
(5, 6, 30, 1, false, 3, 'Campari', 1500),

(6, 33, 50, 1, false, 1, 'Bourbon o rye', 2500),
(6, 27, 25, 1, false, 2, 'Vermut rojo dulce', 1000),
(6, 24, 2, 4, true, 3, 'Bitter aromático', 100),

(7, 33, 60, 1, false, 1, 'Whisky bourbon', 2500),
(7, 8, 25, 1, false, 2, 'Zumo de limón fresco', 400),
(7, 37, 1, 6, false, 3, 'Clara para espuma', 300),

(8, 1, 50, 1, false, 1, 'Vodka', 2000),
(8, 30, 30, 1, false, 2, 'Licor de café', 1800),
(8, 41, 30, 1, false, 3, 'Espresso recién hecho', 300),

(9, 1, 15, 1, false, 1, 'Vodka', 600),
(9, 2, 15, 1, false, 2, 'Ron blanco', 700),
(9, 3, 15, 1, false, 3, 'Tequila', 800),
(9, 6, 15, 1, false, 4, 'Triple sec', 600),
(9, 8, 20, 1, false, 5, 'Jugo de limón', 200),
(9, 12, 100, 1, false, 6, 'Refresco de cola', 200),

(10, 2, 30, 1, false, 1, 'Ron blanco', 700),
(10, 32, 30, 1, false, 2, 'Ron oscuro', 900),
(10, 42, 15, 1, false, 3, 'Jarabe de almendra (orgeat)', 500),
(10, 43, 15, 1, false, 4, 'Zumo de piña', 300),
(10, 8, 20, 1, false, 5, 'Zumo de limón', 300),

(11, 1, 45, 1, false, 1, 'Vodka', 1800),
(11, 38, 120, 1, false, 2, 'Jugo de tomate natural', 400),
(11, 39, 5, 1, false, 3, 'Worcestershire', 200),
(11, 40, 2, 1, false, 4, 'Tabasco', 200),

(12, 33, 60, 1, false, 1, 'Whisky de centeno', 2500),
(12, 35, 5, 1, true, 2, 'Absenta solo para enjuagar vaso', 400),

(13, 4, 45, 1, false, 1, 'Gin seco', 1800),
(13, 31, 15, 1, false, 2, 'Licor de cereza marasquino', 1500),
(13, 8, 10, 1, false, 3, 'Zumo de limón', 200),

(14, 33, 45, 1, false, 1, 'Bourbon o whisky americano', 2500),
(14, 6, 30, 1, false, 2, 'Campari', 1500),
(14, 27, 30, 1, false, 3, 'Vermut rojo', 1200),

-- INGREDIENTES DE COCTELES EXPERTOS
-- Ramos Gin Fizz
(15, 4, 45, 1, false, 1, 'Gin London Dry', 1800),
(15, 8, 30, 1, false, 2, 'Jugo de limón fresco', 300),
(15, 9, 15, 1, false, 3, 'Jugo de lima fresco', 200),
(15, 14, 30, 1, false, 4, 'Jarabe simple', 400),
(15, 37, 1, 6, false, 5, 'Clara de huevo', 300),
(15, 26, 30, 1, false, 6, 'Crema de leche', 500),
(15, 45, 2, 7, false, 7, 'Agua de azahar o agua de rosas', 200),
(15, 13, 60, 1, false, 8, 'Agua con gas', 100),

-- Zombie
(16, 2, 45, 1, false, 1, 'Ron blanco puertorriqueño', 1800),
(16, 32, 45, 1, false, 2, 'Ron oscuro jamaiquino', 2000),
(16, 34, 30, 1, false, 3, 'Ron 151 proof para flambear', 2500),
(16, 8, 30, 1, false, 4, 'Jugo de limón fresco', 300),
(16, 43, 30, 1, false, 5, 'Jugo de piña natural', 400),
(16, 15, 15, 1, false, 6, 'Granadina', 500),
(16, 42, 10, 1, false, 7, 'Jarabe de almendra (orgeat)', 300),
(16, 25, 2, 7, false, 8, 'Angostura bitters', 200),
(16, 44, 10, 1, true, 9, 'Granadina oscura para color', 400),
(16, 21, 4, 6, false, 10, 'Rama de menta para decorar', 150),

-- Vieux Carré
(17, 33, 30, 1, false, 1, 'Whisky rye americano', 2200),
(17, 34, 30, 1, false, 2, 'Coñac VSOP', 2800),
(17, 27, 30, 1, false, 3, 'Vermut rojo dulce', 1200),
(17, 28, 5, 1, false, 4, 'Vermut seco para balance', 300),
(17, 25, 2, 7, false, 5, 'Angostura bitters', 200),
(17, 24, 1, 7, true, 6, 'Benedictine (opcional)', 400),
(17, 16, 1, 6, false, 7, 'Cáscara de limón para expresar', 100);

-- ============================================
-- 11. SUSTITUCIONES
-- ============================================

-- SUSTITUCIONES ORIGINALES
INSERT INTO sustituciones (insumo_original_id, insumo_sustituto_id, factor_conversion, calidad_sustitucion, notas) VALUES
(7, 6, 0.9, 5, 'Cointreau es Triple Sec premium, usar 10% menos'),
(6, 7, 1.1, 5, 'Triple Sec es similar pero menos potente, usar 10% más'),
(1, 4, 1.0, 3, 'El gin añade sabor botánico, puede alterar el perfil'),
(4, 1, 1.0, 3, 'Vodka es más neutral que gin'),
(10, 11, 1.0, 3, 'Ginger Ale es más dulce, cambia el perfil'),

-- NUEVAS SUSTITUCIONES
(27, 28, 1.0, 4, 'Vermut seco puede sustituir al rojo en cócteles más secos'),
(28, 27, 1.0, 4, 'Vermut rojo puede reemplazar al seco para sabor más dulce'),
(2, 32, 1.0, 5, 'Ron oscuro puede usarse en lugar del blanco para más cuerpo'),
(32, 2, 1.0, 5, 'Ron blanco puede usarse en lugar del oscuro para sabor más ligero'),
(29, 42, 1.1, 4, 'Jarabe de almendra puede reemplazar Amaretto en cócteles dulces'),
(42, 29, 0.9, 4, 'Amaretto puede sustituir orgeat con perfil más alcohólico'),

-- SUSTITUCIONES ADICIONALES PARA INGREDIENTES ESPECIALIZADOS
(34, 33, 1.0, 4, 'Whisky puede sustituir coñac en Vieux Carré con perfil diferente'),
(42, 29, 0.8, 3, 'Amaretto puede reemplazar orgeat pero es más dulce y alcohólico'),
(25, 24, 1.0, 2, 'Bitters aromáticos pueden sustituir Angostura pero cambian perfil');



-- 1. ACTUALIZAR veces_preparado en COCTELES para mostrar estadísticas relevantes
UPDATE cocteles SET veces_preparado = 145 WHERE id = 1;  -- Mojito (más preparado)
UPDATE cocteles SET veces_preparado = 128 WHERE id = 2;  -- Margarita
UPDATE cocteles SET veces_preparado = 98 WHERE id = 3;   -- Cuba Libre
UPDATE cocteles SET veces_preparado = 87 WHERE id = 4;   -- Old Fashioned
UPDATE cocteles SET veces_preparado = 76 WHERE id = 5;   -- Negroni
UPDATE cocteles SET veces_preparado = 65 WHERE id = 6;   -- Manhattan
UPDATE cocteles SET veces_preparado = 54 WHERE id = 7;   -- Whisky Sour
UPDATE cocteles SET veces_preparado = 48 WHERE id = 8;   -- Espresso Martini
UPDATE cocteles SET veces_preparado = 42 WHERE id = 9;   -- Long Island
UPDATE cocteles SET veces_preparado = 38 WHERE id = 10;  -- Mai Tai
UPDATE cocteles SET veces_preparado = 32 WHERE id = 11;  -- Bloody Mary
UPDATE cocteles SET veces_preparado = 25 WHERE id = 12;  -- Sazerac (EXPERTO)
UPDATE cocteles SET veces_preparado = 18 WHERE id = 13;  -- Aviation (EXPERTO)
UPDATE cocteles SET veces_preparado = 28 WHERE id = 14;  -- Boulevardier
UPDATE cocteles SET veces_preparado = 12 WHERE id = 15;  -- Ramos Gin Fizz (EXPERTO)
UPDATE cocteles SET veces_preparado = 8 WHERE id = 16;   -- Zombie (EXPERTO)
UPDATE cocteles SET veces_preparado = 15 WHERE id = 17;  -- Vieux Carré (EXPERTO)

-- 2. ACTUALIZAR INSUMOS - Bajar stock de varios para alertas
UPDATE insumos SET cantidad_disponible = 150 WHERE id = 6;   -- Cointreau - BAJO
UPDATE insumos SET cantidad_disponible = 180 WHERE id = 8;   -- Jugo de Limón - BAJO (muy usado)
UPDATE insumos SET cantidad_disponible = 35 WHERE id = 21;   -- Menta - BAJO
UPDATE insumos SET cantidad_disponible = 80 WHERE id = 14;   -- Jarabe Simple - BAJO
UPDATE insumos SET cantidad_disponible = 250 WHERE id = 4;   -- Gin - BAJO
UPDATE insumos SET cantidad_disponible = 400 WHERE id = 2;   -- Ron Havana - BAJO (muy usado)
UPDATE insumos SET cantidad_disponible = 120 WHERE id = 25;  -- Angostura Bitters - BAJO
UPDATE insumos SET cantidad_disponible = 180 WHERE id = 37;  -- Clara de Huevo - BAJO
UPDATE insumos SET cantidad_disponible = 90 WHERE id = 42;   -- Jarabe de Almendra - BAJO
UPDATE insumos SET cantidad_disponible = 15 WHERE id = 19;   -- Cerezas - CRÍTICO


SELECT setval(pg_get_serial_sequence('unidades_medida', 'id'), 
              COALESCE((SELECT MAX(id) FROM unidades_medida), 1), true);
SELECT setval(pg_get_serial_sequence('direcciones', 'id'), 
              COALESCE((SELECT MAX(id) FROM direcciones), 1), true);
SELECT setval(pg_get_serial_sequence('proveedores', 'id'), 
              COALESCE((SELECT MAX(id) FROM proveedores), 1), true);
SELECT setval(pg_get_serial_sequence('insumos', 'id'), 
              COALESCE((SELECT MAX(id) FROM insumos), 1), true);
SELECT setval(pg_get_serial_sequence('bartenders', 'id'), 
              COALESCE((SELECT MAX(id) FROM bartenders), 1), true);
SELECT setval(pg_get_serial_sequence('administradores', 'id'), 
              COALESCE((SELECT MAX(id) FROM administradores), 1), true);
SELECT setval(pg_get_serial_sequence('preparaciones', 'id'), 
              COALESCE((SELECT MAX(id) FROM preparaciones), 1), true);
SELECT setval(pg_get_serial_sequence('pasos_preparacion', 'id'), 
              COALESCE((SELECT MAX(id) FROM pasos_preparacion), 1), true);
SELECT setval(pg_get_serial_sequence('info_cocteles', 'id'), 
              COALESCE((SELECT MAX(id) FROM info_cocteles), 1), true);
SELECT setval(pg_get_serial_sequence('cocteles', 'id'), 
              COALESCE((SELECT MAX(id) FROM cocteles), 1), true);
SELECT setval(pg_get_serial_sequence('ingredientes', 'id'), 
              COALESCE((SELECT MAX(id) FROM ingredientes), 1), true);
SELECT setval(pg_get_serial_sequence('sustituciones', 'id'), 
              COALESCE((SELECT MAX(id) FROM sustituciones), 1), true);


SET session_replication_role = 'origin';

-- ============================================
-- VERIFICACIÓN
-- ============================================
SELECT 'Datos insertados correctamente' AS resultado;