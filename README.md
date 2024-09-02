# SpringCodeGen

SpringCodeGen is a powerful tool designed to generate Java classes with code snippets automatically. It leverages the capabilities of AI to create and modify Java classes efficiently, making development faster and more reliable.

## Features

- **Java Class Generation**: Automatically generate Java classes with predefined or custom code.
- **AI-Powered**: Utilizes AI to ensure that the generated code is clean, efficient, and adheres to best practices.
- **Customizable**: Adjust the generated content to fit your specific project needs.

## Getting Started

### Prerequisites

Before you begin, ensure you have met the following requirements:

- **Java 8** or higher installed on your machine.
- **Maven** installed and configured.
- **Git** for cloning the repository.
- An **Anthropic API Key** for AI-related tasks.

### Installation

1. **Clone the Repository**

   ```bash
   git clone https://github.com/yourusername/SpringCodeGen.git
   ```

2. **Navigate to the Project Directory**

   ```bash
   cd SpringCodeGen
   ```

3. **Replace the Placeholder with Your API Key**

   Open the `application.properties` file located in the `src/main/resources` directory, and replace `${ANTHROPIC_API_KEY}` with your actual Anthropic API Key.

4. **Build the Project**

   Use Maven to build the project:

   ```bash
   mvn clean install
   ```

### Usage

After setting up the project, you can start generating Java classes using the provided templates or by defining your own specifications.

1. **Run the Application**

   ```bash
   mvn spring-boot:run
   ```

2. **Generate Classes**

   Follow the instructions in the application to generate your Java classes.

### Contributing

Contributions are welcome! Please fork the repository and create a pull request with your changes.

### License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

This `README.md` should now accurately reflect the process of setting up and using the SpringCodeGen project.
