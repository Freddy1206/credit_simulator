# Credit Simulator

## Overview
Credit Simulator is a Java-based console application designed to simulate vehicle loan calculations. This application supports user interaction via menus and file-based inputs. It also provides features for saving and loading calculations, integrated with REST APIs.

## Features
1. **Dynamic Menu**:
    - `show`: Displays all available commands.
    - `save sheet`: Saves calculation data to switch between sheets.
    - `load`: Fetches data from a predefined REST API for calculation.
2. **Input-Based Loan Simulation**:
    - Input vehicle type (Car/Bike).
    - Input vehicle condition (New/Used).
    - Input vehicle year and calculate loan eligibility based on the year.
    - Input loan amount and down payment.
    - Calculate monthly installments based on interest rates and tenure.
3. **REST API Integration**:
    - Fetches existing loan data from a web service.
4. **File and Console Support**:
    - Supports file-based inputs for batch processing.
    - Supports console-based user interaction.

## Technologies Used
- **Java 8**: Core language for implementation.
- **Maven**: Dependency management and build automation.
- **Docker**: Containerization for consistent deployment.
- **Log4j**: Logging framework for detailed application logging.
- **OkHttp**: HTTP client library for API integration.
- **Apache POI**: Library for handling Excel sheets.

## Project Structure
```
credit_simulator/
├── app/
│   ├── application/
│   │   ├── Main.java                 # Entry point of the application
│   │   ├── CreditSimulatorRequest.java # Models user input
│   │   ├── CreditCalculator.java     # Core loan calculation logic
│   │   └── MenuHandler.java          # Handles dynamic menu interactions
│   ├── biz/
│   │   ├── service-impl/             # Business service implementations
│   │   ├── shared/                   # Shared business logic
│   └── common/
│       ├── service/integration/      # REST API integration services
│       ├── dal/                      # Data access layer for saving/loading data
│       └── util/                     # Utility classes
├── Dockerfile                        # Dockerfile for containerization
├── pom.xml                           # Maven project configuration
└── README.md                         # Project documentation
```

## Prerequisites
Before running this application, ensure you have the following:
1. Java 8 or higher installed.
2. Maven installed for building the project.
3. Docker installed for containerization.

## How to Run the Application
### Without File Input:
1. Clone the repository:
   ```bash
   git clone <your-repo-url>
   cd credit_simulator
   ```
2. Build the application:
   ```bash
   mvn clean package
   ```
3. Run the application:
   ```bash
   java -jar target/credit-simulator-application-1.0.0-jar-with-dependencies.jar
   ```

### With File Input:
1. Prepare an input file (e.g., `file_inputs.txt`) with user input data.
2. Run the application with the file:
   ```bash
   java -jar target/credit-simulator-application-1.0.0-jar-with-dependencies.jar file_inputs.txt
   ```

### Using Docker:
1. Build the Docker image:
   ```bash
   docker build -t credit-simulator .
   ```
2. Run the Docker container:
   ```bash
   docker run -it --rm credit-simulator
   ```

## Sample Input
**Console Input:**
```
Enter your vehicle type (Mobil|Motor): Mobil
Enter your vehicle condition (Baru|Bekas): Baru
Enter your vehicle year: 2022
Enter your total credit amount: 50000000
Enter your down payment: 20000000
Enter your loan tenure (in years): 5
```

**File Input (`file_inputs.txt`):**
```
Mobil
Baru
2022
50000000
20000000
5
```

## Output Example
```
Year 1: Rp. 1,200,000.00/month, Interest Rate: 8%
Year 2: Rp. 1,230,000.00/month, Interest Rate: 8.1%
Year 3: Rp. 1,250,000.00/month, Interest Rate: 8.6%
...
```

## CI/CD Integration
The project uses GitHub Actions for CI/CD. The pipeline:
1. Builds the project using Maven.
2. Builds a Docker image.
3. Pushes the image to Docker Hub.

### GitHub Actions Workflow (`.github/workflows/ci-cd-docker.yml`):
```yaml
name: Build and Push Docker Image
on:
  push:
    branches:
      - main

jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - name: Checkout code
        uses: actions/checkout@v3
      - name: Set up Java 8
        uses: actions/setup-java@v3
        with:
          java-version: '8'
      - name: Build JAR file
        run: mvn clean package
  push-docker:
    runs-on: ubuntu-latest
    needs: build
    steps:
      - name: Login to Docker Hub
        uses: docker/login-action@v2
        with:
          username: ${{ secrets.DOCKER_USERNAME }}
          password: ${{ secrets.DOCKER_PASSWORD }}
      - name: Build and Push Docker Image
        run: |
          docker build -t credit-simulator .
          docker push credit-simulator
```

## License
This project is licensed under the MIT License. See `LICENSE` for details.

## Contact
For inquiries, contact Freddy Michael at [freddymichaelmanurung356@gamil.com](mailto:freddymichaelmanurung356@gmail.com).
