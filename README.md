# Teste de Automação - Agibank

## 📌 Sobre o projeto

Este projeto tem como objetivo validar funcionalidades por meio de testes automatizados:
- **Testes Web** - Busca no Blog do Agibank (Selenium)
- **Testes API** - Dog CEO API (REST Assured)

---

## 🐕 Testes API - Dog CEO API

### Endpoints Testados

| Endpoint | Método | Descrição |
|----------|--------|-----------|
| `/breeds/list/all` | GET | Lista todas as raças |
| `/breed/{breed}/images` | GET | Imagens por raça |
| `/breeds/image/random` | GET | Imagem aleatória |

### Cenários de Teste

#### GET /breeds/list/all
- ✅ Retorna status 200 e status "success"
- ✅ Retorna lista não vazia de raças
- ✅ Contém raças conhecidas (bulldog, labrador, poodle)
- ✅ Retorna sub-raças para bulldog
- ✅ Retorna Content-Type JSON
- ✅ Responde em tempo aceitável (< 5s)

#### GET /breed/{breed}/images
- ✅ Retorna imagens para raça válida
- ✅ Retorna imagens para múltiplas raças (parametrizado)
- ✅ URLs das imagens são válidas
- ✅ Retorna erro 404 para raça inexistente
- ✅ Retorna imagens para sub-raça
- ✅ Responde em tempo aceitável (< 5s)

#### GET /breeds/image/random
- ✅ Retorna status 200 e status "success"
- ✅ Retorna URL válida de imagem
- ✅ Imagem possui extensão válida (jpg, png, gif)
- ✅ Retorna imagens diferentes (aleatoriedade)
- ✅ Retorna múltiplas imagens quando solicitado
- ✅ Respeita limite máximo de imagens
- ✅ Retorna Content-Type JSON
- ✅ Responde em tempo aceitável (< 5s)

---

## 🌐 Testes Web - Blog do Agi

### Cenários cobertos:
- ✅ Busca com termo válido
- ✅ Busca sem informar termo
- ✅ Busca com caracteres especiais
- ✅ Busca com termo inexistente

---

## 🛠️ Tecnologias utilizadas

| Tecnologia | Uso |
|------------|-----|
| Java 17 | Linguagem |
| Maven | Build/Dependências |
| JUnit 5 | Framework de testes |
| REST Assured | Testes API |
| Selenium WebDriver | Testes Web |
| Allure | Relatórios |
| GitHub Actions | CI/CD |

---

## 📂 Estrutura do projeto
testes-automacao-agibank/
├── teste-blogdoagi/              # Testes Web
│   └── src/test/java/web/
│       └── BuscaBlogAgibankTest.java
├── teste-dogapi/                 # Testes API
│   └── src/test/java/api/
│       ├── base/BaseTest.java
│       ├── model/DogApiResponse.java
│       └── tests/
│           ├── BreedsListTest.java
│           ├── BreedImagesTest.java
│           └── RandomImageTest.java
├── .github/workflows/ci.yml
├── pom.xml
└── README.md
---

## ▶️ Como executar

### Pré-requisitos
- Java 17+
- Maven 3.6+
- Google Chrome (para testes Web)

### Todos os testes
```bash
mvn clean test

Apenas testes Web
bash

Copiar código
mvn clean test -pl teste-blogdoagi

Apenas testes API
bash

Copiar código
mvn clean test -pl teste-dogapi

Gerar relatório Allure
bash

Copiar código
mvn clean test -pl teste-dogapi
mvn allure:serve -pl teste-dogapi

🔄 CI/CD
O projeto utiliza GitHub Actions para execução automática.
Os testes rodam a cada push na branch main.

👤 Autor
Marcelo Alexandre do Nascimento

