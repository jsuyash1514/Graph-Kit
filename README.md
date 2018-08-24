# Graph Library
[![Platform](https://img.shields.io/badge/platform-Android-yellow.svg)](https://www.android.com)
[![API](https://img.shields.io/badge/API-16%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=16)
[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](https://opensource.org/licenses/MIT)

This library allows you to plot different kinds of graphs fromn data points. The currently supported graphs are<br/>
Line Graph, Bar Graph and Pie Chart. This library also includes an EditGraphView which you can edit by dragging points<br/>
and also gt normalized points from the curve.

# Table of Content
* Usage
* Features
  * Line Graph
  * Pie Chart
  * Bar Graph
  * Edit Graph View
* Documentation
* Guidelines for Contributors
* License

# Usage

---------> Add dependencies in gradle 

# Features
 
## Line Graph Usage
### XML
```
<com.example.suyash.graphlibrary.LineGraph
        android:id="@+id/lineGraph"
        android:layout_width="700dp"
        android:layout_height="700dp"
        custom:graph_color="#ff0000"
        custom:label_text_size="25"
        custom:line_thickness="8.0"
        custom:scrollablex="true"
        tools:layout_editor_absoluteX="0dp"
        tools:layout_editor_absoluteY="0dp">
    </com.example.suyash.graphlibrary.LineGraph>    
```
### Java
```
com.example.suyash.graphlibrary.LineGraph lineGraph = new com.example.suyash.graphlibrary.LineGraph(getApplicationContext(),700,700);
```
To add Data Points to your Line Graph create an ArrayList of DataPoints and add them as shown below:
```
        ArrayList<DataPoint> points = new ArrayList<>();
        points.add(new DataPoint(10,10));
        points.add(new DataPoint(100,50));
        points.add(new DataPoint(100,100));
        points.add(new DataPoint(150,200));
        lineGraph.setPoints(points);
```









# Documentation

----------> Make a table listing all the member function (XML attributes, JAVA methods, Description, default value) Refer material shadow.

# Guideline for Contributors

If you want to contribute to improve this library, please read [our guidelines].<--- Link to CONTRIBUTING.md

-----------> Breifly describe the bugs or drawbacks if any

# License

**Graph Library** is licensed under `MIT License`. View license [here].  <----- Link to LICENSE.md
