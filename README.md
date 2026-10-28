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

service_desk/
├── src/
│ └── br/com/service_desk/
│ ├── dao/ # Acesso ao banco (UsuarioDAO, ChamadoDAO)
│ ├── model/ # Entidades (Usuario, Chamado)
│ ├── util/ # ConnectionFactory e utilitários
│ └── view/ # Telas Swing (LoginFrame, ServiceDeskFrame...)
├── .gitignore
└── README.md



---

## 🗄️ Modelo de dados

### Tabela `usuarios`
| Coluna | Tipo | Descrição |
|---|---|---|
| `id` | INT (PK, AUTO_INCREMENT) | Identificador |
| `nome` | VARCHAR(100) | Nome do usuário |
| `email` | VARCHAR(120) UNIQUE | E-mail |
| `login` | VARCHAR(50) UNIQUE | Login de acesso |
| `senha` | VARCHAR(255) | Senha |
| `ativo` | TINYINT(1) | Se está ativo |
| `data_cadastro` | TIMESTAMP | Data de cadastro |

### Tabela `chamados`
| Coluna | Tipo | Descrição |
|---|---|---|
| `id` | INT (PK, AUTO_INCREMENT) | Identificador |
| `titulo` | VARCHAR(100) | Título do chamado |
| `descricao` | TEXT | Descrição detalhada |
| `solicitante` | VARCHAR(80) | Nome do solicitante |
| `status` | VARCHAR(30) | Status do chamado |
| `data_abertura` | TIMESTAMP | Data de abertura |
| `usuario_id` | INT (FK) | Referência ao usuário |

---

## ⚙️ Como executar

### Pré-requisitos
- ☕ **JDK 17+** instalado
- 🐬 **MariaDB** ou **MySQL** rodando
- 📦 Driver JDBC do MySQL (`mysql-connector-j`) adicionado ao *build path*
- 💻 Eclipse (ou outra IDE Java)

### 1. Criar o banco de dados

```sql
CREATE DATABASE service_desk;
USE service_desk;

CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(120) NOT NULL UNIQUE,
    login VARCHAR(50) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    ativo TINYINT(1) DEFAULT 1,
    data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE chamados (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    descricao TEXT,
    solicitante VARCHAR(80) NOT NULL,
    status VARCHAR(30) DEFAULT 'Aberto',
    data_abertura TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    usuario_id INT NOT NULL,
    CONSTRAINT fk_chamado_usuario
        FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);
## ⚙️ Como executar

### Pré-requisitos
- ☕ **JDK 17+** instalado
- 🐬 **MariaDB** ou **MySQL** rodando
- 📦 Driver JDBC do MySQL (`mysql-connector-j`) adicionado ao *build path*
- 💻 Eclipse (ou outra IDE Java)

### 1. Criar o banco de dados

```sql
CREATE DATABASE service_desk;
USE service_desk;

CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(120) NOT NULL UNIQUE,
    login VARCHAR(50) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    ativo TINYINT(1) DEFAULT 1,
    data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE chamados (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    descricao TEXT,
    solicitante VARCHAR(80) NOT NULL,
    status VARCHAR(30) DEFAULT 'Aberto',
    data_abertura TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    usuario_id INT NOT NULL,
    CONSTRAINT fk_chamado_usuario
        FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);

2. Configurar a conexão

Ajuste o arquivo ConnectionFactory.java com os dados do seu banco:
java

private static final String URL  = "jdbc:mysql://localhost:3306/service_desk";
private static final String USER = "root";
private static final String PASS = "sua_senha";

3. Executar

No Eclipse, abra LoginFrame.java e rode (Ctrl + F11).
Cadastre um usuário, faça login e comece a abrir chamados. 🚀



🛣️ Roadmap

    ☑

    Cadastro de usuários
    ☑

    Autenticação
    ☑

    CRUD de chamados
    □

    Hash de senha (SHA-256/BCrypt)
    □

    Filtro de chamados por usuário
    □

    Painel administrativo
    □

    Relatórios
    □

    Exportação para CSV/PDF
