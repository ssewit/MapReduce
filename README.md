# MapReduce_PI
<img width="553" alt="image" src="https://github.com/ssewit/MapReduce_PI/assets/105317921/bb1db023-c4d0-4772-b1cf-dbb87b606324">
<img width="562" alt="image" src="https://github.com/ssewit/MapReduce_PI/assets/105317921/6712f327-c45c-4b56-b846-ec52eebd2644">
<img width="372" alt="image" src="https://github.com/ssewit/MapReduce_PI/assets/105317921/babb7bcf-af91-4a50-8be3-6431418d170d">
<img width="427" alt="image" src="https://github.com/ssewit/MapReduce_PI/assets/105317921/152920f7-cc66-4cc3-8848-9b11fbd9a301">
<img width="372" alt="image" src="https://github.com/ssewit/MapReduce_PI/assets/105317921/46bf807a-3d7d-4999-b642-9f15c8023810">

Step 1: Generate an input file to the Pi MapReduce program
  
  Step 1.1: Create a regular Java program which accepts two command line arguments.
    R: The radius
    N: The number of (x, y) pairs to create
    The Java program then randomly generates N pairs of (x, y) and displays them on the standard output.
  
  Step 1.2: Run the program created in Step 1.1 and save the result in a file. The file is the input to Step 2's Pi MapReduce program.

Step 2: Ccreate a MapReduce program to calculate the numbers of inside darts and outside darts.

Step 3: Use the file generated in Step 1.2 as the input to execute the MapReduce program created in Step 2.

Step 4: Calculate Pi in the driver program based on the numbers of inside darts and outside darts.

## Test Result

Result in Hadoop environment

<img width="437" alt="image" src="https://github.com/ssewit/MapReduce_PI/assets/105317921/4465ad71-24de-45cd-81e7-861adcd90d49">


Test Case:

How many random numbers to generate: 1000000
Radius = 100
Pi = 3.119464

## Input data
```
  $ mkdir PiCalculation
  $ cd PiCalculation
  $ vi GenerateRandomNumbers.java
  $ javac GenerateRandomNumbers.java
  $ java -cp . GenerateRandomNumbers
```

## Ssh passphraseless setup
Now check that you can ssh to the localhost without a passphrase:
```
  $ cd hadoop-3.3.5/
  $ ssh-keygen -t rsa -P '' -f ~/.ssh/id_rsa
  $ cat ~/.ssh/id_rsa.pub >> ~/.ssh/authorized_keys
  $ chmod 0600 ~/.ssh/authorized_keys
  $ ssh localhost
```

## Make the HDFS directories required to execute MapReduce jobs(Copy input data to HDFS)

Make sure to replace the username with your username in the machine
```
  $ cd ..
  $ cd hadoop-3.3.5/
  $ bin/hdfs namenode -format
  $ sbin/start-dfs.sh
  $ wget http://localhost:9870/
  $ bin/hdfs dfs -mkdir /user
  $ bin/hdfs dfs -mkdir /user/username
  $ bin/hdfs dfs -mkdir /user/username/picalculate
  $ bin/hdfs dfs -mkdir /user/username/picalculate/input
  $ bin/hdfs dfs -put ../PiCalculation/PiCalculationInput /user/username/picalculate/input
```
## Code to calculate Pi value (java file)

```
  $ cd /hadoop-3.3.5
  $ vi PiCalculation.java      
```

* Compile PiCalculation.java and create a jar
```
  $ bin/hadoop com.sun.tools.javac.Main PiCalculation.java
  $ jar cf wc.jar PiCalculation*class  
```

## Run

* Execute
```
  $ bin/hadoop jar wc.jar PiCalculation /user/username/picalculate/input /user/username/picalculate/output
```

* Output
```
  $ bin/hdfs dfs -ls /user/username/picalculate/output
  $ bin/hdfs dfs -cat /user/username/picalculate/output/part-r-00000 
```

* Stop
```
  $ sbin/stop-dfs.sh
```
