# transactions-analyzer

Build requirements:
Java 8
Maven 3.0

build command(will produce runnable transaction-analyzer-jar-with-dependencies.jar package in target folder):
mvn clean install


To run already build package please go to 'run' directory and use the following command (make sure JRE 1.8 is installed):

java -jar tanalyzer.jar data.csv "20/08/2018 12:00:00" "20/08/2018 13:00:00" Kwik-E-Mart
