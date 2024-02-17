------------
-- Classe Desafio --

---- Dados ---------

-- Criar DB
criar vemserdbc

use vemserdbc
already on db vemserdbc
db.createCollection("alfabetizai.desafio")

-- Inserir--
---CriarDesafios---
db.alfabetizai.desafio.insertMany([
    {
        "id_modulo": 1,
        "titulo": "Escolha a letra inicial",
        "conteudo": "Aprenda as consoantes",
        "tipo_desafio": 1,
        "instrucao": "Marque a letra, que corresponde a primeira letra da palavra Banana.",
        "pontos": 10,
        "ativo": "s",
        "a": "c",
        "b": "t",
        "c": "b",
        "d": "q",
        "e": "p",
        "alternativa_correta": "c"
    },
    {
        "id_modulo": 2,
        "titulo": "Complete a palavra",
        "conteudo": "Complete a palavra com as letras fornecidas",
        "tipo_desafio": 1,
        "instrucao": "Complete a palavra: M _ SA",
	"a": "c",
        "b": "t",
        "c": "e",
        "d": "q",
        "e": "p",
        "alternativa_correta": "c",
        "pontos": 15,
        "ativo": "s"
    },
    {
        "id_modulo": 1,
        "titulo": "Ordene as letras",
        "conteudo": "Exercício de ordenação de letras",
        "tipo_desafio": 3,
        "instrucao": "Ordene as letras para formar uma palavra: A _ C _ O",
	"a": "cao",
        "b": "tatu",
        "c": "bala",
        "d": "quero",
        "e": "pato",
        "alternativa_correta": "a",
        "pontos": 20,
        "ativo": "s"

    },
    {
        "id_modulo": 2,
        "titulo": "Complete a sequência",
        "conteudo": "Complete a sequência alfabética",
        "tipo_desafio": 1,
        "instrucao": "Complete a sequência: A, B, C, _, E",
	"a": "c",
        "b": "t",
        "c": "d",
        "d": "q",
        "e": "p",
        "alternativa_correta": "c",
        "pontos": 10,
        "ativo": "s"
    },
    {
        "id_modulo": 2,
        "titulo": "Encontre a vogal",
        "conteudo": "Identificação de vogais",
        "tipo_desafio": 1,
        "instrucao": "Identifique a vogal na palavra: LAR",
	"a": "c",
        "b": "e",
        "c": "b",
        "d": "a",
        "e": "p",
        "alternativa_correta": "d",
        "pontos": 10,
        "ativo": "s"
    }
])
{
  acknowledged: true,
  insertedIds: {
    '0': ObjectId('65d0fcc74ce4df5e8f9cdec6'),
    '1': ObjectId('65d0fcc74ce4df5e8f9cdec7'),
    '2': ObjectId('65d0fcc74ce4df5e8f9cdec8'),
    '3': ObjectId('65d0fcc74ce4df5e8f9cdec9'),
    '4': ObjectId('65d0fcc74ce4df5e8f9cdeca')
  }
}

-- Buscar
--- metodo 1 - listarTodos ----

db.alfabetizai.desafio.find({})
{
  _id: ObjectId('65d0fcc74ce4df5e8f9cdec6'),
  id_modulo: 1,
  titulo: 'Escolha a letra inicial',
  conteudo: 'Aprenda as consoantes',
  tipo_desafio: 1,
  instrucao: 'Marque a letra, que corresponde a primeira letra da palavra Banana.',
  pontos: 10,
  ativo: 's',
  a: 'c',
  b: 't',
  c: 'b',
  d: 'q',
  e: 'p',
  alternativa_correta: 'c'
}
{
  _id: ObjectId('65d0fcc74ce4df5e8f9cdec7'),
  id_modulo: 2,
  titulo: 'Complete a palavra',
  conteudo: 'Complete a palavra com as letras fornecidas',
  tipo_desafio: 1,
  instrucao: 'Complete a palavra: M _ SA',
  a: 'c',
  b: 't',
  c: 'e',
  d: 'q',
  e: 'p',
  alternativa_correta: 'c',
  pontos: 15,
  ativo: 's'
}
{
  _id: ObjectId('65d0fcc74ce4df5e8f9cdec8'),
  id_modulo: 1,
  titulo: 'Ordene as letras',
  conteudo: 'Exercício de ordenação de letras',
  tipo_desafio: 3,
  instrucao: 'Ordene as letras para formar uma palavra: A _ C _ O',
  a: 'cao',
  b: 'tatu',
  c: 'bala',
  d: 'quero',
  e: 'pato',
  alternativa_correta: 'a',
  pontos: 20,
  ativo: 's'
}
{
  _id: ObjectId('65d0fcc74ce4df5e8f9cdec9'),
  id_modulo: 2,
  titulo: 'Complete a sequência',
  conteudo: 'Complete a sequência alfabética',
  tipo_desafio: 1,
  instrucao: 'Complete a sequência: A, B, C, _, E',
  a: 'c',
  b: 't',
  c: 'd',
  d: 'q',
  e: 'p',
  alternativa_correta: 'c',
  pontos: 10,
  ativo: 's'
}
{
  _id: ObjectId('65d0fcc74ce4df5e8f9cdec9'),
  id_modulo: 2,
  titulo: 'Complete a sequência',
  conteudo: 'Complete a sequência alfabética',
  tipo_desafio: 1,
  instrucao: 'Complete a sequência: A, B, C, _, E',
  a: 'c',
  b: 't',
  c: 'd',
  d: 'q',
  e: 'p',
  alternativa_correta: 'c',
  pontos: 10,
  ativo: 's'
}

--- metodo 2 - listarPorIdModulo ----

db.alfabetizai.desafio.find({"id_modulo": 1}).pretty()
{
  _id: ObjectId('65d0fcc74ce4df5e8f9cdec6'),
  id_modulo: 1,
  titulo: 'Escolha a letra inicial',
  conteudo: 'Aprenda as consoantes',
  tipo_desafio: 1,
  instrucao: 'Marque a letra, que corresponde a primeira letra da palavra Banana.',
  pontos: 10,
  ativo: 's',
  a: 'c',
  b: 't',
  c: 'b',
  d: 'q',
  e: 'p',
  alternativa_correta: 'c'
}
{
  _id: ObjectId('65d0fcc74ce4df5e8f9cdec8'),
  id_modulo: 1,
  titulo: 'Ordene as letras',
  conteudo: 'Exercício de ordenação de letras',
  tipo_desafio: 3,
  instrucao: 'Ordene as letras para formar uma palavra: A _ C _ O',
  a: 'cao',
  b: 'tatu',
  c: 'bala',
  d: 'quero',
  e: 'pato',
  alternativa_correta: 'a',
  pontos: 20,
  ativo: 's'
}