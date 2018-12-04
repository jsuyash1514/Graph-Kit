# Graph Kit
[![Platform](https://img.shields.io/badge/platform-Android-yellow.svg)](https://www.android.com)
[![API](https://img.shields.io/badge/API-18%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=18)
[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](https://opensource.org/licenses/MIT)

This library allows you to plot different kinds of graphs from data points. The currently supported graphs are<br/>
Line Graph, Bar Graph and Pie Chart. This library also includes an EditGraphView which you can edit by dragging points<br/>
and also get normalized points from the curve.

# Table of Content
* [Usage](#usage)
* [Features](#features)
  * [Line Graph](#line-graph-usage)
  * [Pie Chart](#pie-chart-usage)
  * [Bar Graph](#bar-graph-usage)
  * [Edit Graph View](#editgraphview-usage)
* [Documentation](#api-documentation)
* [Guidelines for Contributors](#guidelines-for-contributors)
* [License](#license)

# Usage

Just add the following dependency in your app's `build.gradle`
```java
dependencies {
      implementation 'com.mdgiitr.suyash:graphkit:0.9.0'
}
```

# Features

## Line Graph Usage
There are two ways you can use this Graph: through XML or Java.
### XML

<img src="https://github.com/mdg-iitr/Graph-Kit/blob/master/images/line_graph.png" align = "right" height = "300px">

```xml
<com.mdgiitr.suyash.graphkit.LineGraph
        android:id="@+id/lineGraph"
        android:layout_width="700dp"
        android:layout_height="700dp"
        app:graph_color="#ff0000"
        app:label_text_size="25"
        app:line_thickness="8.0"
        app:scrollablex="true"/>    
```
---
Then use the View in your java file as follows:<br/>
```java
LineGraph lineGraph = findViewById(R.id.lineGraph);
```
### Java
You can use the following Java code to add the View in your desired layout.<br/>
```java
LineGraph lineGraph = new LineGraph(getApplicationContext(),700,700); //Pass view width and view height as parameters
//Then add the view to your layout
layout.addView(lineGraph);
```
---
To add Data Points to your Line Graph create an ArrayList of DataPoints and add them as shown below:
```java
        ArrayList<DataPoint> points = new ArrayList<>();
        points.add(new DataPoint(10,10));
        points.add(new DataPoint(100,50));
        points.add(new DataPoint(100,100));
        points.add(new DataPoint(150,200));
        lineGraph.setPoints(points);
```
## Bar Graph Usage
There are two ways you can use this Graph: through XML or Java.
### XML

<img src="https://github.com/mdg-iitr/Graph-Kit/blob/master/images/bar_graph.png" align = "right" height = "300px">

```xml
<com.mdgiitr.suyash.graphkit.BarGraph
        android:layout_width="700dp"
        android:layout_height="700dp"
        android:id="@+id/barGraph"
        app:label_text_size="25" />
       
```
---
Then use the View in your java file as follows:<br/>
```java
BarGraph barGraph = findViewById(R.id.barGraph);
```
### Java
You can use the following Java code to add the View in your desired layout.<br/>
```java
BarGraph barGraph = new BarGraph(getApplicationContext(),700,700); //Pass view width and view height as parameters
//Then add the view to your layout
layout.addView(barGraph);
```
---
To add Data to your Bar Graph create an ArrayList of DataPoint and add it as shown below:
```java
        ArrayList<DataPoint> points = new ArrayList<>();
        points.add(new DataPoint("2014",5, Color.parseColor("#34495E")));
        points.add(new DataPoint("2015",9, Color.parseColor("#EC7063")));
        points.add(new DataPoint("2016",2, Color.parseColor("#2ECC71")));
        points.add(new DataPoint("2017",4, Color.parseColor("#F5B041")));
        barGraph.setPoints(points);
 ```
 ## Pie Chart Usage 
 There are two ways you can use this Graph: through XML or Java.
 ### XML
 
 <img src="https://github.com/mdg-iitr/Graph-Kit/blob/master/images/pie_chart.png" align = "right" height = "300px">
 
 ```xml
 <com.mdgiitr.suyash.graphkit.PieChart
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:id="@+id/grid_pie"
        app:label_text_size="40"/>

 ```
 ---
 Then use the View in your java file as follows:<br/>
```java
PieChart pieChart = findViewById(R.id.pie_chart);
```
 ### Java
 You can use the following Java code to add the View in your desired layout.<br/>
 ```java
PieChart pieChart = new PieChart(getApplicationContext(),700,700); //Pass view width and view height as parameters
 //Then add the view to your layout
layout.addView(pieChart);
 ```
 ---
 To add Data to your Pie Chart create an ArrayList of DataPoint and add it as shown below:
 ```java
        ArrayList<DataPoint> points = new ArrayList<>();
        points.add(new DataPoint("Football",(float)40.1,Color.parseColor("#34495E")));
        points.add(new DataPoint("Cricket", (float)30.9, Color.parseColor("#EC7063")));
        points.add(new DataPoint("Basketball", (float)15.8,Color.parseColor("#2ECC71")));
        points.add(new DataPoint("Voleyball",(float)12.4,Color.parseColor("#F5B041")));
        pieChart.setPoints(points);
 ```
 
 ## EditGraphView Usage
 There are two ways you can use this Graph: through XML or Java.
 ### XML
 
 <img src="https://github.com/mdg-iitr/Graph-Kit/blob/master/images/edit_graph.gif" align = "right" height = "300px">
 
 ```xml
 <com.mdgiitr.suyash.graphkit.EditGraphView
        android:id="@+id/editgraphview"
        android:layout_width="350dp"
        android:layout_height="350dp"
        android:layout_marginEnd="8dp"
        android:layout_marginStart="8dp"
        android:layout_marginTop="56dp"/>
 ```
 ---
 Then use the View in your java file as follows:<br/>
 ```java
 final EditGraphView v = findViewById(R.id.editgraphview);
 ```
### Java
You can use the following Java code to add the View in your desired layout.<br/>
```java
EditGraphView v = new EditGraphView(this, 700, 700);//Pass view width and view height as parameters
 //Then add the view to your layout
layout.addView(pieChart);
```

# API Documentation
## LineGraph

| Property                    | Default values | Java method             | Attribute       | Description                                                                                                                   |
|-----------------------------|----------------|-------------------------|-----------------|-------------------------------------------------------------------------------------------------------------------------------|
| Data Points                 | NA             | .setPoints(...)     | NA    | Data Points to plot                                                                                               |
| Scroll X                 | false           | .setSCrollX(...)     | scrollablex     | Is the graph scrollable along the x-axis                                                                                                 |
| Scroll Y               | false       | .setScrollY(...)         | scrollabley         | Is the graph scrollable alog the y-axis|
| Trace Color for Line            | Color.BLACK         | .setGraphColor(...)        | graph_color        | Set the color for tracing the line |
| Label Text Size                    | 20          | .setLabelTextSize(...)       | label_text_size        | Size of text used in the label markings  |
| Grid Color                    | Color.LTGRAY          | .setGridColor(...)       | grid_color        | Set the color of the grid lines created  |
| Maximum number of divisions on each axis   | 50          | .setMaxDivisions(...)       | max_divisions        | Set the maximum number of divisions on each axis  |

## BarGraph

| Property                    | Default values | Java method             | Attribute       | Description                                                                                                                   |
|-----------------------------|----------------|-------------------------|-----------------|-------------------------------------------------------------------------------------------------------------------------------|
| Data Points                 | NA             | .setPoints(...)     | NA    | Data Points to plot |                                                                                               
| Label Text Size                    | 20          | .setLabelTextSize(...)       | label_text_size        | Size of text used in the label markings  |
| Space between two bars             | 10          | .setSpace(...)       | bar_space        | Set the spacing between two bars  |

## PieChart

| Property                    | Default values | Java method             | Attribute       | Description                                                                                                                   |
|-----------------------------|----------------|-------------------------|-----------------|-------------------------------------------------------------------------------------------------------------------------------|
| Data Points                 | NA             | .setPoints(...)     | NA    | Data Points to plot | 
| Label Text Size                    | 40          | .setLabelTextSize(...)       | label_text_size        | Size of text used in the label markings  |


## EditGraphView


| Property                    | Default values | Java method             | Attribute       | Description                                                                                                                   |
|-----------------------------|----------------|-------------------------|-----------------|-------------------------------------------------------------------------------------------------------------------------------|
| Line Thickness                | 12             | .lineThickness(...)     | line_thickness    | Data Points to plot |                                                                                               
| Line Color                  | Color.BLACK          | .lineColor(...)      | graph_color        | Set the color for tracing the line  |
| Get Y-coord from X-coord    | NA                 |   .getYFromX(...)        | NA                | Get Y-coord for a specific X between 0 and 1 |
| Set Touch Tolerance         | 20                  | .setTouchTolerance(...)  | touch_tolerance | Set Touch Tolerance for anchor points |



# Guidelines for Contributors

If you want to contribute to improve this library, please read [our guidelines](https://github.com/jsuyash1514/Graph-Kit/blob/master/CONTRIBUTING.md).

### Possible Improvements

* Line graph can be plotted for all 4 quadrants.
* Zoom-in and zoom-out features can be added to graphs. 
* Number of curves can be plotted in a single line graph to compare between the plots or to find the intersections.
* The Graphs can be made to plot in real-time.
* Snapping feature can be added in EditGraphView.
* Undo and Redo feature can be added in Edit graph.
* Polar Coordinate system can be added.
* Graphs can be plotted from functions and their roots, stationary points, integrals, etc. can be obtained from the graphs.
* Database can be integrated to store the graphs locally in the app. 

# License

**Graph Kit** is licensed under `MIT License`. View license [here](https://github.com/jsuyash1514/Graph-Kit/blob/master/LICENSE).  
