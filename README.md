# 🚀 Framework de Testes Unificados - Agibank

Este repositório contém uma solução de automação **Full Stack**, cobrindo testes de **Interface Web** e **API REST**. A
arquitetura foi desenhada em um modelo **multi-módulo Maven**, permitindo a execução independente ou combinada dos
contextos de teste.

---

## 🏗️ Arquitetura e Tecnologias

O projeto foi estruturado para ser escalável e de fácil manutenção, utilizando:

* **Linguagem:** Java 17 (LTS)
* **Core:** JUnit 5 (Jupyter) para orquestração.
* **API:** REST Assured com validação de **JSON Schema**.
* **Web:** Selenium WebDriver (configurado para execução em **Headless Mode** no CI).
* **Relatórios:** Allure Framework com histórico de execução (Trends).
* **CI/CD:** GitHub Actions com deploy automático para **GitHub Pages**.

---

## 🧪 Estratégia de Testes

### 1. Módulo Web (`teste-blogdoagi`)

Focado na validação do mecanismo de busca do Blog Agibank.

* **Técnicas:** Testes de busca positiva, negativa, caracteres especiais e termos vazios.
* **Destaque:** Implementação robusta de esperas (Waits) para lidar com a renderização de elementos assíncronos.

### 2. Módulo API (`teste-dogapi`)

Consome a [Dog CEO API](https://dog.ceo/dog-api/).

* **Validações:** Status codes, estrutura do corpo (Schemas), tipos de dados e performance (SLA de resposta < 5s).
* **Cenários:** Cobertura de endpoints de raças, sub-raças e imagens aleatórias, incluindo fluxos de exceção (404 e
  parâmetros inválidos).

---

## 📊 Relatórios e Observabilidade

Os relatórios são gerados automaticamente a cada execução do pipeline. O **Allure Report** consolida os resultados de
ambos os módulos em um único dashboard navegável.

🔗 **[Acesse aqui o Allure Report do Projeto](https://martyello.github.io/testes-automacao-agibank/)**

> **Nota:** O dashboard inclui screenshots em caso de falhas na camada Web e o log completo das requisições/respostas na
> camada de API.

---

## 🛠️ Execução Local

### Pré-requisitos

* JDK 17 ou superior.
* Maven 3.6+.
* Google Chrome (versão atualizada).

### Comandos principais

| Objetivo               | Comando                              |
|:-----------------------|:-------------------------------------|
| **Executar tudo**      | `mvn clean test`                     |
| **Apenas Web**         | `mvn clean test -pl teste-blogdoagi` |
| **Apenas API**         | `mvn clean test -pl teste-dogapi`    |
| **Abrir Allure Local** | `mvn allure:serve`                   |

---

## ⚙️ Pipeline de CI/CD

O workflow no GitHub Actions (`.github/workflows/ci.yml`) executa as seguintes etapas:

1. Setup do ambiente (Java, Chrome, Dependências).
2. Execução dos testes via Maven.
3. Coleta e unificação dos resultados do Allure.
4. Publicação do relatório na branch `gh-pages`.

---
**Autor:** Marcelo Alexandre do Nascimento (Senior Test Analyst)