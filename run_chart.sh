#!/bin/bash

# Mude para o diretório onde este script está
cd "$(dirname "$0")"

# Executa o jar, especificando a classe ChartMain
java -cp target/QuantoTaValendoSeuReal-1.0-SNAPSHOT.jar br.com.mddeveloper.ChartMain
