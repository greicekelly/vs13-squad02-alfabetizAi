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


