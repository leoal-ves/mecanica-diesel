# Sistema de Gestão para Oficina Mecânica Diesel
## 📋 Sobre o Projeto

Este projeto foi desenvolvido em conjunto com uma colega durante a disciplina de Desenvolvimento de Projeto 2, com o objetivo de aplicar os conhecimentos adquiridos ao longo do curso no desenvolvimento de uma solução completa, contemplando backend, frontend, banco de dados e automação de processos.

A aplicação foi criada para atender uma necessidade real de uma oficina mecânica especializada em veículos diesel, que realizava o gerenciamento de clientes, veículos e serviços de forma manual. Esse processo dificultava o acompanhamento do histórico de manutenções e o contato com os clientes para agendamento de revisões futuras.

O sistema centraliza as informações da oficina, permitindo o cadastro de clientes e veículos, o registro dos serviços realizados e o envio automático de notificações por e-mail quando a próxima manutenção estiver próxima.
##🎯 Objetivos
Centralizar o gerenciamento de clientes e veículos.
Registrar os serviços realizados pela oficina.
Armazenar o histórico de manutenções.
Automatizar o envio de lembretes de revisão.
Melhorar o relacionamento e a fidelização dos clientes.
Reduzir o controle manual e a perda de informações.
## 🛠 Tecnologias Utilizadas
### Backend
Java
Spring Boot
Spring Data JPA
Hibernate
Spring Security
JWT (JSON Web Token)
Spring Mail
### Frontend
Thymeleaf
HTML5
CSS3
JavaScript

## Banco de Dados
MySQL
Serviços Externos
SMTP Gmail para envio de e-mails automáticos

## ⚙️ Funcionalidades
### 🔐 Autenticação
- Login seguro utilizando autenticação baseada em JWT.
- Controle de acesso aos módulos do sistema.
### 👤 Gestão de Clientes
- Cadastro de clientes.
- Consulta e atualização de informações.
### 🚛 Gestão de Veículos
- Cadastro de veículos vinculados aos clientes.
- Consulta do histórico de veículos.
### 🔧 Gestão de Serviços
- Registro de serviços realizados.
- Armazenamento de quilometragem e data da manutenção.
- Histórico completo de manutenções.
### 📧 Notificações Automáticas
- Verificação diária dos serviços cadastrados.
- Identificação de manutenções próximas do vencimento.
- Envio automático de e-mails personalizados aos clientes.
- Informações de contato da oficina incluídas na mensagem.
## 🏠 Dashboard Inicial
- Exibição dos serviços próximos da data de revisão.
- Acompanhamento rápido das manutenções pendentes.

## 🏗 Arquitetura da Aplicação
O sistema foi desenvolvido seguindo uma arquitetura em camadas:
```text
├── Controller
 - Responsável por receber as requisições HTTP.
├── Service
 - Contém as regras de negócio.
├── Repository
 - Responsável pelo acesso aos dados.
├── Entity
 - Representação das tabelas do banco de dados.
├── Security (JWT)
 - Configurações de autenticação e autorização com JWT.
├── Scheduler (Jobs Automáticos)
 - Rotinas automáticas para envio de notificações por e-mail.
└── Templates (Thymeleaf)
 - Rotinas automáticas para envio de notificações.
```

## 🔄 Fluxo de Funcionamento
O usuário realiza login no sistema.
Cadastra clientes e seus respectivos veículos.
Registra os serviços realizados.
O sistema armazena os dados no MySQL.
Um processo agendado verifica diariamente os serviços cadastrados.
Quando uma revisão se aproxima, um e-mail automático é enviado ao cliente.
O usuário pode acompanhar os serviços pendentes diretamente pela tela inicial.

## 📈 Resultados Obtidos

O projeto resultou em uma aplicação funcional capaz de:

Automatizar processos antes realizados manualmente.
Organizar o histórico de clientes, veículos e serviços.
Melhorar o acompanhamento das manutenções.
Reduzir falhas decorrentes da gestão manual.
Promover um contato proativo com os clientes por meio de notificações automáticas.
