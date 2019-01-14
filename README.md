# pokeapp
App Android feito em Kotlin consumindo a API https://pokeapi.co/ para consulta de Pokemons

Bibliotecas externas utilizadas:
- Instacapture para captura de screenshot
- Fuel-android para requisições http
- Picasso para buscar imagens (url) e atualizar ImageViews.

Descricão:
O Aplicativo realiza uma consulta simples de um pokémon pelo tipo. Ao entrar
no App é mostrado uma lista que o usuário pode selecionar pelo tipo do pokemon.
Ao escolher o tipo ele é mandado para a segunda tela, que disponibiliza a lista
de pokemons para que ele possa escolher. Por ultimo, após selecionar o pokemon,
ele é enviado para a tela de detalhes, onde mostra informações gerais do pokemon
(peso, altura, habilidades) e disponibiliza a opcão do usuário compartilhar as
informações do pokemon.

Estrutura do projeto:
A estrutura do projeto foi montada de modo a separar as funções do App para facilitar
a legibilidade e manutenção do código:
Activities - Montar a tela e suas funcionalidades.
Adapters - Responsáveis pelas criações das listas no app.
Models - Pacote que guarda as entidades do projeto.
Services - Pacote por onde é feito as chamadas para API.

