# Aplicador Dorminhoco

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)

> Status do Projeto: ✔️ Concluido ✔️

### Este repositório contém um aplicativo Java que exemplifica o uso eficiente de threads através da implementação do padrão de dorminhoco. O aplicativo simula um cenário em que várias tarefas precisam ser processadas por um número limitado de threads, usando o padrão de dorminhoco para otimizar a utilização dessas threads.

## ❓ Descrição do Problema ❓

### Muitas vezes, ao lidar com operações concorrentes em Java, é crucial gerenciar eficientemente o uso de threads para otimizar o processamento. O problema abordado por este projeto surge quando há uma lista de tarefas a serem processadas por um número limitado de threads, e desejamos aplicar o padrão de dorminhoco para maximizar a utilização dessas threads. O objetivo é garantir que, quando uma thread conclui uma tarefa, ela busca imediatamente uma nova tarefa para executar, mantendo as threads ocupadas e o processamento eficiente.

## 💡 Solução 💡

### Este projeto apresenta um aplicativo Java que demonstra a implementação do padrão de dorminhoco com semáforo para otimizar o uso de threads em um ambiente concorrente. A solução utiliza a criação de threads para processar uma lista de tarefas, aplicando o padrão de dorminhoco para garantir uma distribuição eficiente das tarefas entre as threads disponíveis.

https://github.com/caiquessantos/Aplicador-Dorminhoco-Threads-JAVA/assets/106163735/fca47eb3-8e73-4140-be4a-602da50a4964

- Vídeo de demonstração

## 🛠️ Funcionalidades 🛠️

### Este projeto demonstra um cenário clássico de barbeiro dorminhoco, em que:

- Padrão Dorminhoco: Utiliza threads para aplicar o padrão dorminhoco, assegurando que sempre há tarefas disponíveis para processamento.
- Controle de Threads: Permite configurar o número máximo de threads ativas simultaneamente.
- Demonstração Eficiente: Mostra como otimizar o uso de threads para distribuir o processamento de tarefas de forma eficiente.

### Este exemplo destina-se principalmente a fins educacionais, fornecendo uma visão prática de como as threads Java podem ser usadas para resolver problemas de concorrência.

## ⚠️ Pré-requisitos ⚠️

- `Java JDK (versão 8)`: https://www.oracle.com/br/java/technologies/downloads/

## 🛠️ Configuração 🛠️

### Clone o repositório para sua máquina local:

1. git clone https://github.com/caiquessantos/Aplicador-Dorminhoco-Threads-JAVA.git

### Abra o CMD no diretório do projeto

### Compile o código fonte:

1. javac Principal.java

### Execute o programa:

1. java Principal

## 💡 Técnicas e Tecnologias usadas 💡

- [Java](https://www.oracle.com/java/technologies/)
- SEMÁFORO
- POO
- JavaFX
- Padrão de Dorminhoco (Sleeping Pattern)
