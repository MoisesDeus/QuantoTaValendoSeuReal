
# Quanto tá valendo seu real? - Cotação Bot Twitter

Uma aplicação Java que busca a cotação atual do dólar e posta automaticamente no Twitter. O projeto utiliza uma arquitetura em camadas sem frameworks, focando em código Java puro.


## Estrutura do projeto

Camadas

- API: Classes responsáveis pela comunicação com APIs externas (Twitter e Cotação)
- DTO: Objetos de transferência de dados para formatação
- Model: Entidades do domínio
- Service: Lógica de negócio
- Main: Ponto de entrada da aplicação
## Configuração

1 - Crie um arquivo .env na raiz do projeto com as seguintes variáveis:

```bash
TWITTER_CONSUMER_KEY=sua_chave_aqui
TWITTER_CONSUMER_SECRET=seu_secret_aqui
TWITTER_ACCESS_TOKEN=seu_token_aqui
TWITTER_ACCESS_TOKEN_SECRET=seu_token_secret_aqui
```
2 - Certifique-se de que o arquivo .env está no .gitignore para não vazar suas credenciais.
    
## Como Executar

1 - Clone o repositório

2 - Configure o arquivo .env

3 - Execute o comando Maven:

```bash
mvn clean package
```
4 - Execute o JAR gerado:

```bash
java -jar target/seu-projeto-1.0-SNAPSHOT.jar
```

Ou execute diretamente pela sua IDE através da classe Application.java.

## Observações

- Certifique-se de ter as credenciais do Twitter configuradas corretamente
- O programa usa a data e hora do sistema para timestamping
- As mensagens são formatadas no padrão "Cotação: [VALOR] em [DATA E HORA]"
## Funcionalidades

- Busca cotação atual do dólar
- Formata os valores para exibição amigável
- Posta automaticamente no Twitter
- Tratamento de erros e logs de execução


## Aprendizados

- Integração com APIs externas, utilizando autenticação OAuth.

- Utilização do ObjectMapper com Jackson, para transformar Json em objeto Java e vice-versa.

- Princípios de responsabilidade única, cada pacote, classe e metódo com sua função.

