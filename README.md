# 🎫 Service Desk

> Sistema de gerenciamento de chamados (help desk) desenvolvido em Java Swing com banco de dados MariaDB/MySQL.

![Java](https://img.shields.io/badge/Java-17+-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Swing](https://img.shields.io/badge/Swing-GUI-007396?style=for-the-badge&logo=java&logoColor=white)
![MariaDB](https://img.shields.io/badge/MariaDB-003545?style=for-the-badge&logo=mariadb&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![GitHub](https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github&logoColor=white)

---

## 📖 Sobre o projeto

O **Service Desk** é uma aplicação desktop de estudo criada para simular o fluxo básico de um sistema de abertura e gestão de chamados técnicos.

O sistema permite que usuários se autentiquem, abram chamados, acompanhem o status e gerenciem os registros — tudo com uma interface simples feita em **Java Swing** e persistência em **MariaDB/MySQL** via **JDBC**.

Este projeto foi desenvolvido com fins **didáticos**, para praticar:

- 🧱 Arquitetura em camadas (`model`, `dao`, `view`, `util`)
- 🗄️ Integração Java ↔ banco de dados com JDBC
- 🎨 Interfaces gráficas com Java Swing
- 🔐 Autenticação de usuários
- 🔗 Relacionamentos entre tabelas (chave estrangeira)
- 📦 Versionamento com Git/GitHub

---

## ✨ Funcionalidades

### 👤 Usuários
- ✅ Cadastro de usuário com validação de login único
- ✅ Autenticação (login e senha)
- ✅ Controle de usuário ativo/inativo

### 🎫 Chamados
- ✅ Abertura de chamados vinculados ao usuário logado
- ✅ Listagem de chamados em tabela
- ✅ Atualização de chamados
- ✅ Exclusão de chamados
- ✅ Controle de status: `Aberto`, `Em Andamento`, `Resolvido`

---

## 🖥️ Tecnologias utilizadas

| Camada | Tecnologia |
|---|---|
| **Linguagem** | Java |
| **Interface** | Java Swing |
| **Banco de dados** | MariaDB / MySQL |
| **Persistência** | JDBC |
| **IDE** | Eclipse |
| **Versionamento** | Git + GitHub |

---

## 🗂️ Estrutura do projeto
