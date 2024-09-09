# Lendable Shopping Basket

This project is an implementation of the Lendable tech test. It provides a simple shopping cart service with the ability to add, remove items, and generate an itemized receipt.

By [James Millner](https://jamesmillner.dev)

## Runtime

I built this against Java 21:

```
openjdk version "21.0.2" 2024-01-16
```

But should be fine on 22 also. Bad habbit of not updating my global java version on my personal machine.

## Technologies Used

- Kotlin
- Spring Boot
- Gradle

## Running Tests

To run the tests, simply execute the following command:

```sh
./gradlew test jacocoTestReport
```

The test report will be generated in the `build/jacocoHtml` directory.