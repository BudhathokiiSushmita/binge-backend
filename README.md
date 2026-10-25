# Binge Backend

REST API for **Binge**, an OTT (streaming platform) clone. It serves movie and TV show content and handles user signup and login. Built with Java, Spring Boot and MongoDB.

Frontend repository: [binge-frontend](https://github.com/BudhathokiiSushmita/binge-frontend)

Live demo: _coming soon_

## Features

- Browse movies and TV shows: featured content, full lists by type, details by ID, and search
- User signup and login endpoints
- Spring Security configuration
- Configurable frontend origin through an environment variable
- Docker support for containerized deployment

## Tech Stack

| Area | Technology |
|---|---|
| Language | Java 17 |
| Framework | Spring Boot, Spring Security, Spring Data MongoDB |
| Database | MongoDB (MongoDB Atlas) |
| Build tool | Maven |
| Containerization | Docker |

## API Endpoints

The content type (`{type}`) is defined in `ContentEnum`.

**Content** (`/content`)

| Method | Endpoint | Description |
|---|---|---|
| GET | `/content/featured/{type}` | Featured items of a type |
| GET | `/content/all/{type}` | All items of a type |
| GET | `/content/{id}` | Details of one item |
| GET | `/content/all/{type}/{search}` | Search items of a type |

**User** (`/user`)

| Method | Endpoint | Description |
|---|---|---|
| POST | `/user` | Register a new user |
| POST | `/user/authenticate` | Log in |

## Project Structure

```
src/main/java/com/sushmita/binge_back/
├── config/        Security configuration
├── constants/     Application constants and content type enum
├── controller/    REST controllers (content, user)
├── dto/           Data transfer objects
├── model/         MongoDB documents and response models
├── repository/    Spring Data MongoDB repositories
└── service/       Business logic
```

## Getting Started

### Prerequisites

- Java 17
- Maven (or use the included `./mvnw`)
- A MongoDB database, such as a free [MongoDB Atlas](https://www.mongodb.com/atlas) cluster
- Docker (optional)

### Configuration

The app reads its settings from environment variables, so no secrets are stored in the code.

| Variable | Required | Description |
|---|---|---|
| `MONGODB_URI` | Yes | MongoDB connection string, including the database name |
| `FRONT_URL` | No | Frontend origin. Defaults to `http://localhost:3000` |

### Run locally

```bash
export MONGODB_URI="mongodb+srv://<user>:<password>@<cluster-host>/<database>?retryWrites=true&w=majority"
./mvnw spring-boot:run
```

The API starts on `http://localhost:8080`.

### Run with Docker

The Dockerfile packages the jar built by Maven, so build the jar first:

```bash
./mvnw clean package -DskipTests
docker build -t binge-back .
docker run -p 8080:8080 -e MONGODB_URI="<your connection string>" binge-back
```

## Sample Data

`data-folder/db.json` contains sample movies and TV shows used during development.

## Author

Sushmita Budhathoki