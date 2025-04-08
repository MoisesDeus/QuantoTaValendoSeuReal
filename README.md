
# Quanto tá valendo seu real? - Cotação Bot Twitter

![Java](https://img.shields.io/badge/java-17-blue)
![Twitter](https://img.shields.io/badge/twitter-follow-blue?logo=twitter&link=https://x.com/dev_mddeveloper)


Uma aplicação Java que busca a cotação do dólar (USD/BRL) e posta automaticamente no Twitter/X. O projeto utiliza uma arquitetura em camadas sem frameworks, focando em código Java puro. Ele oferece dois fluxos principais:

- Fluxo Diário: Busca a cotação diária e posta no X duas vezes por dia.
- Fluxo de Gráfico: Gera um gráfico com a cotação dos últimos 15 dias e posta no X no dia 15 e no último dia de cada mês.

## Estrutura do projeto

Camadas

#### API: Classes responsáveis pela comunicação com APIs externas.
- TwitterApiClient: Faz chamadas à API do Twitter/X (postagem de tweets e upload de mídia).
- CurrencyDailyApiClient: Busca a cotação diária do dólar via AwesomeAPI.
- CurrencyFifteenApiClient: Busca as cotações dos últimos 15 dias via AwesomeAPI.
- OAuthConfig: Gerencia a autenticação OAuth para o Twitter/X.
- MediaUploader: Faz upload de mídia (gráficos) pro Twitter/X.

#### DTO: Objetos de transferência de dados para formatação.
- CurrencyDTO: Formata a cotação diária pra postagem.
- TweetRequestDTO: Formata o texto do tweet (usado no fluxo do gráfico).

#### Model: Entidades do domínio.
- Currency: Representa a cotação diária (USD/BRL).
- CurrencyFifteen: Representa as cotações dos últimos 15 dias.
- Tweet: Representa um tweet a ser postado.

#### Service: Lógica de negócio.
- CurrencyService: Formata os dados da cotação diária.
- TwitterService: Orquestra a postagem de tweets (cotação diária e gráfico).
- ChartGenerator: Gera o gráfico das cotações dos últimos 15 dias usando JFreeChart.

#### Util: Classes utilitárias genéricas.
- HttpRequestSender: Envia requisições HTTP genéricas (usado pelo TwitterApiClient).

#### Main: Pontos de entrada da aplicação.
- DailyMain: Executa o fluxo diário (cotação diária).
- ChartMain: Executa o fluxo do gráfico (cotações dos últimos 15 dias).

## Configuração

1 - Crie um arquivo .env na raiz do projeto com as seguintes variáveis:

```bash
TWITTER_CONSUMER_KEY=sua_chave_aqui
TWITTER_CONSUMER_SECRET=seu_secret_aqui
TWITTER_ACCESS_TOKEN=seu_token_aqui
TWITTER_ACCESS_TOKEN_SECRET=seu_token_secret_aqui
```
2 - Certifique-se de que o arquivo .env está no .gitignore para não vazar suas credenciais.
```bash
echo ".env" >> .gitignore
```
3 - Certifique-se de ter o Java 11+ instalado (o projeto foi desenvolvido com OpenJDK 17).

## Como Executar

1 - Clone o repositório
```bash
git clone https://github.com/seu-usuario/QuantoTaValendoSeuReal.git
cd QuantoTaValendoSeuReal
```

2 - Configure o arquivo .env
Adicione suas credenciais do Twitter/X no arquivo .env, como descrito na seção de configuração.

3 - Execute o comando Maven:
```bash
mvn clean package
```
Isso gera o arquivo .jar em target/QuantoTaValendoSeuReal-1.0-SNAPSHOT.jar.

4 - Execute os fluxos
O projeto tem dois fluxos principais, cada um com seu próprio script de execução:

Fluxo Diário (DailyMain)
Busca a cotação diária e posta no X.
```bash
./run_daily.sh
```
Fluxo do Gráfico (ChartMain)
Gera um gráfico com as cotações dos últimos 15 dias e posta no X.
```bash
./run_chart.sh
```
Nota: Certifique-se de que os scripts run_daily.sh e run_chart.sh têm permissão de execução:
```bash
chmod +x run_daily.sh
chmod +x run_chart.sh
```
Executar Diretamente pela IDE
Você também pode executar diretamente pela sua IDE:
- Para o fluxo diário: Rode a classe DailyMain.
- Para o fluxo do gráfico: Rode a classe ChartMain.

## Automatização com Crontab
O projeto está configurado pra rodar automaticamente via crontab:
- Fluxo Diário: Roda duas vezes por dia (9h e 18h).
- Fluxo do Gráfico: Roda no dia 15 e no último dia de cada mês às 8h.

#### Configuração do Crontab
Edite o crontab com:
```bash
crontab -e
```
```bash
Adicione as seguintes linhas (substitua /caminho/do/projeto pelo caminho absoluto do seu projeto):
0 8 * * * /caminho/do/projeto/run_daily.sh >> /caminho/do/projeto/daily.log 2>&1
0 20 * * * /caminho/do/projeto/run_daily.sh >> /caminho/do/projeto/daily.log 2>&1
0 8 15 * * /caminho/do/projeto/run_chart.sh >> /caminho/do/projeto/chart.log 2>&1
0 8 28-31 * * /bin/bash -c '[ $(date -d tomorrow +\%d) = "01" ] && /caminho/do/projeto/run_chart.sh >> /caminho/do/projeto/chart.log 2>&1'
```
- Logs: Os arquivos daily.log e chart.log armazenam os logs de execução pra debug.

## Observações
- Certifique-se de que as credenciais do Twitter/X estão corretas no .env.
- O fluxo do gráfico usa o JFreeChart, que requer o modo headless pra rodar em ambientes sem interface gráfica (como servidores ou crontab). Isso já está configurado no ChartGenerator.
- O programa usa a data e hora do sistema pra timestamping.
#### As mensagens dos tweets são formatadas como:
- Fluxo diário: "Dólar Americano/Real Brasileiro [VALOR] [DATA]".
- Fluxo do gráfico: "Cotação USD/BRL - Últimos 15 Dias" (com o gráfico anexado).
- 
## Funcionalidades

- Busca a cotação diária do dólar (USD/BRL) via AwesomeAPI.
- Formata os valores pra exibição amigável.
- Posta automaticamente no Twitter/X duas vezes por dia.
- Busca as cotações dos últimos 15 dias e gera um gráfico com JFreeChart.
- Posta o gráfico no Twitter/X no dia 15 e no último dia de cada mês.
- Tratamento de erros e logs de execução.

## Aprendizados

- Integração com APIs externas (AwesomeAPI e Twitter/X), utilizando autenticação OAuth.
- Utilização do ObjectMapper (Jackson) pra transformar JSON em objetos Java e vice-versa.
- Geração de gráficos com JFreeChart, incluindo suporte a ambientes headless.
- Configuração de tarefas agendadas com crontab pra automatização.
- Princípios de responsabilidade única: cada pacote, classe e método com sua função específica.
- Separação de fluxos em classes main distintas pra facilitar execução e manutenção.

## Dependências
- Jackson: Pra parsing de JSON.
- JFreeChart: Pra geração de gráficos.
- Dotenv-java: Pra carregar variáveis de ambiente do .env.
- OAuth Signpost: Pra autenticação OAuth com o Twitter/X.





