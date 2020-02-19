# Agibank-sales-report

## Como usar
* Suporta apenas arquivos .dat 
* Quando colocado um .dat no diretório /data/in na raiz do projeto o sistema processa o arquivo, o relatório gerado fica no diretório /data/out na raiz do projeto, o arquivo é gerado com o mesmo nome do arquivo processado mais o sufixo "_report";
* Os arquivos processados com sucesso são movidos para o diretório /data/success localizado na raiz do projeto.


## Executar a aplicação:
* entrar na raiz do projeto e executar o comando mvn clean package
* entrar na raiz do projeto no diretório target e executar o comando (java -jar relatorio-vendas-0.0.1-SNAPSHOT.jar)
* Inserir arquivos no diretório /data/in para o processamento.

## Executar os testes:
* entrar na raiz do projeto e executar o comando (mvn clean test)