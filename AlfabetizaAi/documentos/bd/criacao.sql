CREATE TABLE USUARIO(
	
	id_usuario NUMBER(38) PRIMARY KEY NOT NULL,
	nome VARCHAR2(255) NOT NULL,
	sobrenome VARCHAR2(255) NOT NULL,
	telefone VARCHAR2(255) NOT NULL,
	email VARCHAR2(255) NOT NULL UNIQUE,
	data_nascimento DATE NOT NULL,
	ativo CHAR(1) NOT NULL CHECK(ativo IN ('S', 'N')),
	sexo CHAR(1) NOT NULL CHECK(sexo IN ('M', 'F', 'O')),
	senha VARCHAR2(255) NOT NULL,
	cpf VARCHAR2(11) NOT NULL UNIQUE,

);

CREATE TABLE ADMIN(

  id_admin NUMBER(38) NOT NULL,
  id_usuario NUMBER(38) NOT NULL,
  descricao VARCHAR2(255) NOT NULL,
  PRIMARY KEY (id_admin),
  CONSTRAINT FK_ADMIN_ID_USUARIO
    FOREIGN KEY (id_usuario)
      REFERENCES USUARIO(id_usuario)
      
);

CREATE TABLE ALUNO(

  id_aluno NUMBER(38) NOT NULL,
  id_usuario NUMBER(38) NOT NULL,
  nome_aluno VARCHAR2(255) NOT NULL,
  sobrenome_aluno VARCHAR2(255) NOT NULL,
  data_nascimento_aluno DATE NOT NULL,
  sexo_aluno CHAR(1) NOT NULL CHECK(sexo_aluno IN ('M', 'F', 'O')),
  PRIMARY KEY (id_aluno),
  CONSTRAINT FK_ALUNO_ID_USUARIO
    FOREIGN KEY (id_usuario)
      REFERENCES USUARIO(id_usuario)
      
);

CREATE TABLE PROFESSOR(

  id_professor NUMBER(38) NOT NULL,
  id_usuario NUMBER(38) NOT NULL,
  descricao VARCHAR2(255) NOT NULL,
  PRIMARY KEY (id_professor),
  CONSTRAINT FK_PROFESSOR_ID_USUARIO
    FOREIGN KEY (id_usuario)
      REFERENCES USUARIO(id_usuario)
      
);

CREATE TABLE MODULO(

  id_modulo NUMBER(38) NOT NULL,
  id_professor NUMBER(38) NOT NULL,
  id_admin NUMBER(38),
  titulo VARCHAR2(255) NOT NULL,
  conteudo CLOB NOT NULL,
  classificacao_modulo NUMBER(1) NOT NULL,
  modulo_aprovado CHAR(1) CHECK (modulo_aprovado IN (NULL, 'S', 'N')),
  PRIMARY KEY (id_modulo),
  CONSTRAINT FK_MODULO_ID_ADMIN
    FOREIGN KEY (id_admin)
      REFERENCES ADMIN(id_admin),
  CONSTRAINT FK_MODULO_ID_PROFESSOR
    FOREIGN KEY (id_professor)
      REFERENCES PROFESSOR(id_professor)
      
);

CREATE TABLE DESAFIO(

  id_desafio NUMBER(38) NOT NULL,
  id_modulo NUMBER(38) NOT NULL,
  titulo VARCHAR2(255) NOT NULL,
  conteudo CLOB NOT NULL,
  tipo_desafio NUMBER(1) NOT NULL,
  PRIMARY KEY (id_desafio),
  CONSTRAINT FK_DESAFIO_ID_MODULO
    FOREIGN KEY (id_modulo)
      REFERENCES MODULO(id_modulo)
      
);

CREATE TABLE MODULO_ALUNO_DESAFIO(

  id_modulo_aluno_desafio NUMBER(38) NOT NULL,
  id_modulo NUMBER(38) NOT NULL,
  id_desafio NUMBER(38) NOT NULL,
  id_aluno NUMBER(38) NOT NULL,
  modulo_concluido CHAR(1) NOT NULL CHECK (modulo_concluido IN ('S', 'N')),
  desafio_concluido CHAR(1) NOT NULL CHECK (desafio_concluido IN ('S', 'N')),
  PRIMARY KEY (id_modulo_aluno_desafio),
  CONSTRAINT FK_MOD_AL_DESAFIO_ID_MODULO
    FOREIGN KEY (id_modulo)
      REFERENCES MODULO(id_modulo),
  CONSTRAINT FK_MOD_AL_DESAFIO_ID_DESAFIO
    FOREIGN KEY (id_desafio)
      REFERENCES DESAFIO(id_desafio),
  CONSTRAINT FK_MOD_AL_DESAFIO_ID_ALUNO
    FOREIGN KEY (id_aluno)
      REFERENCES ALUNO(id_aluno)
      
);







