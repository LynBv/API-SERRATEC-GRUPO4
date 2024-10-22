INSERT INTO usuario (nome, sobrenome, email, senha, data_nascimento) VALUES 
('Rayssa', 'Vieira', 'rayssa.vieira@example.com', 'Senhazinha123', '1990-01-15'),
('Elyn', 'Virginio', 'elyn.virginio@example.com', 'Laela456', '1985-06-23'),
('Rhayssa', 'Frias', 'rhayssa.frias@example.com', 'senhaSegura789', '1992-09-30'),
('Rodrigo', 'Carvalho', 'rodrigo.carvalho@example.com', 'LordMechamadeLord147', '1995-04-12'),
('Patrick', 'Paiva', 'patrick.paiva@example.com', 'SenhordoDiscord258', '1991-07-18'),
('Alberto', 'Paz', 'alberto.paz@example.com', 'Rodrigo2_369', '1988-11-03'),
('Christian', 'Araujo', 'christian.araujo@example.com', 'SenhorDoSalgado159', '1993-02-26'),
('Milton', 'Mendes', 'milton.mendes@example.com', 'Cafezinho753', '1986-12-10'),
('Roberto', 'Carlos', 'roberto.carlos@example.com', 'SoNoNatal951', '1997-08-05'),
('João', 'Das Coves', 'joao.das.coves@example.com', 'SoFaltouEle852', '1989-03-21');


INSERT INTO relacionamento (id_seguidor, id_seguido, data_inicio_seguimento) VALUES
(1, 2, '2024-10-20'), /* Rayssa (id 1)*/ 
(1, 3, '2024-10-21'),
(1, 4, '2024-10-22'), 
(1, 5, '2024-10-23'),  
(1, 6, '2024-10-24'),

(2, 1, '2024-10-20'),  /* Elyn (id 2)*/ 
(2, 3, '2024-10-21'),  
(2, 4, '2024-10-22'),  
(2, 5, '2024-10-23'),  
(2, 6, '2024-10-24'),  

(3, 1, '2024-10-20'),  /* Rhayssa (id 3)*/
(3, 2, '2024-10-21'),
(3, 4, '2024-10-22'),  
(3, 5, '2024-10-23'), 
(3, 6, '2024-10-24'),

(4, 1, '2024-10-20'), /* Rodrigo (id 4)*/ 
(4, 2, '2024-10-21'),  
(4, 3, '2024-10-22'), 
(4, 5, '2024-10-23'), 
(4, 6, '2024-10-24'),

(5, 1, '2024-10-20'), /* Patrick (id 5)*/ 
(5, 2, '2024-10-21'),
(5, 3, '2024-10-22'),
(5, 4, '2024-10-23'), 
(5, 6, '2024-10-24'),

(6, 1, '2024-10-20'), /* Alberto (id 6)*/
(6, 2, '2024-10-21'),
(6, 3, '2024-10-22'),
(6, 4, '2024-10-23'),
(6, 5, '2024-10-24'), 

(7, 1, '2024-10-20'), /* Christian (id 7)*/ 
(7, 2, '2024-10-21'), 
(7, 3, '2024-10-22'), 
(7, 4, '2024-10-23'),
(7, 5, '2024-10-24'),

(8, 1, '2024-10-20'), /* Milton (id 8)*/ 
(8, 2, '2024-10-21'), 
(8, 3, '2024-10-22'), 
(8, 4, '2024-10-23'), 
(8, 5, '2024-10-24'), 

(9, 1, '2024-10-20'), /* Roberto (id 9)*/
(9, 2, '2024-10-21'), 
(9, 3, '2024-10-22'), 
(9, 4, '2024-10-23'), 
(9, 5, '2024-10-24'), 

(10, 1, '2024-10-20'), /* João (id 10)*/
(10, 2, '2024-10-21'), 
(10, 3, '2024-10-22'),
(10, 4, '2024-10-23'),
(10, 5, '2024-10-24'); 

INSERT INTO postagem (conteudo, data_criacao, id_usuario) VALUES
('Ajudando um necessitado!! #serratec #2024.02', '2024-10-20', 6), /*foto que Patrick tem*/ 
('Adotei um novo amiguinho, até atualizei minha foto de perfil!!', '2024-10-21', 1), /*passar a foto de um gatinho pra Ray no usuário*/
('Ocupada, corrigindo Bugs!!', '2024-10-21', 2), /*foto de computador com bug sendo resolvido*/
('Dando aula de API!!', '2024-10-20', 6), /*foto de um professor dando aula*/
('Finalmente ganhei a tão esperada Paçoca!!! #bugs #tecday', '2024-10-19', 4), /*foto do Rodrigo com uma caixa de paçoca*/
('Finalmente descongelado!!', '2024-10-18', 9), /* foto Roberto Carlos descongelado*/
('Passando um cafezinho!!', '2024-10-17', 8); /*Milton passando cafezinho*/

INSERT INTO comentario (texto, data_criacao, id_usuario, id_postagem) VALUES
('Ótima postagem, Patrick!', '2024-10-21', 7, 1),
('Que lindinho, trás ele aqui em casa!', '2024-10-21', 2, 2),
('Interessante, Roberto!', '2024-10-22', 4, 6),
('Fazer o bem e muito bom, Petrick!', '2024-10-21', 4, 1),
('Agora você não reclama !', '2024-10-20', 5, 5), 
('Obaaaaa meu natal, não e mesmo sem você!', '2024-10-19', 6, 6),
('Esse seu cafezinho e ótimo, kkk!', '2024-10-18', 8, 7),
('Depois eu quero a sua ajuda AHAHAHAAAAAAAA!', '2024-10-17', 3, 3),
('Gostei do código, Rodrigo, ta igual do Professor!', '2024-10-16', 2, 5),
('Vou até passar um café por isso!', '2024-10-15', 8, 8);
