-- ============================================
-- NUEVOS INSUMOS (desde id = 27)
-- ============================================
INSERT INTO insumos (id, nombre, tipo, cantidad_disponible, cantidad_minima, precio, proveedor_id, unidad_id, codigo_barras) VALUES
(27, 'Vermut Rojo', 'LICOR', 1000, 300, 65000, 1, 1, '8410023000012'),
(28, 'Vermut Seco', 'LICOR', 1000, 300, 60000, 1, 1, '8410023000029'),
(29, 'Amaretto', 'LICOR', 800, 200, 85000, 1, 1, '8001110012345'),
(30, 'Licor de Café', 'LICOR', 700, 200, 75000, 1, 1, '7501110023456'),
(31, 'Licor de Cereza', 'LICOR', 500, 150, 80000, 1, 1, '5012345000123'),
(32, 'Ron Oscuro', 'LICOR', 1000, 300, 90000, 1, 1, '5099873089348'),
(33, 'Bourbon', 'LICOR', 900, 300, 95000, 1, 1, '5099873089350'),
(34, 'Cognac', 'LICOR', 500, 150, 120000, 1, 1, '3052910043014'),
(35, 'Absenta', 'LICOR', 300, 100, 110000, 1, 1, '8412345678901'),
(36, 'Vermut Blanco', 'LICOR', 800, 200, 62000, 1, 1, '8410023000036'),
(37, 'Clara de Huevo', 'MIXER', 50, 10, 2000, 2, 6, NULL),
(38, 'Jugo de Tomate', 'MIXER', 1000, 200, 4000, 2, 1, NULL),
(39, 'Salsa Worcestershire', 'MIXER', 200, 50, 8000, 2, 1, NULL),
(40, 'Salsa Tabasco', 'MIXER', 200, 50, 9000, 2, 1, NULL),
(41, 'Espresso', 'MIXER', 300, 50, 2500, 2, 1, NULL),
(42, 'Jarabe de Almendra', 'JARABE', 400, 100, 10000, 2, 1, NULL),
(43, 'Zumo de Piña', 'MIXER', 800, 200, 6000, 2, 1, NULL),
(44, 'Granadina Oscura', 'JARABE', 300, 100, 13000, 2, 1, NULL),
(45, 'Hielo en Cubos', 'CANTIDAD', 500, 100, 1000, 2, 6, NULL);

-- ============================================
-- NUEVAS PREPARACIONES (desde id = 4)
-- ============================================
INSERT INTO preparaciones (id, tipo, tiempo_estimado_minutos, utensilios_necesarios) VALUES
(4, 'REMOVIDO', 3, 'Vaso Old Fashioned, cuchara mezcladora, cubos de hielo'),
(5, 'REMOVIDO', 3, 'Vaso mezclador, cuchara, colador, copa Old Fashioned'),
(6, 'REMOVIDO', 4, 'Vaso mezclador, colador, copa Martini'),
(7, 'BATIDO', 5, 'Coctelera, colador, copa Sour'),
(8, 'BATIDO', 5, 'Coctelera, colador fino, copa Martini'),
(9, 'BATIDO', 6, 'Coctelera grande, vaso Collins'),
(10, 'BATIDO', 6, 'Coctelera, vaso tiki o alto'),
(11, 'CONSTRUIDO', 4, 'Vaso highball, cuchara larga'),
(12, 'REMOVIDO', 5, 'Vaso Old Fashioned, cucharilla, vaso mezclador'),
(13, 'AGITADO', 4, 'Coctelera, colador, copa Martini'),
(14, 'REMOVIDO', 4, 'Vaso mezclador, colador, copa Old Fashioned');

-- ============================================
-- PASOS DE PREPARACIÓN (resumen representativo)
-- ============================================
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
(14, 2, 'Remover con hielo y colar en copa Old Fashioned', 15);

-- ============================================
-- INFO COCTELES (desde id = 4)
-- ============================================
INSERT INTO info_cocteles (id, calorias, azucares, alcohol_porcentaje, carbohidratos, proteinas, grasas) VALUES
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
(15, 195, 6, 30.0, 8, 0, 0);

-- ============================================
-- COCTELES (desde id = 4)
-- ============================================
INSERT INTO cocteles (id, nombre, descripcion, categoria, dificultad, preparacion_id, info_coctel_id, veces_preparado, calificacion_promedio, vaso, hielo) VALUES
(4, 'Old Fashioned', 'Coctel clásico con bourbon, azúcar y bitters', 'CLASICO', 'ALTA', 4, 4, 0, 0.0, 'Old Fashioned', 'Cubos'),
(5, 'Negroni', 'Equilibrio perfecto entre gin, Campari y vermut rojo', 'APERITIVO', 'ALTA', 5, 5, 0, 0.0, 'Old Fashioned', 'Cubos'),
(6, 'Manhattan', 'Whisky bourbon con vermut rojo y bitter aromático', 'CLASICO', 'ALTA', 6, 6, 0, 0.0, 'Copa Cóctel', 'Sin hielo'),
(7, 'Whisky Sour', 'Cóctel ácido y sedoso con whisky, limón y clara de huevo', 'SOUR', 'ALTA', 7, 7, 0, 0.0, 'Copa Sour', 'Cubos'),
(8, 'Espresso Martini', 'Martini con espresso, vodka y licor de café', 'MODERNO', 'ALTA', 8, 8, 0, 0.0, 'Copa Martini', 'Sin hielo'),
(9, 'Long Island Iced Tea', 'Potente mezcla de licores con toque de limón y cola', 'TIKI', 'ALTA', 9, 9, 0, 0.0, 'Vaso Alto', 'Cubos'),
(10, 'Mai Tai', 'Exótico cóctel tropical con ron y almendra', 'TIKI', 'ALTA', 10, 10, 0, 0.0, 'Vaso Tiki', 'Picado'),
(11, 'Bloody Mary', 'Cóctel salado y picante con vodka y jugo de tomate', 'DESAYUNO', 'ALTA', 11, 11, 0, 0.0, 'Highball', 'Cubos'),
(12, 'Sazerac', 'Antiguo cóctel de Nueva Orleans con whisky y absenta', 'CLASICO', 'ALTA', 12, 12, 0, 0.0, 'Old Fashioned', 'Sin hielo'),
(13, 'Aviation', 'Cóctel floral y cítrico con gin y licor de cereza', 'CLASICO', 'ALTA', 13, 13, 0, 0.0, 'Copa Martini', 'Sin hielo'),
(14, 'Boulevardier', 'Versión con whisky del Negroni', 'APERITIVO', 'ALTA', 14, 14, 0, 0.0, 'Old Fashioned', 'Cubos');

-- ============================================
-- INGREDIENTES DE COCTELES
-- ============================================
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
(14, 27, 30, 1, false, 3, 'Vermut rojo', 1200);

-- ============================================
-- NUEVAS SUSTITUCIONES
-- ============================================
INSERT INTO sustituciones (insumo_original_id, insumo_sustituto_id, factor_conversion, calidad_sustitucion, notas) VALUES
(27, 28, 1.0, 4, 'Vermut seco puede sustituir al rojo en cócteles más secos'),
(28, 27, 1.0, 4, 'Vermut rojo puede reemplazar al seco para sabor más dulce'),
(2, 32, 1.0, 5, 'Ron oscuro puede usarse en lugar del blanco para más cuerpo'),
(32, 2, 1.0, 5, 'Ron blanco puede usarse en lugar del oscuro para sabor más ligero'),
(29, 42, 1.1, 4, 'Jarabe de almendra puede reemplazar Amaretto en cócteles dulces'),
(42, 29, 0.9, 4, 'Amaretto puede sustituir orgeat con perfil más alcohólico');
