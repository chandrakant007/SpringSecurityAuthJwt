-- Password for all users is: password123
INSERT INTO users (username, password, role) VALUES 
('admin_user', '$2a$10$dM4sX9q1dPHOQADCB2.4eu8DfbnLnUxeRDZ2GOXgENIbtPGkWgLKu', 'ROLE_ADMIN'),
('amit_shah', '$2a$10$dM4sX9q1dPHOQADCB2.4eu8DfbnLnUxeRDZ2GOXgENIbtPGkWgLKu', 'ROLE_USER'),
('priya_g', '$2a$10$dM4sX9q1dPHOQADCB2.4eu8DfbnLnUxeRDZ2GOXgENIbtPGkWgLKu', 'ROLE_USER'),
('vijay_v', '$2a$10$dM4sX9q1dPHOQADCB2.4eu8DfbnLnUxeRDZ2GOXgENIbtPGkWgLKu', 'ROLE_USER');