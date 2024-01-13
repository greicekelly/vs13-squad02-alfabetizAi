CREATE SEQUENCE SEQ_USUARIO
	START WITH 1
	INCREMENT BY 1
	NOCACHE NOCYCLE;
	
CREATE SEQUENCE SEQ_ADMIN
	START WITH 1
	INCREMENT BY 1
	NOCACHE NOCYCLE;
	
CREATE SEQUENCE SEQ_ALUNO
	START WITH 1
	INCREMENT BY 1
	NOCACHE NOCYCLE;
	
CREATE SEQUENCE SEQ_PROFESSOR
	START WITH 1
	INCREMENT BY 1
	NOCACHE NOCYCLE;
	
CREATE SEQUENCE SEQ_MODULO
	START WITH 1
	INCREMENT BY 1
	NOCACHE NOCYCLE;
	
CREATE SEQUENCE SEQ_DESAFIO
	START WITH 1
	INCREMENT BY 1
	NOCACHE NOCYCLE;
	
CREATE SEQUENCE SEQ_MODULO_ALUNO_DESAFIO
	START WITH 1
	INCREMENT BY 1
	NOCACHE NOCYCLE;



--Inserção dados USUARIO

INSERT INTO USUARIO (id_usuario, nome, sobrenome, telefone, email, data_nascimento, ativo, sexo, senha)
VALUES(SEQ_USUARIO.nextval, 'Filipe', 'Andrade Prata', '(79) 99691-9876', 'filipe@email', TO_DATE('27-07-2001', 'dd-mm-yyyy'), 'S', 'M', '0000');

INSERT INTO USUARIO (id_usuario, nome, sobrenome, telefone, email, data_nascimento, ativo, sexo, senha)
VALUES(SEQ_USUARIO.nextval, 'Helcius', 'Cabral', '(70) 99999-5555', 'helcius@email', TO_DATE('10-01-2000', 'dd-mm-yyyy'), 'S', 'M', '0000');

INSERT INTO USUARIO (id_usuario, nome, sobrenome, telefone, email, data_nascimento, ativo, sexo, senha)
VALUES(SEQ_USUARIO.nextval, 'Greice', 'Rosa', '(51) 98888-3333', 'greice@email', TO_DATE('20-10-1999', 'dd-mm-yyyy'), 'S', 'F', '0000');

INSERT INTO USUARIO (id_usuario, nome, sobrenome, telefone, email, data_nascimento, ativo, sexo, senha)
VALUES(SEQ_USUARIO.nextval, 'Renan', 'Meira', '(21) 97777-7777', 'renan@email', TO_DATE('14-03-2001', 'dd-mm-yyyy'), 'S', 'M', '0000');

INSERT INTO USUARIO (id_usuario, nome, sobrenome, telefone, email, data_nascimento, ativo, sexo, senha)
VALUES(SEQ_USUARIO.nextval, 'Gabriel', 'Johann', '(79) 94444-0000', 'gabriel@email', TO_DATE('27-09-2003', 'dd-mm-yyyy'), 'S', 'M', '0000');

INSERT INTO USUARIO (id_usuario, nome, sobrenome, telefone, email, data_nascimento, ativo, sexo, senha)
VALUES(SEQ_USUARIO.nextval, 'Tiago', 'Raupp', '(51) 99696-2222', 'tiago@email', TO_DATE('28-05-1995', 'dd-mm-yyyy'), 'S', 'M', '0000');

INSERT INTO ADMIN (id_usuario, nome, sobrenome, telefone, email, data_nascimento, ativo, sexo, senha)
VALUES(SEQ_USUARIO.nextval, 'Tiago', 'Raupp', '(51) 99696-2222', 'tiago@email', TO_DATE('28-05-1995', 'dd-mm-yyyy'), 'S', 'M', '0000');





--Inserção dados ADMIN(vinculação de Usuário_Admin)

INSERT INTO ADMIN (id_admin, id_usuario, descricao)
VALUES (SEQ_ADMIN.NEXTVAL, 1, 'Responsável por verificar conteúdo criado');

INSERT INTO ADMIN (id_admin, id_usuario, descricao)
VALUES (SEQ_ADMIN.NEXTVAL, 2, 'Responsável por verificar conteúdo criado');

INSERT INTO ADMIN (id_admin, id_usuario, descricao)
VALUES (SEQ_ADMIN.NEXTVAL, 3, 'Responsável por manutenção bando de dados');

INSERT INTO ADMIN (id_admin, id_usuario, descricao)
VALUES (SEQ_ADMIN.NEXTVAL, 4, 'Responsável por manutenção bando de dados');

INSERT INTO ADMIN (id_admin, id_usuario, descricao)
VALUES (SEQ_ADMIN.NEXTVAL, 5, 'Responsável por deploy do sistema');

INSERT INTO ADMIN (id_admin, id_usuario, descricao)
VALUES (SEQ_ADMIN.NEXTVAL, 6, 'Responsável por manutenção do sistema');




--Inserção dados PROFESSOR(vinculação de Usuário_Professor)




--Inserção dados ALUNO(vinculação de Usuário_Aluno)



--Inserção dados DESAFIO(vinculação de MODULO)
INSERT INTO DESAFIO (id_desafio, id_modulo, titulo, conteudo)
VALUES(SEQ_DESAFIO.nextval, 1, 'Acerte as vogais', 'Acerte a sequência  de vogais');

INSERT INTO DESAFIO (id_desafio, id_modulo, titulo, conteudo)
VALUES(SEQ_DESAFIO.nextval, 2, 'Forme as palavras', 'Forme a sequência  de palvras');

INSERT INTO DESAFIO (id_desafio, id_modulo, titulo, conteudo)
VALUES(SEQ_DESAFIO.nextval, 3, 'Animais com a letra A', 'Insira nome de animais com a letra A');

INSERT INTO DESAFIO (id_desafio, id_modulo, titulo, conteudo)
VALUES(SEQ_DESAFIO.nextval, 4, 'Frutas com a letra B', 'Acerte as frutas com a letra B');

--Inserção dados MODULO
INSERT INTO MODULO (id_modulo, id_professor, titulo, conteudo, modulo_aprovado, classificacao, id_admin)
VALUES(SEQ_MODULO.nextval, 1, 'Você conhece as vogais?', 'Conheça as vogais! A de Aniversário, E de Escola, I de Indígena, O de Onda e U de Universo', 'Iniciante', 's', 1);

INSERT INTO MODULO (id_modulo, id_professor, titulo, conteudo, modulo_aprovado, classificacao, id_admin)
VALUES(SEQ_MODULO.nextval, 1, 'Vogais e os animais!', 'O nome de muitos animais começam com vogais! A de Abelha, E de Elefante, I de Iguana, O de Ornitorrinco, U de Urso', 'Iniciante', 's', 1);

INSERT INTO MODULO (id_modulo, id_professor, titulo, conteudo, modulo_aprovado, classificacao, id_admin)
VALUES(SEQ_MODULO.nextval, 2, 'Consoantes e as frutas!', 'Você já provou essas deliciosas frutas que começam com as consoantes? B de Banana, C de Cereja, D de Damasco, F de Framboesa, G de Goiaba, H de ...', 'Iniciante', 'n', 1);

INSERT INTO MODULO (id_modulo, id_professor, titulo, conteudo, modulo_aprovado, classificacao, id_admin)
VALUES(SEQ_MODULO.nextval, 3, 'Sílabas: O lego do alfabeto!', 'Você conhece as peças de lego? Com as sílabas podemos montar palavras assim como montamos lego!', 'Iniciante', 'n', 2);

INSERT INTO MODULO (id_modulo, id_professor, titulo, conteudo, modulo_aprovado, classificacao, id_admin)
VALUES(SEQ_MODULO.nextval, 3, 'Descobrindo os Números Mágicos', 'Vamos explorar os números mágicos! Eles têm propriedades especiais que os tornam incríveis. 2, 3, 5 e 7 são alguns desses números mágicos!', 'Intermediario', 's', 2);

INSERT INTO MODULO (id_modulo, id_professor, titulo, conteudo, modulo_aprovado, classificacao, id_admin)
VALUES(SEQ_MODULO.nextval, 1, 'Descobrindo Palavras: A Magia da Leitura', 'Embarque na magia da leitura! Vamos descobrir novas palavras e histórias encantadoras. A cada palavra, um mundo novo se abre diante de nós! A primeira palavra é: Mangá! Os quadrinhos japoneses...', 'Avançado', 's', 2);

INSERT INTO MODULO (id_modulo, id_professor, titulo, conteudo, modulo_aprovado, classificacao, id_admin)
VALUES(SEQ_MODULO.nextval, 1, 'Consoantes pelo mundo!', 'Divirta-se enquanto aprende as consoantes! P de Parque', 'Iniciante', 'n', 1);