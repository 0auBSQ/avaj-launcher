# avaj-launcher

Small java introduction program which shows java code structures through a simulator

## Build

You need javac to build the program.
```
javac *.java
```

## Usage

```
java Simulator [simulator file]
```
A sample file is given in the repo

## File structure

The first line is a single integer for the number of weather changes.
The following lines are an aircraft each, defined by 5 tokens separated by a single space : `[Aircraft type, Aircraft name, longitude, latitude, height]` :
```
Aircraft type : String [Helicopter, Baloon, JetPlane]
Aircraft name : String
longitude : int
latitude : int
height : int [0..100]
```
Example :
```
10
Helicopter H1 10 10 20
Baloon B1 50 -40 70
JetPlane J1 80 110 50
```
