INSERT INTO categories (name) VALUES
('Textil'),
('Print');

INSERT INTO products (name, price, image_url, featured, category_id) VALUES
('Lámina "Titis"', 29.99, 'https://imagen.jpg', 1, 2),
('Camiseta "Envejecer con las amigas"', 49.99, 'https://imagen.jpg', 0, 1);

INSERT INTO users (username, email, password) VALUES
('Lara', 'lara@correo.com', 'Lara123.'),
('Sofía', 'soffinutria@nutria.com', 'Nutria321.');