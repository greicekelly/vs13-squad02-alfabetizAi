ALTER SESSION SET CURRENT_SCHEMA=VS_13_EQUIPE_2;

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

CREATE SEQUENCE SEQ_DESAFIO_ALTERNATIVA
	START WITH 1
	INCREMENT BY 1
	NOCACHE NOCYCLE;


--Inserção dados USUARIO

INSERT INTO USUARIO (id_usuario, nome, sobrenome, telefone, email, data_nascimento, ativo, sexo, senha, cpf)
VALUES(SEQ_USUARIO.nextval, 'Filipe', 'Andrade Prata', '(79) 99691-9876', 'filipe@email', TO_DATE('27-07-2001', 'dd-mm-yyyy'), 'S', 'M', '0000', '12345678900');

INSERT INTO USUARIO (id_usuario, nome, sobrenome, telefone, email, data_nascimento, ativo, sexo, senha, cpf)
VALUES(SEQ_USUARIO.nextval, 'Helcius', 'Cabral', '(70) 99999-5555', 'helcius@email', TO_DATE('10-01-2000', 'dd-mm-yyyy'), 'S', 'M', '0000', '00123456789');

INSERT INTO USUARIO (id_usuario, nome, sobrenome, telefone, email, data_nascimento, ativo, sexo, senha, cpf)
VALUES(SEQ_USUARIO.nextval, 'Greice', 'Rosa', '(51) 98888-3333', 'greice@email', TO_DATE('20-10-1999', 'dd-mm-yyyy'), 'S', 'F', '0000', '45678912300');

INSERT INTO USUARIO (id_usuario, nome, sobrenome, telefone, email, data_nascimento, ativo, sexo, senha, cpf)
VALUES(SEQ_USUARIO.nextval, 'Renan', 'Meira', '(21) 97777-7777', 'renan@email', TO_DATE('14-03-2001', 'dd-mm-yyyy'), 'S', 'M', '0000', '00456789123');

INSERT INTO USUARIO (id_usuario, nome, sobrenome, telefone, email, data_nascimento, ativo, sexo, senha, cpf)
VALUES(SEQ_USUARIO.nextval, 'Gabriel', 'Johann', '(79) 94444-0000', 'gabriel@email', TO_DATE('27-09-2003', 'dd-mm-yyyy'), 'S', 'M', '0000', '78912345600');

INSERT INTO USUARIO (id_usuario, nome, sobrenome, telefone, email, data_nascimento, ativo, sexo, senha, cpf)
VALUES(SEQ_USUARIO.nextval, 'Tiago', 'Raupp', '(51) 99696-2222', 'tiago@email', TO_DATE('28-05-1995', 'dd-mm-yyyy'), 'S', 'M', '0000', '00789123456');

INSERT INTO USUARIO (id_usuario, nome, sobrenome, telefone, email, data_nascimento, ativo, sexo, senha, cpf)
VALUES(SEQ_USUARIO.nextval, 'Rafael', 'Santos', '(11) 99124-1515', 'rafael@email', TO_DATE('03-02-1992', 'dd-mm-yyyy'), 'S', 'M', '0000', '32414805005');

INSERT INTO USUARIO (id_usuario, nome, sobrenome, telefone, email, data_nascimento, ativo, sexo, senha, cpf)
VALUES(SEQ_USUARIO.nextval, 'Julia', 'Almeida', '(68) 99321-1515', 'julia@email', TO_DATE('21-08-1990', 'dd-mm-yyyy'), 'S', 'F', '0000', '31531621074');

INSERT INTO USUARIO (id_usuario, nome, sobrenome, telefone, email, data_nascimento, ativo, sexo, senha, cpf)
VALUES(SEQ_USUARIO.nextval, 'Bruno', 'Cardoso', '(51) 99136-1515', 'bruno@email', TO_DATE('02-05-1997', 'dd-mm-yyyy'), 'S', 'M', '0000', '55680201089');

INSERT INTO USUARIO (id_usuario, nome, sobrenome, telefone, email, data_nascimento, ativo, sexo, senha, cpf)
VALUES(SEQ_USUARIO.nextval, 'Rafael', 'Silveira', '(51) 99136-4015', 'rafael.s@email', TO_DATE('02-05-1997', 'dd-mm-yyyy'), 'S', 'M', '0000', '05474124015');

INSERT INTO USUARIO (id_usuario, nome, sobrenome, telefone, email, data_nascimento, ativo, sexo, senha, cpf)
VALUES(SEQ_USUARIO.nextval, 'Luiza', 'Roberta', '(51) 98765-4015', 'luiza@email', TO_DATE('02-04-1995', 'dd-mm-yyyy'), 'S', 'F', '0000', '98290517068');

--Inserção dados ADMIN(vinculação de Usuário_Admin)

INSERT INTO ADMIN (id_admin, id_usuario, descricao)
VALUES (SEQ_ADMIN.NEXTVAL, (SELECT u.ID_USUARIO FROM USUARIO u WHERE u.CPF = '12345678900'), 'Responsável por verificar conteúdo criado');

INSERT INTO ADMIN (id_admin, id_usuario, descricao)
VALUES (SEQ_ADMIN.NEXTVAL, (SELECT u.ID_USUARIO FROM USUARIO u WHERE u.CPF = '00123456789'), 'Responsável por verificar conteúdo criado');

INSERT INTO ADMIN (id_admin, id_usuario, descricao)
VALUES (SEQ_ADMIN.NEXTVAL, (SELECT u.ID_USUARIO FROM USUARIO u WHERE u.CPF = '45678912300'), 'Responsável por manutenção bando de dados');

INSERT INTO ADMIN (id_admin, id_usuario, descricao)
VALUES (SEQ_ADMIN.NEXTVAL, (SELECT u.ID_USUARIO FROM USUARIO u WHERE u.CPF = '00456789123'), 'Responsável por manutenção bando de dados');

INSERT INTO ADMIN (id_admin, id_usuario, descricao)
VALUES (SEQ_ADMIN.NEXTVAL, (SELECT u.ID_USUARIO FROM USUARIO u WHERE u.CPF = '78912345600'), 'Responsável por deploy do sistema');

INSERT INTO ADMIN (id_admin, id_usuario, descricao)
VALUES (SEQ_ADMIN.NEXTVAL, (SELECT u.ID_USUARIO FROM USUARIO u WHERE u.CPF = '00789123456'), 'Responsável por manutenção do sistema');

--Inserção dados PROFESSOR(vinculação de Usuário_Professor)

INSERT INTO PROFESSOR (id_professor, id_usuario, descricao)
VALUES (SEQ_PROFESSOR.NEXTVAL, (SELECT u.ID_USUARIO FROM USUARIO u WHERE u.CPF = '05474124015'), 'Bacharelado em Letras')

INSERT INTO PROFESSOR (id_professor, id_usuario, descricao)
VALUES (SEQ_PROFESSOR.NEXTVAL, (SELECT u.ID_USUARIO FROM USUARIO u WHERE u.CPF = '32414805005'), 'Licenciatura em Letras')

INSERT INTO PROFESSOR (id_professor, id_usuario, descricao)
VALUES (SEQ_PROFESSOR.NEXTVAL, (SELECT u.ID_USUARIO FROM USUARIO u WHERE u.CPF = '31531621074'), 'Bacharelado em Letras')

--Inserção dados ALUNO(vinculação de Usuário_Aluno)

INSERT INTO ALUNO (id_aluno, id_usuario, nome_aluno, sobrenome_aluno, data_nascimento_aluno, sexo_aluno)
VALUES(SEQ_ALUNO.NEXTVAL, (SELECT u.ID_USUARIO FROM USUARIO u WHERE u.CPF = '55680201089'), 'Alice', 'Moraes', TO_DATE('02-05-1997', 'dd-mm-yyyy'), 'F');

INSERT INTO ALUNO (id_aluno, id_usuario, nome_aluno, sobrenome_aluno, data_nascimento_aluno, sexo_aluno)
VALUES(SEQ_ALUNO.NEXTVAL, (SELECT u.ID_USUARIO FROM USUARIO u WHERE u.CPF = '05474124015'), 'Enzo', 'Moraes', TO_DATE('02-05-2020', 'dd-mm-yyyy'), 'M');

INSERT INTO ALUNO (id_aluno, id_usuario, nome_aluno, sobrenome_aluno, data_nascimento_aluno, sexo_aluno)
VALUES(SEQ_ALUNO.NEXTVAL, (SELECT u.ID_USUARIO FROM USUARIO u WHERE u.CPF = '98290517068'), 'Erica', 'Roberta', TO_DATE('02-05-2019', 'dd-mm-yyyy'), 'F');

--Inserção dados MODULO
INSERT INTO MODULO (id_modulo, id_professor, titulo, conteudo, classificacao_modulo, modulo_aprovado, id_admin)
VALUES(SEQ_MODULO.nextval, 1, 'Você conhece as vogais?', 'Conheça as vogais! A de Aniversário, E de Escola, I de Indígena, O de Onda e U de Universo', 1, 's', 1);

INSERT INTO MODULO (id_modulo, id_professor, titulo, conteudo, classificacao_modulo, modulo_aprovado, id_admin)
VALUES(SEQ_MODULO.nextval, 1, 'Vogais e os animais!', 'O nome de muitos animais começam com vogais! A de Abelha, E de Elefante, I de Iguana, O de Ornitorrinco, U de Urso', 1, 's', 1);

INSERT INTO MODULO (id_modulo, id_professor, titulo, conteudo, classificacao_modulo, modulo_aprovado, id_admin)
VALUES(SEQ_MODULO.nextval, 2, 'Consoantes e as frutas!', 'Você já provou essas deliciosas frutas que começam com as consoantes? B de Banana, C de Cereja, D de Damasco, F de Framboesa, G de Goiaba, H de ...', 1, 'n', 1);

INSERT INTO MODULO (id_modulo, id_professor, titulo, conteudo, classificacao_modulo, modulo_aprovado, id_admin)
VALUES(SEQ_MODULO.nextval, 3, 'Sílabas: O lego do alfabeto!', 'Você conhece as peças de lego? Com as sílabas podemos montar palavras assim como montamos lego!', 1, 'n', 2);

INSERT INTO MODULO (id_modulo, id_professor, titulo, conteudo, classificacao_modulo, modulo_aprovado, id_admin)
VALUES(SEQ_MODULO.nextval, 3, 'Descobrindo os Números Mágicos', 'Vamos explorar os números mágicos! Eles têm propriedades especiais que os tornam incríveis. 2, 3, 5 e 7 são alguns desses números mágicos!', 2, 's', 2);

INSERT INTO MODULO (id_modulo, id_professor, titulo, conteudo, classificacao_modulo, modulo_aprovado, id_admin)
VALUES(SEQ_MODULO.nextval, 1, 'Descobrindo Palavras: A Magia da Leitura', 'Embarque na magia da leitura! Vamos descobrir novas palavras e histórias encantadoras. A cada palavra, um mundo novo se abre diante de nós! A primeira palavra é: Mangá! Os quadrinhos japoneses...', 3, 's', 2);

INSERT INTO MODULO (id_modulo, id_professor, titulo, conteudo, classificacao_modulo, modulo_aprovado, id_admin)
VALUES(SEQ_MODULO.nextval, 1, 'Consoantes pelo mundo!', 'Divirta-se enquanto aprende as consoantes! P de Parque', 1, 'n', 1);

--Inserção dados DESAFIO(vinculação de MODULO)

INSERT INTO DESAFIO (id_desafio, id_modulo, titulo, conteudo, tipo_desafio)
VALUES(SEQ_DESAFIO.nextval, 1, 'Acerte as vogais', 'Acerte a sequência  de vogais', 2);

INSERT INTO DESAFIO (id_desafio, id_modulo, titulo, conteudo, tipo_desafio)
VALUES(SEQ_DESAFIO.nextval, 2, 'Forme as palavras', 'Forme a sequência  de palvras', 2);

INSERT INTO DESAFIO (id_desafio, id_modulo, titulo, conteudo, tipo_desafio)
VALUES(SEQ_DESAFIO.nextval, 3, 'Animais com a letra A', 'Insira nome de animais com a letra A', 1);

INSERT INTO DESAFIO (id_desafio, id_modulo, titulo, conteudo, tipo_desafio)
VALUES(SEQ_DESAFIO.nextval, 4, 'Frutas com a letra B', 'Acerte as frutas com a letra B', 1);
