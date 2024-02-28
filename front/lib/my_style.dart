import 'package:flutter/material.dart';

var mainTheme = ThemeData(
  scaffoldBackgroundColor: MyColors.black,
  fontFamily: 'NotoSansKR',
  textTheme: TextTheme(
    bodyLarge: TextStyle(color: Colors.white),
  ),
    iconTheme: IconThemeData(
      color: Colors.white,
    )
);

class MyColors{
  static final white = Colors.white;
  static final black = Colors.black;
  static final grey = Colors.grey;
  static final green = Color(0xFF00A200);
  static final light_grey = Color(0xFFDBDBDB);
  static final beige = Color(0xFFF6EDE4);
  static final sky_blue = Color(0xFF88B4C6);
}
class MyTextStyles{
  static final h1 = TextStyle(color: Colors.black,fontSize: 32,fontWeight: FontWeight.bold);
  static final h2 = TextStyle(color: Colors.black,fontSize: 24,fontWeight: FontWeight.bold);
  static final h3 = TextStyle(color: Colors.black,fontSize: 16,fontWeight: FontWeight.bold);
  static final h4 = TextStyle(color: Colors.black,fontSize: 12,fontWeight: FontWeight.bold);

  static final b1 = TextStyle(color: Colors.black,fontSize: 16,fontWeight: FontWeight.normal);
  static final b2 = TextStyle(color: Colors.black,fontSize: 12,fontWeight: FontWeight.normal);

  static final s1 = TextStyle(color: MyColors.grey,fontSize: 12,fontWeight: FontWeight.normal);

  static final b3 = TextStyle(color: Colors.black,fontSize: 16,fontWeight: FontWeight.normal);
  static final b4 = TextStyle(color: Colors.black,fontSize: 12,fontWeight: FontWeight.normal);
}