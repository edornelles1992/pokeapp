# pokeapp
App Android feito em Kotlin consumindo a API https://pokeapi.co/ para consulta de Pokemons

Bibliotecas externas utilizadas:
- Instacapture para captura de screenshot
- Fuel-android para requisi��es http
- Picasso para buscar imagens (url) e atualizar ImageViews.

Descric�o:
O Aplicativo realiza uma consulta simples de um pok�mon pelo tipo. Ao entrar
no App � mostrado uma lista que o usu�rio pode selecionar pelo tipo do pokemon.
Ao escolher o tipo ele � mandado para a segunda tela, que disponibiliza a lista
de pokemons para que ele possa escolher. Por ultimo, ap�s selecionar o pokemon,
ele � enviado para a tela de detalhes, onde mostra informa��es gerais do pokemon
(peso, altura, habilidades) e disponibiliza a opc�o do usu�rio compartilhar as
informa��es do pokemon.

Estrutura do projeto:
A estrutura do projeto foi montada de modo a separar as fun��es do App para facilitar
a legibilidade e manuten��o do c�digo:
Activities - Montar a tela e suas funcionalidades.
Adapters - Respons�veis pelas cria��es das listas no app.
Models - Pacote que guarda as entidades do projeto.
Services - Pacote por onde � feito as chamadas para API.

