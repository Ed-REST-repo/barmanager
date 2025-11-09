-- Unidades de medida
INSERT INTO unidades_medida (nombre, abreviatura, tipo) VALUES
('Mililitros', 'ml', 'VOLUMEN'),
('Onzas', 'oz', 'VOLUMEN'),
('Gramos', 'g', 'PESO'),
('Unidades', 'un', 'CANTIDAD'),
('Dash', 'dash', 'CANTIDAD');

-- Proveedores
INSERT INTO proveedores (nombre, telefono, email, activo) VALUES
('Licores Premium', '3001234567', 'ventas@licorespremium.com', true),
('Frutas Frescas', '3007654321', 'pedidos@frutasfrescas.com', true);

-- Insumos
INSERT INTO insumos (nombre, tipo, cantidad_disponible, cantidad_minima, precio, proveedor_id, unidad_id) VALUES
('Vodka Absolut', 'LICOR', 2000, 500, 85000, 1, 1),
('Ron Havana Club', 'LICOR', 1500, 500, 75000, 1, 1),
('Tequila Jose Cuervo', 'LICOR', 1200, 400, 90000, 1, 1),
('Jugo de Limón', 'MIXER', 500, 200, 5000, 2, 1),
('Jarabe Simple', 'JARABE', 800, 200, 8000, 2, 1),
('Hojas de Menta', 'HIERBAS', 50, 10, 3000, 2, 3),
('Angostura Bitters', 'BITTER', 300, 50, 25000, 1, 1);

-- Sustituciones comunes
INSERT INTO sustituciones (insumo_original_id, insumo_sustituto_id, factor_conversion, calidad_sustitucion, notas) VALUES
(1, 2, 1.0, 4, 'Vodka puede sustituirse por Ron en algunos cocteles'),
(2, 3, 1.0, 3, 'Ron puede sustituirse por Tequila para variaciones');