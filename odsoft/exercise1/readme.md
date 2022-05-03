# ODSOFT

## Class Assignment 1

#### Calculator App

To run the calculator app:

```console
gradle runApp
```

The calculator has several commands that can be ran. For instance, to add a number insert:

```console
add 5
```

In component 1 one can also calculate the factorial of a number with the following command:

```console
factorial
```

In component 3 it is possible to calculate the double of a number:

```console
double
```

As well as in component 4 it is also possible to calculate the exponential of a number with the following command:

```console
exponential ${EXPONENT}
```

Testing to the app can be done with the following command:

```console
gradle test
```

#### CMS Project

In component 1 this project has the functionality to list teachers, add a teacher and view its details. The details can be viewed by clicking on a teacher row in the list teachers table. A popup will appear with the teacher details.

In component 3 this project has the functionality to list teachers, add a teacher, view its details and delete. The details can be viewed by clicking on a teacher row in the list teachers table. A popup will appear with the teacher details.
To remove a teacher, select it's checkbox from the list and then press the button 'Delete'


In component 4 this project has the functionality to list teachers, add a teacher and view its details.

To run the CMS project:

```console
./gradlew gwtRun
```

To run unit tests:

```console
./gradlew test
```

To run integration tests:

```console
./gradlew integrationTest
```

A task can also be run to generate a report of the test suite.

For unit tests:
```console
./gradlew jacocoTestReport
```

For integration tests:

```console
./gradlew jacocoIntegrationReport
```

In this project there's an XML with the jenkins configuration for it.
The jenkins configuration has the following steps:

1. Builds the project
2. Tests the project
3. Generates WAR file
4. Generates Javadocs

The final step is as it follows on the different increments:

Component 1: Generates mutation report testing using pitest and publishes it

Component 3: Generates integration testing report and publishes it

Component 4: Publishes war file

Post build steps:
1. Publishes war file, javadocs and mutation report