-- Insertar datos de ejemplo en tipo_programa
INSERT INTO tipo_programa (id, tipo, descripcion) VALUES
    (random_uuid(), 'Documental', 'Programas educativos y de información factual'),
    (random_uuid(), 'Noticiero', 'Programas de noticias y actualidad'),
    (random_uuid(), 'Serie', 'Series de ficción y entretenimiento');

-- Insertar datos de ejemplo en programa con clasificación española
INSERT INTO programa (id, nombre, descripcion, clasificacion, canal, fecha_inicio, fecha_fin, semanal, dias, tipo_programa_id) VALUES
    (random_uuid(), 'Noticias de la Mañana', 'Noticias diarias matutinas', 'TP', 'Canal 1', '2024-09-02 08:00:00', '2024-09-02 09:00:00', TRUE, 'Lunes,Martes,Miércoles,Jueves,Viernes', (SELECT id FROM tipo_programa WHERE tipo='Noticiero' LIMIT 1)),
    (random_uuid(), 'Documentales del Mundo', 'Documentales sobre naturaleza y cultura', 'TP', 'Canal 2', '2024-09-02 20:00:00', '2024-09-02 21:00:00', TRUE, 'Domingo', (SELECT id FROM tipo_programa WHERE tipo='Documental' LIMIT 1)),
    (random_uuid(), 'Serie de Misterio', 'Serie de ficción con elementos de suspense', '16', 'Canal 3', '2024-09-02 22:00:00', '2024-09-02 23:00:00', TRUE, 'Lunes', (SELECT id FROM tipo_programa WHERE tipo='Serie' LIMIT 1)),
    (random_uuid(), 'Cine de Medianoche', 'Películas para adultos con temas serios', '18', 'Canal 4', '2024-09-02 23:00:00', '2024-09-03 01:00:00', FALSE, 'Sábado', (SELECT id FROM tipo_programa WHERE tipo='Serie' LIMIT 1));