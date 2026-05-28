# Trello API Test Automation

Automated REST API testing project for Trello using RestAssured and JUnit 5.

## Tech Stack
- Java 17
- RestAssured 5.x
- JUnit 5
- Gradle
- Dotenv (for credentials management)

## Project Structure
src/test/java/
├── tests/
│   ├── get/          # GET endpoint tests
│   ├── create/       # POST endpoint tests
│   ├── update/       # PUT endpoint tests
│   └── delete/       # DELETE endpoint tests
├── arguments/
│   ├── Holders/      # Data holders for parameterized tests
│   └── providers/    # Arguments providers
├── consts/           # Endpoints and URL parameter constants
└── utils/            # Helper utilities

## Test Coverage
| Method | Endpoint | Scenario | Status |
|--------|----------|----------|--------|
| GET | /boards/{id} | Valid board | ✅ |
| GET | /boards/{id} | Invalid ID | ✅ |
| GET | /boards/{id} | Invalid auth | ✅ |
| POST | /boards | Create board | ✅ |
| POST | /boards | Invalid name | ✅ |
| POST | /boards | Invalid auth | ✅ |
| PUT | /boards/{id} | Update board | ✅ |
| PUT | /boards/{id} | Invalid ID | ✅ |
| DELETE | /boards/{id} | Delete board | ✅ |
| GET | /cards/{id} | Valid card | ✅ |
| GET | /cards/{id} | Invalid ID | ✅ |
| GET | /cards/{id} | Invalid auth | ✅ |
| POST | /cards | Create card | ✅ |
| POST | /cards | Invalid body | ✅ |
| PUT | /cards/{id} | Update card | ✅ |
| DELETE | /cards/{id} | Delete card | ✅ |

## Prerequisites
- Java 17+
- Gradle
- Trello account with API Key & Token

## Setup
1. Clone the repository
   git clone https://github.com/rusianahlm/trello-api-automation.git

2. Create `.env` file in root directory
   KEY=your_trello_api_key
   TOKEN=your_trello_api_token
   ANOTHER_KEY=another_user_api_key
   ANOTHER_TOKEN=another_user_api_token

3. Run the tests
   ./gradlew test

4. View report
   build/reports/tests/test/index.html

## What I Learned
- REST API testing with RestAssured
- Parameterized testing with JUnit 5
- Test data management with ArgumentsProvider
- Positive and negative test scenarios
- Authentication validation testing
